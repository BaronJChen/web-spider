package com.baron.model;

import com.baron.filter.Filter;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2017/6/11.
 */
public class SpiderTemplate {
    private String id;
    private String name;
    private String domain;
    private List<String> seeds;
    private Integer threadNum;
    private Integer retryCount;
    private Integer sleepTime;
    private Long maxPageCount;
    private Integer timeout;
    private List<String> urlRegexes;
    private Map<String, String> cookies;
    private Map<String, String> headers;
    private String pageEncoding;
    private List<String> taskCallbacks;
    private List<String> unitCallbacks;
    private List<Proxy> proxies;
    private List<Filter> filters;
    private List<Pipeline> pipelines;
    private List<PageProcessor> pageProcessors;
}
