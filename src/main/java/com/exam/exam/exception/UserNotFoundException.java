package com.exam.exam.exception;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String message){
        super(message);
    }
}
