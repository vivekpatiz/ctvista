package com.danaherdigital.che_hx.calcengine.service;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import com.danaherdigital.che_hx.calcengine.serviceimpl.ProcessCalc;

@RunWith(MockitoJUnitRunner.class)
public class ProcessCalcTest {

	@InjectMocks
	ProcessCalc processCalc;
	@Mock
	 ICalcService calcService;
	@Test
	public void getDesignCalcDataSuccessTest() throws Exception {

		Map<String,Object> resp=processCalc.call();
		assertNotEquals(null,resp);

	}

}
