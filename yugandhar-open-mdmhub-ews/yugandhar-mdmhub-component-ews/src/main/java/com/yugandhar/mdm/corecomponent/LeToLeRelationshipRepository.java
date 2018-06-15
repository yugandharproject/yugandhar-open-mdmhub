package com.yugandhar.mdm.corecomponent;
/* Generated Jul 5, 2017 3:07:08 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugandhar.mdm.extern.dobj.LeToLeRelationshipDO;

public interface LeToLeRelationshipRepository extends JpaRepository<LeToLeRelationshipDO, Long> {

	// Get All records by fromLegalentityIdpk or toLegalentityIdpk;
	@Query("select dobj from LeToLeRelationshipDO dobj where fromLegalentityIdpk= ?1 or toLegalentityIdpk=?1")
	Page<LeToLeRelationshipDO> findAllByLegalentityIdpk(String fromLegalentityIdpk, Pageable pageable);

	// Get All ACTIVE records by fromLegalentityIdpk or toLegalentityIdpk;
	@Query("select dobj from LeToLeRelationshipDO dobj where  (fromLegalentityIdpk= ?1  or toLegalentityIdpk=?1) and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	Page<LeToLeRelationshipDO> findAllActiveByLegalentityIdpk(String fromLegalentityIdpk, Pageable pageable);

	// Get All INACTIVE records by fromLegalentityIdpk or toLegalentityIdpk;
	@Query("select dobj from LeToLeRelationshipDO dobj where  (fromLegalentityIdpk= ?1  or toLegalentityIdpk=?1) and  dobj.deletedTs < CURRENT_TIMESTAMP")
	Page<LeToLeRelationshipDO> findAllInActiveByLegalentityIdpk(String fromLegalentityIdpk, Pageable pageable);

}
