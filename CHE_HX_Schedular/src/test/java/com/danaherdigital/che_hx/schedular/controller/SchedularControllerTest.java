package com.danaherdigital.che_hx.schedular.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.danaherdigital.che_hx.schedular.exceptionhandler.ResourceNotFoundException;
import com.danaherdigital.che_hx.schedular.exceptionhandler.RestExceptionHandler;
import com.danaherdigital.che_hx.schedular.service.ICalcStatusService;
import com.danaherdigital.che_hx.schedular.utils.SchedularTestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
public class SchedularControllerTest {

	private MockMvc mockMvc;
	@InjectMocks
	SchedularController schedularController;
	@Mock
	ICalcStatusService calcStatusService;

	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(schedularController)
				//.setCustomArgumentResolvers(pageableArgumentResolver)
				.setControllerAdvice(new RestExceptionHandler())

				.build();
	}

	@Test
    public void getAssetLogStatusApiOkTest() throws Exception {
		given(calcStatusService.getList(ArgumentMatchers.any())).willReturn(SchedularTestUtil.createMockAssetDataLogList());

   	 mockMvc.perform(get("/api/v1/Schedular/getAssetLogStatus/"+2)
				.contentType(MediaType.APPLICATION_JSON)
				//.content(new ObjectMapper().writeValueAsString((UOMTestUtils.getReqTemplate())))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

    }


	@Test
    public void getAssetLogStatusApiFailedTest() throws Exception {
		given(calcStatusService.getList(ArgumentMatchers.any())).willThrow(ResourceNotFoundException.class);

   	 mockMvc.perform(get("/api/v1/Schedular/getAssetLogStatus/"+5)
				.contentType(MediaType.APPLICATION_JSON)
				//.content(new ObjectMapper().writeValueAsString((UOMTestUtils.getReqTemplate())))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

    }


}
