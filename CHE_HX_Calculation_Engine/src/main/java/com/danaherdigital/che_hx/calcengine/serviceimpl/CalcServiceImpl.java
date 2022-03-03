package com.danaherdigital.che_hx.calcengine.serviceimpl;

import java.security.InvalidParameterException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.util.Precision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.danaherdigital.che_hx.calcengine.dto.CalcReqDTO;
import com.danaherdigital.che_hx.calcengine.dto.RequestDTO;
import com.danaherdigital.che_hx.calcengine.service.ICalcService;
import com.danaherdigital.che_hx.calcengine.service.ILookUpService;
import com.danaherdigital.che_hx.calcengine.utils.ApplicationConstants;
import com.danaherdigital.che_hx.calcengine.utils.CalculationConstants;
import com.danaherdigital.che_hx.calcengine.utils.CalculationUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class CalcServiceImpl.
 */
@Service

/** The Constant log. */

/** The Constant log. */
@Slf4j
public class CalcServiceImpl implements ICalcService {

	@Autowired
	ILookUpService lookUpService;

	@Autowired
	private ICalcService calcService;

	/**
	 * Gets the calc data.
	 *
	 * @param req the req
	 * @return the calc data
	 */
	@Override
	public List<Object> getCalcData(CalcReqDTO req) {
		log.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getCalcData");
		String function = req.getFunction();
		if (Objects.isNull(function) || CollectionUtils.isEmpty(req.getInput())) {
			throw new InvalidParameterException();
		}
		List<Object> opDataList = Collections.synchronizedList(new ArrayList<>());
		switch (function) {
		case CalculationConstants.MASTERCALC:
			List<Map<String, Object>> composedInput = composeCalcData(req.getInput());

			if (!CollectionUtils.isEmpty(composedInput)) {
				getMasterCalcData(composedInput, opDataList);
			}
			break;

		case CalculationConstants.PERCENT_OF_FULL_LOAD:
			break;

		default:
			log.info("invalid function name");
		}
		return opDataList;

	}

