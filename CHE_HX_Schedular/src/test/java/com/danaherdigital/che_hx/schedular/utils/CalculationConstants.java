/*
 *
 */
package com.danaherdigital.che_hx.schedular.utils;

public class CalculationConstants {
	private CalculationConstants() {}
	//Unique id for each record
	public static final String ID = "_id";

	//Time Series Data
	public static final String GROSS_PLANT_PRODUCTION = "grossLoad";
	public static final String NET_PRODUCTION = "netProduction";
	public static final String STEAM_TURBINE_LOAD = "steamTurbineLoad";
	public static final String GT_LOAD_1 = "gtLoad1";
	public static final String GT_LOAD_2 = "gtLoad2";
	public static final String GT_LOAD_3 = "gtLoad3";
	public static final String GT_LOAD_4 = "gtLoad4";
	public static final String GROSS_HEAT_RATE = "grossHeatRate";
	public static final String NET_HEAT_RATE = "netHeatRate";
	public static final String AMBIENT_TEMP = "ambientTemp";
	public static final String HUMIDITY = "humidity";
	public static final String PLANT_WET_BULB = "plantWetBulb";
	public static final String BAROMETRIC_PRESSURE = "barometricPressure";
	public static final String BACK_PRESSURE_1 = "condenserBackPressure1";
	public static final String BACK_PRESSURE_2 = "condenserBackPressure2";
	public static final String BACK_PRESSURE_3 = "condenserBackPressure3";
	public static final String BACK_PRESSURE_4= "condenserBackPressure4";
	public static final String CONDENSER_OR_LP_EXHAUST_STEAM_TEMP_1 = "lpExhaustSteamTemp1";
	public static final String CONDENSER_OR_LP_EXHAUST_STEAM_TEMP_2 = "lpExhaustSteamTemp2";
	public static final String CONDENSER_OR_LP_EXHAUST_STEAM_TEMP_3 = "lpExhaustSteamTemp3";
	public static final String CONDENSER_OR_LP_EXHAUST_STEAM_TEMP_4 = "lpExhaustSteamTemp4";
	public static final String HOTWELL_TEMP = "hotWellTemp1";
	public static final String HOTWELL_TEMP_2 = "hotwellTemp2";
	public static final String HOTWELL_LEVEL = "hotwellLevel";
	public static final String HOTWELL_OR_CONDENSATE_DO = "hotwellOrCondensateDo";
	public static final String CONDENSATE_FLOW = "condensateFlow";
	public static final String BOILER_STEAM_FLOW_1 = "boilerSteamFlow1";
	public static final String BOILER_STEAM_FLOW_2 = "boilerSteamFlow2";
	public static final String COGEN_STEAM_FLOW = "cogenSteamFlow";
	public static final String TOTAL_SOLID_FUEL_USAGE = "totalSolidFuelUsage";
	public static final String GT_FUEL_USAGE_1 = "gtFuelUsage1";
	public static final String GT_FUEL_USAGE_2 = "gtFuelUsage2";
	public static final String GT_FUEL_USAGE_3 = "gtFuelUsage3";
	public static final String GT_FUEL_USAGE_4 = "gtFuelUsage4";
	public static final String GT_DUCT_FUEL_USAGE_1 = "gtDuctFuelUsage1";
	public static final String GT_DUCT_FUEL_USAGE_2 = "gtDuctFuelUsage2";
	public static final String GT_DUCT_FUEL_USAGE_3 = "gtDuctFuelUsage3";
	public static final String GT_DUCT_FUEL_USAGE_4 = "gtDuctFuelUsage4";
	public static final String TOTAL_GAS_FUEL_USAGE = "totalGasFuelUsage";
	public static final String CONDENSER_COOLING_WATER_TEMP_IN = "condenserCoolingWaterTempIn";
	public static final String CONDENSER_COOLING_WATER_TEMP_IN_2 = "condenserCoolingWaterTempIn2";
	public static final String CONDENSER_COOLING_WATER_TEMP_IN_3 = "condenserCoolingWaterTempIn3";
	public static final String CONDENSER_COOLING_WATER_TEMP_IN_4 = "condenserCoolingWaterTempIn4";
	public static final String CONDENSER_COOLING_WATER_TEMP_OUT= "condenserCoolingWaterTempOut";
	public static final String CONDENSER_COOLING_WATER_TEMP_OUT_2 = "condenserCoolingWaterTempOut2";
	public static final String CONDENSER_COOLING_WATER_TEMP_OUT_3 = "condenserCoolingWaterTempOut3";
	public static final String CONDENSER_COOLING_WATER_TEMP_OUT_4 = "condenserCoolingWaterTempOut4";
	public static final String AIR_REMOVAL = "airRemoval";
	public static final String VACUUM_PUMP_SEAL_WATER_TEMP_1 = "vacuumPumpSealWaterTemp1";
	public static final String VACUUM_PUMP_SEAL_WATER_TEMP_2 = "vacuumPumpSealWaterTemp2";
	public static final String CIRC_WATER_PUMP_DISCHARGE_PRESSURE_1 = "circWaterPumpDischargePressure1";
	public static final String CIRC_WATER_PUMP_DISCHARGE_PRESSURE_2 = "circWaterPumpDischargePressure2";
	public static final String CIRC_WATER_PUMP_DISCHARGE_PRESSURE_3 = "circWaterPumpDischargePressure3";
	public static final String CIRC_WATER_PUMP_1 = "circWaterPump1";
	public static final String CIRC_WATER_PUMP_2 = "circWaterPump2";
	public static final String CIRC_WATER_PUMP_3 = "circWaterPump3";
	public static final String MEASURED_COOLING_WATER_FLOW_RATE = "measuredCoolingWaterFlowRate";
	public static final String CONDENSER_INLET_PRESSURE = "condenserInletPressure";
	public static final String CONDENSER_INLET_PRESSURE_2 = "condenserInletPressure2";
	public static final String CONDENSER_OUTLET_PRESSURE = "condenserOutletPressure";
	public static final String CONDENSER_OUTLET_PRESSURE_2 = "condenserOutletPressure2";
	public static final String CONDENSER_DIFFERENTIAL_PRESSURE_1 = "condenserDifferentialPressure1";
	public static final String CONDENSER_DIFFERENTIAL_PRESSURE_2 = "condenserDifferentialPressure2";
	public static final String TOWER_FILL_WEIGHT_1 = "towerFillWeight1";
	public static final String TOWER_FILL_WEIGHT_2 = "towerFillWeight2";
	public static final String CONDENSATE_CATION_CONDUCTIVITY = "condensateCationConductivity";
	public static final String CONDENSATE_SODIUM = "condensateSodium";
	public static final String PH = "pH";
	public static final String COOLING_WATER_CONDUCTIVITY = "coolingWaterConductivity";
	public static final String COOLING_WATER_ORP = "coolingWaterOrp";
	public static final String POLYTRAK = "polytrak";
	public static final String OPEN = "open";
	public static final String COOLING_WATER_FREE_CHLORINE = "coolingWaterFreeChlorine";
	public static final String TURBIDITY = "turbidity";
	public static final String CW_INLET_PRESSURE ="condenserCwInletPressure1";
	public static final String CW_OUTLET_PRESSURE ="condenserCwOutletPressure1";
	public static final String AMBIENT_BAROMETRIC_PRESSURE ="barometricPressure";
	public static final String COOLING_TOWER_RETURN_TEMP ="coolingTowerReturnTemp";
	public static final String COOLING_TOWER_SUPPLY_TEMP ="coolingTowerSupplyTemp";

