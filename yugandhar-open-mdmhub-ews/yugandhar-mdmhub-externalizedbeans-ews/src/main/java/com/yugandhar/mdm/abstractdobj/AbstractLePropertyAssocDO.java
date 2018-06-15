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

import com.yugandhar.mdm.extern.dobj.PropertyDO;
import com.yugandhar.mdm.misc.dobj.PrimaryKeyDO;
import com.yugandhar.mdm.extern.dobj.LePropertyAssocDO;

/**
 * Abstract DO class for LePropertyAssocDO class mapped to database LE_PROPERTY_ASSOC entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.LePropertyAssocDO
*/

@MappedSuperclass
public abstract class AbstractLePropertyAssocDO implements java.io.Serializable {

	protected static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String propertyIdpk;
	protected String legalentityIdpk;
	protected String propertyLeReltypeRefkey;
	protected String propertyLeReltypeRefValue;
	protected String description;
	protected String inquiryFilter;
	protected PropertyDO propertyDO;
	protected PrimaryKeyDO primaryKeyDO;
	
	public AbstractLePropertyAssocDO() {
	}

	public AbstractLePropertyAssocDO(LePropertyAssocDO theLePropertyAssocDO) {
		idPk = theLePropertyAssocDO.idPk;
		version = theLePropertyAssocDO.version;
		createdTs = theLePropertyAssocDO.createdTs;
		deletedTs = theLePropertyAssocDO.deletedTs;
		updatedTs = theLePropertyAssocDO.updatedTs;
		updatedByUser = theLePropertyAssocDO.updatedByUser;
		updatedByTxnId = theLePropertyAssocDO.updatedByTxnId;
		propertyIdpk = theLePropertyAssocDO.propertyIdpk;
		legalentityIdpk = theLePropertyAssocDO.legalentityIdpk;
		propertyLeReltypeRefkey = theLePropertyAssocDO.propertyLeReltypeRefkey;
		description = theLePropertyAssocDO.description;
		inquiryFilter = theLePropertyAssocDO.inquiryFilter;
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

	@Column(name = "PROPERTY_IDPK", nullable = false, length = 50)
	public String getPropertyIdpk() {
		return this.propertyIdpk;
	}

	public void setPropertyIdpk(String propertyIdpk) {
		this.propertyIdpk = propertyIdpk;
	}

	@Column(name = "LEGALENTITY_IDPK", nullable = false, length = 50)
	public String getLegalentityIdpk() {
		return this.legalentityIdpk;
	}

	public void setLegalentityIdpk(String legalentityIdpk) {
		this.legalentityIdpk = legalentityIdpk;
	}

	@Column(name = "PROPERTY_LE_RELTYPE_REFKEY", nullable = false, length = 50)
	public String getPropertyLeReltypeRefkey() {
		return this.propertyLeReltypeRefkey;
	}

	public void setPropertyLeReltypeRefkey(String propertyLeReltypeRefkey) {
		this.propertyLeReltypeRefkey = propertyLeReltypeRefkey;
	}

	@Column(name = "DESCRIPTION", length = 100)
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
	 * @return the propertyDO
	 */
	@Transient
	public PropertyDO getPropertyDO() {
		return propertyDO;
	}

	/**
	 * @param propertyDO the propertyDO to set
	 */
	public void setPropertyDO(PropertyDO propertyDO) {
		this.propertyDO = propertyDO;
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
	 * @return the propertyLeReltypeRefValue
	 */
	@Transient
	public String getPropertyLeReltypeRefValue() {
		return propertyLeReltypeRefValue;
	}

	/**
	 * @param propertyLeReltypeRefValue the propertyLeReltypeRefValue to set
	 */
	public void setPropertyLeReltypeRefValue(String propertyLeReltypeRefValue) {
		this.propertyLeReltypeRefValue = propertyLeReltypeRefValue;
	}
	
	

}
