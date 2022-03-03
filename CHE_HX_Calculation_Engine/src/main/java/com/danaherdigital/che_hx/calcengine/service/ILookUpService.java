package com.danaherdigital.che_hx.calcengine.service;

import java.util.List;
import java.util.Map;

import com.danaherdigital.che_hx.calcengine.dto.LookUpReqDTO;
import com.danaherdigital.che_hx.calcengine.dto.LookUpRespDTO;
import com.danaherdigital.che_hx.calcengine.dto.RequestDTO;

public interface ILookUpService {

public List<LookUpRespDTO> getLookUpData(LookUpReqDTO req);

public Map<String, Double> callLookUp(List<RequestDTO> reqDTO);
}
