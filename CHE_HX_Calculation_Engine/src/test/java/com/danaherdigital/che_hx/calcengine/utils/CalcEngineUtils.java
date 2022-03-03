package com.danaherdigital.che_hx.calcengine.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.danaherdigital.che_hx.calcengine.dto.CalcReqDTO;
import com.danaherdigital.che_hx.calcengine.dto.CustomCalcReqDTO;
import com.danaherdigital.che_hx.calcengine.dto.DesignCalReqDTO;
import com.danaherdigital.che_hx.calcengine.dto.LookUpReqDTO;
import com.danaherdigital.che_hx.calcengine.dto.LookUpRespDTO;
import com.danaherdigital.che_hx.calcengine.dto.RequestDTO;


public class CalcEngineUtils {




	public static List<Object> getTemplateList() {

		return null;

	}



	public static CalcReqDTO getCalcReqDTOTemplate()
	{
		CalcReqDTO c=new CalcReqDTO();
		c.setEngine("java");
		c.setFunction("masterCalc");
		List<Map<String,Object>> inputList=getTemplateInput();
		c.setInput(inputList);
		return c;


	}

	public static Map<String,Double> getLookUpMap()
	{
		Map<String,Double> map=new HashMap<>();

		map.put(ApplicationConstants.PRESSURE, 2.65);
		map.put(ApplicationConstants.CORRECTION_FACTOR, 1.057);
		map.put(ApplicationConstants.C_FACTOR, 1.063);
		map.put(ApplicationConstants.COEFFICIENT, 709.3);
		map.put(ApplicationConstants.STEAM_TEMP, 108.7);
		map.put(ApplicationConstants.TOTAL_STEAM, 1108.7);
		map.put(ApplicationConstants.PENALTY, 2d);
		map.put(ApplicationConstants.HEAT_RATE, 12500d);
		map.put(ApplicationConstants.HEAT_ENTHALPY, 1032.22);
		map.put(ApplicationConstants.CO2_EMISSION, 215d);
		map.put(ApplicationConstants.COST_PER_UNIT, 5d);
		map.put(ApplicationConstants.COST_PER_MMBTUS, 1.15);
		map.put(ApplicationConstants.WALL_THCKNESS, 0.028);
		return map;

	}


