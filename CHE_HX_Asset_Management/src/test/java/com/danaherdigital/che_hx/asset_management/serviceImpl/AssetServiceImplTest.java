package com.danaherdigital.che_hx.asset_management.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.modules.agent.PowerMockAgent;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.danaherdigital.che_hx.asset_management.dto.AssetDTO;
import com.danaherdigital.che_hx.asset_management.dto.AssetDTOLoadInt;
import com.danaherdigital.che_hx.asset_management.dto.AssetFacilityDTO;
import com.danaherdigital.che_hx.asset_management.dto.AssetIngestionLogDTO;
import com.danaherdigital.che_hx.asset_management.dto.ChartsConfigDTO;
import com.danaherdigital.che_hx.asset_management.dto.CondenserDTO;
import com.danaherdigital.che_hx.asset_management.dto.DesignCalRespDTO;
import com.danaherdigital.che_hx.asset_management.dto.HistorianDataDTO;
import com.danaherdigital.che_hx.asset_management.dto.PlantDataDTO;
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
import com.danaherdigital.che_hx.asset_management.serviceimpl.AssetServiceImpl;
import com.danaherdigital.che_hx.asset_management.utils.ApplicationConstants;
import com.danaherdigital.che_hx.asset_management.utils.AssetTestUtil;
import com.danaherdigital.che_hx.asset_management.utils.CommonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(MockitoJUnitRunner.class)
public class AssetServiceImplTest {

	@InjectMocks
	AssetServiceImpl assetServiceImpl;

	@Mock
	AssetServiceImpl assetServiceImpl1;

	@Mock
	AssetRepository assetRepository;

	@Mock
	TenantRepository tenantRepository;
	
	@Mock
	AssetDataLogRepository assetDataLogRepository;

	@Mock
	AssetTypeRepository assetTypeRepository;

	@Mock
	AssetParamRepository assetParamRepository;

	@Mock
	AssetTypeParamRepository assetTypeParamRepository;

	@Mock
	 RestTemplate restTemplate;

		/*
		 * @Rule public PowerMockRule rule = new PowerMockRule(); static {
		 * PowerMockAgent.initializeIfNeeded(); }
		 */
//		@Before
//		public void setUp() throws Exception{
//
//			ReflectionTestUtils.setField(assetServiceImpl, "minLoadUrl", "http://localhost:8083/v1/TimeSeries/load");
//			//PowerMockito.mockStatic(QueryApiUtil.class);
//
//
//			PowerMockito.whenNew(RestTemplate.class).withNoArguments().thenReturn(restTemplate);
//			PowerMockito.mockStatic(URLEncoder.class);
//			//PowerMockito.mockStatic(UriComponentsBuilder.class);
//
//		}

	@Test
	public void getAllAssetTest() throws ChemtreatException {
		List<AssetDTO> assetDTO = new ArrayList<>();
		assetDTO.add(AssetTestUtil.getAssetDTOTemplate());
		when(assetRepository.getAllAssetByTenantAndType(1, ApplicationConstants.ASSET_TYPE_CONDENSER))
				.thenReturn(assetDTO);

		Optional<Tenant> tenantOpt = null;
		tenantOpt = AssetTestUtil.getOptionalTenantTemplate();
		when(tenantRepository.findByOrganiztionId(1)).thenReturn(tenantOpt);

		Optional<AssetType> assetTypeOpt = null;
		assetTypeOpt = AssetTestUtil.getOptionalAssetTypeParamTemplate();
		when(assetTypeRepository.findByName(ApplicationConstants.ASSET_TYPE_CONDENSER)).thenReturn(assetTypeOpt);
		// when(assetTypeRepository.save(Mockito.any(AssetType.class))).thenReturn(a);
		Integer tenantId = 1;
		AssetFacilityDTO assetTestFaciDTO = AssetTestUtil.getAssetFacilityDTOTemplate();
		when(assetRepository.getFacilityData(Mockito.anyString(), Mockito.anyString())).thenReturn(assetTestFaciDTO);
		List<AssetDTO> assetDTOList = assetServiceImpl.getAllAssetByTenantAndType(tenantId,
				ApplicationConstants.ASSET_TYPE_CONDENSER);
		assertNotEquals(0, assetDTOList.size());

	}

