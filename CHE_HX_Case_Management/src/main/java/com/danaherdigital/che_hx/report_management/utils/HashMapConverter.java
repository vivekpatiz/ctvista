package com.danaherdigital.che_hx.report_management.utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.persistence.AttributeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HashMapConverter implements AttributeConverter<Map<String, Object>, String>,Serializable {
   

	/**
	 * 
	 */
	private static final long serialVersionUID = -1434151602460632273L;

	private final ObjectMapper objectMapper = new ObjectMapper();

	static final  Logger LOGGER = LoggerFactory.getLogger(HashMapConverter.class);

    @Override
    public String convertToDatabaseColumn(Map<String, Object> customerInfo) {

        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(customerInfo);
        } catch (final JsonProcessingException e) {
        	LOGGER.error(e.getMessage());
        }

        return customerInfoJson;
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String customerInfoJSON) {

        Map<String, Object> customerInfo = null;
        try {
            customerInfo = objectMapper.readValue(customerInfoJSON, Map.class);
        } catch (final IOException e) {
        	LOGGER.error(e.getMessage());
        }

        return customerInfo;
    }

}