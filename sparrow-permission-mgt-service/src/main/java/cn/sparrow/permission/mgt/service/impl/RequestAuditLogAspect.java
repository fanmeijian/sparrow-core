package cn.sparrow.permission.mgt.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.sparrow.permission.mgt.api.RequestAuditLog;
import cn.sparrow.permission.mgt.service.repository.RequestAuditLogRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class RequestAuditLogAspect {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	RequestAuditLogRepository requestAuditLogRepository;

	@Pointcut("execution(public * *(..))")
	public void publicMethod() {
		System.out.println("public method");
	}

	@Around("publicMethod() && @annotation(auditLog)")
	public Object LogExecutionTimeByMethod(ProceedingJoinPoint joinPoint, RequestAuditLog auditLog) throws Throwable {
		System.out.println(joinPoint + "1 -> " + auditLog);
		return joinPoint.proceed();
	}

	@Around("publicMethod() && @within(auditLog)")
	public Object LogExecutionTimeByClass(ProceedingJoinPoint joinPoint, RequestAuditLog auditLog) throws Throwable {
		String actor = "anonymous";
		String origin = "unidentified";

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			actor = ((UserDetails) authentication.getPrincipal()).getUsername();
		}

		try {
			origin = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
					.getRemoteAddr();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("{} {} {} {}", joinPoint, joinPoint.getArgs(), actor, origin);
		requestAuditLogRepository.save(new cn.sparrow.permission.model.common.RequestAuditLog(origin,
				joinPoint.toString(), new ObjectMapper().writeValueAsString(joinPoint.getArgs())));
		return joinPoint.proceed();
	}
}