	public static List<Map<String,Object>> getTemplateInput() {
		Map<String,Object> inputMap=new HashMap<>();
		inputMap.put(CalculationConstants.ID, 12345);

		inputMap.put(CalculationConstants.GROSS_PLANT_PRODUCTION, 647);
		inputMap.put(CalculationConstants.STEAM_TURBINE_LOAD, 0);
		inputMap.put(CalculationConstants.CW_TEMP_IN, 84.66);
		inputMap.put(CalculationConstants.CW_TEMP_OUT, 104.5);
		inputMap.put(CalculationConstants.HOT_WELL_TEMP1, 104.5);
		inputMap.put(CalculationConstants.TOTAL_ATTEMP_STEAM_FLOW, 104.5);
		inputMap.put(CalculationConstants.COGEN_STEAM_FLOW1, 104.5);

		inputMap.put(CalculationConstants.CW_INLET_PRESSURE, 11.50);
		inputMap.put(CalculationConstants.CW_OUTLET_PRESSURE, 8.0);
		inputMap.put(CalculationConstants.AMBIENT_BAROMETRIC_PRESSURE, 29.92);
		inputMap.put(CalculationConstants.TUBE_METAL_AND_WALL_CORR_FACTOR, 0.862);
		inputMap.put(CalculationConstants.TUBE_OD, 1.250);


		inputMap.put(CalculationConstants.PLANT_MAX_LOAD, 640);
		inputMap.put(CalculationConstants.CONDENSER_SATURATED_STEAM_TEMP, 112.6);

		inputMap.put(CalculationConstants.EXTRACTION_STEAM, 34);
		inputMap.put(CalculationConstants.CW_INLET_TEMP_CORR_FACTOR, 1.045);
		inputMap.put(CalculationConstants.NUMBER_OF_PRESSURES, 1);
		inputMap.put(CalculationConstants.CALCULATED_MOISTURE, 9.4);
		inputMap.put(CalculationConstants.DESIGN_WATER_FLOW, 156128);
		inputMap.put(CalculationConstants.DESIGN_UIDEAL, 696);
		inputMap.put(CalculationConstants.DESIGN_CF, 75);
		inputMap.put(CalculationConstants.DESIGN_TR, 17.9);
		inputMap.put(CalculationConstants.CONDENSING_SURFACE_AREA, 224969);
		inputMap.put(CalculationConstants.TYPICAL_HEAT_RATE_PENALTY, 1);
		inputMap.put(CalculationConstants.TYPICAL_HEAT_RATE, 7500);
		inputMap.put(CalculationConstants.CO2_EMMISSION_EXPECTED, 8219016);
		inputMap.put(CalculationConstants.TYPICAL_SALE_PRICE, 45);
		inputMap.put(CalculationConstants.AVERAGE_FUEL_COSTS_PER_MMBTUS, 1.15);
		inputMap.put(CalculationConstants.TOTAL_TUBE_ID_FLOW_AREA, 49.7);
		inputMap.put(CalculationConstants.CALCULATED_VELOCITY, 7.0);
		inputMap.put(CalculationConstants.DESIGN_CW_PRESSURE_DROP, 10);
		inputMap.put(CalculationConstants.TOTAL_BOILER_STEAM_FLOW, 4200);
		inputMap.put(CalculationConstants.TOTAL_ATTEMP_STEAM_FLOW, 120);
		inputMap.put(CalculationConstants.TOTAL_COGEN_STEAM_FLOW, 0);
		inputMap.put(CalculationConstants.BACK_PRESSURE_1, 2.8);
		inputMap.put(CalculationConstants.CONDENSER_OR_LP_EXHAUST_STEAM_TEMP_1, 2.8);
		inputMap.put(CalculationConstants.HOTWELL_TEMP, 2.8);
		inputMap.put(CalculationConstants.CW_DELTA_P, 2.8);
		inputMap.put(CalculationConstants.CONDENSER_OPERATING_BP, 2.80);

		inputMap.put(CalculationConstants.STEAM_LATENT_HEAT_ENTHALPY, 1035.94);
		inputMap.put(CalculationConstants.TOTAL_STEAM_ENTHALPY, 1105.99);
		inputMap.put(CalculationConstants.TARGET_BP_OVERALL, 2.57);
		inputMap.put(CalculationConstants.STEAM_ENTHALPY_OVERALL, 1109.06);
		inputMap.put(CalculationConstants.STEAM_ENTHALPY_CONDENSER_ONLY, 1110);
		inputMap.put(CalculationConstants.COOLING_TOWER_RETURN_TEMP,0);
		inputMap.put(CalculationConstants.COOLING_TOWER_SUPPLY_TEMP,0);
		inputMap.put(CalculationConstants.AMBIENT_TEMP,80);
		inputMap.put(CalculationConstants.HUMIDITY,80);

		return new ArrayList<Map<String,Object>>() {{add(inputMap);}};
	}