	/**
	 * Gets the master calc data.
	 *
	 * @param composedInput the input list
	 * @param opDataList    the op data list
	 * @return the master calc data
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getMasterCalcData(List<Map<String, Object>> composedInput, List<Object> opDataList) {
		log.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getMasterCalcData");

		ExecutorService executor = Executors.newFixedThreadPool(15);
		List<Future<Map<String, Object>>> futureList = new ArrayList<>();

		for (Map<String, Object> map : composedInput) {
			futureList.add(executor.submit(new ProcessCalc(map, calcService)));
		}
		for (Iterator<Future<Map<String, Object>>> it = futureList.iterator(); it.hasNext();) {
			Future<? extends Map<String, Object>> f = it.next();
			try {
				opDataList.add(f.get());
			} catch (InterruptedException | ExecutionException e) {
				log.warn("Interrupted!{}", e);
				Thread.currentThread().interrupt();
			}
		}

		executor.shutdown();

		return opDataList;
	}

	@Override
	public synchronized Map<String, Object> calcProcess(Map<String, ?> inputMap) {
		Map<String, Object> outPutResult = Collections.synchronizedMap(new LinkedHashMap<String, Object>());
		List<RequestDTO> reqDTO;
		Map<String, Double> lookUpRes = null;
		if (!inputMap.isEmpty()) {
			try {

				reqDTO = createListDTOForSteam((Object) inputMap.get(CalculationConstants.CONDENSER_OPERATING_BP),
						ApplicationConstants.PRESSURE);
				lookUpRes = lookUpService.callLookUp(reqDTO);
				outPutResult.put(CalculationConstants.ID, inputMap.get(CalculationConstants.ID));
				double condenserSaturatedSteamTemp = 0;
				double steamLatentHeatEnthalpy = 0;
				double totalSteamEnthalpy = 0;
				if (!lookUpRes.isEmpty()) {
					condenserSaturatedSteamTemp = lookUpRes.get(ApplicationConstants.STEAM_TEMP);
					steamLatentHeatEnthalpy = lookUpRes.get(ApplicationConstants.HEAT_ENTHALPY);
					totalSteamEnthalpy = lookUpRes.get(ApplicationConstants.TOTAL_STEAM);
				}

				double percOfFullLoad = percentOfFullLoad(
						(Object) inputMap.get(CalculationConstants.GROSS_PLANT_PRODUCTION),
						(Object) inputMap.get(CalculationConstants.PLANT_MAX_LOAD));

				double averageCWInletWaterTemp = avgCWInletWaterTemp(
						(Object) inputMap.get(CalculationConstants.CW_TEMP_IN),
						(Object) inputMap.get(CalculationConstants.CW_TEMP_OUT));

				double averageCWOutletWaterTemp = avgCWOutletWaterTemp(
						(Object) inputMap.get(CalculationConstants.CW_TEMP_IN),
						(Object) inputMap.get(CalculationConstants.CW_TEMP_OUT));

				reqDTO = createListDTOForWcf(averageCWInletWaterTemp);
				lookUpRes = lookUpService.callLookUp(reqDTO);
				double cWInletTempCorrFactor = 0;
				if (!lookUpRes.isEmpty()) {
					cWInletTempCorrFactor = lookUpRes.get(ApplicationConstants.CORRECTION_FACTOR);
				}

				double measuredCWTR = measuredCWTR(averageCWInletWaterTemp, averageCWOutletWaterTemp);

				double extractionSteamFlow = extractionSteamFlow(
						(Object) inputMap.get(CalculationConstants.TOTAL_BOILER_STEAM_FLOW),
						(Object) inputMap.get(CalculationConstants.EXTRACTION_STEAM));

				double exhaustTurbineSteamFlow = exhaustTurbineSteamFlow(
						(Object) inputMap.get(CalculationConstants.TOTAL_BOILER_STEAM_FLOW),
						(Object) inputMap.get(CalculationConstants.TOTAL_ATTEMP_STEAM_FLOW),
						(Object) inputMap.get(CalculationConstants.TOTAL_COGEN_STEAM_FLOW), extractionSteamFlow,
						(Object) inputMap.get(CalculationConstants.NUMBER_OF_PRESSURES));

				double calculatedDesignMoistureContentInSteam = calculatedDesignMoistureContentInSteam(
						(Object) inputMap.get(CalculationConstants.CALCULATED_MOISTURE));

				double moistureContentInSteam = moistureContentInSteam(calculatedDesignMoistureContentInSteam,
						exhaustTurbineSteamFlow);
				double effectiveTurbineSteamflow = effectiveTurbineSteamFlow(exhaustTurbineSteamFlow,
						moistureContentInSteam);

				double condenserHeatDuty = condenserHeatDuty(effectiveTurbineSteamflow, steamLatentHeatEnthalpy);

				double targetTRBasedOnDutyDesignFlow = targetTRBasedOnDutyDesignFlow(condenserHeatDuty,
						(Object) inputMap.get(CalculationConstants.DESIGN_WATER_FLOW));

				double tRDeviation = tRDeviation(measuredCWTR, targetTRBasedOnDutyDesignFlow);

				double targetCWOutletTempOverall = targetCWOutletTempOverall(averageCWInletWaterTemp,
						targetTRBasedOnDutyDesignFlow);

				double udesignOverall = udesignOverall((Object) inputMap.get(CalculationConstants.DESIGN_UIDEAL),
						(Object) inputMap.get(CalculationConstants.TUBE_METAL_AND_WALL_CORR_FACTOR),
						cWInletTempCorrFactor);

				double uactualOverall = uactualOverall(udesignOverall,
						(Object) inputMap.get(CalculationConstants.DESIGN_CF));

				double nTUOverall = nTUOverall(uactualOverall,
						(Object) inputMap.get(CalculationConstants.DESIGN_WATER_FLOW),
						(Object) inputMap.get(CalculationConstants.CONDENSING_SURFACE_AREA));

				double targetSteamTempOverall = targetSteamTempOverall(averageCWInletWaterTemp,
						targetCWOutletTempOverall, nTUOverall);
				reqDTO = createListDTOForSteam(targetSteamTempOverall, ApplicationConstants.STEAM_TEMP);
				lookUpRes = lookUpService.callLookUp(reqDTO);

				double targetBPOverall = 0;
				double steamEnthalpyOverall = 0;
				if (!lookUpRes.isEmpty()) {
					targetBPOverall = lookUpRes.get(ApplicationConstants.PRESSURE);
					steamEnthalpyOverall = lookUpRes.get(ApplicationConstants.TOTAL_STEAM);
				}

				double bPdeviationOverall = bPdeviationOverall(
						(Object) inputMap.get(CalculationConstants.CONDENSER_OPERATING_BP), targetBPOverall);

				double heatRateDeviationOverall = heatRateDeviationOverall(
						(Object) inputMap.get(CalculationConstants.TYPICAL_HEAT_RATE_PENALTY),
						(Object) inputMap.get(CalculationConstants.TYPICAL_HEAT_RATE), bPdeviationOverall);
				double estExcessCO2EmissionsOverall = estExcessCO2EmissionsOverall(bPdeviationOverall,
						(Object) inputMap.get(CalculationConstants.TYPICAL_HEAT_RATE_PENALTY),
						(Object) inputMap.get(CalculationConstants.TYPICAL_HEAT_RATE),
						(Object) inputMap.get(CalculationConstants.GROSS_PLANT_PRODUCTION),
						(Object) inputMap.get(CalculationConstants.CO2_EMMISSION_EXPECTED));

				double steamEnthalpyDeviationOverall = steamEnthalpyDeviationOverall(totalSteamEnthalpy,
						steamEnthalpyOverall);

				double effectiveTurbineSteamFlow = effectiveTurbineSteamFlow(exhaustTurbineSteamFlow,
						moistureContentInSteam);

				double totalEnthalpyDeviationOverall = totalEnthalpyDeviationOverall(steamEnthalpyDeviationOverall,
						effectiveTurbineSteamFlow);

				double productionDeviationOverall = productionDeviationOverall(totalEnthalpyDeviationOverall,
						(Object) inputMap.get(CalculationConstants.TYPICAL_HEAT_RATE));

				double dollarLostPerDay = dollarLostPerDay(productionDeviationOverall,
						(Object) inputMap.get(CalculationConstants.TYPICAL_SALE_PRICE));

				double estExcessFuelBurnedOverall = estExcessFuelBurnedOverall(totalEnthalpyDeviationOverall,
						(Object) inputMap.get(CalculationConstants.TYPICAL_HEAT_RATE));

				double estExcessFuelDollarPerDayOverall = estExcessFuelDollarPerDayOverall(estExcessFuelBurnedOverall,
						(Object) inputMap.get(CalculationConstants.AVERAGE_FUEL_COSTS_PER_MMBTUS));

				double calcCurrentCWFlow = calcCurrentCWFlow(condenserHeatDuty, measuredCWTR);

				double calcCurrentCWVelocity = calcCurrentCWVelocity(calcCurrentCWFlow,
						(Object) inputMap.get(CalculationConstants.TOTAL_TUBE_ID_FLOW_AREA));

				double calcCWDeltaP = calcCWDeltaP((Object) inputMap.get(CalculationConstants.CW_INLET_PRESSURE),
						(Object) inputMap.get(CalculationConstants.CW_OUTLET_PRESSURE));

				reqDTO = createListDTOForHEIStd(calcCurrentCWVelocity,
						(Object) inputMap.get(CalculationConstants.TUBE_OD));
				lookUpRes = lookUpService.callLookUp(reqDTO);
				double calUideal = 0;
				if (!lookUpRes.isEmpty()) {
					calUideal = lookUpRes.get(ApplicationConstants.COEFFICIENT);
				}

				double calcUdesign = calcUdesign(cWInletTempCorrFactor,
						(Object) inputMap.get(CalculationConstants.TUBE_METAL_AND_WALL_CORR_FACTOR), calUideal);
				double measuredLMTD = measuredLMTD(averageCWOutletWaterTemp, averageCWInletWaterTemp,
						condenserSaturatedSteamTemp);
				double cWOutletTempCondenserOnly = cWOutletTempCondenserOnly(averageCWInletWaterTemp, measuredCWTR);
				double targetUactualCondenserOnly = targetUactualCondenserOnly(calcUdesign,
						(Object) inputMap.get(CalculationConstants.DESIGN_CF));

				double nTUCondenserOnly = nTUCondenserOnly(targetUactualCondenserOnly,
						(Object) inputMap.get(CalculationConstants.CONDENSING_SURFACE_AREA), calcCurrentCWFlow);

				double targetSteamTempCondenserOnly = targetSteamTempCondenserOnly(cWOutletTempCondenserOnly,
						averageCWInletWaterTemp, nTUCondenserOnly);

				reqDTO = createListDTOForSteam(targetSteamTempCondenserOnly, ApplicationConstants.STEAM_TEMP);
				lookUpRes = lookUpService.callLookUp(reqDTO);

				double targetBPCondenserOnly = 0;
				double steamEnthalpyCondenserOnly = 0;
				if (!lookUpRes.isEmpty()) {

					targetBPCondenserOnly = lookUpRes.get(ApplicationConstants.PRESSURE);
					steamEnthalpyCondenserOnly = lookUpRes.get(ApplicationConstants.TOTAL_STEAM);
				}

				double enthalpyDeviationCondenserOnly = enthalpyDeviationCondenserOnly(totalSteamEnthalpy,
						steamEnthalpyCondenserOnly);

				double totalEnthalpyDeviationCondenserOnly = totalEnthalpyDeviationCondenserOnly(
						enthalpyDeviationCondenserOnly, effectiveTurbineSteamFlow);

				double productionDeviationCondenserOnly = productionDeviationCondenserOnly(
						totalEnthalpyDeviationCondenserOnly,
						(Object) inputMap.get(CalculationConstants.TYPICAL_HEAT_RATE));
				double calUactual = calUactual(condenserHeatDuty,
						(Object) inputMap.get(CalculationConstants.CONDENSING_SURFACE_AREA), measuredLMTD);
				double currentCFPercentage = currentCFPercentage(calUactual, calcUdesign);

				double bPDeviationCondenserOnly = bPDeviationCondenserOnly(targetBPCondenserOnly,
						(Object) inputMap.get(CalculationConstants.CONDENSER_OPERATING_BP));

				double targetTTDCondenserOnly = targetTTDCondenserOnly(targetSteamTempCondenserOnly,
						cWOutletTempCondenserOnly);

				double measuredCWTTD = measuredCWTTD(averageCWOutletWaterTemp, condenserSaturatedSteamTemp);

				double tTDDeviation = tTDDeviation(measuredCWTTD, targetTTDCondenserOnly);

				double heatRateDeviationCondenserOnly = heatRateDeviationCondenserOnly(bPDeviationCondenserOnly,
						(Object) inputMap.get(CalculationConstants.TYPICAL_HEAT_RATE_PENALTY),
						(Object) inputMap.get(CalculationConstants.TYPICAL_HEAT_RATE));

				double cO2EmissionsDeviationCondenserOnly = cO2EmissionsDeviationCondenserOnly(
						heatRateDeviationCondenserOnly,
						(Object) inputMap.get(CalculationConstants.GROSS_PLANT_PRODUCTION),
						(Object) inputMap.get(CalculationConstants.CO2_EMMISSION_EXPECTED));

				double estExcessFuelBurnedCondenserOnly = estExcessFuelBurnedCondenserOnly(
						totalEnthalpyDeviationCondenserOnly,
						(Object) inputMap.get(CalculationConstants.TYPICAL_HEAT_RATE));

				double coolingTowerDeltaT = coolingTowerDeltaT(
						(Object) inputMap.get(CalculationConstants.COOLING_TOWER_RETURN_TEMP),
						(Object) inputMap.get(CalculationConstants.COOLING_TOWER_SUPPLY_TEMP),
						(Object) inputMap.get(CalculationConstants.CW_TEMP_IN),
						(Object) inputMap.get(CalculationConstants.CW_TEMP_OUT));

				double measuredITD = measuredITD(averageCWInletWaterTemp, condenserSaturatedSteamTemp);

				double atmpressurePsia = atmpressurePsia(
						(Object) inputMap.get(CalculationConstants.BAROMETRIC_PRESSURE));

				double atmpressureKpa = atmpressureKpa(atmpressurePsia);

				double partialPressureAtT = partialPressureAtT((Object) inputMap.get(CalculationConstants.AMBIENT_TEMP),
						(Object) inputMap.get(CalculationConstants.HUMIDITY));

				double t = t(partialPressureAtT);

				double ys1 = ys1(partialPressureAtT, atmpressureKpa);

				double ys = ys(ys1);

				double airEnthalpy = airEnthalpy(ys, (Object) inputMap.get(CalculationConstants.AMBIENT_TEMP));

				double vaporPressure = vaporPressure(airEnthalpy);

				Object calcWetBulb = calcWetBulb(vaporPressure,
						(Object) inputMap.get(CalculationConstants.AMBIENT_TEMP));

				double coolingTowerApproach = coolingTowerApproach(averageCWInletWaterTemp, calcWetBulb);

				outPutResult.put(CalculationConstants.PERCENT_OF_FULL_LOAD, Precision.round(percOfFullLoad, 2));
				outPutResult.put(CalculationConstants.AVERAGE_CW_INLET_WATER_TEMP, averageCWInletWaterTemp);
				outPutResult.put(CalculationConstants.AVERAGE_CW_OUTLET_WATER_TEMP, averageCWOutletWaterTemp);
				outPutResult.put(CalculationConstants.CW_INLET_TEMP_CORR_FACTOR, cWInletTempCorrFactor);
				outPutResult.put(CalculationConstants.CONDENSER_SATURATED_STEAM_TEMP, condenserSaturatedSteamTemp);
				outPutResult.put(CalculationConstants.MEASURED_LMTD, Precision.round(measuredLMTD, 2));
				outPutResult.put(CalculationConstants.MEASURED_ITD, Precision.round(measuredITD, 2));
				outPutResult.put(CalculationConstants.MEASURED_CW_TTD, Precision.round(measuredCWTTD, 2));
				outPutResult.put(CalculationConstants.MEASURED_CW_TR, Precision.round(measuredCWTR, 2));
				outPutResult.put(CalculationConstants.SPEC_SHEET_DESIGN_TR,
						inputMap.get(CalculationConstants.DESIGN_TR));

				outPutResult.put(CalculationConstants.COOLING_TOWER_DELTA_T, Precision.round(coolingTowerDeltaT, 2));
				outPutResult.put(CalculationConstants.EXTRACTION_STEAM_FLOW, Precision.round(extractionSteamFlow, 2));
				outPutResult.put(CalculationConstants.EXHAUST_TURBINE_STEAM_FLOW,
						Precision.round(exhaustTurbineSteamFlow, 2));
				outPutResult.put(CalculationConstants.CALCULATED_DESIGN_MOISTURE_CONTENT_IN_STEAM,
						Precision.round(calculatedDesignMoistureContentInSteam, 2));
				outPutResult.put(CalculationConstants.MOISTURE_CONTENT_IN_STEAM,
						Precision.round(moistureContentInSteam, 2));
				outPutResult.put(CalculationConstants.EFFECTIVE_TURBINE_STEAM_FLOW,
						Precision.round(effectiveTurbineSteamflow, 2));

				outPutResult.put(CalculationConstants.SPEC_SHEET_DESIGN_STEAM_FLOW,
						(Object) inputMap.get(CalculationConstants.DESIGN_STEAM_FLOW));
				outPutResult.put(CalculationConstants.STEAM_LATENT_HEAT_ENTHALPY, steamLatentHeatEnthalpy);
				outPutResult.put(CalculationConstants.TOTAL_STEAM_ENTHALPY, totalSteamEnthalpy);
				outPutResult.put(CalculationConstants.CONDENSER_HEAT_DUTY_Q, (long) condenserHeatDuty);
				outPutResult.put(CalculationConstants.TARGET_TR_BASED_ON_DUTY_DESIGN_FLOW,
						Precision.round(targetTRBasedOnDutyDesignFlow, 2));
				outPutResult.put(CalculationConstants.TR_DEVIATION, Precision.round(tRDeviation, 2));
				outPutResult.put(CalculationConstants.TARGET_CW_OUTLET_TEMP_OVERALL,
						Precision.round(targetCWOutletTempOverall, 2));
				outPutResult.put(CalculationConstants.U_DESIGN_OVERALL, Precision.round(udesignOverall, 2));
				outPutResult.put(CalculationConstants.U_ACTUAL_OVERALL, Precision.round(uactualOverall, 2));
				outPutResult.put(CalculationConstants.NTU_OVERALL, Precision.round(nTUOverall, 2));
				outPutResult.put(CalculationConstants.TARGET_STEAM_TEMP_OVERALL,
						Precision.round(targetSteamTempOverall, 2));
				outPutResult.put(CalculationConstants.TARGET_BP_OVERALL, targetBPOverall);
				outPutResult.put(CalculationConstants.STEAM_ENTHALPY_OVERALL, steamEnthalpyOverall);
				outPutResult.put(CalculationConstants.BP_DEVIATION_OVERALL, bPdeviationOverall);
				outPutResult.put(CalculationConstants.HEAT_RATE_DEVIATION_OVERALL,
						Precision.round(heatRateDeviationOverall, 2));
				outPutResult.put(CalculationConstants.EST_EXCESS_CO2_EMISSIONS_OVERALL,
						(long) estExcessCO2EmissionsOverall);
				outPutResult.put(CalculationConstants.STEAM_ENTHALPY_DEVIATION_OVERALL,
						Precision.round(steamEnthalpyDeviationOverall, 2));
				outPutResult.put(CalculationConstants.TOTAL_ENTHALPY_DEVIATION_OVERALL,
						Precision.round(totalEnthalpyDeviationOverall, 2));
				outPutResult.put(CalculationConstants.PRODUCTION_DEVIATION_OVERALL,
						Precision.round(productionDeviationOverall, 2));
				outPutResult.put(CalculationConstants.DOLLAR_LOST_PER_DAY, Precision.round(dollarLostPerDay, 2));
				outPutResult.put(CalculationConstants.EST_EXCESS_FUEL_BURNED_OVERALL,
						(long) estExcessFuelBurnedOverall);
				outPutResult.put(CalculationConstants.EST_EXCESS_FUEL_DOLLAR_PER_DAY_OVERALL,
						Precision.round(estExcessFuelDollarPerDayOverall, 2));
				outPutResult.put(CalculationConstants.CALC_CURRENT_CW_FLOW, Precision.round(calcCurrentCWFlow, 2));
				outPutResult.put(CalculationConstants.SPEC_SHEET_DESIGN_CW_FLOW,
						(Object) inputMap.get(CalculationConstants.DESIGN_WATER_FLOW));
				outPutResult.put(CalculationConstants.CALC_CURRENT_CW_VELOCITY,
						Precision.round(calcCurrentCWVelocity, 2));
				outPutResult.put(CalculationConstants.SPEC_SHEET_DESIGN_CW_VELOCITY,
						inputMap.get(CalculationConstants.CALCULATED_VELOCITY));

				outPutResult.put(CalculationConstants.CALC_CW_DELTA_P, Precision.round(calcCWDeltaP, 2));
				outPutResult.put(CalculationConstants.SPEC_SHEET_DESIGN_CW_DELTA_P,
						(Object) inputMap.get(CalculationConstants.DESIGN_CW_PRESSURE_DROP));
				outPutResult.put(CalculationConstants.CAL_UIDEAL, calUideal);
				outPutResult.put(CalculationConstants.CAL_UDESIGN, Precision.round(calcUdesign, 2));
				outPutResult.put(CalculationConstants.CAL_UACTUAL, Precision.round(calUactual, 2));
				outPutResult.put(CalculationConstants.SPEC_SHEET_DESIGN_U_ACTUAL,
						inputMap.get(CalculationConstants.DESIGN_UACTUAL));
				outPutResult.put(CalculationConstants.CURRENT_CF_PERCENTAGE, Precision.round(currentCFPercentage, 2));
				outPutResult.put(CalculationConstants.SPEC_SHEET_DESIGN_CF,
						inputMap.get(CalculationConstants.DESIGN_CF));
				outPutResult.put(CalculationConstants.TARGET_UACTUAL_CONDENSER_ONLY,
						Precision.round(targetUactualCondenserOnly, 2));
				outPutResult.put(CalculationConstants.NTU_CONDENSER_ONLY, Precision.round(nTUCondenserOnly, 2));
				outPutResult.put(CalculationConstants.CW_OUTLET_TEMP_CONDENSER_ONLY,
						Precision.round(cWOutletTempCondenserOnly, 2));
				outPutResult.put(CalculationConstants.TARGET_STEAM_TEMP_CONDENSER_ONLY,
						Precision.round(targetSteamTempCondenserOnly, 2));
				outPutResult.put(CalculationConstants.TARGET_BP_CONDENSER_ONLY, targetBPCondenserOnly);
				outPutResult.put(CalculationConstants.BP_DEVIATION_CONDENSER_ONLY,
						Precision.round(bPDeviationCondenserOnly, 2));
				outPutResult.put(CalculationConstants.TARGET_TTD_CONDENSER_ONLY,
						Precision.round(targetTTDCondenserOnly, 2));
				outPutResult.put(CalculationConstants.TTD_DEVIATION, Precision.round(tTDDeviation, 2));
				outPutResult.put(CalculationConstants.STEAM_ENTHALPY_CONDENSER_ONLY, steamEnthalpyCondenserOnly);
				outPutResult.put(CalculationConstants.ENTHALPY_DEVIATION_CONDENSER_ONLY,
						Precision.round(enthalpyDeviationCondenserOnly, 2));
				outPutResult.put(CalculationConstants.TOTAL_ENTHALPY_DEVIATION_CONDENSER_ONLY,
						Precision.round(totalEnthalpyDeviationCondenserOnly, 2));
				outPutResult.put(CalculationConstants.PRODUCTION_DEVIATION_CONDENSER_ONLY,
						Precision.round(productionDeviationCondenserOnly, 2));
				outPutResult.put(CalculationConstants.HEAT_RATE_DEVIATION_CONDENSER_ONLY,
						Precision.round(heatRateDeviationCondenserOnly, 2));
				outPutResult.put(CalculationConstants.CO2_EMISSIONS_DEVIATION_CONDENSER_ONLY,
						Precision.round(cO2EmissionsDeviationCondenserOnly, 2));
				outPutResult.put(CalculationConstants.EST_EXCESS_FUEL_BURNED_CONDENSER_ONLY,
						Precision.round(estExcessFuelBurnedCondenserOnly, 2));
				outPutResult.put(CalculationConstants.ATM_PRESSURE_PSIA, Precision.round(atmpressurePsia, 2));
				outPutResult.put(CalculationConstants.ATM_PRESSURE_KPA, Precision.round(atmpressureKpa, 2));
				outPutResult.put(CalculationConstants.PARTIAL_PRESSURE_AT_T, Precision.round(partialPressureAtT, 2));
				outPutResult.put(CalculationConstants.T, Precision.round(t, 2));
				outPutResult.put(CalculationConstants.YS1, Precision.round(ys1, 2));
				outPutResult.put(CalculationConstants.YS, Precision.round(ys, 2));
				outPutResult.put(CalculationConstants.MASS_ABS_KG_H2O_PER_KG_AIR, Precision.round(ys, 2));
				outPutResult.put(CalculationConstants.AIR_ENTHALPY, Precision.round(airEnthalpy, 2));
				outPutResult.put(CalculationConstants.VAPOR_PRESSURE, Precision.round(vaporPressure, 2));
				outPutResult.put(CalculationConstants.CALC_WET_BULB, calcWetBulb);
				outPutResult.put(CalculationConstants.COOLING_TOWER_APPROACH, Precision.round(coolingTowerApproach, 2));

				outPutResult.put("timeStamp", inputMap.get("timeStamp"));

			} catch (Exception e) {
				log.error("error in CalcServiceImpl.getMasterCalcData ->{}", e);

			}
		}
		return outPutResult;
	}

	/**
	 * Percent of full load.
	 *
	 * @param gross_Plant_Production the gross plant production
	 * @param plant_Max_Load         the plant max load
	 * @return the double
	 */
	private double percentOfFullLoad(Object grossPlantProduction, Object plantMaxLoad) {
		// Gross Plant Production (MW)/Plant Max Load *100

		if (!Objects.isNull(grossPlantProduction) && !Objects.isNull(plantMaxLoad)) {
			double grossPlantProductionNew = Double.parseDouble(grossPlantProduction.toString());
			double plantMaxLoadNew = Double.parseDouble(plantMaxLoad.toString());

			return (grossPlantProductionNew / plantMaxLoadNew * CalculationConstants.PERCENT_OF_FULL_LOAD_CONSTANT);
		}
		return 0;
	}

