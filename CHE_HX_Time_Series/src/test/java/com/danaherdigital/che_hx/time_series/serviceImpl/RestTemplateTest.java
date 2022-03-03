package com.danaherdigital.che_hx.time_series.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.agent.PowerMockAgent;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.danaherdigital.che_hx.time_series.dto.AssetDataDTO;

import com.danaherdigital.che_hx.time_series.dto.AssetLoadDataDTO;

import com.danaherdigital.che_hx.time_series.repository.AssetDataRepository;
import com.danaherdigital.che_hx.time_series.serviceimpl.TelemetryServiceImpl;
import com.danaherdigital.che_hx.time_series.utils.ApplicationConstants;
import com.danaherdigital.che_hx.time_series.utils.AssetDataTestUtil;

//@RunWith(PowerMockRunner.class)
@PrepareForTest({ RestTemplate.class, TelemetryServiceImpl.class, UriComponentsBuilder.class, UriComponents.class })
public class RestTemplateTest {

	@InjectMocks
	TelemetryServiceImpl telemetryServiceImpl;

	@Mock
	AssetDataRepository assetDataRepository;

	@Mock
	RestTemplate restTemplate;

	@Mock
	UriComponentsBuilder uriComponentsBuilder;

	@Mock
	UriComponents uriComponents;

	@Rule
	public PowerMockRule rule = new PowerMockRule();
	static {
		PowerMockAgent.initializeIfNeeded();
	}

	@Test
	public void getChartsDataTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		List<AssetDataDTO> assetDataDTO = new ArrayList<>();
		assetDataDTO.add(AssetDataTestUtil.getAssetDataDTOTemplate()); //
		when(assetDataRepository.getChartsData(new Date().getTime() / 1000, new Date().getTime() / 1000,
				ApplicationConstants.CHARTS_TENANT_ID, ApplicationConstants.CHARTS_ASSET_ID,
				ApplicationConstants.CHARTS_ASSET_PARAM_ID)).thenReturn(assetDataDTO); // //
		when(assetDataRepository.getAssetParamName(ApplicationConstants.CHARTS_ASSET_PARAM_ID))
				.thenReturn(AssetDataTestUtil.createMockAssetParamDTO2());

