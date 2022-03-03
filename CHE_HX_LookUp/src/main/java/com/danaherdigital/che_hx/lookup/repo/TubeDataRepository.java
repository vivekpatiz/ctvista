package com.danaherdigital.che_hx.lookup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.danaherdigital.che_hx.lookup.model.TubeData;

/**
 * The Interface TubeDataRepository.
 */
public interface TubeDataRepository extends JpaRepository<TubeData, Integer> {


	/**
	 * Find by bwg.
	 *
	 * @param bwg the bwg
	 * @return the tube data
	 */
	@Query(value = "select * from dhrd_tube_data_master order by abs(bwg-:bwg) limit 1",nativeQuery = true)
	TubeData findByBwg(@Param("bwg") int bwg);
}
