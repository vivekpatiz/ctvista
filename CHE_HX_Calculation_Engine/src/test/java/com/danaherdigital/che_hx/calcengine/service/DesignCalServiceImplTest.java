package com.danaherdigital.che_hx.calcengine.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.danaherdigital.che_hx.calcengine.dto.CustomCalcReqDTO;
import com.danaherdigital.che_hx.calcengine.dto.DesignCalRespDTO;
import com.danaherdigital.che_hx.calcengine.dto.LookUpRespDTO;
import com.danaherdigital.che_hx.calcengine.dto.RequestDTO;
import com.danaherdigital.che_hx.calcengine.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.calcengine.serviceimpl.DesignCalServiceImpl;
import com.danaherdigital.che_hx.calcengine.serviceimpl.LookUpServiceImpl;
import com.danaherdigital.che_hx.calcengine.utils.CalcEngineUtils;

@RunWith(MockitoJUnitRunner.class)
public class DesignCalServiceImplTest {

	@InjectMocks
	DesignCalServiceImpl designCalServiceImpl;

	@Mock
	RestTemplate restTemplate;



	@Mock
	LookUpServiceImpl lookUpServiceImpl;

	@Mock
	ILookUpService lookUpService;

	@Before
	public void setUp() throws Exception{

		ReflectionTestUtils.setField(lookUpServiceImpl, "lookUpUrl", "lookupService.url");
		//PowerMockito.mockStatic(QueryApiUtil.class);
		PowerMockito.whenNew(RestTemplate.class).withNoArguments().thenReturn(restTemplate);
		PowerMockito.mockStatic(URLEncoder.class);


		//PowerMockito.mockStatic(UriComponentsBuilder.class);
	}




	@Test
	public void getDesignCalcDataSuccessTest() throws ChemtreatException {
		List<LookUpRespDTO> resp=new ArrayList<>();
		LookUpRespDTO req=new LookUpRespDTO();
		RequestDTO reqDto=new RequestDTO();
		req.setReq(reqDto);
		req.setCalculatedValues(CalcEngineUtils.getLookUpMap());
		//when(assetTypeRepository.save(Mockito.any(AssetType.class))).thenReturn(a);
		when(lookUpService.callLookUp(Mockito.anyList())).thenReturn(CalcEngineUtils.getLookUpMap());
	//	when(ILookUpService.getLookUpData(Mockito.any())).thenReturn(resp);

		DesignCalRespDTO res = designCalServiceImpl.getCalcDesignData(CalcEngineUtils.getDesignCalcReqDTOTemplate());
		assertNotEquals(null,res);



		when(lookUpService.callLookUp(Mockito.anyList())).thenReturn(Collections.emptyMap());
		DesignCalRespDTO res1 = designCalServiceImpl.getCalcDesignData(CalcEngineUtils.getDesignCalcReqDTOTemplate());
		assertNotEquals(null,res1);

	}

	@Test(expected = ChemtreatException.class)
	public void getDesignCalcDataExceptionTest() throws ChemtreatException {

		DesignCalRespDTO res = designCalServiceImpl.getCalcDesignData(null);
		assertNotEquals(null,res);
	}


	@Test
	public void getCustomDesignCalcDataSuccessTest() throws ChemtreatException {
		List<LookUpRespDTO> resp=new ArrayList<>();
		LookUpRespDTO req=new LookUpRespDTO();
		RequestDTO reqDto=new RequestDTO();
		req.setReq(reqDto);
		req.setCalculatedValues(CalcEngineUtils.getLookUpMap());
		//when(assetTypeRepository.save(Mockito.any(AssetType.class))).thenReturn(a);
		when(lookUpService.callLookUp(Mockito.anyList())).thenReturn(CalcEngineUtils.getLookUpMap());
	//	when(ILookUpService.getLookUpData(Mockito.any())).thenReturn(resp);
		CustomCalcReqDTO res =new CustomCalcReqDTO();
		 res = designCalServiceImpl.getCustomCalcDesignDataData(CalcEngineUtils.getCustomCalcReqDTOTemplate());
		assertNotEquals(null,res);

		 res = designCalServiceImpl.getCustomCalcDesignDataData(CalcEngineUtils.getCustomCalcReqDTOTemplate2());
		 assertNotEquals(null,res);


		 when(lookUpService.callLookUp(Mockito.anyList())).thenReturn(Collections.emptyMap());
		 res = designCalServiceImpl.getCustomCalcDesignDataData(CalcEngineUtils.getCustomCalcReqDTOTemplate());
		 assertNotEquals(null,res);



	}

	@Test(expected = ChemtreatException.class)
	public void getCustomCalcDataExceptionTest() throws ChemtreatException {
		CustomCalcReqDTO resp=new CustomCalcReqDTO();
		 resp=designCalServiceImpl.getCustomCalcDesignDataData(null);
		assertEquals(null,resp);






	}

	@Test(expected = ChemtreatException.class)
	public void getCustomCalcDataExceptionTest3() throws ChemtreatException {
		CustomCalcReqDTO resp=new CustomCalcReqDTO();
		when(lookUpService.callLookUp(Mockito.anyList())).thenThrow(NullPointerException.class);
		 resp = designCalServiceImpl.getCustomCalcDesignDataData(CalcEngineUtils.getCustomCalcReqDTOTemplate2());
		 assertNotEquals(null,resp);
	}

	@Test(expected = ChemtreatException.class)
	public void getCustomCalcDataExceptionTest2() throws ChemtreatException {

		CustomCalcReqDTO resp=new CustomCalcReqDTO();
		CustomCalcReqDTO req=new CustomCalcReqDTO();
		req.setInput(null);
		resp=designCalServiceImpl.getCustomCalcDesignDataData(req);
		assertEquals(null,resp);

	}
	@Test(expected = ChemtreatException.class)
	public void getCalcDataExceptionTest() throws ChemtreatException {
		List<LookUpRespDTO> resp=new ArrayList<>();
		LookUpRespDTO req=new LookUpRespDTO();
		RequestDTO reqDto=new RequestDTO();
		req.setReq(reqDto);
		req.setCalculatedValues(CalcEngineUtils.getLookUpMap());
		//when(assetTypeRepository.save(Mockito.any(AssetType.class))).thenReturn(a);
		//when(ILookUpService.callLookUp(Mockito.anyList())).thenReturn(CalcEngineUtils.getLookUpMap());
		//when(ILookUpService.getLookUpData(Mockito.any())).thenReturn(resp);
		designCalServiceImpl.getCalcDesignData(CalcEngineUtils.getInvalidDesignCalcReqDTOTemplate());

	}
}
