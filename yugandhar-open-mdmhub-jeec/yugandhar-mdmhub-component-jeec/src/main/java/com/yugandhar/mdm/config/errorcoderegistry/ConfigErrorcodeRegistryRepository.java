package com.yugandhar.mdm.config.errorcoderegistry;
/* Generated Aug 8, 2017 4:29:11 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugandhar.mdm.extern.dobj.ConfigErrorcodeRegistryDO;

public interface ConfigErrorcodeRegistryRepository extends JpaRepository<ConfigErrorcodeRegistryDO, Long> {

	//Get All records by Language Code
	@Query("select dobj from ConfigErrorcodeRegistryDO dobj where configLanguageCodeKey= ?1")
	List<ConfigErrorcodeRegistryDO> findAllByLanguageCode(String configLanguageCodeKey);

	//Get All ACTIVE records by Language Code
	@Query("select dobj from ConfigErrorcodeRegistryDO dobj where  configLanguageCodeKey= ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	List<ConfigErrorcodeRegistryDO> findAllActiveByLanguageCode(String configLanguageCodeKey);

	//Get All INACTIVE records by Language Code
	@Query("select dobj from ConfigErrorcodeRegistryDO dobj where  configLanguageCodeKey= ?1 and  dobj.deletedTs < CURRENT_TIMESTAMP")
	List<ConfigErrorcodeRegistryDO> findAllInActiveByLanguageCode(String configLanguageCodeKey);

	//find record based on business key
	@Query("select dobj from ConfigErrorcodeRegistryDO dobj where dobj.configLanguageCodeKey = ?1 and dobj.errorCode= ?2 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP) ")
	ConfigErrorcodeRegistryDO findByBusinessKeyActive(String configLanguageCodeKey, String errorCode);

	//find INACTIVE record based on business key
	@Query("select dobj from ConfigErrorcodeRegistryDO dobj where dobj.configLanguageCodeKey = ?1 and dobj.errorCode= ?2 and dobj.deletedTs < CURRENT_TIMESTAMP ")
	ConfigErrorcodeRegistryDO findByBusinessKeyInactive(String configLanguageCodeKey, String errorCode);

	//find ACTIVE record based on business key
	@Query("select dobj from ConfigErrorcodeRegistryDO dobj where dobj.configLanguageCodeKey = ?1 and dobj.errorCode= ?2")
	ConfigErrorcodeRegistryDO findByBusinessKeyAll(String configLanguageCodeKey, String errorCode);

}
