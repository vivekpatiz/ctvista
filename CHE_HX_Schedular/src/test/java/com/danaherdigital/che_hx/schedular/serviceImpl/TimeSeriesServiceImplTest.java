package com.danaherdigital.che_hx.schedular.serviceImpl;



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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.danaherdigital.che_hx.schedular.dto.PersistResDTO;
import com.danaherdigital.che_hx.schedular.serviceimpl.TimeSeriesServiceImpl;
import com.danaherdigital.che_hx.schedular.utils.SchedularTestUtil;



@RunWith(MockitoJUnitRunner.class)
@PrepareForTest({ RestTemplate.class, TimeSeriesServiceImpl.class })
@TestPropertySource(locations = "classpath:application.properties")
public class TimeSeriesServiceImplTest {

	@InjectMocks
	TimeSeriesServiceImpl timeSeriesServiceImpl;

	@Mock
	RestTemplate restTemplate;






	@Test
	public void timeSeriesSuccessTest() throws Exception {
		ReflectionTestUtils.setField(timeSeriesServiceImpl, "timeSeriesUrl", "timeseries.service.url");

		//HttpStatus status=HttpStatus.OK ;

		Mockito.when(restTemplate.postForEntity(ArgumentMatchers.any(String.class), ArgumentMatchers.any(), ArgumentMatchers.any()))
				.thenReturn(new ResponseEntity(new PersistResDTO(), HttpStatus.OK));

		PersistResDTO resp = timeSeriesServiceImpl
				.persistTimeSeriesData(SchedularTestUtil.createMockTimeSeriesReqDTO());
		Assert.assertNotEquals(null, resp);

	}
	@Test
	public void timeSeriesExceptionTest() throws Exception {




		PersistResDTO resp  = timeSeriesServiceImpl
				.persistTimeSeriesData(SchedularTestUtil.createMockTimeSeriesReqDTO());
		Assert.assertEquals(null, resp);

	}




}
