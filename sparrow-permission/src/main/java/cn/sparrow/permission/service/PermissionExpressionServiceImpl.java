package cn.sparrow.permission.service;

import org.springframework.stereotype.Service;

@Service
public class PermissionExpressionServiceImpl<ID> implements PermissionExpressionService<ID> {

	@Override
	public boolean evaluate(ID id, PermissionExpression<?> permissionExpression) {
		switch (permissionExpression.getExpression()) {
		case IN:
			if (permissionExpression.getIds().contains(id)) {
				return true;
			} else {
				return false;
			}
		case NOT_IN:
			if (permissionExpression.getIds().contains(id)) {
				return true;
			} else {
				return false;
			}
		default:
			break;
		}
		return false;
	}

}