	//Design Data
	public static final String PLANT_MAX_LOAD = "plantMaxLoad";
	public static final String DESIGN_TR = "designTr";
	public static final String EXTRACTION_STEAM = "extractionSteam";
	public static final String NUMBER_OF_PRESSURES = "numberOfPressureSamplePoints";
	public static final String CALCULATED_MOISTURE = "calculatedMoisture";
	public static final String DESIGN_STEAM_FLOW = "designSteamFlow";
	public static final String DESIGN_WATER_FLOW = "designWaterFlow";
	public static final String DESIGN_UIDEAL = "designUideal";
	public static final String TUBE_METAL_AND_WALL_CORR_FACTOR = "tubeMetalAndWallCorrFactor";
	public static final String DESIGN_CF = "designCf";
	public static final String CONDENSING_SURFACE_AREA = "condensingSurfaceArea";
	public static final String TYPICAL_HEAT_RATE_PENALTY = "typicalHeatRatePenalty";
	public static final String TYPICAL_HEAT_RATE = "typicalHeatRate";
	public static final String CO2_EMMISSION_EXPECTED = "expectedCo2Emmissions";
	public static final String TYPICAL_SALE_PRICE = "typicalMwhrSalePrice";
	public static final String AVERAGE_FUEL_COSTS_PER_MMBTUS = "averageFuelCostsPerMmbtus";
	public static final String TOTAL_TUBE_ID_FLOW_AREA = "totalTubeIdFlowArea";
	public static final String CALCULATED_VELOCITY = "calculatedVelocity";
	public static final String DESIGN_CW_PRESSURE_DROP = "designCwPressureDrop";
	public static final String DESIGN_UACTUAL = "designUactual";
	public static final String TURBINE_EXHAUST_STEAM_TEMP = "turbineExhaustSteamTemp";
	public static final String TOTAL_BOILER_STEAM_FLOW = "boilerSteamFlow1";
	public static final String TOTAL_ATTEMP_STEAM_FLOW = "attemperatrionSteamFlow1";
	public static final String TOTAL_COGEN_STEAM_FLOW = "cogenSteamFlow";
	public static final String CW_TEMP_IN = "condenserCwInTemps1";
	public static final String CW_TEMP_OUT = "condenserCwOutTemps1";
	public static final String CONDENSER_OPERATING_BP="condenserOperatingBp";
	public static final String TUBE_OD="tubeOd";
	public static final String CW_DELTA_P = "condenserCwDeltaPressure1";


