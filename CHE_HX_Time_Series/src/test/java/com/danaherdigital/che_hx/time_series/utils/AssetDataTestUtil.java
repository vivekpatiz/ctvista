package com.danaherdigital.che_hx.time_series.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.assertj.core.util.Arrays;

import com.danaherdigital.che_hx.time_series.dto.AssetDataDTO;
import com.danaherdigital.che_hx.time_series.dto.AssetLoadDataDTO;
import com.danaherdigital.che_hx.time_series.dto.AssetParamDTO;
import com.danaherdigital.che_hx.time_series.dto.AssetParamDTO2;
import com.danaherdigital.che_hx.time_series.dto.ChartData;
import com.danaherdigital.che_hx.time_series.dto.ChartDataArray;
import com.danaherdigital.che_hx.time_series.dto.Data;
import com.danaherdigital.che_hx.time_series.dto.ResDTO;
import com.danaherdigital.che_hx.time_series.dto.YAxisData;
import com.danaherdigital.che_hx.time_series.dto.YAxisReqDTO;
import com.danaherdigital.che_hx.time_series.dto.YAxisResponseDTO;
import com.danaherdigital.che_hx.time_series.model.AssetDataTimeSeries;
import com.danaherdigital.che_hx.time_series.model.CalculationConfigMaster;
import com.danaherdigital.che_hx.time_series.model.DerivedData;

public class AssetDataTestUtil {

	public static AssetDataDTO getAssetDataDTOTemplate() {
		AssetDataDTO assetDataDTO = null;
		{

			assetDataDTO = new AssetDataDTO("d3714dfe-d8b7-11ea-b09d-025041000001", new Date(), 23.44,
					"Condenser-1.gross_production", new Long(123456899L));
		}

		return assetDataDTO;

	}

	public static AssetParamDTO2 createMockAssetParamDTO2() {

		AssetParamDTO2 ap = new AssetParamDTO2() {

			@Override
			public String getName() {
				return "air_anthalpy";
			}

			@Override
			public String getId() {
				return "12345jfhf";
			}

			@Override
			public String getDisplayName() {
				return "Air Enthalpy";
			}
		};
		return ap;

	}

