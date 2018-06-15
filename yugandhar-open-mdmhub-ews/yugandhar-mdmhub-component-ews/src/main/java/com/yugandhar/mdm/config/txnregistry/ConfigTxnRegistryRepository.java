package com.yugandhar.mdm.config.txnregistry;
/* Generated Jun 13, 2017 1:01:46 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugandhar.mdm.extern.dobj.ConfigTxnRegistryDO;

public interface ConfigTxnRegistryRepository extends JpaRepository<ConfigTxnRegistryDO, Long> {

	List<ConfigTxnRegistryDO> findAll();

	@Query("select dobj from ConfigTxnRegistryDO dobj where dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP")
	List<ConfigTxnRegistryDO> findAllActive();

	@Query("select dobj from ConfigTxnRegistryDO dobj where dobj.txnserviceName = ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP) ")
	ConfigTxnRegistryDO findByBusinessKeyActive(String txnserviceName);


}
