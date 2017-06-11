package com.baron.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.Properties;

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
        Properties props = new Properties();
        props.load(getClass().getResourceAsStream("/log4j.properties"));
        PropertyConfigurator.configure(props);

        Logger logger = LogUtil.getLogger(LogUtilTest.class.getName());
        LogUtil.block(LogUtil.LogLevel.DEBUG);
        logger.debug("this is a test");
    }
}