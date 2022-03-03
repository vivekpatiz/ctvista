package com.danaherdigital.che_hx.schedular.listener;

import lombok.extern.slf4j.Slf4j;


import org.springframework.batch.core.ItemReadListener;

import com.danaherdigital.che_hx.schedular.model.AssetDataLog;

@Slf4j
public class CalculationReaderListener implements ItemReadListener<AssetDataLog> {


    @Override
    public void beforeRead() {
        log.info("beforeRead");
    }

    @Override
    public void afterRead(AssetDataLog assetDataLog) {
    	log.info("afterRead: " + assetDataLog.toString());
    }

    @Override
    public void onReadError(Exception e) {
    	log.info("onReadError");
    }
}
