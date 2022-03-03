package com.danaherdigital.che_hx.asset_management.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danaherdigital.che_hx.asset_management.ApiSuccess;


public class CommonUtils {

	private CommonUtils() {}
	public static ResponseEntity<Object> buildResponseEntity(ApiSuccess apiSuccess) {
		
		return new ResponseEntity<>(apiSuccess, HttpStatus.OK);
	}

	
	public static Pageable getPageable(int page, int size, String sort, String order) {
		Pageable sorted;
		if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)
				&& order.equalsIgnoreCase(ApplicationConstants.DESCENDING)) {
			sorted = PageRequest.of(page, size, Sort.by(sort).descending());
		} else if (!StringUtils.isEmpty(sort)) {
			sorted = PageRequest.of(page, size, Sort.by(sort));
		} else {
			sorted = PageRequest.of(page, size);
		}
		return sorted;
	}
}
