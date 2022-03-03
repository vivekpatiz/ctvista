package com.danaherdigital.che_hx.time_series.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.danaherdigital.che_hx.time_series.dto.AssetParamDTO;
import com.danaherdigital.che_hx.time_series.model.AssetParam;

public interface AssetParamRepository extends JpaRepository<AssetParam,String>{


			/**
			 * Find params.
			 *
			 * @param id the id
			 * @return the list
			 */
			@Query(value="SELECT a.name as assetName,ap.name as param,a.tenant_id as tenantId,ap.id as id FROM dhrd_asset_params ap join dhrd_asset a  on a.id=ap.asset_id  where  ap.type='RAW' and ap.asset_id=:id and ap.computed=1"
					,nativeQuery = true)
			List<AssetParamDTO>  findParams(@Param("id") String id);
}
