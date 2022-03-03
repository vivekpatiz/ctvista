package com.danaherdigital.che_hx.asset_management.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.danaherdigital.che_hx.asset_management.dto.AssetDTO;
import com.danaherdigital.che_hx.asset_management.dto.AssetFacilityDTO;
import com.danaherdigital.che_hx.asset_management.dto.AssetIngestionLogDTO;
import com.danaherdigital.che_hx.asset_management.dto.ChartsConfigDTO;
import com.danaherdigital.che_hx.asset_management.dto.CondenserDTO;
import com.danaherdigital.che_hx.asset_management.dto.HistorianDataDTO;
import com.danaherdigital.che_hx.asset_management.dto.PlantDataDTO;
import com.danaherdigital.che_hx.asset_management.model.Asset;
import com.danaherdigital.che_hx.asset_management.model.AssetParam;
import com.danaherdigital.che_hx.asset_management.model.AssetType;
import com.danaherdigital.che_hx.asset_management.model.AssetTypeParam;
import com.danaherdigital.che_hx.asset_management.model.Tenant;

public class AssetTestUtil {

	public static AssetDTO getAssetDTOTemplate() {
		AssetDTO assetDTO = null;
		{

			assetDTO = new AssetDTO("d3714dfe-d8b7-11ea-b09d-025041000001", new Date(), new Date(), "kevin", "kevin",
					"Condenser-1", true, "Facility-1", "Tenaska", "Tenaska");
		}

		return assetDTO;

	}

//	public static Optional<AssetParam> getAssetParamUnitTemplate() {
//		AssetParam assetParam = null;
//		{
//			assetParam = new AssetParam("d3714dfe-d8b7-11ea-b09d-025041000001", "design_steam_flow", "design steam flow",
//					new Asset(), "double", true, "", "",
//					"", "DESIGN", "122", "22", false);
//
//		}
//
//		return Optional.of(assetParam);
//
//	}DesignCalRespDTO

	public static Optional<AssetParam> getAssetParamFacilityTemplate() {
		AssetParam assetParam = null;
		{
			assetParam = new AssetParam("d3714dfe-d8b7-11ea-b09d-025041000001", "extraction_steam", "extraction steam",
					new Asset(), "double", true, "", "",
					"", "DESIGN", "122", "22", false);

		}

		return Optional.of(assetParam);

	}

	public static List<PlantDataDTO> getPlantDataDTOListTemplate() {
		List<PlantDataDTO> plantDataDTOList = new ArrayList<>();
		{

			plantDataDTOList.add(new PlantDataDTO("condenser_effective_area", "2332.44"));
			plantDataDTOList.add(new PlantDataDTO("design_steam_flow", "2332.44"));
		}

		return plantDataDTOList;

	}

	public static List<HistorianDataDTO> getHistorianDataDTOListTemplate() {
		List<HistorianDataDTO> HistorianDataDTOList = new ArrayList<>();
		{

			HistorianDataDTOList.add(new HistorianDataDTO("gross_load","MW","66", "640","123"));
			HistorianDataDTOList.add(new HistorianDataDTO("net_load","MW","66", "640","123"));
		}

		return HistorianDataDTOList;

	}

	public static AssetFacilityDTO getAssetFacilityDTOTemplate() {
		AssetFacilityDTO assetFacilityDTO = null;
		{

			assetFacilityDTO = new AssetFacilityDTO() {

				@Override
				public String getUnitName() {

					return "Unit-1";
				}

				@Override
				public String getUnitId() {
					// TODO Auto-generated method stub
					return "d3714dfe-d8b7-11ea-b09d-025041000001";
				}

				@Override
				public String getFacilityName() {
					// TODO Auto-generated method stub
					return "Facility-1";
				}

				@Override
				public String getFacilityId() {
					// TODO Auto-generated method stub
					return "d3714dfe-d8b7-11ea-b09d-225041000001";
				}
			};
		}

		return assetFacilityDTO;

	}

	public static Optional<CondenserDTO> getCondenserDTOTemplate() {
		CondenserDTO condenserDTO = null;
		{

			condenserDTO = new CondenserDTO("d3714dfe-d8b7-11ea-b09d-025041000001", new Date(), new Date(), "kevin",
					"kevin", "Condenser-1", true, "Facility-1", "Tenaska", "Tenaska", 1);

		}

		return Optional.of(condenserDTO);

	}

