package com.danaherdigital.che_hx.schedular.service;


import com.danaherdigital.che_hx.schedular.dto.PersistResDTO;
import com.danaherdigital.che_hx.schedular.dto.TimeSeriesReqDTO;


public interface ITimeSeriesService {


	PersistResDTO persistTimeSeriesData(TimeSeriesReqDTO req);

}
