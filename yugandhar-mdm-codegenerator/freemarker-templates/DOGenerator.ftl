package com.yugandhar.mdm.extern.dobj;
/* Generated ${date} by Hibernate Tools ${version} using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * DO class for mapped to database ${clazz.table.name} entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see Abstract${pojo.getDeclarationName()}DO
*/
<#assign classbody>
<#if true?if_exists>
<#if pojo.isComponent()>
@${pojo.importType("javax.persistence.Embeddable")}
<#else>
@${pojo.importType("javax.persistence.Entity")}
@${pojo.importType("javax.persistence.Table")}(name="${clazz.table.name}"
<#if clazz.table.schema?exists>
    ,schema="${clazz.table.schema}"
</#if><#if clazz.table.catalog?exists>
    ,catalog="${clazz.table.catalog}"
</#if>
<#assign uniqueConstraint=pojo.generateAnnTableUniqueConstraint()>
<#if uniqueConstraint?has_content>
    , uniqueConstraints = ${uniqueConstraint} 
</#if>)
</#if>
</#if>
public class ${pojo.getDeclarationName()}DO extends Abstract${pojo.getDeclarationName()}DO {

	/**
	 *  Any additional attributes in the OOTB entity needs to be added in this class
	 */
	private static final long serialVersionUID = 1L;

<#--  /** default constructor */ -->
    public ${pojo.getDeclarationName()}DO() {
		super();
    }

	public ${pojo.getDeclarationName()}DO(${pojo.getDeclarationName()}DO the${pojo.getDeclarationName()}DO) {
		super(the${pojo.getDeclarationName()}DO);
	}
}
</#assign>

${classbody}

