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
import com.yugandhar.mdm.extern.dobj.AddressDO;
import com.yugandhar.mdm.extern.dobj.RefCountryIsoDO;
import com.yugandhar.mdm.extern.dobj.RefStateProvinceDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model AddressDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class AddressComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	AddressComponentRule theAddressComponentRule;

	@Autowired
	ReferenceTableHelper referenceTableHelper;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public AddressComponent() {
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
	 *             if AddressDO object is not present in the request or other
	 *             mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theAddressComponentRule.prevalidateAddressCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID generator
			AddressDO reqAddressDO = (AddressDO) txnTransferObj.getTxnPayload().getAddressDO();
			if (null == reqAddressDO.getPrimaryKeyDO() || null == reqAddressDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqAddressDO.setIdPk(yugandharKeygenerator.generateKey());
			}else
			{
			reqAddressDO.setIdPk(reqAddressDO.getPrimaryKeyDO().getIdPk());
				AddressDO dbimageAddressDO = entityManager.find(AddressDO.class, reqAddressDO.getIdPk());
				if (null != dbimageAddressDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"AddressComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theAddressComponentRule.preExecuteAddressCompPersist(reqAddressDO, txnTransferObj);
			entityManager.persist(reqAddressDO);
			entityManager.flush();
			reqAddressDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setAddressDO(new AddressDO(reqAddressDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),respTxnTransferObj.getTxnPayload().getAddressDO());
			theAddressComponentRule.postExecuteAddressCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"AddressComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"AddressComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AddressComponent.persist failed unexpectedly");
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
	 *             if AddressDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theAddressComponentRule.PrevalidateAddressCompMerge(txnTransferObj);
			AddressDO reqAddressDO = (AddressDO) txnTransferObj.getTxnPayload().getAddressDO();
			AddressDO dbimageAddressDO = entityManager.find(AddressDO.class, reqAddressDO.getIdPk());
			if (null == dbimageAddressDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AddressComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqAddressDO);
			BeanUtils.copyProperties(reqAddressDO, dbimageAddressDO, strIgnoreProperties);
			entityManager.detach(dbimageAddressDO);
			theAddressComponentRule.preExecuteAddressCompMerge(reqAddressDO, dbimageAddressDO, txnTransferObj);
			AddressDO mergedAddressDO = entityManager.merge(dbimageAddressDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setAddressDO(new AddressDO(mergedAddressDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getAddressDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theAddressComponentRule.postExecuteAddressCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in AddressComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"AddressComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"AddressComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AddressComponent.merge failed unexpectedly");
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
	 *             if AddressDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theAddressComponentRule.prevalidateAddressCompFindById(txnTransferObj);
			AddressDO reqAddressDO = (AddressDO) txnTransferObj.getTxnPayload().getAddressDO();
			AddressDO dbimageAddressDO = entityManager.find(AddressDO.class, reqAddressDO.getIdPk());
			if (null == dbimageAddressDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AddressComponent.findbyId failed with Validation Exception - No Record found for Address Idpk");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setAddressDO(new AddressDO(dbimageAddressDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getAddressDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theAddressComponentRule.postExecuteAddressCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AddressComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting AddressDO object in the
	 * database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AddressDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getAddressDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AddressDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getAddressDO().getAddressLineOne()
				|| txnTransferObj.getTxnPayload().getAddressDO().getAddressLineOne().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10009",
					"AddressDO.addressLineOne is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getAddressDO().getCountryRefkey()
				|| txnTransferObj.getTxnPayload().getAddressDO().getCountryRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10051",
					"AddressDO.countryRefkey is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getAddressDO().getCity()
				|| txnTransferObj.getTxnPayload().getAddressDO().getCity().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10052",
					"AddressDO.city is needed in the request");
		}

		AddressDO theAddressDO = (AddressDO) txnTransferObj.getTxnPayload().getAddressDO();
		theAddressDO.setCreatedTs(new Date());
		theAddressDO.setUpdatedTs(new Date());
		theAddressDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theAddressDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating AddressDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AddressDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getAddressDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AddressDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getAddressDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getAddressDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"AddressDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getAddressDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"AddressDO.version should not be null");
		}

		AddressDO theAddressDO = (AddressDO) txnTransferObj.getTxnPayload().getAddressDO();
		theAddressDO.setUpdatedTs(new Date());
		theAddressDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theAddressDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that AddressDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AddressDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getAddressDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AddressDO is needed in the request");
		}
		AddressDO theAddressDO = (AddressDO) txnTransferObj.getTxnPayload().getAddressDO();
		if (null == theAddressDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"AddressDO.idpk should not be null");
		}

	}


	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage, AddressDO theAddressDO) {



		// stateProvinceRefkey
			if (!(null == theAddressDO.getStateProvinceRefkey()
					|| theAddressDO.getStateProvinceRefkey().isEmpty())) {
				RefStateProvinceDO theRefStateProvinceDO = referenceTableHelper.getRefStateProvinceValueByKey(requesterLanguage,
						theAddressDO.getStateProvinceRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
				if (null != theRefStateProvinceDO) {
					theAddressDO.setStateProvinceRefValue(theRefStateProvinceDO.getValue());
				}
			}

			// CountryRefkey
			if (!(null == theAddressDO.getCountryRefkey()
					|| theAddressDO.getCountryRefkey().isEmpty())) {
				RefCountryIsoDO theRefCountryDO = referenceTableHelper.getRefCountryIsoValueByKey(
						requesterLanguage, theAddressDO.getCountryRefkey(),
						yugandharConstants.FILTER_VALUE_ACTIVE);
				if (null != theRefCountryDO) {
					theAddressDO.setCountryRefValue(theRefCountryDO.getValue());
				}
			}
			
		
			
		

	}

}
