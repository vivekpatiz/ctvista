package com.danaherdigital.che_hx.lookup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.danaherdigital.che_hx.lookup.model.HEIStdCFactor;

/**
 * The Interface HEIStdCFactorRepository.
 */
public interface HEIStdCFactorRepository extends JpaRepository<HEIStdCFactor, Double> {

	/**
	 * Find by C factor.
	 *
	 * @param bwg the bwg
	 * @param ft the ft
	 * @return the HEI std C factor
	 */
	@Query(value = "select * from dhrd_hei_standard_mat_gauge_cor where material_name=:materialName order by abs(bwg-:bwg) limit 1",nativeQuery = true)
	HEIStdCFactor findByCFactor(@Param("bwg") int bwg,
										@Param("materialName") String ft);
}
