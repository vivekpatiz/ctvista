package com.danaherdigital.che_hx.report_management.service;

import java.util.Map;

import com.danaherdigital.che_hx.report_management.exceptionhandler.ChemtreatException;

public interface DashBoardService {

	Map<String ,Object> getKPIOverviewDesign(String assetId, String tenantId, String assetType, String type) throws ChemtreatException;

}
