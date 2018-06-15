${pojo.getPackageDeclaration()}
/* Generated ${date} by Hibernate Tools ${version} using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ${pojo.getDeclarationName()}Repository extends JpaRepository<${pojo.getDeclarationName()}DO, Long> {
	/*
	//Get All records by some id
	@Query("select dobj from ${pojo.getDeclarationName()}DO dobj where <SomeId>= ?1")
	List<${pojo.getDeclarationName()}DO> findAllBy<SomeId>(String <SomeId>);

	//Get All ACTIVE records by some id
	@Query("select dobj from ${pojo.getDeclarationName()}DO dobj where  <SomeId>= ?1 and (dobj.deletedTs is null or dobj.deletedTs > CURRENT_TIMESTAMP)")
	List<${pojo.getDeclarationName()}DO> findAllActiveBy<SomeId>(String <SomeId>);
	
	//Get All INACTIVE records by some id
	@Query("select dobj from ${pojo.getDeclarationName()}DO dobj where  <SomeId>= ?1 and  dobj.deletedTs < CURRENT_TIMESTAMP")
	List<${pojo.getDeclarationName()}DO> findAllInActiveBy<SomeId>(String <SomeId>);
	*/
}