	//lookup tables data
	public static final String STEAM_LATENT_HEAT_ENTHALPY = "steamLatentHeatEnthalpy";
	public static final String TOTAL_STEAM_ENTHALPY = "totalSteamEnthalpy";
	public static final String TARGET_BP_OVERALL ="targetBpOverall";
	public static final String STEAM_ENTHALPY_OVERALL ="steamEnthalpyOverall";
	public static final String STEAM_ENTHALPY_CONDENSER_ONLY ="steamEnthalpyCondenserOnly";
	//derived data
	public static final String PERCENT_OF_FULL_LOAD = "percentageOfFullLoad";
	public static final String AVERAGE_CW_INLET_WATER_TEMP = "averageCwInletWaterTemp";
	public static final String AVERAGE_CW_OUTLET_WATER_TEMP = "averageCwOutletWaterTemp";
	public static final String CW_INLET_TEMP_CORR_FACTOR = "cwInletTempCorrFactor";
	public static final String CONDENSER_SATURATED_STEAM_TEMP = "condenserSaturatedSteamTemp";
	public static final String MEASURED_LMTD = "measuredLmtd";
	public static final String MEASURED_ITD = "measuredItd";
	public static final String MEASURED_CW_TTD = "measuredCwTtd";
	public static final String MEASURED_CW_TR = "measuredCwTr";
	public static final String SPEC_SHEET_DESIGN_TR = "specSheetDesignTr";
	public static final String COOLING_TOWER = "coolingTower";
	public static final String EXTRACTION_STEAM_FLOW = "extractionSteamFlow";
	public static final String EXHAUST_TURBINE_STEAM_FLOW = "exhaustTurbineSteamFlow";
	public static final String CALCULATED_DESIGN_MOISTURE_CONTENT_IN_STEAM = "calculatedDesignMoistureContentInSteam";
	public static final String MOISTURE_CONTENT_IN_STEAM = "moistureContentInSteam";
	public static final String CONDENSER_HEAT_DUTY_Q="condenserHeatDutyQ";
	public static final String TARGET_TR_BASED_ON_DUTY_DESIGN_FLOW="targetTrBasedOnDutyAndDesignFlow";
	public static final String TR_DEVIATION="trDeviation";
	public static final String TARGET_CW_OUTLET_TEMP_OVERALL="targetCwOutletTempOverall";
	public static final String U_DESIGN_OVERALL="udesignOverall";
	public static final String U_ACTUAL_OVERALL = "uactualOverall";
	public static final String NTU_OVERALL = "ntuOverall";
	public static final String BP_DEVIATION_OVERALL = "bpDeviationOverall";
	public static final String HEAT_RATE_DEVIATION_OVERALL = "heatRateDeviationOverall";
	public static final String EST_EXCESS_CO2_EMISSIONS_OVERALL = "estExcessCo2EmissionsOverall";
	public static final String STEAM_ENTHALPY_DEVIATION_OVERALL = "steamEnthalpyDeviationOverall";
	public static final String EFFECTIVE_TURBINE_STEAM_FLOW = "effectiveTurbineSteamFlow";
	public static final String TOTAL_ENTHALPY_DEVIATION_OVERALL = "totalEnthalpyDeviationOverall";
	public static final String PRODUCTION_DEVIATION_OVERALL = "productionDeviationOverall";
	public static final String DOLLAR_LOST_PER_DAY = "mwDollarLostPerDay";
	public static final String EST_EXCESS_FUEL_BURNED_OVERALL = "estExcessFuelBurnedOverall";
	public static final String EST_EXCESS_FUEL_DOLLAR_PER_DAY_OVERALL = "estExcessFuelDollarOverall";
	public static final String CALC_CURRENT_CW_FLOW = "calcCurrentCwFlow";
	public static final String CALC_CURRENT_CW_VELOCITY = "calcCurrentCwVelocity";
	public static final String SPEC_SHEET_DESIGN_CW_FLOW = "specSheetDesignCwFlow";
	public static final String SPEC_SHEET_DESIGN_CW_VELOCITY = "specSheetDesignCwVelocity";
	public static final String SPEC_SHEET_DESIGN_STEAM_FLOW = "specSheetDesignSteamFlow";

