package com.baron.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Created by Baron.Chen on 2017/6/15.
 */
@Document(collection = "unit_task")
public class UnitTask {
    private String id;
    private String url;
    private Date startAt;
    private Date finishedAt;
    private Boolean succeeded;
    private Object result;
    private List<Log> logs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Date finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Boolean getSucceeded() {
        return succeeded;
    }

    public void setSucceeded(Boolean succeeded) {
        this.succeeded = succeeded;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }
}