package com.danaherdigital.che_hx.report_management.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.danaherdigital.che_hx.report_management.exceptionhandler.RestExceptionHandler;
import com.danaherdigital.che_hx.report_management.service.HistorianMappingService;
import com.danaherdigital.che_hx.report_management.utils.TestUtils;

@WebAppConfiguration
@ContextConfiguration(classes = WebConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class HistorianMappingControllerTest {
	private MockMvc mockMvc;
	@InjectMocks
	HistorianMappingController historianMappingController;
	@Mock
	HistorianMappingService historianMappingService;

	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(historianMappingController)
				//.setCustomArgumentResolvers(pageableArgumentResolver)
				.setControllerAdvice(new RestExceptionHandler())

				.build();
	}

	@Test
    public void calcEngineApiOkTest() throws Exception {
		//ClassLoader classLoader = getClass().getClassLoader();
		File interfaceDirectory = new File("src/test/java/testExcel.xlsx");
		 FileInputStream fi3 = new FileInputStream(interfaceDirectory);

		 //FileInputStream fi2 = new FileInputStream(new File(classLoader.getResource("test.xlsx").getFile()));
         MockMultipartFile fstmp = new MockMultipartFile("file","testExcel.xlsx","multipart/form-data",fi3);

		given(historianMappingService.convertExcelToJsonString((Mockito.any()))).willReturn(TestUtils.mockJsonString());

   	 mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/HistorianMapping/parse_excel")
             .file(fstmp)
				.contentType(MediaType.APPLICATION_JSON).contentType(MediaType.MULTIPART_FORM_DATA)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

    }
}