	public static final String SPEC_SHEET_DESIGN_CW_DELTA_P = "specSheetDesignCwDeltaP";
	public static final String SPEC_SHEET_DESIGN_U_ACTUAL = "specSheetDesignUactual";
	public static final String SPEC_SHEET_DESIGN_CF = "specSheetDesignCf";

	public static final String CALC_CW_DELTA_P = "calcCwDeltaP";
	public static final String CALCULATED_PARASITIC_LOAD = "calculatedParasiticLoad";
	public static final String DESIGN_CW_VELOCITY = "designCwVelocity";
	public static final String PRODUCTION_DEVIATION_CONDENSER_ONLY = "productionDeviationCondenserOnly";
	public static final String CW_OUTLET_TEMP_CONDENSER_ONLY = "cwOutletTempCondenserOnly";
	public static final String CAL_UACTUAL = "calcUactual";
	public static final String CAL_UIDEAL = "calcUideal";
	public static final String CAL_UDESIGN = "calcUdesign";
	public static final String TARGET_UACTUAL_CONDENSER_ONLY = "targetUactualCondenserOnly";
	public static final String NTU_CONDENSER_ONLY = "ntuCondenserOnly";
	public static final String TARGET_STEAM_TEMP_CONDENSER_ONLY = "targetSteamTempCondenserOnly";
	public static final String COOLING_TOWER_APPROACH = "coolingTowerApproach";
	public static final String CALC_WET_BULB = "calcWetBulb";
	public static final String VAPOR_PRESSURE = "vapourPressure";
	public static final String AIR_ENTHALPY = "airEnthalpy";
	public static final String YS = "ysDew";
	public static final String YS1 = "ysNotDew";
	public static final String T = "tDewC";
	public static final String PARTIAL_PRESSURE_AT_T = "partialPressureAtT";
	public static final String COOLING_TOWER_DELTA_T = "coolingTowerDeltaT";
	public static final String EST_EXCESS_FUEL_BURNED_CONDENSER_ONLY = "estExcessFuelBurnedCondenserOnly";
	public static final String CO2_EMISSIONS_DEVIATION_CONDENSER_ONLY = "co2EmissionsDeviationCondenserOnly";
	public static final String HEAT_RATE_DEVIATION_CONDENSER_ONLY = "heatRateDeviationCondenserOnly";
	public static final String TTD_DEVIATION = "ttdDeviation";
	public static final String BP_DEVIATION_CONDENSER_ONLY = "bpDeviationCondenserOnly";
	public static final String CURRENT_CF_PERCENTAGE = "currentCf";
	public static final String TOTAL_ENTHALPY_DEVIATION_CONDENSER_ONLY = "totalEnthalpyDeviationCondenserOnly";
	public static final String ENTHALPY_DEVIATION_CONDENSER_ONLY = "enthalpyDeviationCondenserOnly";
	public static final String TARGET_BP_CONDENSER_ONLY = "targetBpCondenserOnly";
	public static final String MASS_ABS_KG_H2O_PER_KG_AIR = "massAbsKgAir";
	public static final String TARGET_TTD_CONDENSER_ONLY = "targetTtdCondenserOnly";

