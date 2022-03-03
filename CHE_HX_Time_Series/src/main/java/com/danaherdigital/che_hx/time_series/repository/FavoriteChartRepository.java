package com.danaherdigital.che_hx.time_series.repository;



import java.util.List;


import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.danaherdigital.che_hx.time_series.dto.FavChartData;
import com.danaherdigital.che_hx.time_series.dto.FavChartLoadDataDto;
import com.danaherdigital.che_hx.time_series.model.FavoriteChartsModel;
import org.springframework.transaction.annotation.Propagation;




@Repository
public interface  FavoriteChartRepository extends JpaRepository<FavoriteChartsModel, String> {

	
//	@Modifying
//    @Query(value = "insert into FavoriteChartsModel (id,tenant_id,asset_type_id,input_params,source,chart_name,min_load_range,max_load_range) VALUES (:id,:tenant_id,:asset_type_id,:input_params,:source,:chart_name,:min_load_range,:max_load_range)", nativeQuery = true)
 //   @Transactional
 //   int addFavouriteCharts(@Param("id") String id, @Param("tenant_id") Integer integer,@Param("asset_type_id") String asset_type_id,@Param("input_params") List<@Valid FavChartData> input_params,@Param("source") String source,@Param("chart_name") String chart_name,@Param("min_load_range") int min_range,@Param("max_load_range") int max_range);

	@Query("SELECT new com.danaherdigital.che_hx.time_series.dto.FavChartLoadDataDto(favCharts.id,favCharts.tenantId,favCharts.assetTypeId,favCharts.inputParams,favCharts.source,favCharts.group_name) from FavoriteChartsModel favCharts WHERE favCharts.tenantId=:tenantId AND favCharts.assetTypeId =:assetTypeId")
	List<FavChartLoadDataDto> getAllFavouriteCharts(@Param("tenantId") Integer tenantId,@Param("assetTypeId") String assetTypeId);

	@Modifying
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Query("delete from FavoriteChartsModel fav where fav.id=:id")
	int deleteFavChart(@Param("id")String id);
	
	@Modifying
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Query("delete from FavoriteChartsModel fav where  fav.tenantId=:tenantId AND fav.assetTypeId =:assetTypeId and fav.group_name =:groupName")
	int deleteFavChartGroup(@Param("tenantId") Integer tenantId,@Param("assetTypeId") String assetTypeId,@Param("groupName") String groupName);

}
