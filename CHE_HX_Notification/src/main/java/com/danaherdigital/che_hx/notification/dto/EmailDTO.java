package com.danaherdigital.che_hx.notification.dto;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class EmailDTO {


@Email
@NotBlank
@JsonProperty("to")
public String to;
@NotBlank
@Email
@JsonProperty("from")
public String from;
@NotBlank
@JsonProperty("subject")
public String subject;
@JsonProperty("body")
public String body;
@JsonProperty("bcc")
public String[] bcc = null;
@JsonProperty("cc")
public String[] cc = null;
@JsonProperty("sentDate")
public Date sentDate;

}