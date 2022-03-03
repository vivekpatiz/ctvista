package com.danaherdigital.che_hx.calcengine.dto;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"condensing_surface_area",
"surface_area_diff_from_design",
"steam_flow_based_on_actual_latent_heat_and_duty",
"calculated_moisture",
"calculated_desing_steam_latent_heat",
"calculated_design_bp",
"calculated_design_steam_temp",
"design_cw_temp_out",
"design_tr",
"design_temp_corr_factor",
"calculated_velocity",
"design_uideal",
"design_udesign",
"design_uactual",
"tube_wall_thickness",
"tube_metal_and_wall_corr_factor",
"ntus",
"tube_id_flow_areatube",
"total_tube_id_flow_area",
"tube_od_surface_areatube",
"typical_heat_rate",
"typical_heat_rate_penalty",
"average_fuel_costs_per_unit",
"average_fuel_costs_per_mmbtus",
"expected_co2_emmissions"
})
@Getter
@Setter
public class DesignCalRespDTO {

	//@NotNull
	@JsonProperty("condensing_surface_area")
	private Object condensingSurfaceArea;
	@JsonProperty("surface_area_diff_from_design")
	private Object surfaceAreaPercentageDifferenceFromDesign;
	@JsonProperty("steam_flow_based_on_actual_latent_heat_and_duty")
	private Object steamFlowBasedOnActualLatentHeatAndDuty;
	@JsonProperty("calculated_moisture")
	private Object calculatedPercentageMoisture;
	@JsonProperty("calculated_desing_steam_latent_heat")
	private Object calculatedDesignSteamLatentHeat;
	@JsonProperty("calculated_design_bp")
	private Object calculatedDesignBP;
	@JsonProperty("calculated_design_steam_temp")
	private Object calculatedDesignSteamTemp;
	@JsonProperty("design_cw_temp_out")
	private Object designCWTempOut;
	@JsonProperty("design_tr")
	private Object designTR;
	@JsonProperty("design_temp_corr_factor")
	private Object designTempCorrFactor;
	@JsonProperty("calculated_velocity")
	private Object calculatedVelocity;
	@JsonProperty("design_uideal")
	private Object designUideal;
	@JsonProperty("design_udesign")
	private Object designUdesign;
	@JsonProperty("design_uactual")
	private Object designUactual;
	@JsonProperty("tube_wall_thickness")
	private Object tubeWallThickness;
	@JsonProperty("tube_metal_and_wall_corr_factor")
	private Object tubeMetalAndWallCorrFactor;
	@JsonProperty("ntus")
	private Object nTUs;
	@JsonProperty("tube_id_flow_areatube")
	private Object tubeIDFlowAreaPerTube;
	@JsonProperty("total_tube_id_flow_area")
	private Object totalTubeIDFlowArea;
	@JsonProperty("tube_od_surface_areatube")
	private Object tubeODSurfaceAreaPerTube;
	@JsonProperty("typical_heat_rate")
	private Object typicalHeatRate;
	@JsonProperty("typical_heat_rate_penalty")
	private Object typicalHeatRatePenalty;
	@JsonProperty("average_fuel_costs_per_unit")
	private Object averageFuelCostsPerUnit;
	@JsonProperty("average_fuel_costs_per_mmbtus")
	private Object averageFuelCostsPerMMbtus;
	@JsonProperty("expected_co2_emmissions")
	private Object expectedCo2Emmissions;


}
