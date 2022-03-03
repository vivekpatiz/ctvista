package com.danaherdigital.che_hx.asset_management.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.danaherdigital.che_hx.asset_management.model.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Integer> {

	@Query("SELECT tenant FROM Tenant tenant WHERE tenant.accountId =:accountId ")
	Optional<Tenant> findByOrganiztionId(@Param(value = "accountId") Integer accountId);
}
