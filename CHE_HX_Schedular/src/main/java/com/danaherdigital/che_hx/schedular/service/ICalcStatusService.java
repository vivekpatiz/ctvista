package com.danaherdigital.che_hx.schedular.service;

import java.util.List;

import com.danaherdigital.che_hx.schedular.model.AssetDataLog;

public interface ICalcStatusService {


	List<AssetDataLog> getList(String tenantId);

}
