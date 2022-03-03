
package com.danaherdigital.che_hx.report_management.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.owasp.esapi.ESAPI;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.danaherdigital.che_hx.report_management.exceptionhandler.RestExceptionHandler;
import com.danaherdigital.che_hx.report_management.service.DocumentService;
import com.danaherdigital.che_hx.report_management.utils.JSONConverter;

@WebAppConfiguration

@ContextConfiguration(classes = WebConfig.class)

@RunWith(SpringJUnit4ClassRunner.class)
public class DocumentControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	DocumentController documentController;

	@Mock
	DocumentService documentService;


	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(documentController).setControllerAdvice(new RestExceptionHandler())

				.build();
	}

	  @Rule
	    public TemporaryFolder folder = new TemporaryFolder();

		@Test
		public void getExcel() throws Exception {
			String type = "mapping";

			final File file1 = folder.newFile("myfile1.txt");

			given(documentService.getExcelDocumentTemplate(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).willReturn(file1);
			mockMvc.perform(post(("/api/v1/document/excel/" + ESAPI.encoder().encodeForHTMLAttribute(type)))
					.contentType(MediaType.APPLICATION_JSON)
					.content(ESAPI.encoder().encodeForHTML(JSONConverter.asJsonString(null)))
					.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		}


	@Test
	public void getS3Doc() throws Exception {

        final File file1 = folder.newFile("myfile1.txt");

		given(documentService.getS3Document(ArgumentMatchers.anyString())).willReturn(file1);
		mockMvc.perform(get(("/api/v1/document/doc")).param("filename", "file.pdf").contentType(MediaType.APPLICATION_JSON)
				.content(JSONConverter.asJsonString(null)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

}