	public static List<Object> getTemplateInvalidInput() {
		Map<String,Object> inputMap=new HashMap<>();
		inputMap.put(CalculationConstants.ID, 12345);

		inputMap.put(CalculationConstants.GROSS_PLANT_PRODUCTION, null);
		inputMap.put(CalculationConstants.STEAM_TURBINE_LOAD, 0);
		inputMap.put(CalculationConstants.CW_TEMP_IN, 84.66);
		inputMap.put(CalculationConstants.CW_TEMP_OUT, 104.5);

		inputMap.put(CalculationConstants.CW_INLET_PRESSURE, 11.50);
		inputMap.put(CalculationConstants.CW_OUTLET_PRESSURE, 8.0);
		inputMap.put(CalculationConstants.AMBIENT_BAROMETRIC_PRESSURE, 29.92);
		inputMap.put(CalculationConstants.TUBE_METAL_AND_WALL_CORR_FACTOR, 0.862);


		inputMap.put(CalculationConstants.PLANT_MAX_LOAD, 640);
		inputMap.put(CalculationConstants.CONDENSER_SATURATED_STEAM_TEMP, 112.6);

		inputMap.put(CalculationConstants.EXTRACTION_STEAM, 34);
		inputMap.put(CalculationConstants.CW_INLET_TEMP_CORR_FACTOR, 1.045);
		inputMap.put(CalculationConstants.NUMBER_OF_PRESSURES, 1);
		inputMap.put(CalculationConstants.CALCULATED_MOISTURE, 9.4);
		inputMap.put(CalculationConstants.DESIGN_WATER_FLOW, 156128);
		inputMap.put(CalculationConstants.DESIGN_UIDEAL, 696);
		inputMap.put(CalculationConstants.DESIGN_CF, 75);
		inputMap.put(CalculationConstants.DESIGN_TR, 17.9);
		inputMap.put(CalculationConstants.CONDENSING_SURFACE_AREA, 224969);
		inputMap.put(CalculationConstants.TYPICAL_HEAT_RATE_PENALTY, 1);
		inputMap.put(CalculationConstants.TYPICAL_HEAT_RATE, 7500);
		inputMap.put(CalculationConstants.CO2_EMMISSION_EXPECTED, 8219016);
		inputMap.put(CalculationConstants.TYPICAL_SALE_PRICE, 45);
		inputMap.put(CalculationConstants.AVERAGE_FUEL_COSTS_PER_MMBTUS, 1.15);
		inputMap.put(CalculationConstants.TOTAL_TUBE_ID_FLOW_AREA, 49.7);
		inputMap.put(CalculationConstants.CALCULATED_VELOCITY, 7.0);
		inputMap.put(CalculationConstants.DESIGN_CW_PRESSURE_DROP, 10);
		inputMap.put(CalculationConstants.TOTAL_BOILER_STEAM_FLOW, 4200);
		inputMap.put(CalculationConstants.TOTAL_ATTEMP_STEAM_FLOW, 120);
		inputMap.put(CalculationConstants.TOTAL_COGEN_STEAM_FLOW, 0);
		inputMap.put(CalculationConstants.CONDENSER_OPERATING_BP, 2.8);

		inputMap.put(CalculationConstants.STEAM_LATENT_HEAT_ENTHALPY, 1035.94);
		inputMap.put(CalculationConstants.TOTAL_STEAM_ENTHALPY, 1105.99);
		inputMap.put(CalculationConstants.TARGET_BP_OVERALL, 2.57);
		inputMap.put(CalculationConstants.STEAM_ENTHALPY_OVERALL, 1109.06);
		inputMap.put(CalculationConstants.STEAM_ENTHALPY_CONDENSER_ONLY, 1110);


		return new ArrayList<Object>() {{add(inputMap);}};
	}

	public static DesignCalReqDTO getDesignCalcReqDTOTemplate()
	{
		DesignCalReqDTO d=new DesignCalReqDTO();
		d.setCondenserEffectiveArea(336426);
		d.setDesignCF(90);
		d.setDesignCWPressureDrop(10);
		d.setDesignDuty(2568.60);
		d.setDesignInletCoolingWaterTemp(84.8);
		d.setDesignSteamFlow(2748307);
		d.setDesignWaterFlow(286917);
		d.setExtractionSteam(34);
		d.setNumberOfPasses(2);
		d.setNumberOfTubesTotal(20540);
		d.setNumberOfUnpluggedTubes(20540);
		d.setPlantCapacityFactor(85);
		d.setPlantFuelSource("Lignite");
		d.setPlantMaxLoad(640);
		d.setPlantType("Sub-Critical (Press <=2400psi)");
		d.setTubeEffectiveLength(50.08);
		d.setTubeMetallurgy( "Type 316/317 SS");
		d.setTubeOD(1.250);
		d.setTubeWallGauge(22);
		d.setTypicalDollarMWhrSalePrice(45);
		return d;


	}


	public static DesignCalReqDTO getDesignCalcReqDTOTemplate2()
	{
		DesignCalReqDTO d=new DesignCalReqDTO();
		d.setCondenserEffectiveArea(336426);
		d.setDesignCF(90);
		d.setDesignCWPressureDrop(10);
		d.setDesignDuty(2568.60);
		d.setDesignInletCoolingWaterTemp(null);
		d.setDesignSteamFlow(2748307);
		d.setDesignWaterFlow(286917);
		d.setExtractionSteam(34);
		d.setNumberOfPasses(2);
		d.setNumberOfTubesTotal(20540);
		d.setNumberOfUnpluggedTubes(20540);
		d.setPlantCapacityFactor(85);
		d.setPlantFuelSource("Lignite");
		d.setPlantMaxLoad(640);
		d.setPlantType("Sub-Critical (Press <=2400psi)");
		d.setTubeEffectiveLength(50.08);
		d.setTubeMetallurgy( "Type 316/317 SS");
		d.setTubeOD(1.250);
		d.setTubeWallGauge(22);
		d.setTypicalDollarMWhrSalePrice(45);
		return d;


	}


