package com.danaherdigital.che_hx.report_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.danaherdigital.che_hx.report_management.model.UserPreferences;

public interface UserPreferenceRepository extends JpaRepository<UserPreferences, String> {

	@Query("SELECT userPref FROM UserPreferences userPref WHERE userPref.assetId = :assetId AND userPref.userName=:userName")
	Optional<UserPreferences> findByUserNameAndAssetId(@Param("userName")String userName, @Param("assetId")String assetId);

}
