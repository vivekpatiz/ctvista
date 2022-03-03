package com.danaherdigital.che_hx.lookup.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.danaherdigital.che_hx.lookup.model.LookUpFuel;

/**
 * The Interface LookUpFuelRepository.
 */
public interface LookUpFuelRepository extends JpaRepository<LookUpFuel, String> {


	 /**
 	 * Find by fuel.
 	 *
 	 * @param fuel the fuel
 	 * @return the look up fuel
 	 */
 	LookUpFuel findByFuel(String fuel);

}
