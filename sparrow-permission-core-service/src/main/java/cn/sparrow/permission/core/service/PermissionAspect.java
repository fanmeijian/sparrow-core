package cn.sparrow.permission.core.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import cn.sparrow.permission.core.api.ScopePermission;
import cn.sparrow.permission.model.common.CurrentUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class PermissionAspect {
	@Around("@annotation(scopePermission)")
	public Object hasPermission(ProceedingJoinPoint joinPoint, ScopePermission scopePermission) throws Throwable {
		log.debug("permission aspect work: {}", scopePermission.username());
		if(!scopePermission.username().equals("$curUser")) {
			log.debug("cur user: {}", CurrentUser.get());
		}
		return joinPoint.proceed();
	}
}
