package com.danaherdigital.che_hx.schedular.listener;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.ItemProcessListener;

import com.danaherdigital.che_hx.schedular.model.AssetDataLog;

@Slf4j
public class CalculationProcessorListener implements ItemProcessListener<AssetDataLog, Object> {


    @Override
    public void beforeProcess(AssetDataLog assetDataLog) {
        log.info("beforeProcess");
    }

    @Override
    public void afterProcess(AssetDataLog assetDataLog, Object object) {
        log.info("afterProcess: " + assetDataLog + " ---> " + object);
    }

    @Override
    public void onProcessError(AssetDataLog assetDataLog, Exception e) {
        log.info("onProcessError");
    }
}
