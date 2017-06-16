package com.baron.concurrent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Baron.Chen on 2017/6/16.
 */
public class ThreadLocal extends java.lang.ThreadLocal {
    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();
        Thread thread = new Thread();
        threadLocal.setThreadLocal(thread, "", "");
    }

    public void setThreadLocal(Thread thread, Object key, Object value) {
        try {
            Method getMapMethod = java.lang.ThreadLocal.class.getDeclaredMethod("getMap", Thread.class);
            getMapMethod.setAccessible(true);
            Object map = getMapMethod.invoke(this, thread);
            System.out.println(map);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
