package com.yugandhar.mdm.abstractdobj;
/* Generated ${date} by Hibernate Tools ${version} using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.yugandhar.mdm.misc.dobj.PrimaryKeyDO;
import com.yugandhar.mdm.extern.dobj.${pojo.getDeclarationName()}DO;

<#assign classbody>

/**
 * Abstract DO class for ${pojo.getDeclarationName()}DO class mapped to database ${clazz.table.name} entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.${pojo.getDeclarationName()}DO
*/

@MappedSuperclass
public abstract class Abstract${pojo.getDeclarationName()}DO implements java.io.Serializable {

<#-- // Fields -->

private static final long serialVersionUID = 1L;
<#foreach field in pojo.getAllPropertiesIterator()>
<#if pojo.getMetaAttribAsBool(field, "gen-property", true)> <#if pojo.hasMetaAttribute(field, "field-description")>    /**
     ${pojo.getFieldJavaDoc(field, 0)}
     */
 </#if>    protected ${pojo.getJavaTypeName(field, false)} ${c2j.keyWordCheck(field.name)}<#if pojo.hasFieldInitializor(field, false)> = ${pojo.getFieldInitialization(field, false)}</#if>;
</#if>
</#foreach>
protected String inquiryFilter;
protected PrimaryKeyDO primaryKeyDO;

<#--  /** default constructor */ -->
    public Abstract${pojo.getDeclarationName()}DO() {
    }
	
	public Abstract${pojo.getDeclarationName()}DO(${pojo.getDeclarationName()}DO the${pojo.getDeclarationName()}DO) {
	<#foreach field in pojo.getAllPropertiesIterator()>
	${c2j.keyWordCheck(field.name)} = the${pojo.getDeclarationName()}DO.${c2j.keyWordCheck(field.name)};
	</#foreach>
	inquiryFilter	=	the${pojo.getDeclarationName()}DO.inquiryFilter;
	}

<#-- // Property accessors -->
	<#foreach property in pojo.getAllPropertiesIterator()>
	<#if pojo.getMetaAttribAsBool(property, "gen-property", true)>
	 <#if pojo.hasFieldJavaDoc(property)>    
		/**       
		 * ${pojo.getFieldJavaDoc(property, 4)}
		 */
	</#if>

		<#-- // ejb3 annotations -->
		<#if true>
	<#if pojo.hasIdentifierProperty()>
	<#if property.equals(clazz.identifierProperty)>
	 ${pojo.generateAnnIdGenerator()}
	<#-- if this is the id property (getter)-->
	<#-- explicitly set the column name for this property-->
	</#if>
	</#if>
	<#if c2h.isOneToOne(property)>
	${pojo.generateOneToOneAnnotation(property, md)}
	<#elseif c2h.isManyToOne(property)>
	${pojo.generateManyToOneAnnotation(property)}
	<#--TODO support optional and targetEntity-->    
	${pojo.generateJoinColumnsAnnotation(property, md)}
	<#elseif c2h.isCollection(property)>
	${pojo.generateCollectionAnnotation(property, md)}
	<#else>
	${pojo.generateBasicAnnotation(property)}
	${pojo.generateAnnColumnAnnotation(property)}
	</#if>
	</#if>
	${pojo.getPropertyGetModifiers(property)} ${pojo.getJavaTypeName(property, false)} ${pojo.getGetterSignature(property)}() {
			return this.${c2j.keyWordCheck(property.name)};
		}
		${pojo.getPropertySetModifiers(property)} void set${pojo.getPropertyName(property)}(${pojo.getJavaTypeName(property, false)} ${c2j.keyWordCheck(property.name)}) {
			this.${c2j.keyWordCheck(property.name)} = ${c2j.keyWordCheck(property.name)};
		}
	</#if>
	</#foreach>
	
<#-- // Property accessors ends here -->
<#-- //filter property -->
	/**
	 * @return the inquiryFilter
	 */
	@Transient
	public String getInquiryFilter() {
		return inquiryFilter;
	}

	/**
	 * @param inquiryFilter the inquiryFilter to set
	 */
	public void setInquiryFilter(String inquiryFilter) {
		this.inquiryFilter = inquiryFilter;
	}

		/**
	 * @return the primaryKeyDO
	 */
	@Transient
	public PrimaryKeyDO getPrimaryKeyDO() {
		return primaryKeyDO;
	}

	/**
	 * @param primaryKeyDO the primaryKeyDO to set
	 */
	public void setPrimaryKeyDO(PrimaryKeyDO primaryKeyDO) {
		this.primaryKeyDO = primaryKeyDO;
	}
	
<#if pojo.hasMetaAttribute("class-code")>  // The following is extra code specified in the hbm.xml files
${pojo.getExtraClassCode()}
  // end of extra code specified in the hbm.xml files
</#if>

}
</#assign>

${classbody}

