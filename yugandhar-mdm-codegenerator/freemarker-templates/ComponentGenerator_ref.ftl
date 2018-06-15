${pojo.getPackageDeclaration()}
/* Generated ${date} by Hibernate Tools ${version} using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheResult;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.common.util.IgnoreAttributesUtil;
import com.yugandhar.common.util.YugandharConfigurationProperties;
import com.yugandhar.mdm.config.app.properties.ConfigAppPropertiesComponent;
import com.yugandhar.mdm.config.langcode.ConfigLanguageCodeComponent;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.extern.dobj.ConfigLanguageCodeDO;
import com.yugandhar.mdm.extern.dobj.${pojo.getDeclarationName()}DO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model ${pojo.getDeclarationName()}DO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * @see Documentation
 */

<#assign classbody>

@Scope(value = "prototype")
@Component
public class ${pojo.getDeclarationName()}Component {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private ${pojo.getDeclarationName()}Repository the${pojo.getDeclarationName()}Repository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	${pojo.getDeclarationName()}ComponentRule the${pojo.getDeclarationName()}ComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public ${pojo.getDeclarationName()}Component() {
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
	 *             if ${pojo.getDeclarationName()}DO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			the${pojo.getDeclarationName()}ComponentRule.prevalidate${pojo.getDeclarationName()}CompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			${pojo.getDeclarationName()}DO req${pojo.getDeclarationName()}DO = (${pojo.getDeclarationName()}DO) txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO();
			if (null == req${pojo.getDeclarationName()}DO.getPrimaryKeyDO() || null == req${pojo.getDeclarationName()}DO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				req${pojo.getDeclarationName()}DO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				req${pojo.getDeclarationName()}DO.setIdPk(req${pojo.getDeclarationName()}DO.getPrimaryKeyDO().getIdPk());
				${pojo.getDeclarationName()}DO dbimage${pojo.getDeclarationName()}DO = entityManager.find(${pojo.getDeclarationName()}DO.class,
						req${pojo.getDeclarationName()}DO.getIdPk());
				if (null != dbimage${pojo.getDeclarationName()}DO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"${pojo.getDeclarationName()}Component.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			the${pojo.getDeclarationName()}ComponentRule.preExecute${pojo.getDeclarationName()}CompPersist(req${pojo.getDeclarationName()}DO, txnTransferObj);
			entityManager.persist(req${pojo.getDeclarationName()}DO);
			entityManager.flush();
			req${pojo.getDeclarationName()}DO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().set${pojo.getDeclarationName()}DO(new ${pojo.getDeclarationName()}DO(req${pojo.getDeclarationName()}DO));
			the${pojo.getDeclarationName()}ComponentRule.postExecute${pojo.getDeclarationName()}CompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"${pojo.getDeclarationName()}Component.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"${pojo.getDeclarationName()}Component.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"${pojo.getDeclarationName()}Component.persist failed unexpectedly");
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
	 *             if ${pojo.getDeclarationName()}DO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			the${pojo.getDeclarationName()}ComponentRule.Prevalidate${pojo.getDeclarationName()}CompMerge(txnTransferObj);
			${pojo.getDeclarationName()}DO req${pojo.getDeclarationName()}DO = (${pojo.getDeclarationName()}DO) txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO();
			${pojo.getDeclarationName()}DO dbimage${pojo.getDeclarationName()}DO = entityManager.find(${pojo.getDeclarationName()}DO.class, req${pojo.getDeclarationName()}DO.getIdPk());
			if (null == dbimage${pojo.getDeclarationName()}DO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"${pojo.getDeclarationName()}Component.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(req${pojo.getDeclarationName()}DO);
			BeanUtils.copyProperties(req${pojo.getDeclarationName()}DO, dbimage${pojo.getDeclarationName()}DO, strIgnoreProperties);
			entityManager.detach(dbimage${pojo.getDeclarationName()}DO);
			the${pojo.getDeclarationName()}ComponentRule.preExecute${pojo.getDeclarationName()}CompMerge(req${pojo.getDeclarationName()}DO, dbimage${pojo.getDeclarationName()}DO,
					txnTransferObj);
			${pojo.getDeclarationName()}DO merged${pojo.getDeclarationName()}DO = entityManager.merge(dbimage${pojo.getDeclarationName()}DO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().set${pojo.getDeclarationName()}DO(new ${pojo.getDeclarationName()}DO(merged${pojo.getDeclarationName()}DO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			the${pojo.getDeclarationName()}ComponentRule.postExecute${pojo.getDeclarationName()}CompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in ${pojo.getDeclarationName()}Component.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"${pojo.getDeclarationName()}Component.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"${pojo.getDeclarationName()}Component.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"${pojo.getDeclarationName()}Component.merge failed unexpectedly");
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
	 *             if ${pojo.getDeclarationName()}DO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			the${pojo.getDeclarationName()}ComponentRule.prevalidate${pojo.getDeclarationName()}CompFindById(txnTransferObj);
			${pojo.getDeclarationName()}DO req${pojo.getDeclarationName()}DO = (${pojo.getDeclarationName()}DO) txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO();
			${pojo.getDeclarationName()}DO dbimage${pojo.getDeclarationName()}DO = entityManager.find(${pojo.getDeclarationName()}DO.class, req${pojo.getDeclarationName()}DO.getIdPk());
			if (null == dbimage${pojo.getDeclarationName()}DO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"${pojo.getDeclarationName()}Component.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().set${pojo.getDeclarationName()}DO(new ${pojo.getDeclarationName()}DO(dbimage${pojo.getDeclarationName()}DO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			the${pojo.getDeclarationName()}ComponentRule.postExecute${pojo.getDeclarationName()}CompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"${pojo.getDeclarationName()}Component.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting ${pojo.getDeclarationName()}DO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if ${pojo.getDeclarationName()}DO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"${pojo.getDeclarationName()}DO is needed in the request");
		}

		${pojo.getDeclarationName()}DO the${pojo.getDeclarationName()}DO = (${pojo.getDeclarationName()}DO) txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO();
		if (null == the${pojo.getDeclarationName()}DO.getKey() || the${pojo.getDeclarationName()}DO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"${pojo.getDeclarationName()}DO.key should not be null");
		}

		if (null == the${pojo.getDeclarationName()}DO.getConfigLanguageCodeKey()
				|| the${pojo.getDeclarationName()}DO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"${pojo.getDeclarationName()}DO.configLanguageCodeKey should not be null");
		}

		if (null == the${pojo.getDeclarationName()}DO.getValue() || the${pojo.getDeclarationName()}DO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"${pojo.getDeclarationName()}DO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == the${pojo.getDeclarationName()}DO.getConfigLanguageCodeKey()
				|| the${pojo.getDeclarationName()}DO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(the${pojo.getDeclarationName()}DO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"${pojo.getDeclarationName()}DO.configLanguageCodeKey is not valid");
			}
		}

