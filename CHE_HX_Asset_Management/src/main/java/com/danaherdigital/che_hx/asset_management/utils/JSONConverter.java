package com.danaherdigital.che_hx.asset_management.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.databind.ObjectMapper;

@Service("JSONConverter")
public class JSONConverter {
	
	final static Logger logger = LoggerFactory.getLogger(JSONConverter.class);
	
	 public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            
				logger.info("JSONConverter.asJsonString error " + e.getMessage());

	        }
			return null;
	    }
	 
	 
}
