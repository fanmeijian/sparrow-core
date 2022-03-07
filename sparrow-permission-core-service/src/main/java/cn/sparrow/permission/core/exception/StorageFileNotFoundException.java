package cn.sparrow.permission.core.exception;

public class StorageFileNotFoundException extends RuntimeException {
	public StorageFileNotFoundException(String message) {
		super(message);
	}

	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
