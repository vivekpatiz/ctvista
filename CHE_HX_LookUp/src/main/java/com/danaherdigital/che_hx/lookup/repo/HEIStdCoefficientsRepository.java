package com.danaherdigital.che_hx.lookup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.danaherdigital.che_hx.lookup.model.HEIStdCoefficients;

/**
 * The Interface HEIStdCoefficientsRepository.
 */
public interface HEIStdCoefficientsRepository extends JpaRepository<HEIStdCoefficients, Double>{

	/**
	 * Find by coeff.
	 *
	 * @param dia the dia
	 * @param ft the ft
	 * @return the HEI std coefficients
	 */
	@Query(value = "select * from dhrd_hei_standards_ht_tx_coef order by abs(dia-:dia), abs(ft_per_sec-:ft) limit 1",nativeQuery = true)
	HEIStdCoefficients findByCoeff(@Param("dia") double dia,
										@Param("ft") double ft);
}
