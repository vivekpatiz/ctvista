package com.danaherdigital.che_hx.schedular.service;

import com.danaherdigital.che_hx.schedular.dto.UOMReqDTO;
import com.danaherdigital.che_hx.schedular.dto.UOMRespDTO;

public interface IUOMService {

	UOMRespDTO getUOMConversionData(UOMReqDTO req);

}
