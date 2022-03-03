package com.danaherdigital.che_hx.calcengine.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danaherdigital.che_hx.calcengine.dto.CustomCalcReqDTO;
import com.danaherdigital.che_hx.calcengine.dto.DesignCalReqDTO;
import com.danaherdigital.che_hx.calcengine.dto.DesignCalRespDTO;
import com.danaherdigital.che_hx.calcengine.dto.RequestDTO;
import com.danaherdigital.che_hx.calcengine.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.calcengine.service.IDesignCalService;
import com.danaherdigital.che_hx.calcengine.service.ILookUpService;
import com.danaherdigital.che_hx.calcengine.utils.ApplicationConstants;
import com.danaherdigital.che_hx.calcengine.utils.CalculationUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class DesignCalServiceImpl.
 */
@Service

/** The Constant log. */
@Slf4j
public class DesignCalServiceImpl implements IDesignCalService {

	@Autowired
	ILookUpService lookUpService;

	/**
	 * Gets the calc design data.
	 *
	 * @param req the req
	 * @return the calc design data
	 * @throws ChemtreatException the chemtreat exception
	 */
	@Override
	public DesignCalRespDTO getCalcDesignData(DesignCalReqDTO req) throws ChemtreatException {
		log.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getCalcDesignData");
		return getDesignData(req);
	}

	@Override
	public CustomCalcReqDTO getCustomCalcDesignDataData(CustomCalcReqDTO req) throws ChemtreatException {
		log.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getCalcDesignData");
		return getDesingDataCustom(req);
	}

	private CustomCalcReqDTO getDesingDataCustom(CustomCalcReqDTO req) throws ChemtreatException {
		log.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getDesingDataCustom");

		if (Objects.isNull(req) || Objects.isNull(req.getInput())) {
			log.error("Invalid Input");
			throw new ChemtreatException(ApplicationConstants.INVALID_INPUT);
		}
		CustomCalcReqDTO response = new CustomCalcReqDTO();
		List<RequestDTO> reqDTO;
		Map<String, Double> lookUpRes = null;
		Map<String, Object> responseMap = new HashMap<>();
		try {

			req.getInput().entrySet();
			reqDTO = new ArrayList<>();
			for (Map.Entry<String, Object> entry : req.getInput().entrySet()) {
				RequestDTO reqs = new RequestDTO();
				if (StringUtils.equalsIgnoreCase(entry.getKey(), ApplicationConstants.PLANT_TYPE)) {
					reqs.setFrom(ApplicationConstants.LOOK_UP_UNIT);
					reqs.setTableName(ApplicationConstants.LOOK_UP);
					reqs.setValue(entry.getValue());
					reqDTO.add(reqs);

				}

				else if (StringUtils.equalsIgnoreCase(entry.getKey(), ApplicationConstants.PLANT_FUEL_SOURCE)) {
					reqs.setFrom(ApplicationConstants.LOOK_UP_FUEL);
					reqs.setTableName(ApplicationConstants.LOOK_UP);
					reqs.setValue(entry.getValue());
					reqDTO.add(reqs);
				}
			}

			lookUpRes = lookUpService.callLookUp(reqDTO);
			log.info("lookUpRes1...{}", lookUpRes);
			if (!lookUpRes.isEmpty()) {
				for (Map.Entry<String, Object> entry : req.getInput().entrySet()) {

					if (StringUtils.equalsIgnoreCase(entry.getKey(), ApplicationConstants.PLANT_TYPE)) {
						double typicalHeatRate = lookUpRes.get(ApplicationConstants.HEAT_RATE);
						responseMap.put("typical_heat_rate", typicalHeatRate);
					}

					else if (StringUtils.equalsIgnoreCase(entry.getKey(), ApplicationConstants.PLANT_FUEL_SOURCE)) {
						double averageFuelCostsPerUnit = lookUpRes.get(ApplicationConstants.COST_PER_UNIT);
						responseMap.put("average_fuel_costs_per_unit", averageFuelCostsPerUnit);
					}

				}
			}

			response.setInput(responseMap);

			return response;
		} catch (Exception e) {
			log.error("error in DesignCalServiceImpl.getDesignData ->{}", e);
			throw new ChemtreatException(ApplicationConstants.INVALID_INPUT);
		}

	}