	/**
	 * Avg CW inlet water temp.
	 *
	 * @param CW_Temp_In  the c W temp in
	 * @param CW_Temp_Out the c W temp out
	 * @return the double
	 */
	private double avgCWInletWaterTemp(Object cwTempIn, Object cwTempOut) {
		double cWTempInNew = Double.parseDouble(cwTempIn.toString());
		double cWTempOutNew = Double.parseDouble(cwTempOut.toString());

		double avgCWInletWaterTemp = Math.min(cWTempInNew, cWTempOutNew);
		avgCWInletWaterTemp = Precision.round(avgCWInletWaterTemp, 2);

		return avgCWInletWaterTemp;
	}

	/**
	 * Avg CW outlet water temp.
	 *
	 * @param CW_Temp_In  the c W temp in
	 * @param CW_Temp_Out the c W temp out
	 * @return the double
	 */
	private double avgCWOutletWaterTemp(Object cwTempIn, Object cwTempOut) {
		double cWTempInNew = Double.parseDouble(cwTempIn.toString());
		double cWTempOutNew = Double.parseDouble(cwTempOut.toString());
		double avgCWOutletWaterTemp = Math.max(cWTempInNew, cWTempOutNew);
		avgCWOutletWaterTemp = Precision.round(avgCWOutletWaterTemp, 2);
		return avgCWOutletWaterTemp;
	}

	/**
	 * Measured LMTD.
	 *
	 * @param average_CW_Outlet_Water_Temp the average C W outlet water temp
	 * @param average_CW_inlet_Water_Temp  the average C W inlet water temp
	 * @param CondenserSaturatedSteamTemp  the condenser saturated steam temp
	 * @return the double
	 */
	private double measuredLMTD(double averageCwOutletWaterTemp, double averageCwInletWaterTemp,
			Object condenserSaturatedSteamTemp) {

		/*
		 * (Average CW Outlet Water Temp (f)-Average CW Inlet Water Temp (f)) /Log base
		 * e ((Condenser Saturated Steam Temp (f)-Average CW Inlet Water Temp)
		 * /(Condenser Saturated Steam Temp (f)-Average CW Outlet Water Temp (f)))
		 */

		double condenserSaturatedsteamtemp = Double.parseDouble(condenserSaturatedSteamTemp.toString());
		return (averageCwOutletWaterTemp - averageCwInletWaterTemp)
				/ Math.log((condenserSaturatedsteamtemp - averageCwInletWaterTemp)
						/ (condenserSaturatedsteamtemp - averageCwOutletWaterTemp));
	}

	/**
	 * Measured ITD.
	 *
	 * @param average_CW_inlet_Water_Temp the average C W inlet water temp
	 * @param CondenserSaturatedSteamTemp the condenser saturated steam temp
	 * @return the double
	 */
	private double measuredITD(double averageCwInletWaterTemp, Object condenserSaturatedSteamTemp) {
		// (Condenser Saturated Steam Temp (f)-Average CW Inlet Water Temp)

		double condenserSaturatedSteamTempNew = Double.parseDouble(condenserSaturatedSteamTemp.toString());
		return condenserSaturatedSteamTempNew - averageCwInletWaterTemp;
	}

	/**
	 * Measured CWTR.
	 *
	 * @param avgCWInletWaterTemp  the avg CW inlet water temp
	 * @param avgCWOutletWaterTemp the avg CW outlet water temp
	 * @return the double
	 */
	private double measuredCWTR(double avgCWInletWaterTemp, double avgCWOutletWaterTemp) {

		return avgCWOutletWaterTemp - avgCWInletWaterTemp;

	}

	/**
	 * Measured CWTTD.
	 *
	 * @param average_CW_Outlet_Water_Temp the average C W outlet water temp
	 * @param CondenserSaturatedSteamTemp  the condenser saturated steam temp
	 * @return the double
	 */
	private double measuredCWTTD(double avgCWOutletWaterTemp, Object condenserSaturatedSteamTemp) {
		// (Condenser Saturated Steam Temp (f)-Average CW Outlet Water Temp (f))

		double condenserSaturatedsteamtempnew = Double.parseDouble(condenserSaturatedSteamTemp.toString());
		return condenserSaturatedsteamtempnew - avgCWOutletWaterTemp;
	}

	/**
	 * Cooling tower delta T.
	 *
	 * @param cooling_Tower_Return_Temp the cooling tower return temp
	 * @param cooling_Tower_Supply_Temp the cooling tower supply temp
	 * @param CW_Temp_In                the c W temp in
	 * @param CW_Temp_Out               the c W temp out
	 * @return the double
	 */
	private double coolingTowerDeltaT(Object coolingTowerReturnTemp, Object coolingTowerSupplyTemp, Object cwTempIn,
			Object cwTempOut) {

		// IF(Cooling Tower Return Temp (f) > 0) then (Cooling Tower Supply Temp (f) -
		// Cooling Tower Return Temp (f)) else (CW Temp Out (f) -CW Temp In (f))

		double coolingTowerReturnTempNew = (!Objects.isNull(coolingTowerReturnTemp))
				? Double.parseDouble(coolingTowerReturnTemp.toString())
				: 0;
		double coolingTowerSupplyTempNew = (!Objects.isNull(coolingTowerSupplyTemp))
				? Double.parseDouble(coolingTowerSupplyTemp.toString())
				: 0;
		double cwTempInNew = Double.parseDouble(cwTempIn.toString());
		double cwTempOutNew = Double.parseDouble(cwTempOut.toString());

		return (coolingTowerReturnTempNew > 0) ? (coolingTowerSupplyTempNew - coolingTowerReturnTempNew)
				: (cwTempOutNew - cwTempInNew);
	}

	/**
	 * Extraction steam flow.
	 *
	 * @param total_boiler_steam_flow -summed value of all boilers steam flow
	 * @param extraction_steam        -design data
	 * @return
	 * @return the long
	 */
	private double extractionSteamFlow(Object totalBoilerSteamFlow, Object extractionSteam)

