package cn.sparrow.permission.exception;

public class NoPermissionException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoPermissionException(String message) {
		super(message);
	}

	public NoPermissionException(Throwable cause) {
		super(cause);
	}

	public NoPermissionException(String message, Throwable cause) {
		super(message, cause);
	}
}
