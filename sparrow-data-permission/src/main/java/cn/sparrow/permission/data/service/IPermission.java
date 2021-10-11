package cn.sparrow.permission.data.service;

public interface IPermission<T> {

	public boolean isConfigPermission(T target);

	public boolean hasPermission(T target, String username);
}
