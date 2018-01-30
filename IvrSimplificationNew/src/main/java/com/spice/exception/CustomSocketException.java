package com.spice.exception;

import com.spice.request.*;

import lombok.Data;

@Data
public class CustomSocketException extends Exception{
	private static final long serialVersionUID = 1L;
	private String code;
	private long uniqueId;
	private RequestIvr api;
	public CustomSocketException() {
		super();		
	}
	public CustomSocketException(long uniqueId,RequestIvr api,String code) {
		super();
		//super(message);
		
		this.uniqueId=uniqueId;
        this.api=api;
        this.code=code;
	}
}
