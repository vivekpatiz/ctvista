package com.danaherdigital.che_hx.schedular.serviceimpl;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.danaherdigital.che_hx.schedular.dto.UOMReqDTO;
import com.danaherdigital.che_hx.schedular.dto.UOMRespDTO;
import com.danaherdigital.che_hx.schedular.service.IUOMService;
import com.danaherdigital.che_hx.schedular.utils.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UOMServiceImpl implements IUOMService{

	@Value("${uom.service.url}")
	String uomServiceUrl;

	@Autowired
	RestTemplate restTemplate;
	@Override
	public UOMRespDTO getUOMConversionData(UOMReqDTO req) {
		log.info("UOM service started...");

		log.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getUOMConversionData");
		UOMRespDTO result = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<UOMReqDTO> request = new HttpEntity<>(req, headers);

		try {

			ResponseEntity<UOMRespDTO> response = restTemplate.postForEntity(uomServiceUrl, request, UOMRespDTO.class);

			if (response.getStatusCode() == HttpStatus.OK) {


				result = response.getBody();
			}

		} catch (Exception  e) {
			log.error("error in UOMServiceImpl.getUOMConversionData->{}", e);
		}
		return result;

	}



}
