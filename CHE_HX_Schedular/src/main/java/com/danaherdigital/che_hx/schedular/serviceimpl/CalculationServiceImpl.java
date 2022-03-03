package com.danaherdigital.che_hx.schedular.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.danaherdigital.che_hx.schedular.dto.CalcEngineDTO;
import com.danaherdigital.che_hx.schedular.model.Input;
import com.danaherdigital.che_hx.schedular.service.ICalculationService;
import com.danaherdigital.che_hx.schedular.utils.ApplicationConstants;


import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CalculationServiceImpl implements ICalculationService {

	@Value("${calculation.engine.url}")
	String calEngineUrl;
	@Autowired
	RestTemplate restTemplate;

	@Override
	public List<Input> getCalculatedData(CalcEngineDTO calDTO) {

		log.info("cal service started...");

		log.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getCalculatedData");
		List<Input> result = Collections.emptyList();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<CalcEngineDTO> request = new HttpEntity<>(calDTO, headers);

		try {
		log.info("before call");
			ResponseEntity<Input[]> response = restTemplate.postForEntity(calEngineUrl, request, Input[].class);
				log.info("calc response{}",response);
			if (response.getStatusCode() == HttpStatus.OK) {
				log.info("inside success");
				Input[] respList = response.getBody();

				result = new ArrayList<>(Arrays.asList(respList));
			}

		} catch (Exception e) {
			log.error("error in CalculationServiceImpl.getCalculatedData" + e);

		}
		return result;

	}

}
