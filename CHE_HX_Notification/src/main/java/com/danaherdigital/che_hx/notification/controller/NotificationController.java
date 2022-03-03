package com.danaherdigital.che_hx.notification.controller;

import java.time.LocalDateTime;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.danaherdigital.che_hx.notification.dto.EmailDTO;
import com.danaherdigital.che_hx.notification.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.notification.service.NotificationService;
import com.danaherdigital.che_hx.notification.utils.ApplicationConstants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class NotificationController.
 */
@RestController

/** The Constant log. */
@Slf4j
@RequestMapping("/api/v1/Notification")
@Validated
@AllArgsConstructor
public class NotificationController {

	/** The notification service. */
	@Autowired
	NotificationService notificationService;

	/**
	 * Send email notification.
	 *
	 * @param emailDTO the email DTO
	 * @param files    the files
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@PostMapping("/send_email")
	public ResponseEntity<ResponseBody> sendEmailNotification(@Valid @RequestPart("req") EmailDTO emailDTO,
			@RequestPart(value = "attachments", required = false) MultipartFile[] files) throws ChemtreatException,MessagingException
 {
		log.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "sendEmailNotification");
		notificationService.sendEmail(emailDTO, files);
		return ResponseEntity.ok().body(new ResponseBody("Email Sent Successfuly", LocalDateTime.now()));
	}

	@AllArgsConstructor
	@Setter
	@Getter
	private static class ResponseBody {
		private String message;
		private LocalDateTime timestamp;
	}
}
