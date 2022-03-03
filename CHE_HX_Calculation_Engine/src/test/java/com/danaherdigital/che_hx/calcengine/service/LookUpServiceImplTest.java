package com.danaherdigital.che_hx.calcengine.service;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.danaherdigital.che_hx.calcengine.dto.LookUpRespDTO;
import com.danaherdigital.che_hx.calcengine.dto.RequestDTO;
import com.danaherdigital.che_hx.calcengine.serviceimpl.LookUpServiceImpl;
import com.danaherdigital.che_hx.calcengine.utils.CalcEngineUtils;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest({RestTemplate.class,LookUpServiceImpl.class})
@TestPropertySource(locations = "classpath:application.properties")
public class LookUpServiceImplTest {

	@InjectMocks
	LookUpServiceImpl lookUpServiceImpl;



	@Mock
	RestTemplate restTemplate;





	@Before
	public void setUp() throws Exception{

		ReflectionTestUtils.setField(lookUpServiceImpl, "lookUpUrl", "lookupService.url");


	}

	@Test
	public void callLookUpTest() throws UnsupportedEncodingException {
		LookUpRespDTO resp=CalcEngineUtils.createMockRespDTO();
		LookUpRespDTO[] list=new LookUpRespDTO[1];
		list[0]=resp;
	//Mockito.when(restTemplate.exchange(Mockito.any(URI.class), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class))).thenReturn(responseEntity);
		//when(ILookUpService.getLookUpData(Mockito.any())).thenReturn(resp);
		Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.any(), Mockito.any()))
		.thenReturn(new ResponseEntity(list, HttpStatus.OK));
		Map<String, Double> response = lookUpServiceImpl.callLookUp(CalcEngineUtils.getLookUpReqList());
		assertEquals(false,response.isEmpty() );


		Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.any(), Mockito.any()))
		.thenReturn(new ResponseEntity(list, HttpStatus.INTERNAL_SERVER_ERROR));
		Map<String, Double> response2 = lookUpServiceImpl.callLookUp(CalcEngineUtils.getLookUpReqList());
		assertEquals(true,response2.isEmpty() );
	}

	@Test
	public void callLookExceptiomUpTest() throws UnsupportedEncodingException {

	//Mockito.when(restTemplate.exchange(Mockito.any(URI.class), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class))).thenReturn(responseEntity);
		//when(ILookUpService.getLookUpData(Mockito.any())).thenReturn(resp);
		Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.any(), Mockito.any()))
		.thenReturn(new ResponseEntity(CalcEngineUtils.getLookupRespList().toArray(), HttpStatus.OK));
		Map<String, Double> assetType_id = lookUpServiceImpl.callLookUp(CalcEngineUtils.getLookUpReqList());
		assertEquals(true,assetType_id.isEmpty() );

	}

	@Test
	public void callLookUpTest3() throws UnsupportedEncodingException {
		List<RequestDTO> o=new ArrayList<>();

		Map<String, Double> assetType_id = lookUpServiceImpl.callLookUp(Collections.emptyList());
		assertEquals(true,assetType_id.isEmpty() );

		Map<String, Double> assetType_id2 = lookUpServiceImpl.callLookUp(o);
		assertEquals(true,assetType_id2.isEmpty() );

	}
}
