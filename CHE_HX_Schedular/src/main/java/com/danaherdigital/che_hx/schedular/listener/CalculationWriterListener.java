package com.danaherdigital.che_hx.schedular.listener;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.ItemWriteListener;

import com.danaherdigital.che_hx.schedular.dto.ApiResponseDTO;


import java.util.List;

@Slf4j
public class CalculationWriterListener implements ItemWriteListener<ApiResponseDTO> {



    @Override
    public void beforeWrite(List<? extends ApiResponseDTO> list) {
        log.info("beforeWrite");
    }


    @Override
    public void afterWrite(List<? extends ApiResponseDTO> list) {
        for (ApiResponseDTO apiResp : list) {
            log.info("afterWrite :" + apiResp.getStatus());

        }
    }

    @Override
    public void onWriteError(Exception e, List<? extends ApiResponseDTO> list) {
        log.info("onWriteError");
    }
}
