package com.danaherdigital.che_hx.asset_management.service;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.json.JSONObject;
import org.springframework.data.domain.Page;

import com.danaherdigital.che_hx.asset_management.dto.AssetDTO;
import com.danaherdigital.che_hx.asset_management.dto.AssetDTOLoadInt;
import com.danaherdigital.che_hx.asset_management.dto.AssetIngestionLogDTO;
import com.danaherdigital.che_hx.asset_management.dto.ChartsConfigDTO;
import com.danaherdigital.che_hx.asset_management.exceptionhandler.ChemtreatException;

public interface IAssetService {

	String saveAsset(String asset) throws ChemtreatException;

	JSONObject getAssetById(String assetId) throws ChemtreatException;

	void deleteAsset(String assetId);

	List<AssetDTO> getAllAssetByTenantAndType(Integer tenantId,String assetType) throws ChemtreatException;

	Page<AssetDTO> getAllAssetByTenantAndTypes(Integer tenantId,String assetType, Integer page, Integer size, String sort, String order, String facilityName, String systemName) throws ChemtreatException;

	
	void updateCondenser(String asset,String assetId) throws ChemtreatException;

	AssetDTOLoadInt getCondenserLoadById(String assetId);

	List<ChartsConfigDTO> getCondenserChartValues(String assetId, Boolean isKpi);

	List<String> getKPIChartValues(String assetId);

	JSONObject getAssetInfo(String name, String type,Integer tenantId);

	List<AssetIngestionLogDTO> getAllInflowLog(@NotBlank Integer tenantId,Integer size) throws ChemtreatException;

}
