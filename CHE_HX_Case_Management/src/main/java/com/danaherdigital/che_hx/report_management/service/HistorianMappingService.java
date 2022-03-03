package com.danaherdigital.che_hx.report_management.service;


import java.io.InputStream;

import com.danaherdigital.che_hx.report_management.exceptionhandler.ChemtreatException;



public interface HistorianMappingService {

	String convertExcelToJsonString(InputStream inputStream) throws ChemtreatException;
}