	public static Optional<Tenant> getOptionalTenantTemplate() {
		Tenant tenant = new Tenant(1, 1);

		return Optional.of(tenant);

	}

//	public static Optional<Asset> getOptionalAssetTemplateNull() {
//
//		return Optional.empty();
//
//	}



	public static ChartsConfigDTO getChartsConfigDTOTemplate() {

		ChartsConfigDTO chartsConfigDTO = new ChartsConfigDTO("duct_burner_fuel_flow_1",
				"04718dd3-77c9-4f56-ab79-dd5d98b970d6", "MW", "Duct Burner Fuel Flow #1", "RAW", false,"KPI");
		return chartsConfigDTO;

	}



	public static Optional<Asset> getOptionalAssetTemplate() {

		Asset assetType = new Asset("eb2c4e2b-f819-480f-9a9e-fcdf991ba47c", "", "Condenser", true, new AssetType(),
				new Tenant(), new Asset());
		return Optional.of(assetType);

	}
	
	public static AssetIngestionLogDTO getInflowLogTemplate() {

		AssetIngestionLogDTO assetIngestionLogDTO = new AssetIngestionLogDTO(new Date(), "Condenser","done","fileName",232.22,new Date(),new Date(),new Date(),new Date());
		return assetIngestionLogDTO;

	}
	
	public static AssetIngestionLogDTO getInflowLogTemplate2() {

		AssetIngestionLogDTO assetIngestionLogDTO = new AssetIngestionLogDTO(new Date(), "Condenser2","Calc-Completed","fileName",232.22,new Date(),new Date(),new Date(),new Date());
		return assetIngestionLogDTO;

	}
	
	public static AssetIngestionLogDTO getInflowLogTemplate3() {

		AssetIngestionLogDTO assetIngestionLogDTO = new AssetIngestionLogDTO(new Date(), "Condenser3","failed","fileName",232.22,new Date(),new Date(),new Date(),new Date());
		return assetIngestionLogDTO;

	}

	public static Optional<AssetType> getOptionalAssetTypeParamTemplate() {
		AssetType assetType = new AssetType("eb2c4e2b-f819-480f-9a9e-fcdf991ba47c", "Condenser",null);

		return Optional.of(assetType);

	}

	public static AssetTypeParam getAssetTypeParam() {
		return new AssetTypeParam("56b31e31-3819-a715-7a00-2d587c6aa3bc", "", "plant_type", "Plant Type", "double",
				true, "134", "11", new AssetType(), true);

	}

	public static AssetTypeParam getAssetTypeParam2() {
		return new AssetTypeParam("56b31e31-3819-a715-7a00-2d587c6aa3bc", "", "design_duty", "Design duty", "double",
				true, "134", "11", new AssetType(), true);

	}

	public static AssetTypeParam getAssetTypeParam4() {
		return new AssetTypeParam("56b31e31-3819-a715-7a00-23587c6aa3bc", "", "extraction_steam", "extraction_steam", "double",
				true, "134", "11", new AssetType(), true);

	}

	public static AssetTypeParam getAssetTypeParam5() {
		return new AssetTypeParam("56b31e31-3819-a715-7a00-2f587c6aa3bc", "", "design_steam_flow", "design_steam_flow", "double",
				true, "134", "11", new AssetType(), true);

	}

	public static AssetTypeParam getAssetTypeParam6() {
		return new AssetTypeParam("56b31e31-3819-a715-7a00-2f587c6aa3bc", "", "gross_load", "gross Load", "double",
				true, "134", "11", new AssetType(), true);

	}

	public static AssetTypeParam getAssetTypeParam3() {
		return new AssetTypeParam("56b31e31-3819-a715-7a00-2d587c6aa3bc", "", "gross_load", "Gross load", "double",
				true, "134", "", new AssetType(), true);

	}



