package com.yugandhar.mdm.match.component;
/* Generated Oct 27, 2017 5:20:01 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import org.springframework.data.jpa.repository.JpaRepository;

import com.yugandhar.mdm.extern.dobj.InactiveLeRegistryDO;

public interface InactiveLeRegistryRepository extends JpaRepository<InactiveLeRegistryDO, Long> {
	/*
	//Get All records by some id
	@Query("select dobj from InactiveLeRegistryDO dobj where <SomeId>= ?1")
	List<InactiveLeRegistryDO> findAllBy<SomeId>(String <SomeId>);
	
	//Get All ACTIVE records by some id
	@Query("select dobj from InactiveLeRegistryDO dobj where  <SomeId>= ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	List<InactiveLeRegistryDO> findAllActiveBy<SomeId>(String <SomeId>);
	
	//Get All INACTIVE records by some id
	@Query("select dobj from InactiveLeRegistryDO dobj where  <SomeId>= ?1 and  dobj.deletedTs < CURRENT_TIMESTAMP")
	List<InactiveLeRegistryDO> findAllInActiveBy<SomeId>(String <SomeId>);
	*/
}
