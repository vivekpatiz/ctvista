package com.danaherdigital.che_hx.schedular.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.danaherdigital.che_hx.schedular.dto.ApiResponseDTO;
import com.danaherdigital.che_hx.schedular.dto.AssetDataDTO;
import com.danaherdigital.che_hx.schedular.dto.AssetDataParamDTO;
import com.danaherdigital.che_hx.schedular.dto.CalcEngineDTO;
import com.danaherdigital.che_hx.schedular.dto.SliceResponseDTO;
import com.danaherdigital.che_hx.schedular.dto.TimeSeriesReqDTO;
import com.danaherdigital.che_hx.schedular.dto.UOMReqData;
import com.danaherdigital.che_hx.schedular.dto.UOMRespDTO;
import com.danaherdigital.che_hx.schedular.dto.UOMResult;
import com.danaherdigital.che_hx.schedular.dto.UnitAndPlantIdDTO;
import com.danaherdigital.che_hx.schedular.model.AssetData;
import com.danaherdigital.che_hx.schedular.model.AssetDataLog;
import com.danaherdigital.che_hx.schedular.model.AssetParam;
import com.danaherdigital.che_hx.schedular.model.CalcJob;
import com.danaherdigital.che_hx.schedular.model.CalculationConfigMaster;
import com.danaherdigital.che_hx.schedular.model.Input;
import com.danaherdigital.che_hx.schedular.model.UnitOfMeasure;

public class SchedularTestUtil {

	public static CalcEngineDTO createMockCalcEngineDTO() {

		CalcEngineDTO c = new CalcEngineDTO();
		c.setEngine("java");
		c.setFunction("masterCalc");
		c.setInput(getTemplateInput());
		return c;

	}

	public static List<Map<String, Object>> getTemplateInput() {
		Map<String, Object> inputMap = new HashMap<>();
		inputMap.put(CalculationConstants.ID, 12345);

		inputMap.put(CalculationConstants.GROSS_PLANT_PRODUCTION, 647);
		inputMap.put(CalculationConstants.STEAM_TURBINE_LOAD, 0);
		inputMap.put(CalculationConstants.CW_TEMP_IN, 84.66);
		inputMap.put(CalculationConstants.CW_TEMP_OUT, 104.5);

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
		inputMap.put(CalculationConstants.COOLING_TOWER_RETURN_TEMP, 0);
		inputMap.put(CalculationConstants.COOLING_TOWER_SUPPLY_TEMP, 0);
		inputMap.put(CalculationConstants.AMBIENT_TEMP, 80);
		inputMap.put(CalculationConstants.HUMIDITY, 80);

		return new ArrayList<Map<String, Object>>() {
			{
				add(inputMap);
			}
		};
	}

	public static List<AssetDataLog> createMockAssetDataLogList() {
		AssetDataLog adl = new AssetDataLog();
		adl.setAssetName(UUID.randomUUID().toString());
		adl.setEndDate(new Date().toString());
		adl.setFileName("test.xls");
		adl.setId(1l);
		adl.setRowCount(1234L);
		adl.setStartDate(new Date().toString());
		adl.setTenantId(2L);
		adl.setTimestamp(new Date());
		adl.setWorkFlowStatus("Calc-Completed");

		return new ArrayList<AssetDataLog>(Arrays.asList(adl));
	}

	public static AssetDataLog createMockAssetLog() {
		AssetDataLog adl = new AssetDataLog();
		adl.setId(1L);
		adl.setAssetName(UUID.randomUUID().toString());
		adl.setTimestamp(new Date());
		adl.setEndDate(new Date().toString());
		adl.setStartDate(new Date().toString());
		adl.setRowCount(11696L);
		adl.setTenantId(2L);
		adl.setWorkFlowStatus("done");
		adl.setFileName("xyz.xls");
		return adl;
	}

	public static AssetDataLog createMockInavliidAssetLog() {
		AssetDataLog adl = new AssetDataLog();
		adl.setId(1L);
		adl.setAssetName(UUID.randomUUID().toString());
		adl.setTimestamp(new Date());
		adl.setEndDate(null);
		adl.setStartDate(null);
		adl.setRowCount(11696L);
		adl.setTenantId(2L);
		adl.setWorkFlowStatus("done");
		adl.setFileName("xyz.xls");
		return adl;
	}

