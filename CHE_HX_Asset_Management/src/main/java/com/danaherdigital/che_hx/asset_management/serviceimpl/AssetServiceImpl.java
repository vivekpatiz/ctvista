package com.danaherdigital.che_hx.asset_management.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.danaherdigital.che_hx.asset_management.dto.AssetDTO;
import com.danaherdigital.che_hx.asset_management.dto.AssetDTOLoadInt;
import com.danaherdigital.che_hx.asset_management.dto.AssetFacilityDTO;
import com.danaherdigital.che_hx.asset_management.dto.AssetIngestionLogDTO;
import com.danaherdigital.che_hx.asset_management.dto.ChartsConfigDTO;
import com.danaherdigital.che_hx.asset_management.dto.CondenserDTO;
import com.danaherdigital.che_hx.asset_management.dto.DesignCalReqDTO;
import com.danaherdigital.che_hx.asset_management.dto.DesignCalRespDTO;
import com.danaherdigital.che_hx.asset_management.dto.HistorianDataDTO;
import com.danaherdigital.che_hx.asset_management.dto.PlantDataDTO;
import com.danaherdigital.che_hx.asset_management.dto.UOMdataDTO;
import com.danaherdigital.che_hx.asset_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.asset_management.exceptionhandler.ResourceNotFoundException;
import com.danaherdigital.che_hx.asset_management.model.Asset;
import com.danaherdigital.che_hx.asset_management.model.AssetParam;
import com.danaherdigital.che_hx.asset_management.model.AssetType;
import com.danaherdigital.che_hx.asset_management.model.AssetTypeParam;
import com.danaherdigital.che_hx.asset_management.model.Tenant;
import com.danaherdigital.che_hx.asset_management.respository.AssetDataLogRepository;
import com.danaherdigital.che_hx.asset_management.respository.AssetParamRepository;
import com.danaherdigital.che_hx.asset_management.respository.AssetRepository;
import com.danaherdigital.che_hx.asset_management.respository.AssetTypeParamRepository;
import com.danaherdigital.che_hx.asset_management.respository.AssetTypeRepository;
import com.danaherdigital.che_hx.asset_management.respository.TenantRepository;
import com.danaherdigital.che_hx.asset_management.service.IAssetService;
import com.danaherdigital.che_hx.asset_management.utils.ApplicationConstants;
import com.danaherdigital.che_hx.asset_management.utils.CommonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@EnableAsync
public class AssetServiceImpl implements IAssetService {

	@Autowired
	private AssetRepository assetRepository;

	@Autowired
	private TenantRepository tenantRepository;

	@Autowired
	private AssetTypeRepository assetTypeRepository;

	@Autowired
	private AssetDataLogRepository assetDataLogRepository;

	@Autowired
	private AssetParamRepository assetParamRepository;

	@Autowired
	private AssetTypeParamRepository assetTypeParamRepository;

	@Value("${timeseries.minload.url}")
	private String minLoadUrl;

	@Value("${uom.service.url}")
	String uomServiceUrl;

	@Value("${calcengine.designcalc.url}")
	private String designCalcURL;

	@Autowired
	RestTemplate restTemplate;

	
	/** The Constant LOGGER. */
	static final Logger LOGGER = LoggerFactory.getLogger(AssetServiceImpl.class);

