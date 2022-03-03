package com.danaherdigital.che_hx.notification.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.danaherdigital.che_hx.notification.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.notification.utils.NotificationTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceImplTest {

	@InjectMocks
	NotificationServiceImpl notificationServiceImpl;

	@Mock
	JavaMailSender emailSender;
	@Mock
	MimeMessage message;

	@Test
	public void sendEmailSuccessTest() throws Exception {
		when(emailSender.createMimeMessage()).thenReturn(message);

		MultipartFile multipartFile = new MockMultipartFile("file", "test.json", "",
				new FileInputStream(new File("src/test/java/test.xlsx")));
		MultipartFile[] a = new MultipartFile[1];
		a[0] = multipartFile;
		boolean s = notificationServiceImpl.sendEmail(NotificationTestUtils.createMockEmailDTO(), a);
		assertEquals(true, s);

		boolean s1 = notificationServiceImpl.sendEmail(NotificationTestUtils.createMockEmailDTO2(), a);
		assertEquals(true, s1);
		boolean s2 = notificationServiceImpl.sendEmail(NotificationTestUtils.createMockEmailDTO3(), a);
		assertEquals(true, s2);
		boolean s3 = notificationServiceImpl.sendEmail(NotificationTestUtils.createMockEmailDTO4(), a);
		assertEquals(true, s3);
	}

	@Test(expected = NullPointerException.class)
	public void sendEmailExceptionTest() throws Exception {
		when(emailSender.createMimeMessage()).thenReturn(message);

		MultipartFile multipartFile = new MockMultipartFile("file", "test.json", "",
				new FileInputStream(new File("src/test/java/test.xlsx")));
		MultipartFile[] a = new MultipartFile[2];
		a[0] = multipartFile;
		boolean s = notificationServiceImpl.sendEmail(NotificationTestUtils.createMockEmailDTO(), a);
		assertEquals(false, s);

	}

	@Test(expected = ChemtreatException.class)
	public void sendEmailExceptionTest2() throws Exception {
		when(emailSender.createMimeMessage()).thenThrow(MailSendException.class);
		//doThrow(MailSendException.class).when(emailSender.send(message));

		MultipartFile multipartFile = new MockMultipartFile("file", "test.json", "",
				new FileInputStream(new File("src/test/java/test.xlsx")));
		MultipartFile[] a = new MultipartFile[2];
		a[0] = multipartFile;
		boolean s = notificationServiceImpl.sendEmail(NotificationTestUtils.createMockEmailDTO(), a);
		assertEquals(false, s);

	}


}
