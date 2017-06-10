package com.baron.model;

import com.baron.util.LogUtil;

import java.util.Date;

/**
 * Created by Jason on 2017/6/10.
 */
public class Log {
    private Date date;
    private LogUtil.LogLevel logLevel;
    private Object message;
    private Throwable throwable;

    public Log(Date date, LogUtil.LogLevel logLevel, Object message, Throwable throwable) {
        this.date = date;
        this.logLevel = logLevel;
        this.message = message;
        this.throwable = throwable;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LogUtil.LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogUtil.LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
