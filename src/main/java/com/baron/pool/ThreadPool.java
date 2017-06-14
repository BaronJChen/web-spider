package com.baron.pool;

import com.baron.exception.MethodNotSupportedException;
import com.baron.util.ListUtil;
import com.baron.util.LogUtil;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jason on 2017/6/13.
 */
/**
 * We need threadIds, but default ThreadPoolExecutor can't meet the demand
 * so we override it
*/
public class ThreadPool implements ExecutorService {
    private static Logger logger = LogUtil.getLogger(ThreadPool.class);
    private BlockingQueue<Runnable> taskQueue;
    private Worker[] workers;
    private boolean isShutdown;
    private Lock lock;
    private Condition condition;
    private AtomicInteger threadCount;

    private ThreadPool(Integer threadCount) {
        init(threadCount);
    }

    protected void init(Integer threadCount) {
        this.threadCount = new AtomicInteger(threadCount);
        taskQueue = new LinkedBlockingDeque<>();
        workers = new Worker[threadCount];
        lock = new ReentrantLock();

        for (int i = 0; i < threadCount; ++i) {
            workers[i] = new Worker(taskQueue, this.threadCount, condition);
            workers[i].start();
        } // for
    }

    public ExecutorService create(int threadCount) {
        return new ThreadPool(threadCount);
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        } // for
    }

    @Override
    public List<Runnable> shutdownNow() {
        for (Worker worker : workers) {
            worker.interrupt();
            worker.shutdown();
        } // for

        return ListUtil.from(taskQueue);
    }

    @Override
    public boolean isShutdown() {
        return isShutdown;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        condition.wait(unit.toMicros(timeout));
        return true;
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        FutureTask<T> futureTask = new FutureTask<T>(task);
        taskQueue.add(futureTask);
        return futureTask;
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        FutureTask<T> futureTask = new FutureTask<T>(task, result);
        taskQueue.add(futureTask);
        return futureTask;
    }

    @Override
    public Future<?> submit(Runnable task) {
        FutureTask futureTask = new FutureTask(task, null);
        taskQueue.add(futureTask);
        return futureTask;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    @Override
    public void execute(Runnable command) {

    }

    private static class Worker extends Thread {
        private BlockingQueue<Runnable> taskQueue;
        private boolean isShutdown;
        private AtomicInteger threadCount;
        private Condition condition;

        public Worker(BlockingQueue<Runnable> taskQueue, AtomicInteger threadCount, Condition condition) {
            this.taskQueue = taskQueue;
            isShutdown = false;
            this.threadCount = threadCount;
            this.condition = condition;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted() && !isShutdown) {
                try {
                    Runnable task = taskQueue.poll();
                    task.run();
                } catch (Throwable e) {
                    logger.error(e);
                } // catch
            } // while

            afterShutdown();
        }

        protected void afterShutdown() {
            threadCount.addAndGet(-1);
            condition.signal();
        }

        public void shutdown() {
            isShutdown = true;
        }
    }
}
