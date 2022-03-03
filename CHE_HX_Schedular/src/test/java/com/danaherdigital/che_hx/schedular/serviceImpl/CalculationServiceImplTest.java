package com.danaherdigital.che_hx.schedular.serviceImpl;


import java.util.List;

import org.junit.Assert;
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
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.danaherdigital.che_hx.schedular.model.Input;
import com.danaherdigital.che_hx.schedular.serviceimpl.CalculationServiceImpl;
import com.danaherdigital.che_hx.schedular.utils.SchedularTestUtil;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest({ RestTemplate.class, CalculationServiceImpl.class })
public class CalculationServiceImplTest {

	@InjectMocks
	CalculationServiceImpl calculationServiceImpl;
	@Mock
	RestTemplate restTemplate;



	@Test
	public void callLookUpTest() {
		ReflectionTestUtils.setField(calculationServiceImpl, "calEngineUrl", "calculation.engine.url");

		Input inputs = SchedularTestUtil.createMockInput();
		Input inputist[] = new Input[1];
		inputist[0] = inputs;
		Mockito.when(restTemplate.postForEntity(ArgumentMatchers.anyString(), ArgumentMatchers.any(), ArgumentMatchers.any()))
				.thenReturn(new ResponseEntity(inputist, HttpStatus.OK));

		List<Input> assetType_id = calculationServiceImpl
				.getCalculatedData(SchedularTestUtil.createMockCalcEngineDTO());
		Assert.assertNotEquals(0, assetType_id.size());

	}

	@Test
	public void callLookUpTest2() {



		List<Input> assetType_id = calculationServiceImpl
				.getCalculatedData(SchedularTestUtil.createMockCalcEngineDTO());
		Assert.assertEquals(0, assetType_id.size());

	}

}
