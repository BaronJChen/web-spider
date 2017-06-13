package com.baron.service;

import com.baron.model.SpiderTask;
import com.baron.model.SpiderTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Jason on 2017/6/13.
 */
@Component
public class SpiderTaskService implements BaseService {
    public SpiderTask create(SpiderTemplate spiderTemplate) {
        SpiderTask spiderTask = new SpiderTask();
        ExecutorService service = Executors.newFixedThreadPool(spiderTemplate.getThreadCount());
        return null;
    }
}
