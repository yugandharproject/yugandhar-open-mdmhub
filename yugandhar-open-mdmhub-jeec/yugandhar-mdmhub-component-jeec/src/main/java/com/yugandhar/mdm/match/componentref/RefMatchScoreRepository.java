package com.yugandhar.mdm.match.componentref;
/* Generated Oct 27, 2017 5:17:38 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugandhar.mdm.extern.dobj.RefMatchScoreDO;

public interface RefMatchScoreRepository extends JpaRepository<RefMatchScoreDO, Long> {

	//Get All records by Language Code
	@Query("select dobj from RefMatchScoreDO dobj where matchEntityObjectName= ?1")
	Page<RefMatchScoreDO> findAllByMatchEntityObjectName(String matchEntityObjectName, Pageable pageable);

	//Get All ACTIVE records by Language Code
	@Query("select dobj from RefMatchScoreDO dobj where  matchEntityObjectName= ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	Page<RefMatchScoreDO> findAllActiveByMatchEntityObjectName(String matchEntityObjectName, Pageable pageable);

	//Get All INACTIVE records by Language Code
	@Query("select dobj from RefMatchScoreDO dobj where  matchEntityObjectName= ?1 and  dobj.deletedTs < CURRENT_TIMESTAMP")
	Page<RefMatchScoreDO> findAllInActiveByMatchEntityObjectName(String matchEntityObjectName, Pageable pageable);

	//find record based on business key
	@Query("select dobj from RefMatchScoreDO dobj where dobj.matchEntityObjectName = ?1 and dobj.matchAttrPattern = ?2  and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP) ")
	RefMatchScoreDO findByBusinessKeyActive(String matchEntityObjectName,String matchAttrPattern);

	//find INACTIVE record based on business key
	@Query("select dobj from RefMatchScoreDO dobj where dobj.matchEntityObjectName = ?1 and dobj.matchAttrPattern = ?2  and dobj.deletedTs < CURRENT_TIMESTAMP ")
	RefMatchScoreDO findByBusinessKeyInactive(String matchEntityObjectName,String matchAttrPattern);

	//find ACTIVE record based on business key
	@Query("select dobj from RefMatchScoreDO dobj where dobj.matchEntityObjectName = ?1 and dobj.matchAttrPattern = ?2 ")
	RefMatchScoreDO findByBusinessKeyAll(String matchEntityObjectName,String matchAttrPattern);

}
