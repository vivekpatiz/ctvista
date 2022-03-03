package com.danaherdigital.che_hx.uom.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.danaherdigital.che_hx.uom.dto.UOMRespDTO;
import com.danaherdigital.che_hx.uom.dto.UOMdataDTO;
import com.danaherdigital.che_hx.uom.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.uom.exceptionhandler.RestExceptionHandler;
import com.danaherdigital.che_hx.uom.service.IUnitOfMeasureService;
import com.danaherdigital.che_hx.uom.utils.UOMTestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class UnitOfMeasureControllerTest {

	private MockMvc mockMvc;
	@InjectMocks
	UnitOfMeasureController UnitOfMeasureController;
	@Mock
	IUnitOfMeasureService unitOfMeasureService;


	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(UnitOfMeasureController)
				//.setCustomArgumentResolvers(pageableArgumentResolver)
				.setControllerAdvice(new RestExceptionHandler())

				.build();
	}

	@Test
    public void designcalcApiOkTest() throws Exception {
			UOMRespDTO resp=new UOMRespDTO();
		given(unitOfMeasureService.getUOMConversionData(Mockito.any())).willReturn(resp);

   	 mockMvc.perform(post("/UOM/api/v1/runUOMConversion")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString((UOMTestUtils.getReqTemplate())))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

    }


	@Test
    public void designcalcApiExceptionTest() throws Exception {
		UOMRespDTO resp=new UOMRespDTO();
		given(unitOfMeasureService.getUOMConversionData(Mockito.any())).willThrow(ChemtreatException.class);

   	 mockMvc.perform(post("/UOM/api/v1/runUOMConversion")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString((UOMTestUtils.getInvalidReqTemplate())))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

    }


	@Test
    public void getListOfUOMOKTEST() throws Exception {
		List<UOMdataDTO> uomDataDTO = new ArrayList<UOMdataDTO>();
		given(unitOfMeasureService.getUOMList()).willReturn(uomDataDTO);

   	 mockMvc.perform(get("/UOM/api/v1/uom")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

    }







}
