package cn.sparrow.permission.exception;

public class DenyPermissionException extends Exception {

	private static final long serialVersionUID = 1L;

	public DenyPermissionException(String message) {
		super(message);
	}

	public DenyPermissionException(Throwable cause) {
		super(cause);
	}

	public DenyPermissionException(String message, Throwable cause) {
		super(message, cause);
	}
}
