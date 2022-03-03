package com.danaherdigital.che_hx.report_management.serviceimpl;

import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danaherdigital.che_hx.report_management.exceptionhandler.ChemtreatException;
import com.danaherdigital.che_hx.report_management.model.DashBoard;
import com.danaherdigital.che_hx.report_management.repository.DashboardRepository;
import com.danaherdigital.che_hx.report_management.service.DashBoardService;
import com.danaherdigital.che_hx.report_management.utils.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DashBoardServiceImpl implements DashBoardService {

	@Autowired
	DashboardRepository dashboardRepository;

	@Override
	public Map<String ,Object> getKPIOverviewDesign(String assetId, String tenantId, String assetType, String type)
			throws ChemtreatException {
		log.info(ApplicationConstants.LOGGER_PLACEHOLDER, ApplicationConstants.CLASSNAME,
				ApplicationConstants.METHODNAME, this.getClass().getSimpleName(), "getKPIOverviewDesign");
		Optional<DashBoard> design = dashboardRepository.findDesign(type, assetId,assetType);

		if (!design.isPresent()) {
			throw new ChemtreatException("entity not found");

		}
		Map<String ,Object> data = new HashedMap<>();
		data.put("data", design.get().getConfigJson());

		return data;
	}

}