	/**
	 * Gets the design data.
	 *
	 * @param req the req
	 * @return the design data
	 * @throws ChemtreatException the chemtreat exception
	 */
	private DesignCalRespDTO getDesignData(DesignCalReqDTO req) throws ChemtreatException {
		log.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getDesignData");

		if (Objects.isNull(req)) {
			log.error("Invalid Input");
			throw new ChemtreatException(ApplicationConstants.INVALID_INPUT);
		}
		DesignCalRespDTO resp = new DesignCalRespDTO();
		try {
			List<RequestDTO> reqDTO;
			Map<String, Double> lookUpRes = null;
			double calculatedDesignBP = 0;
			double designUideal = 0;
			double tubeODSurfaceAreaPerTube = tubeODSurfaceAreaPerTube(req.getTubeOD());
			resp.setTubeODSurfaceAreaPerTube(tubeODSurfaceAreaPerTube);
			double condensingSurfaceArea = condensingSurfaceArea(req.getNumberOfUnpluggedTubes(),
					req.getTubeEffectiveLength(), tubeODSurfaceAreaPerTube);
			resp.setCondensingSurfaceArea(condensingSurfaceArea);
			double surfaceAreaPercentageDifferenceFromDesign = surfaceAreaPercentageDifferenceFromDesign(
					condensingSurfaceArea, req.getCondenserEffectiveArea());
			resp.setSurfaceAreaPercentageDifferenceFromDesign(surfaceAreaPercentageDifferenceFromDesign);
			double designTR = designTR(req.getDesignDuty(), req.getDesignWaterFlow());
			resp.setDesignTR(designTR);
			double designCWTempOut = designCWTempOut(req.getDesignInletCoolingWaterTemp(), designTR);
			resp.setDesignCWTempOut(designCWTempOut);
			double designTempCorrFactor = 0;

			double tubeMetalAndWallCorrFactor = 0;
			double tubeWallThickness = 0;
			reqDTO = getReqDTO(req, ApplicationConstants.TUBE_DATA);
			lookUpRes = lookUpService.callLookUp(reqDTO);
			log.info("lookUpRes TUBE_DATA...{}", lookUpRes);
			if (!lookUpRes.isEmpty()) {
				tubeWallThickness = lookUpRes.get(ApplicationConstants.WALL_THCKNESS);
			}
			resp.setTubeWallThickness(tubeWallThickness);

			double tubeIDFlowAreaPerTube = tubeIDFlowAreaPerTube(req.getTubeOD(), tubeWallThickness);
			resp.setTubeIDFlowAreaPerTube(tubeIDFlowAreaPerTube);
			double totalTubeIDFlowArea = totalTubeIDFlowArea(tubeIDFlowAreaPerTube, req.getNumberOfUnpluggedTubes(),
					req.getNumberOfPasses());
			resp.setTotalTubeIDFlowArea(totalTubeIDFlowArea);
			double calculatedVelocity = calculatedVelocity(req.getDesignWaterFlow(), totalTubeIDFlowArea);
			resp.setCalculatedVelocity(calculatedVelocity);
			req.setCalculatedVelocity(calculatedVelocity);
			req.setDesignInletCwTemp(req.getDesignInletCoolingWaterTemp());

			reqDTO = getReqDTO(req, null);

			lookUpRes = lookUpService.callLookUp(reqDTO);
			log.info("lookUpRes1...{}", lookUpRes);
			if (!lookUpRes.isEmpty()) {
				designUideal = lookUpRes.get(ApplicationConstants.COEFFICIENT);
				resp.setDesignUideal(designUideal);
				designTempCorrFactor = lookUpRes.get(ApplicationConstants.CORRECTION_FACTOR);
				resp.setDesignTempCorrFactor(designTempCorrFactor);
				tubeMetalAndWallCorrFactor = lookUpRes.get(ApplicationConstants.C_FACTOR);
				resp.setTubeMetalAndWallCorrFactor(tubeMetalAndWallCorrFactor);

				double typicalHeatRate = lookUpRes.get(ApplicationConstants.HEAT_RATE);
				resp.setTypicalHeatRate(typicalHeatRate);
				double typicalHeatRatePenalty = lookUpRes.get(ApplicationConstants.PENALTY);
				resp.setTypicalHeatRatePenalty(typicalHeatRatePenalty / 100);
				double averageFuelCostsPerUnit = lookUpRes.get(ApplicationConstants.COST_PER_UNIT);
				resp.setAverageFuelCostsPerUnit(averageFuelCostsPerUnit);
				double averageFuelCostsPerMMbtus = lookUpRes.get(ApplicationConstants.COST_PER_MMBTUS);
				resp.setAverageFuelCostsPerMMbtus(averageFuelCostsPerMMbtus);
				double cO2EmmissionExpected = lookUpRes.get(ApplicationConstants.CO2_EMISSION);
				resp.setExpectedCo2Emmissions(cO2EmmissionExpected);
			}
			double designUdesign = designUdesign(designTempCorrFactor, designUideal, tubeMetalAndWallCorrFactor);
			resp.setDesignUdesign(designUdesign);
			double designUactual = designUactual(designUdesign, req.getDesignCF());
			resp.setDesignUactual(designUactual);
			double nTUs = nTUs(designUactual, condensingSurfaceArea, req.getDesignWaterFlow());

			resp.setNTUs(nTUs);
			double calculatedDesignSteamTemp = calculatedDesignSteamTemp(req.getDesignInletCoolingWaterTemp(),
					designCWTempOut, nTUs);

			resp.setCalculatedDesignSteamTemp(calculatedDesignSteamTemp);
			req.setCalculatedDesignSteamTemp(calculatedDesignSteamTemp);
			reqDTO = getReqDTO(req, ApplicationConstants.STEAM);
			lookUpRes = lookUpService.callLookUp(reqDTO);
			log.info("lookUpRes2...{}", lookUpRes);
			if (!lookUpRes.isEmpty()) {
				calculatedDesignBP = lookUpRes.get(ApplicationConstants.PRESSURE);
				resp.setCalculatedDesignBP(calculatedDesignBP);
				lookUpRes.clear();
			}
			double calculatedDesignSteamLatentHeat = calculatedDesignSteamLatentHeat(calculatedDesignBP);
			resp.setCalculatedDesignSteamLatentHeat(calculatedDesignSteamLatentHeat);
			double steamFlowBasedOnActualLatentHeatAndDuty = steamFlowBasedOnActualLatentHeatAndDuty(
					req.getDesignDuty(), calculatedDesignSteamLatentHeat);
			resp.setSteamFlowBasedOnActualLatentHeatAndDuty(steamFlowBasedOnActualLatentHeatAndDuty);
			double calculatedPercentageMoisture = calculatedPercentageMoisture(req.getDesignSteamFlow(),
					steamFlowBasedOnActualLatentHeatAndDuty);
			resp.setCalculatedPercentageMoisture(calculatedPercentageMoisture);

			return resp;
		} catch (Exception e) {
			log.error("error in DesignCalServiceImpl.getDesignData ->{}", e);
			throw new ChemtreatException(ApplicationConstants.INVALID_INPUT);
		}

	}

