package com.danaherdigital.che_hx.schedular.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
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

import com.danaherdigital.che_hx.schedular.dto.UOMReqDTO;
import com.danaherdigital.che_hx.schedular.dto.UOMReqData;
import com.danaherdigital.che_hx.schedular.dto.UOMRespDTO;
import com.danaherdigital.che_hx.schedular.serviceimpl.UOMServiceImpl;
import com.danaherdigital.che_hx.schedular.utils.SchedularTestUtil;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest({ RestTemplate.class, UOMServiceImpl.class })
@TestPropertySource(locations = "classpath:application.properties")
public class UOMServiceImplTest {
	@InjectMocks
	UOMServiceImpl uOMServiceImpl;

	@Mock
	RestTemplate restTemplate;

	@Before
	public void setUp() throws Exception {

		ReflectionTestUtils.setField(uOMServiceImpl, "uomServiceUrl", "uom.service.url");

	}

	@Test
	public void callLookUpTest() {
		UOMReqData req = new UOMReqData();
		req.setFromUom("W");
		req.setToUom("MW");
		req.setValue(12d);
		UOMReqDTO dto = new UOMReqDTO();
		dto.setUomReqList(new ArrayList<UOMReqData>(Arrays.asList(req)));
		Mockito.when(restTemplate.postForEntity(ArgumentMatchers.anyString(), ArgumentMatchers.any(), ArgumentMatchers.any()))
				.thenReturn(new ResponseEntity(SchedularTestUtil.createMockUOMRespDTO(), HttpStatus.OK));

		UOMRespDTO uom = uOMServiceImpl.getUOMConversionData(dto);
		assertNotEquals(null, uom);

	}

	@Test
	public void callLookUpExceptionTest() {
		UOMReqData req = new UOMReqData();
		req.setFromUom("W");
		req.setToUom("MW");
		req.setValue(12d);
		UOMReqDTO dto = new UOMReqDTO();
		dto.setUomReqList(new ArrayList<UOMReqData>(Arrays.asList(req)));

		UOMRespDTO uom = uOMServiceImpl.getUOMConversionData(dto);
		assertEquals(null, uom);

	}

}
