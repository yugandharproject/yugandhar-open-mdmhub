package com.yugandhar.mdm.abstractdobj;
/* Generated Sep 5, 2017 1:54:23 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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
import com.yugandhar.mdm.extern.dobj.RefPersonTypeDO;

/**
 * Abstract DO class for RefPersonTypeDO class mapped to database REF_PERSON_TYPE entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.RefPersonTypeDO
*/

@MappedSuperclass
public abstract class AbstractRefPersonTypeDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String configLanguageCodeKey;
	protected String key;
	protected String value;
	protected String description;
	protected String inquiryFilter;
	protected PrimaryKeyDO primaryKeyDO;

	public AbstractRefPersonTypeDO() {
	}

	public AbstractRefPersonTypeDO(RefPersonTypeDO theRefPersonTypeDO) {
		idPk = theRefPersonTypeDO.idPk;
		version = theRefPersonTypeDO.version;
		createdTs = theRefPersonTypeDO.createdTs;
		deletedTs = theRefPersonTypeDO.deletedTs;
		updatedTs = theRefPersonTypeDO.updatedTs;
		updatedByUser = theRefPersonTypeDO.updatedByUser;
		updatedByTxnId = theRefPersonTypeDO.updatedByTxnId;
		configLanguageCodeKey = theRefPersonTypeDO.configLanguageCodeKey;
		key = theRefPersonTypeDO.key;
		value = theRefPersonTypeDO.value;
		description = theRefPersonTypeDO.description;
		inquiryFilter = theRefPersonTypeDO.inquiryFilter;
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

	@Column(name = "CONFIG_LANGUAGE_CODE_KEY", nullable = false, length = 50)
	public String getConfigLanguageCodeKey() {
		return this.configLanguageCodeKey;
	}

	public void setConfigLanguageCodeKey(String configLanguageCodeKey) {
		this.configLanguageCodeKey = configLanguageCodeKey;
	}

	@Column(name = "KEY", nullable = false, length = 50)
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "VALUE", nullable = false, length = 50)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "DESCRIPTION", length = 50)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}
