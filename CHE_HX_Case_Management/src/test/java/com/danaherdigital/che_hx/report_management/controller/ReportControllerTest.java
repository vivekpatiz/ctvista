
package com.danaherdigital.che_hx.report_management.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.danaherdigital.che_hx.report_management.dto.ReportListDTO;
import com.danaherdigital.che_hx.report_management.exceptionhandler.ResourceNotFoundException;
import com.danaherdigital.che_hx.report_management.model.Report;
import com.danaherdigital.che_hx.report_management.service.ReportService;
import com.danaherdigital.che_hx.report_management.utils.JSONConverter;

@RunWith(SpringJUnit4ClassRunner.class)
public class ReportControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	ReportController ReportController;

	@InjectMocks
	private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

	@Mock
	ReportService reportService;

	Report ReportObj = new Report();

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(ReportController).setCustomArgumentResolvers(pageableArgumentResolver)
				.build();
	}



	@Test
    public void getAllReportOkTest() throws Exception {

		List<ReportListDTO> reportList = new ArrayList<>();
		Page<ReportListDTO> ChartsConfigDTO = new PageImpl<>(reportList);
   	 given(reportService.getAllReport(1,1,1,"","ASCENDING","1","1")).willReturn(ChartsConfigDTO);
   	 		Integer tenantId = 2;
   	mockMvc.perform(get("/api/v1/ReportManagement/report-all/"+tenantId)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

    }

	@Test
    public void getAllReportByIdOkTest() throws Exception {
	 		String reportId = "231212";

		Report reportList = new  Report();
		//Page<Report> ChartsConfigDTO = new PageImpl<>(reportList);
   	 given(reportService.getReportById(reportId)).willReturn(reportList);
   	mockMvc.perform(get("/api/v1/ReportManagement/report-id/"+reportId)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

    }


	@Test
    public void createReportApiOkTest() throws Exception {
		Report report = new Report("12312", 22.33, 44.55, new Date(), new Date(), "1232", "333", "422", new HashMap<>(), 2, true,"tenaska","unit1");

		//doNothing().when(reportService).saveorUpdate(Mockito.any());
   	 mockMvc.perform(post("/api/v1/ReportManagement/report")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JSONConverter.asJsonString(report))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

    }


	@Test
	public void updateAssetApiOkTest() throws Exception {
		String reportId = "e9b1df36-16c0-47ac-bc46-9e991f3959be";
		Report report = new Report("12312", 22.33, 44.55, new Date(), new Date(), "1232", "333", "422", new HashMap<>(),
				2, true, "tenaska", "unit1");

		mockMvc.perform(put("/api/v1/ReportManagement/report/" + reportId).contentType(MediaType.APPLICATION_JSON)
				.content(JSONConverter.asJsonString(report)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted());

	}


	@Test
    public void deletePostOkTest() throws Exception {
	 		String reportId = "231212";


	 		doNothing().doThrow(new ResourceNotFoundException()).when(reportService).deleteReport((Mockito.any()));

	 		mockMvc.perform(delete("/api/v1/ReportManagement/reports/"+reportId)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
    }
}
