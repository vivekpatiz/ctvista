package com.danaherdigital.che_hx.calcengine.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import static org.mockito.Mockito.when;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import org.apache.commons.collections4.map.HashedMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import com.danaherdigital.che_hx.calcengine.dto.CalcReqDTO;
import com.danaherdigital.che_hx.calcengine.dto.LookUpRespDTO;
import com.danaherdigital.che_hx.calcengine.dto.RequestDTO;
import com.danaherdigital.che_hx.calcengine.serviceimpl.CalcServiceImpl;
import com.danaherdigital.che_hx.calcengine.serviceimpl.LookUpServiceImpl;
import com.danaherdigital.che_hx.calcengine.utils.CalcEngineUtils;

@RunWith(MockitoJUnitRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
public class CalcServiceImplTest {

	@InjectMocks
	CalcServiceImpl calcServiceImpl;

	@Mock
	LookUpServiceImpl lookUpServiceImpl;

	@Mock
	ILookUpService lookUpService;

	@Mock
	RestTemplate restTemplate;


	private CalcReqDTO s;

	@Before
	public void setUp() throws Exception{
	 this.s=CalcEngineUtils.getCalcReqDTOTemplate();

     MockitoAnnotations.initMocks(this);


	}

	@Test
	public void getCalcDataSuccessTest() throws Exception {
  	List<LookUpRespDTO> resp=new ArrayList<>();
		LookUpRespDTO req=new LookUpRespDTO();
		RequestDTO reqDto=new RequestDTO();
		req.setReq(reqDto);
		req.setCalculatedValues(CalcEngineUtils.getLookUpMap());

		List<?> assetType_id = calcServiceImpl.getCalcData(s);
		assertEquals(true,assetType_id.isEmpty() );

	}


	@Test(expected = InvalidParameterException.class)
	public void getCalcDataExceptionTest() {
		Map<String,Object> map=new HashedMap<>();
		List<Object> resp=new ArrayList<>();
		map.put("abc", "abc");
		CalcReqDTO c=new CalcReqDTO();
		c.setFunction(null);
		c.setInput(new ArrayList<>(Arrays.asList(map)));
		//when(assetTypeRepository.save(Mockito.any(AssetType.class))).thenReturn(a);
		resp=calcServiceImpl.getCalcData(c);
		assertEquals(0,resp.size() );

			}
	@Test(expected = InvalidParameterException.class)
	public void getCalcDataExceptionTest2() {
		List<Object> resp=new ArrayList<>();

		CalcReqDTO c=new CalcReqDTO();
		 c.setFunction("masterCalc");
		 c.setInput(Collections.emptyList());
		 resp=calcServiceImpl.getCalcData(c);
				assertEquals(0,resp.size() );

	}
	@Test
	public void getCalcDataTest() {
		List<Object> resp=new ArrayList<>();
		Map<String,Object> map=new HashedMap<>();

		CalcReqDTO c=new CalcReqDTO();
		 c.setFunction("dummy");
		 c.setInput(new ArrayList<>(Arrays.asList(map)));
		 resp=calcServiceImpl.getCalcData(c);
				assertEquals(0,resp.size() );

				map.put("abc", "abc")	;
				c.setFunction("masterCalc");
				 c.setInput(new ArrayList<>(Arrays.asList(map)));

				resp=calcServiceImpl.getCalcData(c);
				assertEquals(0,resp.size() );



	}

	@Test
	public void getCalcDataSuccessTest2() throws Exception {
	when(lookUpService.callLookUp(Mockito.anyListOf(RequestDTO.class))).thenReturn(CalcEngineUtils.getLookUpMap());
	Map<String,Object> map=	calcServiceImpl.calcProcess(CalcEngineUtils.getTemplateInput().get(0));
		assertNotEquals(0,map.size() );

	}

	@Test
	public void getCalcDataExceptionTest3() {
		when(lookUpService.callLookUp(Mockito.anyListOf(RequestDTO.class))).thenThrow(NullPointerException.class);
		Map<String,Object> map=	calcServiceImpl.calcProcess(CalcEngineUtils.getTemplateInput().get(0));
		assertEquals(0,map.size() );

	}

}
