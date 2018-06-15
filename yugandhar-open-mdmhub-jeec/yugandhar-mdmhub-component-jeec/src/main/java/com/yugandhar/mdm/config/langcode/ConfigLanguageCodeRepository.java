package com.yugandhar.mdm.config.langcode;
/* Generated Aug 8, 2017 3:00:17 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugandhar.mdm.extern.dobj.ConfigLanguageCodeDO;

public interface ConfigLanguageCodeRepository extends JpaRepository<ConfigLanguageCodeDO, Long> {
	// Get All records by Language Code
	@Query("select dobj from ConfigLanguageCodeDO dobj")
	List<ConfigLanguageCodeDO> findAll();

	// Get All ACTIVE records by Language Code
	@Query("select dobj from ConfigLanguageCodeDO dobj where  (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	List<ConfigLanguageCodeDO> findAllActive();

	// Get All INACTIVE records by Language Code
	@Query("select dobj from ConfigLanguageCodeDO dobj where  dobj.deletedTs < CURRENT_TIMESTAMP")
	List<ConfigLanguageCodeDO> findAllInActive();

	// find record based on business key
	@Query("select dobj from ConfigLanguageCodeDO dobj where dobj.key= ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP) ")
	ConfigLanguageCodeDO findByBusinessKeyActive(String key);

	// find INACTIVE record based on business key
	@Query("select dobj from ConfigLanguageCodeDO dobj where dobj.key= ?1 and dobj.deletedTs < CURRENT_TIMESTAMP ")
	ConfigLanguageCodeDO findByBusinessKeyInactive(String key);

	// find ACTIVE record based on business key
	@Query("select dobj from ConfigLanguageCodeDO dobj where dobj.key= ?1")
	ConfigLanguageCodeDO findByBusinessKeyAll(String key);

}
