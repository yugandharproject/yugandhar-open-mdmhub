${pojo.getPackageDeclaration()}
/* Generated ${date} by Hibernate Tools ${version} using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yugandhar.mdm.extern.dobj.${pojo.getDeclarationName()}DO;

public interface ${pojo.getDeclarationName()}Repository extends JpaRepository<${pojo.getDeclarationName()}DO, Long> {
	
	//Get All records by Language Code
	@Query("select dobj from ${pojo.getDeclarationName()}DO dobj where configLanguageCodeKey= ?1")
	Page<${pojo.getDeclarationName()}DO> findAllByLanguageCode(String configLanguageCodeKey, Pageable pageable);

	//Get All ACTIVE records by Language Code
	@Query("select dobj from ${pojo.getDeclarationName()}DO dobj where  configLanguageCodeKey= ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	Page<${pojo.getDeclarationName()}DO> findAllActiveByLanguageCode(String configLanguageCodeKey, Pageable pageable);
	
	//Get All INACTIVE records by Language Code
	@Query("select dobj from ${pojo.getDeclarationName()}DO dobj where  configLanguageCodeKey= ?1 and  dobj.deletedTs < CURRENT_TIMESTAMP")
	Page<${pojo.getDeclarationName()}DO> findAllInActiveByLanguageCode(String configLanguageCodeKey, Pageable pageable);
	
	//find record based on business key
	@Query("select dobj from ${pojo.getDeclarationName()}DO dobj where dobj.configLanguageCodeKey = ?1 and dobj.key= ?2 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP) ")
	${pojo.getDeclarationName()}DO findByBusinessKeyActive(String configLanguageCodeKey, String key);
	
	//find INACTIVE record based on business key
	@Query("select dobj from ${pojo.getDeclarationName()}DO dobj where dobj.configLanguageCodeKey = ?1 and dobj.key= ?2 and dobj.deletedTs < CURRENT_TIMESTAMP ")
	${pojo.getDeclarationName()}DO findByBusinessKeyInactive(String configLanguageCodeKey, String key);
	
	//find ACTIVE record based on business key
	@Query("select dobj from ${pojo.getDeclarationName()}DO dobj where dobj.configLanguageCodeKey = ?1 and dobj.key= ?2")
	${pojo.getDeclarationName()}DO findByBusinessKeyAll(String configLanguageCodeKey, String key);
	
	

}
