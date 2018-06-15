package com.yugandhar.mdm.corecomponent;
/* Generated Jul 5, 2017 3:07:08 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugandhar.mdm.extern.dobj.LeAccountAssocDO;

public interface LeAccountAssocRepository extends JpaRepository<LeAccountAssocDO, Long> {

	// Get All records by LegalentityIdpk
	@Query("select dobj from LeAccountAssocDO dobj where legalentityIdpk= ?1")
	Page<LeAccountAssocDO> findAllByLegalentityIdpk(String legalentityIdpk, Pageable pageable);

	// Get All ACTIVE records by LegalentityIdpk
	@Query("select dobj from LeAccountAssocDO dobj where  legalentityIdpk= ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	Page<LeAccountAssocDO> findAllActiveByLegalentityIdpk(String legalentityIdpk, Pageable pageable);

	// Get All INACTIVE records by LegalentityIdpk
	@Query("select dobj from LeAccountAssocDO dobj where  legalentityIdpk= ?1 and  dobj.deletedTs < CURRENT_TIMESTAMP")
	Page<LeAccountAssocDO> findAllInActiveByLegalentityIdpk(String legalentityIdpk, Pageable pageable);

	// Get All records by accountIdpk
	@Query("select dobj from LeAccountAssocDO dobj where accountIdpk= ?1")
	Page<LeAccountAssocDO> findAllByAccountIdpk(String accountIdpk, Pageable pageable);

	// Get All ACTIVE records by accountIdpk
	
	@Query("select dobj from LeAccountAssocDO dobj where  accountIdpk= ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP) order by idPk")
	Page<LeAccountAssocDO> findAllActiveByAccountIdpk(String accountIdpk, Pageable pageable);

	// Get All INACTIVE records by accountIdpk
	@Query("select dobj from LeAccountAssocDO dobj where  accountIdpk= ?1 and  dobj.deletedTs < CURRENT_TIMESTAMP")
	Page<LeAccountAssocDO> findAllInActiveByAccountIdpk(String accountIdpk, Pageable pageable);

}



//@QueryHints({  @QueryHint(name = "javax.persistence.cache.retrieveMode", value = "BYPASS"), @QueryHint(name = "javax.persistence.cache.storeMode", value = "BYPASS") })