	public static List<AssetData> createMockAssetDataEntries() {
		List<AssetData> entries = new ArrayList();
		entries.add(createMockAssetData());
		return entries;
	}

	public static List<Long> createMockAssetDataTimestamp() {
		List<Long> entries = new ArrayList();
		entries.add(System.currentTimeMillis());
		return entries;
	}

	public static AssetData createMockAssetData() {
		AssetData ad = new AssetData();

		ad.setAssetName(UUID.randomUUID().toString());
		ad.setDataTimestamp(System.currentTimeMillis());
		ad.setFloatValues(12.4);
		ad.setTenantId(1L);
		return ad;
	}

	public static AssetDataDTO createMockAssetDataDTO() {
		AssetDataDTO ad = new AssetDataDTO() {

			@Override
			public Long getToDate() {
				// TODO Auto-generated method stub
				return System.currentTimeMillis();
			}

			@Override
			public Long getFromDate() {
				// TODO Auto-generated method stub
				return System.currentTimeMillis();
			}

			@Override
			public String getAssetName() {
				// TODO Auto-generated method stub
				return UUID.randomUUID().toString();
			}
		};
		return ad;

	}

	public static CalcJob createMockCalcJob() {
		CalcJob job = new CalcJob();
		job.setAssetName(UUID.randomUUID().toString());
		job.setEngine("java");
		job.setFromDate(System.currentTimeMillis());
		job.setId(UUID.randomUUID().toString());
		job.setJobDate(new Date());
		job.setMethod("masterCalc");
		job.setTenantId(1L);
		job.setStatus("Ready");
		job.setToDate(System.currentTimeMillis());
		return job;
	}

	public static UnitAndPlantIdDTO createMockUnitAndPlantIdDTO() {
		UnitAndPlantIdDTO udto = new UnitAndPlantIdDTO() {

			@Override
			public String getUnitId() {
				// TODO Auto-generated method stub
				return UUID.randomUUID().toString();
			}

			@Override
			public String getPlantId() {
				// TODO Auto-generated method stub
				return UUID.randomUUID().toString();
			}
		};
		return udto;
	}

	public static AssetParam createMockAssetParam(String name) {
		AssetParam ap = new AssetParam();
		ap.setAssetId(UUID.randomUUID().toString());
		ap.setCalculationMasterId("1");
		ap.setComputed((byte) 1);
		ap.setCustomerTagName("tagName");
		ap.setDataType("double");
		ap.setDescriptor("descriptor");
		ap.setDisplayName("displayName");
		ap.setId(UUID.randomUUID().toString());
		ap.setName(name);
		ap.setStatus((byte) 1);
		ap.setValue("12,300");
		ap.setType("historian");
		ap.setUnitOfMeasureId("uom");
		return ap;

	}

	public static List<AssetParam> createMockAssetParams() {
		List<AssetParam> apList = new ArrayList<AssetParam>();

		List<String> paramNames = new ArrayList<String>(Arrays.asList("plant_max_load", "design_cw_pressure_drop",
				"design_tr", "design_cf", "calculated_velocity", "typical_sale_price", "average_fuel_costs_per_mmbtus",
				"total_tube_id_flow_area", "design_uideal", "condensing_surface_area", "design_water_flow",
				"expected_co2_emmissions", "extraction_steam", "number_of_pressures", "typical_heat_rate",
				"typical_heat_rate_penalty", "tube_metal_and_wall_corr_factor", "calculated_moisture", "tube_od"));

		for (String s : paramNames) {
			apList.add(createMockAssetParam(s));

		}

		return apList;
	}

	public static AssetDataParamDTO createMockAssetDataParamDTO(String paramName, Long uom) {
		AssetDataParamDTO a = new AssetDataParamDTO() {

			@Override
			public String getStringValues() {
				return "";
			}

			@Override
			public String getName() {
				return paramName;
			}

			@Override
			public Object getFloatValues() {
				return 23.4;
			}

			@Override
			public Long getDataTimeStamp() {

				return System.currentTimeMillis();
			}

			@Override
			public String getId() {

				return UUID.randomUUID().toString();
			}

			@Override
			public Long getUom() {
				return uom;
			}

		};
		return a;

	}

