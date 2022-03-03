
package com.danaherdigital.che_hx.time_series.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.danaherdigital.che_hx.time_series.dto.AssetDataDTO;
import com.danaherdigital.che_hx.time_series.dto.AssetLoadDataDTO;
import com.danaherdigital.che_hx.time_series.dto.ResDTO;

import com.danaherdigital.che_hx.time_series.repository.AssetDataRepository;
import com.danaherdigital.che_hx.time_series.repository.AssetDataTimeSeriesRepository;
import com.danaherdigital.che_hx.time_series.repository.AssetParamRepository;
import com.danaherdigital.che_hx.time_series.repository.CalculationConfigMasterRepository;
import com.danaherdigital.che_hx.time_series.serviceimpl.TelemetryServiceImpl;
import com.danaherdigital.che_hx.time_series.utils.ApplicationConstants;
import com.danaherdigital.che_hx.time_series.utils.AssetDataTestUtil;

@PrepareForTest({ TelemetryServiceImpl.class, RestTemplate.class, UriComponentsBuilder.class, UriComponents.class,
		URI.class })
@TestPropertySource(locations = "classpath:application.properties")
public class AssetDataServiceImplTest {

	@InjectMocks
	TelemetryServiceImpl telemetryServiceImpl;

	@Mock
	AssetDataRepository assetDataRepository;
	@Mock
	RestTemplate restTemplate;
	@Mock
	CalculationConfigMasterRepository calculationConfigMasterRepository;
	@Mock
	AssetDataTimeSeriesRepository assetDataTimeSeriesRepository;
	@Mock
	UriComponentsBuilder uriComponentsBuilder;
	@Mock
	AssetParamRepository assetParamRepository;

	@Mock
	private EntityManagerFactory emf;
	@Mock
	private SessionFactory mockedSessionFactory;
	@Mock
	UriComponents uriComponents;

	@Mock
	URI uri;
	@Mock
	private Session session;

	@Mock
	private EntityTransaction mockedTransaction;

	@Mock
	private EntityManager em;
	@Rule
	   public PowerMockRule rule = new PowerMockRule();



	@Test
	public void getChartsData2Test() throws Exception {
		ReflectionTestUtils.setField(telemetryServiceImpl, "assetParamIdURL", "asset.mgmt.assetparam.id.url");
		PowerMockito.mockStatic(UriComponentsBuilder.class);
		Mockito.when(UriComponentsBuilder.fromHttpUrl(Mockito.anyString())).thenReturn(uriComponentsBuilder);
		Mockito.when(uriComponentsBuilder.path(Mockito.anyString())).thenReturn(uriComponentsBuilder);
		Mockito.when(uriComponentsBuilder.build(Mockito.anyBoolean())).thenReturn(uriComponents);
		Mockito.when(uriComponents.toUri()).thenReturn(uri);

		String[] st = new String[2];
		st[0] = "abc";
		st[1] = "xyz";
		Mockito.when(restTemplate.exchange(Mockito.any(URI.class), Mockito.any(HttpMethod.class),
				Mockito.any(HttpEntity.class), Mockito.eq(String[].class)))
				.thenReturn(new ResponseEntity(st, HttpStatus.OK));
		List<AssetDataDTO> assetDataDTO = new ArrayList<>();
		assetDataDTO.add(AssetDataTestUtil.getAssetDataDTOTemplate()); //
		when(assetDataRepository.getChartsData(new Date().getTime() / 1000, new Date().getTime() / 1000,
				ApplicationConstants.CHARTS_TENANT_ID, ApplicationConstants.CHARTS_ASSET_ID,
				ApplicationConstants.CHARTS_ASSET_PARAM_ID)).thenReturn(assetDataDTO); // //
		when(assetDataRepository.getAssetParamName(ApplicationConstants.CHARTS_ASSET_PARAM_ID))
				.thenReturn(AssetDataTestUtil.createMockAssetParamDTO2());

		List<AssetLoadDataDTO> assetLoadDataDTO = new ArrayList<>(
				Arrays.asList(AssetDataTestUtil.getassetLoadDataDTOTemplate()));
		when(assetDataRepository.getLoadData(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyInt(),
				Mockito.anyString(), Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble()))
						.thenReturn(assetLoadDataDTO);
		ResDTO resultObj = telemetryServiceImpl.getChartsData2(new Date(), new Date(), 0, 0,
				ApplicationConstants.CHARTS_TENANT_ID, ApplicationConstants.CHARTS_ASSET_ID,
				new ArrayList<String>(Arrays.asList("32a7862f-dba5-11ea-b09d-025041000001")), true);
		assertEquals(0, resultObj.getChartData().size());

		ResDTO resultObj2 = telemetryServiceImpl.getChartsData2(new Date(), new Date(), 0, 0,
				ApplicationConstants.CHARTS_TENANT_ID, ApplicationConstants.CHARTS_ASSET_ID,
				new ArrayList<String>(Arrays.asList("32a7862f-dba5-11ea-b09d-025041000001")), false);
		assertEquals(0, resultObj2.getChartData().size());
		ResDTO resultObj3 = telemetryServiceImpl.getChartsData2(new Date(), new Date(), 0, 0,
				ApplicationConstants.CHARTS_TENANT_ID,null,
				new ArrayList<String>(Arrays.asList("32a7862f-dba5-11ea-b09d-025041000001")), true);
		assertEquals(null, resultObj3);

		Mockito.when(restTemplate.exchange(Mockito.any(URI.class), Mockito.any(HttpMethod.class),
				Mockito.any(HttpEntity.class), Mockito.eq(String[].class)))
				.thenReturn(new ResponseEntity(null, HttpStatus.BAD_REQUEST));
		ResDTO resultObj4 = telemetryServiceImpl.getChartsData2(new Date(), new Date(), 0, 0,
				ApplicationConstants.CHARTS_TENANT_ID, ApplicationConstants.CHARTS_ASSET_ID,
				new ArrayList<String>(Arrays.asList("32a7862f-dba5-11ea-b09d-025041000001")), true);
		assertEquals(null, resultObj4);

		ResDTO resultObj5 = telemetryServiceImpl.getChartsData2(new Date(), new Date(), 0, 0,
				null, ApplicationConstants.CHARTS_ASSET_ID,
				null, false);
		assertEquals(null, resultObj5);

		ResDTO resultObj6 = telemetryServiceImpl.getChartsData2(new Date(), new Date(), 0, 0,
				ApplicationConstants.CHARTS_TENANT_ID, null,
				new ArrayList<String>(Arrays.asList("32a7862f-dba5-11ea-b09d-025041000001")), false);
		assertEquals(null, resultObj6);

		ResDTO resultObj7 = telemetryServiceImpl.getChartsData2(new Date(), new Date(), 0, 0,
				ApplicationConstants.CHARTS_TENANT_ID, ApplicationConstants.CHARTS_ASSET_ID,
				Collections.emptyList(), false);
		assertEquals(null, resultObj7);

		when(assetDataRepository.getLoadData(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyInt(),
				Mockito.anyString(), Mockito.anyString(), Mockito.anyDouble(), Mockito.anyDouble()))
						.thenReturn(Collections.emptyList());

		ResDTO resultObj8 = telemetryServiceImpl.getChartsData2(new Date(), new Date(), 0, 0,
				ApplicationConstants.CHARTS_TENANT_ID, ApplicationConstants.CHARTS_ASSET_ID,
				new ArrayList<String>(Arrays.asList("32a7862f-dba5-11ea-b09d-025041000001")), false);
		assertNotEquals(null, resultObj8);

	}


}
