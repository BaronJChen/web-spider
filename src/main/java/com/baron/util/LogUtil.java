package com.baron.util;

import org.apache.log4j.Priority;
import org.slf4j.spi.LocationAwareLogger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Baron.Chen on 2017/6/9.
 */

/**
 * this is Logger can block thread logs
 */
public class LogUtil {
    private static LogUtil logUtil = new LogUtil();

    private LogUtil() {}

    private static Logger getLogger(String name) {
        return new Logger(name);
    }

    private static class Logger extends org.apache.log4j.Logger {
        private static final List<Integer> threadList = new CopyOnWriteArrayList<>();

        protected Logger(String name) {
            super(name);
        }

        public void blockLog(List<Integer> threadIds) {

        }

        @Override
        public void debug(Object message) {
            super.debug(message);
        }

        /**
         * Delegates to {@link org.slf4j.Logger#debug(String,Throwable)} method in
         * SLF4J.
         */
        public void debug(Object message, Throwable t) {
        }

        /**
         * Delegates to {@link org.slf4j.Logger#info(String)} method in SLF4J.
         */
        public void info(Object message) {
        }

        public void info(Object message, Throwable t) {
        }

        public void warn(Object message) {
        }

        public void warn(Object message, Throwable t) {
        }

        public void error(Object message) {
        }

        public void error(Object message, Throwable t) {
        }

        public void fatal(Object message) {
        }

        /**
         * Delegates to {@link org.slf4j.Logger#error(String,Throwable)} method in
         * SLF4J. In addition, the call is marked with a marker named "FATAL".
         */
        public void fatal(Object message, Throwable t) {
        }

        protected void forcedLog(String FQCN, Priority p, Object msg, Throwable t) {
        }

        // See also http://jira.qos.ch/browse/SLF4J-159
        public void log(String FQCN, Priority p, Object msg, Throwable t) {
        }

        public void log(Priority p, Object message, Throwable t) {
        }

        public void log(Priority p, Object message) {
        }

    }
}
