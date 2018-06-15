package com.yugandhar.mdm.config.inqlevel;
/* Generated Aug 22, 2017 5:25:47 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.yugandhar.mdm.extern.dobj.ConfigInquiryLevelsDO;

public interface ConfigInquiryLevelsRepository extends JpaRepository<ConfigInquiryLevelsDO, Long> {

	//Get All records by Language Code
	@Query("select dobj from ConfigInquiryLevelsDO dobj")
	List<ConfigInquiryLevelsDO> findAll();

	//Get All ACTIVE records by Language Code
	@Query("select dobj from ConfigInquiryLevelsDO dobj where  dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP")
	List<ConfigInquiryLevelsDO> findAllActive();

	//Get All INACTIVE records by Language Code
	@Query("select dobj from ConfigInquiryLevelsDO dobj where  dobj.deletedTs < CURRENT_TIMESTAMP")
	List<ConfigInquiryLevelsDO> findAllInActive();

	//find record based on business key
	@Query("select dobj from ConfigInquiryLevelsDO dobj where  dobj.inquiryLevel= ?1 and dobj.applicableDobj=?2 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP) ")
	List<ConfigInquiryLevelsDO> findAllByBusinessKeyActive(String inquiryLevel, String applicableDobj);

	//find INACTIVE record based on business key
	@Query("select dobj from ConfigInquiryLevelsDO dobj where  dobj.inquiryLevel= ?1 and dobj.applicableDobj=?2  and dobj.deletedTs < CURRENT_TIMESTAMP ")
	List<ConfigInquiryLevelsDO> findAllByBusinessKeyInactive(String inquiryLevel, String applicableDobj);

	//find ACTIVE record based on business key
	@Query("select dobj from ConfigInquiryLevelsDO dobj where  dobj.inquiryLevel= ?1 and dobj.applicableDobj=?2 ")
	List<ConfigInquiryLevelsDO> findAllByBusinessKeyAll(String inquiryLevel, String applicableDobj);

}
