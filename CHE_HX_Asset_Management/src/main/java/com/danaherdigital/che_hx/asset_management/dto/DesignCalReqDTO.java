package com.danaherdigital.che_hx.asset_management.dto;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DesignCalReqDTO {
	@JsonProperty("no_of_passes")
	private Object numberOfPasses;
	@JsonProperty("condenser_effective_area")
	private Object condenserEffectiveArea;
	@JsonProperty("design_duty")
	private Object designDuty;
	@JsonProperty("design_steam_flow")
	private Object designSteamFlow;
	@JsonProperty("design_water_flow")
	private Object designWaterFlow;
	@JsonProperty("design_cw_temp_in")
	private Object designInletCoolingWaterTemp;
	@JsonProperty("design_cw_pressure_drop")
	private Object designCWPressureDrop;
	@JsonProperty("design_cf")
	private Object designCF;
	@JsonProperty("no_of_total_tubes")
	private Object numberOfTubesTotal;
	@JsonProperty("no_of_unplugged_tubes")
	private Object numberOfUnpluggedTubes;
	@JsonProperty("tube_od")
	private Object tubeOD;
	@JsonProperty("tube_wall_gauge")
	private Object tubeWallGauge;
	@JsonProperty("tube_metallurgy")
	private Object tubeMetallurgy;
	@JsonProperty("tube_effective_length")
	private Object tubeEffectiveLength;
	@JsonProperty("plant_type")
	private Object plantType;
	@JsonProperty("plant_fuel_source")
	private Object plantFuelSource;
	@JsonProperty("plant_max_load")
	private Object plantMaxLoad;
	@JsonProperty("plant_capacity_factor")
	private Object plantCapacityFactor;
	@JsonProperty("extraction_steam")
	private Object extractionSteam;
	@JsonProperty("typical_mwhr_sale_price")
	private Object typical$$MWhrSalePrice;
//	@JsonProperty("calculated_velocity")
	@JsonIgnore
	private Object calculatedVelocity;
	@JsonIgnore
	@JsonProperty("calculated_design_steam_temp")
	private Object calculatedDesignSteamTemp;

}
