package com.danaherdigital.che_hx.calcengine.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.danaherdigital.che_hx.calcengine.dto.LookUpReqDTO;
import com.danaherdigital.che_hx.calcengine.dto.LookUpRespDTO;
import com.danaherdigital.che_hx.calcengine.dto.RequestDTO;
import com.danaherdigital.che_hx.calcengine.service.ILookUpService;
import com.danaherdigital.che_hx.calcengine.utils.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class LookUpServiceImpl implements ILookUpService{

	@Value("${lookupService.url}")
	private String lookUpUrl;
	@Autowired
	RestTemplate restTemplate;

	@Override
	public List<LookUpRespDTO> getLookUpData(LookUpReqDTO req) {
		log.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getLookUpData");
		List<LookUpRespDTO> result = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<LookUpReqDTO> request = new HttpEntity<>(req, headers);
		try {


			ResponseEntity<LookUpRespDTO[]> response = restTemplate.postForEntity(lookUpUrl, request,
					LookUpRespDTO[].class);

			if (response.getStatusCode() == HttpStatus.OK) {
				LookUpRespDTO[] respList = response.getBody();
				result = new ArrayList<>(Arrays.asList(respList));
			}

		} catch (Exception e) {
			log.error("error in LookUpServiceImpl.getLookUpData" + e);
		}
		return result;
	}




	 public Map<String, Double> callLookUp(List<RequestDTO> reqDTO) {
		LookUpReqDTO inp = new LookUpReqDTO();
		Map<String, Double> result = new HashedMap<>();

			if (!CollectionUtils.isEmpty(reqDTO)) {
				inp.setInput(reqDTO);
				List<LookUpRespDTO> resp = getLookUpData(inp);
				if (!Objects.isNull(resp)) {
					for (LookUpRespDTO ip : resp)
						for (Map.Entry<String, Double> entry : ip.getCalculatedValues().entrySet()) {
							result.put(entry.getKey(), entry.getValue());
						}
				}

			}
		return result;
	}



}
