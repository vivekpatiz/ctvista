package com.danaherdigital.che_hx.uom.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.danaherdigital.che_hx.uom.dto.ResponseDTO;
import com.danaherdigital.che_hx.uom.exceptionhandler.RestExceptionHandler;
import com.danaherdigital.che_hx.uom.service.ILookUpService;
import com.danaherdigital.che_hx.uom.utils.LookUpTestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class LookUpControllerTest {

	private MockMvc mockMvc;
	@InjectMocks
	LookUpController LookUpController;
	@Mock
	ILookUpService lookUpService;


	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(LookUpController)
				//.setCustomArgumentResolvers(pageableArgumentResolver)
				.setControllerAdvice(new RestExceptionHandler())

				.build();
	}

	@Test
	public void lookUpApiOkTest() throws Exception {
		List<ResponseDTO> resp = new ArrayList<>();
		given(lookUpService.getLookUpData(Mockito.any())).willReturn(resp);

		mockMvc.perform(post("/UOM/Lookup/api/v1/runLookUp").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString((LookUpTestUtil.getLookUpReq())))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}







}