	public static String getSaveCondenserTemplate() {
		return "{    \"condenserName\": \"TPBd15672\",\n    \"facilityName\": \"Test Station\",\n    \"systemName\": \"System1\",\n    \"unitName\": \"UTest-232\",\n    \"action\":\"NEW\",\n    \"tenantId\":2,\n     \"orgId\":2,\n    \"createdBy\":\"Kevin\",\n    \"plantData\":{\n      \n            \"plant_type\": \"Subcritical\",\n            \"plant_fuel_source\": \"Nuclear\",\n            \"plant_max_load\": \"640\",\n            \"plant_capacity_factor\": \"66\",\n            \"extraction_steam\": \"66\",\n            \"typical_mwhr_sale_price\": \"45.00\",\n            \"typical_heat_rate\": \"\",\n            \"typical_heat_rate_penalty\": \"\",\n            \"average_fuel_costs_per_unit\": \"\",\n            \"average_fuel_costs_per_mmbtus\": \"\",\n            \"expected_co2_emmissions\": \"\"\n      \n    },\n\n     \"unitData\":{\n      \n            \"design_duty\": \"222.55\",\n            \"design_steam_flow\": \"2233212.99\",\n            \"design_water_flow\": \"321212\",\n            \"design_cw_temp_in\": \"23.7\",\n            \"design_cw_pressure_drop\": \"12.88\",\n            \"design_cf\": \"90.00\",\n            \"number_of_pressure_sample_points\": \"22\",\n            \"no_of_shells\": \"22\",\n            \"tube_metallurgy\": \"Type 316/317 SS\",\n            \"no_of_passes\": \"2\",\n            \"no_of_total_tubes\": \"20540\",\n             \"no_of_unplugged_tubes\": \"20540\",\n            \"tube_od\": \"1.250\",\n            \"tube_wall_gauge\": \"22\",\n            \"tube_effective_length\": \"Type 316/317 SS\",\n            \"condenser_effective_area\": \"2332.44\"\n      \n    },\n      \"historianMap\":[{ \n            \"key\": \"gross_load\",\n            \"uom\": \"MW\",\n            \"tagName\": \"640\",\n            \"descriptor\": \"66\"},{ \n            \"key\": \"net_load\",\n            \"uom\": \"MW\",\n            \"tagName\": \"641\",\n            \"descriptor\": \"662\"}]\n\n    \n    \n}";
	}

	public static String getUpdCondenserTemplate() {
		return "{     \"facilityName\": \"Test Station\",\n    \"systemName\": \"System1\",\n    \"unitName\": \"UTest-232\",\n    \"action\":\"NEW\",\n    \"tenantId\":2,\n     \"orgId\":2,\n    \"createdBy\":\"Kevin\",\n    \"plantData\":{\n      \n            \"plant_type\": \"Subcritical\",\n            \"plant_fuel_source\": \"Nuclear\",\n            \"plant_max_load\": \"640\",\n            \"plant_capacity_factor\": \"66\",\n            \"extraction_steam\": \"66\",\n            \"typical_mwhr_sale_price\": \"45.00\",\n            \"typical_heat_rate\": \"\",\n            \"typical_heat_rate_penalty\": \"\",\n            \"average_fuel_costs_per_unit\": \"\",\n            \"average_fuel_costs_per_mmbtus\": \"\",\n            \"expected_co2_emmissions\": \"\"\n      \n    },\n\n     \"unitData\":{\n      \n            \"design_duty\": \"222.55\",\n            \"design_steam_flow\": \"2233212.99\",\n            \"design_water_flow\": \"321212\",\n            \"design_cw_temp_in\": \"23.7\",\n            \"design_cw_pressure_drop\": \"12.88\",\n            \"design_cf\": \"90.00\",\n            \"number_of_pressure_sample_points\": \"22\",\n            \"no_of_shells\": \"22\",\n            \"tube_metallurgy\": \"Type 316/317 SS\",\n            \"no_of_passes\": \"2\",\n            \"no_of_total_tubes\": \"20540\",\n             \"no_of_unplugged_tubes\": \"20540\",\n            \"tube_od\": \"1.250\",\n            \"tube_wall_gauge\": \"22\",\n            \"tube_effective_length\": \"Type 316/317 SS\",\n            \"condenser_effective_area\": \"2332.44\"\n      \n    },\n      \"historianMap\":[{ \n            \"key\": \"gross_load\",\n            \"uom\": \"MW\",\n            \"tagName\": \"640\",\n            \"descriptor\": \"66\"},{ \n            \"key\": \"net_load\",\n            \"uom\": \"MW\",\n            \"tagName\": \"641\",\n            \"descriptor\": \"662\"}]\n\n    \n    \n}";
	}

}
