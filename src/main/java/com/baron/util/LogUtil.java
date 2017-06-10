package com.baron.util;

import com.baron.model.Log;
import com.baron.program.Cache;
import org.apache.log4j.Priority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Baron.Chen on 2017/6/9.
 */

/**
 * this is Logger can block thread logs
 */
public class LogUtil {
    private static final LogUtil logUtil = new LogUtil();
    private static final Map<Long, LogLevel> levelMap = new ConcurrentHashMap<>();

    private LogUtil() {
    }

    public static org.apache.log4j.Logger getLogger(String name) {
        return new Logger(name);
    }

    public static List<List<Log>> collect(List<Long> threadIds) {
        List<List<Log>> logsList = new ArrayList<>();

        for (Long threadId : threadIds) {
            logsList.add((List<Log>) Cache.get(threadId));
        } // for

        return logsList;
    }

    private static List<Log> collect(Long threadId) {
        return (List<Log>)Cache.get(threadId);
    }

    private static List<Log> collect() {
        return (List<Log>)Cache.get(Thread.currentThread().getId());
    }

    /**
     * block the log whose level is < lowestLevel
     */
    public static void block(List<Long> threadIds, LogLevel lowestLevel) {
        for (Long threadId : threadIds) {
            levelMap.put(threadId, lowestLevel);
        }
    }

    public static void block(Long threadId, LogLevel lowestLevel) {
        levelMap.put(threadId, lowestLevel);
    }

    public static void block(LogLevel level) {
        levelMap.put(Thread.currentThread().getId(), level);
    }

    private static class Logger extends org.apache.log4j.Logger {
        protected Logger(String name) {
            super(name);
        }

        private boolean checkAccessibility(LogLevel currentLevel) {
            LogLevel allowedMinimumLevel = levelMap.get(Thread.currentThread().getId());

            if (currentLevel.compareTo(allowedMinimumLevel) >= 0) {
                return true;
            } else {
                return false;
            } // else
        }

        private void cacheLog(Log log) {
            Long id = Thread.currentThread().getId();
            List<Log> logs = (List<Log>)Cache.get(id);

            if (logs == null) {
                Cache.put(id, logs = new ArrayList<>());
            } // if

            logs.add(log);
        }

        @Override
        public void trace(Object message) {
            if (!checkAccessibility(LogLevel.TRACE)) {
                return;
            } // if

            cacheLog(new Log(new Date(), LogLevel.TRACE, message, null));
            super.trace(message);
        }

        @Override
        public void trace(Object message, Throwable t) {
            if (!checkAccessibility(LogLevel.TRACE)) {
                return;
            } // if

            cacheLog(new Log(new Date(), LogLevel.TRACE, message, t));
            super.trace(message);
        }

        @Override
        public void debug(Object message) {
            if (!checkAccessibility(LogLevel.DEBUG)) {
                return;
            } // if

            cacheLog(new Log(new Date(), LogLevel.DEBUG, message, null));
            super.debug(message);
        }

        @Override
        public void debug(Object message, Throwable t) {
            if (!checkAccessibility(LogLevel.DEBUG)) {
                return;
            } // if

            cacheLog(new Log(new Date(), LogLevel.DEBUG, message, t));
            super.debug(message, t);
        }

        @Override
        public void info(Object message) {
            if (!checkAccessibility(LogLevel.INFO)) {
                return;
            } // if

            cacheLog(new Log(new Date(), LogLevel.INFO, message, null));
            super.info(message);
        }

        @Override
        public void info(Object message, Throwable t) {
            if (!checkAccessibility(LogLevel.INFO)) {
                return;
            } // if

            cacheLog(new Log(new Date(), LogLevel.INFO, message, t));
            super.info(message, t);
        }

        @Override
        public void warn(Object message) {
            if (!checkAccessibility(LogLevel.WARN)) {
                return;
            } // if

            cacheLog(new Log(new Date(), LogLevel.WARN, message, null));
            super.warn(message);
        }

        @Override
        public void warn(Object message, Throwable t) {
            if (!checkAccessibility(LogLevel.WARN)) {
                return;
            } // if

            cacheLog(new Log(new Date(), LogLevel.WARN, message, t));
            super.warn(message);
        }

        @Override
        public void error(Object message) {
            if (!checkAccessibility(LogLevel.ERROR)) {
                return;
            } // if

            cacheLog(new Log(new Date(), LogLevel.ERROR, message, null));
            super.error(message);
        }

        @Override
        public void error(Object message, Throwable t) {
            if (!checkAccessibility(LogLevel.ERROR)) {
                return;
            } // if

            cacheLog(new Log(new Date(), LogLevel.ERROR, message, t));
            super.error(message, t);
        }

        @Override
        public void fatal(Object message) {
            if (!checkAccessibility(LogLevel.FATAL)) {
                return;
            } // if

            cacheLog(new Log(new Date(), LogLevel.FATAL, message, null));
            super.fatal(message);
        }

        @Override
        public void fatal(Object message, Throwable t) {
            if (!checkAccessibility(LogLevel.FATAL)) {
                return;
            } // if

            cacheLog(new Log(new Date(), LogLevel.FATAL, message, t));
            super.fatal(message, t);
        }

        @Override
        public void log(String FQCN, Priority p, Object msg, Throwable t) {
            super.log(FQCN, p, msg, t);
        }

        @Override
        public void log(Priority p, Object message, Throwable t) {
            super.log(p, message, t);
        }

        @Override
        public void log(Priority p, Object message) {
            super.log(p, message);
        }
    }

    public static class LogLevel implements Comparable<LogLevel> {
        public static final LogLevel TRACE = new LogLevel(-1);
        public static final LogLevel DEBUG = new LogLevel(0);
        public static final LogLevel INFO = new LogLevel(1);
        public static final LogLevel WARN = new LogLevel(2);
        public static final LogLevel ERROR = new LogLevel(3);
        public static final LogLevel FATAL = new LogLevel(4);
        public static final LogLevel ALL = new LogLevel(5);
        private int level;

        private LogLevel(int level) {
            this.level = level;
        }

        @Override
        public int compareTo(LogLevel o) {
            return this.level - o.level;
        }

        @Override
        public String toString() {
            String str;
            switch(level) {
                case -1:
                    str = "TRACE";
                    break;
                case 0:
                    str = "DEBUG";
                    break;
                case 1:
                    str = "INFO";
                    break;
                case 2:
                    str = "WARN";
                    break;
                case 3:
                    str = "ERROR";
                    break;
                case 4:
                    str = "FATAL";
                    break;
                default:
                    str = "unknown";
                    break;
            } // switch

            return str;
        }
    }
}
