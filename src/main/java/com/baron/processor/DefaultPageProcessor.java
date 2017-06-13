package com.baron.processor;

import com.baron.model.SpiderTask;
import com.baron.model.SpiderTemplate;
import com.baron.program.AppCache;
import com.baron.program.AppConstants;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by Jason on 2017/6/13.
 */
public class DefaultPageProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        SpiderTask spiderTask = AppCache.getThreadLocal(AppConstants.THREAD_LOCAL_CACHE_SPIDER_TASK);
        SpiderTemplate spiderTemplate = AppCache.getThreadLocal(AppConstants.THREAD_LOCAL_CACHE_SPIDER_TEMPLATE);

    }

    @Override
    public Site getSite() {
        return AppCache.getThreadLocal(AppConstants.THREAD_LOCAL_CACHE_SPIDER_THREAD_SITE);
    }
}
