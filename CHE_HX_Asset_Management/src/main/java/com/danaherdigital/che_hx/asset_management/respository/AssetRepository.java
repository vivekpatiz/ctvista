package com.danaherdigital.che_hx.asset_management.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.danaherdigital.che_hx.asset_management.dto.AssetDTO;
import com.danaherdigital.che_hx.asset_management.dto.AssetFacilityDTO;
import com.danaherdigital.che_hx.asset_management.dto.CondenserDTO;
import com.danaherdigital.che_hx.asset_management.model.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, String> {


	@Query("SELECT new com.danaherdigital.che_hx.asset_management.dto.AssetDTO(asset.id,asset.createdAt,asset.updatedAt,asset.createdBy,asset.updatedBy,asset.name,asset.status,asset.name,asset.name,asset.name) from Asset asset WHERE asset.tenant.id=:tenantId AND asset.assetType.name =:assetTypeName")
	List<AssetDTO> getAllAssetByTenantAndType(@Param("tenantId") Integer tenantId,@Param("assetTypeName") String assetTypeName);


	@Query("SELECT new com.danaherdigital.che_hx.asset_management.dto.CondenserDTO(asset.id,asset.createdAt,asset.updatedAt,asset.createdBy,asset.updatedBy,asset.name,asset.status,asset.name,asset.name,asset.name,asset.tenant.id) from Asset asset WHERE asset.id=:assetId")
	Optional<CondenserDTO> findAssetByAssetId(@Param("assetId") String assetId);

	@Query(value="SELECT a3.name FROM dhrd_asset a1 LEFT JOIN  dhrd_asset a2 ON a1.parent_asset_id=a2.id JOIN dhrd_asset a3 ON a3.id = a2.parent_asset_id JOIN dhrd_asset_type att ON att.id=a1.asset_type_id  JOIN dhrd_asset_type att2 ON att2.id=a3.asset_type_id WHERE  att2.name= ?1 AND a1.id= ?2 " ,nativeQuery = true)
	String getFacilityNameByID(String assetName,String assetId);


	@Query(value="SELECT a2.name as unitName,a3.name as facilityName,a2.id as unitId,a3.id as facilityId FROM dhrd_asset a1 LEFT JOIN  dhrd_asset a2 ON a1.parent_asset_id=a2.id JOIN dhrd_asset a3 ON a3.id = a2.parent_asset_id JOIN dhrd_asset_type att ON att.id=a1.asset_type_id  JOIN dhrd_asset_type att2 ON att2.id=a3.asset_type_id WHERE  att2.name= ?1 AND a1.id= ?2 " ,nativeQuery = true)
	AssetFacilityDTO getFacilityData(String assetName,String assetId);

	@Query("SELECT asset from Asset asset WHERE asset.tenant.id=:tenantId AND asset.assetType.name =:assetTypeName AND asset.name=:assetName")
	Optional<Asset> getAssetByTenantAndAssetNameType(@Param("tenantId") Integer tenantId,@Param("assetTypeName") String assetTypeName,@Param("assetName") String assetName);


	@Query("SELECT asset from Asset asset WHERE asset.tenant.id=:tenantId AND asset.assetType.name =:assetTypeName AND asset.name=:assetName AND asset.asset.id=:parentAssetId")
	Optional<Asset> getAssetByTenantAndAssetNameTypeUnit(@Param("tenantId") Integer tenantId,@Param("assetTypeName") String assetTypeName,@Param("assetName") String assetName,@Param("parentAssetId") String parentAssetId);

	@Query("SELECT new com.danaherdigital.che_hx.asset_management.dto.AssetDTO(asset.id,asset.createdAt,asset.updatedAt,asset.createdBy,asset.updatedBy,asset.name,asset.status,asset.asset.asset.name,asset.name,asset.asset.name) from Asset asset WHERE asset.tenant.id=:tenantId AND asset.assetType.name =:assetTypeName AND (:facilityName IS NULL OR asset.asset.asset.name = :facilityName) AND (:systemName IS NULL OR  asset.asset.name LIKE :systemName% ) ")
	Page<AssetDTO> getAllAssetByTenantAndTypes(Pageable sortedPage, @Param("tenantId") Integer tenantId,@Param("assetTypeName") String assetTypeName,@Param("facilityName") String facilityName,@Param("systemName") String systemName);

	
	Optional<Asset> findById(String id);
}