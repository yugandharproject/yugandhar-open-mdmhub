package com.yugandhar.mdm.composite.service;
/* Generated Jul 3, 2017 2:32:54 PM by Hibernate Tools 5.2.1.Final using Yugandhar custom templates. 
Generated and to be used in accordance with Yugandhar Licensing Terms. */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.exception.YugandharCommonException;
import com.yugandhar.common.extern.transferobj.TxnPayload;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.common.util.CommonValidationUtil;
import com.yugandhar.mdm.corecomponent.EntityGroupAssocComponent;
import com.yugandhar.mdm.corecomponent.EntityGroupComponent;
import com.yugandhar.mdm.extern.dobj.EntityGroupAssocDO;
import com.yugandhar.mdm.extern.dobj.EntityGroupDO;

@Scope(value = "prototype")
@Service("com.yugandhar.mdm.composite.service.UpdateEntityGroupService")
public class UpdateEntityGroupService {

	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	TxnTransferObj txnTransferObjResponse;
	TxnTransferObj respTxnTransferObj;
	TxnPayload respTxnPayload;
	EntityGroupDO respEntityGroupDO;
	TxnTransferObj transitTxnTransferObj;
	TxnPayload transitTxnPayload;

	@Autowired
	CommonValidationUtil commonValidationUtil;

	@Autowired
	EntityGroupComponent entityGroupComponent;

	@Autowired
	EntityGroupAssocComponent entityGroupAssocComponent;

	public UpdateEntityGroupService() {
		txnTransferObjResponse = new TxnTransferObj();
		respEntityGroupDO = new EntityGroupDO();
		respTxnTransferObj = new TxnTransferObj();
		respTxnPayload = new TxnPayload();
		transitTxnTransferObj = new TxnTransferObj();
	}

	@Transactional
	public TxnTransferObj process(TxnTransferObj txnTransferObj) throws YugandharCommonException {
		TxnTransferObj transitTxnTransferObj = new TxnTransferObj();
		TxnPayload transitTxnPayload = null;
		respTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());

		try {
			// TODO logic here
			validateRequestEntityGroupDO(txnTransferObj);
			EntityGroupDO reqEntityGroupDO = txnTransferObj.getTxnPayload().getEntityGroupDO();
			EntityGroupDO respEntityGroupDO = null;

			transitTxnPayload = new TxnPayload();
			transitTxnPayload.setEntityGroupDO(reqEntityGroupDO);
			transitTxnTransferObj.setTxnPayload(transitTxnPayload);
			transitTxnTransferObj = entityGroupComponent.merge(transitTxnTransferObj);
			respEntityGroupDO = transitTxnTransferObj.getTxnPayload().getEntityGroupDO();

			// Process EntityGroupAssoc
			if (null != reqEntityGroupDO.getEntityGroupAssocDOList()
					&& reqEntityGroupDO.getEntityGroupAssocDOList().size() > 0) {
				Iterator<EntityGroupAssocDO> theEntityGroupAssocDOListIterator = reqEntityGroupDO
						.getEntityGroupAssocDOList().iterator();
				List<EntityGroupAssocDO> respLePhoneAssocDOList = new ArrayList<EntityGroupAssocDO>();

				while (theEntityGroupAssocDOListIterator.hasNext()) {
					EntityGroupAssocDO reqEntityGroupAssocDO = theEntityGroupAssocDOListIterator.next();

					if (null != reqEntityGroupAssocDO) {
						if(null != reqEntityGroupAssocDO.getIdPk() && !reqEntityGroupAssocDO.getIdPk().isEmpty())
						{
							transitTxnPayload = new TxnPayload();
							reqEntityGroupAssocDO.setEntityGroupIdpk(respEntityGroupDO.getIdPk());
							transitTxnPayload.setEntityGroupAssocDO(reqEntityGroupAssocDO);
							transitTxnTransferObj.setTxnPayload(transitTxnPayload);
							transitTxnTransferObj = entityGroupAssocComponent.merge(transitTxnTransferObj);
							respLePhoneAssocDOList.add(transitTxnTransferObj.getTxnPayload().getEntityGroupAssocDO());
						} else
						{
							transitTxnPayload = new TxnPayload();
							reqEntityGroupAssocDO.setEntityGroupIdpk(respEntityGroupDO.getIdPk());
							transitTxnPayload.setEntityGroupAssocDO(reqEntityGroupAssocDO);
							transitTxnTransferObj.setTxnPayload(transitTxnPayload);
							transitTxnTransferObj = entityGroupAssocComponent.persist(transitTxnTransferObj);
							respLePhoneAssocDOList.add(transitTxnTransferObj.getTxnPayload().getEntityGroupAssocDO());
						}

					}

				}
				respEntityGroupDO.setEntityGroupAssocDOList(respLePhoneAssocDOList);

			}

			// Final Response object
			respTxnPayload.setEntityGroupDO(respEntityGroupDO);
			respTxnTransferObj.setTxnPayload(respTxnPayload);

		} catch (YugandharCommonException yce) {
			logger.error("Composite Service failed", yce);
			throw yce;
		} catch (Exception e) {
			// write the logic to get error message based on error code. Error
			// code is hard-coded here
			logger.error("Composite Service failed", e);
			e.printStackTrace();
			throw commonValidationUtil.populateErrorResponse(txnTransferObj, "5", e,
					"CreateEntityGroupService failed unexpectedly");

		}
		respTxnTransferObj.setResponseCode(yugandharConstants.RESPONSE_CODE_SUCCESS);
		return respTxnTransferObj;

	}

	private void validateRequestEntityGroupDO(TxnTransferObj txnTransferObj) {
		transitTxnTransferObj.setTxnHeader(txnTransferObj.getTxnHeader());
		if (null == txnTransferObj.getTxnPayload().getEntityGroupDO()) {
			throw commonValidationUtil.populateValidationErrorResponse(txnTransferObj, "1001",
					"EntityGroupDO is needed in the request");
		}

	}
}