		the${pojo.getDeclarationName()}DO.setCreatedTs(new Date());
		the${pojo.getDeclarationName()}DO.setUpdatedTs(new Date());
		the${pojo.getDeclarationName()}DO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		the${pojo.getDeclarationName()}DO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating ${pojo.getDeclarationName()}DO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if ${pojo.getDeclarationName()}DO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"${pojo.getDeclarationName()}DO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO().getIdPk()
				|| txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"${pojo.getDeclarationName()}DO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"${pojo.getDeclarationName()}DO.version should not be null");
		}

		${pojo.getDeclarationName()}DO the${pojo.getDeclarationName()}DO = (${pojo.getDeclarationName()}DO) txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO();
		if (null == the${pojo.getDeclarationName()}DO.getKey() || the${pojo.getDeclarationName()}DO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"${pojo.getDeclarationName()}DO.key should not be null");
		}

		if (null == the${pojo.getDeclarationName()}DO.getConfigLanguageCodeKey()
				|| the${pojo.getDeclarationName()}DO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"${pojo.getDeclarationName()}DO.configLanguageCodeKey should not be null");
		}

		if (null == the${pojo.getDeclarationName()}DO.getValue() || the${pojo.getDeclarationName()}DO.getValue().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1011",
					"${pojo.getDeclarationName()}DO.Value should not be null");
		}

		// configLanguageCodeKey
		if (!(null == the${pojo.getDeclarationName()}DO.getConfigLanguageCodeKey()
				|| the${pojo.getDeclarationName()}DO.getConfigLanguageCodeKey().isEmpty())) {
			ConfigLanguageCodeDO theConfigLanguageCodeDO = theConfigLanguageCodeComponent
					.getConfigLanguageCodeValueByKey(the${pojo.getDeclarationName()}DO.getConfigLanguageCodeKey(),
							yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null == theConfigLanguageCodeDO) {

				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1012",
						"${pojo.getDeclarationName()}DO.configLanguageCodeKey is not valid");
			}
		}

		the${pojo.getDeclarationName()}DO.setUpdatedTs(new Date());
		the${pojo.getDeclarationName()}DO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		the${pojo.getDeclarationName()}DO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that ${pojo.getDeclarationName()}DO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if ${pojo.getDeclarationName()}DO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"${pojo.getDeclarationName()}DO is needed in the request");
		}
		${pojo.getDeclarationName()}DO the${pojo.getDeclarationName()}DO = (${pojo.getDeclarationName()}DO) txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO();
		if (null == the${pojo.getDeclarationName()}DO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"${pojo.getDeclarationName()}DO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for ${pojo.getDeclarationName()}DO entity based on
	 * language code and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if ${pojo.getDeclarationName()}DO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByLanguageCode(txnTransferObj);
			Page<${pojo.getDeclarationName()}DO> page${pojo.getDeclarationName()}DO = findAllRecordsByLanguageCodeFromRepository(
					txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO().getConfigLanguageCodeKey(),
					txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if(page${pojo.getDeclarationName()}DO.getTotalPages() == 0){
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"${pojo.getDeclarationName()} reference list does not have any records in the database");
			}			
			
			if ((page${pojo.getDeclarationName()}DO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for ${pojo.getDeclarationName()}, total number of pages is "
								+ page${pojo.getDeclarationName()}DO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(page${pojo.getDeclarationName()}DO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(page${pojo.getDeclarationName()}DO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(page${pojo.getDeclarationName()}DO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(page${pojo.getDeclarationName()}DO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(page${pojo.getDeclarationName()}DO.getSize());

			List<${pojo.getDeclarationName()}DO> dbimage${pojo.getDeclarationName()}DOlist = page${pojo.getDeclarationName()}DO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<${pojo.getDeclarationName()}DO> cloned${pojo.getDeclarationName()}DOList = null;
			if (null != dbimage${pojo.getDeclarationName()}DOlist && dbimage${pojo.getDeclarationName()}DOlist.size() > 0) {
				cloned${pojo.getDeclarationName()}DOList = new ArrayList<${pojo.getDeclarationName()}DO>();
				Iterator<${pojo.getDeclarationName()}DO> itr = dbimage${pojo.getDeclarationName()}DOlist.iterator();
				while (itr.hasNext()) {
					${pojo.getDeclarationName()}DO the${pojo.getDeclarationName()}DO = new ${pojo.getDeclarationName()}DO(itr.next());
					cloned${pojo.getDeclarationName()}DOList.add(the${pojo.getDeclarationName()}DO);
				}
			}

			if (null == cloned${pojo.getDeclarationName()}DOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"${pojo.getDeclarationName()} reference list does not have any records in the database");
				// Record not found
			} else if (cloned${pojo.getDeclarationName()}DOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"${pojo.getDeclarationName()} reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().set${pojo.getDeclarationName()}DOList(cloned${pojo.getDeclarationName()}DOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"${pojo.getDeclarationName()}Component.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "${pojo.getDeclarationName().toUpperCase()}_BUSKEY")
	public Page<${pojo.getDeclarationName()}DO> findAllRecordsByLanguageCodeFromRepository(@CacheKey String configLanguageCodeKey,
			@CacheKey String filter, @CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize,
			TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<${pojo.getDeclarationName()}DO> page${pojo.getDeclarationName()}DO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			page${pojo.getDeclarationName()}DO = this.the${pojo.getDeclarationName()}Repository.findAllActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			page${pojo.getDeclarationName()}DO = this.the${pojo.getDeclarationName()}Repository.findAllInActiveByLanguageCode(configLanguageCodeKey,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			page${pojo.getDeclarationName()}DO = this.the${pojo.getDeclarationName()}Repository.findAllByLanguageCode(configLanguageCodeKey,
					localPageable);

		}

		return page${pojo.getDeclarationName()}DO;
	}

	/**
	 * This method search the database table records based on
	 * business key (e.g.LanguageCode and Key)
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if ${pojo.getDeclarationName()}DO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			${pojo.getDeclarationName()}DO req${pojo.getDeclarationName()}DO = (${pojo.getDeclarationName()}DO) txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO();
			the${pojo.getDeclarationName()}ComponentRule.preValidate${pojo.getDeclarationName()}findByBusinessKey(txnTransferObj);
			the${pojo.getDeclarationName()}ComponentRule.preExecute${pojo.getDeclarationName()}findByBusinessKey(txnTransferObj);

			${pojo.getDeclarationName()}DO dbimage${pojo.getDeclarationName()}DO = executeRepositoryQuery(req${pojo.getDeclarationName()}DO.getConfigLanguageCodeKey(),
					req${pojo.getDeclarationName()}DO.getKey(), req${pojo.getDeclarationName()}DO.getInquiryFilter());

			if (null == dbimage${pojo.getDeclarationName()}DO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"${pojo.getDeclarationName()}Component.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimage${pojo.getDeclarationName()}DO
						.setInquiryFilter(txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().set${pojo.getDeclarationName()}DO(new ${pojo.getDeclarationName()}DO(dbimage${pojo.getDeclarationName()}DO));
			}

			the${pojo.getDeclarationName()}ComponentRule.postExecute${pojo.getDeclarationName()}findByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"${pojo.getDeclarationName()}Component.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;
	}

	/**
	 * Execute the query using JPA Repository
	 * 
	 * @since 1.0
	 * @param String
	 *            configLanguageCodeKey
	 * @param String
	 *            key
	 * @param String
	 *            filter
	 * @return ${pojo.getDeclarationName()}DO returns the populated ${pojo.getDeclarationName()}DO object
	 */
	@CacheResult(cacheName = "${pojo.getDeclarationName().toUpperCase()}_BUSKEY")
	public ${pojo.getDeclarationName()}DO executeRepositoryQuery(String configLanguageCodeKey, String key, String filter) {
		${pojo.getDeclarationName()}DO dbimage${pojo.getDeclarationName()}DO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimage${pojo.getDeclarationName()}DO = this.the${pojo.getDeclarationName()}Repository.findByBusinessKeyActive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimage${pojo.getDeclarationName()}DO = this.the${pojo.getDeclarationName()}Repository.findByBusinessKeyInactive(configLanguageCodeKey, key);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimage${pojo.getDeclarationName()}DO = this.the${pojo.getDeclarationName()}Repository.findByBusinessKeyAll(configLanguageCodeKey, key);

		}

		return dbimage${pojo.getDeclarationName()}DO;
	}

	/**
	 * perform the common validation that ${pojo.getDeclarationName()}DO,
	 * ${pojo.getDeclarationName()}DO.configLanguageCodeKey and ${pojo.getDeclarationName()}DO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if ${pojo.getDeclarationName()}DO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"${pojo.getDeclarationName()}DO is needed in the request");
		}
		${pojo.getDeclarationName()}DO the${pojo.getDeclarationName()}DO = (${pojo.getDeclarationName()}DO) txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO();
		if (null == the${pojo.getDeclarationName()}DO.getKey() || the${pojo.getDeclarationName()}DO.getKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1006",
					"${pojo.getDeclarationName()}DO.key should not be null");
		}

		if (null == the${pojo.getDeclarationName()}DO.getConfigLanguageCodeKey()
				|| the${pojo.getDeclarationName()}DO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"${pojo.getDeclarationName()}DO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO().getInquiryFilter());
		}
	}

	/**
	 * perform the common validation that ${pojo.getDeclarationName()}DO and
	 * ${pojo.getDeclarationName()}DO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if ${pojo.getDeclarationName()}DO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByLanguageCode(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"${pojo.getDeclarationName()}DO is needed in the request");
		}
		${pojo.getDeclarationName()}DO the${pojo.getDeclarationName()}DO = (${pojo.getDeclarationName()}DO) txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO();

		if (null == the${pojo.getDeclarationName()}DO.getConfigLanguageCodeKey()
				|| the${pojo.getDeclarationName()}DO.getConfigLanguageCodeKey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1007",
					"${pojo.getDeclarationName()}DO.configLanguageCodeKey should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO().getInquiryFilter());
		}

		// set the current page index to zero (0) if page index is not provided
		// in the request or negative value is provided
		if (null == txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice()
				|| txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice() < 0) {
			txnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(0);
		}

		// set default page size as configured in application properties
		if (null == txnTransferObj.getTxnPayload().getPaginationPageSize()
				|| txnTransferObj.getTxnPayload().getPaginationPageSize() <= 0) {
			ConfigAppPropertiesDO theConfigAppPropertiesDO = configAppPropertiesComponent.executeRepositoryQuery(
					YugandharConfigurationProperties.com_yugandhar_pagination_referencelov_default_pagesize,
					yugandharConstants.FILTER_VALUE_ACTIVE);
			txnTransferObj.getTxnPayload().setPaginationPageSize(Integer.valueOf(theConfigAppPropertiesDO.getValue()));

		}

	}

}

</#assign>

${classbody}