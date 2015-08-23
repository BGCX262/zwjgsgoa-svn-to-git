package com.zwjgsgoa.exception;

public class ZGException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ATTRIBUTE_IS_NULL_OR_EMPTY = "����";
	
	
	public ZGException(String Message){
		super(Message);
	}
}
