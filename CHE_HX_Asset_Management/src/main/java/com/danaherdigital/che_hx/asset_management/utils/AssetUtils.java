package com.danaherdigital.che_hx.asset_management.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danaherdigital.che_hx.asset_management.ApiSuccess;

public class AssetUtils {

	public static ResponseEntity<Object> buildResponseEntity(ApiSuccess apiSuccess) {
		
		return new ResponseEntity<Object>(apiSuccess, HttpStatus.OK);
	}

}
