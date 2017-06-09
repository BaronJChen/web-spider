package com.baron.program;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Jason on 2017/5/31.
 */
public class AppCache {
    private static Map<String, Object> cache = new ConcurrentHashMap<>();

    public Object get(String key) {
        return cache.get(key);
    }

    public void put(String key, Object value) {
        cache.put(key, value);
    }
}