	/**
	 * Condensing surface area.
	 *
	 * @param No_of_Unplugged_Tubes         the no of unplugged tubes
	 * @param tube_Effective_Length         the tube effective length
	 * @param tube_OD_Surface_Area_Per_Tube the tube O D surface area per tube
	 * @return the double
	 */
	private double condensingSurfaceArea(Object noOfUnpluggedTubes, Object tubeEffectiveLength,
			Object tubeOdSurfaceAreaPerTube) {
		// = (No. of Unplugged Tubes) * (Tube Effective Length) * Tube OD Surface
		// Area/Tube

		return (objectToDouble(noOfUnpluggedTubes) * objectToDouble(tubeEffectiveLength)
				* objectToDouble(tubeOdSurfaceAreaPerTube));

	}

	/**
	 * Surface area percentage difference from design.
	 *
	 * @param condensingSurfaceArea    the condensing surface area
	 * @param condenser_Effective_Area the condenser effective area
	 * @return the double
	 */
	private double surfaceAreaPercentageDifferenceFromDesign(double condensingSurfaceArea,
			Object condenserEffectiveArea) {
		// = 100-((Condenser Surface Area/ Condenser Effective Area)*100)
		double a = condensingSurfaceArea / objectToDouble(condenserEffectiveArea);
		double b = a * 100;
		return 100 - b;

	}

