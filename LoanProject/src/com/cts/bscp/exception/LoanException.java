package com.cts.bscp.exception;

@SuppressWarnings("serial")
public class LoanException extends Exception {
	public LoanException(String errMsg){
		super(errMsg);
	}
}
