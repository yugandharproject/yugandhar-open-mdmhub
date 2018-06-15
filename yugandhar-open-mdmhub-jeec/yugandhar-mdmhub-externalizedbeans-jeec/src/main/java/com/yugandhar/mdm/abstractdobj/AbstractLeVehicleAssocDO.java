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

import com.yugandhar.mdm.extern.dobj.VehicleDO;
import com.yugandhar.mdm.misc.dobj.PrimaryKeyDO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yugandhar.mdm.extern.dobj.LeVehicleAssocDO;

/**
 * Abstract DO class for LeVehicleAssocDO class mapped to database LE_VEHICLE_ASSOC entity
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see com.yugandhar.mdm.extern.dobj.LeVehicleAssocDO
*/

@MappedSuperclass
public abstract class AbstractLeVehicleAssocDO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	protected String idPk;
	protected Integer version;
	protected Date createdTs;
	protected Date deletedTs;
	protected Date updatedTs;
	protected String updatedByUser;
	protected String updatedByTxnId;
	protected String legalentityIdpk;
	protected String vehicleIdpk;
	protected String leRoletypeRefkey;
	protected String leRoletypeRefValue;
	protected String accountIdpk;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected Date roleActivationDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected Date roleDeactivationDate;
	protected String deactivationReasonRefkey;
	protected String deactivationReasonRefValue;
	protected String agreementTypeRefkey;
	protected String agreementTypeRefValue;
	protected String agreementTypeDescription;
	protected String inquiryFilter;
	protected VehicleDO vehicleDO;
	protected PrimaryKeyDO primaryKeyDO;
	
	public AbstractLeVehicleAssocDO() {
	}

	public AbstractLeVehicleAssocDO(LeVehicleAssocDO theLeVehicleAssocDO) {
		idPk = theLeVehicleAssocDO.idPk;
		version = theLeVehicleAssocDO.version;
		createdTs = theLeVehicleAssocDO.createdTs;
		deletedTs = theLeVehicleAssocDO.deletedTs;
		updatedTs = theLeVehicleAssocDO.updatedTs;
		updatedByUser = theLeVehicleAssocDO.updatedByUser;
		updatedByTxnId = theLeVehicleAssocDO.updatedByTxnId;
		legalentityIdpk = theLeVehicleAssocDO.legalentityIdpk;
		leRoletypeRefkey = theLeVehicleAssocDO.leRoletypeRefkey;
		accountIdpk = theLeVehicleAssocDO.accountIdpk;
		roleActivationDate = theLeVehicleAssocDO.roleActivationDate;
		roleDeactivationDate = theLeVehicleAssocDO.roleDeactivationDate;
		deactivationReasonRefkey = theLeVehicleAssocDO.deactivationReasonRefkey;
		agreementTypeRefkey = theLeVehicleAssocDO.agreementTypeRefkey;
		agreementTypeDescription = theLeVehicleAssocDO.agreementTypeDescription;
		vehicleIdpk = theLeVehicleAssocDO.vehicleIdpk;
		inquiryFilter = theLeVehicleAssocDO.inquiryFilter;
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

	@Column(name = "LEGALENTITY_IDPK", nullable = false, length = 50)
	public String getLegalentityIdpk() {
		return this.legalentityIdpk;
	}

	public void setLegalentityIdpk(String legalentityIdpk) {
		this.legalentityIdpk = legalentityIdpk;
	}

	@Column(name = "VEHICLE_IDPK", nullable = false, length = 50)
	public String getVehicleIdpk() {
		return this.vehicleIdpk;
	}

	public void setVehicleIdpk(String vehicleIdpk) {
		this.vehicleIdpk = vehicleIdpk;
	}
	
	@Column(name = "LE_ROLETYPE_REFKEY", nullable = false, length = 50)
	public String getLeRoletypeRefkey() {
		return this.leRoletypeRefkey;
	}

	public void setLeRoletypeRefkey(String leRoletypeRefkey) {
		this.leRoletypeRefkey = leRoletypeRefkey;
	}

	@Column(name = "ACCOUNT_IDPK", length = 50)
	public String getAccountIdpk() {
		return this.accountIdpk;
	}

	public void setAccountIdpk(String accountIdpk) {
		this.accountIdpk = accountIdpk;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ROLE_ACTIVATION_DATE", length = 7)
	public Date getRoleActivationDate() {
		return this.roleActivationDate;
	}

	public void setRoleActivationDate(Date roleActivationDate) {
		this.roleActivationDate = roleActivationDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ROLE_DEACTIVATION_DATE", length = 7)
	public Date getRoleDeactivationDate() {
		return this.roleDeactivationDate;
	}

	public void setRoleDeactivationDate(Date roleDeactivationDate) {
		this.roleDeactivationDate = roleDeactivationDate;
	}

	@Column(name = "DEACTIVATION_REASON_REFKEY", length = 50)
	public String getDeactivationReasonRefkey() {
		return this.deactivationReasonRefkey;
	}

	public void setDeactivationReasonRefkey(String deactivationReasonRefkey) {
		this.deactivationReasonRefkey = deactivationReasonRefkey;
	}

	@Column(name = "AGREEMENT_TYPE_REFKEY", length = 50)
	public String getAgreementTypeRefkey() {
		return this.agreementTypeRefkey;
	}

	public void setAgreementTypeRefkey(String agreementTypeRefkey) {
		this.agreementTypeRefkey = agreementTypeRefkey;
	}

	@Column(name = "AGREEMENT_TYPE_DESCRIPTION", length = 100)
	public String getAgreementTypeDescription() {
		return this.agreementTypeDescription;
	}

	public void setAgreementTypeDescription(String agreementTypeDescription) {
		this.agreementTypeDescription = agreementTypeDescription;
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
	 * @return the vehicleDO
	 */
	@Transient
	public VehicleDO getVehicleDO() {
		return vehicleDO;
	}

	/**
	 * @param vehicleDO the vehicleDO to set
	 */
	public void setVehicleDO(VehicleDO vehicleDO) {
		this.vehicleDO = vehicleDO;
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
	 * @return the leRoletypeRefValue
	 */
	@Transient
	public String getLeRoletypeRefValue() {
		return leRoletypeRefValue;
	}

	/**
	 * @param leRoletypeRefValue the leRoletypeRefValue to set
	 */
	public void setLeRoletypeRefValue(String leRoletypeRefValue) {
		this.leRoletypeRefValue = leRoletypeRefValue;
	}

	/**
	 * @return the deactivationReasonRefValue
	 */
	@Transient
	public String getDeactivationReasonRefValue() {
		return deactivationReasonRefValue;
	}

	/**
	 * @param deactivationReasonRefValue the deactivationReasonRefValue to set
	 */
	public void setDeactivationReasonRefValue(String deactivationReasonRefValue) {
		this.deactivationReasonRefValue = deactivationReasonRefValue;
	}

	/**
	 * @return the agreementTypeRefValue
	 */
	@Transient
	public String getAgreementTypeRefValue() {
		return agreementTypeRefValue;
	}

	/**
	 * @param agreementTypeRefValue the agreementTypeRefValue to set
	 */
	public void setAgreementTypeRefValue(String agreementTypeRefValue) {
		this.agreementTypeRefValue = agreementTypeRefValue;
	}
	
	
}
