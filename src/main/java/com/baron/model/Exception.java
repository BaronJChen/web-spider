package com.baron.model;

import java.util.Date;

/**
 * Created by Baron.Chen on 2017/6/8.
 */
public class Exception {
    private String name;
    private String message;
    private Date time;
    private String strackTrace;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Exception{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", time=" + time +
                ", strackTrace='" + strackTrace + '\'' +
                '}';
    }

    public String getStrackTrace() {
        return strackTrace;
    }

    public void setStrackTrace(String strackTrace) {
        this.strackTrace = strackTrace;
    }


}
