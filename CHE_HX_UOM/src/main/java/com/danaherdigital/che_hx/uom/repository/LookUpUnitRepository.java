package com.danaherdigital.che_hx.uom.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.danaherdigital.che_hx.uom.model.LookUpUnit;


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