	public static CustomCalcReqDTO getCustomCalcReqDTOTemplate()
	{
		CustomCalcReqDTO c=new CustomCalcReqDTO();
		Map<String,Object> inputMap = new HashMap<>();
		inputMap.put("plant_type", "Natural Gas");
		inputMap.put("plant_fuel_source", "Natural Gas");

		c.setInput(inputMap);
		return c;
	}

	public static CustomCalcReqDTO getCustomCalcReqDTOTemplate2()
	{
		CustomCalcReqDTO c=new CustomCalcReqDTO();
		Map<String,Object> inputMap = new HashMap<>();
		inputMap.put("plant_type1", "Natural Gas");
		inputMap.put("plant_fuel_source1", "Natural Gas");

		c.setInput(inputMap);
		return c;
	}



	public static CustomCalcReqDTO getInvalidCustomCalcReqDTOTemplate()
	{
		CustomCalcReqDTO c=new CustomCalcReqDTO();
		Map<String,Object> inputMap = new HashMap<>();
		inputMap.put("sd", "Natural Gas");
		c.setInput(inputMap);
		return c;
	}

	public static DesignCalReqDTO getInvalidDesignCalcReqDTOTemplate()
	{
		DesignCalReqDTO d=new DesignCalReqDTO();
		d.setCondenserEffectiveArea(336426);
		return d;
	}



	public static List<LookUpRespDTO> getLookupRespList()
	{
		List<LookUpRespDTO> list=new ArrayList<>();
		LookUpRespDTO dto=new LookUpRespDTO();
		dto.setCalculatedValues(getLookUpMap());
		LookUpReqDTO dto1=new LookUpReqDTO();
		dto1.setInput(null);
		dto1.getInput();
		RequestDTO inp8=new RequestDTO();
		inp8.setFrom("fuel");
		inp8.setTableName("lookup");
		inp8.setValue("Lignite");
		dto.setReq(inp8);

		list.add(dto);
		return list;



	}

	public static LookUpRespDTO createMockRespDTO()
	{
		LookUpRespDTO dto=new LookUpRespDTO();
		dto.setCalculatedValues(getLookUpMap());
		LookUpReqDTO dto1=new LookUpReqDTO();
		dto1.setInput(null);
		dto1.getInput();
		RequestDTO inp8=new RequestDTO();
		inp8.setFrom("fuel");
		inp8.setTableName("lookup");
		inp8.setValue("Lignite");
		dto.setReq(inp8);
		return dto;
	}


	public static List<RequestDTO> getLookUpReqList() {
		List<RequestDTO> list=new ArrayList<>();
		RequestDTO inp=new RequestDTO();
		inp.setFrom("bwg");
		inp.setTableName("HEIStandards");
		inp.setValue(22);
		inp.setValue2("Type 316/317 SS");

		RequestDTO inp2=new RequestDTO();
		inp2.setFrom("dia");
		inp2.setTableName("HEIStandards");
		inp2.setValue(1.250);
		inp2.setValue2(8.0);

		RequestDTO inp3=new RequestDTO();
		inp3.setFrom("bwg");
		inp3.setTableName("tubedata");
		inp3.setValue(22);

		RequestDTO inp4=new RequestDTO();
		inp4.setFrom("pressure");
		inp4.setTableName("steam");
		inp4.setValue(2.8);

		RequestDTO inp5=new RequestDTO();
		inp5.setFrom("temp");
		inp5.setTableName("steam");
		inp5.setValue(109.7);

		RequestDTO inp6=new RequestDTO();
		inp6.setFrom("temp");
		inp6.setTableName("wcf");
		inp6.setValue(84);

		RequestDTO inp7=new RequestDTO();
		inp7.setFrom("unit");
		inp7.setTableName("lookup");
		inp7.setValue("Sub-Critical (Press <=2400psi)");


		RequestDTO inp8=new RequestDTO();
		inp8.setFrom("fuel");
		inp8.setTableName("lookup");
		inp8.setValue("Lignite");

		CalculationUtil.round(Double.NaN, 0);
		list.add(inp);list.add(inp2);list.add(inp3);list.add(inp4);
		list.add(inp5);list.add(inp6);list.add(inp7);list.add(inp8);


		return list;
	}


}
