package com.baron.concurrent;

import com.baron.exception.MethodNotSupportedException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Baron.Chen on 2017/6/14.
 */
public class ThreadPoolTest {
    private ExecutorService getThreadPool() {
        return new ThreadPoolBuilder().threadCount(2).build();
    }

    @Test
    public void create() throws Exception {
        ExecutorService executorService = getThreadPool();
        assertNotNull(executorService);
    }

    @Test(expected = ExecutionException.class)
    public void shutdown() throws Exception {
        ExecutorService executorService = getThreadPool();
        Future<?> task = executorService.submit(() -> {
            throw new RuntimeException();
        });

        Thread.currentThread().sleep(11);
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
        Thread.currentThread().sleep(10);
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
        executorService.awaitTermination(1, TimeUnit.DAYS);
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
        Thread.currentThread().sleep(10);
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
        ExecutorService executorService = getThreadPool();
        Future<Object> future = executorService.submit(() -> {}, 1);
        Thread.currentThread().yield();
        assertEquals(1, future.get());
    }

    @Test
    public void submit2() throws Exception {
        ExecutorService executorService = getThreadPool();
        Future<?> future = executorService.submit((Runnable)() -> {});
        Thread.currentThread().getPriority();
    }

    @Test(expected = MethodNotSupportedException.class)
    public void invokeAll() throws Exception {
        ExecutorService executorService = getThreadPool();
        executorService.invokeAll(null);
    }

    @Test(expected = MethodNotSupportedException.class)
    public void invokeAll1() throws Exception {
        ExecutorService executorService = getThreadPool();
        executorService.invokeAll(null, 0, null);
    }

    @Test(expected = MethodNotSupportedException.class)
    public void invokeAny() throws Exception {
        ExecutorService executorService = getThreadPool();
        executorService.invokeAny(null);
    }

    @Test(expected = MethodNotSupportedException.class)
    public void invokeAny1() throws Exception {
        ExecutorService executorService = getThreadPool();
        executorService.invokeAny(null, 0, null);
    }

    @Test
    public void execute() throws Exception {
        ExecutorService executorService = getThreadPool();
        final List<Object> objects = new ArrayList<>();
        executorService.execute(() -> {
            objects.add(new Object());
        });
        assertEquals(1, objects.size());
    }
}