	/**
	 * Steam flow based on actual latent heat and duty.
	 *
	 * @param design_Duty                         the design duty
	 * @param calculated_Design_Steam_Latent_Heat the calculated design steam latent
	 *                                            heat
	 * @return the double
	 */
	private double steamFlowBasedOnActualLatentHeatAndDuty(Object designDuty, double calculatedDesignSteamLatentHeat) {
		// "= Design Duty*1000000/Calculated Design Steam Latent Heat"
		return objectToDouble(designDuty) * 1000000 / calculatedDesignSteamLatentHeat;

	}

	/**
	 * Calculated percentage moisture.
	 *
	 * @param design_Steam_Flow                       the design steam flow
	 * @param steamFlowBasedOnActualLatentHeatAndDuty the steam flow based on actual
	 *                                                latent heat and duty
	 * @return the double
	 */
	private double calculatedPercentageMoisture(Object designSteamFlow,
			double steamFlowBasedOnActualLatentHeatAndDuty) {
		// = 100-(Steam Flow Based on Actual Latent Heat and Duty /Design Steam
		// Flow*100)
		return (100 - (steamFlowBasedOnActualLatentHeatAndDuty / objectToDouble(designSteamFlow) * 100));

	}

	/**
	 * Calculated design steam latent heat.
	 *
	 * @param calculated_Design_BP the calculated design BP
	 * @return the double
	 */
	private double calculatedDesignSteamLatentHeat(double calculatedDesignBp) {
		// "=(-19.3*LN(Calculated Design BP)+1049)

		return (-19.3 * Math.log(calculatedDesignBp) + 1049);

	}

	/**
	 * Calculated design steam temp.
	 *
	 * @param design_Inlet_CW_Temp the design inlet C W temp
	 * @param design_CT_Temp_Out   the design C T temp out
	 * @param NTUs                 the NT us
	 * @return the double
	 */
	private double calculatedDesignSteamTemp(Object designInletCwTemp, double designCtTempOut, Object nTUs) {
		// =((Design CT Temp Out-Design Inlet CW Temp*e^(-NTUs))/(1-e^(-NTUs))

		return (designCtTempOut - objectToDouble(designInletCwTemp) * Math.exp(-objectToDouble(nTUs)))
				/ (1 - Math.exp(-objectToDouble(nTUs)));

	}

	/**
	 * Design CW temp out.
	 *
	 * @param designInletCoolingWaterTemp the design inlet cooling water temp
	 * @param design_TR                   the design TR
	 * @return the double
	 */
	private double designCWTempOut(Object designInletCoolingWaterTemp, double designTr) {
		// "= Design Inlet Cooling Water Temp + Design TR"
		return objectToDouble(designInletCoolingWaterTemp) + designTr;

	}

