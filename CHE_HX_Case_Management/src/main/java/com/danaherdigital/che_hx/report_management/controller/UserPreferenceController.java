package com.danaherdigital.che_hx.report_management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danaherdigital.che_hx.report_management.ApiSuccess;
import com.danaherdigital.che_hx.report_management.dto.UserPreferenceDTO;
import com.danaherdigital.che_hx.report_management.service.UserPreferenceService;
import com.danaherdigital.che_hx.report_management.utils.ApplicationConstants;


@RestController
@RequestMapping("/api/v1/UserPreference")
public class UserPreferenceController {

	static final  Logger LOGGER = LoggerFactory.getLogger(UserPreferenceController.class);

    @Autowired
    UserPreferenceService userPreferenceService;



    @GetMapping("/preference/{assetId}/{userName}")
    public ResponseEntity<Object> getUserPreferenceById(@PathVariable String assetId,@PathVariable String userName) {
    	LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(),
				 "getUserPreferenceById");
    	UserPreferenceDTO userPreferenceDTO = userPreferenceService.getUserPreferenceById(assetId,userName);
   		return new ResponseEntity<>(userPreferenceDTO,HttpStatus.OK);
    }

    @PostMapping("/preference")
    public ResponseEntity<Object> createUserPreference(@RequestBody UserPreferenceDTO userPreferenceDTO) {
    	LOGGER.info(ApplicationConstants.LOGGER_PLACEHOLDER,ApplicationConstants.CLASSNAME,ApplicationConstants.METHODNAME, this.getClass().getSimpleName(),
				 "createUserPreference");

    	userPreferenceService.createUserPreference(userPreferenceDTO);
		return new ResponseEntity<>(new ApiSuccess(null), HttpStatus.CREATED);
    }

}
