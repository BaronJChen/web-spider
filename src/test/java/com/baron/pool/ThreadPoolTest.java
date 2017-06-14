package com.baron.pool;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Baron.Chen on 2017/6/14.
 */
public class ThreadPoolTest {
    private ExecutorService getThreadPool() {
        return ThreadPool.create(10);
    }

    @Test
    public void create() throws Exception {
        ExecutorService executorService = ThreadPool.create(10);
        assertNotNull(executorService);
    }

    @Test(expected = ExecutionException.class)
    public void shutdown() throws Exception {
        ExecutorService executorService = getThreadPool();
        Future<?> task = executorService.submit(() -> {
            throw new RuntimeException();
        });

        Thread.currentThread().yield();
        executorService.shutdown();
        assertEquals(true, executorService.isShutdown());
        Thread.currentThread().yield();

        task.get();
    }

    @Test(expected = ExecutionException.class)
    public void shutdownNow() throws Exception {
        ExecutorService executorService = getThreadPool();
        Future<Void> task = executorService.submit((Callable<Void>) () -> {
            Object o = new Object();
            synchronized (o) {
                o.wait();
            }
            return null;
        });
        Thread.currentThread().yield();
        executorService.shutdownNow();
        task.get();
    }

    @Test
    public void isShutdown() throws Exception {
        ExecutorService executorService = getThreadPool();
        executorService.shutdown();
        assertEquals(true, executorService.isShutdown());
    }

    @Test
    public void isTerminated() throws Exception {
        ExecutorService executorService = getThreadPool();
        executorService.submit((Callable<Void>)() -> {
            Object obj = new Object();
            synchronized (obj) {
                obj.wait();
            }
            return null;
        });
        executorService.shutdownNow();
        assertEquals(true, executorService.isTerminated());
    }

    @Test
    public void awaitTermination() throws Exception {
        final List<Object> list = new CopyOnWriteArrayList<>();
        ExecutorService executorService = getThreadPool();
        executorService.submit(() -> {
            Thread.currentThread().yield();
            list.add(new Object());
        });
        executorService.shutdownNow();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
        assertNotEquals(0, list.size());
    }

    @Test
    public void submit() throws Exception {
        ExecutorService service = getThreadPool();
        Future<Object> future = service.submit((Callable<Object>) () -> {
           return null;
        });
        assertEquals(null, future .get());
    }

    @Test
    public void submit1() throws Exception {
    }

    @Test
    public void submit2() throws Exception {
    }

    @Test
    public void invokeAll() throws Exception {
    }

    @Test
    public void invokeAll1() throws Exception {
    }

    @Test
    public void invokeAny() throws Exception {
    }

    @Test
    public void invokeAny1() throws Exception {
    }

    @Test
    public void execute() throws Exception {
    }

}