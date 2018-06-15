package com.yugandhar.mdm.match.componentref;
/* Generated Oct 27, 2017 5:17:38 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugandhar.mdm.extern.dobj.RefMatchProposedActionDO;

public interface RefMatchProposedActionRepository extends JpaRepository<RefMatchProposedActionDO, Long> {

	//Get All records by Language Code
	@Query("select dobj from RefMatchProposedActionDO dobj where configLanguageCodeKey= ?1")
	Page<RefMatchProposedActionDO> findAllByLanguageCode(String configLanguageCodeKey, Pageable pageable);

	//Get All ACTIVE records by Language Code
	@Query("select dobj from RefMatchProposedActionDO dobj where  configLanguageCodeKey= ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	Page<RefMatchProposedActionDO> findAllActiveByLanguageCode(String configLanguageCodeKey, Pageable pageable);

	//Get All INACTIVE records by Language Code
	@Query("select dobj from RefMatchProposedActionDO dobj where  configLanguageCodeKey= ?1 and  dobj.deletedTs < CURRENT_TIMESTAMP")
	Page<RefMatchProposedActionDO> findAllInActiveByLanguageCode(String configLanguageCodeKey, Pageable pageable);

	//find record based on business key
	@Query("select dobj from RefMatchProposedActionDO dobj where dobj.configLanguageCodeKey = ?1 and dobj.key= ?2 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP) ")
	RefMatchProposedActionDO findByBusinessKeyActive(String configLanguageCodeKey, String key);

	//find INACTIVE record based on business key
	@Query("select dobj from RefMatchProposedActionDO dobj where dobj.configLanguageCodeKey = ?1 and dobj.key= ?2 and dobj.deletedTs < CURRENT_TIMESTAMP ")
	RefMatchProposedActionDO findByBusinessKeyInactive(String configLanguageCodeKey, String key);

	//find ACTIVE record based on business key
	@Query("select dobj from RefMatchProposedActionDO dobj where dobj.configLanguageCodeKey = ?1 and dobj.key= ?2")
	RefMatchProposedActionDO findByBusinessKeyAll(String configLanguageCodeKey, String key);

}