	@Test
	public void getAllAssetTestv2() throws ChemtreatException {

		List<AssetDTO> assetDTO = new ArrayList<>();
		assetDTO.add(AssetTestUtil.getAssetDTOTemplate());
		Page<AssetDTO> assetDTOPage = new PageImpl<>(assetDTO);

		when(assetRepository.getAllAssetByTenantAndTypes(CommonUtils.getPageable(1, 1, "", "ASCENDING"), 1,
				ApplicationConstants.ASSET_TYPE_CONDENSER, ApplicationConstants.FACILITY_NAME,
				ApplicationConstants.SYSTEM_NAME+"~")).thenReturn(assetDTOPage);

		Optional<Tenant> tenantOpt = null;
		tenantOpt = AssetTestUtil.getOptionalTenantTemplate();
		when(tenantRepository.findByOrganiztionId(1)).thenReturn(tenantOpt);

		Optional<AssetType> assetTypeOpt = null;
		assetTypeOpt = AssetTestUtil.getOptionalAssetTypeParamTemplate();
		when(assetTypeRepository.findByName(ApplicationConstants.ASSET_TYPE_CONDENSER)).thenReturn(assetTypeOpt);
		// when(assetTypeRepository.save(Mockito.any(AssetType.class))).thenReturn(a);
		Integer tenantId = 1;
		Page<AssetDTO> assetDTOList = assetServiceImpl.getAllAssetByTenantAndTypes(tenantId,
				ApplicationConstants.ASSET_TYPE_CONDENSER, 1, 1, "", "ASCENDING", ApplicationConstants.FACILITY_NAME,
				ApplicationConstants.SYSTEM_NAME);
		assertNotEquals(0, assetDTOList.getContent().size());

	}

	@Test
	public void getCondenserByIdTest() throws ChemtreatException {

		Optional<CondenserDTO> condenserDto = AssetTestUtil.getCondenserDTOTemplate();
		when(assetRepository.findAssetByAssetId(Mockito.anyString())).thenReturn(condenserDto);

		AssetFacilityDTO assetTestFaciDTO = AssetTestUtil.getAssetFacilityDTOTemplate();
		when(assetRepository.getFacilityData(Mockito.anyString(), Mockito.anyString())).thenReturn(assetTestFaciDTO);

		List<PlantDataDTO> plantDataDTOList = AssetTestUtil.getPlantDataDTOListTemplate();
		when(assetParamRepository.getAssetParamById(Mockito.anyString())).thenReturn(plantDataDTOList);

		List<HistorianDataDTO> historianDataDTOList = AssetTestUtil.getHistorianDataDTOListTemplate();
		when(assetParamRepository.getHistAssetParamById(Mockito.anyString())).thenReturn(historianDataDTOList);

		JSONObject jobj = assetServiceImpl.getAssetById("e9b1df36-16c0-47ac-bc46-9e991f3959be");
		assertNotEquals(0, jobj.length());

	}

	@Test
	public void getCondenserChartValuesTestOk() throws ChemtreatException {

		when(assetRepository.findById(Mockito.anyString())).thenReturn(AssetTestUtil.getOptionalAssetTemplate());

		List<ChartsConfigDTO> chartsConfigDTOListTest = new ArrayList<ChartsConfigDTO>();
		chartsConfigDTOListTest.add(AssetTestUtil.getChartsConfigDTOTemplate());
		when(assetParamRepository.getAssetParamChartsKPI(Mockito.anyString(), Mockito.any()))
				.thenReturn(chartsConfigDTOListTest);
		List<ChartsConfigDTO> chartsConfigDTOList = assetServiceImpl
				.getCondenserChartValues("e9b1df36-16c0-47ac-bc46-9e991f3959be", true);
		assertNotEquals(0, chartsConfigDTOList.size());

	}

