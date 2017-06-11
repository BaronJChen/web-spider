package com.baron.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jason on 2017/6/10.
 */
public class LogUtilTest {
    @Test
    public void collect() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(100);

        List<Long> threadIds = new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            service.submit(() -> {
                synchronized (threadIds) {
                    threadIds.add(Thread.currentThread().getId());
                }

                Logger log = LogUtil.getLogger(new Random().doubles() + "");
                for (int j = 0; j < 100; ++j) {
                    log.debug("test");
                    log.info("test");
                } // for
            });
        } // for

        service.shutdownNow();
        service.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
        System.out.println(LogUtil.collect(threadIds));
    }

    @Test
    public void block() throws Exception {
    }

    @Test
    public void block1() throws Exception {
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream("/log4j.properties"));
        PropertyConfigurator.configure(props);

        Logger logger = LogUtil.getLogger(LogUtilTest.class.getName());
        LogUtil.block(LogUtil.LogLevel.DEBUG);
        logger.debug("this is a test");
    }
}