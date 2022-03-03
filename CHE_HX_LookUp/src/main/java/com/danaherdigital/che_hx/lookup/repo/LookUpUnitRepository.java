package com.danaherdigital.che_hx.lookup.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.danaherdigital.che_hx.lookup.model.LookUpUnit;

/**
 * The Interface LookUpUnitRepository.
 */
public interface LookUpUnitRepository extends JpaRepository<LookUpUnit, String>{

	/**
	 * Find by type of unit.
	 *
	 * @param type_of_unit the type of unit
	 * @return the look up unit
	 */
	LookUpUnit findByTypeOfUnit(String typeOfUnit);
}
