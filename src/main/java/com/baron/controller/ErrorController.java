package com.baron.controller;

import com.alibaba.fastjson.JSON;
import com.baron.model.Exception;
import com.baron.util.ThrowableUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Baron.Chen on 2017/6/8.
 */

/**
 * package all throwable error and exception
 */
@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(value = Throwable.class)
    public void errorHandler(HttpServletResponse response,
                             Throwable e) throws IOException {
        Exception exception = new Exception();
        exception.setMessage(e.getMessage());
        exception.setName(e.getClass().getName());
        exception.setStrackTrace(ThrowableUtil.getStackTrace(e));
        exception.setTime(new Date());

        response.setContentType("application/json");
        response.setStatus(500);
        response.getOutputStream().println(JSON.toJSONString(exception));
    }
}
