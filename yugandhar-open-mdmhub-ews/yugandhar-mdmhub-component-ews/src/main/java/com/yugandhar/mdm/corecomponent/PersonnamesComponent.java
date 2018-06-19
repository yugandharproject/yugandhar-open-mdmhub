package com.yugandhar.mdm.corecomponent;
/* Generated Jul 1, 2017 2:56:24 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.mdm.extern.dobj.PersonnamesDO;
import com.yugandhar.mdm.extern.dobj.RefPersonnameTypeDO;
import com.yugandhar.mdm.extern.dobj.RefPrefixNameDO;
import com.yugandhar.mdm.extern.dobj.RefSourceSystemDO;
import com.yugandhar.mdm.extern.dobj.RefSuffixNameDO;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model PersonnamesDO class
 * 
 * @author Yugandhar
 * @version 1.0
 * @since 1.0
 * 
 */

@Scope(value = "prototype")
@Component
public class PersonnamesComponent {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	// entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	PersonnamesComponentRule thePersonnamesComponentRule;

	// get the repository instance
	@Autowired
	private PersonnamesRepository thePersonnamesRepository;

	@Autowired
	ReferenceTableHelper referenceTableHelper;
	// default transaction response object
	TxnTransferObj respTxnTransferObj;

	// default constructor
	public PersonnamesComponent() {
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
	 *             if PersonnamesDO object is not present in the request or
	 *             other mandatory attributes not present
	 *
	 */
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			thePersonnamesComponentRule.prevalidatePersonnamesCompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID
			// generator
			PersonnamesDO reqPersonnamesDO = (PersonnamesDO) txnTransferObj.getTxnPayload().getPersonnamesDO();
			if (null == reqPersonnamesDO.getPrimaryKeyDO() || null == reqPersonnamesDO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				reqPersonnamesDO.setIdPk(yugandharKeygenerator.generateKey());
			} else {
				reqPersonnamesDO.setIdPk(reqPersonnamesDO.getPrimaryKeyDO().getIdPk());
				PersonnamesDO dbimagePersonnamesDO = entityManager.find(PersonnamesDO.class,
						reqPersonnamesDO.getIdPk());
				if (null != dbimagePersonnamesDO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101",
							"PersonnamesComponent.persist failed with Validation Exception");
					// Record already present for given Idpk
				}
			}
			thePersonnamesComponentRule.preExecutePersonnamesCompPersist(reqPersonnamesDO, txnTransferObj);
			entityManager.persist(reqPersonnamesDO);
			entityManager.flush();
			reqPersonnamesDO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().setPersonnamesDO(new PersonnamesDO(reqPersonnamesDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getPersonnamesDO());
			thePersonnamesComponentRule.postExecutePersonnamesCompPersit(respTxnTransferObj);
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"PersonnamesComponent.persist failed - Unique Key Violated, record with LegalEntityIdPk - personnameTypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"PersonnamesComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"PersonnamesComponent.persist failed unexpectedly");
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
	 *             if PersonnamesDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			thePersonnamesComponentRule.PrevalidatePersonnamesCompMerge(txnTransferObj);
			PersonnamesDO reqPersonnamesDO = (PersonnamesDO) txnTransferObj.getTxnPayload().getPersonnamesDO();
			PersonnamesDO dbimagePersonnamesDO = entityManager.find(PersonnamesDO.class, reqPersonnamesDO.getIdPk());
			if (null == dbimagePersonnamesDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"PersonnamesComponent.merge failed with Validation Exception");
				// Record not found for given Idpk
			}

			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(reqPersonnamesDO);
			BeanUtils.copyProperties(reqPersonnamesDO, dbimagePersonnamesDO, strIgnoreProperties);
			entityManager.detach(dbimagePersonnamesDO);
			thePersonnamesComponentRule.preExecutePersonnamesCompMerge(reqPersonnamesDO, dbimagePersonnamesDO,
					txnTransferObj);
			PersonnamesDO mergedPersonnamesDO = entityManager.merge(dbimagePersonnamesDO);
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setPersonnamesDO(new PersonnamesDO(mergedPersonnamesDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getPersonnamesDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			thePersonnamesComponentRule.postExecutePersonnamesCompMerge(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe,
					"OptimisticLockException in PersonnamesComponent.merge");
			// OptimisticLockException- Row was updated or deleted by another
			// transaction
		} catch (javax.persistence.PersistenceException pe) {
			logger.error("persist failed", pe);
			pe.printStackTrace();
			Throwable theCause = pe.getCause();
			if (theCause instanceof ConstraintViolationException) {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "3", pe,
						"PersonnamesComponent.persist failed - Unique Key Violated, record with LegalEntityIdPk - personnameTypeRefkey combination already exists");
			} else {
				throw commonValidationUtil.populateErrorResponse(txnTransferObj, "4", pe,
						"PersonnamesComponent.persist failed unexpectedly with PersistenceException");
				// Transaction Failed due to unknown error, please check statck
				// trace
			}

		} catch (RuntimeException re) {
			logger.error("merge failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"PersonnamesComponent.merge failed unexpectedly");
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
	 *             if PersonnamesDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			thePersonnamesComponentRule.prevalidatePersonnamesCompFindById(txnTransferObj);
			PersonnamesDO reqPersonnamesDO = (PersonnamesDO) txnTransferObj.getTxnPayload().getPersonnamesDO();
			PersonnamesDO dbimagePersonnamesDO = entityManager.find(PersonnamesDO.class, reqPersonnamesDO.getIdPk());
			if (null == dbimagePersonnamesDO) {
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102",
						"PersonnamesComponent.findbyId failed with Validation Exception");
				// Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().setPersonnamesDO(new PersonnamesDO(dbimagePersonnamesDO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
					respTxnTransferObj.getTxnPayload().getPersonnamesDO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			thePersonnamesComponentRule.postExecutePersonnamesCompFindById(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"PersonnamesComponent.findById failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/**
	 * perform the common validation before persisting PersonnamesDO object in
	 * the database. populate createdTimestamp, updatedTimestamp, transaction
	 * reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if PersonnamesDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getPersonnamesDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"PersonnamesDO is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getPersonnamesDO().getLegalentityIdpk()
				|| txnTransferObj.getTxnPayload().getPersonnamesDO().getLegalentityIdpk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10003",
					"PersonnamesDO.LegalEntityIdPk is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getPersonnamesDO().getPersonnameTypeRefkey()
				|| txnTransferObj.getTxnPayload().getPersonnamesDO().getPersonnameTypeRefkey().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10004",
					"PersonnamesDO.personnameTypeRefkey is needed in the request");
		}

		if (null == txnTransferObj.getTxnPayload().getPersonnamesDO().getNameOne()
				|| txnTransferObj.getTxnPayload().getPersonnamesDO().getNameOne().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "10049",
					"PersonnamesDO.nameOne is needed in the request");
		}

		PersonnamesDO thePersonnamesDO = (PersonnamesDO) txnTransferObj.getTxnPayload().getPersonnamesDO();
		thePersonnamesDO.setCreatedTs(new Date());
		thePersonnamesDO.setUpdatedTs(new Date());
		thePersonnamesDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		thePersonnamesDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation before updating PersonnamesDO object in the
	 * database. populate updatedTimestamp, transaction reference Id and user
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if PersonnamesDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getPersonnamesDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"PersonnamesDO is needed in the request");
		}
		if (null == txnTransferObj.getTxnPayload().getPersonnamesDO().getIdPk()
				|| txnTransferObj.getTxnPayload().getPersonnamesDO().getIdPk().isEmpty()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"PersonnamesDO.idpk should not be null");
		}

		if (null == txnTransferObj.getTxnPayload().getPersonnamesDO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"PersonnamesDO.version should not be null");
		}

		PersonnamesDO thePersonnamesDO = (PersonnamesDO) txnTransferObj.getTxnPayload().getPersonnamesDO();
		thePersonnamesDO.setUpdatedTs(new Date());
		thePersonnamesDO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		thePersonnamesDO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
	}

	/**
	 * perform the common validation that PersonnamesDO and idpk is not null
	 * 
	 * @since 1.0
	 * @param txnTransferObj
	 *            Transfer Object TxnTransferObj instance
	 * @return txnTransferObj Returns the Transfer Object TxnTransferObj
	 *         instance populated with database instance
	 * @throws YugandharCommonException
	 *             if PersonnamesDO object is not present in the request or
	 *             mandatory attributes business key is not present
	 */
	private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getPersonnamesDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"PersonnamesDO is needed in the request");
		}
		PersonnamesDO thePersonnamesDO = (PersonnamesDO) txnTransferObj.getTxnPayload().getPersonnamesDO();
		if (null == thePersonnamesDO.getIdPk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004",
					"PersonnamesDO.idpk should not be null");
		}

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
	 *             if PersonnamesDO object is not present in the request or
	 *             mandatory attributes primary key is not present
	 */
	public TxnTransferObj findByLegalEntityIdPk(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforeFindByLegalEntityIdPk(txnTransferObj);
			thePersonnamesComponentRule.prevalidatePersonnamesCompFindByLegalEntityIdPk(txnTransferObj);
			PersonnamesDO reqPersonnamesDO = (PersonnamesDO) txnTransferObj.getTxnPayload().getPersonnamesDO();

			Page<PersonnamesDO> pagePersonnamesDO = findByLegalEntityIdPkFromRepository(
					reqPersonnamesDO.getLegalentityIdpk(), reqPersonnamesDO.getInquiryFilter(),
					txnTransferObj.getTxnPayload().getPaginationIndexOfCurrentSlice(),
					txnTransferObj.getTxnPayload().getPaginationPageSize(), txnTransferObj);

			// Copy pagination properties in response
			respTxnTransferObj.getTxnPayload().setPaginationIndexOfCurrentSlice(pagePersonnamesDO.getNumber());
			respTxnTransferObj.getTxnPayload()
					.setPaginationInfoElementsOnCurrentSlice(pagePersonnamesDO.getNumberOfElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalElements(pagePersonnamesDO.getTotalElements());
			respTxnTransferObj.getTxnPayload().setPaginationInfoTotalPages(pagePersonnamesDO.getTotalPages());
			respTxnTransferObj.getTxnPayload().setPaginationPageSize(pagePersonnamesDO.getSize());

			// process pages if response have results
			if (pagePersonnamesDO.getTotalPages() != 0) {
				if ((pagePersonnamesDO.getTotalPages() - 1) < txnTransferObj.getTxnPayload()
						.getPaginationIndexOfCurrentSlice()) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "1021",
							"Invalid page number in the request for PersonnamesComponent.findByLegalEntityIdPk, total number of pages is "
									+ pagePersonnamesDO.getTotalPages() + ". Note- Pages Index starts with 0");
				}

				List<PersonnamesDO> dbimagePersonnamesDOList = pagePersonnamesDO.getContent();

				// clone the object before sending response.
				// This is important to prevent editing/ future referencing of
				// entity manager level 1 (L1) cached object
				List<PersonnamesDO> clonedPersonnamesDOList = new ArrayList<PersonnamesDO>();
				if (null != dbimagePersonnamesDOList && dbimagePersonnamesDOList.size() > 0) {
					Iterator<PersonnamesDO> itr = dbimagePersonnamesDOList.iterator();
					while (itr.hasNext()) {
						PersonnamesDO thePersonnamesDO = new PersonnamesDO(itr.next());
						clonedPersonnamesDOList.add(thePersonnamesDO);
					}
				}

				// populate reference data attributes
				if (null != clonedPersonnamesDOList && clonedPersonnamesDOList.size() > 0) {
					Iterator<PersonnamesDO> itr = clonedPersonnamesDOList.iterator();
					while (itr.hasNext()) {
						populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),
								itr.next());
					}
				}

				respTxnTransferObj.getTxnPayload().setPersonnamesDOList(clonedPersonnamesDOList);
			}
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			thePersonnamesComponentRule.postExecutePersonnamesCompFindByLegalEntityIdPk(respTxnTransferObj);

		} catch (YugandharCommonException yce) {
			throw yce;
		} catch (RuntimeException re) {
			logger.error("PersonnamesComponent.findByLegalEntityIdPk failed unexpectedly", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,
					"PersonnamesComponent.findByLegalEntityIdPk failed unexpectedly");
			// Transaction Failed due to unknown error, please check statck
			// trace
		}
		return respTxnTransferObj;

	}

	/*
	 * Sample Code
	 * 
	 * @Autowired protected RefEntityObjectTypeComponent
	 * theRefEntityObjectTypeComponent;
	 */

	private void performCommonvalidationBeforeFindByLegalEntityIdPk(TxnTransferObj txnTransferObj) {

		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().getPersonnamesDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"PersonnamesDO is needed in the request");
		}
		PersonnamesDO thePersonnamesDO = (PersonnamesDO) txnTransferObj.getTxnPayload().getPersonnamesDO();
		if (null == thePersonnamesDO.getLegalentityIdpk()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1009",
					"PersonnamesDO.legalentityIdpk should not be null");
		}

		// if inquiry filter is null then consider the inquiry is for ACTIVE
		// records
		if (null == thePersonnamesDO.getInquiryFilter() || thePersonnamesDO.getInquiryFilter().isEmpty()) {
			thePersonnamesDO.setInquiryFilter("ACTIVE");
		} else {
			commonValidationUtil.validateFilterValue(txnTransferObj, thePersonnamesDO.getInquiryFilter());
		}

	}

	public Page<PersonnamesDO> findByLegalEntityIdPkFromRepository(String legalEntityIdPk, String filter,
			Integer requestedPageNumber, Integer requestedPageSize, TxnTransferObj txnTransferObj) {

		Sort localSort = new Sort(new Sort.Order(Sort.Direction.ASC, "legalentityIdpk"));
		Pageable localPageable = new PageRequest(requestedPageNumber, requestedPageSize, localSort);

		Page<PersonnamesDO> pagePersonnamesDOlist = null;
		if (filter.toUpperCase().equals("ACTIVE")) {
			pagePersonnamesDOlist = this.thePersonnamesRepository.findAllActiveByLegalentityIdpk(legalEntityIdPk,
					localPageable);
		} else if (filter.toUpperCase().equals("INACTIVE")) {
			pagePersonnamesDOlist = this.thePersonnamesRepository.findAllInActiveByLegalentityIdpk(legalEntityIdPk,
					localPageable);
		} else if (filter.toUpperCase().equals("ALL")) {
			pagePersonnamesDOlist = this.thePersonnamesRepository.findAllByLegalentityIdpk(legalEntityIdPk,
					localPageable);

		}

		return pagePersonnamesDOlist;

	}

	// Implement this method to get the Key-Value pair of reference data
	// attributes
	protected void populateReferenceDataAttributes(String requesterLanguage, PersonnamesDO thePersonnamesDO) {

		// PersonnameTypeRefkey
		if (!(null == thePersonnamesDO.getPersonnameTypeRefkey()
				|| thePersonnamesDO.getPersonnameTypeRefkey().isEmpty())) {
			RefPersonnameTypeDO theRefPersonnameTypeDO = referenceTableHelper.getRefPersonnameTypeValueByKey(
					requesterLanguage, thePersonnamesDO.getPersonnameTypeRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPersonnameTypeDO) {
				thePersonnamesDO.setPersonnameTypeRefValue(theRefPersonnameTypeDO.getValue());
			}
		}

		// PrefixNameRefkey
		if (!(null == thePersonnamesDO.getPrefixNameRefkey() || thePersonnamesDO.getPrefixNameRefkey().isEmpty())) {
			RefPrefixNameDO theRefPrefixNameDO = referenceTableHelper.getRefPrefixNameValueByKey(requesterLanguage,
					thePersonnamesDO.getPrefixNameRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefPrefixNameDO) {
				thePersonnamesDO.setPrefixNameRefValue(theRefPrefixNameDO.getValue());
			}
		}

		// SuffixNameRefkey
		if (!(null == thePersonnamesDO.getSuffixNameRefkey() || thePersonnamesDO.getSuffixNameRefkey().isEmpty())) {
			RefSuffixNameDO theRefSuffixNameDO = referenceTableHelper.getRefSuffixNameValueByKey(requesterLanguage,
					thePersonnamesDO.getSuffixNameRefkey(), yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSuffixNameDO) {
				thePersonnamesDO.setSuffixNameRefValue(theRefSuffixNameDO.getValue());
			}
		}

		// SourceSystemRefkey
		if (!(null == thePersonnamesDO.getSourceSystemRefkey() || thePersonnamesDO.getSourceSystemRefkey().isEmpty())) {
			RefSourceSystemDO theRefSourceSystemDO = referenceTableHelper.getRefSourceSystemValueByKey(
					requesterLanguage, thePersonnamesDO.getSourceSystemRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSourceSystemDO) {
				thePersonnamesDO.setSourceSystemRefValue(theRefSourceSystemDO.getValue());
			}
		}

	}

}
