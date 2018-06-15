package com.yugandhar.common.aop.ruleoverride;

/*import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yugandhar.common.constant.yugandharConstants;
import com.yugandhar.common.transobj.TxnTransferObj;
import com.yugandhar.mdm.extern.dobj.LegalentityDO;*/

public class YugandharRuleOverrideAspect {
	
}

// Sample Code
/*
@Aspect
@Component
public class YugandharRuleOverrideAspect {

    
	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);
	
	
	@Around("execution(* com.yugandhar.mdm.corecomponent.LegalentityComponentRule.preExecuteLegalentityCompMerge(..)) && args(reqLegalentityDO, dbimageLegalentityDO, txnTransferObj)")
	public void preExecuteLegalentityCompMerge(ProceedingJoinPoint pjp, LegalentityDO reqLegalentityDO, LegalentityDO dbimageLegalentityDO,
			TxnTransferObj txnTransferObj) {
		
		if(logger.isDebugEnabled()){
		logger.debug("Execution of @Around advice on method: " +pjp.getSignature().getDeclaringTypeName() +"."+ pjp.getSignature().getName() + " started");
		}
		
		// if you dont defined pjp.proceed then the logic inside com.yugandhar.mdm.corecomponent.LegalentityComponentRule.preExecuteLegalentityCompMerge() method will not be invoked.
		 * You may completely override the logic of this method or consider before, after or around advice of aop on the same.
		try {
			pjp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("Execution of @Around advice on method: " +pjp.getSignature().getDeclaringTypeName() +"."+ pjp.getSignature().getName() + " completed");
			}
	
		
	}
}*/
