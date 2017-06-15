package com.baron.downloader;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

/**
 * Created by Baron.Chen on 2017/6/15.
 */
@Component
@Scope("singleton")
public class DefaultDownloader extends HttpClientDownloader {
}
