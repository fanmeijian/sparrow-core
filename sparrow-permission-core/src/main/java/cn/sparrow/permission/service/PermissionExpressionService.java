package cn.sparrow.permission.service;

public interface PermissionExpressionService<ID> {

	public boolean evaluate(ID id ,PermissionExpression<?> permissionExpression);
}
