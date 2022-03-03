package com.danaherdigital.che_hx.notification.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.danaherdigital.che_hx.notification.exceptionhandler.RestExceptionHandler;
import com.danaherdigital.che_hx.notification.service.NotificationService;
import com.danaherdigital.che_hx.notification.utils.NotificationTestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringJUnit4ClassRunner.class)
public class NotificationControllerTest {
	private MockMvc mockMvc;
	@InjectMocks
	NotificationController notificationController;
	@Mock
	NotificationService notificationService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(notificationController)
				.setControllerAdvice(new RestExceptionHandler())

				.build();
	}

	@Test
	public void sendEmailOkTest() throws Exception {

		given(notificationService.sendEmail(Mockito.any(), Mockito.any())).willReturn(true);

		MockMultipartFile attachment = new MockMultipartFile("attachments", "test.xlsx", "multipart/form-data",
				new FileInputStream(new File("src/test/java/test.xlsx")));

		MockMultipartFile metadata = new MockMultipartFile("req", "req", MediaType.APPLICATION_JSON_VALUE,
				new ObjectMapper().writeValueAsString(NotificationTestUtils.createMockEmailDTO2())
						.getBytes(StandardCharsets.UTF_8));

		mockMvc.perform(multipart("/api/v1/Notification/send_email")

				.file(attachment).file(metadata).contentType(MediaType.ALL).accept(MediaType.ALL))
				.andDo(MockMvcResultHandlers.print())

				.andExpect(status().isOk());

	}
}
