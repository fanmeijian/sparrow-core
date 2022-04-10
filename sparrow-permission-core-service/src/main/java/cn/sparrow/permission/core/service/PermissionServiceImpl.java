package cn.sparrow.permission.core.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.constant.PermissionTargetEnum;
import cn.sparrow.permission.constant.PermissionTypeEnum;
import cn.sparrow.permission.core.api.PermissionExpressionService;
import cn.sparrow.permission.core.api.PermissionService;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.permission.model.organization.OrganizationRolePK;
import cn.sparrow.permission.model.token.EmployeeToken;
import cn.sparrow.permission.model.token.PermissionExpression;
import cn.sparrow.permission.model.token.PermissionToken;
import cn.sparrow.permission.model.token.SparrowPermissionToken;

public class PermissionServiceImpl implements PermissionService {
	private EntityManager entityManager;
	PermissionExpressionService<String> permissionExpressionService;
	PermissionExpressionServiceGroup permissionExpressionServiceGroup;
	PermissionExpressionService<OrganizationRolePK> permissionExpressionServiceRole;
	PermissionExpressionService<OrganizationPositionLevelPK> permissionExpressionServicePositionLevel;
	PermissionExpressionServiceOrganization permissionExpressionServiceOrganization;

	public PermissionServiceImpl() {

	}

