package com.bank.csm.handlers;

import java.util.logging.Logger;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.bank.csm.utils.AppWebUtils;

/**
 * Application localized message handler
 * 
 * @author kumar-sand
 *
 */
@Aspect
public class AppLocaleMessageHandler {
	
	private static final Logger LOGGER = Logger.getLogger(AppLocaleMessageHandler.class.getName());
	
	@Autowired
	private MessageSource messageSource;

	@SuppressWarnings("unchecked")
	@AfterReturning(pointcut = "execution(* com.bank.csm.service.*(..))", returning = "retVal")
	public void initServiceReturnedObject(Object retVal) {
		LOGGER.info("Enter message resolving");
		if(retVal instanceof java.util.Collection) {
			((java.util.Collection<Object>)retVal).stream().forEach(e -> AppWebUtils.initMessages(messageSource, e));
		} else {
			AppWebUtils.initMessages(messageSource, retVal);
		}
		LOGGER.info("Enter message resolving");
	}

	
	
}
