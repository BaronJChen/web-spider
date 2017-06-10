package com.baron.program;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Jason on 2017/5/31.
 */
public class Cache {
    private static final Map<Object, Object> cache = new ConcurrentHashMap<>();

    public static Object get(Object key) {
        return cache.get(key);
    }

    public static void put(Object key, Object value) {
        cache.put(key, value);
    }
}