	public static List<AssetDataParamDTO> createMockAssetDataParamDTOList() {
		List<AssetDataParamDTO> list = new ArrayList<AssetDataParamDTO>();
		int i = 0;
		List<String> paramNames = new ArrayList<String>(Arrays.asList("gross_load", "ambient_temp", "humidity",
				"barometric_pressure", "condenser_back_pressure_", "lp_exhaust_steam_temp_", "hotwell_temp_",
				"boiler_steam_flow_", "attemperatrion_steam_flow_", "cogen_steam_flow_", "condenser_cw_in_temps_",
				"condenser_cw_out_temps_", "cooling_tower_return_temp", "cooling_tower_supply_temp",
				"condenser_cw_inlet_pressure_", "condenser_cw_outlet_pressure_", "condenser_cw_delta_pressure_"));
		long[] id = { 256L, 4L, 393L, 398L, 115L };

		for (String s : paramNames) {
			list.add(createMockAssetDataParamDTO(s, id[i]));
			i++;
			if (i >= 5) {
				i = 0;
			}

		}
		return list;
	}

	public static List<AssetDataParamDTO> createMockAssetDataParamDTOList2() {
		List<AssetDataParamDTO> list = new ArrayList<AssetDataParamDTO>();
		int i = 0;
		List<String> paramNames = new ArrayList<String>(Arrays.asList("gross_load", "ambient_temp", "humidity",
				"barometric_pressure", "condenser_back_pressure_", "lp_exhaust_steam_temp_", "hotwell_temp_",
				"boiler_steam_flow_", "attemperatrion_steam_flow_", "cogen_steam_flow_", "condenser_cw_in_temps_",
				"condenser_cw_out_temps_", "cooling_tower_return_temp", "cooling_tower_supply_temp",
				"condenser_cw_inlet_pressure_", "condenser_cw_outlet_pressure_", "condenser_cw_delta_pressure_"));
		long[] id = { 256L, 124L, 392L, 394L, 394L, 124L, 124L, 398L, 398L, 398L, 124L, 124L, 124L, 124L, 115L, 115L,
				115L };

		for (String s : paramNames) {
			list.add(createMockAssetDataParamDTO(s, id[i]));
			i++;

		}
		return list;
	}

	public static Input createMockInput() {
		Input ip = new Input();

		ip.setAverageCwInletWaterTemp(12.1);
		ip.setAverageCwOutletWaterTemp(12.1);
		ip.setBpDeviationOverall(12.1);
		ip.setDollarLostPerDay(12.1);
		ip.setEstExcessCo2EmissionsOverall(12.1);
		ip.setEstExcessFuelBurnedOverall(12.1);
		ip.setEstExcessFuelDollarPerDayOverall(12.1);
		ip.setHeatRateDeviationOverall(12.1);
		ip.setId(UUID.randomUUID().toString());
		ip.setPercentOfFullLoad(12.1);
		ip.setProductionDeviationOverall(12.1);
		ip.setSteamEnthalpyDeviationOverall(12.1);
		ip.setSteamEnthalpyOverall(12.1);
		ip.setSteamLatentHeatEnthalpy(12.1);
		ip.setTargetBpOverall(12.1);
		ip.setTargetCwOutletTempOverall(12.1);
		ip.setTargetSteamTempOverall(12.1);
		ip.setTimeStamp(System.currentTimeMillis());
		ip.setTotalEnthalpyDeviationOverall(12.1);
		ip.setTotalSteamEnthalpy(12.1);
		ip.setTrDeviation(12.1);
		ip.setTtdDeviation(12.1);

		return ip;
	}

	public static SliceResponseDTO createMockSliceResponseDTO() {
		SliceResponseDTO sd = new SliceResponseDTO();
		sd.setAssetId(UUID.randomUUID().toString());
		sd.setJobs(new ArrayList<CalcJob>(Arrays.asList(createMockCalcJob())));
		sd.setStatus("completed");
		sd.setId(1L);
		sd.setTenantId(1L);
		return sd;
	}

	public static SliceResponseDTO createMockSliceResponseDTO2() {
		SliceResponseDTO sd = new SliceResponseDTO();
		sd.setAssetId(UUID.randomUUID().toString());
		sd.setId(1L);
		sd.setTenantId(1L);

		return sd;
	}

	public static SliceResponseDTO createMockSliceResponseDTO4() {
		SliceResponseDTO sd = new SliceResponseDTO();
		sd.setAssetId(UUID.randomUUID().toString());
		sd.setId(1L);
		sd.setTenantId(1L);
		sd.setRecordSame(true);
		return sd;
	}

