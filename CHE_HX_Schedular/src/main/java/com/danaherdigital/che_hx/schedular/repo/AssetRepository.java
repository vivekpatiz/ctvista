package com.danaherdigital.che_hx.schedular.repo;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.danaherdigital.che_hx.schedular.model.Asset;



@Repository
public interface AssetRepository extends JpaRepository<Asset, String> {



	Optional<Asset> findById(String id);
}