	{

		// Total Boiler Steam Flow(kpph)*1000*Extraction Steam/100
		return (Double.parseDouble(totalBoilerSteamFlow.toString())
				* CalculationConstants.EXTRACTION_STEAM_FLOW_CONSTANT1 * Double.parseDouble(extractionSteam.toString())
				/ CalculationConstants.EXTRACTION_STEAM_FLOW_CONSTANT2);

	}

	/**
	 * Exhaust turbine steam flow.
	 *
	 * @param total_boiler_steam_flow the total boiler steam flow
	 * @param total_attemp_steam_flow -summed value of all Attemperatrion steam flow
	 * @param total_cogen_steam_flow  -summed value of all cogen steam flow
	 * @param extraction_steam_flow   the extraction steam flow
	 * @param no_of_pressure          the no of pressure
	 * @return the long
	 */
	private double exhaustTurbineSteamFlow(Object totalBoilerSteamFlow, Object totalAttempSteamFlow,
			Object totalCogenSteamFlow, Object extractionSteamFlow, Object noOfPressure)

	{
		// ((AD2*1000)+(AE2*1000)-(AF2*1000)-AY2)/'Design Data'!$B$2

		// ((Total Boiler Steam Flow (kpph)*1000)
		// +(Total Attemp Steam Flow (kpph)*1000)-(Total Cogen Steam Flow
		// (kpph)*1000)-Extraction Steam Flow)/Number of Pressures

		double totalBoilerSteamFlowNew = Double.parseDouble(totalBoilerSteamFlow.toString());
		double totalAttempSteamFlowNew = Double.parseDouble(totalAttempSteamFlow.toString());
		double totalCogenSteamFlowNew = Double.parseDouble(totalCogenSteamFlow.toString());
		double extractionSteamFlowNew = Double.parseDouble(extractionSteamFlow.toString());
		double noOfPressureNew = Double.parseDouble(noOfPressure.toString());
		return (((totalBoilerSteamFlowNew * CalculationConstants.EXHAUST_TURBINE_STEAM_FLOW_CONSTANT)
				+ (totalAttempSteamFlowNew * CalculationConstants.EXHAUST_TURBINE_STEAM_FLOW_CONSTANT)
				- (totalCogenSteamFlowNew * CalculationConstants.EXHAUST_TURBINE_STEAM_FLOW_CONSTANT)
				- extractionSteamFlowNew) / noOfPressureNew);

	}

	/**
	 * Calculated design moisture content in steam.
	 *
	 * @param calculated_percentage_Moisture the calculated percentage moisture
	 * @return the double
	 */
	private double calculatedDesignMoistureContentInSteam(Object calculatedPercentageMoisture) {

		double d = Double.parseDouble(calculatedPercentageMoisture.toString());

		// If(Calculated percentage Moisture >=0) then Calculated percentage Moisture
		// else 10
		return (d >= 0) ? d : 10;

	}

	/**
	 * Moisture content in steam.
	 *
	 * @param calculated_Design_Moisture_ContentInSteam the calculated design
	 *                                                  moisture content in steam
	 * @param exhaustTurbineSteamFlow                   the exhaust turbine steam
	 *                                                  flow
	 * @return the long
	 */
	private double moistureContentInSteam(double calculatedDesignMoistureContentinsteam,
			double exhaustTurbineSteamFlow) {
//Calculated Design percentage Moisture Content in Steam/100*Exhaust Turbine Steam Flow (lb/hr)

		return (calculatedDesignMoistureContentinsteam / CalculationConstants.MOISTURE_CONTENT_IN_STEAM_COSTANT
				* exhaustTurbineSteamFlow);

	}

	/**
	 * Effective turbine steam flow.
	 *
	 * @param exhaustTurbineSteamFlow  the exhaust turbine steam flow
	 * @param moisture_Content_InSteam the moisture content in steam
	 * @return the double
	 */
	private double effectiveTurbineSteamFlow(double exhaustTurbineSteamFlow, double moistureContentInsteam) {
		// Exhaust Turbine Steam Flow-Moisture Content in Steam
		return exhaustTurbineSteamFlow - moistureContentInsteam;
	}

	/**
	 * Condenser heat duty.
	 *
	 * @param effective_Turbine_SteamFlow the effective turbine steam flow
	 * @param steam_Latent_Heat_Enthalpy  the steam latent heat enthalpy
	 * @return the long
	 */
	private double condenserHeatDuty(double effectiveTurbineSteamflow, double steamLatentHeatEnthalpy) {

//Effective Turbine Steam Flow (lb/hr)*Steam Latent Heat Enthalpy (btu/lb)
		return (effectiveTurbineSteamflow * steamLatentHeatEnthalpy);

	}

	/**
	 * Target TR based on duty design flow.
	 *
	 * @param condenser_Heat_Duty the condenser heat duty
	 * @param design_water_flow   the design water flow
	 * @return the double
	 */
	private double targetTRBasedOnDutyDesignFlow(double condenserHeatDuty, Object designWaterFlow) {

		// Condenser Heat Duty - Q (btu/hr)/(Design Water Flow*500)
		double designwaterFlow = Double.parseDouble(designWaterFlow.toString());
		return (condenserHeatDuty
				/ (designwaterFlow * CalculationConstants.TARGET_TR_BASED_ON_DUTY_DESIGN_FLOW_CONSTANT));

	}

	/**
	 * TR deviation.
	 *
	 * @param measured_CW_TR                the measured C W TR
	 * @param targetTRBasedOnDutyDesignFlow the target TR based on duty design flow
	 * @return the double
	 */
	private double tRDeviation(double measuredCWTR, double targetTRBasedOnDutyDesignFlow) {
//Measured CW TR (f)-Target TR Based on Duty and Design Flow (f)
		return measuredCWTR - targetTRBasedOnDutyDesignFlow;
	}

	/**
	 * Target CW outlet temp overall.
	 *
	 * @param average_CW_Inlet_Water_Temp   the average C W inlet water temp
	 * @param targetTRBasedOnDutyDesignFlow the target TR based on duty design flow
	 * @return the double
	 */
	private double targetCWOutletTempOverall(double averageCwInletWaterTemp, double targetTRBasedOnDutyDesignFlow) {
//Average CW Inlet Water Temp (f)+Target TR Based on Duty and Design Flow (f)
		return (averageCwInletWaterTemp + targetTRBasedOnDutyDesignFlow);

	}

	/**
	 * Udesign overall.
	 *
	 * @param U_ideal                         the u ideal
	 * @param tube_Metal_And_Wall_Corr_Factor the tube metal and wall corr factor
	 * @param CW_inlet_temp_corr_factor       the c W inlet temp corr factor
	 * @return the double
	 */
	private double udesignOverall(Object uIdeal, Object tubeMetalAndWallCorrFactor, double cwInletTempCorrFactor) {
//Design Uideal*Tube Metal and Wall Corr Factor*CW Inlet Temp Corr Factor
		double uIdealNew = Double.parseDouble(uIdeal.toString());
		double tubeMetalAndWallCorrFactorNew = Double.parseDouble(tubeMetalAndWallCorrFactor.toString());
		return (uIdealNew * tubeMetalAndWallCorrFactorNew * cwInletTempCorrFactor);
	}

	/**
	 * Uactual overall.
	 *
	 * @param UdesignOverall the udesign overall
	 * @param design_CF      ---design data
	 * @return the double
	 */
	private double uactualOverall(double udesignOverall, Object designCf) {
		double designCfNew = Double.parseDouble(designCf.toString());
		return udesignOverall * (designCfNew / 100);
	}

	/**
	 * NTU overall.
	 *
	 * @param UactualOverall          the uactual overall
	 * @param design_Water_Flow       the design water flow
	 * @param condensing_Surface_Area the condensing surface area
	 * @return the double
	 */
	private double nTUOverall(double uactualOverall, Object designWaterFlow, Object condensingSurfaceArea) {
//(Uactual Overall*Condensing Surface Area)/(Design Water Flow*500)

		double designWaterFlowNew = Double.parseDouble(designWaterFlow.toString());
		double condensingSurfaceAreaNew = Double.parseDouble(condensingSurfaceArea.toString());
		return ((uactualOverall * condensingSurfaceAreaNew)
				/ (designWaterFlowNew * CalculationConstants.NTU_OVERALL_CONSTANT));

	}

	/**
	 * Target steam temp overall.
	 *
	 * @param averageCWInletWaterTemp   the average CW inlet water temp
	 * @param targetCWOutletTempOverall the target CW outlet temp overall
	 * @param NTUOverall                the NTU overall
	 * @return the double
	 */
	private double targetSteamTempOverall(double averageCWInletWaterTemp, double targetCWOutletTempOverall,
			double nTUOverall) {
		// (Target CW Outlet Temp Overall (f)-Average CW
		// * Inlet Water Temp (f)*EXP(-NTU Overall))/(1-EXP(-NTU Overall))
		return ((targetCWOutletTempOverall - averageCWInletWaterTemp * Math.pow(Math.E, -nTUOverall))
				/ (1 - Math.pow(Math.E, -nTUOverall)));

	}

	/**
	 * B pdeviation overall.
	 *
	 * @param condenser_Operating_BP the condenser operating BP
	 * @param target_BP_Overall      the target B P overall
	 * @return the double
	 */
	private double bPdeviationOverall(Object condenserOperatingBp, Object targetBpOverall) {
//Condenser Operating BP (inHga) - Target BP Overall (inHga)
		double condenserOperatingBpNew = Double.parseDouble(condenserOperatingBp.toString());
		double targetBpOverallNew = Double.parseDouble(targetBpOverall.toString());
		return (condenserOperatingBpNew - targetBpOverallNew);
	}

	/**
	 * Heat rate deviation overall.
	 *
	 * @param typical_Heat_Rate_Penalty the typical heat rate penalty
	 * @param typical_Heat_Rate         the typical heat rate
	 * @param BPdeviationOverall        the b pdeviation overall
	 * @return the double
	 */
	private double heatRateDeviationOverall(Object typicalHeatRatePenalty, Object typicalHeatRate,
			double bPdeviationOverall) {
//Typical Heat Rate Penalty*Typical Heat Rate (btu/kWhr)*BP Deviation Overall (inHga)
		// ='Design Data'!$F$25*'Design Data'!$F$24*BQ2
		double typicalHeatRatePenaltyNew = Double.parseDouble(typicalHeatRatePenalty.toString());
		double typicalHeatRateNew = Double.parseDouble(typicalHeatRate.toString());
		return (typicalHeatRatePenaltyNew * typicalHeatRateNew * bPdeviationOverall);
	}

	/**
	 * Est excess CO 2 emissions overall.
	 *
	 * @param BPDeviationOverall        the BP deviation overall
	 * @param typical_Heat_Rate_Penalty ---design data
	 * @param typical_Heat_Rate         the typical heat rate
	 * @param gross_Plant_Production    the gross plant production
	 * @param CO2_emmission_expected    ---design data
	 * @return the double
	 */
	private Double estExcessCO2EmissionsOverall(double bPdeviationOverall, Object typicalHeatRatePenalty,
			Object typicalHeatRate, Object grossPlantProduction, Object co2EmmissionExpected) {
//BP Deviation Overall (btu/kWhr) *Typical Heat Rate Penalty*Typical Heat Rate (btu/kWhr)*1000*Gross Plant Production (MW)/1000000*CO2 Emmission Expected
		double typicalHeatRatePenaltyNew = Double.parseDouble(typicalHeatRatePenalty.toString());
		double typicalHeatRateNew = Double.parseDouble(typicalHeatRate.toString());
		double grossPlantProductionNew = Double.parseDouble(grossPlantProduction.toString());
		double co2EmmissionExpectedNew = Double.parseDouble(co2EmmissionExpected.toString());

		return (bPdeviationOverall * typicalHeatRatePenaltyNew * typicalHeatRateNew
				* CalculationConstants.EST_EXCESS_CO2_EMISSIONS_OVERALL_CONSTANT_1 * grossPlantProductionNew
				/ CalculationConstants.EST_EXCESS_CO2_EMISSIONS_OVERALL_CONSTANT_2 * co2EmmissionExpectedNew);
	}

