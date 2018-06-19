package com.yugandhar.mdm.auth;
/* Generated Oct 2, 2017 1:29:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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
import com.yugandhar.mdm.extern.dobj.AuthUserRoleAssocDO;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model AuthUserRoleAssocDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class AuthUserRoleAssocComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private AuthUserRoleAssocRepository theAuthUserRoleAssocRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	AuthUserRoleAssocComponentRule theAuthUserRoleAssocComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public AuthUserRoleAssocComponent() {
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
	 *             if AuthUserRoleAssocDO object is not present in the request
	 *             or other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theAuthUserRoleAssocComponentRule.prevalidateAuthUserRoleAssocCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			AuthUserRoleAssocDO reqAuthUserRoleAssocDO = (AuthUserRoleAssocDO) txnTransferObj.getTxnPayload()
					.getAuthUserRoleAssocDO();
			if (null == reqAuthUserRoleAssocDO.getPrimaryKeyDO()
					|| null == reqAuthUserRoleAssocDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqAuthUserRoleAssocDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqAuthUserRoleAssocDO.setIdPk(reqAuthUserRoleAssocDO.getPrimaryKeyDO().getIdPk());
				AuthUserRoleAssocDO dbimageAuthUserRoleAssocDO = entityManager.find(AuthUserRoleAssocDO.class,
						reqAuthUserRoleAssocDO.getIdPk());
				if (null != dbimageAuthUserRoleAssocDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"AuthUserRoleAssocComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theAuthUserRoleAssocComponentRule.preExecuteAuthUserRoleAssocCompPersist(reqAuthUserRoleAssocDO,
					txnTransferObj);
			entityManager.persist(reqAuthUserRoleAssocDO);
			entityManager.flush();
			reqAuthUserRoleAssocDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setAuthUserRoleAssocDO(new AuthUserRoleAssocDO(reqAuthUserRoleAssocDO));
			theAuthUserRoleAssocComponentRule.postExecuteAuthUserRoleAssocCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"AuthUserRoleAssocComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"AuthUserRoleAssocComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AuthUserRoleAssocComponent.persist failed unexpectedly");
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
	 *             if AuthUserRoleAssocDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theAuthUserRoleAssocComponentRule.PrevalidateAuthUserRoleAssocCompMerge(txnTransferObj);
			AuthUserRoleAssocDO reqAuthUserRoleAssocDO = (AuthUserRoleAssocDO) txnTransferObj.getTxnPayload()
					.getAuthUserRoleAssocDO();
			AuthUserRoleAssocDO dbimageAuthUserRoleAssocDO = entityManager.find(AuthUserRoleAssocDO.class,
					reqAuthUserRoleAssocDO.getIdPk());
			if (null == dbimageAuthUserRoleAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AuthUserRoleAssocComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqAuthUserRoleAssocDO);
			BeanUtils.copyProperties(reqAuthUserRoleAssocDO, dbimageAuthUserRoleAssocDO, strIgnoreProperties);
			entityManager.detach(dbimageAuthUserRoleAssocDO);
			theAuthUserRoleAssocComponentRule.preExecuteAuthUserRoleAssocCompMerge(reqAuthUserRoleAssocDO,
					dbimageAuthUserRoleAssocDO, txnTransferObj);
			AuthUserRoleAssocDO mergedAuthUserRoleAssocDO = entityManager.merge(dbimageAuthUserRoleAssocDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setAuthUserRoleAssocDO(new AuthUserRoleAssocDO(mergedAuthUserRoleAssocDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theAuthUserRoleAssocComponentRule.postExecuteAuthUserRoleAssocCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in AuthUserRoleAssocComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"AuthUserRoleAssocComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"AuthUserRoleAssocComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AuthUserRoleAssocComponent.merge failed unexpectedly");
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
	 *             if AuthUserRoleAssocDO object is not present in the request
	 *             or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theAuthUserRoleAssocComponentRule.prevalidateAuthUserRoleAssocCompFindById(txnTransferObj);
			AuthUserRoleAssocDO reqAuthUserRoleAssocDO = (AuthUserRoleAssocDO) txnTransferObj.getTxnPayload()
					.getAuthUserRoleAssocDO();
			AuthUserRoleAssocDO dbimageAuthUserRoleAssocDO = entityManager.find(AuthUserRoleAssocDO.class,
					reqAuthUserRoleAssocDO.getIdPk());
			if (null == dbimageAuthUserRoleAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AuthUserRoleAssocComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setAuthUserRoleAssocDO(new AuthUserRoleAssocDO(dbimageAuthUserRoleAssocDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theAuthUserRoleAssocComponentRule.postExecuteAuthUserRoleAssocCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AuthUserRoleAssocComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting AuthUserRoleAssocDO
	 * object in the database. populate createdTimestamp, updatedTimestamp,
	 * transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserRoleAssocDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AuthUserRoleAssocDO is needed in the request");
		}

		AuthUserRoleAssocDO theAuthUserRoleAssocDO = (AuthUserRoleAssocDO) txnTransferObj.getTxnPayload()
				.getAuthUserRoleAssocDO();
		if (null == theAuthUserRoleAssocDO.getAuthRolesRegistryIdpk()
				|| theAuthUserRoleAssocDO.getAuthRolesRegistryIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10078",
					"authUserRoleAssocDO.authRolesRegistryIdpk should not be null");
		}

		if (null == theAuthUserRoleAssocDO.getAuthUserRegistryIdpk()
				|| theAuthUserRoleAssocDO.getAuthUserRegistryIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10079",
					"authUserRoleAssocDO.authUserRegistryIdpk should not be null");
		}

		theAuthUserRoleAssocDO.setCreatedTs(new Date());
		theAuthUserRoleAssocDO.setUpdatedTs(new Date());
		theAuthUserRoleAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theAuthUserRoleAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating AuthUserRoleAssocDO object
	 * in the database. populate updatedTimestamp, transaction reference Id and
	 * user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserRoleAssocDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AuthUserRoleAssocDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"AuthUserRoleAssocDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"AuthUserRoleAssocDO.version should not be null");
		}

		AuthUserRoleAssocDO theAuthUserRoleAssocDO = (AuthUserRoleAssocDO) txnTransferObj.getTxnPayload()
				.getAuthUserRoleAssocDO();
		if (null == theAuthUserRoleAssocDO.getAuthRolesRegistryIdpk()
				|| theAuthUserRoleAssocDO.getAuthRolesRegistryIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10078",
					"authUserRoleAssocDO.authRolesRegistryIdpk should not be null");
		}

		if (null == theAuthUserRoleAssocDO.getAuthUserRegistryIdpk()
				|| theAuthUserRoleAssocDO.getAuthUserRegistryIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10079",
					"authUserRoleAssocDO.authUserRegistryIdpk should not be null");
		}

		theAuthUserRoleAssocDO.setUpdatedTs(new Date());
		theAuthUserRoleAssocDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theAuthUserRoleAssocDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that AuthUserRoleAssocDO and idpk is not
	 * null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserRoleAssocDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AuthUserRoleAssocDO is needed in the request");
		}
		AuthUserRoleAssocDO theAuthUserRoleAssocDO = (AuthUserRoleAssocDO) txnTransferObj.getTxnPayload()
				.getAuthUserRoleAssocDO();
		if (null == theAuthUserRoleAssocDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"AuthUserRoleAssocDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for AuthUserRoleAssocDO entity based on
	 * authUserRegistryIdpk and maximum of records as configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserRoleAssocDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByAuthUserRegistryIdpk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByAuthUserRegistryIdpk(txnTransferObj);
			Page<AuthUserRoleAssocDO> pageAuthUserRoleAssocDO = findAllRecordsByAuthUserRegistryIdpkFromRepository(
					txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().getAuthUserRegistryIdpk(),
					txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageAuthUserRoleAssocDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"AuthUserRoleAssoc reference list does not have any records in the database");
			}

			if ((pageAuthUserRoleAssocDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for AuthUserRoleAssoc, total number of pages is "
								+ pageAuthUserRoleAssocDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageAuthUserRoleAssocDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageAuthUserRoleAssocDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageAuthUserRoleAssocDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageAuthUserRoleAssocDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageAuthUserRoleAssocDO.getSize());

			List<AuthUserRoleAssocDO> dbimageAuthUserRoleAssocDOlist = pageAuthUserRoleAssocDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<AuthUserRoleAssocDO> clonedAuthUserRoleAssocDOList = null;
			if (null != dbimageAuthUserRoleAssocDOlist && dbimageAuthUserRoleAssocDOlist.size() > 0) {
				clonedAuthUserRoleAssocDOList = new ArrayList<AuthUserRoleAssocDO>();
				Iterator<AuthUserRoleAssocDO> itr = dbimageAuthUserRoleAssocDOlist.iterator();
				while (itr.hasNext()) {
					AuthUserRoleAssocDO theAuthUserRoleAssocDO = new AuthUserRoleAssocDO(itr.next());
					clonedAuthUserRoleAssocDOList.add(theAuthUserRoleAssocDO);
				}
			}

			if (null == clonedAuthUserRoleAssocDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"AuthUserRoleAssoc reference list does not have any records in the database");
				// Record not found
			} else if (clonedAuthUserRoleAssocDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"AuthUserRoleAssoc reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setAuthUserRoleAssocDOList(clonedAuthUserRoleAssocDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AuthUserRoleAssocComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "AUTHUSERROLEASSOC_BUSKEY")
	public Page<AuthUserRoleAssocDO> findAllRecordsByAuthUserRegistryIdpkFromRepository(
			@CacheKey String authUserRegistryIdpk, @CacheKey String filter, @CacheKey Integer requestedPageNumber,
			@CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<AuthUserRoleAssocDO> pageAuthUserRoleAssocDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageAuthUserRoleAssocDO = this.theAuthUserRoleAssocRepository
					.findAllActiveByAuthUserRegistryIdpk(authUserRegistryIdpk, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageAuthUserRoleAssocDO = this.theAuthUserRoleAssocRepository
					.findAllInActiveByAuthUserRegistryIdpk(authUserRegistryIdpk, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageAuthUserRoleAssocDO = this.theAuthUserRoleAssocRepository
					.findAllByAuthUserRegistryIdpk(authUserRegistryIdpk, localPageable);

		}

		return pageAuthUserRoleAssocDO;
	}

	/**
	 * perform the common validation that AuthUserRoleAssocDO and
	 * AuthUserRoleAssocDO.authRolesRegistryIdpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserRoleAssocDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByAuthUserRegistryIdpk(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AuthUserRoleAssocDO is needed in the request");
		}
		AuthUserRoleAssocDO theAuthUserRoleAssocDO = (AuthUserRoleAssocDO) txnTransferObj.getTxnPayload()
				.getAuthUserRoleAssocDO();

		if (null == theAuthUserRoleAssocDO.getAuthUserRegistryIdpk()
				|| theAuthUserRoleAssocDO.getAuthUserRegistryIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10079",
					"authUserRoleAssocDO.authUserRegistryIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().getInquiryFilter());
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

	/**
	 * This method search the database table records based on business key
	 * (authRolesRegistryIdpk and authUserRegistryIdpk)
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserRoleAssocDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			AuthUserRoleAssocDO reqAuthUserRoleAssocDO = (AuthUserRoleAssocDO) txnTransferObj.getTxnPayload()
					.getAuthUserRoleAssocDO();
			theAuthUserRoleAssocComponentRule.preValidateAuthUserRoleAssocfindByBusinessKey(txnTransferObj);
			theAuthUserRoleAssocComponentRule.preExecuteAuthUserRoleAssocfindByBusinessKey(txnTransferObj);

			AuthUserRoleAssocDO dbimageAuthUserRoleAssocDO = executeRepositoryQuery(
					reqAuthUserRoleAssocDO.getAuthRolesRegistryIdpk(),
					reqAuthUserRoleAssocDO.getAuthUserRegistryIdpk(), reqAuthUserRoleAssocDO.getInquiryFilter());

			if (null == dbimageAuthUserRoleAssocDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AuthUserRoleAssocComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageAuthUserRoleAssocDO
						.setInquiryFilter(txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload()
						.setAuthUserRoleAssocDO(new AuthUserRoleAssocDO(dbimageAuthUserRoleAssocDO));
			}

			theAuthUserRoleAssocComponentRule.postExecuteAuthUserRoleAssocfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AuthUserRoleAssocComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;
	}

	/**
	 * Execute the query using JPA Repository
	 * 
	 * @since 1.0
	 * @param String
	 *            authRolesRegistryIdpk
	 * @param String
	 *            authUserRoleAssocIdpk
	 * @param String
	 *            filter
	 * @return AuthUserRoleAssocDO returns the populated AuthUserRoleAssocDO
	 *         object
	 */
	@CacheResult(cacheName = "AUTHUSERROLEASSOC_BUSKEY")
	public AuthUserRoleAssocDO executeRepositoryQuery(String authRolesRegistryIdpk, String authUserRegistryIdpk,
			String filter) {
		AuthUserRoleAssocDO dbimageAuthUserRoleAssocDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageAuthUserRoleAssocDO = this.theAuthUserRoleAssocRepository
					.findByBusinessKeyActive(authRolesRegistryIdpk, authUserRegistryIdpk);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageAuthUserRoleAssocDO = this.theAuthUserRoleAssocRepository
					.findByBusinessKeyInactive(authRolesRegistryIdpk, authUserRegistryIdpk);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageAuthUserRoleAssocDO = this.theAuthUserRoleAssocRepository.findByBusinessKeyAll(authRolesRegistryIdpk,
					authUserRegistryIdpk);

		}

		return dbimageAuthUserRoleAssocDO;
	}

	/**
	 * perform the common validation that AuthUserRoleAssocDO,
	 * AuthUserRoleAssocDO.authRolesRegistryIdpk and
	 * AuthUserRoleAssocDO.authRolesRegistryIdpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserRoleAssocDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AuthUserRoleAssocDO is needed in the request");
		}
		AuthUserRoleAssocDO theAuthUserRoleAssocDO = (AuthUserRoleAssocDO) txnTransferObj.getTxnPayload()
				.getAuthUserRoleAssocDO();
		if (null == theAuthUserRoleAssocDO.getAuthRolesRegistryIdpk()
				|| theAuthUserRoleAssocDO.getAuthRolesRegistryIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10078",
					"authUserRoleAssocDO.authRolesRegistryIdpk should not be null");
		}

		if (null == theAuthUserRoleAssocDO.getAuthUserRegistryIdpk()
				|| theAuthUserRoleAssocDO.getAuthUserRegistryIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10079",
					"authUserRoleAssocDO.authUserRegistryIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().getInquiryFilter());
		}
	}

	/**
	 * Returns all the records from the for AuthUserRoleAssocDO entity based on
	 * authRolesRegistryIdpk and maximum of records as configuration in
	 * properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserRoleAssocDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	public TxnTransferObj findAllRecordsByAuthRolesRegistryIdpk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAllRecordsByAuthRolesRegistryIdpk(txnTransferObj);
			Page<AuthUserRoleAssocDO> pageAuthUserRoleAssocDO = findAllRecordsByAuthRolesRegistryIdpkFromRepository(
					txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().getAuthRolesRegistryIdpk(),
					txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageAuthUserRoleAssocDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"AuthUserRoleAssoc reference list does not have any records in the database");
			}

			if ((pageAuthUserRoleAssocDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for AuthUserRoleAssoc, total number of pages is "
								+ pageAuthUserRoleAssocDO.getTotalPages() + ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pageAuthUserRoleAssocDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageAuthUserRoleAssocDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageAuthUserRoleAssocDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pageAuthUserRoleAssocDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageAuthUserRoleAssocDO.getSize());

			List<AuthUserRoleAssocDO> dbimageAuthUserRoleAssocDOlist = pageAuthUserRoleAssocDO.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<AuthUserRoleAssocDO> clonedAuthUserRoleAssocDOList = null;
			if (null != dbimageAuthUserRoleAssocDOlist && dbimageAuthUserRoleAssocDOlist.size() > 0) {
				clonedAuthUserRoleAssocDOList = new ArrayList<AuthUserRoleAssocDO>();
				Iterator<AuthUserRoleAssocDO> itr = dbimageAuthUserRoleAssocDOlist.iterator();
				while (itr.hasNext()) {
					AuthUserRoleAssocDO theAuthUserRoleAssocDO = new AuthUserRoleAssocDO(itr.next());
					clonedAuthUserRoleAssocDOList.add(theAuthUserRoleAssocDO);
				}
			}

			if (null == clonedAuthUserRoleAssocDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"AuthUserRoleAssoc reference list does not have any records in the database");
				// Record not found
			} else if (clonedAuthUserRoleAssocDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"AuthUserRoleAssoc reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload().setAuthUserRoleAssocDOList(clonedAuthUserRoleAssocDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AuthUserRoleAssocComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "AUTHUSERROLEASSOC_BUSKEY")
	public Page<AuthUserRoleAssocDO> findAllRecordsByAuthRolesRegistryIdpkFromRepository(
			@CacheKey String authRolesRegistryIdpk, @CacheKey String filter, @CacheKey Integer requestedPageNumber,
			@CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<AuthUserRoleAssocDO> pageAuthUserRoleAssocDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageAuthUserRoleAssocDO = this.theAuthUserRoleAssocRepository
					.findAllActiveByAuthRolesRegistryIdpk(authRolesRegistryIdpk, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageAuthUserRoleAssocDO = this.theAuthUserRoleAssocRepository
					.findAllInActiveByAuthRolesRegistryIdpk(authRolesRegistryIdpk, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageAuthUserRoleAssocDO = this.theAuthUserRoleAssocRepository
					.findAllByAuthRolesRegistryIdpk(authRolesRegistryIdpk, localPageable);

		}

		return pageAuthUserRoleAssocDO;
	}

	/**
	 * perform the common validation that AuthUserRoleAssocDO and
	 * AuthUserRoleAssocDO.authRolesRegistryIdpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserRoleAssocDO object is not present in the request
	 *             or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAllRecordsByAuthRolesRegistryIdpk(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AuthUserRoleAssocDO is needed in the request");
		}
		AuthUserRoleAssocDO theAuthUserRoleAssocDO = (AuthUserRoleAssocDO) txnTransferObj.getTxnPayload()
				.getAuthUserRoleAssocDO();

		if (null == theAuthUserRoleAssocDO.getAuthRolesRegistryIdpk()
				|| theAuthUserRoleAssocDO.getAuthRolesRegistryIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10078",
					"authUserRoleAssocDO.authRolesRegistryIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getAuthUserRoleAssocDO().getInquiryFilter());
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
