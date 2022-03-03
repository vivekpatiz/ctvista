package com.danaherdigital.che_hx.notification.utils;

import java.util.Date;

import com.danaherdigital.che_hx.notification.dto.EmailDTO;

public class NotificationTestUtils {



	public static EmailDTO createMockEmailDTO()
	{

		EmailDTO em=new EmailDTO();
		em.setBcc(null);
		em.setBody("demo test");
		em.setCc(null);
		em.setFrom("service@pobox.ctvistaplus.com");
		em.setSubject("Demo email");
		em.setTo("mohammad.bagali@danaherdigital.com");
		return em;
	}


	public static EmailDTO createMockEmailDTO3()
	{

		EmailDTO em=new EmailDTO();
		em.setBcc(new String[]{});
		em.setBody("demo test");
		em.setCc(new String[]{});
		em.setFrom("service@pobox.ctvistaplus.com");
		em.setSubject("Demo email");
		em.setTo("mohammad.bagali@danaherdigital.com");
		return em;
	}

	public static EmailDTO createMockEmailDTO4()
	{

		EmailDTO em=new EmailDTO();
		em.setBcc(new String[]{""});
		em.setBody("demo test");
		em.setCc(new String[]{""});
		em.setFrom("service@pobox.ctvistaplus.com");
		em.setSubject("Demo email");
		em.setTo("mohammad.bagali@danaherdigital.com");
		return em;
	}

	public static EmailDTO createMockEmailDTO2()
	{

		EmailDTO em=new EmailDTO();
		em.setBcc(new String[]{"a@b.com"});
		em.setBody("demo test");
		em.setCc(new String[]{"a@b.com"});
		em.setFrom("service@pobox.ctvistaplus.com");
		em.setSubject("Demo email");
		em.setTo("mohammad.bagali@danaherdigital.com");
		em.setSentDate(new Date());
		return em;
	}
}
