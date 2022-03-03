package com.danaherdigital.che_hx.report_management.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.danaherdigital.che_hx.report_management.dto.UserPreferenceDTO;
import com.danaherdigital.che_hx.report_management.service.UserPreferenceService;
import com.danaherdigital.che_hx.report_management.utils.JSONConverter;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserPreferencesControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	UserPreferenceController userPreferenceController;

	@InjectMocks
	private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

	@Mock
	UserPreferenceService userPreferenceService;


	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(userPreferenceController).setCustomArgumentResolvers(pageableArgumentResolver)
				.build();
	}

	
	
	
	
	@Test
    public void getUserPreferenceByUserNameOKTest() throws Exception {
	 		String assetID = "e88aba22-6772-4718-a9b3-61f84ac0c148";
	 		String userName = "Kevin";

		UserPreferenceDTO userPreferenceDTO = new  UserPreferenceDTO();
		//Page<Report> ChartsConfigDTO = new PageImpl<>(reportList);
   	 given(userPreferenceService.getUserPreferenceById(assetID, userName)).willReturn(userPreferenceDTO);
   	mockMvc.perform(get("/api/v1/UserPreference/preference/"+assetID+"/"+userName)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))    	 
			.andExpect(status().isOk());
   	 
    }
	
	
	@Test
    public void createReportApiOkTest() throws Exception {
		UserPreferenceDTO userPreferenceDTO = new UserPreferenceDTO("12312", 22.33, 44.55, new Date(), new Date(), "1232", "333",  new HashMap<>(), 2,"Kevin");
		
   	 mockMvc.perform(post("/api/v1/UserPreference/preference")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JSONConverter.asJsonString(userPreferenceDTO))
				.accept(MediaType.APPLICATION_JSON))    	 
				.andExpect(status().isCreated());    
   	 
    }
	
	
}
