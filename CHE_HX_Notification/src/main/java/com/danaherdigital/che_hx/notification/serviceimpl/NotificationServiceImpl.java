package com.danaherdigital.che_hx.notification.serviceimpl;

import java.util.Objects;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.danaherdigital.che_hx.notification.dto.EmailDTO;
import com.danaherdigital.che_hx.notification.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.notification.service.NotificationService;
import com.danaherdigital.che_hx.notification.utils.ApplicationConstants;
import com.sun.mail.smtp.SMTPSendFailedException;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class NotificationServiceImpl.
 */
@Service

/** The Constant log. */
@Slf4j
public class NotificationServiceImpl implements NotificationService {

	/** The email sender. */
	@Autowired
	private JavaMailSender emailSender;

	/**
	 * Send email.
	 *
	 * @param emailDTO the email DTO
	 * @param files    the files
	 * @return true, if successful
	 * @throws MessagingException
	 * @throws Exception the exception
	 */
	@Override
	public boolean sendEmail(EmailDTO emailDTO, MultipartFile[] files) throws
	ChemtreatException, MessagingException{

		log.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "sendEmail");
		try {
			MimeMessage message = emailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(emailDTO.getFrom());
			helper.setTo(emailDTO.getTo());
			helper.setSubject(emailDTO.getSubject());
			helper.setText(emailDTO.getBody());
			if (!(Objects.isNull(emailDTO.getBcc())) && (emailDTO.getBcc().length != 0)
					&& !emailDTO.getBcc()[0].isEmpty()) {
				helper.setBcc(emailDTO.getBcc());
			}

			if (!(Objects.isNull(emailDTO.getCc())) && (emailDTO.getCc().length != 0)
					&& !emailDTO.getCc()[0].isEmpty()) {
				helper.setCc(emailDTO.getCc());
			}
			if (!(Objects.isNull(emailDTO.getSentDate()))) {
				helper.setSentDate(emailDTO.getSentDate());
			}

			for (MultipartFile file : files) {

				helper.addAttachment(file.getOriginalFilename(), file);
			}
			emailSender.send(message);
			return true;

		} catch (MailSendException e) {
			log.error("error occured while sending email->{}", e);
			throw new ChemtreatException("File is too large");
		} catch (SMTPSendFailedException e) {
			log.error("error occured while sending email->{}", e);
			throw e;
		}
	}

}
