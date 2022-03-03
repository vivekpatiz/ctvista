package com.danaherdigital.che_hx.asset_management.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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

import com.danaherdigital.che_hx.asset_management.dto.AssetDTO;
import com.danaherdigital.che_hx.asset_management.respository.AssetRepository;
import com.danaherdigital.che_hx.asset_management.service.IAssetService;
import com.danaherdigital.che_hx.asset_management.utils.ApplicationConstants;

@RunWith(SpringJUnit4ClassRunner.class)
public class AssetControllerV2Test {

	private MockMvc mockMvc;
	
	@InjectMocks
	AssetControllerV2 assetController;
	
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
    public void getAllAssetByTenantIdAndTypeApiOkTest() throws Exception {
		List<AssetDTO> assetDTOlist = new ArrayList<>();
   	 given(IAssetService.getAllAssetByTenantAndType(Mockito.any(),Mockito.any())).willReturn(assetDTOlist);
   	 		
   	mockMvc.perform(get("/v2/AssetManagement/assets/1/"+ApplicationConstants.ASSET_TYPE_CONDENSER)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))    	 
			.andExpect(status().isOk());
   	 
    }
}
