package com.danaherdigital.che_hx.asset_management.dto;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class DesignCalRespDTO {

	@NotNull
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
	private Object NTUs;
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
	private Object cO2EmmissionExpected;


}
