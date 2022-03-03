package com.danaherdigital.che_hx.calcengine.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.InvalidParameterException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.danaherdigital.che_hx.calcengine.dto.CustomCalcReqDTO;
import com.danaherdigital.che_hx.calcengine.dto.DesignCalRespDTO;
import com.danaherdigital.che_hx.calcengine.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.calcengine.exceptionhandler.RestExceptionHandler;
import com.danaherdigital.che_hx.calcengine.service.ICalcService;
import com.danaherdigital.che_hx.calcengine.service.IDesignCalService;
import com.danaherdigital.che_hx.calcengine.utils.CalcEngineUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class DesignCalControllerTest {

	private MockMvc mockMvc;
	@InjectMocks
	DesignCalController DesignCalController;
	@Mock
	IDesignCalService designCalService;


	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(DesignCalController)
				//.setCustomArgumentResolvers(pageableArgumentResolver)
				.setControllerAdvice(new RestExceptionHandler())

				.build();
	}

	@Test
    public void designcalcApiOkTest() throws Exception {
DesignCalRespDTO resp=new DesignCalRespDTO();
		given(designCalService.getCalcDesignData(Mockito.any())).willReturn(resp);

   	 mockMvc.perform(post("/api/v1/runDesignCalculation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString((CalcEngineUtils.getDesignCalcReqDTOTemplate())))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

    }


	@Test
    public void designcalcApiExceptionTest() throws Exception {
DesignCalRespDTO resp=new DesignCalRespDTO();
		given(designCalService.getCalcDesignData(Mockito.any())).willThrow(ChemtreatException.class);

   	 mockMvc.perform(post("/api/v1/runDesignCalculation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString((CalcEngineUtils.getInvalidDesignCalcReqDTOTemplate())))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

    }

	@Test
    public void designcalcApiException2Test() throws Exception {
DesignCalRespDTO resp=new DesignCalRespDTO();
		given(designCalService.getCalcDesignData(Mockito.any())).willThrow(HttpMessageNotReadableException.class);

   	 mockMvc.perform(post("/api/v1/runDesignCalculation")
				.contentType(MediaType.APPLICATION_JSON)
				.content("")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

    }

	
	@Test
    public void designCustomcalcApiOkTest() throws Exception {
		CustomCalcReqDTO customResDTO =new CustomCalcReqDTO();
		given(designCalService.getCustomCalcDesignDataData(Mockito.any())).willReturn(customResDTO);

   	 mockMvc.perform(post("/api/v1/runCustomDesignCalculation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString((CalcEngineUtils.getCustomCalcReqDTOTemplate())))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

    }


	@Test
    public void designCustomcalcApiExceptionTest() throws Exception {
		given(designCalService.getCustomCalcDesignDataData(Mockito.any())).willThrow(ChemtreatException.class);

   	 mockMvc.perform(post("/api/v1/runCustomDesignCalculation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString((CalcEngineUtils.getInvalidDesignCalcReqDTOTemplate())))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

    }

	@Test
    public void designCustomcalcApiException2Test() throws Exception {
		given(designCalService.getCustomCalcDesignDataData(Mockito.any())).willThrow(HttpMessageNotReadableException.class);

   	 mockMvc.perform(post("/api/v1/runCustomDesignCalculation")
				.contentType(MediaType.APPLICATION_JSON)
				.content("")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

    }




}
