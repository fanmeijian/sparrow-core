package cn.sparrow.permission.core.api;

import cn.sparrow.permission.model.token.PermissionExpression;

public interface PermissionExpressionService<ID> {

	public boolean evaluate(ID id ,PermissionExpression<?> permissionExpression);
}
