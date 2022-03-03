package com.danaherdigital.che_hx.time_series.serviceimpl;


import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.danaherdigital.che_hx.time_series.dto.FavChartLoadDataDto;
import com.danaherdigital.che_hx.time_series.dto.FavoriteChartsReqDto;
import com.danaherdigital.che_hx.time_series.model.FavoriteChartsModel;
import com.danaherdigital.che_hx.time_series.repository.FavoriteChartRepository;
import com.danaherdigital.che_hx.time_series.service.FavoriteChartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
@EnableAsync
public class FavoriteChartServiceImpl implements FavoriteChartService {

	@Autowired
	FavoriteChartRepository favoriteChartRepository;
	FavoriteChartsModel favmodel;
	@Override
	public String addFavouriteChartsData(FavoriteChartsReqDto req) {
			if (!Objects.isNull(req)) {
				favmodel=new FavoriteChartsModel();
				favmodel.setAssetTypeId(req.getAssetId());
				favmodel.setTenantId(req.getTenantid());
				String newJsonData="";
				ObjectMapper mapper = new ObjectMapper();
				 try {
					newJsonData = mapper.writeValueAsString(req.getData());
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				favmodel.setInputParams(newJsonData);
				favmodel.setSource(req.getSource());
				favmodel.setGroup_name(req.getGroup_name());
				favoriteChartRepository.save(favmodel);
			}
		return "success";

}
	@Override
	public List<FavChartLoadDataDto> getAllFavouriteCharts(@NotBlank Integer tenantId, String assetId) {
		// TODO Auto-generated method stub
		List<FavChartLoadDataDto> resultObj=null;
		if (tenantId != null && assetId!=null) {
		resultObj=favoriteChartRepository.getAllFavouriteCharts(tenantId, assetId);

		}
		return resultObj;
	}
	@Override
	public int deleteFavouriteChart(@NotBlank String id) {
		// TODO Auto-generated method stub
		int response=favoriteChartRepository.deleteFavChart(id);
		return response;
	}
	@Override
	public int deleteFavouriteChartGrp(@NotBlank Integer tenantId, String assetId, String groupName) {
		// TODO Auto-generated method stub
		int response=favoriteChartRepository.deleteFavChartGroup(tenantId, assetId, groupName);
		return response;
	}
}