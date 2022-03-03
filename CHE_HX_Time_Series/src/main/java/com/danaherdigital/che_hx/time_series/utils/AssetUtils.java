package com.danaherdigital.che_hx.time_series.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.danaherdigital.che_hx.time_series.ApiSuccess;


public class AssetUtils {

	private AssetUtils() {}

	public static ResponseEntity<Object> buildResponseEntity(ApiSuccess apiSuccess) {

		return new ResponseEntity<>(apiSuccess, HttpStatus.OK);
	}

public static ResponseEntity<Object> buildResponseEntity(ApiSuccess apiSuccess,HttpStatus status) {

		return new ResponseEntity<>(apiSuccess, status);
	}


	public static String getReadableDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		return dateFormat.format(cal.getTime());

	}

}
