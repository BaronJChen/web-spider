package com.baron.model;

import com.baron.filter.Filter;
import org.springframework.data.mongodb.core.mapping.Document;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2017/6/11.
 */
@Document
public class SpiderTemplate {
    private String id;
    private String name;
    private String domain;
    private List<String> seeds;
    private Integer threadCount;
    private Integer retryCount;
    private Integer sleepTime;
    private Long maxPageCount;
    private Integer timeout;
    private List<String> urlRegexes;
    private Map<String, String> cookies;
    private Map<String, String> headers;
    private String pageEncoding;
    private Map<String, MatchExpression> expressions;
    private Map<String, String> tags;
    private List<String> taskCallbacks;
    private List<String> unitCallbacks;
    private List<Proxy> proxies;
    private List<String> filters;
    private List<String> pipelines;
    private String pageProcessor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public List<String> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<String> seeds) {
        this.seeds = seeds;
    }

    public Integer getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(Integer threadCount) {
        this.threadCount = threadCount;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public Integer getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(Integer sleepTime) {
        this.sleepTime = sleepTime;
    }

    public Long getMaxPageCount() {
        return maxPageCount;
    }

    public void setMaxPageCount(Long maxPageCount) {
        this.maxPageCount = maxPageCount;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public List<String> getUrlRegexes() {
        return urlRegexes;
    }

    public void setUrlRegexes(List<String> urlRegexes) {
        this.urlRegexes = urlRegexes;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getPageEncoding() {
        return pageEncoding;
    }

    public void setPageEncoding(String pageEncoding) {
        this.pageEncoding = pageEncoding;
    }

    public Map<String, MatchExpression> getExpressions() {
        return expressions;
    }

    public void setExpressions(Map<String, MatchExpression> expressions) {
        this.expressions = expressions;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public List<String> getTaskCallbacks() {
        return taskCallbacks;
    }

    public void setTaskCallbacks(List<String> taskCallbacks) {
        this.taskCallbacks = taskCallbacks;
    }

    public List<String> getUnitCallbacks() {
        return unitCallbacks;
    }

    public void setUnitCallbacks(List<String> unitCallbacks) {
        this.unitCallbacks = unitCallbacks;
    }

    public List<Proxy> getProxies() {
        return proxies;
    }

    public void setProxies(List<Proxy> proxies) {
        this.proxies = proxies;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

    public List<String> getPipelines() {
        return pipelines;
    }

    public void setPipelines(List<String> pipelines) {
        this.pipelines = pipelines;
    }

    public String getPageProcessor() {
        return pageProcessor;
    }

    public void setPageProcessor(String pageProcessor) {
        this.pageProcessor = pageProcessor;
    }
}
