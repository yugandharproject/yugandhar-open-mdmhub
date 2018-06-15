package com.yugandhar.mdm.config.app.properties;
/* Generated Aug 7, 2017 3:52:45 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;

public interface ConfigAppPropertiesRepository extends JpaRepository<ConfigAppPropertiesDO, Long> {

	//find record based on business key
	@Query("select dobj from ConfigAppPropertiesDO dobj where dobj.key= ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP) ")
	ConfigAppPropertiesDO findByBusinessKeyActive(String key);

	//find INACTIVE record based on business key
	@Query("select dobj from ConfigAppPropertiesDO dobj where dobj.key= ?1 and dobj.deletedTs < CURRENT_TIMESTAMP ")
	ConfigAppPropertiesDO findByBusinessKeyInactive(String key);

	//find ACTIVE record based on business key
	@Query("select dobj from ConfigAppPropertiesDO dobj where dobj.key= ?1")
	ConfigAppPropertiesDO findByBusinessKeyAll(String key);

}
