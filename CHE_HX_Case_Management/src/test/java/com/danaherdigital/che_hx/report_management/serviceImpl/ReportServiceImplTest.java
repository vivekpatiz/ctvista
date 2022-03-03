
package com.danaherdigital.che_hx.report_management.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.owasp.esapi.errors.IntrusionException;
import org.owasp.esapi.errors.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.danaherdigital.che_hx.report_management.dto.ReportListDTO;
import com.danaherdigital.che_hx.report_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.report_management.exceptionhandler.ConflictException;
import com.danaherdigital.che_hx.report_management.exceptionhandler.ResourceNotFoundException;
import com.danaherdigital.che_hx.report_management.model.Report;
import com.danaherdigital.che_hx.report_management.repository.ReportRepository;
import com.danaherdigital.che_hx.report_management.serviceimpl.ReportServiceImpl;
import com.danaherdigital.che_hx.report_management.utils.ApplicationConstants;
import com.danaherdigital.che_hx.report_management.utils.CommonUtils;
import com.danaherdigital.che_hx.report_management.utils.TestUtils;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceImplTest {

	@InjectMocks
	ReportServiceImpl reportServiceImpl;

	@Mock
	ReportRepository reportRepository;

	@Mock
	ModelMapper modelMapper;
	private Report report = new Report();

	@Before
	public void setUp() {
		when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(report);

	}

	@Test
	public void getAllReportTest() throws ChemtreatException {
		List<ReportListDTO> reportList = new ArrayList<>();
		reportList.add(TestUtils.getReportListDtoTemplate());
		Page<ReportListDTO> ChartsConfigDTO = new PageImpl<>(reportList);

		Integer tenantId = 1;
		given(reportRepository.getAllReports(CommonUtils.getPageable(1, 1, "", "ASCENDING"), tenantId, "1", "1"))
				.willReturn(ChartsConfigDTO);

		Page<ReportListDTO> ChartsConfigDTO2 = reportServiceImpl.getAllReport(1, 1, 1, "", "ASCENDING", "1", "1");
		assertNotEquals(0, ChartsConfigDTO2.getContent().size());

	}

	@Test
	public void getReportByIdTestOK() throws ChemtreatException {

		String reportId = "123121";
		given(reportRepository.findById(reportId)).willReturn(Optional.of(TestUtils.getReportTemplate()));

		Report report = reportServiceImpl.getReportById(reportId);
		assertNotEquals(0, report.getMaxLoad());

	}

	@Test
	public void getReportByIdTestFail() throws ChemtreatException {

		String reportId = "123121";
		given(reportRepository.findById(reportId)).willReturn(Optional.empty());

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			reportServiceImpl.getReportById(reportId);
		});
		String expectedMessage = ApplicationConstants.REPORT_NOT_PRESENT;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void saveReportOKTest() throws ChemtreatException, ConflictException {

		String reportName = "422";
		given(reportRepository.findByReportName(reportName)).willReturn(Optional.empty());
		given(reportRepository.save(ArgumentMatchers.any())).willReturn(TestUtils.getReportTemplate());
		String id = reportServiceImpl.saveorUpdate(TestUtils.getReportDTOTemplate());
		assertEquals("12312", id);
	}

	@Test
	public void updateReportTest() throws ChemtreatException, ConflictException, IntrusionException, ValidationException {

		String reportId = "123121";
		String reportName = "422";
		given(reportRepository.findById(reportId)).willReturn(Optional.of(TestUtils.getReportTemplate()));
		given(reportRepository.findByReportName(reportName)).willReturn(Optional.of(TestUtils.getReportTemplate()));

		reportServiceImpl.updateReport(TestUtils.getReportDTOTemplate(), reportId);
		assertEquals("123121", reportId);
	}

	@Test
	public void updateReportTestFail() throws ChemtreatException {

		String reportId = "123121";
		given(reportRepository.findById(reportId)).willReturn(Optional.empty()); //
		//given(reportRepository.findByReportName(reportName)).willReturn(Optional.of(TestUtils.getReportTemplate()));

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			reportServiceImpl.updateReport(TestUtils.getReportDTOTemplate(), reportId);
		});
		String expectedMessage = ApplicationConstants.REPORT_NOT_PRESENT;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void updateReportTestFail2() throws ChemtreatException {

		String reportId = "123121";
		String reportName = "422";
		given(reportRepository.findById(reportId)).willReturn(Optional.of(TestUtils.getReportTemplate()));
		given(reportRepository.findByReportName(reportName)).willReturn(Optional.of(TestUtils.getReportTemplate2()));

		Exception exception = assertThrows(ConflictException.class, () -> {
			reportServiceImpl.updateReport(TestUtils.getReportDTOTemplate(), reportId);
		});

		String expectedMessage = ApplicationConstants.REPORT_PRESENT;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void saveReportTestFail() throws ChemtreatException {

		String reportName = "422";
		given(reportRepository.findByReportName(reportName)).willReturn(Optional.of(TestUtils.getReportTemplate2()));

		Exception exception = assertThrows(ConflictException.class, () -> {
			reportServiceImpl.saveorUpdate(TestUtils.getReportDTOTemplate());
		});

		String expectedMessage = ApplicationConstants.REPORT_PRESENT;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void updateReportTestElse() throws ChemtreatException, ConflictException, IntrusionException, ValidationException {

		String reportId = "123121";
		String reportName = "422";
		given(reportRepository.findById(reportId)).willReturn(Optional.of(TestUtils.getReportTemplate()));
		given(reportRepository.findByReportName(reportName)).willReturn(Optional.empty());

		reportServiceImpl.updateReport(TestUtils.getReportDTOTemplate(), reportId);
		assertEquals("123121", reportId);

	}

	@Test
	public void deleteReportByIdTestOK() throws ChemtreatException, ConflictException {

		String reportId = "123121";
		given(reportRepository.findById(reportId)).willReturn(Optional.of(TestUtils.getReportTemplate()));

		reportServiceImpl.deleteReport(reportId);
		assertEquals("123121", reportId);
	}

	@Test
	public void deleteReportByIdTestFail() throws ChemtreatException {

		String reportId = "123121";
		given(reportRepository.findById(reportId)).willReturn(Optional.empty());

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
			reportServiceImpl.deleteReport(reportId);
		});
		String expectedMessage = ApplicationConstants.REPORT_NOT_PRESENT;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

}
