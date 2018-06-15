package com.yugandhar.mdm.auth;
/* Generated Oct 2, 2017 1:29:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugandhar.mdm.extern.dobj.AuthUserRegistryDO;

public interface AuthUserRegistryRepository extends JpaRepository<AuthUserRegistryDO, Long> {

	//find record based on business key
	@Query("select dobj from AuthUserRegistryDO dobj where upper(dobj.userName) = upper(?1) and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP) ")
	AuthUserRegistryDO findByBusinessKeyActive(String userName);

	//find INACTIVE record based on business key
	@Query("select dobj from AuthUserRegistryDO dobj where upper(dobj.userName) = upper(?1) and dobj.deletedTs < CURRENT_TIMESTAMP ")
	AuthUserRegistryDO findByBusinessKeyInactive(String userName);

	//find ACTIVE record based on business key
	@Query("select dobj from AuthUserRegistryDO dobj where upper(dobj.userName) = upper(?1) ")
	AuthUserRegistryDO findByBusinessKeyAll(String userName);

}
