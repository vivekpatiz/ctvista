package com.danaherdigital.che_hx.schedular.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Setter
public class Input {

	@JsonProperty("_id")
	public String id;
	@JsonProperty("percentageOfFullLoad")
	public Double percentOfFullLoad;
	@JsonProperty("averageCwInletWaterTemp")
	public Double averageCwInletWaterTemp;
	@JsonProperty("averageCwOutletWaterTemp")
	public Double averageCwOutletWaterTemp;
	@JsonProperty("cwInletTempCorrFactor")
	public Double cwInletTempCorrFactor;
	@JsonProperty("condenserSaturatedSteamTemp")
	public Double condenserSaturatedSteamTemp;
	@JsonProperty("measuredLmtd")
	public Double measuredLmtd;
	@JsonProperty("measuredItd")
	public Double measuredItd;
	@JsonProperty("measuredCwTtd")
	public Double measuredCwTtd;
	@JsonProperty("measuredCwTr")
	public Double measuredCwTr;
	@JsonProperty("specSheetDesignTr")
	public Double specSheetDesignTr;
	@JsonProperty("coolingTowerDeltaT")
	public Double coolingTowerDeltaT;
	@JsonProperty("extractionSteamFlow")
	public Double extractionSteamFlow;
	@JsonProperty("exhaustTurbineSteamFlow")
	public Double exhaustTurbineSteamFlow;
	@JsonProperty("calculatedDesignMoistureContentInSteam")
	public Double calculatedDesignMoistureContentInSteam;
	@JsonProperty("moistureContentInSteam")
	public Double moistureContentInSteam;
	@JsonProperty("effectiveTurbineSteamFlow")
	public Double effectiveTurbineSteamFlow;
	@JsonProperty("specSheetDesignSteamFlow")
	public Double designSteamFlow;
	@JsonProperty("steamLatentHeatEnthalpy")
	public Double steamLatentHeatEnthalpy;
	@JsonProperty("totalSteamEnthalpy")
	public Double totalSteamEnthalpy;
	@JsonProperty("condenserHeatDutyQ")
	public Double condenserHeatDuty;
	@JsonProperty("targetTrBasedOnDutyAndDesignFlow")
	public Double targetTrBasedOnDutyDesignFlow;
	@JsonProperty("trDeviation")
	public Double trDeviation;
	@JsonProperty("targetCwOutletTempOverall")
	public Double targetCwOutletTempOverall;
	@JsonProperty("udesignOverall")
	public Double uDesignOverall;
	@JsonProperty("uactualOverall")
	public Double uActualOverall;
	@JsonProperty("ntuOverall")
	public Double ntuOverall;
	@JsonProperty("targetSteamTempOverall")
	public Double targetSteamTempOverall;
	@JsonProperty("targetBpOverall")
	public Double targetBpOverall;
	@JsonProperty("steamEnthalpyOverall")
	public Double steamEnthalpyOverall;
	@JsonProperty("bpDeviationOverall")
	public Double bpDeviationOverall;
	@JsonProperty("heatRateDeviationOverall")
	public Double heatRateDeviationOverall;
	@JsonProperty("estExcessCo2EmissionsOverall")
	public Double estExcessCo2EmissionsOverall;
	@JsonProperty("steamEnthalpyDeviationOverall")
	public Double steamEnthalpyDeviationOverall;
	@JsonProperty("totalEnthalpyDeviationOverall")
	public Double totalEnthalpyDeviationOverall;
	@JsonProperty("productionDeviationOverall")
	public Double productionDeviationOverall;
	@JsonProperty("mwDollarLostPerDay")
	public Double dollarLostPerDay;
	@JsonProperty("estExcessFuelBurnedOverall")
	public Double estExcessFuelBurnedOverall;
	@JsonProperty("estExcessFuelDollarOverall")
	public Double estExcessFuelDollarPerDayOverall;
	@JsonProperty("calcCurrentCwFlow")
	public Double calcCurrentCwFlow;
	@JsonProperty("specSheetDesignCwFlow")
	public Double specSheetDesignCwFlow;
	@JsonProperty("calcCurrentCwVelocity")
	public Double calcCurrentCwVelocity;
	@JsonProperty("calcCwDeltaP")
	public Double calcCwDeltaP;
	@JsonProperty("specSheetDesignCwVelocity")
	public Double specSheetDesignCwVelocity;
	@JsonProperty("specSheetDesignCwDeltaP")
	public Double specSheetDesignCwDelta;
	@JsonProperty("calcUideal")
	public Double calUideal;
	@JsonProperty("calcUdesign")
	public Double calUdesign;
	@JsonProperty("calcUactual")
	public Double calUactual;
	@JsonProperty("specSheetDesignUactual")
	public Double specSheetDesignUactual;
	@JsonProperty("currentCf")
	public Double currentCFPercentage;
	@JsonProperty("specSheetDesignCf")
	public Double specSheetDesignCf;
	@JsonProperty("targetUactualCondenserOnly")
	public Double targetUactualCondenserOnly;
	@JsonProperty("ntuCondenserOnly")
	public Double ntuCondenserOnly;
	@JsonProperty("cwOutletTempCondenserOnly")
	public Double cwOutletTempCondenserOnly;
	@JsonProperty("targetSteamTempCondenserOnly")
	public Double targetSteamTempCondenserOnly;
	@JsonProperty("targetBpCondenserOnly")
	public Double targetBPCondenserOnly;
	@JsonProperty("bpDeviationCondenserOnly")
	public Double bpDeviationCondenserOnly;
	@JsonProperty("targetTtdCondenserOnly")
	public Double targetTTDCondenserOnly;
	@JsonProperty("ttdDeviation")
	public Double ttdDeviation;
	@JsonProperty("steamEnthalpyCondenserOnly")
	public Double steamEnthalpyCondenserOnly;
	@JsonProperty("enthalpyDeviationCondenserOnly")
	public Double enthalpyDeviationCondenserOnly;
	@JsonProperty("totalEnthalpyDeviationCondenserOnly")
	public Double totalEnthalpyDeviationCondenserOnly;
	@JsonProperty("productionDeviationCondenserOnly")
	public Double productionDeviationCondenserOnly;
	@JsonProperty("heatRateDeviationCondenserOnly")
	public Double heatRateDeviationCondenserOnly;
	@JsonProperty("co2EmissionsDeviationCondenserOnly")
	public Double cO2EmissionsDeviationCondenserOnly;
	@JsonProperty("estExcessFuelBurnedCondenserOnly")
	public Double estExcessFuelBurnedCondenserOnly;
	@JsonProperty("atmPressurePsia")
	public Double atmPressurePsia;
	@JsonProperty("atmPressureKpa")
	public Double atmPressureKpa;
	@JsonProperty("partialPressureAtT")
	public Double partialPressureAtT;
	@JsonProperty("tDewC")
	public Double t;
	@JsonProperty("ysNotDew")
	public Double ys1;
	@JsonProperty("ysDew")
	public Double ys;
	@JsonProperty("massAbsKgAir")
	public Double massAbskgH2OPerKgAir;
	@JsonProperty("airEnthalpy")
	public Double airEnthalpy;
	@JsonProperty("vapourPressure")
	public Double vaporPressure;
	@JsonProperty("calcWetBulb")
	public Double calcWetBulb;
	@JsonProperty("coolingTowerApproach")
	public Double coolingTowerApproach;
@JsonProperty("timeStamp")
public Long timeStamp;





}