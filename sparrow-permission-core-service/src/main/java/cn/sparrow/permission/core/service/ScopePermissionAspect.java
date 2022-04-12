package cn.sparrow.permission.core.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.core.api.PermissionService;
import cn.sparrow.permission.core.api.ScopePermission;
import cn.sparrow.permission.exception.NoPermissionException;
import cn.sparrow.permission.model.common.CurrentUser;
import cn.sparrow.permission.model.resource.Scope;
import cn.sparrow.permission.model.token.PermissionToken;
import cn.sparrow.permission.model.token.SparrowPermissionToken;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class ScopePermissionAspect {

	@PersistenceContext
	EntityManager entityManager;

	@Around("@annotation(scopePermission)")
	public Object hasPermission(ProceedingJoinPoint joinPoint, ScopePermission scopePermission) throws Throwable {
		log.debug("permission aspect work: {}", scopePermission.username());
		PermissionService permissionService = new PermissionServiceImpl(entityManager);
		log.debug("entityManager : {}", entityManager);
		if (!scopePermission.username().equals("$curUser")) {
			log.debug("cur user: {}", CurrentUser.get());
		}
		Query query = entityManager.createNamedQuery("Scope.findByCode", Scope.class);
		query.setParameter("code", scopePermission.scope());
		try {
			Scope scope = (Scope) query.getSingleResult();
			PermissionToken permissionToken = entityManager.find(SparrowPermissionToken.class, scope.getPermissionTokenId()).getPermissionToken();
			if (permissionToken != null) {
				if(!permissionService.hasPermission(CurrentUser.get(), permissionToken,
						PermissionEnum.EXECUTE)) {
					throw new NoPermissionException("没有权限: " + scopePermission.scope() + PermissionEnum.EXECUTE);
				}
			}
		} catch (NoResultException e) {
			// TODO: handle exception
		}
		

		return joinPoint.proceed();
	}
}
