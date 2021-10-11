package cn.sparrow.permission.service;

public interface IPermission<T> {

	public boolean isConfigPermission(T target);

	public boolean hasPermission(T target, String username);
}
