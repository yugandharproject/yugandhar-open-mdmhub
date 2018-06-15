${pojo.getPackageDeclaration()}
/* Generated ${date} by Hibernate Tools ${version} using Yugandhar custom templates. 
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
import com.yugandhar.common.util.IgnoreAttributesUtil;
import com.yugandhar.mdm.component.util.ReferenceTableHelper;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.mdm.keygen.YugandharKeygenerator;

/**
 * Component object for domain model ${pojo.getDeclarationName()}DO class
*@author Yugandhar
*@version 1.0
*@since 1.0
*@see Documentation
*/
<#assign classbody>

@Scope(value = "prototype")
@Component
public class ${pojo.getDeclarationName()}Component {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	//  entityManager instance injection
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	CommonValidationUtil commonValidationUtil;
	
	@Autowired
	${pojo.getDeclarationName()}ComponentRule the${pojo.getDeclarationName()}ComponentRule;
	
	@Autowired
	ReferenceTableHelper referenceTableHelper;
	
	//default transaction response object
	TxnTransferObj respTxnTransferObj;
	
	// default constructor
	public ${pojo.getDeclarationName()}Component() {
	}

	/**
	*This method creates a record in database. generates the idpk if not provided in the request and 
	*populate the updatedByUser, updatedByTxnId, createdTsString, updatedTsString attributes.
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with persisted instance 
	*@throws YugandharCommonException if ${pojo.getDeclarationName()}DO object is not present in the request or other mandatory attributes not present
	*
	*/
	
	public TxnTransferObj persist(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			performCommonvalidationBeforePersist(txnTransferObj);
			the${pojo.getDeclarationName()}ComponentRule.prevalidate${pojo.getDeclarationName()}CompPersit(txnTransferObj);
			// check if idpk is provided, else generate idpk with default UUID generator
			${pojo.getDeclarationName()}DO req${pojo.getDeclarationName()}DO = (${pojo.getDeclarationName()}DO) txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO();
			if (null == req${pojo.getDeclarationName()}DO.getPrimaryKeyDO() || null == req${pojo.getDeclarationName()}DO.getPrimaryKeyDO().getIdPk()) {
				logger.debug("Persist Method - pk Id in request is null, generating new id");
				YugandharKeygenerator yugandharKeygenerator = new YugandharKeygenerator();
				req${pojo.getDeclarationName()}DO.setIdPk(yugandharKeygenerator.generateKey());
			}else
			{
			req${pojo.getDeclarationName()}DO.setIdPk(req${pojo.getDeclarationName()}DO.getPrimaryKeyDO().getIdPk());
				${pojo.getDeclarationName()}DO dbimage${pojo.getDeclarationName()}DO = entityManager.find(${pojo.getDeclarationName()}DO.class, req${pojo.getDeclarationName()}DO.getIdPk());
				if (null != dbimage${pojo.getDeclarationName()}DO) {
					throw commonValidationUtil.createErrorResponse(txnTransferObj, "101", "${pojo.getDeclarationName()}Component.persist failed with Validation Exception"); 
					//Record already present for given Idpk
				}
			}
			the${pojo.getDeclarationName()}ComponentRule.preExecute${pojo.getDeclarationName()}CompPersist(req${pojo.getDeclarationName()}DO, txnTransferObj);
			entityManager.persist(req${pojo.getDeclarationName()}DO);
			entityManager.flush();
			
			req${pojo.getDeclarationName()}DO.setPrimaryKeyDO(null);
			respTxnTransferObj.getTxnPayload().set${pojo.getDeclarationName()}DO(new ${pojo.getDeclarationName()}DO(req${pojo.getDeclarationName()}DO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),respTxnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO());
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
		}catch (RuntimeException re) {
			logger.error("persist failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re, "${pojo.getDeclarationName()}Component.persist failed unexpectedly"); 
			//Transaction Failed due to unknown error, please check statck trace
		}
		return respTxnTransferObj;
	}
	
	/**This service updates the record in database. populate the updatedByUser, updatedByTxnId, updatedTsString attributes
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ${pojo.getDeclarationName()}DO object is not present in the request or mandatory attributes primary key is not present
	*/

