package com.yugandhar.mdm.corecomponent;
/* Generated Jul 5, 2017 3:07:08 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.yugandhar.mdm.extern.dobj.LeCorporationDO;

@SuppressWarnings("unused")
public interface LeCorporationRepository extends JpaRepository<LeCorporationDO, Long> {
	/*
	//Get All records by some id
	@Query("select dobj from LeCorporationDO dobj where <SomeId>= ?1")
	List<LeCorporationDO> findAllBy<SomeId>(String <SomeId>);
	
	//Get All ACTIVE records by some id
	@Query("select dobj from LeCorporationDO dobj where  <SomeId>= ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	List<LeCorporationDO> findAllActiveBy<SomeId>(String <SomeId>);
	
	//Get All INACTIVE records by some id
	@Query("select dobj from LeCorporationDO dobj where  <SomeId>= ?1 and  dobj.deletedTs < CURRENT_TIMESTAMP")
	List<LeCorporationDO> findAllInActiveBy<SomeId>(String <SomeId>);
	*/
}
