package com.baron.util;

import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jason on 2017/6/10.
 */
public class LogUtilTest {
    @Test
    public void collect() throws Exception {
    }

    @Test
    public void block() throws Exception {
    }

    @Test
    public void block1() throws Exception {
        Logger logger = LogUtil.getLogger(LogUtilTest.class.getName());

        LogUtil.block(LogUtil.LogLevel.DEBUG);
        logger.debug("this is a test");
    }
}