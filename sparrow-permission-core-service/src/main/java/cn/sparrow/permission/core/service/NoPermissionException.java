package cn.sparrow.permission.core.service;

public class NoPermissionException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoPermissionException(String message) {
		super(message);
	}
}
