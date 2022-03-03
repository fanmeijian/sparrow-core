package cn.sparrow.permission.service;

import java.awt.geom.IllegalPathStateException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import cn.sparrow.permission.constant.PermissionEnum;
import cn.sparrow.permission.constant.PermissionTargetEnum;
import cn.sparrow.permission.constant.PermissionTypeEnum;
import cn.sparrow.permission.listener.CurrentEntityManagerFactory;
import cn.sparrow.permission.model.organization.EmployeeUser;
import cn.sparrow.permission.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.permission.model.organization.OrganizationRolePK;

public class PermissionServiceImpl implements PermissionService {

	PermissionExpressionService<String> permissionExpressionService = new PermissionExpressionServiceImpl<String>();
	PermissionExpressionService<OrganizationRolePK> permissionExpressionServiceRole = new PermissionExpressionServiceImpl<OrganizationRolePK>();
	PermissionExpressionService<OrganizationPositionLevelPK> permissionExpressionServicePositionLevel = new PermissionExpressionServiceImpl<OrganizationPositionLevelPK>();
	PermissionExpressionServiceOrganization permissionExpressionServiceOrganization;

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
					for (String username : employeeToken.getUsernames()) {
						if (permissionExpressionService.evaluate(username, permissionExpression)) {
							return true;
						}
					}
				}

				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.SYSROLE))) {
					for (String sysroleId : employeeToken.getSysroleIds()) {
						if (permissionExpressionService.evaluate(sysroleId, permissionExpression)) {
							return true;
						}
					}
				}

				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.GROUP))) {
					for (String groupId : employeeToken.getGroupIds()) {
						if (permissionExpressionService.evaluate(groupId, permissionExpression)) {
							return true;
						}
					}
				}

				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.ROLE))) {
					for (OrganizationRolePK organizationRoleId : employeeToken.getRoleIds()) {
						if (permissionExpressionServiceRole.evaluate(organizationRoleId, permissionExpression)) {
							return true;
						}
					}
				}

				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.LEVEL))) {
					for (OrganizationPositionLevelPK organizationPositionLevelId : employeeToken
							.getPositionLevelIds()) {
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
					for (String username : employeeToken.getUsernames()) {
						if (permissionExpressionService.evaluate(username, permissionExpression)) {
							return true;
						}
					}
				}

				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.SYSROLE))) {
					for (String sysroleId : employeeToken.getSysroleIds()) {
						if (permissionExpressionService.evaluate(sysroleId, permissionExpression)) {
							return true;
						}
					}
				}

				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.GROUP))) {
					for (String groupId : employeeToken.getGroupIds()) {
						if (permissionExpressionService.evaluate(groupId, permissionExpression)) {
							return true;
						}
					}
				}

				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.ROLE))) {
					for (OrganizationRolePK organizationRoleId : employeeToken.getRoleIds()) {
						if (permissionExpressionServiceRole.evaluate(organizationRoleId, permissionExpression)) {
							return true;
						}
					}
				}

				for (PermissionExpression<?> permissionExpression : nullToEmptyList(
						permissionExpress.get(PermissionTargetEnum.LEVEL))) {
					for (OrganizationPositionLevelPK organizationPositionLevelId : employeeToken
							.getPositionLevelIds()) {
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
		return false;
	}

	private List<PermissionExpression<?>> nullToEmptyList(List<PermissionExpression<?>> list) {
		return list == null ? Collections.emptyList() : list;
	}

	@Override
	public boolean hasPermission(String username, PermissionToken permissionToken, PermissionEnum permissionEnum) {
		if (username.equals("ROOT"))
			return true;
		return this.hasPermission(new EmployeeTokenServiceImpl().getEmployeeToken(username), permissionToken,
				permissionEnum);
	}
}
