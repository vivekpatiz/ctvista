package com.danaherdigital.che_hx.uom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.danaherdigital.che_hx.uom.model.WaterCFactor;


/**
 * The Interface WaterCFactorRepository.
 */
public interface WaterCFactorRepository extends JpaRepository<WaterCFactor, Integer> {


	@Query(value = "select * from dhrd_water_correction_factor_master order by abs(inlet_water_temp-:temp)limit 1",nativeQuery = true)
	WaterCFactor findByTemp(@Param("temp")int temp);

}
