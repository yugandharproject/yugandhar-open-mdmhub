package com.yugandhar.mdm.auth;
/* Generated Oct 2, 2017 1:29:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugandhar.mdm.extern.dobj.AuthUserRoleAssocDO;

public interface AuthUserRoleAssocRepository extends JpaRepository<AuthUserRoleAssocDO, Long> {

	// Get All records
	@Query("select dobj from AuthUserRoleAssocDO dobj where authUserRegistryIdpk= ?1")
	Page<AuthUserRoleAssocDO> findAllByAuthUserRegistryIdpk(String authUserRegistryIdpk, Pageable pageable);

	// Get All ACTIVE records
	@Query("select dobj from AuthUserRoleAssocDO dobj where  authUserRegistryIdpk= ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	Page<AuthUserRoleAssocDO> findAllActiveByAuthUserRegistryIdpk(String authUserRegistryIdpk, Pageable pageable);

	// Get All INACTIVE records
	@Query("select dobj from AuthUserRoleAssocDO dobj where  authUserRegistryIdpk= ?1 and  dobj.deletedTs < CURRENT_TIMESTAMP")
	Page<AuthUserRoleAssocDO> findAllInActiveByAuthUserRegistryIdpk(String authUserRegistryIdpk, Pageable pageable);

	// find record based on business key
	@Query("select dobj from AuthUserRoleAssocDO dobj where dobj.authRolesRegistryIdpk = ?1 and dobj.authUserRegistryIdpk= ?2 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP) ")
	AuthUserRoleAssocDO findByBusinessKeyActive(String authRolesRegistryIdpk, String authUserRegistryIdpk);

	// find INACTIVE record based on business key
	@Query("select dobj from AuthUserRoleAssocDO dobj where dobj.authRolesRegistryIdpk = ?1 and dobj.authUserRegistryIdpk= ?2 and dobj.deletedTs < CURRENT_TIMESTAMP ")
	AuthUserRoleAssocDO findByBusinessKeyInactive(String authRolesRegistryIdpk, String authUserRegistryIdpk);

	// find ACTIVE record based on business key
	@Query("select dobj from AuthUserRoleAssocDO dobj where dobj.authRolesRegistryIdpk = ?1 and dobj.authUserRegistryIdpk= ?2")
	AuthUserRoleAssocDO findByBusinessKeyAll(String authRolesRegistryIdpk, String authUserRegistryIdpk);

	// Get All records
	@Query("select dobj from AuthUserRoleAssocDO dobj where authRolesRegistryIdpk= ?1")
	Page<AuthUserRoleAssocDO> findAllByAuthRolesRegistryIdpk(String authRolesRegistryIdpk, Pageable pageable);

	// Get All ACTIVE records
	@Query("select dobj from AuthUserRoleAssocDO dobj where  authRolesRegistryIdpk= ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	Page<AuthUserRoleAssocDO> findAllActiveByAuthRolesRegistryIdpk(String authRolesRegistryIdpk, Pageable pageable);

	// Get All INACTIVE records
	@Query("select dobj from AuthUserRoleAssocDO dobj where  authRolesRegistryIdpk= ?1 and  dobj.deletedTs < CURRENT_TIMESTAMP")
	Page<AuthUserRoleAssocDO> findAllInActiveByAuthRolesRegistryIdpk(String authRolesRegistryIdpk, Pageable pageable);

}
