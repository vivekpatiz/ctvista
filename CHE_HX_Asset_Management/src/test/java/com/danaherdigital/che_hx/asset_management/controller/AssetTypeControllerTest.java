package com.danaherdigital.che_hx.asset_management.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

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

import com.danaherdigital.che_hx.asset_management.exceptionhandler.ResourceNotFoundException;
import com.danaherdigital.che_hx.asset_management.model.AssetType;
import com.danaherdigital.che_hx.asset_management.respository.AssetTypeRepository;
import com.danaherdigital.che_hx.asset_management.service.IAssetTypeService;
import com.danaherdigital.che_hx.asset_management.utils.AssetTypeTestUtil;
import com.danaherdigital.che_hx.asset_management.utils.JSONConverter;



@RunWith(SpringJUnit4ClassRunner.class)
public class AssetTypeControllerTest {
	private MockMvc mockMvc;
	@InjectMocks
	AssetTypeController assetTypeController;
	@Mock
	IAssetTypeService IAssetTypeService;
	@Mock
	AssetTypeRepository assetTypeRepository;
	@InjectMocks
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(assetTypeController)
				.setCustomArgumentResolvers(pageableArgumentResolver)
				.build();
	}
	
	@Test
    public void createAssetTypeApiOkTest() throws Exception {
		given(IAssetTypeService.saveAssetType(Mockito.any())).willReturn(new String());

   	 mockMvc.perform(post("/v1/AssetManagement/AssetType")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JSONConverter.asJsonString(AssetTypeTestUtil.getAssetTypeTemplate()))
				.accept(MediaType.APPLICATION_JSON))    	 
				.andExpect(status().isCreated());    
   	 
    }
	
	@Test
    public void createAssetTypeThrowInvalidParamExceptionTest() throws Exception {
		given(IAssetTypeService.saveAssetType(Mockito.any())).willThrow(ConstraintViolationException.class);

   	 mockMvc.perform(post("/v1/AssetManagement/AssetType")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JSONConverter.asJsonString(AssetTypeTestUtil.getInvalidAssetTypeTemplate()))
				.accept(MediaType.APPLICATION_JSON))    	 
				.andExpect(status().isBadRequest());    
   	 
    }
	
	/*
	 * @Test public void createAssetTypeThrowDuplicateEntryExceptionTest() throws
	 * Exception {
	 * 
	 * when(assetTypeRepository.findByName(Mockito.anyString())).thenReturn(Optional
	 * .of(AssetTypeTestUtil.getAssetTypeTemplate()));
	 * when(assetTypeRepository.save(Mockito.any())).thenReturn(Optional.of(
	 * AssetTypeTestUtil.getAssetTypeTemplate()));
	 * 
	 * given(IAssetTypeService.saveAssetType(Mockito.any())).willThrow(
	 * ChemtreatException.class);
	 * 
	 * mockMvc.perform(post("/v1/AssetManagement/AssetType")
	 * .contentType(MediaType.APPLICATION_JSON)
	 * .content(JSONConverter.asJsonString(AssetTypeTestUtil.getAssetTypeTemplate())
	 * ) .accept(MediaType.APPLICATION_JSON)) .andExpect(status().isConflict());
	 * 
	 * }
	 */
	
	@Test
    public void getAssetTypeByIdApiOkTest() throws Exception {
		String assetTypeId="e9b1df36-16c0-47ac-bc46-9e991f3959be";
   	 given(IAssetTypeService.getAssetTypeById(Mockito.any())).willReturn(AssetTypeTestUtil.getAssetTypeTemplate());

   	 mockMvc.perform(get("/v1/AssetManagement/AssetType/"+assetTypeId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))    	 
				.andExpect(status().isOk());    
   	 
    }
	
	@Test
    public void getAllAssetTypesApiOkTest() throws Exception {
		List<AssetType> tasks = new ArrayList<>();
		given(IAssetTypeService.getAllAssetTypes()).willReturn(tasks);

   	 mockMvc.perform(get("/v1/AssetManagement/AssetTypes")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))    	 
				.andExpect(status().isOk());    
   	 
    }
	
	@Test
    public void updateCaseApiOkTest() throws Exception {
 		String assetTypeId="e9b1df36-16c0-47ac-bc46-9e991f3959be";
  	 
   	 given(IAssetTypeService.updateAssetType(Mockito.any(), Mockito.any())).willReturn(new String());

 		mockMvc.perform(put("/v1/AssetManagement/AssetType/"+assetTypeId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(JSONConverter.asJsonString(AssetTypeTestUtil.getAssetTypeTemplate()))
				.accept(MediaType.APPLICATION_JSON))    	 
				.andExpect(status().isOk());    
   	 
    }
    
    @Test
    public void deleteCaseApiOkTest() throws Exception {
 		String assetTypeId="e9b1df36-16c0-47ac-bc46-9e991f3959be";
  	 
 		doNothing().doThrow(new ResourceNotFoundException()).when(IAssetTypeService).deleteAssetType(Mockito.any());

 		mockMvc.perform(delete("/v1/AssetManagement/AssetType/"+assetTypeId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))    	 
				.andExpect(status().isOk());    
   	 
    }

}
