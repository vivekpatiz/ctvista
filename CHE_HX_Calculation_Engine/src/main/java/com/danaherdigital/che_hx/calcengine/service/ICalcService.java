package com.danaherdigital.che_hx.calcengine.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.danaherdigital.che_hx.calcengine.dto.CalcReqDTO;

public interface ICalcService {

	public List<Object> getCalcData(@Valid CalcReqDTO req);
	public Map<String, Object> calcProcess(Map<String, ?> inputMap);
}
