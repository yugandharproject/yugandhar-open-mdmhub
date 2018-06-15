package com.yugandhar.mdm.abstractdobj;
/* Generated Jul 1, 2017 2:56:24 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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
import com.yugandhar.mdm.extern.dobj.MiscellaneousInfoDO;

/**
 * Abstract DO class for MiscellaneousInfoDO class mapped to database MISCELLANEOUS_INFO entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.MiscellaneousInfoDO
*/

@MappedSuperclass
public abstract class AbstractMiscellaneousInfoDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String entityObjectTypeRefkey;
	protected String entityObjectTypeRefValue;
	protected String entityIdpk;
	protected String name1;
	protected String value1;
	protected String name2;
	protected String value2;
	protected String name3;
	protected String value3;
	protected String name4;
	protected String value4;
	protected String name5;
	protected String value5;
	protected String name6;
	protected String value6;
	protected String name7;
	protected String value7;
	protected String name8;
	protected String value8;
	protected String name9;
	protected String value9;
	protected String name10;
	protected String value10;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;
	
	public AbstractMiscellaneousInfoDO() {
	}

	public AbstractMiscellaneousInfoDO(MiscellaneousInfoDO theMiscellaneousInfoDO) {
		idPk = theMiscellaneousInfoDO.idPk;
		version = theMiscellaneousInfoDO.version;
		createdTs = theMiscellaneousInfoDO.createdTs;
		deletedTs = theMiscellaneousInfoDO.deletedTs;
		updatedTs = theMiscellaneousInfoDO.updatedTs;
		updatedByUser = theMiscellaneousInfoDO.updatedByUser;
		updatedByTxnId = theMiscellaneousInfoDO.updatedByTxnId;
		entityObjectTypeRefkey = theMiscellaneousInfoDO.entityObjectTypeRefkey;
		entityIdpk = theMiscellaneousInfoDO.entityIdpk;
		name1 = theMiscellaneousInfoDO.name1;
		value1 = theMiscellaneousInfoDO.value1;
		name2 = theMiscellaneousInfoDO.name2;
		value2 = theMiscellaneousInfoDO.value2;
		name3 = theMiscellaneousInfoDO.name3;
		value3 = theMiscellaneousInfoDO.value3;
		name4 = theMiscellaneousInfoDO.name4;
		value4 = theMiscellaneousInfoDO.value4;
		name5 = theMiscellaneousInfoDO.name5;
		value5 = theMiscellaneousInfoDO.value5;
		name6 = theMiscellaneousInfoDO.name6;
		value6 = theMiscellaneousInfoDO.value6;
		name7 = theMiscellaneousInfoDO.name7;
		value7 = theMiscellaneousInfoDO.value7;
		name8 = theMiscellaneousInfoDO.name8;
		value8 = theMiscellaneousInfoDO.value8;
		name9 = theMiscellaneousInfoDO.name9;
		value9 = theMiscellaneousInfoDO.value9;
		name10 = theMiscellaneousInfoDO.name10;
		value10 = theMiscellaneousInfoDO.value10;
		inquiryFilter = theMiscellaneousInfoDO.inquiryFilter;
	}

	@Id

	@Column(name = "ID_PK", unique = true, nullable = false, length = 50)
	public String getIdPk() {
		return this.idPk;
	}

	public void setIdPk(String idPk) {
		this.idPk = idPk;
	}

	@Version
	@Column(name = "VERSION", nullable = false, precision = 22, scale = 0)
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_TS", nullable = false)
	public Date getCreatedTs() {
		return this.createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DELETED_TS")
	public Date getDeletedTs() {
		return this.deletedTs;
	}

	public void setDeletedTs(Date deletedTs) {
		this.deletedTs = deletedTs;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_TS", nullable = false)
	public Date getUpdatedTs() {
		return this.updatedTs;
	}

	public void setUpdatedTs(Date updatedTs) {
		this.updatedTs = updatedTs;
	}

	@Column(name = "UPDATED_BY_USER", nullable = false, length = 50)
	public String getUpdatedByUser() {
		return this.updatedByUser;
	}

	public void setUpdatedByUser(String updatedByUser) {
		this.updatedByUser = updatedByUser;
	}

	@Column(name = "UPDATED_BY_TXN_ID", length = 100)
	public String getUpdatedByTxnId() {
		return this.updatedByTxnId;
	}

	public void setUpdatedByTxnId(String updatedByTxnId) {
		this.updatedByTxnId = updatedByTxnId;
	}

	@Column(name = "ENTITY_OBJECT_TYPE_REFKEY", nullable = false, length = 50)
	public String getEntityObjectTypeRefkey() {
		return this.entityObjectTypeRefkey;
	}

	public void setEntityObjectTypeRefkey(String entityObjectTypeRefkey) {
		this.entityObjectTypeRefkey = entityObjectTypeRefkey;
	}

	@Column(name = "ENTITY_IDPK", nullable = false, length = 50)
	public String getEntityIdpk() {
		return this.entityIdpk;
	}

	public void setEntityIdpk(String entityIdpk) {
		this.entityIdpk = entityIdpk;
	}

	@Column(name = "NAME1", nullable = false, length = 50)
	public String getName1() {
		return this.name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	@Column(name = "VALUE1", nullable = false, length = 50)
	public String getValue1() {
		return this.value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	@Column(name = "NAME2", length = 50)
	public String getName2() {
		return this.name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	@Column(name = "VALUE2", length = 50)
	public String getValue2() {
		return this.value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	@Column(name = "NAME3", length = 50)
	public String getName3() {
		return this.name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	@Column(name = "VALUE3", length = 50)
	public String getValue3() {
		return this.value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	@Column(name = "NAME4", length = 50)
	public String getName4() {
		return this.name4;
	}

	public void setName4(String name4) {
		this.name4 = name4;
	}

	@Column(name = "VALUE4", length = 50)
	public String getValue4() {
		return this.value4;
	}

	public void setValue4(String value4) {
		this.value4 = value4;
	}

	@Column(name = "NAME5", length = 50)
	public String getName5() {
		return this.name5;
	}

	public void setName5(String name5) {
		this.name5 = name5;
	}

	@Column(name = "VALUE5", length = 50)
	public String getValue5() {
		return this.value5;
	}

	public void setValue5(String value5) {
		this.value5 = value5;
	}

	@Column(name = "NAME6", length = 50)
	public String getName6() {
		return this.name6;
	}

	public void setName6(String name6) {
		this.name6 = name6;
	}

	@Column(name = "VALUE6", length = 50)
	public String getValue6() {
		return this.value6;
	}

	public void setValue6(String value6) {
		this.value6 = value6;
	}

	@Column(name = "NAME7", length = 50)
	public String getName7() {
		return this.name7;
	}

	public void setName7(String name7) {
		this.name7 = name7;
	}

	@Column(name = "VALUE7", length = 50)
	public String getValue7() {
		return this.value7;
	}

	public void setValue7(String value7) {
		this.value7 = value7;
	}

	@Column(name = "NAME8", length = 50)
	public String getName8() {
		return this.name8;
	}

	public void setName8(String name8) {
		this.name8 = name8;
	}

	@Column(name = "VALUE8", length = 50)
	public String getValue8() {
		return this.value8;
	}

	public void setValue8(String value8) {
		this.value8 = value8;
	}

	@Column(name = "NAME9", length = 50)
	public String getName9() {
		return this.name9;
	}

	public void setName9(String name9) {
		this.name9 = name9;
	}

	@Column(name = "VALUE9", length = 50)
	public String getValue9() {
		return this.value9;
	}

	public void setValue9(String value9) {
		this.value9 = value9;
	}

	@Column(name = "NAME10", length = 50)
	public String getName10() {
		return this.name10;
	}

	public void setName10(String name10) {
		this.name10 = name10;
	}

	@Column(name = "VALUE10", length = 50)
	public String getValue10() {
		return this.value10;
	}

	public void setValue10(String value10) {
		this.value10 = value10;
	}

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

	/**
	 * @return the entityObjectTypeRefValue
	 */
	@Transient
	public String getEntityObjectTypeRefValue() {
		return entityObjectTypeRefValue;
	}

	/**
	 * @param entityObjectTypeRefValue the entityObjectTypeRefValue to set
	 */
	public void setEntityObjectTypeRefValue(String entityObjectTypeRefValue) {
		this.entityObjectTypeRefValue = entityObjectTypeRefValue;
	}
	
	
	
}
