package com.danaherdigital.che_hx.uom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.danaherdigital.che_hx.uom.dto.UOMdataDTO;
import com.danaherdigital.che_hx.uom.model.UnitOfMeasure;

public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Integer>{

	
	@Query("SELECT new com.danaherdigital.che_hx.uom.dto.UOMdataDTO(uom.id,uom.displayName) FROM UnitOfMeasure uom")
	List<UOMdataDTO> getAllUOM();

}
