/* Generated ${date} by Hibernate Tools ${version} using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

//This file covers the artifacts for manual modification of database and codebase.
// Step 1. Execute the SQLs in database. For Maria DB enable the SQL_MODE=ORACLE option and then execute the insert scripts. 
// Step 2. Make an entry in make entry in YugandharBootProjectApplication
// Step 3. Add the generated DO to in TxnPayload object
// Step 4. execute the transaction using sample message generated

###STEP 1###
/* insert sqls for transaction registration */
//----------------------------------------------------
/*#enable below SQL_MODE option for mariaDB/MySQL else the insert SQL will fail. No need to enable this option for Oracle Database */
/*set SQL_MODE=ORACLE; */

Insert into CONFIG_TXN_REGISTRY
   (ID_PK, VERSION, TXNSERVICE_NAME, TXNSERVICE_CLASS, TXNSERVICE_CLASSMETHOD, DESCRIPTION, CREATED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_TXN_REF_ID)
 Values
   (YUG_REGISTRY_SEQ.nextval, 0, 'create${pojo.getDeclarationName()}Base', '${pojo.getPackageName()}.${pojo.getDeclarationName()}Service', 'add', 
    'create record in the database', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 'Generator', '000000000');


Insert into CONFIG_TXN_REGISTRY
   (ID_PK, VERSION, TXNSERVICE_NAME, TXNSERVICE_CLASS, TXNSERVICE_CLASSMETHOD, DESCRIPTION,CREATED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_TXN_REF_ID)
 Values
    (YUG_REGISTRY_SEQ.nextval, 0, 'update${pojo.getDeclarationName()}Base', '${pojo.getPackageName()}.${pojo.getDeclarationName()}Service', 'merge', 
    'update the database record based on primary key i.e. idpk', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'Generator', '000000000');


Insert into CONFIG_TXN_REGISTRY
   (ID_PK, VERSION, TXNSERVICE_NAME, TXNSERVICE_CLASS, TXNSERVICE_CLASSMETHOD, DESCRIPTION,CREATED_TS, UPDATED_TS, UPDATED_BY_USER, UPDATED_TXN_REF_ID)
 Values
   (YUG_REGISTRY_SEQ.nextval, 0, 'retrieve${pojo.getDeclarationName()}Base', '${pojo.getPackageName()}.${pojo.getDeclarationName()}Service', 'findById', 
    'retrieve the record from database based on primary key i.e. idpk', CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,'Generator', '000000000');
COMMIT;

/* rollback scripts if needed
delete from CONFIG_TXN_REGISTRY where TXNSERVICE_NAME ='create${pojo.getDeclarationName()}Base';
delete from CONFIG_TXN_REGISTRY where TXNSERVICE_NAME ='update${pojo.getDeclarationName()}Base';
delete from CONFIG_TXN_REGISTRY where TXNSERVICE_NAME ='retrieve${pojo.getDeclarationName()}Base';
commit; */

###STEP 2###
	//Modify the entry in YugandharBootProjectApplication for your generated packages for spring component scan, entity scan and repository scan. Make the changes as below
	//
		@ComponentScan({"com.yugandhar.*","<your package name here>"})
		@EntityScan({"com.yugandhar.*","<your package name here>"})
		@EnableJpaRepositories({"com.yugandhar.*","<your package name here>"})
		
		e.g.
		@ComponentScan({"com.yugandhar.*","com.demo.*"})
		@EntityScan({"com.yugandhar.*","com.demo.*"})
		@EnableJpaRepositories({"com.yugandhar.*","com.demo.*"})

###STEP 3###
	/* Add the below code snippet in com.yugandhar.common.extern.transferobj.TxnPayload object */

	protected ${pojo.getDeclarationName()}DO ${pojo.getDeclarationName()?uncap_first}DO;
	protected List<${pojo.getDeclarationName()}DO> ${pojo.getDeclarationName()?uncap_first}DOList;
	/**
	 * @return the demoDO
	 */
	public ${pojo.getDeclarationName()}DO get${pojo.getDeclarationName()}DO() {
		return ${pojo.getDeclarationName()?uncap_first}DO;
	}
	/**
	 * @param demoDO the demoDO to set
	 */
	public void set${pojo.getDeclarationName()}DO(${pojo.getDeclarationName()}DO ${pojo.getDeclarationName()?uncap_first}DO) {
		this.${pojo.getDeclarationName()?uncap_first}DO = ${pojo.getDeclarationName()?uncap_first}DO;
	}
	/**
	 * @return the demoDOList
	 */
	public List<${pojo.getDeclarationName()}DO> get${pojo.getDeclarationName()}DOList() {
		return ${pojo.getDeclarationName()?uncap_first}DOList;
	}
	/**
	 * @param demoDOList the demoDOList to set
	 */
	public void set${pojo.getDeclarationName()}DOList(List<${pojo.getDeclarationName()}DO> ${pojo.getDeclarationName()?uncap_first}DOList) {
		this.${pojo.getDeclarationName()?uncap_first}DOList = ${pojo.getDeclarationName()?uncap_first}DOList;
	}


###STEP 4###
/* Sample JSON Messages for test */
//----------------------------------------------------
=================================================================
create${pojo.getDeclarationName()}Base sample JSON message
=================================================================

{
	"txnHeader": {
	 "requesterLanguage": "1",
      "requesterLocale": null,
      "userName": "admin",
      "userRole": "admin",
      "accessToken": null,
      "txnCorrelationId": null,
      "txnMessageId": "1234567890123",
      "requestOriginSource": null,
      "requesterSystem": null,
      "transactionServiceName": "create${pojo.getDeclarationName()}Base"
	},
	"txnPayload": {
			 "${"${pojo.getDeclarationName()}DO"?uncap_first}":       {
		 <#list pojo.getAllPropertiesIterator() as field>
		 <#if field?has_next> "${c2j.keyWordCheck(field.name)}": null, 
		 <#else> "${c2j.keyWordCheck(field.name)}": null
		 </#if>
		</#list>
      }
		
	}
	
}
  
=================================================================
update${pojo.getDeclarationName()}Base sample JSON message
================================================================= 
  {
	"txnHeader": {
		"requesterLanguage": "1",
      "requesterLocale": null,
      "userName": "admin",
      "userRole": "admin",
      "accessToken": null,
      "txnCorrelationId": null,
      "txnMessageId": "1234567890123",
      "requestOriginSource": null,
      "requesterSystem": null,
      "transactionServiceName": "update${pojo.getDeclarationName()}Base"
	},
	"txnPayload": {
			 "${"${pojo.getDeclarationName()}DO"?uncap_first}":       {
		 <#list pojo.getAllPropertiesIterator() as field>
		 <#if field?has_next> "${c2j.keyWordCheck(field.name)}": null, 
		 <#else> "${c2j.keyWordCheck(field.name)}": null
		 </#if>
		</#list>
      }
		
	}
	
}

=================================================================
retrieve${pojo.getDeclarationName()}Base sample JSON message
================================================================= 
{
	"txnHeader": {
		"requesterLanguage": "1",
      "requesterLocale": null,
      "userName": "admin",
      "userRole": "admin",
      "accessToken": null,
      "txnCorrelationId": null,
      "txnMessageId": "1234567890123",
      "requestOriginSource": null,
      "requesterSystem": null,
	  "transactionServiceName": "retrieve${pojo.getDeclarationName()}Base"
	},
	"txnPayload": {
			 "${"${pojo.getDeclarationName()}DO"?uncap_first}":       {
		"idPk": "1"
		 
      }
		
	}
	
}
  
