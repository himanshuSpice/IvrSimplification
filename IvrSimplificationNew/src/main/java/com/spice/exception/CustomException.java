package com.spice.exception;

public class CustomException extends Exception 
{
	
	private static final long serialVersionUID = -7558129084173257948L;
	private String mesage;
	public CustomException(String mesage)
	{
		super(mesage);
		this.mesage=mesage;
		// TODO Auto-generated constructor stub
	}
	
}
