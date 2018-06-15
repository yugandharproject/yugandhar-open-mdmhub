package com.yugandhar.common.aop.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yugandhar.common.constant.yugandharConstants;

@Aspect
@Component
public class YugandharEntryExitAspectLogger {
	
	private static final Logger logger = LoggerFactory.getLogger(yugandharConstants.YUGANDHAR_COMMON_LOGGER);

	    
	@Before("execution(* com.yugandhar..*(..))")
	public void logMethodBeforeExecution(JoinPoint joinPoint) {
		if(logger.isDebugEnabled()){
		logger.debug("Execution of : " +joinPoint.getSignature().getDeclaringTypeName() +"."+ joinPoint.getSignature().getName() + " Method - Started");
		}
		
	}

	@After("execution(* com.yugandhar..*(..))")
	public void logAfter(JoinPoint joinPoint) {
		logger.debug("After Execution of : " +joinPoint.getSignature().getDeclaringTypeName() +"."+ joinPoint.getSignature().getName() + " Method");

	}
	
	@AfterReturning(
			pointcut = "execution(* com.yugandhar..*(..))",
			returning= "result")
	public void logMethodAfterReturning(JoinPoint joinPoint, Object result) {
		if(logger.isDebugEnabled()){
			logger.debug("Execution of : " +joinPoint.getSignature().getDeclaringTypeName() +"."+ joinPoint.getSignature().getName() + " Method - Completed");
		}
	}
	
	@AfterThrowing(	pointcut = "execution(* com.yugandhar..*(..))",
			throwing= "error")
	public void logMethodAfterThrowing(JoinPoint joinPoint, Throwable error) {

		logger.error("Execution FAILED for : " +joinPoint.getSignature().getDeclaringTypeName() +"."+ joinPoint.getSignature().getName() + " Method with error:" + error);

	}

}