	public TxnTransferObj merge(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		try {
			performCommonvalidationBeforeMerge(txnTransferObj);
			the${pojo.getDeclarationName()}ComponentRule.Prevalidate${pojo.getDeclarationName()}CompMerge(txnTransferObj);
			${pojo.getDeclarationName()}DO req${pojo.getDeclarationName()}DO = (${pojo.getDeclarationName()}DO) txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO();
			${pojo.getDeclarationName()}DO dbimage${pojo.getDeclarationName()}DO = entityManager.find(${pojo.getDeclarationName()}DO.class, req${pojo.getDeclarationName()}DO.getIdPk());
			if (null == dbimage${pojo.getDeclarationName()}DO){
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102" , "${pojo.getDeclarationName()}Component.merge failed with Validation Exception"); 
				//Record not found for given Idpk
			}
			
			IgnoreAttributesUtil ignoreAttributesUtil = new IgnoreAttributesUtil();
			String[] strIgnoreProperties = ignoreAttributesUtil.getAttributesToIgnore(req${pojo.getDeclarationName()}DO);
			BeanUtils.copyProperties(req${pojo.getDeclarationName()}DO, dbimage${pojo.getDeclarationName()}DO, strIgnoreProperties);
			entityManager.detach(dbimage${pojo.getDeclarationName()}DO);
			the${pojo.getDeclarationName()}ComponentRule.preExecute${pojo.getDeclarationName()}CompMerge(req${pojo.getDeclarationName()}DO,dbimage${pojo.getDeclarationName()}DO, txnTransferObj);
			${pojo.getDeclarationName()}DO merged${pojo.getDeclarationName()}DO = entityManager.merge(dbimage${pojo.getDeclarationName()}DO);
			entityManager.flush();	
			respTxnTransferObj.getTxnPayload().set${pojo.getDeclarationName()}DO(new ${pojo.getDeclarationName()}DO(merged${pojo.getDeclarationName()}DO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(),respTxnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			the${pojo.getDeclarationName()}ComponentRule.postExecute${pojo.getDeclarationName()}CompMerge(respTxnTransferObj);
			
		} catch (YugandharCommonException yce) {
			throw yce;
		}catch (OptimisticLockException oe) {
			logger.error("merge failed with OptimisticLockException", oe);
			oe.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "2", oe, "OptimisticLockException in ${pojo.getDeclarationName()}Component.merge"); 
			//OptimisticLockException- Row was updated or deleted by another transaction
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
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re,"${pojo.getDeclarationName()}Component.merge failed unexpectedly"); 
			//Transaction Failed due to unknown error, please check statck trace
		}
		return respTxnTransferObj;
	}

