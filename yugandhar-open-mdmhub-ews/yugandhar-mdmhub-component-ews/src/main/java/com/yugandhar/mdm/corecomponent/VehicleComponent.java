package com.yugandhar.mdm.corecomponent;
/* Generated Jul 1, 2017 2:56:24 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.common.util.IgnoreAttributesUtil;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.RefCountryIsoDO;
import com.yugandhar.mdm.extern.dobj.VehicleDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model VehicleDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class VehicleComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	VehicleComponentRule theVehicleComponentRule;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public VehicleComponent() {
	}

	/**
	 * This method creates a record in database. generates the idpk if not
	 * provided in the request and populate the updatedByUser, updatedByTxnId,
	 * createdTsString, updatedTsString attributes.
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with persisted instance
	 * @throws YugandharCommonException
	 *             if VehicleDO object is not present in the request or other
	 *             mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theVehicleComponentRule.prevalidateVehicleCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID generator
			VehicleDO reqVehicleDO = (VehicleDO) txnTransferObj.getTxnPayload().getVehicleDO();
			if (null == reqVehicleDO.getPrimaryKeyDO() || null == reqVehicleDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqVehicleDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqVehicleDO.setIdPk(reqVehicleDO.getPrimaryKeyDO().getIdPk());
				VehicleDO dbimageVehicleDO = entityManager.find(VehicleDO.class, reqVehicleDO.getIdPk());
				if (null != dbimageVehicleDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"VehicleComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theVehicleComponentRule.preExecuteVehicleCompPersist(reqVehicleDO, txnTransferObj);
			entityManager.persist(reqVehicleDO);
			entityManager.flush();
			reqVehicleDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setVehicleDO(new VehicleDO(reqVehicleDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),respTxnTransferObj.getTxnPayload().getVehicleDO());
			theVehicleComponentRule.postExecuteVehicleCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"VehicleComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"VehicleComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"VehicleComponent.persist failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;
	}

	/**
	 * This service updates the record in database. populate the updatedByUser,
	 * updatedByTxnId, updatedTsString attributes
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if VehicleDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theVehicleComponentRule.PrevalidateVehicleCompMerge(txnTransferObj);
			VehicleDO reqVehicleDO = (VehicleDO) txnTransferObj.getTxnPayload().getVehicleDO();
			VehicleDO dbimageVehicleDO = entityManager.find(VehicleDO.class, reqVehicleDO.getIdPk());
			if (null == dbimageVehicleDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"VehicleComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqVehicleDO);
			BeanUtils.copyProperties(reqVehicleDO, dbimageVehicleDO, strIgnoreProperties);
			entityManager.detach(dbimageVehicleDO);
			theVehicleComponentRule.preExecuteVehicleCompMerge(reqVehicleDO, dbimageVehicleDO, txnTransferObj);
			VehicleDO mergedVehicleDO = entityManager.merge(dbimageVehicleDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setVehicleDO(new VehicleDO(mergedVehicleDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getVehicleDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theVehicleComponentRule.postExecuteVehicleCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in VehicleComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"VehicleComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"VehicleComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"VehicleComponent.merge failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;
	}

	/**
	 * This method search the database record based on primary key.
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if VehicleDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theVehicleComponentRule.prevalidateVehicleCompFindById(txnTransferObj);
			VehicleDO reqVehicleDO = (VehicleDO) txnTransferObj.getTxnPayload().getVehicleDO();
			VehicleDO dbimageVehicleDO = entityManager.find(VehicleDO.class, reqVehicleDO.getIdPk());
			if (null == dbimageVehicleDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"VehicleComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setVehicleDO(new VehicleDO(dbimageVehicleDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getVehicleDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theVehicleComponentRule.postExecuteVehicleCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"VehicleComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting VehicleDO object in the
	 * database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if VehicleDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getVehicleDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"VehicleDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getVehicleDO().getVinNumber()
				|| txnTransferObj.getTxnPayload().getVehicleDO().getVinNumber().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10021",
					"VehicleDO.vinNumber is needed in the request");
		}

		VehicleDO theVehicleDO = (VehicleDO) txnTransferObj.getTxnPayload().getVehicleDO();
		theVehicleDO.setCreatedTs(new Date());
		theVehicleDO.setUpdatedTs(new Date());
		theVehicleDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theVehicleDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating VehicleDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if VehicleDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getVehicleDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"VehicleDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getVehicleDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getVehicleDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"VehicleDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getVehicleDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"VehicleDO.version should not be null");
		}

		VehicleDO theVehicleDO = (VehicleDO) txnTransferObj.getTxnPayload().getVehicleDO();
		theVehicleDO.setUpdatedTs(new Date());
		theVehicleDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theVehicleDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that VehicleDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if VehicleDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getVehicleDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"VehicleDO is needed in the request");
		}
		VehicleDO theVehicleDO = (VehicleDO) txnTransferObj.getTxnPayload().getVehicleDO();
		if (null == theVehicleDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"VehicleDO.idpk should not be null");
		}

	}

	/*
	 * Sample Code
	 * 
	 * @Autowired protected RefEntityObjectTypeComponent
	 * theRefEntityObjectTypeComponent;
	 */

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage, VehicleDO theVehicleDO) {

		// CountryOfRegistrationRefkey
		if (!(null == theVehicleDO.getCountryOfRegistrationRefkey()
				|| theVehicleDO.getCountryOfRegistrationRefkey().isEmpty())) {
			RefCountryIsoDO theRefCountryIsoDO = referenceTableHelper.getRefCountryIsoValueByKey(requesterLanguage,
					theVehicleDO.getCountryOfRegistrationRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefCountryIsoDO) {
				theVehicleDO.setCountryOfRegistrationRefValue(theRefCountryIsoDO.getValue());
			}
		}

	}

}
