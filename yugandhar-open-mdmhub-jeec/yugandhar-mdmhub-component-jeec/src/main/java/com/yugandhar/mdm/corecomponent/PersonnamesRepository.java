package com.yugandhar.mdm.corecomponent;
/* Generated Jul 5, 2017 3:07:08 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugandhar.mdm.extern.dobj.PersonnamesDO;

public interface PersonnamesRepository extends JpaRepository<PersonnamesDO, Long> {

	// Get All records by some id
	@Query("select dobj from PersonnamesDO dobj where legalentityIdpk= ?1")
	Page<PersonnamesDO> findAllByLegalentityIdpk(String legalentityIdpk, Pageable pageable);

	// Get All ACTIVE records by some id
	@Query("select dobj from PersonnamesDO dobj where  legalentityIdpk= ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	Page<PersonnamesDO> findAllActiveByLegalentityIdpk(String legalentityIdpk, Pageable pageable);

	// Get All INACTIVE records by some id
	@Query("select dobj from PersonnamesDO dobj where  legalentityIdpk= ?1 and  dobj.deletedTs < CURRENT_TIMESTAMP")
	Page<PersonnamesDO> findAllInActiveByLegalentityIdpk(String legalentityIdpk, Pageable pageable);

}