		List<AssetLoadDataDTO> assetLoadDataDTO = new ArrayList<>();
		assetLoadDataDTO.add(AssetDataTestUtil.getassetLoadDataDTOTemplate()); //
		when(assetDataRepository.getLoadData(new Date().getTime() / 1000, new Date().getTime() / 1000,
				ApplicationConstants.CHARTS_TENANT_ID, ApplicationConstants.CHARTS_ASSET_ID, "percentage_of_full_load",
				0.0, 0.0)).thenReturn(assetLoadDataDTO);
		JSONObject resultObj = telemetryServiceImpl.getChartsData(new Date(), new Date(), 0, 0,
				ApplicationConstants.CHARTS_TENANT_ID, ApplicationConstants.CHARTS_ASSET_ID,
				new ArrayList<String>(Arrays.asList("32a7862f-dba5-11ea-b09d-025041000001")), false);
		assertNotEquals(0, resultObj.length());
		ReflectionTestUtils.setField(telemetryServiceImpl, "assetParamIdURL", "asset.mgmt.assetparam.id.url");
		String[] st = new String[2];
		st[0] = "abc";
		st[1] = "xyz";
		PowerMockito.mockStatic(UriComponentsBuilder.class);
		Mockito.when(UriComponentsBuilder.fromHttpUrl(Mockito.anyString())).thenReturn(uriComponentsBuilder);
		Mockito.when(uriComponentsBuilder.path(Mockito.anyString())).thenReturn(uriComponentsBuilder);
		Mockito.when(uriComponentsBuilder.toUriString())
				.thenReturn("http://localhost:8082/v1/AssetManagement/condenser-chart-kpi/");
		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.<Class<Object>>any(),
				Mockito.<HttpEntity<?>>any())).thenReturn(new ResponseEntity(st, HttpStatus.OK));
		JSONObject resultObj2 = telemetryServiceImpl.getChartsData(new Date(), new Date(), 0, 0,
				ApplicationConstants.CHARTS_TENANT_ID, ApplicationConstants.CHARTS_ASSET_ID,
				new ArrayList<String>(Arrays.asList("32a7862f-dba5-11ea-b09d-025041000001")), true);
		assertNotEquals(0, resultObj2.length());
		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.<Class<Object>>any(),
				Mockito.<HttpEntity<?>>any())).thenReturn(new ResponseEntity(st, HttpStatus.BAD_REQUEST));
		JSONObject resultObj3 = telemetryServiceImpl.getChartsData(new Date(), new Date(), 0, 0,
				ApplicationConstants.CHARTS_TENANT_ID, ApplicationConstants.CHARTS_ASSET_ID,
				new ArrayList<String>(Arrays.asList("32a7862f-dba5-11ea-b09d-025041000001")), true);
		assertEquals(1, resultObj2.length());
		JSONObject resultObj4 = telemetryServiceImpl.getChartsData(new Date(), new Date(), 0, 0,
				ApplicationConstants.CHARTS_TENANT_ID, null,
				new ArrayList<String>(Arrays.asList("32a7862f-dba5-11ea-b09d-025041000001")), false);
		assertEquals(null, resultObj4);

		JSONObject resultObj5 = telemetryServiceImpl.getChartsData(new Date(), new Date(), 0, 0, null,
				ApplicationConstants.CHARTS_ASSET_ID,
				new ArrayList<String>(Arrays.asList("32a7862f-dba5-11ea-b09d-025041000001")), false);
		assertEquals(null, resultObj5);

		JSONObject resultObj6 = telemetryServiceImpl.getChartsData(new Date(), new Date(), 0, 0,
				ApplicationConstants.CHARTS_TENANT_ID, ApplicationConstants.CHARTS_ASSET_ID, null, false);
		assertEquals(null, resultObj6);

		JSONObject resultObj7 = telemetryServiceImpl.getChartsData(new Date(), new Date(), 0, 0,
				ApplicationConstants.CHARTS_TENANT_ID, ApplicationConstants.CHARTS_ASSET_ID, Collections.emptyList(),
				false);
		assertEquals(null, resultObj7);

		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.<Class<Object>>any(),
				Mockito.<HttpEntity<?>>any())).thenReturn(new ResponseEntity(null, HttpStatus.BAD_REQUEST));
		JSONObject resultObj8 = telemetryServiceImpl.getChartsData(new Date(), new Date(), 0, 0,
				ApplicationConstants.CHARTS_TENANT_ID, ApplicationConstants.CHARTS_ASSET_ID, new ArrayList<String>(Arrays.asList("32a7862f-dba5-11ea-b09d-025041000001")),
				true);
		assertEquals(null, resultObj8);

	}

	@Test
	public void getChartsDataInvalidTest() {

		JSONObject resultObj1 = telemetryServiceImpl.getChartsData(new Date(), new Date(), 0, 0,
				ApplicationConstants.CHARTS_TENANT_ID, ApplicationConstants.CHARTS_ASSET_ID,
				new ArrayList<String>(Arrays.asList("32a7862f-dba5-11ea-b09d-025041000001")), false);
		assertNotEquals(0, resultObj1.length());


		JSONObject resultObj2 = telemetryServiceImpl.getChartsData(new Date(), new Date(), 0, 0,
				ApplicationConstants.CHARTS_TENANT_ID, null,
				new ArrayList<String>(Arrays.asList("32a7862f-dba5-11ea-b09d-025041000001")), true);

		assertEquals(null, resultObj2);
	}

	@Test
	public void getChartsDataExceptionTest() {
		ReflectionTestUtils.setField(telemetryServiceImpl, "assetParamIdURL", "asset.mgmt.assetparam.id.url");

		PowerMockito.mockStatic(UriComponentsBuilder.class);
		String[] st = new String[2];
		st[0] = "abc";
		st[1] = "xyz";
		Mockito.when(UriComponentsBuilder.fromHttpUrl(Mockito.anyString())).thenReturn(uriComponentsBuilder);
		Mockito.when(uriComponentsBuilder.path(Mockito.anyString())).thenReturn(uriComponentsBuilder);
		Mockito.when(uriComponentsBuilder.toUriString())
				.thenReturn("http://localhost:8082/v1/AssetManagement/condenser-chart-kpi/");
		Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.any(), Mockito.eq(HttpEntity.class)))
				.thenReturn(new ResponseEntity(st, HttpStatus.OK));
		JSONObject resultObj2 = telemetryServiceImpl.getChartsData(new Date(), new Date(), 0, 0,
				ApplicationConstants.CHARTS_TENANT_ID, ApplicationConstants.CHARTS_ASSET_ID,
				new ArrayList<String>(Arrays.asList("32a7862f-dba5-11ea-b09d-025041000001")), true);

		assertEquals(null, resultObj2);
	}

}
