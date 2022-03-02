package cn.sparrow.permission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionExpressionServiceOrganization {

	@Autowired
	OrganizationHelper organizationHelper;

	public boolean evaluate(String id, PermissionExpression<?> permissionExpression) {
		switch (permissionExpression.getExpression()) {
		case IN:
			if (permissionExpression.getIds().contains(id)) {
				return true;
			}
			break;
		case NOT_IN:
			if (permissionExpression.getIds().contains(id)) {
				return true;
			}
			break;
		case IS_ABOVE:
			if (organizationHelper.isAbove(permissionExpression.getIds().get(0).toString(), id)) {
				return true;
			}
			break;
		case IS_AND_ABOVE:
			if (organizationHelper.isAbove(permissionExpression.getIds().get(0).toString(), id)
					|| permissionExpression.getIds().get(0).toString().equals(id)) {
				return true;
			}
			break;
		case IS_BELOW:
			if (organizationHelper.isBelow(permissionExpression.getIds().get(0).toString(), id)) {
				return true;
			}
			break;
		case IS_AND_BELOW:
			if (organizationHelper.isBelow(permissionExpression.getIds().get(0).toString(), id)
					|| permissionExpression.getIds().get(0).toString().equals(id)) {
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}

}