	/**
	 * Steam enthalpy deviation overall.
	 *
	 * @param total_Steam_Enthalpy   the total steam enthalpy
	 * @param steam_Enthalpy_Overall the steam enthalpy overall
	 * @return the double
	 */
	private double steamEnthalpyDeviationOverall(Object totalSteamEnthalpy, Object steamEnthalpyOverall) {
//Total Steam Enthalpy (btu/lb)-Steam Enthalpy Overall (btu/lb)
		double totalSteamEnthalpyNew = Double.parseDouble(totalSteamEnthalpy.toString());
		double steamEnthalpyOverallNew = Double.parseDouble(steamEnthalpyOverall.toString());
		return (totalSteamEnthalpyNew - steamEnthalpyOverallNew);

	}

	/**
	 * Total enthalpy deviation overall.
	 *
	 * @param steamEnthalpyDeviationOverall the steam enthalpy deviation overall
	 * @param effectiveTurbineSteamFlow     the effective turbine steam flow
	 * @return the double
	 */
	private double totalEnthalpyDeviationOverall(double steamEnthalpyDeviationOverall,
			double effectiveTurbineSteamFlow) {
//Steam Enthalpy Deviation Overall (btu/lb)*Effective Turbine Steam Flow (lb/hr)
		return (steamEnthalpyDeviationOverall * effectiveTurbineSteamFlow);
	}

	/**
	 * Production deviation overall.
	 *
	 * @param totalEnthalpyDeviationOverall the total enthalpy deviation overall
	 * @param typical_Heat_Rate             the typical heat rate
	 * @return the double
	 */
	private double productionDeviationOverall(double totalEnthalpyDeviationOverall, Object typicalHeatRate) {
		// Total Enthalpy Deviation Overall (btu/hr)/3412/1000/(3412/Typical Heat Rate
		// (btu/kWhr))
		double typicalHeatRateNew = Double.parseDouble(typicalHeatRate.toString());
		return (totalEnthalpyDeviationOverall / CalculationConstants.PI
				/ CalculationConstants.PRODUCTION_DEVIATION_OVERALL_CONSTANT
				/ (CalculationConstants.PI / typicalHeatRateNew));

	}

	/**
	 * Dollar lost per day.
	 *
	 * @param productionDeviationOverall the production deviation overall
	 * @param typical_sale_price         the typical sale price
	 * @return the double
	 */
	private double dollarLostPerDay(double productionDeviationOverall, Object typicalSalePrice) {
		// Production Deviation Overall (MWhrs)*Typical $$/MWhr Sale Price*24
		double typicalSalePriceNew = Double.parseDouble(typicalSalePrice.toString());
		return (productionDeviationOverall * typicalSalePriceNew * CalculationConstants.DOLLAR_LOST_PER_DAY_CONSTANT);

	}

	/**
	 * Est excess fuel burned overall.
	 *
	 * @param totalEnthalpyDeviationOverall the total enthalpy deviation overall
	 * @param typicalHeat_Rate              ---design data
	 * @return the double
	 */
	private double estExcessFuelBurnedOverall(double totalEnthalpyDeviationOverall, Object typicalheatRate) {
		// Total Enthalpy Deviation Overall (btu/hr)/(3412/Typical Heat Rate
		// (btu/kWhr))0.27296

		double typicalheatRateNew = Double.parseDouble(typicalheatRate.toString());
		return (totalEnthalpyDeviationOverall / (CalculationConstants.PI / typicalheatRateNew));

	}

	/**
	 * Est excess fuel dollar per day overall.
	 *
	 * @param estExcessFuelBurnedOverall    the est excess fuel burned overall
	 * @param average_fuel_costs_per_mmbtus the average fuel costs per mmbtus
	 * @return the double
	 */
	private double estExcessFuelDollarPerDayOverall(double estExcessFuelBurnedOverall,
			Object averageFuelCostsPerMmbtus) {
		// Est. Excess Fuel Burned Overall (btu/hr)/1000000*Average Fuel Costs (per
		// MMbtus)*24

		double averageFuelCostsPerMmbtusNew = Double.parseDouble(averageFuelCostsPerMmbtus.toString());
		return (estExcessFuelBurnedOverall / CalculationConstants.EST_EXCESS_FUEL_DOLLAR_PER_DAY_OVERALL_CONSTANT_1
				* averageFuelCostsPerMmbtusNew
				* CalculationConstants.EST_EXCESS_FUEL_DOLLAR_PER_DAY_OVERALL_CONSTANT_2);

	}

	/**
	 * Calc current CW flow.
	 *
	 * @param condenser_Heat_Duty the condenser heat duty
	 * @param measured_CW_TR      the measured C W TR
	 * @return the double
	 */
	private double calcCurrentCWFlow(double condenserHeatDuty, double measuredCwTr) {
		// Condenser Heat Duty - Q (btu/hr) /(500*Measured CW TR (f))

		return (condenserHeatDuty / (CalculationConstants.CALC_CURRENT_CW_FLOW_CONSTANT * measuredCwTr));

	}

	/**
	 * Calc current CW velocity.
	 *
	 * @param calcCurrentCWFlow       the calc current CW flow
	 * @param total_Tube_ID_Flow_Area the total tube I D flow area
	 * @return the double
	 */
	private double calcCurrentCWVelocity(double calcCurrentCWFlow, Object totalTubeIdFlowArea) {
		// ((Calc Current CW Flow (gpm)/60/7.48)/Total Tube ID Flow Area)
		double totalTubeIdFlowAreaNew = Double.parseDouble(totalTubeIdFlowArea.toString());
		return (calcCurrentCWFlow / CalculationConstants.CALC_CURRENT_CW_VELOCITY_CONSTANT_1
				/ CalculationConstants.CALC_CURRENT_CW_VELOCITY_CONSTANT_2 / totalTubeIdFlowAreaNew);

	}

	/**
	 * Calc CW delta P.
	 *
	 * @param cw_inlet_pressure  the cw inlet pressure
	 * @param cw_outlet_pressure the cw outlet pressure
	 * @return the double
	 */
	private double calcCWDeltaP(Object cwInletPressure, Object cwOutletPressure) {
		// CW Inlet Pressure (psi)-CW Outlet Pressure (psi)
		double cwInletPressureNew = Double.parseDouble(cwInletPressure.toString());
		double cwOutletPressureNew = Double.parseDouble(cwOutletPressure.toString());
		return (cwInletPressureNew - cwOutletPressureNew);
	}

	/**
	 * Calc udesign.
	 *
	 * @param CW_Inlet_Temp_Corr_Factor       the c W inlet temp corr factor
	 * @param tube_Metal_and_Wall_Corr_Factor the tube metal and wall corr factor
	 * @param calUideal                       the cal uideal
	 * @return the double
	 */
	private double calcUdesign(double cwInletTempCorrFactor, Object tubeMetalAndWallCorrFactor, double calUideal) {
		// CW Inlet Temp Corr Factor*Tube Metal and Wall Corr Factor*Calc Uideal
		double tubeMetalAndWallCorrFactorNew = Double.parseDouble(tubeMetalAndWallCorrFactor.toString());
		return (cwInletTempCorrFactor * tubeMetalAndWallCorrFactorNew * calUideal);
	}

	/**
	 * Cal uactual.
	 *
	 * @param condenser_Heat_Duty   the condenser heat duty
	 * @param CondensingSurfaceArea the condensing surface area
	 * @param MeasuredLMTD          the measured LMTD
	 * @return the double
	 */
	private double calUactual(double condenserHeatDuty, Object condensingSurfaceArea, double measuredLMTD) {
		// Condenser Heat Duty - Q (btu/hr)/(Condensing Surface Area*Measured LMTD (f))

		double condensingSurfaceAreaNew = Double.parseDouble(condensingSurfaceArea.toString());
		return (condenserHeatDuty / (condensingSurfaceAreaNew * measuredLMTD));
	}

	/**
	 * Current CF percentage.
	 *
	 * @param calUactual the cal uactual
	 * @param calUideal  the cal uideal
	 * @return the double
	 */
	private double currentCFPercentage(double calUactual, double calcUdesign) {
		// Calc Uactual/Calc Uideal*100

		return (calUactual / calcUdesign * 100);

	}

	/**
	 * Target uactual condenser only.
	 *
	 * @param calcUdesign the calc udesign
	 * @param designCF    the design CF
	 * @return the double
	 */
	private double targetUactualCondenserOnly(double calcUdesign, Object designCF) {
		// Calc Udesign*Design CF/100
		double designCFNew = Double.parseDouble(designCF.toString());

		return (calcUdesign * designCFNew / 100);
	}

	/**
	 * NTU condenser only.
	 *
	 * @param targetUactualCondenserOnly the target uactual condenser only
	 * @param CondensingSurfaceArea      the condensing surface area
	 * @param calc_Current_CW_Flow       the calc current C W flow
	 * @return the double
	 */
	private double nTUCondenserOnly(double targetUactualCondenserOnly, Object condensingSurfaceArea,
			double calcCurrentCwFlow) {
		// (Target Uactual Condenser Only
		// *Condensing Surface Area)/(Calc Current CW Flow (gpm)*500)
		double condensingSurfaceAreaNew = Double.parseDouble(condensingSurfaceArea.toString());
		return ((targetUactualCondenserOnly * condensingSurfaceAreaNew) / (calcCurrentCwFlow * 500));
	}

	/**
	 * CW outlet temp condenser only.
	 *
	 * @param average_CW_inlet_Water_Temp the average C W inlet water temp
	 * @param measured_CW_TR              the measured C W TR
	 * @return the double
	 */
	private double cWOutletTempCondenserOnly(double averageCwInletWaterTemp, double measuredCwTr) {
		// Average CW Inlet Water Temp (f)+Measured CW TR (f)
		return (averageCwInletWaterTemp + measuredCwTr);
	}

	/**
	 * Target steam temp condenser only.
	 *
	 * @param CWOutletTempCondenserOnly   the CW outlet temp condenser only
	 * @param average_CW_inlet_Water_Temp the average C W inlet water temp
	 * @param NTUCondenserOnly            the NTU condenser only
	 * @return the double
	 */
	private double targetSteamTempCondenserOnly(double cWOutletTempCondenserOnly, double averageCwInletWaterTemp,
			double nTUCondenserOnly) {
		// (CW Outlet Temp Condenser Only (f)-Average CW Inlet Water Temp (f)
		// *EXP(-NTU Condenser Only))/(1-EXP(-NTU Condenser Only))

		return ((cWOutletTempCondenserOnly - averageCwInletWaterTemp * Math.pow(Math.E, -nTUCondenserOnly))
				/ (1 - Math.pow(Math.E, -nTUCondenserOnly)));
	}

	/**
	 * BP deviation condenser only.
	 *
	 * @param targetBPCondenserOnly the target BP condenser only
	 * @param condenserOperatingBP  the condenser operating BP
	 * @return the double
	 */
	private double bPDeviationCondenserOnly(double targetBPCondenserOnly, Object condenserOperatingBP) {
		// Condenser Operating BP (inHga)-Target BP Condenser Only (inHga)
		double condenserOperatingbp = Double.parseDouble(condenserOperatingBP.toString());
		return (condenserOperatingbp - targetBPCondenserOnly);
	}

