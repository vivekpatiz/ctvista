package com.danaherdigital.che_hx.time_series.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.danaherdigital.che_hx.time_series.model.CalculationConfigMaster;


public interface CalculationConfigMasterRepository extends JpaRepository<CalculationConfigMaster, String> {


	/**
	 * Find by asset type id.
	 *
	 * @param id the id
	 * @return the calculation config master
	 */
	@Query("select cm from CalculationConfigMaster cm join Asset a on cm.assetTypeId=a.assetTypeId where a.id=:id")
	CalculationConfigMaster findByAssetTypeId(@Param("id")String id);
}
