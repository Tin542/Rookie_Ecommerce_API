package com.tinnt.AssigmentRookie.exception;


public class NotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public NotFoundException (String mess) {
		super(mess);
	}

	public NotFoundException (String mess, Throwable cause){
		super(mess, cause);
	}
}
