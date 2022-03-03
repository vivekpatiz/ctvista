package com.danaherdigital.che_hx.schedular.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.danaherdigital.che_hx.schedular.model.CalculationConfigMaster;



public interface CalculationConfigMasterRepository extends JpaRepository<CalculationConfigMaster, String> {


	@Query("select cm from CalculationConfigMaster cm join Asset a on cm.assetTypeId=a.assetTypeId where a.id=:id")
	CalculationConfigMaster findByAssetTypeId(@Param("id")String id);
}
