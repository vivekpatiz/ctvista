package com.danaherdigital.che_hx.calcengine.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.danaherdigital.che_hx.calcengine.CheHxCalculationEngineApplication;
import com.danaherdigital.che_hx.calcengine.dto.CalcReqDTO;
import com.danaherdigital.che_hx.calcengine.exceptionhandler.RestExceptionHandler;
import com.danaherdigital.che_hx.calcengine.service.ICalcService;
import com.danaherdigital.che_hx.calcengine.utils.CalcEngineUtils;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {TestContext.class, WebApplicationContext.class})
@WebAppConfiguration
public class CalcControllerTest {
	private MockMvc mockMvc;
	@InjectMocks
	CalcController calcController;
	@Mock
	ICalcService calcService;


	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(calcController)

				//.setCustomArgumentResolvers(pageableArgumentResolver)
				//.setControllerAdvice(new RestExceptionHandler())

				.build();
	}

	@Test
    public void calcEngineApiOkTest() throws Exception {

		given(calcService.getCalcData(Mockito.any())).willReturn(CalcEngineUtils.getTemplateList());

   	 mockMvc.perform(post("/api/v1/runCalculation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString((CalcEngineUtils.getCalcReqDTOTemplate())))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

    }


	@Test
    public void calcEngineApiValidationFailedTest() throws Exception {
		given(calcService.getCalcData(Mockito.any())).willThrow(HttpMessageNotReadableException.class);
		CalcReqDTO c=new CalcReqDTO();

   	 mockMvc.perform(post("/api/v1/runCalculation")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString((c)))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

    }

	/*
	 * @Test public void calcEngineApiValidationFailed2Test() throws Exception {
	 * given(ICalcService.getCalcData(Mockito.any())).willReturn(CalcEngineUtils.
	 * getTemplateList()); CalcReqDTO c=new CalcReqDTO();
	 * c.setFunction("masterCalc");
	 * c.setInput(CalcEngineUtils.getTemplateInvalidInput());
	 * mockMvc.perform(post("/api/v1/runCalculation")
	 * .contentType(MediaType.APPLICATION_JSON) .content(new
	 * ObjectMapper().writeValueAsString((c))) .accept(MediaType.APPLICATION_JSON))
	 * .andExpect(status().isBadRequest());
	 *
	 * }
	 */


}
