package com.baron.processor;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by Jason on 2017/6/15.
 */
@Component
@Scope("prototype")
public class DefaultPageProcessor implements PageProcessor {
    private String domain;
    private Site site;

    public DefaultPageProcessor(String domain) {
        this.domain = domain;
        this.site = Site.me()
                .setDomain(domain);
    }
    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().all());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
