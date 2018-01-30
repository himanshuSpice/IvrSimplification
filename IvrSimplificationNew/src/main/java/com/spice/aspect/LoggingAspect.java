package com.spice.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
	/*@Before("execution(public * com.spice.service.*.*(..))")
	public void logBefore(JoinPoint joinpoint) 
	{
	
		Object className = joinpoint.getTarget().getClass().getName();
		String methodName = joinpoint.getSignature().getName();
		log.info(className + "->" + methodName + "(" + Arrays.asList(joinpoint.getArgs()) + ")");
	}*/
	
	@Around("execution(* com.spice.controller.*.*(..))||execution(* com.spice.exception.*.*(..)) || execution(* com.spice.service.*.*(..)) || execution(* com.spice.utility.SocketUrl.*(..)) ")
	public Object log(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		Object value;

		log.info("Class: {} , Method: {} ,Req Data- :{} ",
				proceedingJoinPoint.getTarget().getClass().getSimpleName(),
				proceedingJoinPoint.getSignature().getName(), Arrays.asList(proceedingJoinPoint.getArgs()));

		try {

			value = proceedingJoinPoint.proceed();
			log.info("Class: {} , Method: {} ,Resp Data- :{} ", proceedingJoinPoint.getTarget().getClass().getSimpleName(),
					proceedingJoinPoint.getSignature().getName(), value.toString());

		} catch (Throwable throwable) {
			log.error("exception - " + throwable);
			throw throwable;
		}
		return value;
	}
}
