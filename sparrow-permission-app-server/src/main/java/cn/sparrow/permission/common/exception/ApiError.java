package cn.sparrow.permission.common.exception;

import java.util.Date;

import lombok.Data;

@Data
public class ApiError {
	private String errorCode;
	private String errorMessage;
	private Date timestamp;

	public ApiError(String code, String message) {
		super();
		this.errorCode = code;
		this.errorMessage = message;
		this.timestamp = new Date();
	}

}
