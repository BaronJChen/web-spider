package com.baron.program;

import com.baron.filter.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.Scheduler;

import java.util.Date;
import java.util.List;

/**
 * Created by Baron.Chen on 2017/6/15.
 */
@Component
public class App {
    private List<String> filters;
    private List<String> pageProcessors;
    private List<String> pipelines;
    private List<String> schedulers;
    private List<String> downloaders;
    private Date startedAt;
}
