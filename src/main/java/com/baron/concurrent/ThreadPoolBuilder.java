package com.baron.concurrent;

import com.baron.exception.MethodNotSupportedException;
import com.baron.program.AppConstants;
import com.baron.util.ListUtil;
import com.google.common.base.Preconditions;
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
public class ThreadPoolBuilder {
    private Integer threadCount;

    public ThreadPoolBuilder threadCount(int threadCount) {
        this.threadCount = threadCount;
        return this;
    }

    public ThreadPool build() {
        return new ThreadPool(threadCount);
    }

    private static class ThreadPool implements ExecutorService {
        private static Logger logger = Logger.getLogger(ThreadPoolBuilder.class);
        private BlockingQueue<Runnable> taskQueue;
        private Worker[] workers;
        private boolean isShutdown;
        private Lock lock;
        private Condition condition;
        private AtomicInteger threadCount;

        ThreadPool(Integer threadCount) {
            init(threadCount);
        }

        protected void init(Integer threadCount) {
            Preconditions.checkArgument(threadCount > 0, AppConstants.THREAD_COUNT_CAN_NOT_LESS_THAN_ZERO);
            this.threadCount = new AtomicInteger(threadCount);
            taskQueue = new LinkedBlockingDeque<>();
            workers = new Worker[threadCount];
            lock = new ReentrantLock();
            condition = lock.newCondition();

            for (int i = 0; i < threadCount; ++i) {
                workers[i] = new Worker(taskQueue, this.threadCount, lock, condition);
                workers[i].start();
            } // for

            waitWorkers(threadCount);
        }

        protected void waitWorkers(int threadCount) {
            boolean flag = false;

            while (!flag) {
                lock.lock();
                try {
                    condition.await();
                    flag = true;
                } catch (InterruptedException e) {
                    logger.error(e);
                } finally {
                    lock.unlock();
                }
            } // while
            this.threadCount.set(threadCount);
        }

        @Override
        public void shutdown() {
            for (Worker worker : workers) {
                worker.shutdown();
            } // for
            isShutdown = true;
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
            return threadCount.get() == 0;
        }

        @Override
        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            if (threadCount.get() == 0) {
                return true;
            } // if

            try {
                lock.lock();
                condition.await(timeout, unit);
            } finally {
                lock.unlock();
            } // finally

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
            throw new MethodNotSupportedException();
        }

        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws
                InterruptedException {
            throw new MethodNotSupportedException();
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
            throw new MethodNotSupportedException();
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws
                InterruptedException, ExecutionException, TimeoutException {
            throw new MethodNotSupportedException();
        }

        @Override
        public void execute(Runnable command) {
            command.run();
        }

    }

    private static class Worker extends Thread {
        private BlockingQueue<Runnable> taskQueue;
        private boolean isShutdown;
        private AtomicInteger threadCount;
        private Condition condition;
        private Lock lock;

        public Worker(BlockingQueue<Runnable> taskQueue, AtomicInteger threadCount, Lock lock, Condition condition) {
            this.taskQueue = taskQueue;
            isShutdown = false;
            this.threadCount = threadCount;
            this.condition = condition;
            this.lock = lock;
        }

        protected void afterStart() {
            if (threadCount.addAndGet(-1) == 0) {
                lock.lock();
                condition.signal();
                lock.unlock();
            } // if
        }

        @Override
        public void run() {
            afterStart();

            while (!Thread.currentThread().isInterrupted() && !isShutdown) {
                try {
                    Runnable task = taskQueue.poll(Integer.MAX_VALUE, TimeUnit.DAYS);
                    task.run();
                } catch (InterruptedException e) {
                    break;
                } catch (Throwable t) {
                    // make sure worker not fail
                    continue;
                } // catch
            } // while

            afterShutdown();
        }

        protected void afterShutdown() {
            if (threadCount.addAndGet(-1) == 0) {
                lock.lock();
                condition.signal();
                lock.unlock();
            } // if
        }

        public void shutdown() {
            isShutdown = true;
        }
    }
}