	public static CalculationConfigMaster createMockCalculationConfigMasterDO() {
		String ip = "[{\"uom\": \"\", \"name\": \"grossPlantProduction\"}, {\"uom\": \"\", \"name\": \"ambientTemp\"}, {\"uom\": \"\", \"name\": \"humidity\"}, {\"uom\": \"\", \"name\": \"ambientBarometricPressure\"}, {\"uom\": \"\", \"name\": \"condenserOperatingBp\"}, {\"uom\": \"\", \"name\": \"turbineExhaustSteamTemp\"}, {\"uom\": \"\", \"name\": \"hotWellTemp\"}, {\"uom\": \"\", \"name\": \"totalBoilerSteamFlow\"}, {\"uom\": \"\", \"name\": \"totalAttempSteamFlow\"}, {\"uom\": \"\", \"name\": \"totalCogenSteamFlow\"}, {\"uom\": \"\", \"name\": \"cwTempIn\"}, {\"uom\": \"\", \"name\": \"cwTempOut\"}, {\"uom\": \"\", \"name\": \"coolingTowerReturnTemp\"}, {\"uom\": \"\", \"name\": \"coolingTowerSupplyTemp\"}, {\"uom\": \"\", \"name\": \"cwInletPressure\"}, {\"uom\": \"\", \"name\": \"cwOutletPressure\"}, {\"uom\": \"\", \"name\": \"cwDeltaP\"}, {\"uom\": \"\", \"name\": \"plantMaxLoad\"}, {\"uom\": \"\", \"name\": \"designCwPressureDrop\"}, {\"uom\": \"\", \"name\": \"designTr\"}, {\"uom\": \"\", \"name\": \"designCf\"}, {\"uom\": \"\", \"name\": \"calculatedVelocity\"}, {\"uom\": \"\", \"name\": \"typicalSalePrice\"}, {\"uom\": \"\", \"name\": \"averageFuelCostsPerMmbtus\"}, {\"uom\": \"\", \"name\": \"totalTubeIdFlowArea\"}, {\"uom\": \"\", \"name\": \"designUideal\"}, {\"uom\": \"\", \"name\": \"condensingSurfaceArea\"}, {\"uom\": \"\", \"name\": \"designWaterFlow\"}, {\"uom\": \"\", \"name\": \"designSteamFlow\"}, {\"uom\": \"\", \"name\": \"co2EmmissionExpected\"}, {\"uom\": \"\", \"name\": \"extractionSteam\"}, {\"uom\": \"\", \"name\": \"numberOfPressures\"}, {\"uom\": \"\", \"name\": \"typicalHeatRatePenalty\"}, {\"uom\": \"\", \"name\": \"typicalHeatRate\"}, {\"uom\": \"\", \"name\": \"tubeMetalAndWallCorrFactor\"}, {\"uom\": \"\", \"name\": \"calculatedMoisture\"}, {\"uom\": \"\", \"name\": \"tubeOD\"}]";
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

	public static AssetParamDTO createMockAssetParamDTO() {
		AssetParamDTO apm = new AssetParamDTO() {

			@Override
			public Integer getTenantId() {
				return 1;
			}

			@Override
			public String getParam() {
				return "percentage_of_full_load";
			}

			@Override
			public String getId() {
				return UUID.randomUUID().toString();
			}

			@Override
			public String getAssetName() {
				return "a4aa545f-acca-4e7d-818b-37fe2d7a093d";
			}
		};
		return apm;

	}

	public static AssetDataTimeSeries createMockAssetDataTimeSeriesDO() {
		AssetDataTimeSeries adts = new AssetDataTimeSeries();
		adts.setAssetName(UUID.randomUUID().toString());
		adts.setAssetParamsName(UUID.randomUUID().toString());
		adts.setTimeStamp(123456l);
		adts.setFloatValues(234.6);
		adts.setAssetDataId(UUID.randomUUID().toString());
		adts.setStrValues(null);
		adts.setTagName("Condenser-1.percentage_of_full_load");
		adts.setTenantId(1);
		return adts;

	}

	public static DerivedData createMockDerivedDataDTO() {

		DerivedData dd = new DerivedData();
		dd.setAssetId(UUID.randomUUID().toString());
		dd.setInput(createMockInputMapList());
		dd.setTenantId(1);
		return dd;

	}

	public static List<Map<String, Object>> createMockInputMapList() {
		List<Map<String, Object>> list = new ArrayList<>();

		for (int i = 0; i < 1000; i++) {
			Map<String, Object> map = new HashMap<>();

			map.put("percentageOfFullLoad", 72.92);
			map.put("timeStamp", 123456l);
			list.add(map);
		}

		return list;

	}

	public static AssetLoadDataDTO getassetLoadDataDTOTemplate() {
		AssetLoadDataDTO assetLoadDataDTO = new AssetLoadDataDTO(123456899L, 29.0);

		return assetLoadDataDTO;
	}

	public static ResDTO createMockResDTO() {
		ResDTO res = new ResDTO();
		ChartData cd = new ChartData();
		Data d = new Data();
		d.setDate(new Date());
		d.setLoad(120D);
		d.setValue(23.4);
		List<Data> ld = new ArrayList<>();
		ld.add(d);
		cd.setData(ld);
		cd.setAvgKpi(1.4);
		List<ChartData> lcd = new ArrayList<>();
		lcd.add(cd);
		res.setChartData(lcd);

		return res;

	}

	public static YAxisReqDTO createMockYAxisReqDTO() {
		List<YAxisData> list = new ArrayList<>();
		YAxisReqDTO req = new YAxisReqDTO();
		req.setAssetId("126156jhbjhbhj");
		req.setFromdate("2020/06/18");
		req.setTodate("2020/07/06");
		req.setTenantid(1207);
		req.setMinLoadRange(23);
		req.setMaxLoadRange(70);
		YAxisData data = new YAxisData();
		data.setMax(100d);
		data.setMin(1d);
		data.setName("air_enthalpy");
		data.setParamId("326632jvcjhvcj");
		list.add(data);
		req.setData(list);
		return req;

	}

	public static YAxisResponseDTO createMockYAxisResponseDTO() {
		YAxisResponseDTO resp = new YAxisResponseDTO();
		List<ChartDataArray> chartData = new ArrayList<>();
		ChartDataArray array = new ChartDataArray();
		List<Data> dataList = new ArrayList<Data>();
		Data data = new Data();
		data.setDate(new Date());
		data.setLoad(32.4);
		data.setValue(23.2);
		array.setAssetParamName("8787whhwvjh");
		dataList.add(data);

		array.setData(dataList);
		array.setName("air_enthalpy");
		chartData.add(array);

		resp.setChartData(chartData);
		return resp;

	}

	public static YAxisResponseDTO createMockYAxisResponseDTO2() {
		YAxisResponseDTO resp = new YAxisResponseDTO();
		resp.setChartData(Collections.emptyList());
		return resp;

	}

}