	public static SliceResponseDTO createMockSliceResponseDTO3() {
		SliceResponseDTO sd = new SliceResponseDTO();
		sd.setJobs(new ArrayList<CalcJob>(Arrays.asList(createMockCalcJob())));
		return sd;
	}

	public static ApiResponseDTO createMockApiResponseDTO() {
		ApiResponseDTO a = new ApiResponseDTO();
		a.setAssetId(UUID.randomUUID().toString());
		a.setResult(new ArrayList<Input>(Arrays.asList(createMockInput())));
		a.setJobId(UUID.randomUUID().toString());
		a.setStatus("completed");
		a.setError("error");
		a.setTenantId(1L);
		a.setId(1L);
		return a;
	}

	public static CalculationConfigMaster createMockCalculationConfigMasterDO() {
		String ip = "[{\"name\": \"gross_load\"}, {\"name\": \"ambient_temp\"}, {\"name\": \"humidity\"}, {\"name\": \"barometric_pressure\"}, {\"name\": \"condenser_back_pressure_1\"}, {\"name\": \"condenser_back_pressure_2\"}, {\"name\": \"condenser_back_pressure_3\"}, {\"name\": \"condenser_back_pressure_4\"}, {\"name\": \"condenser_back_pressure_5\"}, {\"name\": \"condenser_back_pressure_6\"}, {\"name\": \"condenser_back_pressure_7\"}, {\"name\": \"condenser_back_pressure_8\"}, {\"name\": \"lp_exhaust_steam_temp_1\"}, {\"name\": \"lp_exhaust_steam_temp_2\"}, {\"name\": \"lp_exhaust_steam_temp_3\"}, {\"name\": \"lp_exhaust_steam_temp_4\"}, {\"name\": \"hotwell_temp_1\"}, {\"name\": \"hotwell_temp_2\"}, {\"name\": \"hotwell_temp_3\"}, {\"name\": \"hotwell_temp_4\"}, {\"name\": \"boiler_steam_flow_1\"}, {\"name\": \"boiler_steam_flow_2\"}, {\"name\": \"boiler_steam_flow_3\"}, {\"name\": \"boiler_steam_flow_4\"}, {\"name\": \"boiler_steam_flow_5\"}, {\"name\": \"boiler_steam_flow_6\"}, {\"name\": \"boiler_steam_flow_7\"}, {\"name\": \"boiler_steam_flow_8\"}, {\"name\": \"boiler_steam_flow_9\"}, {\"name\": \"boiler_steam_flow_10\"}, {\"name\": \"boiler_steam_flow_11\"}, {\"name\": \"boiler_steam_flow_12\"}, {\"name\": \"attemperatrion_steam_flow_1\"}, {\"name\": \"attemperatrion_steam_flow_2\"}, {\"name\": \"attemperatrion_steam_flow_3\"}, {\"name\": \"attemperatrion_steam_flow_4\"}, {\"name\": \"attemperatrion_steam_flow_5\"}, {\"name\": \"cogen_steam_flow_1\"}, {\"name\": \"cogen_steam_flow_2\"}, {\"name\": \"condenser_cw_in_temps_1\"}, {\"name\": \"condenser_cw_in_temps_2\"}, {\"name\": \"condenser_cw_in_temps_3\"}, {\"name\": \"condenser_cw_in_temps_4\"}, {\"name\": \"condenser_cw_in_temps_5\"}, {\"name\": \"condenser_cw_in_temps_6\"}, {\"name\": \"condenser_cw_in_temps_7\"}, {\"name\": \"condenser_cw_in_temps_8\"}, {\"name\": \"condenser_cw_out_temps_1\"}, {\"name\": \"condenser_cw_out_temps_2\"}, {\"name\": \"condenser_cw_out_temps_3\"}, {\"name\": \"condenser_cw_out_temps_4\"}, {\"name\": \"condenser_cw_out_temps_5\"}, {\"name\": \"condenser_cw_out_temps_6\"}, {\"name\": \"condenser_cw_out_temps_7\"}, {\"name\": \"condenser_cw_out_temps_8\"}, {\"name\": \"cooling_tower_return_temp\"}, {\"name\": \"cooling_tower_supply_temp\"}, {\"name\": \"condenser_cw_inlet_pressure_1\"}, {\"name\": \"condenser_cw_inlet_pressure_2\"}, {\"name\": \"condenser_cw_inlet_pressure_3\"}, {\"name\": \"condenser_cw_inlet_pressure_4\"}, {\"name\": \"condenser_cw_outlet_pressure_1\"}, {\"name\": \"condenser_cw_outlet_pressure_2\"}, {\"name\": \"condenser_cw_outlet_pressure_3\"}, {\"name\": \"condenser_cw_outlet_pressure_4\"}, {\"name\": \"condenser_cw_delta_pressure_1\"}, {\"name\": \"condenser_cw_delta_pressure_2\"}, {\"name\": \"condenser_cw_delta_pressure_3\"}, {\"name\": \"condenser_cw_delta_pressure_4\"}, {\"name\": \"plant_max_load\"}, {\"name\": \"design_cw_pressure_drop\"}, {\"name\": \"design_tr\"}, {\"name\": \"design_cf\"}, {\"name\": \"design_uactual\"}, {\"name\": \"calculated_velocity\"}, {\"name\": \"typical_mwhr_sale_price\"}, {\"name\": \"average_fuel_costs_per_mmbtus\"}, {\"name\": \"total_tube_id_flow_area\"}, {\"name\": \"design_uideal\"}, {\"name\": \"condensing_surface_area\"}, {\"name\": \"design_water_flow\"}, {\"name\": \"design_steam_flow\"}, {\"name\": \"expected_co2_emmissions\"}, {\"name\": \"extraction_steam\"}, {\"name\": \"number_of_pressure_sample_points\"}, {\"name\": \"typical_heat_rate\"}, {\"name\": \"typical_heat_rate_penalty\"}, {\"name\": \"tube_metal_and_wall_corr_factor\"}, {\"name\": \"calculated_moisture\"}, {\"name\": \"tube_od\"}]";
		String op = "[{\"name\": \"percentage_of_full_load\"}, {\"name\": \"average_cw_inlet_water_temp\"}, {\"name\": \"average_cw_outlet_water_temp\"}, {\"name\": \"cw_inlet_temp_corr_factor\"}, {\"name\": \"condenser_saturated_steam_temp\"}, {\"name\": \"measured_lmtd\"}, {\"name\": \"measured_itd\"}, {\"name\": \"measured_cw_ttd\"}, {\"name\": \"measured_cw_tr\"}, {\"name\": \"spec_sheet_design_tr\"}, {\"name\": \"cooling_tower_delta_t\"}, {\"name\": \"extraction_steam_flow\"}, {\"name\": \"exhaust_turbine_steam_flow\"}, {\"name\": \"calculated_design_moisture_content_in_steam\"}, {\"name\": \"moisture_content_in_steam\"}, {\"name\": \"effective_turbine_steam_flow\"}, {\"name\": \"spec_sheet_design_steam_flow\"}, {\"name\": \"steam_latent_heat_enthalpy\"}, {\"name\": \"total_steam_enthalpy\"}, {\"name\": \"condenser_heat_duty_q\"}, {\"name\": \"target_tr_based_on_duty_and_design_flow\"}, {\"name\": \"tr_deviation\"}, {\"name\": \"target_cw_outlet_temp_overall\"}, {\"name\": \"udesign_overall\"}, {\"name\": \"uactual_overall\"}, {\"name\": \"ntu_overall\"}, {\"name\": \"target_steam_temp_overall\"}, {\"name\": \"target_bp_overall\"}, {\"name\": \"steam_enthalpy_overall\"}, {\"name\": \"bp_deviation_overall\"}, {\"name\": \"heat_rate_deviation_overall\"}, {\"name\": \"est_excess_co2_emissions_overall\"}, {\"name\": \"steam_enthalpy_deviation_overall\"}, {\"name\": \"total_enthalpy_deviation_overall\"}, {\"name\": \"production_deviation_overall\"}, {\"name\": \"mw_dollar_lost_per_day\"}, {\"name\": \"est_excess_fuel_burned_overall\"}, {\"name\": \"est_excess_fuel_dollar_overall\"}, {\"name\": \"calc_current_cw_flow\"}, {\"name\": \"spec_sheet_design_cw_flow\"}, {\"name\": \"calc_current_cw_velocity\"}, {\"name\": \"spec_sheet_design_cw_velocity\"}, {\"name\": \"calc_cw_delta_p\"}, {\"name\": \"spec_sheet_design_cw_delta_p\"}, {\"name\": \"calc_uideal\"}, {\"name\": \"calc_udesign\"}, {\"name\": \"calc_uactual\"}, {\"name\": \"spec_sheet_design_uactual\"}, {\"name\": \"current_cf\"}, {\"name\": \"spec_sheet_design_cf\"}, {\"name\": \"target_uactual_condenser_only\"}, {\"name\": \"ntu_condenser_only\"}, {\"name\": \"cw_outlet_temp_condenser_only\"}, {\"name\": \"target_steam_temp_condenser_only\"}, {\"name\": \"target_bp_condenser_only\"}, {\"name\": \"bp_deviation_condenser_only\"}, {\"name\": \"target_ttd_condenser_only\"}, {\"name\": \"ttd_deviation\"}, {\"name\": \"steam_enthalpy_condenser_only\"}, {\"name\": \"enthalpy_deviation_condenser_only\"}, {\"name\": \"total_enthalpy_deviation_condenser_only\"}, {\"name\": \"production_deviation_condenser_only\"}, {\"name\": \"heat_rate_deviation_condenser_only\"}, {\"name\": \"cw_inlet_temp_corr_factor\"}, {\"name\": \"co2_emissions_deviation_condenser_only\"}, {\"name\": \"est_excess_fuel_burned_condenser_only\"}, {\"name\": \"atm_pressure_psia\"}, {\"name\": \"atm_pressure_kpa\"}, {\"name\": \"partial_pressure_at_t\"}, {\"name\": \"t_dew_c\"}, {\"name\": \"ys_not_dew\"}, {\"name\": \"ys_dew\"}, {\"name\": \"mass_abs_kg_air\"}, {\"name\": \"air_enthalpy \"}, {\"name\": \"vapour_pressure \"}, {\"name\": \"calc_wet_bulb\"}, {\"name\": \"cooling_tower_approach\"}]";
		CalculationConfigMaster cm = new CalculationConfigMaster();
		cm.setAssetTypeId(UUID.randomUUID().toString());
		cm.setCalculationName("");
		cm.setEngine("java");
		cm.setId(UUID.randomUUID().toString());
		cm.setInputParams(ip);
		cm.setOutputParams(op);
		cm.setStatus((byte) 1);
		cm.setType("masterCalc");
		return cm;

	}

