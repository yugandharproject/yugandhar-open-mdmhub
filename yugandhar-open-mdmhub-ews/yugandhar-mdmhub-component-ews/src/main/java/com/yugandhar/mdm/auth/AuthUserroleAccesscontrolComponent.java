package com.yugandhar.mdm.auth;
/* Generated Oct 1, 2017 8:58:12 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
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
import com.yugandhar.mdm.extern.dobj.AuthUserroleAccesscontrolDO;
import com.yugandhar.mdm.extern.dobj.ConfigAppPropertiesDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model AuthUserroleAccesscontrolDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class AuthUserroleAccesscontrolComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	// get the repository instance
	@Autowired
	private AuthUserroleAccesscontrolRepository theAuthUserroleAccesscontrolRepository;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	AuthUserroleAccesscontrolComponentRule theAuthUserroleAccesscontrolComponentRule;

	@Autowired
	ConfigLanguageCodeComponent theConfigLanguageCodeComponent;

	@Autowired
	ConfigAppPropertiesComponent configAppPropertiesComponent;

	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public AuthUserroleAccesscontrolComponent() {
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
	 *             if AuthUserroleAccesscontrolDO object is not present in the
	 *             request or other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			theAuthUserroleAccesscontrolComponentRule.prevalidateAuthUserroleAccesscontrolCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			AuthUserroleAccesscontrolDO reqAuthUserroleAccesscontrolDO = (AuthUserroleAccesscontrolDO) txnTransferObj
					.getTxnPayload().getAuthUserroleAccesscontrolDO();
			if (null == reqAuthUserroleAccesscontrolDO.getPrimaryKeyDO()
					|| null == reqAuthUserroleAccesscontrolDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqAuthUserroleAccesscontrolDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqAuthUserroleAccesscontrolDO.setIdPk(reqAuthUserroleAccesscontrolDO.getPrimaryKeyDO().getIdPk());
				AuthUserroleAccesscontrolDO dbimageAuthUserroleAccesscontrolDO = entityManager
						.find(AuthUserroleAccesscontrolDO.class, reqAuthUserroleAccesscontrolDO.getIdPk());
				if (null != dbimageAuthUserroleAccesscontrolDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"AuthUserroleAccesscontrolComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			theAuthUserroleAccesscontrolComponentRule
					.preExecuteAuthUserroleAccesscontrolCompPersist(reqAuthUserroleAccesscontrolDO, txnTransferObj);
			entityManager.persist(reqAuthUserroleAccesscontrolDO);
			entityManager.flush();
			reqAuthUserroleAccesscontrolDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload()
					.setAuthUserroleAccesscontrolDO(new AuthUserroleAccesscontrolDO(reqAuthUserroleAccesscontrolDO));
			theAuthUserroleAccesscontrolComponentRule
					.postExecuteAuthUserroleAccesscontrolCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"AuthUserroleAccesscontrolComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"AuthUserroleAccesscontrolComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AuthUserroleAccesscontrolComponent.persist failed unexpectedly");
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
	 *             if AuthUserroleAccesscontrolDO object is not present in the
	 *             request or mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			theAuthUserroleAccesscontrolComponentRule.PrevalidateAuthUserroleAccesscontrolCompMerge(txnTransferObj);
			AuthUserroleAccesscontrolDO reqAuthUserroleAccesscontrolDO = (AuthUserroleAccesscontrolDO) txnTransferObj
					.getTxnPayload().getAuthUserroleAccesscontrolDO();
			AuthUserroleAccesscontrolDO dbimageAuthUserroleAccesscontrolDO = entityManager
					.find(AuthUserroleAccesscontrolDO.class, reqAuthUserroleAccesscontrolDO.getIdPk());
			if (null == dbimageAuthUserroleAccesscontrolDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AuthUserroleAccesscontrolComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqAuthUserroleAccesscontrolDO);
			BeanUtils.copyProperties(reqAuthUserroleAccesscontrolDO, dbimageAuthUserroleAccesscontrolDO,
					strIgnoreProperties);
			entityManager.detach(dbimageAuthUserroleAccesscontrolDO);
			theAuthUserroleAccesscontrolComponentRule.preExecuteAuthUserroleAccesscontrolCompMerge(
					reqAuthUserroleAccesscontrolDO, dbimageAuthUserroleAccesscontrolDO, txnTransferObj);
			AuthUserroleAccesscontrolDO mergedAuthUserroleAccesscontrolDO = entityManager
					.merge(dbimageAuthUserroleAccesscontrolDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload()
					.setAuthUserroleAccesscontrolDO(new AuthUserroleAccesscontrolDO(mergedAuthUserroleAccesscontrolDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theAuthUserroleAccesscontrolComponentRule.postExecuteAuthUserroleAccesscontrolCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in AuthUserroleAccesscontrolComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"AuthUserroleAccesscontrolComponent.persist failed - Unique Key Violated");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"AuthUserroleAccesscontrolComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}
		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AuthUserroleAccesscontrolComponent.merge failed unexpectedly");
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
	 *             if AuthUserroleAccesscontrolDO object is not present in the
	 *             request or mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			theAuthUserroleAccesscontrolComponentRule.prevalidateAuthUserroleAccesscontrolCompFindById(txnTransferObj);
			AuthUserroleAccesscontrolDO reqAuthUserroleAccesscontrolDO = (AuthUserroleAccesscontrolDO) txnTransferObj
					.getTxnPayload().getAuthUserroleAccesscontrolDO();
			AuthUserroleAccesscontrolDO dbimageAuthUserroleAccesscontrolDO = entityManager
					.find(AuthUserroleAccesscontrolDO.class, reqAuthUserroleAccesscontrolDO.getIdPk());
			if (null == dbimageAuthUserroleAccesscontrolDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AuthUserroleAccesscontrolComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setAuthUserroleAccesscontrolDO(
					new AuthUserroleAccesscontrolDO(dbimageAuthUserroleAccesscontrolDO));
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			theAuthUserroleAccesscontrolComponentRule
					.postExecuteAuthUserroleAccesscontrolCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AuthUserroleAccesscontrolComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting
	 * AuthUserroleAccesscontrolDO object in the database. populate
	 * createdTimestamp, updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserroleAccesscontrolDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AuthUserroleAccesscontrolDO is needed in the request");
		}

		AuthUserroleAccesscontrolDO theAuthUserroleAccesscontrolDO = (AuthUserroleAccesscontrolDO) txnTransferObj
				.getTxnPayload().getAuthUserroleAccesscontrolDO();
		if (null == theAuthUserroleAccesscontrolDO.getProfileType()
				|| theAuthUserroleAccesscontrolDO.getProfileType().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10075",
					"authUserroleAccesscontrolDO.profileType should not be null");
		}

		if (null == theAuthUserroleAccesscontrolDO.getAuthUserRoleRegistryIdpk()
				|| theAuthUserroleAccesscontrolDO.getAuthUserRoleRegistryIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10076",
					"authUserroleAccesscontrolDO.authUserRoleRegistryIdpk should not be null");
		}

		if (null == theAuthUserroleAccesscontrolDO.getConfigTxnRegistryIdpk()
				|| theAuthUserroleAccesscontrolDO.getConfigTxnRegistryIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10077",
					"authUserroleAccesscontrolDO.configTxnRegistryIdpk should not be null");
		}

		theAuthUserroleAccesscontrolDO.setCreatedTs(new Date());
		theAuthUserroleAccesscontrolDO.setUpdatedTs(new Date());
		theAuthUserroleAccesscontrolDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theAuthUserroleAccesscontrolDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating AuthUserroleAccesscontrolDO
	 * object in the database. populate updatedTimestamp, transaction reference
	 * Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserroleAccesscontrolDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AuthUserroleAccesscontrolDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"AuthUserroleAccesscontrolDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"AuthUserroleAccesscontrolDO.version should not be null");
		}

		AuthUserroleAccesscontrolDO theAuthUserroleAccesscontrolDO = (AuthUserroleAccesscontrolDO) txnTransferObj
				.getTxnPayload().getAuthUserroleAccesscontrolDO();
		if (null == theAuthUserroleAccesscontrolDO.getProfileType()
				|| theAuthUserroleAccesscontrolDO.getProfileType().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10075",
					"authUserroleAccesscontrolDO.profileType should not be null");
		}

		if (null == theAuthUserroleAccesscontrolDO.getAuthUserRoleRegistryIdpk()
				|| theAuthUserroleAccesscontrolDO.getAuthUserRoleRegistryIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10076",
					"authUserroleAccesscontrolDO.authUserRoleRegistryIdpk should not be null");
		}

		if (null == theAuthUserroleAccesscontrolDO.getConfigTxnRegistryIdpk()
				|| theAuthUserroleAccesscontrolDO.getConfigTxnRegistryIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10077",
					"authUserroleAccesscontrolDO.configTxnRegistryIdpk should not be null");
		}

		theAuthUserroleAccesscontrolDO.setUpdatedTs(new Date());
		theAuthUserroleAccesscontrolDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		theAuthUserroleAccesscontrolDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that AuthUserroleAccesscontrolDO and idpk
	 * is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserroleAccesscontrolDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

		if (null == txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AuthUserroleAccesscontrolDO is needed in the request");
		}
		AuthUserroleAccesscontrolDO theAuthUserroleAccesscontrolDO = (AuthUserroleAccesscontrolDO) txnTransferObj
				.getTxnPayload().getAuthUserroleAccesscontrolDO();
		if (null == theAuthUserroleAccesscontrolDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"AuthUserroleAccesscontrolDO.idpk should not be null");
		}

	}

	/**
	 * Returns all the records from the for AuthUserroleAccesscontrolDO entity
	 * based on ProfileType and AuthUserRoleRegistryIdpk, maximum of records as
	 * configuration in properties
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserroleAccesscontrolDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */
	public TxnTransferObj findAuthUserroleAccesscontrolByRegistryIdpk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforefindAuthUserroleAccesscontrolByRegistryIdpk(txnTransferObj);
			Page<AuthUserroleAccesscontrolDO> pageAuthUserroleAccesscontrolDO = findAuthUserroleAccesscontrolByRegistryIdpkFromRepository(
					txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO().getProfileType(),
					txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO().getAuthUserRoleRegistryIdpk(),
					txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO().getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			if (pageAuthUserroleAccesscontrolDO.getTotalPages() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"AuthUserroleAccesscontrol reference list does not have any records in the database");
			}

			if ((pageAuthUserroleAccesscontrolDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
					.getPaginationIndexOfCurrentSlice()) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
						"Invalid page number in the request for AuthUserroleAccesscontrol, total number of pages is "
								+ pageAuthUserroleAccesscontrolDO.getTotalPages()
								+ ". Note- Pages Index starts with 0");
			}

			// Copy pagination properties
			respTxnTransferObj.getTxnPayload()
					.setPaginationIndexOfCurrentSlice(pageAuthUserroleAccesscontrolDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pageAuthUserroleAccesscontrolDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalElements(pageAuthUserroleAccesscontrolDO.getTotalElements());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoTotalPages(pageAuthUserroleAccesscontrolDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pageAuthUserroleAccesscontrolDO.getSize());

			List<AuthUserroleAccesscontrolDO> dbimageAuthUserroleAccesscontrolDOlist = pageAuthUserroleAccesscontrolDO
					.getContent();
			// clone the object before sending response. This is important to
			// prevent editing/ future referencing of entity manager level 1
			// (L1) cached object
			List<AuthUserroleAccesscontrolDO> clonedAuthUserroleAccesscontrolDOList = null;
			if (null != dbimageAuthUserroleAccesscontrolDOlist && dbimageAuthUserroleAccesscontrolDOlist.size() > 0) {
				clonedAuthUserroleAccesscontrolDOList = new ArrayList<AuthUserroleAccesscontrolDO>();
				Iterator<AuthUserroleAccesscontrolDO> itr = dbimageAuthUserroleAccesscontrolDOlist.iterator();
				while (itr.hasNext()) {
					AuthUserroleAccesscontrolDO theAuthUserroleAccesscontrolDO = new AuthUserroleAccesscontrolDO(
							itr.next());
					clonedAuthUserroleAccesscontrolDOList.add(theAuthUserroleAccesscontrolDO);
				}
			}

			if (null == clonedAuthUserroleAccesscontrolDOList) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"AuthUserroleAccesscontrol reference list does not have any records in the database");
				// Record not found
			} else if (clonedAuthUserroleAccesscontrolDOList.size() == 0) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "103",
						"AuthUserroleAccesscontrol reference list does not have any records in the database");
				// Record not found
			} else {
				respTxnTransferObj.getTxnPayload()
						.setAuthUserroleAccesscontrolDOList(clonedAuthUserroleAccesscontrolDOList);
			}

			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AuthUserroleAccesscontrolComponent.findByBusinessKey failed unexpectedly");
		}

		return respTxnTransferObj;

	}

	@CacheResult(cacheName = "AUTHUSERROLEACCESSCONTROL_BUSKEY")
	public Page<AuthUserroleAccesscontrolDO> findAuthUserroleAccesscontrolByRegistryIdpkFromRepository(
			@CacheKey String profileType, @CacheKey String authUserRoleRegistryIdpk, @CacheKey String filter,
			@CacheKey Integer requestedPageNumber, @CacheKey Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "idPk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<AuthUserroleAccesscontrolDO> pageAuthUserroleAccesscontrolDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pageAuthUserroleAccesscontrolDO = this.theAuthUserroleAccesscontrolRepository
					.findAllActiveAuthUserroleAccesscontrolByRegistryIdpk(profileType,authUserRoleRegistryIdpk, localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pageAuthUserroleAccesscontrolDO = this.theAuthUserroleAccesscontrolRepository
					.findAllInActiveAuthUserroleAccesscontrolByRegistryIdpk(profileType,authUserRoleRegistryIdpk, localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pageAuthUserroleAccesscontrolDO = this.theAuthUserroleAccesscontrolRepository
					.findAuthUserroleAccesscontrolByRegistryIdpk(profileType,authUserRoleRegistryIdpk, localPageable);

		}

		return pageAuthUserroleAccesscontrolDO;
	}
	
	/**
	 * perform the common validation that AuthUserroleAccesscontrolDO and
	 * AuthUserroleAccesscontrolDO.configLanguageCodeKey is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserroleAccesscontrolDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforefindAuthUserroleAccesscontrolByRegistryIdpk(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AuthUserroleAccesscontrolDO is needed in the request");
		}
		AuthUserroleAccesscontrolDO theAuthUserroleAccesscontrolDO = (AuthUserroleAccesscontrolDO) txnTransferObj
				.getTxnPayload().getAuthUserroleAccesscontrolDO();

		if (null == theAuthUserroleAccesscontrolDO.getProfileType()
				|| theAuthUserroleAccesscontrolDO.getProfileType().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10075",
					"authUserroleAccesscontrolDO.profileType should not be null");
		}

		if (null == theAuthUserroleAccesscontrolDO.getAuthUserRoleRegistryIdpk()
				|| theAuthUserroleAccesscontrolDO.getAuthUserRoleRegistryIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10076",
					"authUserroleAccesscontrolDO.authUserRoleRegistryIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO().getInquiryFilter());
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
	 * This method search the database table REF_CURRENCY records based on
	 * business key (profileType, authUserRoleRegistryIdpk, configTxnRegistryIdpk)
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserroleAccesscontrolDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */

	public TxnTransferObj findByBusinessKey(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByBusinessKey(txnTransferObj);
			AuthUserroleAccesscontrolDO reqAuthUserroleAccesscontrolDO = (AuthUserroleAccesscontrolDO) txnTransferObj
					.getTxnPayload().getAuthUserroleAccesscontrolDO();
			theAuthUserroleAccesscontrolComponentRule
					.preValidateAuthUserroleAccesscontrolfindByBusinessKey(txnTransferObj);
			theAuthUserroleAccesscontrolComponentRule
					.preExecuteAuthUserroleAccesscontrolfindByBusinessKey(txnTransferObj);

			AuthUserroleAccesscontrolDO dbimageAuthUserroleAccesscontrolDO = executeRepositoryQuery(
					reqAuthUserroleAccesscontrolDO.getProfileType(),
					reqAuthUserroleAccesscontrolDO.getAuthUserRoleRegistryIdpk(), 
					reqAuthUserroleAccesscontrolDO.getConfigTxnRegistryIdpk(),
					reqAuthUserroleAccesscontrolDO.getInquiryFilter());

			if (null == dbimageAuthUserroleAccesscontrolDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"AuthUserroleAccesscontrolComponent.findByBusinessKey search result have no records");
				// Record not found
			} else {
				dbimageAuthUserroleAccesscontrolDO.setInquiryFilter(
						txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO().getInquiryFilter());
				respTxnTransferObj.getTxnPayload().setAuthUserroleAccesscontrolDO(
						new AuthUserroleAccesscontrolDO(dbimageAuthUserroleAccesscontrolDO));
			}

			theAuthUserroleAccesscontrolComponentRule
					.postExecuteAuthUserroleAccesscontrolfindByBusinessKey(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findByLanguageCodeAndKey failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"AuthUserroleAccesscontrolComponent.findByBusinessKey failed unexpectedly");
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
	 * @return AuthUserroleAccesscontrolDO returns the populated
	 *         AuthUserroleAccesscontrolDO object
	 */
	@CacheResult(cacheName = "AUTHUSERROLEACCESSCONTROL_BUSKEY")
	public AuthUserroleAccesscontrolDO executeRepositoryQuery(String profileType,String authUserRoleRegistryIdpk,String configTxnRegistryIdpk, String filter) {
		AuthUserroleAccesscontrolDO dbimageAuthUserroleAccesscontrolDO = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			dbimageAuthUserroleAccesscontrolDO = this.theAuthUserroleAccesscontrolRepository
					.findByBusinessKeyActive(profileType, authUserRoleRegistryIdpk, configTxnRegistryIdpk);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			dbimageAuthUserroleAccesscontrolDO = this.theAuthUserroleAccesscontrolRepository
					.findByBusinessKeyInactive(profileType, authUserRoleRegistryIdpk, configTxnRegistryIdpk);
		} else if (filter.toUpperCase().equals("ALL")) {
			dbimageAuthUserroleAccesscontrolDO = this.theAuthUserroleAccesscontrolRepository
					.findByBusinessKeyAll(profileType, authUserRoleRegistryIdpk, configTxnRegistryIdpk);

		}

		return dbimageAuthUserroleAccesscontrolDO;
	}

	/**
	 * perform the common validation that AuthUserroleAccesscontrolDO,
	 * AuthUserroleAccesscontrolDO.configLanguageCodeKey and
	 * AuthUserroleAccesscontrolDO.key is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if AuthUserroleAccesscontrolDO object is not present in the
	 *             request or mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindByBusinessKey(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"AuthUserroleAccesscontrolDO is needed in the request");
		}
		AuthUserroleAccesscontrolDO theAuthUserroleAccesscontrolDO = (AuthUserroleAccesscontrolDO) txnTransferObj
				.getTxnPayload().getAuthUserroleAccesscontrolDO();
		if (null == theAuthUserroleAccesscontrolDO.getProfileType()
				|| theAuthUserroleAccesscontrolDO.getProfileType().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10075",
					"authUserroleAccesscontrolDO.profileType should not be null");
		}

		if (null == theAuthUserroleAccesscontrolDO.getAuthUserRoleRegistryIdpk()
				|| theAuthUserroleAccesscontrolDO.getAuthUserRoleRegistryIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10076",
					"authUserroleAccesscontrolDO.authUserRoleRegistryIdpk should not be null");
		}

		if (null == theAuthUserroleAccesscontrolDO.getConfigTxnRegistryIdpk()
				|| theAuthUserroleAccesscontrolDO.getConfigTxnRegistryIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10077",
					"authUserroleAccesscontrolDO.configTxnRegistryIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO().getInquiryFilter()
				|| txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO().getInquiryFilter().isEmpty()) {
			txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO().setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj,
					txnTransferObj.getTxnPayload().getAuthUserroleAccesscontrolDO().getInquiryFilter());
		}
	}

	

}