	@Override
	@Transactional(rollbackFor = ChemtreatException.class)
	public String saveAsset(String asset) throws ChemtreatException {

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "saveAsset");
		LOGGER.info("Saving Asset");
		List<AssetTypeParam> filterAssetTypeParamFac = null;
		JSONObject designCalcReq = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		try {

			JsonNode node = mapper.readTree(asset);

			// check for tenant else create new tenant.
			Integer orgId = mapper.convertValue(node.get("orgId"), Integer.class);
			Tenant tenant = new Tenant();
			Optional<Tenant> tenantOpt = tenantRepository.findByOrganiztionId(orgId);

			if (!tenantOpt.isPresent()) {
				tenant.setAccountId(orgId);
				tenant.setId(orgId);
				tenant = tenantRepository.save(tenant);
			} else {
				tenant = tenantOpt.get();
			}
			List<AssetTypeParam> assetTypeParamList = assetTypeParamRepository
					.getAllAssetTypeParamOnName(ApplicationConstants.ASSET_FACILITY);

			// check for Facility else create new Facility.
			String facilityName = mapper.convertValue(node.get(ApplicationConstants.FACILITY_NAME), String.class);
			Asset facilityAsset = null;
			Optional<Asset> facilityAssetOpt = assetRepository.getAssetByTenantAndAssetNameType(tenant.getId(),
					ApplicationConstants.ASSET_FACILITY, facilityName);
			String createdBy = mapper.convertValue(node.get("createdBy"), String.class);
			filterAssetTypeParamFac = assetTypeParamList.stream().filter(AssetTypeParam::getComputed)
					.collect(Collectors.toList());
			if (!facilityAssetOpt.isPresent()) {
				facilityAsset = saveAssetTemplate(ApplicationConstants.ASSET_FACILITY, facilityName, tenant, createdBy,
						null);

				// add attributes for computation

				if (!filterAssetTypeParamFac.isEmpty()) {
					for (AssetTypeParam assetTypeParam : filterAssetTypeParamFac) {
						saveAssetParam(createdBy, createdBy, true, assetTypeParam.getDataType(),
								assetTypeParam.getName(), assetTypeParam.getDisplayName(), assetTypeParam.getValue(),
								facilityAsset, "", "", assetTypeParam.getUnitOfMeasureId(),
								ApplicationConstants.ASSET_DESIGN);
					}
				}

			} else {
				facilityAsset = facilityAssetOpt.get();
			}

			List<AssetTypeParam> assetTypeUnitParamList = assetTypeParamRepository
					.getAllAssetTypeParamOnName(ApplicationConstants.ASSET_Unit);
			List<AssetTypeParam> filterAssetTypeParamUnit = null;
			// check for Unit asset else create new Unit.
			String unitName = mapper.convertValue(node.get(ApplicationConstants.SYSTEM_NAME), String.class);
			Asset unitAsset = null;
			Optional<Asset> unitAssetOpt = assetRepository.getAssetByTenantAndAssetNameTypeUnit(tenant.getId(),
					ApplicationConstants.ASSET_Unit, unitName, facilityAsset.getId());
			filterAssetTypeParamUnit = assetTypeUnitParamList.stream().filter(AssetTypeParam::getComputed)
					.collect(Collectors.toList());
			if (!unitAssetOpt.isPresent()) {
				unitAsset = saveAssetTemplate(ApplicationConstants.ASSET_Unit, unitName, tenant, createdBy,
						facilityAsset);

				// add attributes for computation unit

				if (!filterAssetTypeParamUnit.isEmpty()) {
					for (AssetTypeParam assetTypeParam : filterAssetTypeParamUnit) {
						saveAssetParam(createdBy, createdBy, true, assetTypeParam.getDataType(),
								assetTypeParam.getName(), assetTypeParam.getDisplayName(), assetTypeParam.getValue(),
								unitAsset, "", "", assetTypeParam.getUnitOfMeasureId(),
								ApplicationConstants.ASSET_DESIGN);
					}
				}

			} else {
				unitAsset = unitAssetOpt.get();
			}

			// check for Condenser asset else create new Unit.
			String condenserName = mapper.convertValue(node.get(ApplicationConstants.CONDENSER_NAME), String.class);
			Asset condenserAsset = null;
			Optional<Asset> condenserAssetOpt = assetRepository.getAssetByTenantAndAssetNameType(tenant.getId(),
					ApplicationConstants.ASSET_TYPE_CONDENSER, condenserName);
			if (!condenserAssetOpt.isPresent()) {
				condenserAsset = saveAssetTemplate(ApplicationConstants.ASSET_TYPE_CONDENSER, condenserName, tenant,
						createdBy, unitAsset);
			} else {
				LOGGER.error(ApplicationConstants.CONDENSOR_EXISTS);
				throw new ChemtreatException(ApplicationConstants.CONDENSOR_EXISTS);
			}

			// add plant attributes
			JsonNode plantNode = mapper.readTree(node.get(ApplicationConstants.PLANT_DATA).toString());

			final Asset unitAssetF = unitAsset;
			final Asset faciltyAssetF = facilityAsset;
			plantNode.fields().forEachRemaining(entry -> {
				AssetTypeParam assetTypeParam = assetTypeParamList.stream()
						.filter(assetParamF -> assetParamF.getName().equals(entry.getKey())).findFirst().orElse(null);

				if (assetTypeParam != null
						&& !uniqueAsset(assetTypeParam.getName(), faciltyAssetF.getId()).isPresent()) {
					saveAssetParam(createdBy, createdBy, false, assetTypeParam.getDataType(), assetTypeParam.getName(),
							assetTypeParam.getDisplayName(),
							mapper.convertValue(plantNode.get(assetTypeParam.getName()), String.class), faciltyAssetF,
							"", "", assetTypeParam.getUnitOfMeasureId(), ApplicationConstants.ASSET_DESIGN);
					designCalcReq.put(assetTypeParam.getName(),
							mapper.convertValue(plantNode.get(assetTypeParam.getName()), String.class));

				} else if (assetTypeParam != null
						&& uniqueAsset(assetTypeParam.getName(), faciltyAssetF.getId()).isPresent()) {
					Optional<AssetParam> assetOpt = uniqueAsset(assetTypeParam.getName(), faciltyAssetF.getId());
					AssetParam assetParam = assetOpt.get();
					assetParam.setValue(mapper.convertValue(plantNode.get(assetTypeParam.getName()), String.class));
					designCalcReq.put(assetTypeParam.getName(),
							mapper.convertValue(plantNode.get(assetTypeParam.getName()), String.class));
					assetParamRepository.save(assetParam);
				}

			});

			// add Unit attributes
			JsonNode unitNode = mapper.readTree(node.get(ApplicationConstants.UNIT_DATA).toString());

			final Asset condenserAssetF = condenserAsset;
			unitNode.fields().forEachRemaining(entry -> {
				AssetTypeParam assetTypeParam = assetTypeUnitParamList.stream()
						.filter(assetParamF -> assetParamF.getName().equals(entry.getKey())).findFirst().orElse(null);

				if (assetTypeParam != null && !uniqueAsset(assetTypeParam.getName(), unitAssetF.getId()).isPresent()) {
					saveAssetParam(createdBy, createdBy, false, assetTypeParam.getDataType(), assetTypeParam.getName(),
							assetTypeParam.getDisplayName(),
							mapper.convertValue(unitNode.get(assetTypeParam.getName()), String.class), unitAssetF, null,
							null, assetTypeParam.getUnitOfMeasureId(), ApplicationConstants.ASSET_DESIGN);
					designCalcReq.put(assetTypeParam.getName(),
							mapper.convertValue(unitNode.get(assetTypeParam.getName()), String.class));
				} else if (assetTypeParam != null
						&& uniqueAsset(assetTypeParam.getName(), unitAssetF.getId()).isPresent()) {
					Optional<AssetParam> assetOpt = uniqueAsset(assetTypeParam.getName(), unitAssetF.getId());
					AssetParam assetParam = assetOpt.get();
					assetParam.setValue(mapper.convertValue(unitNode.get(assetTypeParam.getName()), String.class));
					designCalcReq.put(assetTypeParam.getName(),
							mapper.convertValue(unitNode.get(assetTypeParam.getName()), String.class));
					assetParamRepository.save(assetParam);
				}

			});
			// add condenser attributes
			JsonNode historianNode = mapper.readTree(node.get(ApplicationConstants.HISTORIAN_MAP).toString());
			List<AssetTypeParam> assetTypeParamUnitList = assetTypeParamRepository
					.getAllAssetTypeParamOnName(ApplicationConstants.ASSET_TYPE_CONDENSER);
			List<AssetTypeParam> filterAssetTypeParam = assetTypeParamUnitList.stream()
					.filter(AssetTypeParam::getComputed).collect(Collectors.toList());
			if (!filterAssetTypeParam.isEmpty()) {
				for (AssetTypeParam assetTypeParam : filterAssetTypeParam) {
					saveAssetParam(createdBy, createdBy, true, assetTypeParam.getDataType(), assetTypeParam.getName(),
							assetTypeParam.getDisplayName(), assetTypeParam.getValue(), condenserAssetF, "", "",
							assetTypeParam.getUnitOfMeasureId(), ApplicationConstants.ASSET_RAW_HEADER);
				}
			}

			for (int i = 0; i < historianNode.size(); i++) {
				JsonNode historianInnerNode = historianNode.get(i);
				AssetTypeParam assetTypeParam = assetTypeParamUnitList.stream()
						.filter(assetParamF -> assetParamF.getName()
								.equalsIgnoreCase(mapper.convertValue(historianInnerNode.get("key"), String.class)))
						.findFirst().orElse(null);
				// add a list here

				if (assetTypeParam != null
						&& !uniqueAsset(assetTypeParam.getName(), condenserAssetF.getId()).isPresent()) {
					saveAssetParam(createdBy, createdBy, false, assetTypeParam.getDataType(), assetTypeParam.getName(),
							assetTypeParam.getDisplayName(),
							mapper.convertValue(historianInnerNode.get(assetTypeParam.getName()), String.class),
							condenserAssetF,
							mapper.convertValue(historianInnerNode.get(ApplicationConstants.DESCRIPTOR), String.class),
							mapper.convertValue(historianInnerNode.get(ApplicationConstants.TAG_NAME), String.class),
							mapper.convertValue(historianInnerNode.get("uom"), String.class),
							ApplicationConstants.ASSET_RAW_HEADER);
				}

			}

			filterAssetTypeParamFac.addAll(filterAssetTypeParamUnit);
			calculateDesign(designCalcReq, filterAssetTypeParamFac, faciltyAssetF, unitAssetF, createdBy);
			LOGGER.info("Asset Saved successfully");
			return condenserAssetF.getId();
		} catch (JsonProcessingException e) {
			LOGGER.error("Condenser Save JSON ERROR");
			throw new ChemtreatException(e.getLocalizedMessage());
		}

	}

	@Async
	private void calculateDesign(JSONObject designCalcReq, List<AssetTypeParam> filterAssetTypeParamFac,
			Asset facilityAsset, Asset unitAsset, String createdBy) {

		// call the design data
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "calculateDesign");

		DesignCalRespDTO result = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		try {

			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);

			DesignCalReqDTO designCalReqDTO = mapper.readValue(designCalcReq.toString(), DesignCalReqDTO.class);
			HttpEntity<DesignCalReqDTO> request = new HttpEntity<>(designCalReqDTO, headers);
			ResponseEntity<DesignCalRespDTO> response = restTemplate.postForEntity(designCalcURL, request,
					DesignCalRespDTO.class);

			if (response.getStatusCode() == HttpStatus.OK) {
				DesignCalRespDTO respList = response.getBody();
				result = respList;

				String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
				JSONObject jsonObject = new JSONObject(jsonStr);
				jsonObject.keySet().forEach(keyStr -> {
					Object keyvalue = jsonObject.get(keyStr);
					AssetTypeParam assetTypeParam = filterAssetTypeParamFac.stream()
							.filter(assetParamF -> assetParamF.getName().equalsIgnoreCase(keyStr)).findFirst()
							.orElse(null);
					if (assetTypeParam != null
							&& uniqueAsset(assetTypeParam.getName(), facilityAsset.getId()).isPresent()) {
						Optional<AssetParam> assetOpt = uniqueAsset(assetTypeParam.getName(), facilityAsset.getId());
						AssetParam assetParam = assetOpt.get();
						assetParam.setValue(keyvalue.toString());
						assetParamRepository.save(assetParam);
					}

					if (assetTypeParam != null
							&& uniqueAsset(assetTypeParam.getName(), unitAsset.getId()).isPresent()) {
						Optional<AssetParam> assetOpt = uniqueAsset(assetTypeParam.getName(), unitAsset.getId());
						AssetParam assetParam = assetOpt.get();
						assetParam.setValue(keyvalue.toString());
						assetParamRepository.save(assetParam);
					}

				});

			}

		} catch (Exception e) {
			LOGGER.error("error in LookUpServiceImpl.getLookUpData {}", e.getMessage());
		}

	}

	@Override
	@Transactional(rollbackFor = ChemtreatException.class)
	public void updateCondenser(String asset, String assetId) throws ChemtreatException {

		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "updateCondenser");

		LOGGER.info("updateCondenser");
		ObjectMapper mapper = new ObjectMapper();
		List<AssetTypeParam> filterAssetTypeParamFac = null;
		JSONObject designCalcReq = new JSONObject();
		try {
			JsonNode node = mapper.readTree(asset);
			Tenant tenant = null;
			Asset facilityAsset = null;
			Asset unitAsset = null;
			Asset condenserAsset = null;

			// check condenser
			Optional<Asset> condenserAssetOpt = assetRepository.findById(assetId);
			if (!condenserAssetOpt.isPresent()) {
				throw new ResourceNotFoundException(ApplicationConstants.ASSET_NOT_PRESENT);
			}
			condenserAsset = condenserAssetOpt.get();

			List<AssetTypeParam> assetTypeUpdParamList = assetTypeParamRepository
					.getAllAssetTypeParamOnName(ApplicationConstants.ASSET_FACILITY);
			filterAssetTypeParamFac = assetTypeUpdParamList.stream().filter(AssetTypeParam::getComputed)
					.collect(Collectors.toList());

			String updatedBy = mapper.convertValue(node.get("updatedBy"), String.class);
			// check for tenant else create new tenant.
			if (node.has("tenantId")) {
				Integer tenantId = mapper.convertValue(node.get("tenantId"), Integer.class);
				Optional<Tenant> tenantOpt = tenantRepository.findById(tenantId);
				if (!tenantOpt.isPresent()) {
					throw new ResourceNotFoundException(ApplicationConstants.ASSET_NO_TENANT_ERR);
				} else {
					tenant = tenantOpt.get();
				}
			}

			// check for Facility else create new Facility.

			if (node.has("facilityName")) {
				String facilityName = mapper.convertValue(node.get("facilityName"), String.class);
				Optional<Asset> facilityAssetOpt = assetRepository.getAssetByTenantAndAssetNameType(tenant.getId(),
						ApplicationConstants.ASSET_FACILITY, facilityName);

				if (!facilityAssetOpt.isPresent()) {
					facilityAsset = saveAssetTemplate(ApplicationConstants.ASSET_FACILITY, facilityName, tenant,
							updatedBy, null);

					// add attributes for computation

					if (!filterAssetTypeParamFac.isEmpty()) {
						for (AssetTypeParam assetTypeParam : filterAssetTypeParamFac) {
							saveAssetParam(updatedBy, updatedBy, true, assetTypeParam.getDataType(),
									assetTypeParam.getName(), assetTypeParam.getDisplayName(),
									assetTypeParam.getValue(), facilityAsset, "", "",
									assetTypeParam.getUnitOfMeasureId(), ApplicationConstants.ASSET_DESIGN);
						}
					}
				} else {
					facilityAsset = facilityAssetOpt.get();
				}
			}
			// check for Unit asset else create new Unit.
			List<AssetTypeParam> filterAssetTypeParamUnit = null;
			List<AssetTypeParam> assetTypeUnitParamList = assetTypeParamRepository
					.getAllAssetTypeParamOnName(ApplicationConstants.ASSET_Unit);

			filterAssetTypeParamUnit = assetTypeUnitParamList.stream().filter(AssetTypeParam::getComputed)
					.collect(Collectors.toList());
			if (node.has("systemName")) {
				String unitName = mapper.convertValue(node.get("systemName"), String.class);
				Optional<Asset> unitAssetOpt = assetRepository.getAssetByTenantAndAssetNameTypeUnit(tenant.getId(),
						ApplicationConstants.ASSET_Unit, unitName, facilityAsset.getId());
				if (!unitAssetOpt.isPresent()) {

					unitAsset = saveAssetTemplate(ApplicationConstants.ASSET_Unit, unitName, tenant, updatedBy,
							facilityAsset);

					if (!filterAssetTypeParamUnit.isEmpty()) {
						for (AssetTypeParam assetTypeParam : filterAssetTypeParamUnit) {
							saveAssetParam(updatedBy, updatedBy, true, assetTypeParam.getDataType(),
									assetTypeParam.getName(), assetTypeParam.getDisplayName(),
									assetTypeParam.getValue(), unitAsset, "", "", assetTypeParam.getUnitOfMeasureId(),
									ApplicationConstants.ASSET_DESIGN);
						}
					}
				} else {
					unitAsset = unitAssetOpt.get();
					unitAsset.setAsset(facilityAsset);
					unitAsset = assetRepository.save(unitAsset);
				}
			}
			// check for Condenser asset else create new Unit.

			if (node.has("condenserName")) {

				String condenserName = mapper.convertValue(node.get("condenserName"), String.class);
				Optional<Asset> condenserAssetOpt2 = assetRepository.getAssetByTenantAndAssetNameType(tenant.getId(),
						ApplicationConstants.ASSET_TYPE_CONDENSER, condenserName);
				if (condenserAssetOpt2.isPresent() && !condenserAssetOpt2.get().getId().equals(assetId)) {
					LOGGER.error(ApplicationConstants.CONDENSOR_EXISTS);
					throw new ChemtreatException(ApplicationConstants.CONDENSOR_EXISTS);
				}

				condenserAsset = condenserAssetOpt.get();
				condenserAsset.setName(condenserName);
				condenserAsset.setAsset(unitAsset);
				condenserAsset = assetRepository.save(condenserAsset);
			}

			// add plant attributes
			JsonNode plantUpdNode = mapper.readTree(node.get(ApplicationConstants.PLANT_DATA).toString());

			final Asset unitUpdAssetF = unitAsset;
			final Asset faciltyUpdAssetF = facilityAsset;
			plantUpdNode.fields().forEachRemaining(entry -> {
				AssetTypeParam assetUpdateTypeParam = assetTypeUpdParamList.stream()
						.filter(assetParamF -> assetParamF.getName().equals(entry.getKey())).findFirst().orElse(null);

				if (assetUpdateTypeParam != null
						&& !uniqueAsset(assetUpdateTypeParam.getName(), faciltyUpdAssetF.getId()).isPresent()) {
					saveAssetParam(updatedBy, updatedBy, false, assetUpdateTypeParam.getDataType(),
							assetUpdateTypeParam.getName(), assetUpdateTypeParam.getDisplayName(),
							mapper.convertValue(plantUpdNode.get(assetUpdateTypeParam.getName()), String.class),
							faciltyUpdAssetF, "", "", assetUpdateTypeParam.getUnitOfMeasureId(),
							ApplicationConstants.ASSET_DESIGN);
					designCalcReq.put(assetUpdateTypeParam.getName(),
							mapper.convertValue(plantUpdNode.get(assetUpdateTypeParam.getName()), String.class));

				} else if (assetUpdateTypeParam != null
						&& uniqueAsset(assetUpdateTypeParam.getName(), faciltyUpdAssetF.getId()).isPresent()) {
					Optional<AssetParam> assetOpt = uniqueAsset(assetUpdateTypeParam.getName(),
							faciltyUpdAssetF.getId());
					AssetParam assetParam = assetOpt.get();
					assetParam.setValue(
							mapper.convertValue(plantUpdNode.get(assetUpdateTypeParam.getName()), String.class));
					assetParamRepository.save(assetParam);
					designCalcReq.put(assetUpdateTypeParam.getName(),
							mapper.convertValue(plantUpdNode.get(assetUpdateTypeParam.getName()), String.class));

				}

			});

			// add Unit attributes
			JsonNode unitNode = mapper.readTree(node.get("unitData").toString());

			final Asset condenserUpdAssetF = condenserAsset;
			unitNode.fields().forEachRemaining(entry -> {
				AssetTypeParam assetUpdateTypeParam = assetTypeUnitParamList.stream()
						.filter(assetParamF -> assetParamF.getName().equals(entry.getKey())).findFirst().orElse(null);

				if (assetUpdateTypeParam != null
						&& !uniqueAsset(assetUpdateTypeParam.getName(), unitUpdAssetF.getId()).isPresent()) {
					saveAssetParam(updatedBy, updatedBy, false, assetUpdateTypeParam.getDataType(),
							assetUpdateTypeParam.getName(), assetUpdateTypeParam.getDisplayName(),
							mapper.convertValue(unitNode.get(assetUpdateTypeParam.getName()), String.class),
							unitUpdAssetF, null, null, assetUpdateTypeParam.getUnitOfMeasureId(),
							ApplicationConstants.ASSET_DESIGN);
					designCalcReq.put(assetUpdateTypeParam.getName(),
							mapper.convertValue(unitNode.get(assetUpdateTypeParam.getName()), String.class));

				} else if (assetUpdateTypeParam != null
						&& uniqueAsset(assetUpdateTypeParam.getName(), unitUpdAssetF.getId()).isPresent()) {
					Optional<AssetParam> assetOpt = uniqueAsset(assetUpdateTypeParam.getName(), unitUpdAssetF.getId());
					AssetParam assetParam = assetOpt.get();
					assetParam
							.setValue(mapper.convertValue(unitNode.get(assetUpdateTypeParam.getName()), String.class));
					assetParamRepository.save(assetParam);
					designCalcReq.put(assetUpdateTypeParam.getName(),
							mapper.convertValue(unitNode.get(assetUpdateTypeParam.getName()), String.class));

				}

			});
			// add condenser attributes
			JsonNode historianNode = mapper.readTree(node.get("historianMap").toString());
			List<AssetTypeParam> assetTypeParamUnitList = assetTypeParamRepository
					.getAllAssetTypeParamOnName(ApplicationConstants.ASSET_TYPE_CONDENSER);

			for (int i = 0; i < historianNode.size(); i++) {
				JsonNode historianInnerNode = historianNode.get(i);
				AssetTypeParam assetTypeUpdateParam = assetTypeParamUnitList.stream()
						.filter(assetParamF -> assetParamF.getName()
								.equalsIgnoreCase(mapper.convertValue(historianInnerNode.get("key"), String.class)))
						.findFirst().orElse(null);

				List<AssetTypeParam> assetTypeParamUnitListDup = assetTypeParamUnitList;
				assetTypeParamUnitListDup.remove(assetTypeUpdateParam);
				if (i == historianNode.size() - 1) {
					assetTypeParamUnitListDup = assetTypeParamUnitListDup.stream()
							.filter(assetParam -> !assetParam.getComputed()).collect(Collectors.toList());
					for (AssetTypeParam assetTypeUpdateParamDupObj : assetTypeParamUnitListDup) {
						Optional<AssetParam> assetParamDel = assetParamRepository
								.getAssetByNameIdF(assetTypeUpdateParamDupObj.getName(), condenserUpdAssetF.getId());
						if (assetParamDel.isPresent()) {
							assetParamRepository.delete(assetParamDel.get());
						}
					}
				}
				if (assetTypeUpdateParam != null
						&& !uniqueAsset(assetTypeUpdateParam.getName(), condenserUpdAssetF.getId()).isPresent()) {
					saveAssetParam(updatedBy, updatedBy, false, assetTypeUpdateParam.getDataType(),
							assetTypeUpdateParam.getName(), assetTypeUpdateParam.getDisplayName(),
							mapper.convertValue(historianInnerNode.get(assetTypeUpdateParam.getName()), String.class),
							condenserUpdAssetF, mapper.convertValue(historianInnerNode.get("descriptor"), String.class),
							mapper.convertValue(historianInnerNode.get("tagName"), String.class),
							mapper.convertValue(historianInnerNode.get("uom"), String.class),
							ApplicationConstants.ASSET_RAW_HEADER);
				} else if (assetTypeUpdateParam != null
						&& uniqueAsset(assetTypeUpdateParam.getName(), condenserUpdAssetF.getId()).isPresent()) {
					Optional<AssetParam> assetOpt = uniqueAsset(assetTypeUpdateParam.getName(),
							condenserUpdAssetF.getId());
					AssetParam assetParam = assetOpt.isPresent() ? assetOpt.get() : null;

					if (assetParam != null) {
						assetParam
								.setDescriptor(mapper.convertValue(historianInnerNode.get("descriptor"), String.class));
						assetParam.setCustomerTagName(
								mapper.convertValue(historianInnerNode.get("tagName"), String.class));
						assetParam.setUnitOfMeasureId(mapper.convertValue(historianInnerNode.get("uom"), String.class));
						assetParamRepository.save(assetParam);
					}

				}

			}

			filterAssetTypeParamFac.addAll(filterAssetTypeParamUnit);
			calculateDesign(designCalcReq, filterAssetTypeParamFac, faciltyUpdAssetF, unitUpdAssetF, updatedBy);
		}

		catch (JsonProcessingException e) {
			LOGGER.error("Condenser Save JSON ERROR");
			throw new ChemtreatException(e.getLocalizedMessage());
		}
		LOGGER.info("Asset Updated successfully");
	}

	private void saveAssetParam(String createdBy, String updatedBy, boolean computed, String dataType, String name,
			String displayName, String value, Asset asset, String desc, String tag, String uom, String type) {

		AssetParam assetParam = new AssetParam();
		assetParam.setCreatedBy(createdBy);
		assetParam.setUpdatedBy(updatedBy);
		assetParam.setStatus(true);
		assetParam.setComputed(computed);
		assetParam.setDataType(dataType);
		assetParam.setName(name);
		assetParam.setDisplayName(displayName);
		assetParam.setValue(value);
		assetParam.setAsset(asset);
		assetParam.setDescriptor(desc);
		assetParam.setCustomerTagName(tag);
		assetParam.setUnitOfMeasureId(uom);
		assetParam.setType(type);
		assetParamRepository.save(assetParam);

	}

	private Asset saveAssetTemplate(String assetType, String assetName, Tenant tenant, String createdBy,
			Asset parentAsset) {
		Asset asset = new Asset();

		Optional<AssetType> assetTypeOpt = assetTypeRepository.findByName(assetType);
		asset.setAssetType(assetTypeOpt.isPresent() ? assetTypeOpt.get() : null);
		asset.setName(assetName);
		asset.setStatus(true);
		asset.setTenant(tenant);
		asset.setCreatedBy(createdBy);
		asset.setUpdatedBy(createdBy);
		asset = assetRepository.save(asset);
		asset.setAsset(assetType.equalsIgnoreCase(ApplicationConstants.ASSET_FACILITY) ? asset : parentAsset);
		asset = assetRepository.save(asset);

		return asset;

	}

	private Optional<AssetParam> uniqueAsset(String name, String assetId) {
		return assetParamRepository.getAssetByNameId(name, assetId);
	}

	@Override
	public JSONObject getAssetById(String assetId) throws ChemtreatException {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getAssetById");
		LOGGER.info("Fetching Condenser Asset");

		JSONObject resultObj = null;
		if (assetId != null) {
			Optional<CondenserDTO> condenserAssetDTO = assetRepository.findAssetByAssetId(assetId);
			if (condenserAssetDTO.isPresent()) {
				CondenserDTO condenserDto = condenserAssetDTO.get();
				resultObj = new JSONObject();
				condenserDto.setAccountName("Tenaska");
				AssetFacilityDTO assetFacDTO = assetRepository.getFacilityData(ApplicationConstants.ASSET_FACILITY,
						assetId);

				condenserDto.setSystemName(assetFacDTO.getUnitName());
				condenserDto.setFacilityName(assetFacDTO.getFacilityName());

				List<PlantDataDTO> plantDataDTOList = assetParamRepository
						.getAssetParamById(assetFacDTO.getFacilityId());
				JSONObject plantData = null;
				if (!plantDataDTOList.isEmpty()) {
					plantData = new JSONObject();
					for (PlantDataDTO plantDataDTO : plantDataDTOList) {
						plantData.put(plantDataDTO.getAssetKey(), plantDataDTO.getAssetValue());
					}
				}

				List<PlantDataDTO> unitDTOList = assetParamRepository.getAssetParamById(assetFacDTO.getUnitId());
				JSONObject plantData1 = null;
				if (!unitDTOList.isEmpty()) {
					plantData1 = new JSONObject();
					for (PlantDataDTO unitDataDTO : unitDTOList) {
						plantData1.put(unitDataDTO.getAssetKey(), unitDataDTO.getAssetValue());
					}
				}
				resultObj.put("plantData", plantData);
				resultObj.put("unitData", plantData1);
				resultObj.put("Condenser", condenserDto);

				List<HistorianDataDTO> historianDataDTOList = assetParamRepository.getHistAssetParamById(assetId);
				JSONArray historianArry = null;
				List<UOMdataDTO> uomListM = null;
				if (!historianDataDTOList.isEmpty()) {
					
						uomListM = getUOMList();
					
					historianArry = new JSONArray();
					for (HistorianDataDTO historianDataDTO : historianDataDTOList) {
						// add one if condition to ignore calc value
						// uomList.stream().filter(UOMdataDTO u1->u1.getId()=historianDataDTO)
						if (uomListM != null && !uomListM.isEmpty()) {
							UOMdataDTO uomData = uomListM.stream()
									.filter(uomListVal -> uomListVal.getId().equals(historianDataDTO.getUom()))
									.findFirst().orElse(null);
							historianDataDTO.setUomName(uomData != null ? uomData.getName() : "");
						}
						JSONObject jObj = new JSONObject(historianDataDTO);
						historianArry.put(jObj);
					}

				}
				resultObj.put("historianMap", historianArry);
			} else {
				throw new ResourceNotFoundException(ApplicationConstants.ASSET_NOT_PRESENT);
			}

		} else {
			throw new ResourceNotFoundException(ApplicationConstants.ASSET_NOT_PRESENT);
		}
		LOGGER.info(" Condenser Fetched successfully");
		return resultObj;

	}

	@Override
	@Transactional
	public void deleteAsset(String assetId) {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "deleteAsset");

		LOGGER.info("Fetching Asset");

		assetParamRepository.deleteAssetParamOnAssetId(assetId);

		Asset asset = assetRepository.findById(assetId)
				.orElseThrow(() -> new ResourceNotFoundException(ApplicationConstants.ASSET_NOT_PRESENT));

		assetRepository.delete(asset);
		LOGGER.info("Asset deleted successfully");
	}

	/**
	 * Get Asset By tenant and type.
	 *
	 * @param tenantId  the tenant id
	 * @param assetType the asset type
	 * @return the AssetDTO
	 */
	@Override
	public List<AssetDTO> getAllAssetByTenantAndType(Integer tenantId, String assetType) throws ChemtreatException {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getAllAssetByTenantAndType");

		LOGGER.info("Get all Asset By Tenant and type fetching started");

		List<AssetDTO> assetList = null;
		if (tenantId != null) {
			Optional<Tenant> tenantOpt = tenantRepository.findByOrganiztionId(tenantId);
			if (tenantOpt.isPresent()) {
				Optional<AssetType> assetTypeOpt = assetTypeRepository.findByName(assetType);
				if (assetTypeOpt.isPresent()) {
					assetList = assetRepository.getAllAssetByTenantAndType(tenantId, assetType);

					for (AssetDTO asset : assetList) {
						// Need to integrate with china teams API
						asset.setAccountName("Tenaska");
						AssetFacilityDTO assetFacDTO = assetRepository
								.getFacilityData(ApplicationConstants.ASSET_FACILITY, asset.getId());
						asset.setSystemName(assetFacDTO.getUnitName() != null ? assetFacDTO.getUnitName() : "");
						asset.setFacilityName(
								assetFacDTO.getFacilityName() != null ? assetFacDTO.getFacilityName() : "");

					}
					assetList.sort((AssetDTO a1, AssetDTO a2) -> a1.getName().compareTo(a2.getName()));
				} else {
					throw new ResourceNotFoundException("Asset Type Not found");
				}

			} else {
				throw new ResourceNotFoundException(ApplicationConstants.TENANT_NOT_FOUND);
			}
		} else {
			throw new ChemtreatException(ApplicationConstants.TENANT_NOT_NULL);

		}
		return assetList;
	}

	@Override
	public AssetDTOLoadInt getCondenserLoadById(String assetId) {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getCondenserLoadById");

		AssetDTOLoadInt respList = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		Optional<AssetParam> assetParamOpt = assetParamRepository
				.getAssetByNameId(ApplicationConstants.ASSET_PERCENTAGE_LOAD, assetId);
		if (!assetParamOpt.isPresent()) {
			return new AssetDTOLoadInt();
		}
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(minLoadUrl).queryParam("assetId", assetId)
				.queryParam("assetParamName", assetParamOpt.get().getId());

		try {

			ResponseEntity<AssetDTOLoadInt> response = restTemplate.getForEntity(builder.toUriString(),
					AssetDTOLoadInt.class, entity);

			if (response.getStatusCode() == HttpStatus.OK) {
				respList = response.getBody();

			}

		} catch (Exception e) {
			LOGGER.error("error in LookUpServiceImpl.getLookUpData {}", e.getMessage());
		}

		return respList;
	}

	@Override
	public List<ChartsConfigDTO> getCondenserChartValues(String assetId, Boolean isKpi) {
		// check condenser
		List<ChartsConfigDTO> chartsConfigDTOList = null;
		Optional<Asset> condenserAssetOpt = assetRepository.findById(assetId);
		if (!condenserAssetOpt.isPresent()) {
			throw new ResourceNotFoundException(ApplicationConstants.ASSET_NOT_PRESENT);
		}
		if (Boolean.TRUE.equals(isKpi)) {
			chartsConfigDTOList = assetParamRepository.getAssetParamChartsKPI(assetId, ApplicationConstants.KPI_ATTR);
			chartsConfigDTOList.sort((ChartsConfigDTO c1, ChartsConfigDTO c2) -> c1.getKey().compareTo(c2.getKey()));
		} else {
			chartsConfigDTOList = assetParamRepository.getAssetParamChartsNonKPI(assetId,
					ApplicationConstants.ASSET_RAW_HEADER);
			chartsConfigDTOList.sort((ChartsConfigDTO c1, ChartsConfigDTO c2) -> c1.getKey().compareTo(c2.getKey()));

		}
		return chartsConfigDTOList;
	}

	@Override
	public List<String> getKPIChartValues(String assetId) {
		List<String> kpiList = null;
		Optional<Asset> condenserAssetOpt = assetRepository.findById(assetId);
		if (condenserAssetOpt.isPresent()) {
			kpiList = assetParamRepository.getAssetParamIdbyName(assetId, ApplicationConstants.KPI_ATTR);
		}
		return kpiList;
	}

	@Override
	public JSONObject getAssetInfo(String name, String type, Integer tenantId) {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getAssetInfo");
		JSONObject resultObj = new JSONObject();
		Optional<Asset> optAsset = assetRepository.getAssetByTenantAndAssetNameType(tenantId, type, name);

		if (optAsset.isPresent()) {
			resultObj = new JSONObject();
			List<PlantDataDTO> plantDataDTOList = assetParamRepository.getAssetParamById(optAsset.get().getId());
			JSONObject plantData = null;
			if (!plantDataDTOList.isEmpty()) {
				plantData = new JSONObject();
				for (PlantDataDTO plantDataDTO : plantDataDTOList) {
					plantData.put(plantDataDTO.getAssetKey(), plantDataDTO.getAssetValue());
				}
			}
			resultObj.put("plantData", plantData);
		}
		return resultObj;
	}

	@Override
	public Page<AssetDTO> getAllAssetByTenantAndTypes(Integer tenantId, String assetType, Integer page, Integer size,
			String sort, String order, String facilityName, String systemName) throws ChemtreatException {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getAllAssetByTenantAndType");

		LOGGER.info("Get all Asset By Tenant and type fetching started");

		Page<AssetDTO> assetDTOPage = null;

		Pageable sortedPage = CommonUtils.getPageable(page, size, sort, order);

		if (tenantId != null) {
			Optional<Tenant> tenantOpt = tenantRepository.findByOrganiztionId(tenantId);
			if (tenantOpt.isPresent()) {
				Optional<AssetType> assetTypeOpt = assetTypeRepository.findByName(assetType);
				if (assetTypeOpt.isPresent()) {

					assetDTOPage = assetRepository.getAllAssetByTenantAndTypes(sortedPage, tenantId, assetType,
							facilityName, systemName == null ? null : systemName + "~");

				} else {
					throw new ResourceNotFoundException("Asset Type Not found");
				}

			} else {
				throw new ResourceNotFoundException(ApplicationConstants.TENANT_NOT_FOUND);
			}
		} else {
			throw new ChemtreatException(ApplicationConstants.TENANT_NOT_NULL);

		}
		return assetDTOPage;
	}

	private List<UOMdataDTO> getUOMList() {

		List<UOMdataDTO> result = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		try {

			ResponseEntity<UOMdataDTO[]> response = restTemplate.getForEntity(uomServiceUrl, UOMdataDTO[].class);

			if (response.getStatusCode() == HttpStatus.OK) {

				UOMdataDTO[] respList = response.getBody();
				result = new ArrayList<>(Arrays.asList(respList));

			}

		} catch (Exception e) {
			LOGGER.error("error in UOMServiceImpl.getUOMConversionData->{}", e);
		}
		return result;

	}

	@Override
	public List<AssetIngestionLogDTO> getAllInflowLog(@NotBlank Integer tenantId,Integer size) throws ChemtreatException {
		LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getAllInflowLog");

		LOGGER.info("Get all getAllInflowLog by Tenant  fetching started");

		List<AssetIngestionLogDTO> assetIngestionLogDTO = null;
		if (tenantId != null) {
			Optional<Tenant> tenantOpt = tenantRepository.findByOrganiztionId(tenantId);
			if (tenantOpt.isPresent()) {
				assetIngestionLogDTO = assetDataLogRepository.findAllByTenantId(String.valueOf(tenantId));
				if (assetIngestionLogDTO.size() > size) {
					assetIngestionLogDTO.subList(size, assetIngestionLogDTO.size()).clear();
				}
				for (AssetIngestionLogDTO assetInflow : assetIngestionLogDTO) {
					Optional<Asset> assetOpt = assetRepository.findById(assetInflow.getCondenserName());
					if (assetOpt.isPresent()) {
						assetInflow.setCondenserName(assetOpt.get().getName());

					}
					if (assetInflow.getIngestionStatus().equals("Calc-Completed")) {
						assetInflow.setIngestionStatus("Completed");
					} else if (assetInflow.getIngestionStatus().equals("failed")) {
						assetInflow.setIngestionStatus("Error");
					} else if (assetInflow.getIngestionStatus().equals("Calc-In-Progress")) {
						assetInflow.setIngestionStatus("Running-Calculations");
					} else if (assetInflow.getIngestionStatus().equals("inprogress")) {
						assetInflow.setIngestionStatus("Running-Ingestion");
					} else if (assetInflow.getIngestionStatus().equals("done")) {
						assetInflow.setIngestionStatus("Running-Ingestion-completed");
					}
				}

			} else {
				throw new ResourceNotFoundException(ApplicationConstants.TENANT_NOT_FOUND);
			}
		} else {
			throw new ChemtreatException(ApplicationConstants.TENANT_NOT_NULL);

		}
		return assetIngestionLogDTO;
	}
}