	/**
	 * Target TTD condenser only.
	 *
	 * @param targetSteamTempCondenserOnly the target steam temp condenser only
	 * @param CWOutletTempCondenserOnly    the CW outlet temp condenser only
	 * @return the double
	 */
	private double targetTTDCondenserOnly(double targetSteamTempCondenserOnly, double cWOutletTempCondenserOnly) {
		// Target Steam Temp Condenser Only (f)-CW Outlet Temp Condenser Only (f)
		return (targetSteamTempCondenserOnly - cWOutletTempCondenserOnly);
	}

	/**
	 * TTD deviation.
	 *
	 * @param measured_CW_TTD        the measured C W TTD
	 * @param targetTTDCondenserOnly the target TTD condenser only
	 * @return the double
	 */
	private double tTDDeviation(double measuredCwTtd, double targetTTDCondenserOnly) {
		// Measured CW TTD (f)-Target TTD Condenser Only (f)

		return (measuredCwTtd - targetTTDCondenserOnly);
	}

	/**
	 * Enthalpy deviation condenser only.
	 *
	 * @param totalSteamEnthalpy         the total steam enthalpy
	 * @param steamEnthalpyCondenserOnly the steam enthalpy condenser only
	 * @return the double
	 */
	private double enthalpyDeviationCondenserOnly(Object totalSteamEnthalpy, double steamEnthalpyCondenserOnly) {
		// Total Steam Enthalpy (btu/lb)-Steam Enthalpy Condenser Only (btu/lb)
		double totalSteamenthalpy = Double.parseDouble(totalSteamEnthalpy.toString());
		return (totalSteamenthalpy - steamEnthalpyCondenserOnly);
	}

	/**
	 * Total enthalpy deviation condenser only.
	 *
	 * @param enthalpyDeviationCondenserOnly the enthalpy deviation condenser only
	 * @param effectiveTurbineSteamFlow      the effective turbine steam flow
	 * @return the double
	 */
	private double totalEnthalpyDeviationCondenserOnly(double enthalpyDeviationCondenserOnly,
			double effectiveTurbineSteamFlow) {
		// Enthalpy Deviation Condenser Only (btu/lb)-Effective Turbine Steam Flow
		// (lb/hr)

		return (enthalpyDeviationCondenserOnly * effectiveTurbineSteamFlow);
	}

	/**
	 * Production deviation condenser only.
	 *
	 * @param totalEnthalpyDeviationCondenserOnly the total enthalpy deviation
	 *                                            condenser only
	 * @param typicalHeatRate                     the typical heat rate
	 * @return the double
	 */
	private double productionDeviationCondenserOnly(double totalEnthalpyDeviationCondenserOnly,
			Object typicalHeatRate) {
		// Total Enthalpy Deviation Condenser Only (btu)/3412/1000/(3412/Typical Heat
		// Rate (btu/kWhr))

		double typicalHeatrate = Double.parseDouble(typicalHeatRate.toString());
		return (totalEnthalpyDeviationCondenserOnly / CalculationConstants.PI / CalculationConstants.THOUSAND
				/ (CalculationConstants.PI / typicalHeatrate));
	}

	/**
	 * Heat rate deviation condenser only.
	 *
	 * @param BPDeviationCondenserOnly the BP deviation condenser only
	 * @param typicalHeatRatePenalty   the typical heat rate penalty
	 * @param typicalHeatRate          the typical heat rate
	 * @return the double
	 */
	private double heatRateDeviationCondenserOnly(double bPDeviationCondenserOnly, Object typicalHeatRatePenalty,
			Object typicalHeatRate) {
		// Typical Heat Rate Penalty*Typical Heat Rate (btu/kWhr)*BP Deviation Condenser
		// Only (inHga)
		double typicalHeatrate = Double.parseDouble(typicalHeatRate.toString());
		double typicalHeatratepenalty = Double.parseDouble(typicalHeatRatePenalty.toString());
		return (typicalHeatratepenalty * typicalHeatrate * bPDeviationCondenserOnly);
	}

	/**
	 * C O 2 emissions deviation condenser only.
	 *
	 * @param heatRateDeviationCondenserOnly the heat rate deviation condenser only
	 * @param grossPlantProduction           the gross plant production
	 * @param cO2EmisionExpected             the c O 2 emision expected
	 * @return the double
	 */
	private double cO2EmissionsDeviationCondenserOnly(double heatRateDeviationCondenserOnly,
			Object grossPlantProduction, Object cO2EmisionExpected) {
		// Heat Rate Deviation Condenser Only (btu/kWhr)
		// * 1000*Gross Plant Production (MW)/1000000*CO2 Emmission Expected

		double grossPlantproduction = Double.parseDouble(grossPlantProduction.toString());
		double co2Emisionexpected = Double.parseDouble(cO2EmisionExpected.toString());
		return (heatRateDeviationCondenserOnly * 1000 * grossPlantproduction / 1000000 * co2Emisionexpected);
	}

	/**
	 * Est excess fuel burned condenser only.
	 *
	 * @param totalEnthalpyDeviationCondenserOnly the total enthalpy deviation
	 *                                            condenser only
	 * @param typicalHeatRate                     the typical heat rate
	 * @return the double
	 */
	private double estExcessFuelBurnedCondenserOnly(double totalEnthalpyDeviationCondenserOnly,
			Object typicalHeatRate) {
		// Total Enthalpy Deviation Condenser Only (btu)/(3412/Typical Heat Rate
		// (btu/kWhr))

		double typicalHeatrate = Double.parseDouble(typicalHeatRate.toString());
		return (totalEnthalpyDeviationCondenserOnly / (3412 / typicalHeatrate));
	}

	/**
	 * Atm pressure psia.
	 *
	 * @param ambientBarometricPressure the ambient barometric pressure
	 * @return the double
	 */
	private double atmpressurePsia(Object ambientBarometricPressure) {
		// Ambient Barometric Pressure (inHga)*0.491

		double ambientBarometricpressure = Double.parseDouble(ambientBarometricPressure.toString());
		return (ambientBarometricpressure * CalculationConstants.ATM_PRESSURE_PSIA_CONSTANT);
	}

	/**
	 * Atm pressure k pa.
	 *
	 * @param AtmPressure_psia the atm pressure psia
	 * @return the double
	 */
	private double atmpressureKpa(double atmpressurePsia) {
		// Atm Pressure psia*6.89

		return atmpressurePsia * CalculationConstants.ATM_PRESSURE_KPA_CONSTANT;
	}

	/**
	 * Partial pressure at T.
	 *
	 * @param ambientTemp     the ambient temp
	 * @param ambientHumidity the ambient humidity
	 * @return the double
	 */
	private double partialPressureAtT(Object ambientTemp, Object ambientHumidity) {
		// (-6.42408360512612E-14*((Ambient Temp (f)-32)*5/9)^6+4.1234523556912E-11
		// *((Ambient Temp (f)-32)*5/9)^5+1.68235938604627E-09
		// *((Ambient Temp (f)-32)*5/9)^4+3.0597090647296E-07
		// *((Ambient Temp (f)-32)*5/9)^3+0.0000137001148244504
		// *((Ambient Temp (f)-32)*5/9)^2+0.000446890499983739
		// *((Ambient Temp (f)-32)*5/9)+(0.00610402348459321)*Ambient Humidity (%)/100)
		double ambienttemp = Double.parseDouble(ambientTemp.toString());
		double ambienthumidity = Double.parseDouble(ambientHumidity.toString());

		return (Math.pow(-6.42408360512612E-14 * ((ambienttemp - 32) * 5 / 9), 6)
				+ Math.pow(4.1234523556912E-11 * ((ambienttemp - 32) * 5 / 9), 5)
				+ Math.pow(1.68235938604627E-09 * ((ambienttemp - 32) * 5 / 9), 4)
				+ 3.0597090647296E-07 * Math.pow(((ambienttemp - 32) * 5 / 9), 3)
				+ 0.0000137001148244504 * Math.pow(((ambienttemp - 32) * 5 / 9), 2)
				+ 0.000446890499983739 * ((ambienttemp - 32) * 5 / 9) + (0.00610402348459321) * ambienthumidity / 100);
	}

	/**
	 * T.
	 *
	 * @param partialPressureAtT the partial pressure at T
	 * @return the double
	 */
	private Double t(double partialPressureAtT) {
		// 17.9012217*Log e(Partial Pressure at T (dew)) + 88.0316819 + 838105.248
		// *Partial Pressure at T (dew)^6 - 864594.341*Partial Pressure at T (dew)^5
		// + 348585.996*Partial Pressure at T (dew)^4
		// - 69532.2236*Partial Pressure at T (dew)^3
		// + 7160.68408*Partial Pressure at T (dew)^2
		// - 341.334794*Partial Pressure at T (dew) + 4.2139565

		return (17.9012217 * Math.log(partialPressureAtT) + 88.0316819 + 838105.248 * Math.pow(partialPressureAtT, 6)
				- 864594.341 * Math.pow(partialPressureAtT, 5) + 348585.996 * Math.pow(partialPressureAtT, 4)
				- 69532.2236 * Math.pow(partialPressureAtT, 3) + 7160.68408 * Math.pow(partialPressureAtT, 2)
				- 341.334794 * partialPressureAtT + 4.2139565);

	}

	/**
	 * Ys 1.
	 *
	 * @param partialPressureAtT the partial pressure at T
	 * @param AtmPressure_kPa    the atm pressure k pa
	 * @return the double
	 */
	private double ys1(double partialPressureAtT, double atmpressureKpa) {
		// 18.02/28.97*(Partial Pressure at T (dew)*100000/((Atm Pressure
		// kPa*1000+101325)-Partial Pressure at T (dew)*100000))

		return (18.02 / 28.97
				* (partialPressureAtT * 100000 / ((atmpressureKpa * 1000 + 101325) - partialPressureAtT * 100000)));
	}

	/**
	 * Ys.
	 *
	 * @param Ys1 the ys 1
	 * @return the double
	 */
	private double ys(double ys1) {
		// Ys' (dew)*28.97/18.02
		return (ys1 * 28.97 / 18.02);
	}

	/**
	 * Air enthalpy.
	 *
	 * @param Ys          the ys
	 * @param ambientTemp the ambient temp
	 * @return the double
	 */
	private double airEnthalpy(double ys, Object ambientTemp) {
		// ((18.02/28.97)*Mass Abs kg H2O/kg air*(1884*((Ambient Temp
		// (f)-32)*5/9)+2502300)+1005*((Ambient Temp (f)-32)*5/9))/1000
		double ambienttemp = Double.parseDouble(ambientTemp.toString());
		return (((18.02 / 28.97) * ys * (1884 * ((ambienttemp - 32) * 5 / 9) + 2502300)
				+ 1005 * ((ambienttemp - 32) * 5 / 9)) / 1000);
	}

	/**
	 * Vapor pressure.
	 *
	 * @param AirEnthalpy the air enthalpy
	 * @return the double
	 */
	private double vaporPressure(double airEnthalpy) {
		// 0.000000000000000421*Air Enthalpy^6 + 0.000000000000844329*Air Enthalpy^5 -
		// 0.00000000020533587
		// *Air Enthalpy^4 + 0.000000002621956315*Air Enthalpy^3 +
		// 0.000002865040720711*Air Enthalpy^2 + 0.00018680935595267
		// *Air Enthalpy + 0.00431952061419925
		// 0.000000000000000421*M16^6 + 0.000000000000844329*M16^5 -
		// 0.00000000020533587*M16^4 + 0.000000002621956315*M16^3 +
		// 0.000002865040720711*M16^2 +
		// 0.00018680935595267*M16 + 0.00431952061419925

		return (0.000000000000000421 * Math.pow(airEnthalpy, 6) + 0.000000000000844329 * Math.pow(airEnthalpy, 5)
				- 0.00000000020533587 * Math.pow(airEnthalpy, 4) + 0.000000002621956315 * Math.pow(airEnthalpy, 3)
				+ 0.000002865040720711 * Math.pow(airEnthalpy, 2) + 0.00018680935595267 * airEnthalpy
				+ 0.00431952061419925);
	}

