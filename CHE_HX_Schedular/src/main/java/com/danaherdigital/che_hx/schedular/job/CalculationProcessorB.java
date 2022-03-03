package com.danaherdigital.che_hx.schedular.job;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.danaherdigital.che_hx.schedular.dto.ApiResponseDTO;
import com.danaherdigital.che_hx.schedular.dto.AssetDataParamDTO;
import com.danaherdigital.che_hx.schedular.dto.AssetDataUOMDTO;
import com.danaherdigital.che_hx.schedular.dto.CalcEngineDTO;
import com.danaherdigital.che_hx.schedular.dto.UOMReqDTO;
import com.danaherdigital.che_hx.schedular.dto.UOMReqData;
import com.danaherdigital.che_hx.schedular.dto.UOMRespDTO;
import com.danaherdigital.che_hx.schedular.dto.UOMResult;
import com.danaherdigital.che_hx.schedular.model.Asset;
import com.danaherdigital.che_hx.schedular.model.AssetParam;
import com.danaherdigital.che_hx.schedular.model.CalcJob;
import com.danaherdigital.che_hx.schedular.model.CalculationConfigMaster;
import com.danaherdigital.che_hx.schedular.model.Input;
import com.danaherdigital.che_hx.schedular.model.UnitOfMeasure;
import com.danaherdigital.che_hx.schedular.repo.AssetDataRespository;
import com.danaherdigital.che_hx.schedular.repo.AssetParamRepository;
import com.danaherdigital.che_hx.schedular.repo.AssetRepository;
import com.danaherdigital.che_hx.schedular.repo.CalculationConfigMasterRepository;
import com.danaherdigital.che_hx.schedular.repo.UnitOfMeasureRepository;
import com.danaherdigital.che_hx.schedular.service.ICalculationService;
import com.danaherdigital.che_hx.schedular.service.IUOMService;
import com.danaherdigital.che_hx.schedular.utils.ApplicationConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CalculationProcessorB implements ItemProcessor<CalcJob, ApiResponseDTO> {
	@Autowired
	private AssetDataRespository assetDataRepository;

	@Autowired
	private AssetParamRepository assetParamRepository;
	@Autowired
	private AssetRepository assetRepository;
	@Autowired
	CalculationConfigMasterRepository calculationConfigMasterRepository;
	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;
	@Autowired
	ICalculationService calculationService;
	@Autowired
	IUOMService uOMService;
	private Map<Long, String> uomMap = null;

	@PostConstruct
	public void init() {

		uomMap = createUomMap();
	}

	@Override
	public ApiResponseDTO process(CalcJob calcJob) throws Exception {
		ApiResponseDTO respDTO = new ApiResponseDTO();
		CalculationConfigMaster cm = null;

		try {
			if (!Objects.isNull(calcJob)) {

				List<Map<String, Object>> calReq = new ArrayList<>();
				CalcEngineDTO calDTO = new CalcEngineDTO();

				respDTO.setJobId(calcJob.getId());
				respDTO.setId(calcJob.getInflowId());
				respDTO.setTenantId(calcJob.getTenantId());
				// =-----tentative---time series service

				log.info("min timestamp:{} max timstamp:{}",calcJob.getFromDate(),calcJob.getToDate());
				List<Long> sliceList = assetDataRepository.findAllByDataTimestampBetween(calcJob.getFromDate(),
						calcJob.getToDate(), calcJob.getAssetName(), calcJob.getTenantId());
				log.info("total timstamps1:{}",sliceList.size());

				if (!CollectionUtils.isEmpty(sliceList)) {

					cm = calculationConfigMasterRepository.findByAssetTypeId(calcJob.getAssetName());
					if (!(Objects.isNull(cm) || Objects.isNull(cm.getInputParams()))) {

						List<Map<String, String>> inPutParams = new ObjectMapper().readValue(cm.getInputParams(),
								new TypeReference<List<Map<String, String>>>() {
								});

						respDTO = composeInputForCalc(sliceList, calDTO, calReq, respDTO, calcJob, inPutParams, cm);
					}
				}
			}
		} catch (Exception e) {

			respDTO.setError(e.getMessage());
			log.error("error  in CalculationProcessorB:{}", e);

		}

		return respDTO;
	}

	private ApiResponseDTO composeInputForCalc(List<Long> sliceList, CalcEngineDTO calDTO,
			List<Map<String, Object>> calReq, ApiResponseDTO respDTO, CalcJob calcJob,
			List<Map<String, String>> inPutParams, CalculationConfigMaster cm) throws JsonProcessingException {
		int count = 0;

		for (Long aData : sliceList) {

			List<AssetDataParamDTO> assetData = assetDataRepository.findAllByAssetNameTimestamp(calcJob.getAssetName(),
					aData, calcJob.getTenantId());
			if (!CollectionUtils.isEmpty(assetData)) {

				Optional<Asset> asset = assetRepository.findById(calcJob.getAssetName());

				if (asset.isPresent()) {
					List<AssetParam> designDataList = assetParamRepository
							.findByUnit(asset.get().getParentAssetId().getId());

					if (!CollectionUtils.isEmpty(designDataList)) {
						log.info("calculation data set composing :{}", count);
						count++;

						Map<String, Object> req = createInput(assetData, designDataList, inPutParams,
								aData);
						if (!Objects.isNull(req) && !CollectionUtils.isEmpty(req)) {

							calReq.add(req);
						}

					}
				}
			}

		}

		if (!CollectionUtils.isEmpty(calReq)) {
			calDTO.setInput(calReq);

			if (!Objects.isNull(cm)) {
				calDTO.setEngine(cm.getEngine());
				calDTO.setFunction(cm.getType());
			}

			List<Input> output = calculationService.getCalculatedData(calDTO);
			if (!CollectionUtils.isEmpty(output)) {
				respDTO.setResult(output);
				respDTO.setAssetId(calcJob.getAssetName());
			}

		}
		return respDTO;
	}

	private Map<String, Object> createInput(List<AssetDataParamDTO> assetData, List<AssetParam> designData,
			List<Map<String, String>> inPutParams, Long timeStamp) {
		Map<String, Object> rawInput = new HashMap<>();

		if (!(CollectionUtils.isEmpty(assetData) && CollectionUtils.isEmpty(designData))) {

			if (!Objects.isNull(uomMap) || !CollectionUtils.isEmpty(uomMap)) {

				List<AssetDataUOMDTO> uomConvertedList = checkExpectedUom(assetData, uomMap);

				for (AssetDataUOMDTO assetDt : uomConvertedList) {

					for (Map<String, String> map : inPutParams) {

						for (Map.Entry<String, String> entry : map.entrySet()) {

							if (Objects.equals(entry.getValue(), assetDt.getName())) {

								rawInput.put(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, entry.getValue()),
										assetDt.getFloatValues());

							}

						}
					}

				}

			}

			rawInput = createDesignInput(designData, rawInput, inPutParams);

			if (!Objects.isNull(rawInput) || !CollectionUtils.isEmpty(rawInput)) {
				rawInput.put("_id", UUID.randomUUID().toString());
				rawInput.put("timeStamp", timeStamp);
			}

		}
		return rawInput;

	}

	private Map<String, Object> createDesignInput(List<AssetParam> designData, Map<String, Object> rawInput,
			List<Map<String, String>> inPutParams) {
		Map<String, Object> designInput = new HashMap<>();
		for (AssetParam assetParam : designData) {
			String paramName = assetParam.getName().trim();
			Object paramVal = null;
			if (!(Objects.isNull(assetParam.getValue()) || assetParam.getValue().isEmpty())) {
				if (assetParam.getValue() instanceof String) {
					paramVal = assetParam.getValue();
				} else {
					Double paramvalue = Double.parseDouble(assetParam.getValue().replaceAll("\\,", ""));
					paramVal = paramvalue;
				}

			}

			for (Map<String, String> map : inPutParams) {

				for (Map.Entry<String, String> entry : map.entrySet()) {

					if (Objects.equals(entry.getValue(), paramName)) {
						designInput.put(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, entry.getValue()),
								paramVal);

					}

				}
			}

		}

		if (!(Objects.isNull(designInput) || CollectionUtils.isEmpty(designData))) {
			rawInput.putAll(designInput);

		}
		return rawInput;

	}

	private Map<Long, String> createUomMap() {
		Map<Long, String> uomMapData = new HashMap<>();
		List<UnitOfMeasure> uomList = unitOfMeasureRepository.findAllUom();
		if (!CollectionUtils.isEmpty(uomList)) {

			for (UnitOfMeasure uom : uomList) {

				uomMapData.put(uom.getId(), uom.getDisplayName());
			}

		}

		return uomMapData;
	}

	private List<AssetDataUOMDTO> checkExpectedUom(List<AssetDataParamDTO> assetDataList, Map<Long, String> uomMap) {

		List<UOMReqData> uomReqList = new ArrayList<>();
		UOMReqDTO uomReqDTO = new UOMReqDTO();
		List<AssetDataUOMDTO> newList = composeAssetData(assetDataList);
		List<UOMResult> uomResList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(newList)) {
			for (AssetDataUOMDTO assetDt : newList) {

				String uomName = uomMap.get(assetDt.getUom()).trim();

				if (StringUtils.equalsIgnoreCase(assetDt.getName(), "gross_load")
						&& !StringUtils.equalsIgnoreCase(uomName, ApplicationConstants.MW)) {

					uomReqList.add(createUOMReqData(uomName, ApplicationConstants.MW, assetDt));

				} else if (StringUtils.equalsIgnoreCase(assetDt.getName(), "ambient_temp")
						&& !StringUtils.equalsIgnoreCase(uomName, ApplicationConstants.FARENHIET)) {

					uomReqList.add(createUOMReqData(uomName, ApplicationConstants.FARENHIET, assetDt));

				} else if (StringUtils.equalsIgnoreCase(assetDt.getName(), "humidity")
						&& !StringUtils.equalsIgnoreCase(uomName, ApplicationConstants.PERCENTAGE)) {

					uomReqList.add(createUOMReqData(uomName, ApplicationConstants.PERCENTAGE, assetDt));

				} else if ((StringUtils.equalsIgnoreCase(assetDt.getName(), "barometric_pressure")
						|| StringUtils.contains(assetDt.getName(), "condenser_back_pressure_"))
						&& (!(StringUtils.equalsIgnoreCase(uomName, ApplicationConstants.INHGA)
								|| StringUtils.equalsIgnoreCase(uomName, ApplicationConstants.INHG)))) {
					if (StringUtils.equalsIgnoreCase(uomName, ApplicationConstants.INHGV)) {
						uomResList.add(createUomResultDOForINHGV(uomName, ApplicationConstants.INHGA, assetDt.getId(),
								assetDt.getFloatValues()));
					} else {
						uomReqList.add(createUOMReqData(uomName, ApplicationConstants.INHGA, assetDt));
					}

				} else if (StringUtils.contains(assetDt.getName(), "lp_exhaust_steam_temp_")
						&& !StringUtils.equalsIgnoreCase(uomName, ApplicationConstants.FARENHIET)) {

					uomReqList.add(createUOMReqData(uomName, ApplicationConstants.FARENHIET, assetDt));

				} else if (StringUtils.contains(assetDt.getName(), "hotwell_temp_")
						&& (!StringUtils.equalsIgnoreCase(uomName, ApplicationConstants.FARENHIET))) {

					uomReqList.add(createUOMReqData(uomName, ApplicationConstants.FARENHIET, assetDt));

				} else if (StringUtils.contains(assetDt.getName(), "boiler_steam_flow_")
						&& !(StringUtils.equalsIgnoreCase(uomName, ApplicationConstants.KPPH))) {

					uomReqList.add(createUOMReqData(uomName, ApplicationConstants.KPPH, assetDt));

				} else if (StringUtils.contains(assetDt.getName(), "attemperatrion_steam_flow_")
						&& !(StringUtils.equalsIgnoreCase(uomName, ApplicationConstants.KPPH))) {

					uomReqList.add(createUOMReqData(uomName, ApplicationConstants.KPPH, assetDt));

				} else if (StringUtils.contains(assetDt.getName(), "cogen_steam_flow_")
						&& !(StringUtils.equalsIgnoreCase(uomName, ApplicationConstants.KPPH))) {

					uomReqList.add(createUOMReqData(uomName, ApplicationConstants.KPPH, assetDt));

				} else if (StringUtils.contains(assetDt.getName(), "condenser_cw_in_temps_")
						&& !StringUtils.equalsIgnoreCase(uomName, ApplicationConstants.FARENHIET)) {

					uomReqList.add(createUOMReqData(uomName, ApplicationConstants.FARENHIET, assetDt));

				} else if (StringUtils.contains(assetDt.getName(), "condenser_cw_out_temps_")
						&& !StringUtils.equalsIgnoreCase(uomName, ApplicationConstants.FARENHIET)) {

					uomReqList.add(createUOMReqData(uomName, ApplicationConstants.FARENHIET, assetDt));

				} else if (StringUtils.equalsIgnoreCase(assetDt.getName(), "cooling_tower_return_temp")
						&& !StringUtils.equalsIgnoreCase(uomName, ApplicationConstants.FARENHIET)) {

					uomReqList.add(createUOMReqData(uomName, ApplicationConstants.FARENHIET, assetDt));

				} else if (StringUtils.equalsIgnoreCase(assetDt.getName(), "cooling_tower_supply_temp")
						&& !StringUtils.equalsIgnoreCase(uomName, ApplicationConstants.FARENHIET)) {

					uomReqList.add(createUOMReqData(uomName, ApplicationConstants.FARENHIET, assetDt));

				} else if (StringUtils.contains(assetDt.getName(), "condenser_cw_inlet_pressure_")
						&& !StringUtils.equalsIgnoreCase(uomName, ApplicationConstants.PSI)) {

					uomReqList.add(createUOMReqData(uomName, ApplicationConstants.PSI, assetDt));

				} else if (StringUtils.contains(assetDt.getName(), "condenser_cw_outlet_pressure_")
						&& !StringUtils.equalsIgnoreCase(uomName, ApplicationConstants.PSI)) {

					uomReqList.add(createUOMReqData(uomName, ApplicationConstants.PSI, assetDt));

				} else if (StringUtils.contains(assetDt.getName(), "condenser_cw_delta_pressure_")
						&& !StringUtils.equalsIgnoreCase(uomName, ApplicationConstants.PSI)) {

					uomReqList.add(createUOMReqData(uomName, ApplicationConstants.PSI, assetDt));

				}

			}
		}

		if (!CollectionUtils.isEmpty(uomReqList)) {
			uomReqDTO.setUomReqList(uomReqList);

			UOMRespDTO resp = uOMService.getUOMConversionData(uomReqDTO);
			if (!CollectionUtils.isEmpty(uomResList)) {
				resp.getList().addAll(uomResList);
			}
			return changeAssetDataList(resp.getList(), newList);

		} else {
			if (!CollectionUtils.isEmpty(uomResList)) {
				newList = changeAssetDataList(uomResList, newList);
			}
			return newList;
		}
	}

	private UOMResult createUomResultDOForINHGV(String uomName, String inhga, String id, Object floatValues) {
		UOMResult res = new UOMResult();

		if (!(Objects.isNull(floatValues.toString()) && Objects.equals(floatValues, 0))) {
			res.setConvertedValue(29.92 - Double.parseDouble(floatValues.toString()));
			UOMReqData req = new UOMReqData();
			req.setFromUom(uomName);
			req.setToUom(inhga);
			req.setId(id);
			req.setValue(Double.parseDouble(floatValues.toString()));
			res.setInput(req);

		}
		return res;
	}

	private List<AssetDataUOMDTO> composeAssetData(List<AssetDataParamDTO> assetDataList) {

		List<AssetDataUOMDTO> aduList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(assetDataList)) {
			for (AssetDataParamDTO adp : assetDataList) {
				AssetDataUOMDTO adu = new AssetDataUOMDTO();
				adu.setDataTimeStamp(adp.getDataTimeStamp());
				adu.setFloatValues(adp.getFloatValues());
				adu.setId(adp.getId());
				adu.setName(adp.getName());
				adu.setUom(adp.getUom());
				aduList.add(adu);

			}

			if (!CollectionUtils.isEmpty(aduList)) {
				return aduList;
			}

		}

		return Collections.emptyList();
	}

	private List<AssetDataUOMDTO> changeAssetDataList(List<UOMResult> uomResList, List<AssetDataUOMDTO> assetDataList) {

		List<AssetDataUOMDTO> adpList = new ArrayList<>();
		for (AssetDataUOMDTO adp : assetDataList) {

			adpList.add(changeUomForAssetList(uomResList, adp));
		}

		return adpList;
	}

	private AssetDataUOMDTO changeUomForAssetList(List<UOMResult> list, AssetDataUOMDTO adp) {

		if (!(CollectionUtils.isEmpty(list) && Objects.isNull(adp))) {

			for (UOMResult res : list) {

				if (res.getInput().getId().equals(adp.getId())) {

					adp.setFloatValues(res.getConvertedValue());

				}
			}

		}
		return adp;

	}

	private UOMReqData createUOMReqData(String fromUom, String toUom, AssetDataUOMDTO assetDtUom)

	{

		try {
			UOMReqData req = new UOMReqData();
			req.setFromUom(fromUom);
			req.setToUom(toUom);
			req.setValue(Double.parseDouble(assetDtUom.getFloatValues().toString()));
			req.setId(assetDtUom.getId());
			return req;
		} catch (Exception e) {
			log.error("error in CalculationProcessorB.createUOMReqData");
			return null;
		}
	}

}
