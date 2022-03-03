package com.danaherdigital.che_hx.asset_management.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.danaherdigital.che_hx.asset_management.SimpleCorsFilter;
import com.danaherdigital.che_hx.asset_management.dto.AssetDTO;
import com.danaherdigital.che_hx.asset_management.dto.AssetDTOLoadInt;
import com.danaherdigital.che_hx.asset_management.dto.ChartsConfigDTO;
import com.danaherdigital.che_hx.asset_management.exceptionhandler.ResourceNotFoundException;
import com.danaherdigital.che_hx.asset_management.respository.AssetRepository;
import com.danaherdigital.che_hx.asset_management.service.IAssetService;
import com.danaherdigital.che_hx.asset_management.utils.ApplicationConstants;
import com.danaherdigital.che_hx.asset_management.utils.JSONConverter;


@RunWith(SpringJUnit4ClassRunner.class)
public class AssetControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	AssetController assetController;

	@Mock
	IAssetService IAssetService;

	@Mock
	AssetRepository assetRepository;

	@InjectMocks
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;


	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(assetController)
				.setCustomArgumentResolvers(pageableArgumentResolver)
				.build();
	}


	@Test
    public void deleteAssetApiOkTest() throws Exception {
 		String assetId="e9b1df36-16c0-47ac-bc46-9e991f3959be";

 		doNothing().doThrow(new ResourceNotFoundException()).when(IAssetService).deleteAsset(Mockito.any());
  		mockMvc.perform(delete("/v1/AssetManagement/asset/"+assetId).header("Origin","*")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

    }


	@Test
    public void createAssetApiOkTest() throws Exception {


   	 mockMvc.perform(post("/v1/AssetManagement/asset")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JSONConverter.asJsonString(""))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

    }

	@Test
    public void updateAssetApiOkTest() throws Exception {
		String assetId="e9b1df36-16c0-47ac-bc46-9e991f3959be";

   	 mockMvc.perform(put("/v1/AssetManagement/asset/"+assetId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(JSONConverter.asJsonString(""))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

    }

	@Test
    public void getAllAssetByTenantIdAndTypeApiOkTest() throws Exception {
		List<AssetDTO> assetDTOlist = new ArrayList<>();
   	 given(IAssetService.getAllAssetByTenantAndType(Mockito.any(),Mockito.any())).willReturn(assetDTOlist);

   	mockMvc.perform(get("/v1/AssetManagement/assets/1/"+ApplicationConstants.ASSET_TYPE_CONDENSER)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

    }



	@Test
    public void getgetCondenserLoadByIdOkTest() throws Exception {
		String assetId="e9b1df36-16c0-47ac-bc46-9e991f3959be";
		AssetDTOLoadInt assetDTOLoadInt = new AssetDTOLoadInt();
   	 given(IAssetService.getCondenserLoadById(assetId)).willReturn(assetDTOLoadInt);

   	mockMvc.perform(get("/v1/AssetManagement/condenser-load/"+assetId)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

    }


	@Test
    public void getCondenserChartValuesOkTest() throws Exception {
		String assetId="e9b1df36-16c0-47ac-bc46-9e991f3959be";
		List<ChartsConfigDTO> ChartsConfigDTO = new ArrayList<>();
   	 given(IAssetService.getCondenserChartValues(assetId,true)).willReturn(ChartsConfigDTO);

   	mockMvc.perform(get("/v1/AssetManagement/condenser-chart-key/"+assetId+"?isKpi=true")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

    }


	@Test
    public void getKPIChartValuesOkTest() throws Exception {
		String assetId="e9b1df36-16c0-47ac-bc46-9e991f3959be";
		List<String> kpiList = new ArrayList<>();
   	 given(IAssetService.getKPIChartValues(assetId)).willReturn(kpiList);

   	mockMvc.perform(get("/v1/AssetManagement/condenser-chart-kpi/"+assetId)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

    }


	@Test
    public void getCondenserApiOkTest() throws Exception {
		JSONObject jobj = new JSONObject();
   	 given(IAssetService.getAssetById(Mockito.any())).willReturn(jobj);

   	mockMvc.perform(get("/v1/AssetManagement/condenser-asset/"+ApplicationConstants.ASSET_CONDENSER_ID)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

    }


	@Test
    public void getAssetInfoOkTest() throws Exception {
		JSONObject jobj = new JSONObject();
   	 given(IAssetService.getAssetInfo(Mockito.anyString(),Mockito.anyString(),Mockito.anyInt())).willReturn(jobj);

   	mockMvc.perform(get("/v1/AssetManagement/asset-data?name=System7&type=Unit&tenantId=1")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

    }
	
	
	@Test
    public void getAssetInflowLogApiOkTest() throws Exception {
		JSONObject jobj = new JSONObject();
   	 given(IAssetService.getAssetById(Mockito.any())).willReturn(jobj);

   	mockMvc.perform(get("/v1/AssetManagement/inflow-log/2?size=2")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

    }

}
