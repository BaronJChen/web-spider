package com.baron.exception;

/**
 * Created by Baron.Chen on 2017/6/14.
 */
public class BusinessRuntimeException extends RuntimeException {
    public BusinessRuntimeException(String message) {
        super(message);
    }
}
