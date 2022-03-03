package com.danaherdigital.che_hx.schedular.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.danaherdigital.che_hx.schedular.model.UnitOfMeasure;

public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long> {

	@Query("select u from UnitOfMeasure u")
	List<UnitOfMeasure> findAllUom();

}