	public static List<UnitOfMeasure> createMockUnitOfMeasureDOList() {
		List<UnitOfMeasure> uomList = new ArrayList<>();

		String[] uom = { "MW1", "F", "inHg(a)", "kpph1", "psi1" };
		long[] id = { 256L, 4L, 393L, 398L, 115L };
		for (int i = 0; i < uom.length; i++) {
			uomList.add(createUnitOfMeasureDO(uom[i], id[i]));

		}

		return uomList;
	}

	public static List<UnitOfMeasure> createMockUnitOfMeasureDOList2() {
		List<UnitOfMeasure> uomList = new ArrayList<>();

		String[] uom = { "MW", "°F", "%", "inHg(v)", "kpph", "psi" };
		long[] id = { 256L, 124L, 392L, 394L, 398L, 115L };
		for (int i = 0; i < uom.length; i++) {
			uomList.add(createUnitOfMeasureDO(uom[i], id[i]));

		}

		return uomList;
	}

	public static UnitOfMeasure createUnitOfMeasureDO(String uom, long id) {
		UnitOfMeasure u = new UnitOfMeasure();
		u.setDimension("length^2 x mass x time^-3");
		u.setDisplayName(uom);
		u.setGroup("power");
		u.setId(id);
		u.setName("Mega watt");
		u.setSymbol("MW");

		return u;
	}

	public static UOMRespDTO createMockUOMRespDTO() {
		UOMRespDTO resp = new UOMRespDTO();
		UOMResult res = new UOMResult();
		UOMReqData req = new UOMReqData();
		req.setFromUom("W");
		req.setToUom("MW");
		req.setValue(12d);
		req.setId(UUID.randomUUID().toString());
		res.setConvertedValue(123d);
		res.setInput(req);
		resp.setList(new ArrayList<UOMResult>(Arrays.asList(res)));
		return resp;
	}

	public static TimeSeriesReqDTO createMockTimeSeriesReqDTO() {
		TimeSeriesReqDTO t = new TimeSeriesReqDTO();
		t.setAssetId(UUID.randomUUID().toString());
		t.setInput(new ArrayList<>(Arrays.asList(createMockInput())));
		return t;

	}

}