	public PermissionServiceImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.permissionExpressionService = new PermissionExpressionServiceImpl<String>();
		this.permissionExpressionServiceRole = new PermissionExpressionServiceImpl<OrganizationRolePK>();
		this.permissionExpressionServicePositionLevel = new PermissionExpressionServiceImpl<OrganizationPositionLevelPK>();
		this.permissionExpressionServiceOrganization = new PermissionExpressionServiceOrganization(entityManager);
		this.permissionExpressionServiceGroup = new PermissionExpressionServiceGroup(entityManager);
	}

	@Override
	public boolean hasPermission(@NotNull EmployeeToken employeeToken, @NotNull PermissionToken permissionToken,
			PermissionEnum permissionEnum) {

//		if (permissionToken == null || SecurityContextHolder.getContext().getAuthentication().getName().equals("ROOT"))
//				SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("ROLE_SYSADMIN1"))
//			return true;

		if (permissionToken == null)
			return true;
		if (employeeToken == null) {
			return false;
		}
		Map<PermissionEnum, Map<PermissionTargetEnum, List<PermissionExpression<?>>>> denyPermission = permissionToken
				.getDenyPermissions();
		Map<PermissionEnum, Map<PermissionTargetEnum, List<PermissionExpression<?>>>> allowPermission = permissionToken
				.getAllowPermissions();

		// deny
		if (denyPermission != null) {
			Map<PermissionTargetEnum, List<PermissionExpression<?>>> permissionExpress = denyPermission
					.get(permissionEnum);
			if (permissionExpress != null) {
				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.EMPLOYEE))) {
					if (permissionExpressionService.evaluate(employeeToken.getEmployeeId(), permissionExpression)) {
						return false;
					}
				}

				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.USER))) {
					if (permissionExpressionService.evaluate(employeeToken.getUsername(), permissionExpression)) {
						return true;
					}
				}

				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.SYSROLE))) {
					for (String sysroleId : employeeToken.getSysroles()) {
						if (permissionExpressionService.evaluate(sysroleId, permissionExpression)) {
							return true;
						}
					}
				}

				// 需同时展开子组，看是否在里面。
				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.GROUP))) {
					for (String groupId : employeeToken.getAllGroups()) {
						if (permissionExpressionServiceGroup.evaluate(groupId, permissionExpression)) {
							return true;
						}
					}
				}

				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.ROLE))) {
					for (OrganizationRolePK organizationRoleId : employeeToken.getOrgRoleIds()) {
						if (permissionExpressionServiceRole.evaluate(organizationRoleId, permissionExpression)) {
							return true;
						}
					}
				}

				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.LEVEL))) {
					for (OrganizationPositionLevelPK organizationPositionLevelId : employeeToken.getOrgJobLevelIds()) {
						if (permissionExpressionServicePositionLevel.evaluate(organizationPositionLevelId,
								permissionExpression)) {
							return true;
						}
					}
				}

				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.ORGANIZATION))) {
					for (String organizationId : employeeToken.getOrganizationIds()) {
						if (permissionExpressionServiceOrganization.evaluate(organizationId, permissionExpression)) {
							return true;
						}
					}
				}
			}
		}

		// allow
		if (allowPermission != null) {
			Map<PermissionTargetEnum, List<PermissionExpression<?>>> permissionExpress = allowPermission
					.get(permissionEnum);
			if (permissionExpress != null) {
				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.EMPLOYEE))) {
					if (permissionExpressionService.evaluate(employeeToken.getEmployeeId(), permissionExpression)) {
						return true;
					}
				}

				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.USER))) {
					if (permissionExpressionService.evaluate(employeeToken.getUsername(), permissionExpression)) {
						return true;
					}
				}

				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.SYSROLE))) {
					for (String sysroleId : employeeToken.getSysroles()) {
						if (permissionExpressionService.evaluate(sysroleId, permissionExpression)) {
							return true;
						}
					}
				}

				// 需同时展开子组，看是否在里面。
				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.GROUP))) {
					for (String groupId : employeeToken.getAllGroups()) {
						if (permissionExpressionServiceGroup.evaluate(groupId, permissionExpression)) {
							return true;
						}
					}
				}

				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.ROLE))) {
					for (OrganizationRolePK organizationRoleId : employeeToken.getOrgRoleIds()) {
						if (permissionExpressionServiceRole.evaluate(organizationRoleId, permissionExpression)) {
							return true;
						}
					}
				}

				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.LEVEL))) {
					for (OrganizationPositionLevelPK organizationPositionLevelId : employeeToken.getOrgJobLevelIds()) {
						if (permissionExpressionServicePositionLevel.evaluate(organizationPositionLevelId,
								permissionExpression)) {
							return true;
						}
					}
				}

				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.ORGANIZATION))) {
					for (String organizationId : employeeToken.getOrganizationIds()) {
						if (permissionExpressionServiceOrganization.evaluate(organizationId, permissionExpression)) {
							return true;
						}
					}
				}
			}

		} else {
			return true;
		}
		return false;

	}

	@Override
	public boolean isConfigPermission(PermissionToken permissionToken, PermissionEnum permissionEnum) {
		if (isConfigPermission(permissionToken, permissionEnum, PermissionTypeEnum.ALLOW)
				|| isConfigPermission(permissionToken, permissionEnum, PermissionTypeEnum.DENY))
			return true;
		else
			return false;
	}

	@Override
	public boolean isConfigPermission(PermissionToken permissionToken, PermissionEnum permissionEnum,
			PermissionTypeEnum permissionTypeEnum) {

		if (permissionTypeEnum.equals(PermissionTypeEnum.ALLOW)) {
			if (permissionToken.getAllowPermissions().get(permissionEnum) != null)
				return true;
		}

		if (permissionTypeEnum.equals(PermissionTypeEnum.DENY)) {
			if (permissionToken.getDenyPermissions().get(permissionEnum) != null)
				return true;
		}

		return false;
	}

	@Override
	public boolean hasPermission(String employeeId, String tokenId, PermissionEnum permissionEnum) {
		EmployeeToken employeeToken = new EmployeeTokenServiceImpl(entityManager)
				.getEmployeeTokenByEmployeeId(employeeId);
		PermissionToken permissionToken = entityManager.find(SparrowPermissionToken.class, tokenId)
				.getPermissionToken();
		return hasPermission(employeeToken, permissionToken, permissionEnum);
	}

	private List<PermissionExpression<?>> nullToEmptyList(List<PermissionExpression<?>> list) {
		return list == null ? Collections.emptyList() : list;
	}

	@Override
	public boolean hasPermission(String username, PermissionToken permissionToken, PermissionEnum permissionEnum) {
		if (username.equals("ROOT"))
			return true;
		return this.hasPermission(new EmployeeTokenServiceImpl(entityManager).getEmployeeTokenByUsername(username),
				permissionToken, permissionEnum);
	}

}
