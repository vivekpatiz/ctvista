package com.danaherdigital.che_hx.schedular.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.danaherdigital.che_hx.schedular.exceptionhandler.ResourceNotFoundException;
import com.danaherdigital.che_hx.schedular.model.AssetDataLog;
import com.danaherdigital.che_hx.schedular.repo.AssetDataLogRepository;
import com.danaherdigital.che_hx.schedular.service.ICalcStatusService;


@Service
public class CalcStatusServiceImpl implements ICalcStatusService {

	@Autowired
	AssetDataLogRepository assetDataLogRepository;
	@Override
	public List<AssetDataLog> getList(String tenantId) {

		List<AssetDataLog> list = new ArrayList<>();
		try {
			list = assetDataLogRepository.findByTenantId(tenantId);
			if (CollectionUtils.isEmpty(list)) {
				throw new ResourceNotFoundException();

			}
			return list;
		} catch (Exception e) {
			throw new ResourceNotFoundException();
		}

	}

}
