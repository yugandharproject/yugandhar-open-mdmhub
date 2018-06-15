package com.yugandhar.mdm.auth;
/* Generated Oct 1, 2017 8:58:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugandhar.mdm.extern.dobj.AuthUserroleAccesscontrolDO;

public interface AuthUserroleAccesscontrolRepository extends JpaRepository<AuthUserroleAccesscontrolDO, Long> {

	// Get All records by Language Code
	@Query("select dobj from AuthUserroleAccesscontrolDO dobj where upper(dobj.profileType) = upper(?1) and authUserRoleRegistryIdpk= ?2 ")
	Page<AuthUserroleAccesscontrolDO> findAllActiveAuthUserroleAccesscontrolByRegistryIdpk(String profileType,
			String authUserRoleRegistryIdpk, Pageable pageable);

	// Get All ACTIVE records by Language Code
	@Query("select dobj from AuthUserroleAccesscontrolDO dobj where  upper(dobj.profileType) = upper(?1) and authUserRoleRegistryIdpk= ?2 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	Page<AuthUserroleAccesscontrolDO> findAllInActiveAuthUserroleAccesscontrolByRegistryIdpk(String profileType,
			String authUserRoleRegistryIdpk, Pageable pageable);

	// Get All INACTIVE records by Language Code
	@Query("select dobj from AuthUserroleAccesscontrolDO dobj where  upper(dobj.profileType) = upper(?1) and authUserRoleRegistryIdpk= ?2 and  dobj.deletedTs < CURRENT_TIMESTAMP")
	Page<AuthUserroleAccesscontrolDO> findAuthUserroleAccesscontrolByRegistryIdpk(String profileType,
			String authUserRoleRegistryIdpk, Pageable pageable);

	// find record based on business key
	@Query("select dobj from AuthUserroleAccesscontrolDO dobj where upper(dobj.profileType) = (?1) and dobj.authUserRoleRegistryIdpk= ?2 and dobj.configTxnRegistryIdpk= ?3 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP) ")
	AuthUserroleAccesscontrolDO findByBusinessKeyActive(String profileType, String authUserRoleRegistryIdpk,
			String configTxnRegistryIdpk);

	// find INACTIVE record based on business key
	@Query("select dobj from AuthUserroleAccesscontrolDO dobj where upper(dobj.profileType) = (?1) and dobj.authUserRoleRegistryIdpk= ?2 and dobj.configTxnRegistryIdpk= ?3 and dobj.deletedTs < CURRENT_TIMESTAMP ")
	AuthUserroleAccesscontrolDO findByBusinessKeyInactive(String profileType, String authUserRoleRegistryIdpk,
			String configTxnRegistryIdpk);

	// find ACTIVE record based on business key
	@Query("select dobj from AuthUserroleAccesscontrolDO dobj where upper(dobj.profileType) = (?1) and dobj.authUserRoleRegistryIdpk= ?2 and dobj.configTxnRegistryIdpk= ?3")
	AuthUserroleAccesscontrolDO findByBusinessKeyAll(String profileType, String authUserRoleRegistryIdpk,
			String configTxnRegistryIdpk);

}
