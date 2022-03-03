package com.danaherdigital.che_hx.report_management.serviceImpl;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.danaherdigital.che_hx.report_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.report_management.repository.DashboardRepository;
import com.danaherdigital.che_hx.report_management.serviceimpl.DashBoardServiceImpl;
import com.danaherdigital.che_hx.report_management.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class DashBoardServiceImplTest {

	@InjectMocks
	DashBoardServiceImpl dashBoardServiceImpl;
	@Mock
	DashboardRepository dashboardRepository;

	@Test
	public void getKPIOverviewDesignSuccessTest() throws ChemtreatException
	{

		when(dashboardRepository.findDesign(ArgumentMatchers.any(), ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(Optional.of(TestUtils.createMockDashBoardDO()));
		Map<String, Object> json=dashBoardServiceImpl.getKPIOverviewDesign("12345", "2", "condenser", "kpi");
		assertNotEquals(null, json);
	}

	@Test(expected = ChemtreatException.class)
	public void getKPIOverviewDesignExceptionTest() throws ChemtreatException
	{

		when(dashboardRepository.findDesign(ArgumentMatchers.any(), ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(Optional.empty());
		Map<String, Object> json=dashBoardServiceImpl.getKPIOverviewDesign("12345", "2", "condenser", "kpi");
		assertNotEquals(null, json);
	}

}
