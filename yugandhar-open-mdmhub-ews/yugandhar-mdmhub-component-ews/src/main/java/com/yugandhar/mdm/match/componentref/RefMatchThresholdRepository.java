package com.yugandhar.mdm.match.componentref;
/* Generated Oct 27, 2017 5:17:38 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugandhar.mdm.extern.dobj.RefMatchThresholdDO;

public interface RefMatchThresholdRepository extends JpaRepository<RefMatchThresholdDO, Long> {

	//Get All records by Language Code
	@Query("select dobj from RefMatchThresholdDO dobj ")
	Page<RefMatchThresholdDO> findAll(Pageable pageable);

	//Get All ACTIVE records by Language Code
	@Query("select dobj from RefMatchThresholdDO dobj where  (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	Page<RefMatchThresholdDO> findAllActive( Pageable pageable);

	//Get All INACTIVE records by Language Code
	@Query("select dobj from RefMatchThresholdDO dobj where   dobj.deletedTs < CURRENT_TIMESTAMP")
	Page<RefMatchThresholdDO> findAllInActive(Pageable pageable);

	//find record based on business key
	@Query("select dobj from RefMatchThresholdDO dobj where dobj.attrBlockName = ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP) ")
	RefMatchThresholdDO findByBusinessKeyActive(String attrBlockName);

	//find INACTIVE record based on business key
	@Query("select dobj from RefMatchThresholdDO dobj where dobj.attrBlockName = ?1 and dobj.deletedTs < CURRENT_TIMESTAMP ")
	RefMatchThresholdDO findByBusinessKeyInactive(String attrBlockName);

	//find ACTIVE record based on business key
	@Query("select dobj from RefMatchThresholdDO dobj where dobj.attrBlockName = ?1 ")
	RefMatchThresholdDO findByBusinessKeyAll(String attrBlockName);

}
