package cn.sparrow.permission.core.service;

public class DenyPermissionException extends Exception {

	private static final long serialVersionUID = 1L;

	public DenyPermissionException(String message) {
		super(message);
	}
}
