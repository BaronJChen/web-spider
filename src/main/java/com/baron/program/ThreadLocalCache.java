package com.baron.program;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Baron.Chen on 2017/6/9.
 */
public class ThreadLocalCache {
    private static final ThreadLocal<Map<String, Object>> cache = new ThreadLocal<>();

    public Object get(String key) {
        if (cache.get() == null) {
            return null;
        } // if

        return cache.get().get(key);
    }

    public void put(String key, Object value) {
        if (cache.get() == null) {
            cache.set(new HashMap<>());
        } // if

        cache.get().put(key, value);
    }
}