	/**
	 * Calc wet bulb.
	 *
	 * @param VaporPressure the vapor pressure
	 * @param ambientTemp   the ambient temp
	 * @return the object
	 */
	private Object calcWetBulb(double vaporPressure, Object ambientTemp) {
		// IF(Ambient Temp (f)>115 then "OUT OF RANGE" else (((17.9012217*LN(Vapor
		// Pressure )+88.0316819+838105.248*Vapor Pressure ^6-864594.341
		// *Vapor Pressure ^5+348585.996*Vapor Pressure ^4-69532.2236
		// *Vapor Pressure ^3+7160.68408*Vapor Pressure ^2-341.334794*Vapor Pressure
		// +4.2139565)*9/5)+32))
		double calWetBulb = 0;
		// =IF(C3>115,"OUT OF RANGE",(((17.9012217*LN(N16)
		// +88.0316819+838105.248*N16^6-864594.341*N16^5+348585.996*N16^4-69532.2236*N16^3
		// +7160.68408*N16^2-341.334794*N16+4.2139565)*9/5)+32))
		double ambienttemp = Double.parseDouble(ambientTemp.toString());
		Object calcWetBulb = (ambienttemp > 115) ? "OUT OF RANGE"
				: (((17.9012217 * Math.log(vaporPressure) + 88.0316819 + 838105.248 * Math.pow(vaporPressure, 6)
						- 864594.341 * Math.pow(vaporPressure, 5) + 348585.996 * Math.pow(vaporPressure, 4)
						- 69532.2236 * Math.pow(vaporPressure, 3) + 7160.68408 * Math.pow(vaporPressure, 2)
						- 341.334794 * vaporPressure + 4.2139565) * 9 / 5) + 32);
		if (!Objects.equals(calcWetBulb, "CalcWetBulb")) {
			calWetBulb = Double.parseDouble(calcWetBulb.toString());
			return Precision.round(calWetBulb, 2);
		}
		return calcWetBulb;
	}

	/**
	 * Cooling tower approach.
	 *
	 * @param average_CW_Inlet_Water_Temp the average C W inlet water temp
	 * @param CalcWetBulb                 the calc wet bulb
	 * @return the double
	 */
	private double coolingTowerApproach(double averageCwInletWaterTemp, Object calcWetBulb) {
		// Average CW Inlet Water Temp (f)-Calc Wet Bulb (f)
		double calcWetBulbNew = (calcWetBulb.toString().equals("OUT OF RANGE")) ? 0
				: Double.parseDouble(calcWetBulb.toString());
		return averageCwInletWaterTemp - calcWetBulbNew;
	}

	private List<RequestDTO> createListDTOForSteam(Object p, String from) {
		List<RequestDTO> reqList = new ArrayList<>();
		RequestDTO req = new RequestDTO();
		double pressure = Double.parseDouble(p.toString());
		pressure = (Double.isNaN(pressure)) ? 0d : pressure;
		pressure = Precision.round(pressure, 2);
		String fromColumn = (StringUtils.equals(from, ApplicationConstants.PRESSURE)) ? ApplicationConstants.PRESSURE
				: ApplicationConstants.STEAM_TEMP;

		req.setFrom(fromColumn);
		req.setTableName(ApplicationConstants.STEAM);
		req.setValue(pressure);
		reqList.add(req);
		return reqList;
	}

	private List<RequestDTO> createListDTOForWcf(Double p) {
		List<RequestDTO> reqList = new ArrayList<>();
		RequestDTO req = new RequestDTO();
		req.setFrom(ApplicationConstants.STEAM_TEMP);
		req.setTableName(ApplicationConstants.WATER_CORRECTION_FACTOR);
		p = Precision.round(p, 0);
		req.setValue(p.intValue());
		reqList.add(req);
		return reqList;
	}

	private List<RequestDTO> createListDTOForHEIStd(double calcCurrentCWVelocity, Object tubeOd) {
		List<RequestDTO> reqList = new ArrayList<>();
		RequestDTO req = new RequestDTO();
		double dia = Double.parseDouble(tubeOd.toString());
		double ft = CalculationUtil.round(calcCurrentCWVelocity * 2, 0) / 2;
		req.setFrom(ApplicationConstants.DIA);
		req.setTableName(ApplicationConstants.HEI_STANDARDS);
		req.setValue(dia);
		req.setValue2(ft);
		reqList.add(req);
		return reqList;
	}

	private List<Map<String, Object>> composeCalcData(List<Map<String, Object>> list) {

		List<Map<String, Object>> composedInputList = new ArrayList<>();

		for (Map<String, Object> map : list) {
			Map<String, Object> composedInputMap = null;
			Map<String, List<Double>> defaultListMap=getDefaultListMap();
			Map<String, Double> defaultInputMap=getDefaultInputMap();
			composedInputMap = iterateAndCompose(map, defaultInputMap,defaultListMap );
			composedInputMap = addToCollection(composedInputMap, defaultInputMap,defaultListMap);

			composedInputList.add(composedInputMap);

		}



			return composedInputList;


	}

	private Map<String, List<Double>> getDefaultListMap() {

		Map<String, List<Double>> defaultListMap = new HashMap<>();
		List<Double> condenserBP = new ArrayList<>();
		List<Double> turbineExhaustSteamTemp = new ArrayList<>();
		List<Double> hotwellTemp = new ArrayList<>();
		List<Double> totalBoilerSteamFlow = new ArrayList<>();
		List<Double> totalAttempSteamFlow = new ArrayList<>();
		List<Double> totalCogenSteamFlow = new ArrayList<>();
		List<Double> cWTempIn = new ArrayList<>();
		List<Double> cWTempOut = new ArrayList<>();
		List<Double> cwInletPressure = new ArrayList<>();
		List<Double> cwOutletPressure = new ArrayList<>();
		List<Double> cwDeltaP = new ArrayList<>();

		defaultListMap.put(CalculationConstants.CONDENSER_BP, condenserBP);
		defaultListMap.put(CalculationConstants.TURBINE_EXHAUST_STEAM_TEMP, turbineExhaustSteamTemp);
		defaultListMap.put(CalculationConstants.HOT_WELL_TEMP, hotwellTemp);
		defaultListMap.put(CalculationConstants.TOTAL_BOILER_STEAM_FLOW, totalBoilerSteamFlow);
		defaultListMap.put(CalculationConstants.TOTAL_ATTEMP_STEAM_FLOW, totalAttempSteamFlow);
		defaultListMap.put(CalculationConstants.TOTAL_COGEN_STEAM_FLOW, totalCogenSteamFlow);
		defaultListMap.put(CalculationConstants.CW_TEMP_IN, cWTempIn);
		defaultListMap.put(CalculationConstants.CW_TEMP_OUT, cWTempOut);
		defaultListMap.put(CalculationConstants.CW_INLET_PRESSURE, cwInletPressure);
		defaultListMap.put(CalculationConstants.CW_OUTLET_PRESSURE, cwOutletPressure);
		defaultListMap.put(CalculationConstants.CW_DELTA_P, cwDeltaP);
		return defaultListMap;

	}

	private Map<String, Double> getDefaultInputMap() {
		Map<String, Double> defaultInputMap = new HashMap<>();
		Double avgCondenserBP = 0d;
		Double avgTurbineExhaustSteamTemp = 0d;
		Double avgHotwellTemp = 0d;
		Double sumTotalBoilerSteamFlow = 0d;
		Double sumTotalAttempSteamFlow = 0d;
		Double sumTotalCogenSteamFlow = 0d;
		Double avgcWTempIn = 0d;
		Double avgcWTempOut = 0d;
		Double avgcwInletPressure = 0d;
		Double avgcwOutletPressure = 0d;
		Double avgcwDeltaP = 0d;
		Double ambientTemp = 0d;
		Double humidity = 0d;
		Double barometricPressure = 29.92;

		defaultInputMap.put(CalculationConstants.AVG_CONDENSER_BP, avgCondenserBP);
		defaultInputMap.put(CalculationConstants.AVG_TURBINE_EXHAUST_STEAM_TEMP, avgTurbineExhaustSteamTemp);
		defaultInputMap.put(CalculationConstants.AVG_HOTWELL_TEMP, avgHotwellTemp);
		defaultInputMap.put(CalculationConstants.SUM_TOTAL_BOILER_STEAM_FLOW, sumTotalBoilerSteamFlow);
		defaultInputMap.put(CalculationConstants.SUM_TOTAL_ATTEMP_STEAM_FLOW, sumTotalAttempSteamFlow);
		defaultInputMap.put(CalculationConstants.SUM_TOTAL_COGEN_STEAM_FLOW, sumTotalCogenSteamFlow);
		defaultInputMap.put(CalculationConstants.AVG_CW_TEMP_IN, avgcWTempIn);
		defaultInputMap.put(CalculationConstants.AVG_CW_TEMP_OUT, avgcWTempOut);
		defaultInputMap.put(CalculationConstants.AVG_CW_INLET_PRESSURE, avgcwInletPressure);
		defaultInputMap.put(CalculationConstants.AVG_CW_OUTLET_PRESSURE, avgcwOutletPressure);
		defaultInputMap.put(CalculationConstants.AVG_CW_DELTA_P, avgcwDeltaP);
		defaultInputMap.put(CalculationConstants.AMBIENT_TEMP, ambientTemp);
		defaultInputMap.put(CalculationConstants.HUMIDITY, humidity);
		defaultInputMap.put(CalculationConstants.BAROMETRIC_PRESSURE, barometricPressure);
		return defaultInputMap;

	}