	/**
	 * Design TR.
	 *
	 * @param design_Duty       the design duty
	 * @param design_Water_Flow the design water flow
	 * @return the double
	 */
	private double designTR(Object designDuty, Object designWaterFlow) {
		// "=(Design Duty*1000000)/(Design Water Flow*500)"
		return (objectToDouble(designDuty) * 1000000) / (objectToDouble(designWaterFlow) * 500);

	}

	/**
	 * Calculated velocity.
	 *
	 * @param design_Water_Flow          the design water flow
	 * @param tubeMetalAndWallCorrFactor the tube metal and wall corr factor
	 * @return the double
	 */
	private double calculatedVelocity(Object designWaterFlow, double tubeMetalAndWallCorrFactor) {
		// =(Design Water Flow/60/7.48)/(Total Metal and Wall Corr Factor)
		// (('Design Data'!B8/60/7.48)/F20)
		return (objectToDouble(designWaterFlow) / 60 / 7.48) / tubeMetalAndWallCorrFactor;

	}

	/**
	 * Design udesign.
	 *
	 * @param designTempCorrFactor       the design temp corr factor
	 * @param designUideal               the design uideal
	 * @param tubeMetalAndWallCorrFactor the tube metal and wall corr factor
	 * @return the double
	 */
	private double designUdesign(double designTempCorrFactor, double designUideal, double tubeMetalAndWallCorrFactor) {
		// = Design Temp Corr Factor * Design Uideal * Tube Metal and Wall Corr Factor
		return designTempCorrFactor * designUideal * tubeMetalAndWallCorrFactor;

	}

	/**
	 * Design uactual.
	 *
	 * @param designUdesign the design udesign
	 * @param designCF      the design CF
	 * @return the double
	 */
	private double designUactual(double designUdesign, Object designCF) {
		// "= Design Udesign*(Design CF/100)"
		return designUdesign * (objectToDouble(designCF) / 100);

	}

	/**
	 * NT us.
	 *
	 * @param designUactual         the design uactual
	 * @param condensingSurfaceArea the condensing surface area
	 * @param design_Water_Flow     the design water flow
	 * @return the double
	 */
	private double nTUs(double designUactual, double condensingSurfaceArea, Object designWaterFlow) {
		// =Design Uactual*Condensing Surface Area/(Design Water Flow*500)

		return designUactual * condensingSurfaceArea / (objectToDouble(designWaterFlow) * 500);

	}

	/**
	 * Tube ID flow area per tube.
	 *
	 * @param tube_OD           the tube OD
	 * @param tubeWallThickness the tube wall thickness
	 * @return the double
	 */
	private double tubeIDFlowAreaPerTube(Object tubeOd, double tubeWallThickness) {
		// =((Tube OD-2*Tube Wall Thickness)/2)^(2*3.141592)

		return Math.pow(((objectToDouble(tubeOd) - 2 * tubeWallThickness) / 2), 2) * 3.141592;

	}

	/**
	 * Total tube ID flow area.
	 *
	 * @param tubeIDFlowAreaPerTube     the tube ID flow area per tube
	 * @param number_Of_Unplugged_Tubes the number of unplugged tubes
	 * @param number_Of_Passes          the number of passes
	 * @return the double
	 */
	private double totalTubeIDFlowArea(double tubeIDFlowAreaPerTube, Object numberOfUnpluggedTubes,
			Object numberOfPasses) {
		// =(((Tube ID Flow Area per tube)/144)*number of unplugged tubes)/(number of
		// passes)
//(F19/144)*'Design Data'!B13/'Design Data'!B4

		return (tubeIDFlowAreaPerTube / 144) * objectToDouble(numberOfUnpluggedTubes) / objectToDouble(numberOfPasses);

	}

	/**
	 * Tube OD surface area per tube.
	 *
	 * @param tube_OD the tube OD
	 * @return the double
	 */
	private double tubeODSurfaceAreaPerTube(Object tubeOd) {
		// =(Tube OD/12)*3.141592
		return (objectToDouble(tubeOd) / 12) * 3.141592;

	}

