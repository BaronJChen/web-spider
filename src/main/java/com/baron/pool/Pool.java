package com.baron.pool;

/**
 * Created by Jason on 2017/6/13.
 */
public interface Pool<T> {
    T get() throws InterruptedException;
    void returnToPool(T t);
    void closeAll();
}
