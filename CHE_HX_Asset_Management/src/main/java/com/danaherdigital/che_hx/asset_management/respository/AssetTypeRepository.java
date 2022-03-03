package com.danaherdigital.che_hx.asset_management.respository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danaherdigital.che_hx.asset_management.model.AssetType;

/**
 * The Interface AssetTypeRepository.
 */
public interface AssetTypeRepository extends JpaRepository<AssetType, String> {

    /**
     * Find by name.
     *
     * @param name the name
     * @return the optional
     */
    Optional<AssetType> findByName(String name);

    Optional<AssetType> findById(String id);

}