	public static final String MEASURED_CWTTD = "measuredCwttd";
	public static final String ATM_PRESSURE_PSIA = "atmPressurePsia";
	public static final String ATM_PRESSURE_KPA = "atmPressureKpa";
	public static final String TARGET_STEAM_TEMP_OVERALL = "targetSteamTempOverall";





	//contants values

	public static final int PERCENT_OF_FULL_LOAD_CONSTANT = 100;
	public static final double ATM_PRESSURE_PSIA_CONSTANT=0.491;
	public static final double ATM_PRESSURE_KPA_CONSTANT = 6.89;
	public static final int EXTRACTION_STEAM_FLOW_CONSTANT1=1000;
	public static final int EXTRACTION_STEAM_FLOW_CONSTANT2=100;
	public static final int EXHAUST_TURBINE_STEAM_FLOW_CONSTANT=1000;
	public static final int MOISTURE_CONTENT_IN_STEAM_COSTANT=100;
	public static final int TARGET_TR_BASED_ON_DUTY_DESIGN_FLOW_CONSTANT=500;
	public static final int NTU_OVERALL_CONSTANT=500;
	public static final int EST_EXCESS_CO2_EMISSIONS_OVERALL_CONSTANT_1=1000;
	public static final int EST_EXCESS_CO2_EMISSIONS_OVERALL_CONSTANT_2=1000000;
	public static final int PRODUCTION_DEVIATION_OVERALL_PI=3412;
	public static final int PRODUCTION_DEVIATION_OVERALL_CONSTANT=1000;
	public static final int DOLLAR_LOST_PER_DAY_CONSTANT=24;
	public static final long EST_EXCESS_FUEL_DOLLAR_PER_DAY_OVERALL_CONSTANT_1=1000000;
	public static final int EST_EXCESS_FUEL_DOLLAR_PER_DAY_OVERALL_CONSTANT_2=24;

	public static final int CALC_CURRENT_CW_FLOW_CONSTANT=500;
	public static final int CALC_CURRENT_CW_VELOCITY_CONSTANT_1=60;
	public static final double CALC_CURRENT_CW_VELOCITY_CONSTANT_2=7.48;
	public static final int HUNDRED = 100;
	public static final int FIVE_HUNDRED = 500;
	public static final int THOUSAND = 1000;
	public static final int TEN_THOUSAND = 10000;
	public static final int LAKH = 100000;
	public static final int PI = 3412;

	public static final String ENGINE = "engine";
	public static final String FUNCTION = "function";
	public static final String MASTERCALC = "masterCalc";



}
