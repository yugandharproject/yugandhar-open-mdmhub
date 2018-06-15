package com.yugandhar.mdm.corecomponent;
/* Generated Jul 5, 2017 3:05:23 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.yugandhar.mdm.extern.dobj.LeToLeRelationshipDO;

public interface LeToLeRelationshipRepository extends JpaRepository<LeToLeRelationshipDO, Long> {
	/*
	//Get All records by some id
	@Query("select dobj from LeToLeRelationshipDO dobj where <SomeId>= ?1")
	List<LeToLeRelationshipDO> findAllBy<SomeId>(String <SomeId>);
	
	//Get All ACTIVE records by some id
	@Query("select dobj from LeToLeRelationshipDO dobj where  <SomeId>= ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	List<LeToLeRelationshipDO> findAllActiveBy<SomeId>(String <SomeId>);
	
	//Get All INACTIVE records by some id
	@Query("select dobj from LeToLeRelationshipDO dobj where  <SomeId>= ?1 and  dobj.deletedTs < CURRENT_TIMESTAMP")
	List<LeToLeRelationshipDO> findAllInActiveBy<SomeId>(String <SomeId>);
	*/
}
