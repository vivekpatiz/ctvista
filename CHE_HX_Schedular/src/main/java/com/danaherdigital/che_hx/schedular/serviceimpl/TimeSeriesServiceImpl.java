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

import com.danaherdigital.che_hx.schedular.dto.PersistResDTO;
import com.danaherdigital.che_hx.schedular.dto.TimeSeriesReqDTO;
import com.danaherdigital.che_hx.schedular.service.ITimeSeriesService;
import com.danaherdigital.che_hx.schedular.utils.ApplicationConstants;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TimeSeriesServiceImpl implements ITimeSeriesService {

	@Value("${timeseries.service.url}")
	String timeSeriesUrl;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public PersistResDTO persistTimeSeriesData(TimeSeriesReqDTO req) {
		log.info("timeseries service started...");
		PersistResDTO resp=null;
		log.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "persistTimeSeriesData");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<TimeSeriesReqDTO> request = new HttpEntity<>(req, headers);

		try {

			ResponseEntity<PersistResDTO> response = restTemplate.postForEntity(timeSeriesUrl, request, PersistResDTO.class);

			if (response.getStatusCode() == HttpStatus.OK) {
				resp=response.getBody();
			}
		} catch (Exception e) {
			log.error("error in CalculationServiceImpl.getCalculatedData" + e);
		}
		return resp;

	}

}
