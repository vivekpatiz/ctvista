package com.danaherdigital.che_hx.time_series.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.danaherdigital.che_hx.time_series.dto.PersistResDTO;
import com.danaherdigital.che_hx.time_series.dto.YAxisReqDTO;
import com.danaherdigital.che_hx.time_series.model.DerivedData;
import com.danaherdigital.che_hx.time_series.repository.AssetDataRepository;
import com.danaherdigital.che_hx.time_series.service.TelemetryService;
import com.danaherdigital.che_hx.time_series.utils.ApplicationConstants;
import com.danaherdigital.che_hx.time_series.utils.AssetDataTestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class TelemetryControllerTest {


private MockMvc mockMvc;

	@InjectMocks
	TelemetryController telemetryController;

	@InjectMocks
	TelemetryController2 telemetryController2;

	@Mock
	TelemetryService telemetryService;

	@Mock
	AssetDataRepository assetDataRepository;


	@InjectMocks
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;


	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(telemetryController)
				.setCustomArgumentResolvers(pageableArgumentResolver)
				.build();
	}


	@Test
    public void getChartsDataTest() throws Exception {
		JSONObject assetDataDTOlist = new JSONObject();
		JSONArray jarry1 = new JSONArray();
		jarry1.put("");
		assetDataDTOlist.put("chart_data", jarry1);
   	 given(telemetryService.getChartsData(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any())).willReturn(assetDataDTOlist);

   	mockMvc.perform(get("/v1/TimeSeries/charts/"+"?fromDate="+ApplicationConstants.CHARTS_FROM_DATE+"&toDate="+ApplicationConstants.CHARTS_TO_DATE+""
   			+ "&tenantId="+ApplicationConstants.CHARTS_TENANT_ID+"&assetId="+ApplicationConstants.CHARTS_ASSET_ID+"&assetParamName="+" "+"&isKpi=&fromLoad=&toLoad=")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

    }

	@Test
    public void getChartsDataNullTest() throws Exception {

   	 given(telemetryService.getChartsData(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any())).willReturn(null);

   	mockMvc.perform(get("/v1/TimeSeries/charts/"+"?fromDate="+ApplicationConstants.CHARTS_FROM_DATE+"&toDate="+ApplicationConstants.CHARTS_TO_DATE+""
   			+ "&tenantId="+ApplicationConstants.CHARTS_TENANT_ID+"&assetId="+ApplicationConstants.CHARTS_ASSET_ID+"&assetParamName="+" "+"&isKpi=&fromLoad=&toLoad=")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());

    }

	@Test
    public void getCharts2DataTest() throws Exception {

		mockMvc = MockMvcBuilders.standaloneSetup(telemetryController2)
				.setCustomArgumentResolvers(pageableArgumentResolver)
				.build();
   	 given(telemetryService.getChartsData2(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any())).willReturn(AssetDataTestUtil.createMockResDTO());

   	mockMvc.perform(get("/v2/TimeSeries/charts/"+"?fromDate="+ApplicationConstants.CHARTS_FROM_DATE+"&toDate="+ApplicationConstants.CHARTS_TO_DATE+""
   			+ "&tenantId="+ApplicationConstants.CHARTS_TENANT_ID+"&assetId="+ApplicationConstants.CHARTS_ASSET_ID+"&assetParamName="+" "+"&isKpi=&fromLoad=&toLoad=")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

	 given(telemetryService.getChartsData2(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any())).willReturn(null);

	   	mockMvc.perform(get("/v2/TimeSeries/charts/"+"?fromDate="+ApplicationConstants.CHARTS_FROM_DATE+"&toDate="+ApplicationConstants.CHARTS_TO_DATE+""
	   			+ "&tenantId="+ApplicationConstants.CHARTS_TENANT_ID+"&assetId="+ApplicationConstants.CHARTS_ASSET_ID+"&assetParamName="+" "+"&isKpi=&fromLoad=&toLoad=")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

    }


	@Test
    public void getLoadDataTest() throws Exception {
		JSONObject assetDataDTOlist = new JSONObject();
   	 given(telemetryService.getChartsData(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any())).willReturn(assetDataDTOlist);

   	mockMvc.perform(get("/v1/TimeSeries/load/"+"?assetId="+ApplicationConstants.CHARTS_ASSET_ID+"&assetParamName="+ApplicationConstants.CHARTS_ASSET_PARAM_ID)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
    }


	@Test
    public void persistDataTest() throws Exception {
		given(telemetryService.persistCalculatedData(Mockito.any(DerivedData.class))).willReturn(new PersistResDTO());
   	mockMvc.perform(post("/v1/TimeSeries/persist")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString((AssetDataTestUtil.createMockDerivedDataDTO())))
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
    }

	@Test
    public void getYaxisChartsDataSuccessTest() throws Exception {
		 given(telemetryService.getYaxisChartsData(Mockito.any(YAxisReqDTO.class)))
		 .willReturn(AssetDataTestUtil.createMockYAxisResponseDTO());
   	mockMvc.perform(post("/v1/TimeSeries/charts/yaxis")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString((AssetDataTestUtil.createMockYAxisReqDTO())))
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
    }

	@Test
    public void getYaxisChartsDataFailedTest2() throws Exception {
		 given(telemetryService.getYaxisChartsData(Mockito.any(YAxisReqDTO.class)))
		 .willReturn(AssetDataTestUtil.createMockYAxisResponseDTO2());
   	mockMvc.perform(post("/v1/TimeSeries/charts/yaxis")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString((AssetDataTestUtil.createMockYAxisReqDTO())))
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
    }

	@Test
    public void getYaxisChartsDataFailedTest() throws Exception {
		 given(telemetryService.getYaxisChartsData(Mockito.any(YAxisReqDTO.class)))
		 .willReturn(null);
   	mockMvc.perform(post("/v1/TimeSeries/charts/yaxis")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new ObjectMapper().writeValueAsString((AssetDataTestUtil.createMockYAxisReqDTO())))
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
    }





}
