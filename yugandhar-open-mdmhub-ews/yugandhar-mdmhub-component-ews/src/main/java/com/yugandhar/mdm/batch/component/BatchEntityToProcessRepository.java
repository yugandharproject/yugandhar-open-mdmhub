package com.yugandhar.mdm.batch.component;
/* Generated Dec 12, 2017 6:24:10 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.yugandhar.mdm.extern.dobj.BatchEntityToProcessDO;

public interface BatchEntityToProcessRepository extends JpaRepository<BatchEntityToProcessDO, Long> {
	/*
	//Get All records by some id
	@Query("select dobj from BatchEntityToProcessDO dobj where <SomeId>= ?1")
	List<BatchEntityToProcessDO> findAllBy<SomeId>(String <SomeId>);
	
	//Get All ACTIVE records by some id
	@Query("select dobj from BatchEntityToProcessDO dobj where  <SomeId>= ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	List<BatchEntityToProcessDO> findAllActiveBy<SomeId>(String <SomeId>);
	
	//Get All INACTIVE records by some id
	@Query("select dobj from BatchEntityToProcessDO dobj where  <SomeId>= ?1 and  dobj.deletedTs < CURRENT_TIMESTAMP")
	List<BatchEntityToProcessDO> findAllInActiveBy<SomeId>(String <SomeId>);
	*/
}
