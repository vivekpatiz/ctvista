package com.danaherdigital.che_hx.lookup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.danaherdigital.che_hx.lookup.model.Steam;

/**
 * The Interface SteamRepository.
 */
public interface SteamRepository extends JpaRepository<Steam, Double> {

	/**
	 * Find by temp.
	 *
	 * @param temp the temp
	 * @return the steam
	 */
	@Query(value = "select * from dhrd_steam_pressure_temp_master order by abs(sat_temp_deg_f-:temp) limit 1", nativeQuery = true)
	Steam findByTemp(@Param("temp") Double temp);

	@Query(value = "select * from dhrd_steam_pressure_temp_master order by abs(pressure_in_hg-:press) limit 1", nativeQuery = true)
	Steam findByPressure(@Param("press") Double press);
}
