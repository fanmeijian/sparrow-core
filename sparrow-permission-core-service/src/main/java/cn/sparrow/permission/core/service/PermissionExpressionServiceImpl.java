package cn.sparrow.permission.core.service;

import cn.sparrow.permission.core.api.PermissionExpressionService;
import cn.sparrow.permission.model.token.PermissionExpression;

public class PermissionExpressionServiceImpl<ID> implements PermissionExpressionService<ID> {

	@Override
	public boolean evaluate(ID id, PermissionExpression<?> permissionExpression) {
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
		default:
			break;
		}
		return false;
	}

}
