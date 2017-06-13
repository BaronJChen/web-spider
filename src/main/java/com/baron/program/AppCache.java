package com.baron.program;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Jason on 2017/5/31.
 */
public class AppCache {
    private static final Map<Object, Object> cache = new ConcurrentHashMap<>();
    private static final ThreadLocal<Map<Object, Object>> threadLocalCache = new ThreadLocal<>();

    public static <T> T get(Object key) {
        return (T) cache.get(key);
    }

    public static void put(Object key, Object value) {
        cache.put(key, value);
    }

    public static void putThreadLocal(Object key, Object value) {
        if (threadLocalCache.get() == null) {
            threadLocalCache.set(new HashMap<>());
        } // if
        threadLocalCache.get().put(key, value);
    }

    public static <T> T getThreadLocal(Object key) {
        if (threadLocalCache.get() == null) {
            return null;
        }
        return (T) threadLocalCache.get().get(key);
    }
}
