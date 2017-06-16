package com.baron.concurrent;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class WebDriverPool {
	private Logger logger = Logger.getLogger(getClass());
	private final static int DEFAULT_CAPACITY = 5;
	private final int capacity;
	private final static int STAT_RUNNING = 1;
	private final static int STAT_CLODED = 2;
	private AtomicInteger stat = new AtomicInteger(STAT_RUNNING);
	private WebDriver mDriver = null;
	private boolean mAutoQuitDriver = true;
	protected static Properties config;
	protected static DesiredCapabilities capabilities;
	private List<WebDriver> webDriverList = Collections.synchronizedList(new ArrayList<WebDriver>());
	private BlockingDeque<WebDriver> innerQueue = new LinkedBlockingDeque<WebDriver>();

    public WebDriverPool(int capacity) {
        this.capacity = capacity;
    }

    public WebDriverPool() {
        this(DEFAULT_CAPACITY);
    }

	private void configure() throws IOException {
        List<String> argsList = new ArrayList<>();
        argsList.add("--web-security=false");
        argsList.add("--ssl-protocol=any");
        argsList.add("--ignore-ssl-errors=true");

		capabilities = new DesiredCapabilities();
		capabilities.setJavascriptEnabled(true);
		capabilities.setCapability("takesScreenshot", false);
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, argsList);
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY
                , "phantomjs/phantomjs-windows64");
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS
                , new String[] { "--logLevel= NONE"});

        mDriver = new PhantomJSDriver(capabilities);
	}

	private boolean isUrl(String urlString) {
		try {
			new URL(urlString);
			return true;
		} catch (MalformedURLException mue) {
			return false;
		}
	}

	public WebDriver get() throws InterruptedException {
		checkRunning();
		WebDriver poll = innerQueue.poll();
		if (poll != null) {
			return poll;
		}
		if (webDriverList.size() < capacity) {
			synchronized (webDriverList) {
				if (webDriverList.size() < capacity) {
					try {
						configure();
						innerQueue.add(mDriver);
						webDriverList.add(mDriver);
					} catch (IOException e) {
					    new RuntimeException(e);
					}
				}
			}
		}
		return innerQueue.take();
	}

	public void returnToPool(WebDriver webDriver) {
		checkRunning();
		innerQueue.add(webDriver);
	}

	protected void checkRunning() {
		if (!stat.compareAndSet(STAT_RUNNING, STAT_RUNNING)) {
			throw new IllegalStateException("Already closed!");
		}
	}

	public void closeAll() {
		boolean b = stat.compareAndSet(STAT_RUNNING, STAT_CLODED);
		if (!b) {
			throw new IllegalStateException("Already closed!");
		}
		for (WebDriver webDriver : webDriverList) {
			logger.info("Quit webDriver" + webDriver);
			webDriver.quit();
			webDriver = null;
		}
	}
}
