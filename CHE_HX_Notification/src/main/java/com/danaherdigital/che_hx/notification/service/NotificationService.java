package com.danaherdigital.che_hx.notification.service;

import javax.mail.MessagingException;

import org.springframework.web.multipart.MultipartFile;

import com.danaherdigital.che_hx.notification.dto.EmailDTO;
import com.danaherdigital.che_hx.notification.exceptionhandler.ChemtreatException;

public interface NotificationService {

	public boolean sendEmail(EmailDTO emailDTO, MultipartFile[] files) throws  ChemtreatException,MessagingException;

}
