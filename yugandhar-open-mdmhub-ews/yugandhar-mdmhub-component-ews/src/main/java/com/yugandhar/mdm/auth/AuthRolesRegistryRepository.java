package com.yugandhar.mdm.auth;
/* Generated Oct 1, 2017 8:58:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugandhar.mdm.extern.dobj.AuthRolesRegistryDO;

public interface AuthRolesRegistryRepository extends JpaRepository<AuthRolesRegistryDO, Long> {

	//find record based on business key
	@Query("select dobj from AuthRolesRegistryDO dobj where upper(dobj.roleName) = upper(?1) and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP) ")
	AuthRolesRegistryDO findByBusinessKeyActive(String roleName);

	//find INACTIVE record based on business key
	@Query("select dobj from AuthRolesRegistryDO dobj where upper(dobj.roleName) = upper(?1) and dobj.deletedTs < CURRENT_TIMESTAMP ")
	AuthRolesRegistryDO findByBusinessKeyInactive(String roleName);

	//find ACTIVE record based on business key
	@Query("select dobj from AuthRolesRegistryDO dobj where upper(dobj.roleName) = upper(?1) ")
	AuthRolesRegistryDO findByBusinessKeyAll(String roleName);

}
