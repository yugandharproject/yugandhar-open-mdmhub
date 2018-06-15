package com.yugandhar.mdm.corecomponent;
/* Generated Jul 5, 2017 3:07:08 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugandhar.mdm.extern.dobj.LeSystemKeysRegistryDO;

public interface LeSystemKeysRegistryRepository extends JpaRepository<LeSystemKeysRegistryDO, Long> {
	
	//Get All records by some id
	@Query("select dobj from LeSystemKeysRegistryDO dobj where legalentityIdpk= ?1")
	Page<LeSystemKeysRegistryDO> findAllByLegalentityIdpk(String legalEntityIdPk, Pageable pageable);
	
	//Get All ACTIVE records by some id
	@Query("select dobj from LeSystemKeysRegistryDO dobj where  legalentityIdpk= ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	Page<LeSystemKeysRegistryDO> findAllActiveByLegalentityIdpk(String legalEntityIdPk, Pageable pageable);
	
	//Get All INACTIVE records by some id
	@Query("select dobj from LeSystemKeysRegistryDO dobj where  legalentityIdpk= ?1 and  dobj.deletedTs < CURRENT_TIMESTAMP")
	Page<LeSystemKeysRegistryDO> findAllInActiveByLegalentityIdpk(String legalEntityIdPk, Pageable pageable);
	
}
