package com.uploadFile.rest.RestFileUpload.exception;

public class CustomError {

	private String errorCode;
	private String errorDesc;
	
	public CustomError(String errorCode, String errorDesc) {
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	@Override
	public String toString() {
		return "Custom Error { errorCode = " + errorCode + " errorDesc = " + errorDesc + " }";
	}

}