	/**
	 * Object to double.
	 *
	 * @param o the o
	 * @return the double
	 */
	private Double objectToDouble(Object o) {
		return Double.parseDouble(o.toString());

	}

	private List<RequestDTO> getReqDTO(DesignCalReqDTO request, String table) {

		List<RequestDTO> reqList = new ArrayList<>();
		String[] tableName = new String[] { ApplicationConstants.DIA, ApplicationConstants.BWG,
				ApplicationConstants.WATER_CORRECTION_FACTOR, ApplicationConstants.LOOK_UP_UNIT,
				ApplicationConstants.LOOK_UP_FUEL };
		List<String> tables = new ArrayList<>(Arrays.asList(tableName));
		if (StringUtils.equalsIgnoreCase(table, ApplicationConstants.STEAM)) {
			RequestDTO req = new RequestDTO();
			req.setFrom(ApplicationConstants.STEAM_TEMP);
			req.setTableName(ApplicationConstants.STEAM);
			double d = 0;

			d = Double.parseDouble(request.getCalculatedDesignSteamTemp().toString());

			req.setValue(d);
			reqList.add(req);
		} else if (StringUtils.equalsIgnoreCase(table, ApplicationConstants.TUBE_DATA)) {
			RequestDTO req = new RequestDTO();
			req.setFrom(ApplicationConstants.BWG);
			req.setTableName(ApplicationConstants.TUBE_DATA);
			Double d = null;
			int i = 0;

			d = Double.parseDouble(request.getTubeWallGauge().toString());
			i = d.intValue();

			req.setValue(i);
			reqList.add(req);
		}

		else {
			for (String t : tables) {

				reqList.add(createReqDTO(t, request));

			}
		}

		return reqList;
	}

	/**
	 * Creates the req DTO.
	 *
	 * @param t       the t
	 * @param request the request
	 * @return the request DTO
	 */
	private RequestDTO createReqDTO(String t, DesignCalReqDTO request) {
		RequestDTO req = new RequestDTO();

		if (StringUtils.equalsIgnoreCase(t, ApplicationConstants.DIA)) {
			req.setFrom(ApplicationConstants.DIA);
			req.setTableName(ApplicationConstants.HEI_STANDARDS);
			double d = Double.parseDouble(request.getCalculatedVelocity().toString());
			req.setValue2(CalculationUtil.round(d * 2, 0) / 2);
			req.setValue(request.getTubeOD());

		} else if (StringUtils.equalsIgnoreCase(t, ApplicationConstants.BWG)) {
			req.setFrom(ApplicationConstants.BWG);
			req.setTableName(ApplicationConstants.HEI_STANDARDS);
			req.setValue2(request.getTubeMetallurgy());
			req.setValue(request.getTubeWallGauge());

		} else if (StringUtils.equalsIgnoreCase(t, ApplicationConstants.WATER_CORRECTION_FACTOR)) {
			req.setFrom(ApplicationConstants.STEAM_TEMP);
			req.setTableName(ApplicationConstants.WATER_CORRECTION_FACTOR);
			Double d = Double.parseDouble(request.getDesignInletCoolingWaterTemp().toString());
			req.setValue(d.intValue());

		}

		else if (StringUtils.equalsIgnoreCase(t, ApplicationConstants.LOOK_UP_UNIT)) {
			req.setFrom(ApplicationConstants.LOOK_UP_UNIT);
			req.setTableName(ApplicationConstants.LOOK_UP);
			req.setValue(request.getPlantType());

		}

		else if (StringUtils.equalsIgnoreCase(t, ApplicationConstants.LOOK_UP_FUEL)) {
			req.setFrom(ApplicationConstants.LOOK_UP_FUEL);
			req.setTableName(ApplicationConstants.LOOK_UP);
			req.setValue(request.getPlantFuelSource());

		}
		return req;
	}

}