	/**
	* This method search the database record based on primary key. 
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ${pojo.getDeclarationName()}DO object is not present in the request or mandatory attributes primary key is not present
	*/
	public TxnTransferObj findById(TxnTransferObj txnTransferObj) {
		respTxnTransferObj = new TxnTransferObj();
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		
		try {
			performCommonvalidationBeforeFindById(txnTransferObj);
			the${pojo.getDeclarationName()}ComponentRule.prevalidate${pojo.getDeclarationName()}CompFindById(txnTransferObj);
			${pojo.getDeclarationName()}DO req${pojo.getDeclarationName()}DO = (${pojo.getDeclarationName()}DO) txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO();
			${pojo.getDeclarationName()}DO dbimage${pojo.getDeclarationName()}DO = entityManager.find(${pojo.getDeclarationName()}DO.class, req${pojo.getDeclarationName()}DO.getIdPk());
			if (null == dbimage${pojo.getDeclarationName()}DO){
				throw commonValidationUtil.createErrorResponse(txnTransferObj, "102", "${pojo.getDeclarationName()}Component.findbyId failed with Validation Exception"); 
				//Record not found
			}
			entityManager.flush();
			respTxnTransferObj.getTxnPayload().set${pojo.getDeclarationName()}DO(new ${pojo.getDeclarationName()}DO(dbimage${pojo.getDeclarationName()}DO));
			populateReferenceDataAttributes(respTxnTransferObj.getTxnHeader().getRequesterLanguage(), respTxnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO());
			respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
			the${pojo.getDeclarationName()}ComponentRule.postExecute${pojo.getDeclarationName()}CompFindById(respTxnTransferObj);
			
		}catch (YugandharCommonException yce) {
			throw yce;
		}
		catch (RuntimeException re) {
			logger.error("findById failed", re);
			re.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "1", re, "${pojo.getDeclarationName()}Component.findById failed unexpectedly"); 
			//Transaction Failed due to unknown error, please check statck trace
		}
		return respTxnTransferObj;

	}
	/**
	* perform the common validation before persisting ${pojo.getDeclarationName()}DO object in the database.
	* populate createdTimestamp, updatedTimestamp, transaction reference Id and user
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ${pojo.getDeclarationName()}DO object is not present in the request or mandatory attributes business key is not present
	*/
	private void performCommonvalidationBeforePersist(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		
		if (null == txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO())
		{
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001", "${pojo.getDeclarationName()}DO is needed in the request" ); 
		}
		${pojo.getDeclarationName()}DO the${pojo.getDeclarationName()}DO = (${pojo.getDeclarationName()}DO)txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO();
		the${pojo.getDeclarationName()}DO.setCreatedTs(new Date());
		the${pojo.getDeclarationName()}DO.setUpdatedTs(new Date());
		the${pojo.getDeclarationName()}DO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		the${pojo.getDeclarationName()}DO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
		}
	
	/**
	* perform the common validation before updating ${pojo.getDeclarationName()}DO object in the database.
	* populate updatedTimestamp, transaction reference Id and user
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ${pojo.getDeclarationName()}DO object is not present in the request or mandatory attributes business key is not present
	*/
	private void performCommonvalidationBeforeMerge(TxnTransferObj txnTransferObj) {
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);
		if (null == txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO())
		{
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001", "${pojo.getDeclarationName()}DO is needed in the request" ); 
		}
		if (null == txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO().getIdPk() || txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO().getIdPk().isEmpty()){
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004","${pojo.getDeclarationName()}DO.idpk should not be null"); 
		}
		
		if (null == txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO().getVersion()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1010",
					"${pojo.getDeclarationName()}DO.version should not be null");
		}
		
		${pojo.getDeclarationName()}DO the${pojo.getDeclarationName()}DO = (${pojo.getDeclarationName()}DO)txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO();
		the${pojo.getDeclarationName()}DO.setUpdatedTs(new Date());
		the${pojo.getDeclarationName()}DO.setUpdatedByTxnId(txnTransferObj.getTxnHeader().getTxnMessageId());
		the${pojo.getDeclarationName()}DO.setUpdatedByUser(txnTransferObj.getTxnHeader().getUserName());
		}
	
	/**
	* perform the common validation that ${pojo.getDeclarationName()}DO and idpk is not null
	*@since 1.0
	*@param  txnTransferObj  Transfer Object TxnTransferObj instance
	*@return  txnTransferObj Returns the Transfer Object TxnTransferObj instance populated with database instance 
	*@throws YugandharCommonException if ${pojo.getDeclarationName()}DO object is not present in the request or mandatory attributes business key is not present
	*/
		private void performCommonvalidationBeforeFindById(TxnTransferObj txnTransferObj) {
		
		commonValidationUtil.validateHeaderForInternalTxn(txnTransferObj);

	if (null == txnTransferObj.getTxnPayload().get${pojo.getDeclarationName()}DO()) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001", "${pojo.getDeclarationName()}DO is needed in the request" ); 
			}
			${pojo.getDeclarationName()}DO the${pojo.getDeclarationName()}DO = (${pojo.getDeclarationName()}DO) txnTransferObj
					.getTxnPayload().get${pojo.getDeclarationName()}DO();
			if (null == the${pojo.getDeclarationName()}DO.getIdPk()) {
				throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1004","${pojo.getDeclarationName()}DO.idpk should not be null"); 
			}
			
		}
		
		
	//Implement this method to get the Key-Value pair of reference data attributes
	protected void populateReferenceDataAttributes(String requesterLanguage, ${pojo.getDeclarationName()}DO the${pojo.getDeclarationName()}DO){
		
		/*	// SAMPLEREFLOVRefkey
		if (!(null == the${pojo.getDeclarationName()}DO.getSAMPLEREFLOVRefkey() || the${pojo.getDeclarationName()}DO.getSAMPLEREFLOVRefkey().isEmpty())) {
			RefSAMPLEREFLOVDO theRefSAMPLEREFLOVDO = referenceTableHelper.getRefSAMPLEREFLOVValueByKey(
					requesterLanguage, the${pojo.getDeclarationName()}DO.getSAMPLEREFLOVRefkey(),
					yugandharConstants.FILTER_VALUE_ACTIVE);
			if (null != theRefSAMPLEREFLOVDO) {
				the${pojo.getDeclarationName()}DO.setSAMPLEREFLOVRefValue(theRefSAMPLEREFLOVDO.getValue());
			}
		} */
		
			
		
	}
		
}

</#assign>

${classbody}