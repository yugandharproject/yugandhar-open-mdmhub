package com.yugandhar.mdm.corecomponent;
/* Generated Jul 5, 2017 3:07:08 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugandhar.mdm.extern.dobj.AccountPhoneAssocDO;


public interface AccountPhoneAssocRepository extends JpaRepository<AccountPhoneAssocDO, Long> {
	
	//Get All records by accountIdpk
	@Query("select dobj from AccountPhoneAssocDO dobj where accountIdpk= ?1")
	Page<AccountPhoneAssocDO> findAllByAccountIdpk(String accountIdPk, Pageable pageable);
	
	//Get All ACTIVE records by accountIdpk
	@Query("select dobj from AccountPhoneAssocDO dobj where  accountIdpk= ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	Page<AccountPhoneAssocDO> findAllActiveByAccountIdpk(String accountIdPk, Pageable pageable);
	
	//Get All INACTIVE records by accountIdpk
	@Query("select dobj from AccountPhoneAssocDO dobj where  accountIdpk= ?1 and  dobj.deletedTs < CURRENT_TIMESTAMP")
	Page<AccountPhoneAssocDO> findAllInActiveByAccountIdpk(String accountIdPk, Pageable pageable);
	
}
