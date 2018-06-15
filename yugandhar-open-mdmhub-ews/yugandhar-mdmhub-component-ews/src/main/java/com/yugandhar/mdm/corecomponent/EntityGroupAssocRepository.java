package com.yugandhar.mdm.corecomponent;
/* Generated Jul 5, 2017 3:07:08 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugandhar.mdm.extern.dobj.EntityGroupAssocDO;

public interface EntityGroupAssocRepository extends JpaRepository<EntityGroupAssocDO, Long> {

	// Get All records by entityIdPk
	@Query("select dobj from EntityGroupAssocDO dobj where entityObjectTypeRefkey=?1 and entityIdpk = ?2")
	Page<EntityGroupAssocDO> findAllByEntityIdpk(String entityObjectTypeRefkey, String entityIdpk, Pageable pageable);

	// Get All ACTIVE records by entityIdPk
	@Query("select dobj from EntityGroupAssocDO dobj where  entityObjectTypeRefkey=?1 and entityIdpk = ?2 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	Page<EntityGroupAssocDO> findAllActiveByEntityIdpk(String entityObjectTypeRefkey, String entityIdpk,
			Pageable pageable);

	// Get All INACTIVE records by entityIdPk
	@Query("select dobj from EntityGroupAssocDO dobj where entityObjectTypeRefkey=?1 and entityIdpk = ?2 and  dobj.deletedTs < CURRENT_TIMESTAMP")
	Page<EntityGroupAssocDO> findAllInActiveByEntityIdpk(String entityObjectTypeRefkey, String entityIdpk,
			Pageable pageable);

	// Entity Grouping Id search
	// Get All records by some id
	@Query("select dobj from EntityGroupAssocDO dobj where entityGroupIdpk = ?1")
	Page<EntityGroupAssocDO> findAllByEntityGroupIdpk(String entityGroupIdpk, Pageable pageable);

	// Get All ACTIVE records by some id
	@Query("select dobj from EntityGroupAssocDO dobj where entityGroupIdpk = ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	Page<EntityGroupAssocDO> findAllActiveByEntityGroupIdpk(String entityGroupIdpk, Pageable pageable);

	// Get All INACTIVE records by some id
	@Query("select dobj from EntityGroupAssocDO dobj where entityGroupIdpk = ?1 and  dobj.deletedTs < CURRENT_TIMESTAMP")
	Page<EntityGroupAssocDO> findAllInActiveByEntityGroupIdpk(String entityGroupIdpk, Pageable pageable);
}