	private Map<String, Object> addToCollection(Map<String, Object> composedInputMap,
			Map<String, Double> defaultInputMap, Map<String, List<Double>> defaultListMap) {

		if (!CollectionUtils.isEmpty(defaultListMap.get(CalculationConstants.CONDENSER_BP))) {
			defaultInputMap.put(CalculationConstants.AVG_CONDENSER_BP,
					defaultListMap.get(CalculationConstants.CONDENSER_BP).stream().mapToDouble(Double::doubleValue)
							.average().getAsDouble());
		}
		if (!CollectionUtils.isEmpty(defaultListMap.get(CalculationConstants.TURBINE_EXHAUST_STEAM_TEMP))) {
			defaultInputMap.put(CalculationConstants.AVG_TURBINE_EXHAUST_STEAM_TEMP,
					defaultListMap.get(CalculationConstants.TURBINE_EXHAUST_STEAM_TEMP).stream()
							.mapToDouble(Double::doubleValue).average().getAsDouble());

		}
		if (!CollectionUtils.isEmpty(defaultListMap.get(CalculationConstants.HOT_WELL_TEMP))) {
			defaultInputMap.put(CalculationConstants.AVG_HOTWELL_TEMP,
					defaultListMap.get(CalculationConstants.HOT_WELL_TEMP).stream().mapToDouble(Double::doubleValue)
							.average().getAsDouble());

		}
		if (!CollectionUtils.isEmpty(defaultListMap.get(CalculationConstants.TOTAL_BOILER_STEAM_FLOW))) {
			defaultInputMap.put(CalculationConstants.SUM_TOTAL_BOILER_STEAM_FLOW, defaultListMap
					.get(CalculationConstants.TOTAL_BOILER_STEAM_FLOW).stream().mapToDouble(Double::doubleValue).sum());
		}
		if (!CollectionUtils.isEmpty(defaultListMap.get(CalculationConstants.TOTAL_ATTEMP_STEAM_FLOW))) {
			defaultInputMap.put(CalculationConstants.SUM_TOTAL_ATTEMP_STEAM_FLOW, defaultListMap
					.get(CalculationConstants.TOTAL_ATTEMP_STEAM_FLOW).stream().mapToDouble(Double::doubleValue).sum());

		}
		if (!CollectionUtils.isEmpty(defaultListMap.get(CalculationConstants.TOTAL_COGEN_STEAM_FLOW))) {
			defaultInputMap.put(CalculationConstants.SUM_TOTAL_COGEN_STEAM_FLOW, defaultListMap
					.get(CalculationConstants.TOTAL_COGEN_STEAM_FLOW).stream().mapToDouble(Double::doubleValue).sum());

		}
		if (!CollectionUtils.isEmpty(defaultListMap.get(CalculationConstants.CW_TEMP_IN))) {
			defaultInputMap.put(CalculationConstants.AVG_CW_TEMP_IN, defaultListMap.get(CalculationConstants.CW_TEMP_IN)
					.stream().mapToDouble(Double::doubleValue).average().getAsDouble());

		}
		if (!CollectionUtils.isEmpty(defaultListMap.get(CalculationConstants.CW_TEMP_OUT))) {
			defaultInputMap.put(CalculationConstants.AVG_CW_TEMP_OUT,
					defaultListMap.get(CalculationConstants.CW_TEMP_OUT).stream().mapToDouble(Double::doubleValue)
							.average().getAsDouble());

		}
		if (!CollectionUtils.isEmpty(defaultListMap.get(CalculationConstants.CW_INLET_PRESSURE))) {
			defaultInputMap.put(CalculationConstants.AVG_CW_INLET_PRESSURE,
					defaultListMap.get(CalculationConstants.CW_INLET_PRESSURE).stream().mapToDouble(Double::doubleValue)
							.average().getAsDouble());

		}
		if (!CollectionUtils.isEmpty(defaultListMap.get(CalculationConstants.CW_OUTLET_PRESSURE))) {
			defaultInputMap.put(CalculationConstants.AVG_CW_OUTLET_PRESSURE,
					defaultListMap.get(CalculationConstants.CW_OUTLET_PRESSURE).stream()
							.mapToDouble(Double::doubleValue).average().getAsDouble());

		}
		if (!CollectionUtils.isEmpty(defaultListMap.get(CalculationConstants.CW_DELTA_P))) {
			defaultInputMap.put(CalculationConstants.AVG_CW_DELTA_P, defaultListMap.get(CalculationConstants.CW_DELTA_P)
					.stream().mapToDouble(Double::doubleValue).average().getAsDouble());

		}
		if (!Objects.equals(0d, defaultInputMap.get(CalculationConstants.AVG_CW_INLET_PRESSURE))
				&& !Objects.equals(0d, defaultInputMap.get(CalculationConstants.AVG_CW_OUTLET_PRESSURE))) {
			defaultInputMap.put(CalculationConstants.AVG_CW_DELTA_P,
					defaultInputMap.get(CalculationConstants.AVG_CW_INLET_PRESSURE)
							- defaultInputMap.get(CalculationConstants.AVG_CW_OUTLET_PRESSURE));
		}
		composedInputMap.put(CalculationConstants.CONDENSER_OPERATING_BP,
				defaultInputMap.get(CalculationConstants.AVG_CONDENSER_BP));

		composedInputMap.put(CalculationConstants.TURBINE_EXHAUST_STEAM_TEMP,
				defaultInputMap.get(CalculationConstants.AVG_TURBINE_EXHAUST_STEAM_TEMP));
		composedInputMap.put(CalculationConstants.HOTWELL_TEMP,
				defaultInputMap.get(CalculationConstants.AVG_HOTWELL_TEMP));
		composedInputMap.put(CalculationConstants.TOTAL_BOILER_STEAM_FLOW,
				defaultInputMap.get(CalculationConstants.SUM_TOTAL_BOILER_STEAM_FLOW));
		composedInputMap.put(CalculationConstants.TOTAL_ATTEMP_STEAM_FLOW,
				defaultInputMap.get(CalculationConstants.SUM_TOTAL_ATTEMP_STEAM_FLOW));
		composedInputMap.put(CalculationConstants.TOTAL_COGEN_STEAM_FLOW,
				defaultInputMap.get(CalculationConstants.SUM_TOTAL_COGEN_STEAM_FLOW));
		composedInputMap.put(CalculationConstants.CW_TEMP_IN,
				Precision.round(defaultInputMap.get(CalculationConstants.AVG_CW_TEMP_IN), 2));
		composedInputMap.put(CalculationConstants.CW_TEMP_OUT,
				Precision.round(defaultInputMap.get(CalculationConstants.AVG_CW_TEMP_OUT), 2));
		composedInputMap.put(CalculationConstants.CW_INLET_PRESSURE,
				defaultInputMap.get(CalculationConstants.AVG_CW_INLET_PRESSURE));
		composedInputMap.put(CalculationConstants.CW_OUTLET_PRESSURE,
				defaultInputMap.get(CalculationConstants.AVG_CW_OUTLET_PRESSURE));
		composedInputMap.put(CalculationConstants.CW_DELTA_P, defaultInputMap.get(CalculationConstants.AVG_CW_DELTA_P));
		return composedInputMap;
	}

	private Map<String, Object> iterateAndCompose(Map<String, Object> map, Map<String, Double> defaultInputMap,
			Map<String, List<Double>> defaultListMap) {
		Map<String, Object> composedInputMap = new HashMap<>();

		for (Map.Entry<String, Object> entry : map.entrySet()) {

		    	String key=filterKey(entry.getKey());
			switch (key) {
			case CalculationConstants.AMBIENT_TEMP:
				defaultInputMap.put(CalculationConstants.AMBIENT_TEMP, Double.parseDouble(entry.getValue().toString()));
				composedInputMap.put(CalculationConstants.AMBIENT_TEMP,
						defaultInputMap.get(CalculationConstants.AMBIENT_TEMP));
				break;

			case CalculationConstants.HUMIDITY:
				defaultInputMap.put(CalculationConstants.HUMIDITY, Double.parseDouble(entry.getValue().toString()));
				composedInputMap.put(CalculationConstants.HUMIDITY, defaultInputMap.get(CalculationConstants.HUMIDITY));
				break;

			case CalculationConstants.BAROMETRIC_PRESSURE:
				defaultInputMap.put(CalculationConstants.BAROMETRIC_PRESSURE,
						Double.parseDouble(entry.getValue().toString()));
				composedInputMap.put(CalculationConstants.BAROMETRIC_PRESSURE,
						defaultInputMap.get(CalculationConstants.BAROMETRIC_PRESSURE));
				break;

			case CalculationConstants.CONDENSER_BACK_PRESSURE:
				defaultListMap.get(CalculationConstants.CONDENSER_BP)
						.add(Double.parseDouble(entry.getValue().toString()));
				break;

			case CalculationConstants.LP_EXHAUST_STEAM_TEMP:
				defaultListMap.get(CalculationConstants.TURBINE_EXHAUST_STEAM_TEMP)
						.add(Double.parseDouble(entry.getValue().toString()));
				break;

			case CalculationConstants.HOT_WELL_TEMP:
				defaultListMap.get(CalculationConstants.HOT_WELL_TEMP)
						.add(Double.parseDouble(entry.getValue().toString()));
				break;
			case CalculationConstants.BOILER_STEAM_FLOW:
				defaultListMap.get(CalculationConstants.TOTAL_BOILER_STEAM_FLOW)
						.add(Double.parseDouble(entry.getValue().toString()));
				break;

			case CalculationConstants.ATTEMPERATRION_STEAM_FLOW:
				defaultListMap.get(CalculationConstants.TOTAL_ATTEMP_STEAM_FLOW)
						.add(Double.parseDouble(entry.getValue().toString()));
				break;
			case CalculationConstants.COGEN_STEAM_FLOW:
				defaultListMap.get(CalculationConstants.TOTAL_COGEN_STEAM_FLOW)
						.add(Double.parseDouble(entry.getValue().toString()));
				break;
			case CalculationConstants.CONDENSER_CW_IN_TEMPS:
				defaultListMap.get(CalculationConstants.CW_TEMP_IN)
						.add(Double.parseDouble(entry.getValue().toString()));
				break;
			case CalculationConstants.CONDENSER_CW_OUT_TEMPS:
				defaultListMap.get(CalculationConstants.CW_TEMP_OUT)
						.add(Double.parseDouble(entry.getValue().toString()));
				break;
			case CalculationConstants.CONDENSER_CW_INLET_PRESSURE:
				defaultListMap.get(CalculationConstants.CW_INLET_PRESSURE)
						.add(Double.parseDouble(entry.getValue().toString()));
				break;
			case CalculationConstants.CONDENSER_CW_OUTLET_PRESSURE:
				defaultListMap.get(CalculationConstants.CW_OUTLET_PRESSURE)
						.add(Double.parseDouble(entry.getValue().toString()));
				break;
			case CalculationConstants.CONDENSER_CW_DELTA_PRESSURE:
				defaultListMap.get(CalculationConstants.CW_DELTA_P)
						.add(Double.parseDouble(entry.getValue().toString()));
				break;
			default:
				composedInputMap.put(entry.getKey(), entry.getValue());
				break;
			}

		}
		if (Objects.equals(0d, defaultInputMap.get(CalculationConstants.AMBIENT_TEMP))) {
			composedInputMap.put(CalculationConstants.AMBIENT_TEMP,
					defaultInputMap.get(CalculationConstants.AMBIENT_TEMP));
		}
		if (Objects.equals(0d, defaultInputMap.get(CalculationConstants.HUMIDITY))) {
			composedInputMap.put(CalculationConstants.HUMIDITY, defaultInputMap.get(CalculationConstants.HUMIDITY));
		}
		if (Objects.equals(29.92, defaultInputMap.get(CalculationConstants.BAROMETRIC_PRESSURE))) {
			composedInputMap.put(CalculationConstants.BAROMETRIC_PRESSURE,
					defaultInputMap.get(CalculationConstants.BAROMETRIC_PRESSURE));
		}
		return composedInputMap;

	}

	private String filterKey(String key) {
		if (StringUtils.contains(key, CalculationConstants.BOILER_STEAM_FLOW)
				||StringUtils.contains(key, CalculationConstants.CONDENSER_BACK_PRESSURE)
				||StringUtils.contains(key, CalculationConstants.LP_EXHAUST_STEAM_TEMP)
				||StringUtils.contains(key, CalculationConstants.HOT_WELL_TEMP)
				|| StringUtils.contains(key, CalculationConstants.COGEN_STEAM_FLOW)
				|| StringUtils.contains(key, CalculationConstants.ATTEMPERATRION_STEAM_FLOW)
				|| StringUtils.contains(key, CalculationConstants.CONDENSER_CW_IN_TEMPS)
				|| StringUtils.contains(key, CalculationConstants.CONDENSER_CW_OUT_TEMPS)
				|| StringUtils.contains(key, CalculationConstants.CONDENSER_CW_INLET_PRESSURE)
				|| StringUtils.contains(key, CalculationConstants.CONDENSER_CW_OUTLET_PRESSURE)
				|| StringUtils.contains(key, CalculationConstants.CONDENSER_CW_DELTA_PRESSURE)) {

			return StringUtils.chop(key);

		}
		return key;
	}

}