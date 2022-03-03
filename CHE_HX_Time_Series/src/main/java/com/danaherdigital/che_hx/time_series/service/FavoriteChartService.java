package com.danaherdigital.che_hx.time_series.service;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.danaherdigital.che_hx.time_series.dto.FavChartLoadDataDto;
import com.danaherdigital.che_hx.time_series.dto.FavoriteChartsReqDto;



public interface FavoriteChartService {

	public String addFavouriteChartsData(FavoriteChartsReqDto req);
	
	public List<FavChartLoadDataDto> getAllFavouriteCharts(@NotBlank Integer tenantId,String assetId);

	public int deleteFavouriteChart(@NotBlank String id);
	
	public int deleteFavouriteChartGrp(@NotBlank Integer tenantId,String assetId,String groupName);
}