	@Test
	public void getCondenserChartValuesTestOkElse() throws ChemtreatException {

		when(assetRepository.findById(Mockito.anyString())).thenReturn(AssetTestUtil.getOptionalAssetTemplate());

		List<ChartsConfigDTO> chartsConfigDTOListTest = new ArrayList<ChartsConfigDTO>();
		chartsConfigDTOListTest.add(AssetTestUtil.getChartsConfigDTOTemplate());
		when(assetParamRepository.getAssetParamChartsNonKPI(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(chartsConfigDTOListTest);
		List<ChartsConfigDTO> chartsConfigDTOList = assetServiceImpl.getCondenserChartValues("e9b1df36-16c0-47ac-bc46-9e991f3959be",false);
		assertNotEquals(0, chartsConfigDTOList.size());

	}

	@Test
	public void getCondenserChartValuesTestFail() throws ChemtreatException {

		when(assetRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			assetServiceImpl.getCondenserChartValues("e9b1df36-16c0-47ac-bc46-9e991f3959be", false);
		});
		String expectedMessage = ApplicationConstants.ASSET_NOT_PRESENT;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void getCondenserLoadByIdTestOk() throws ChemtreatException {
		boolean b=true;
		when(assetParamRepository.getAssetByNameId(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(AssetTestUtil.getAssetParamFacilityTemplate());
		ReflectionTestUtils.setField(assetServiceImpl, "minLoadUrl", "http://localhost:8083/v1/TimeSeries/load");
		AssetDTOLoadInt assetDTOLoadInt = new AssetDTOLoadInt();
//		when(restTemplate.getForEntity("http://localhost:8083/v1/TimeSeries/load?assetId=3232123",AssetDTOLoadInt.class,entity)).thenReturn(
//				new ResponseEntity<>(assetDTOLoadInt,HttpStatus.OK));
		assetDTOLoadInt = assetServiceImpl.getCondenserLoadById("e9b1df36-16c0-47ac-bc46-9e991f3959be");
		Assertions.assertEquals(true, b);

	}

	@Test
	public void getCondenserLoadByIdTestOkElse() throws ChemtreatException {

		when(assetParamRepository.getAssetByNameId(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(Optional.empty());

		AssetDTOLoadInt assetDTOLoadInt2 = assetServiceImpl
				.getCondenserLoadById("e9b1df36-16c0-47ac-bc46-9e991f3959be");
		assertEquals(0, assetDTOLoadInt2.getMaxVal());

	}

	@Test
	public void getKPIChartValuesTestOk() throws ChemtreatException {

		when(assetRepository.findById(Mockito.anyString())).thenReturn(AssetTestUtil.getOptionalAssetTemplate());

		List<String> kpiString = new ArrayList<String>();
		kpiString.add("eb2c4e2b-f819-480f-9a9e-fcdf991ba47c");
		when(assetParamRepository.getAssetParamIdbyName(Mockito.anyString(), Mockito.any())).thenReturn(kpiString);
		List<String> chartsConfigDTOList = assetServiceImpl.getKPIChartValues("e9b1df36-16c0-47ac-bc46-9e991f3959be");
		assertNotEquals(0, chartsConfigDTOList.size());

	}

	@Test
	public void getCondenserByIdTestNull() throws JsonProcessingException {

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			assetServiceImpl.getAssetById(null);
		});
		String expectedMessage = ApplicationConstants.ASSET_NOT_PRESENT;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}
	
	@Test
	public void getAssetInflowNull() throws JsonProcessingException {

		Exception exception = assertThrows(ChemtreatException.class, () -> {
			assetServiceImpl.getAllInflowLog(null,0);
		});
		String expectedMessage = ApplicationConstants.ASSET_TENANT_NULLS;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}


	@Test
	public void getCondenserByIdTestNotFound() throws JsonProcessingException {
		Optional<CondenserDTO> condenserDto = Optional.empty();
		when(assetRepository.findAssetByAssetId(Mockito.anyString())).thenReturn(condenserDto);

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			assetServiceImpl.getAssetById("e9b1df36-16c0-47ac-bc46-9e991f3959be");
		});
		String expectedMessage = ApplicationConstants.ASSET_NOT_PRESENT;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}
	
	@Test
	public void getAssetInflowByIdTestNull() throws JsonProcessingException, ChemtreatException {

		
			Asset a = new Asset();
			a.setId("e9b1df36-16c0-47ac-bc46-9e991f3959be");
			when(assetRepository.findById(Mockito.anyString())).thenReturn(Optional.of(a));
			
			Optional<Tenant> tenantOpt = null;
			tenantOpt = AssetTestUtil.getOptionalTenantTemplate();
			when(tenantRepository.findByOrganiztionId(1)).thenReturn(tenantOpt);
			
			List<AssetIngestionLogDTO> assetIngestionLogDTO = new ArrayList<AssetIngestionLogDTO>();
			assetIngestionLogDTO.add(AssetTestUtil.getInflowLogTemplate());
			assetIngestionLogDTO.add(AssetTestUtil.getInflowLogTemplate2());
			assetIngestionLogDTO.add(AssetTestUtil.getInflowLogTemplate3());
			assetIngestionLogDTO.add(AssetTestUtil.getInflowLogTemplate2());
			assetIngestionLogDTO.add(AssetTestUtil.getInflowLogTemplate3());
			when(assetDataLogRepository.findAllByTenantId(Mockito.anyString())).thenReturn(assetIngestionLogDTO);
			List<AssetIngestionLogDTO> assetIngestionLogDTO2 = assetServiceImpl.getAllInflowLog(1,1);
			assertTrue(!assetIngestionLogDTO2.isEmpty());

	}
	
	@Test
	public void getAssetInflowThrowsTenantNotFound() throws ResourceNotFoundException {

		when(tenantRepository.findByOrganiztionId(2)).thenReturn(Optional.empty());

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			assetServiceImpl.getAllInflowLog(2,1);
		});
		String expectedMessage = ApplicationConstants.ASSET_NO_TENANT_ERR;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void  getAssetInflowByIdTestNotFound() throws JsonProcessingException {
		

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			assetServiceImpl.getAllInflowLog(2,1);
		});
		String expectedMessage = ApplicationConstants.ASSET_NO_TENANT_ERR;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void getAllAssetTestThrowsNullTenant() throws ChemtreatException {

		Exception exception = assertThrows(ChemtreatException.class, () -> {
			assetServiceImpl.getAllAssetByTenantAndType(null, ApplicationConstants.ASSET_TYPE_CONDENSER);
		});
		String expectedMessage = ApplicationConstants.ASSET_TENANT_NULLS;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void getAllAssetTestThrowsTenantNotFound() throws ResourceNotFoundException {

		when(tenantRepository.findByOrganiztionId(2)).thenReturn(Optional.empty());

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			assetServiceImpl.getAllAssetByTenantAndType(2, ApplicationConstants.ASSET_TYPE_CONDENSER);
		});
		String expectedMessage = ApplicationConstants.ASSET_NO_TENANT_ERR;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void getAllAssetTestThrowsAssetTypeNotFound() throws ResourceNotFoundException {
		Optional<Tenant> tenantOpt = null;
		tenantOpt = AssetTestUtil.getOptionalTenantTemplate();
		when(tenantRepository.findByOrganiztionId(1)).thenReturn(tenantOpt);

		when(assetTypeRepository.findByName(ApplicationConstants.ASSET_TYPE_CONDENSERS)).thenReturn(Optional.empty());

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			assetServiceImpl.getAllAssetByTenantAndType(1, ApplicationConstants.ASSET_TYPE_CONDENSERS);
		});
		String expectedMessage = ApplicationConstants.ASSET_TYPE_NOT_PRESENT;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void getAllAssetTestThrowsTenantNotFoundv2() throws ResourceNotFoundException {

		when(tenantRepository.findByOrganiztionId(2)).thenReturn(Optional.empty());

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			assetServiceImpl.getAllAssetByTenantAndTypes(2, ApplicationConstants.ASSET_TYPE_CONDENSER, 1, 1, "",
					"ASCENDING", ApplicationConstants.FACILITY_NAME, ApplicationConstants.SYSTEM_NAME);
		});
		String expectedMessage = ApplicationConstants.ASSET_NO_TENANT_ERR;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void getAllAssetTestThrowsAssetTypeNotFoundv2() throws ResourceNotFoundException {
		Optional<Tenant> tenantOpt = null;
		tenantOpt = AssetTestUtil.getOptionalTenantTemplate();
		when(tenantRepository.findByOrganiztionId(1)).thenReturn(tenantOpt);

		when(assetTypeRepository.findByName(ApplicationConstants.ASSET_TYPE_CONDENSERS)).thenReturn(Optional.empty());

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			assetServiceImpl.getAllAssetByTenantAndTypes(1, ApplicationConstants.ASSET_TYPE_CONDENSERS, 1, 1, "",
					"ASCENDING", ApplicationConstants.FACILITY_NAME, ApplicationConstants.SYSTEM_NAME);
		});
		String expectedMessage = ApplicationConstants.ASSET_TYPE_NOT_PRESENT;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void getAllAssetTestThrowsNullTenantv2() throws ChemtreatException {

		Exception exception = assertThrows(ChemtreatException.class, () -> {
			assetServiceImpl.getAllAssetByTenantAndTypes(null, ApplicationConstants.ASSET_TYPE_CONDENSER, 1, 1, "",
					"ASCENDING", ApplicationConstants.FACILITY_NAME, ApplicationConstants.SYSTEM_NAME);
		});
		String expectedMessage = ApplicationConstants.ASSET_TENANT_NULLS;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void deleteAssetByIdTestValid() throws ResourceNotFoundException {
		Asset a = new Asset();
		String assetId = "e9b1df36-16c0-47ac-bc46-9e991f3959be";
		a.setId("e9b1df36-16c0-47ac-bc46-9e991f3959be");
		when(assetRepository.findById(Mockito.anyString())).thenReturn(Optional.of(a));
		doNothing().when(assetParamRepository).deleteAssetParamOnAssetId(assetId);
		doNothing().when(assetRepository).delete(Mockito.any(Asset.class));
		assetServiceImpl.deleteAsset(a.getId());
		assertEquals("e9b1df36-16c0-47ac-bc46-9e991f3959be", assetId);
	}

	@Test
	public void deleteAssetByIdTestInValid() throws ResourceNotFoundException {
		Asset a = new Asset();
		a.setId("e9b1df36-16c0-47ac-bc46-9e991f3959be");
		when(assetRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

		lenient().doNothing().when(assetRepository).delete(Mockito.any(Asset.class));
		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			assetServiceImpl.deleteAsset(a.getId());
		});
		String expectedMessage = ApplicationConstants.ASSET_NOT_PRESENT;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void createAssetTestPass() throws ChemtreatException {
		ReflectionTestUtils.setField(assetServiceImpl, "designCalcURL",
				"calcengine.designcalc.url");
		boolean b=true;
		DesignCalRespDTO resp=new DesignCalRespDTO();
		resp.setAverageFuelCostsPerMMbtus(24);
		Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.any(), Mockito.any()))
		.thenReturn(new ResponseEntity(resp, HttpStatus.OK));
		Tenant tenant = new Tenant();
		Optional<Tenant> tenantOpt = Optional.of(tenant);
		when(tenantRepository.findByOrganiztionId(Mockito.anyInt())).thenReturn(tenantOpt);

		Optional<AssetType> assetTypeOpt = null;
		assetTypeOpt = AssetTestUtil.getOptionalAssetTypeParamTemplate();
		when(assetTypeRepository.findByName(Mockito.anyString())).thenReturn(assetTypeOpt);

		Asset asset = new Asset();
		when(assetRepository.save(Mockito.any())).thenReturn(asset);

		List<AssetTypeParam> assetTypeParamList = new ArrayList<>();


		assetTypeParamList.add(AssetTestUtil.getAssetTypeParam());
		assetTypeParamList.add(AssetTestUtil.getAssetTypeParam2());
		assetTypeParamList.add(AssetTestUtil.getAssetTypeParam3());
		when(assetTypeParamRepository.getAllAssetTypeParamOnName(Mockito.anyString())).thenReturn(assetTypeParamList);
		assetServiceImpl.saveAsset(AssetTestUtil.getSaveCondenserTemplate());
		Assertions.assertEquals(true, b);

	}

	@Test
	public void createAssetTestPassElse() throws ChemtreatException {
		boolean b=true;
		Tenant tenant = new Tenant();
		Optional<Tenant> tenantOpt = Optional.of(tenant);
		when(tenantRepository.findByOrganiztionId(Mockito.anyInt())).thenReturn(tenantOpt);

		Optional<AssetType> assetTypeOpt = null;
		assetTypeOpt = AssetTestUtil.getOptionalAssetTypeParamTemplate();
		when(assetTypeRepository.findByName(Mockito.anyString())).thenReturn(assetTypeOpt);

		Asset asset = AssetTestUtil.getOptionalAssetTemplate().get();
		when(assetRepository.save(Mockito.any())).thenReturn(asset);

		List<AssetTypeParam> assetTypeParamList = new ArrayList<>();

		assetTypeParamList.add(AssetTestUtil.getAssetTypeParam4());
		assetTypeParamList.add(AssetTestUtil.getAssetTypeParam5());
		when(assetTypeParamRepository.getAllAssetTypeParamOnName(Mockito.anyString())).thenReturn(assetTypeParamList);

		Optional<AssetParam> assetParamTypeOpt = null;
		assetParamTypeOpt = AssetTestUtil.getAssetParamFacilityTemplate();
		when(assetParamRepository.getAssetByNameId(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(assetParamTypeOpt);

		assetServiceImpl.saveAsset(AssetTestUtil.getSaveCondenserTemplate());
		Assertions.assertEquals(true, b);

	}

	@Test
	public void createAssetTestFail() throws ChemtreatException {

		Optional<Tenant> tenantOpt = Optional.empty();
		when(tenantRepository.findByOrganiztionId(Mockito.anyInt())).thenReturn(tenantOpt);

		Tenant tenant = AssetTestUtil.getOptionalTenantTemplate().get();
		when(tenantRepository.save(Mockito.any())).thenReturn(tenant);
		Optional<Asset> assetOpt = AssetTestUtil.getOptionalAssetTemplate();
		when(assetRepository.getAssetByTenantAndAssetNameType(Mockito.anyInt(), Mockito.anyString(),
				Mockito.anyString())).thenReturn(assetOpt);
		Asset asset = new Asset();
		when(assetRepository.save(Mockito.any())).thenReturn(asset);

		Exception exception = assertThrows(ChemtreatException.class, () -> {
			assetServiceImpl.saveAsset(AssetTestUtil.getSaveCondenserTemplate());
		});

		String expectedMessage = ApplicationConstants.ASSET_CONDENSER_DUPLICATE;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void createCondenserTestFail2() throws JsonProcessingException {

		assertThrows(ChemtreatException.class, () -> {
			assetServiceImpl.saveAsset("{ooo:rr}");
		});

	}

	@Test
	public void updateAssetTestPass() throws ChemtreatException {
		boolean b=true;
		Optional<Tenant> tenantOpt = AssetTestUtil.getOptionalTenantTemplate();
		when(tenantRepository.findById(Mockito.anyInt())).thenReturn(tenantOpt);

		Optional<AssetType> assetTypeOpt = null;
		assetTypeOpt = AssetTestUtil.getOptionalAssetTypeParamTemplate();
		when(assetTypeRepository.findByName(Mockito.anyString())).thenReturn(assetTypeOpt);

		Asset asset = new Asset();
		when(assetRepository.save(Mockito.any())).thenReturn(asset);
		when(assetRepository.findById(Mockito.anyString())).thenReturn(AssetTestUtil.getOptionalAssetTemplate());
		List<AssetTypeParam> assetTypeParamList = new ArrayList<>();

		assetTypeParamList.add(AssetTestUtil.getAssetTypeParam());
		assetTypeParamList.add(AssetTestUtil.getAssetTypeParam2());
		assetTypeParamList.add(AssetTestUtil.getAssetTypeParam3());
		when(assetTypeParamRepository.getAllAssetTypeParamOnName(Mockito.anyString())).thenReturn(assetTypeParamList);

		assetServiceImpl.updateCondenser(AssetTestUtil.getSaveCondenserTemplate(), Mockito.anyString());
		Assertions.assertEquals(true, b);

	}

	@Test
	public void updateAssetTestPassElse() throws ChemtreatException {
		boolean b=true;

		Optional<Tenant> tenantOpt = AssetTestUtil.getOptionalTenantTemplate();
		when(tenantRepository.findById(Mockito.anyInt())).thenReturn(tenantOpt);

		Optional<AssetType> assetTypeOpt = null;
		assetTypeOpt = AssetTestUtil.getOptionalAssetTypeParamTemplate();
		when(assetTypeRepository.findByName(Mockito.anyString())).thenReturn(assetTypeOpt);

		Asset asset = AssetTestUtil.getOptionalAssetTemplate().get();
		when(assetRepository.save(Mockito.any())).thenReturn(asset);

		List<AssetTypeParam> assetTypeParamList = new ArrayList<>();

		when(assetRepository.findById(Mockito.anyString())).thenReturn(AssetTestUtil.getOptionalAssetTemplate());
		assetTypeParamList.add(AssetTestUtil.getAssetTypeParam4());
		assetTypeParamList.add(AssetTestUtil.getAssetTypeParam5());
		when(assetTypeParamRepository.getAllAssetTypeParamOnName(Mockito.anyString())).thenReturn(assetTypeParamList);

		Optional<AssetParam> assetParamTypeOpt = null;
		assetParamTypeOpt = AssetTestUtil.getAssetParamFacilityTemplate();
		when(assetParamRepository.getAssetByNameId(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(assetParamTypeOpt);

		assetServiceImpl.updateCondenser(AssetTestUtil.getSaveCondenserTemplate(), Mockito.anyString());
		Assertions.assertEquals(true, b);

	}

	@Test
	public void updateAssetTestPassFacElse() throws ChemtreatException {
		boolean b=true;

		Optional<Tenant> tenantOpt = AssetTestUtil.getOptionalTenantTemplate();
		when(tenantRepository.findById(Mockito.anyInt())).thenReturn(tenantOpt);

		when(assetRepository.findById(Mockito.anyString())).thenReturn(AssetTestUtil.getOptionalAssetTemplate());
		Optional<Asset> assetOpt = Optional.empty();
		when(assetRepository.getAssetByTenantAndAssetNameType(1, ApplicationConstants.ASSET_FACILITY, "Test Station"))
				.thenReturn(assetOpt);

		Optional<Asset> assetOpt2 = AssetTestUtil.getOptionalAssetTemplate();
		when(assetRepository.getAssetByTenantAndAssetNameTypeUnit(1, ApplicationConstants.ASSET_Unit, "System1",
				"eb2c4e2b-f819-480f-9a9e-fcdf991ba47c")).thenReturn(assetOpt2);

		Asset asset = AssetTestUtil.getOptionalAssetTemplate().get();
		when(assetRepository.save(Mockito.any())).thenReturn(asset);
		assetServiceImpl.updateCondenser(AssetTestUtil.getSaveCondenserTemplate(), Mockito.anyString());
		Assertions.assertEquals(true, b);

	}

	@Test
	public void getAssetInfoTestPass() throws ChemtreatException {
		boolean b=true;

		Optional<Asset> assetOpt2 = AssetTestUtil.getOptionalAssetTemplate();
		when(assetRepository.getAssetByTenantAndAssetNameType(Mockito.anyInt(), Mockito.anyString(),
				Mockito.anyString())).thenReturn(assetOpt2);

		List<PlantDataDTO> plantDataDTOList = AssetTestUtil.getPlantDataDTOListTemplate();
		when(assetParamRepository.getAssetParamById(Mockito.anyString())).thenReturn(plantDataDTOList);

		assetServiceImpl.getAssetInfo(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt());
		Assertions.assertEquals(true, b);

	}

	@Test
	public void updateAssetTestPassFacElse2() throws ChemtreatException {
		boolean b=true;

		Optional<Tenant> tenantOpt = AssetTestUtil.getOptionalTenantTemplate();
		when(tenantRepository.findById(Mockito.anyInt())).thenReturn(tenantOpt);

		when(assetRepository.findById(Mockito.anyString())).thenReturn(AssetTestUtil.getOptionalAssetTemplate());
		Optional<Asset> assetOpt = AssetTestUtil.getOptionalAssetTemplate();
		when(assetRepository.getAssetByTenantAndAssetNameType(1, ApplicationConstants.ASSET_FACILITY, "Test Station"))
				.thenReturn(assetOpt);

		Optional<Asset> assetOpt2 = AssetTestUtil.getOptionalAssetTemplate();
		when(assetRepository.getAssetByTenantAndAssetNameTypeUnit(1, ApplicationConstants.ASSET_Unit, "System1",
				"eb2c4e2b-f819-480f-9a9e-fcdf991ba47c")).thenReturn(assetOpt2);

		Optional<AssetParam> assetParamTypeOpt = null;
		assetParamTypeOpt = AssetTestUtil.getAssetParamFacilityTemplate();
		when(assetParamRepository.getAssetByNameId(Mockito.anyString(), Mockito.anyString()))
				.thenReturn(assetParamTypeOpt);

		List<AssetTypeParam> assetTypeParamList = new ArrayList<>();
		assetTypeParamList.add(AssetTestUtil.getAssetTypeParam6());
		when(assetTypeParamRepository.getAllAssetTypeParamOnName(Mockito.anyString())).thenReturn(assetTypeParamList);

		Asset asset = AssetTestUtil.getOptionalAssetTemplate().get();
		when(assetRepository.save(Mockito.any())).thenReturn(asset);
		assetServiceImpl.updateCondenser(AssetTestUtil.getSaveCondenserTemplate(), Mockito.anyString());
		Assertions.assertEquals(true, b);

	}

	@Test
	public void updateAssetTestPassSysElse() throws ChemtreatException {
		boolean b=true;

		Optional<Tenant> tenantOpt = AssetTestUtil.getOptionalTenantTemplate();
		when(tenantRepository.findById(Mockito.anyInt())).thenReturn(tenantOpt);

		Optional<AssetType> assetTypeOpt = null;
		assetTypeOpt = AssetTestUtil.getOptionalAssetTypeParamTemplate();
		when(assetTypeRepository.findByName(Mockito.anyString())).thenReturn(assetTypeOpt);

		when(assetRepository.findById(Mockito.anyString())).thenReturn(AssetTestUtil.getOptionalAssetTemplate());
		Optional<Asset> assetOpt = Optional.empty();
		when(assetRepository.getAssetByTenantAndAssetNameType(1, ApplicationConstants.ASSET_FACILITY, "Test Station"))
				.thenReturn(assetOpt);

		Optional<Asset> assetOpt2 = AssetTestUtil.getOptionalAssetTemplate();
		when(assetRepository.getAssetByTenantAndAssetNameTypeUnit(1, ApplicationConstants.ASSET_Unit, "System1",
				"eb2c4e2b-f819-480f-9a9e-fcdf991ba47c")).thenReturn(assetOpt2);

		List<AssetTypeParam> assetTypeParamList = new ArrayList<>();
		assetTypeParamList.add(AssetTestUtil.getAssetTypeParam6());
		when(assetTypeParamRepository.getAllAssetTypeParamOnName(Mockito.anyString())).thenReturn(assetTypeParamList);

		Asset asset = AssetTestUtil.getOptionalAssetTemplate().get();
		when(assetRepository.save(Mockito.any())).thenReturn(asset);

		assetServiceImpl.updateCondenser(AssetTestUtil.getUpdCondenserTemplate(), Mockito.anyString());
		Assertions.assertEquals(true, b);

	}

	@Test
	public void updateAssetTestFail() throws ChemtreatException {

		Optional<Asset> assetOpt = Optional.empty();
		when(assetRepository.findById(Mockito.anyString())).thenReturn(assetOpt);

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			assetServiceImpl.updateCondenser(AssetTestUtil.getSaveCondenserTemplate(), Mockito.anyString());
		});

		String expectedMessage = ApplicationConstants.ASSET_NOT_PRESENT;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void updateAssetTestFail2() throws ChemtreatException {

		when(assetRepository.findById(Mockito.anyString())).thenReturn(AssetTestUtil.getOptionalAssetTemplate());

		Optional<Tenant> tenantOpt = Optional.empty();
		when(tenantRepository.findById(Mockito.anyInt())).thenReturn(tenantOpt);

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			assetServiceImpl.updateCondenser(AssetTestUtil.getSaveCondenserTemplate(), Mockito.anyString());
		});

		String expectedMessage = ApplicationConstants.ASSET_NO_TENANT_ERR;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	public void updateCondenserTestFail2() throws JsonProcessingException {

		Exception exec = assertThrows(ChemtreatException.class, () -> {
			assetServiceImpl.updateCondenser("{ooo:rr}", "56b31e31-3819-a715-7a00-2f587c6aa3bc");
		});
		exec.getCause();

	}

}
