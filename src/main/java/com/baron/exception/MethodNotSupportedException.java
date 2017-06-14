package com.baron.exception;

import com.baron.program.AppConstants;
import us.codecraft.webmagic.utils.HttpConstant;

/**
 * Created by Baron.Chen on 2017/6/14.
 */
public class MethodNotSupportedException extends BusinessRuntimeException {
    public MethodNotSupportedException() {
        super(AppConstants.METHOD_NOT_SUPPORTED);
    }
}
