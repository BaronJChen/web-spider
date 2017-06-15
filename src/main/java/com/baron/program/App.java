package com.baron.program;

import com.baron.filter.Filter;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.Scheduler;

import java.util.List;

/**
 * Created by Baron.Chen on 2017/6/15.
 */
public class App {
    private List<Filter> filters;
    private List<PageProcessor> pageProcessors;
    private List<Pipeline> pipelines;
    private List<Scheduler> schedulers;
    private List<Downloader> downloaders;
}
