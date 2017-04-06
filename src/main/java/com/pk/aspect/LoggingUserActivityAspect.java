package com.pk.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.pk.configuration.SessionRoutingDataSource;
import com.pk.exception.ErrorCode;
import com.pk.exception.RestException;

import lombok.extern.log4j.Log4j2;

@JsonAutoDetect
@Aspect
@Component
@Log4j2
public class LoggingUserActivityAspect {
	
	@Value("${javatech.saas.based.application.enable}")
	boolean saasBased;
	
	@Autowired
	SessionRoutingDataSource sessionRoutingDataSource;
	


	@Pointcut("execution(* com.pk.controller.*.*(..))")
	private void pointCut() {
		// Nothing to be implement
	}

	

	@Before("pointCut()")
	public void beforeInit() {

		ServletRequestAttributes servletContainer = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		String saas = null;

		if (saasBased) {
			HttpServletRequest request = servletContainer.getRequest();

			saas = request.getHeader("SAAS_ID");

			if (saas == null || saas.trim().length() == 0) {
				log.error("SAAS Id is Empty");
				throw new RestException(ErrorCode.SAAS_USER_EMPTY);
			}

			if (sessionRoutingDataSource.getSaasMap().get(saas) != null) {
				sessionRoutingDataSource.setClientKey(saas);
			} else {
				log.error("Invaild SAAS Id");
				throw new RestException(ErrorCode.SAAS_USER_EMPTY);
			}

		} else {
			sessionRoutingDataSource.setClientKey(saas);
		}

	}
}
