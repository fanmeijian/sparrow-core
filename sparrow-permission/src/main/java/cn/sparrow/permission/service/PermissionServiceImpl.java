package cn.sparrow.permission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import cn.sparrow.model.common.PermissionEnum;
import cn.sparrow.model.common.PermissionTargetEnum;
import cn.sparrow.model.common.PermissionTypeEnum;
import cn.sparrow.model.organization.EmployeeToken;
import cn.sparrow.model.organization.OrganizationPositionLevelPK;
import cn.sparrow.model.organization.OrganizationRolePK;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	PermissionExpressionService<String> permissionExpressionService;
	@Autowired
	PermissionExpressionService<OrganizationRolePK> permissionExpressionServiceRole;
	@Autowired
	PermissionExpressionService<OrganizationPositionLevelPK> permissionExpressionServicePositionLevel;
	@Autowired
	PermissionExpressionServiceOrganization permissionExpressionServiceOrganization;

	@Override
	public boolean hasPermission(EmployeeToken employeeToken, PermissionToken permissionToken,
			PermissionEnum permissionEnum) {

		if (permissionToken == null || SecurityContextHolder.getContext().getAuthentication().getName().equals("ROOT")
				|| SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains("ROLE_SYSADMIN"))
			return true;

		for (PermissionExpression<?> permissionExpression : permissionToken.getAllowPermissions().get(permissionEnum)
				.get(PermissionTargetEnum.EMPLOYEE)) {
			permissionExpressionService.evaluate(employeeToken.getEmployeeId(), permissionExpression);
		}

		for (PermissionExpression<?> permissionExpression : permissionToken.getAllowPermissions().get(permissionEnum)
				.get(PermissionTargetEnum.USER)) {
			employeeToken.getUsernames().forEach(username -> {
				permissionExpressionService.evaluate(username, permissionExpression);
			});
		}

		for (PermissionExpression<?> permissionExpression : permissionToken.getAllowPermissions().get(permissionEnum)
				.get(PermissionTargetEnum.SYSROLE)) {
			employeeToken.getSysroleIds().forEach(sysroleId -> {
				permissionExpressionService.evaluate(sysroleId, permissionExpression);
			});
		}

		for (PermissionExpression<?> permissionExpression : permissionToken.getAllowPermissions().get(permissionEnum)
				.get(PermissionTargetEnum.GROUP)) {
			employeeToken.getGroupIds().forEach(groupId -> {
				permissionExpressionService.evaluate(groupId, permissionExpression);
			});
		}

		for (PermissionExpression<?> permissionExpression : permissionToken.getAllowPermissions().get(permissionEnum)
				.get(PermissionTargetEnum.ROLE)) {
			employeeToken.getRoleIds().forEach(organizationRoleId -> {
				permissionExpressionServiceRole.evaluate(organizationRoleId, permissionExpression);
			});
		}

		for (PermissionExpression<?> permissionExpression : permissionToken.getAllowPermissions().get(permissionEnum)
				.get(PermissionTargetEnum.LEVEL)) {
			employeeToken.getPositionLevelIds().forEach(organizationPositionLevelId -> {
				permissionExpressionServicePositionLevel.evaluate(organizationPositionLevelId, permissionExpression);
			});
		}

		for (PermissionExpression<?> permissionExpression : permissionToken.getAllowPermissions().get(permissionEnum)
				.get(PermissionTargetEnum.ORGANIZATION)) {
			employeeToken.getOrganizationIds().forEach(organizationId -> {
				permissionExpressionServiceOrganization.evaluate(organizationId, permissionExpression);
			});
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
	public boolean hasPermission(String employeeId, String tokenId, PermissionEnum permission) {
		// TODO Auto-generated method stub
		return false;
	}
}
