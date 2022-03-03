package com.danaherdigital.che_hx.report_management.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.danaherdigital.che_hx.report_management.exceptionhandler.RestExceptionHandler;
import com.danaherdigital.che_hx.report_management.service.DashBoardService;

@RunWith(SpringJUnit4ClassRunner.class)
public class DashBoardControllerTest {

	private MockMvc mockMvc;
	@InjectMocks
	DashBoardController dashBoardController;
	@Mock
	DashBoardService dashBoardService;


	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(dashBoardController)
				//.setCustomArgumentResolvers(pageableArgumentResolver)
				.setControllerAdvice(new RestExceptionHandler())

				.build();
	}

	@Test
    public void getKPIOverviewCardsOkTest() throws Exception {
		when(dashBoardService.getKPIOverviewDesign(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(new HashMap<>());
		 mockMvc.perform(get("/api/v1/dashboard/design").param("asset_id", "6ddb6a59-da6d-4066-9185-c91c95f61d371").param("type", "kpi")
				 .param("asset_type", "condenser").param("tenant_id", "2")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());

	}


}
