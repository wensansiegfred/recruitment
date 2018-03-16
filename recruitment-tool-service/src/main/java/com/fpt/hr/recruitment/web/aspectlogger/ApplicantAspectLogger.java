package com.fpt.hr.recruitment.web.aspectlogger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy
@Aspect
@Component
public class ApplicantAspectLogger {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private long startTime;
    private long endTime;
    private double elapsedTime;
	
	@Before("execution(* com.fpt.hr.recruitment.web.controller.ApplicantController.*(..))")
	public void doBefore(JoinPoint joinPoint){
		startTime = System.currentTimeMillis();
		logger.info("INFO::Application Controller START EXECUTION OF METHOD: " + joinPoint.getSignature().getName() + " | TIME: " + System.currentTimeMillis());		
	}
	
	@After("execution(* com.fpt.hr.recruitment.web.controller.ApplicantController.*(..))")
	public void doAfter(JoinPoint joinPoint){
		endTime = System.currentTimeMillis();
		elapsedTime = endTime - startTime;
		logger.info("INFO::Application Controller::TOTAL EXECUTION OF" + joinPoint.getSignature().getName() + " | IN: " + elapsedTime + " milliseconds.");		
	}
}
