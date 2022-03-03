package com.danaherdigital.che_hx.schedular.service;

import java.util.List;

import com.danaherdigital.che_hx.schedular.dto.CalcEngineDTO;
import com.danaherdigital.che_hx.schedular.model.Input;

public interface ICalculationService {

	List<Input> getCalculatedData(CalcEngineDTO calDTO);



}
