package ru.aston.homework.modul4;

import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

class MyDeadLock {
    private static final ReentrantLock LOCK_1 = new ReentrantLock();
    private static final ReentrantLock LOCK_2 = new ReentrantLock();
    private static final Logger LOGGER = Logger.getLogger(MyDeadLock.class.getName());
    private static final String THREAD = "Thread";

    public static void main(String[] args) {
        Thread thread1 = new Thread(new LockAcquirer(1));
        Thread thread2 = new Thread(new LockAcquirer(2));

        thread1.start();
        thread2.start();
    }

    private static class LockAcquirer implements Runnable {
        private final int threadNumber;

        LockAcquirer(int threadNumber) {
            this.threadNumber = threadNumber;
        }

        @Override
        public void run() {
            try {
                acquireLocks(threadNumber);
            } catch (InterruptedException e) {
                handleInterruptedException(e);
            }
        }

        private static void acquireLocks(int threadNumber) throws InterruptedException {
            try {
                if (threadNumber == 1) {
                    LOCK_1.lock();
                    LOGGER.info(THREAD + " " + threadNumber + ": захватил lock1");
                    Thread.sleep(100);
                    LOCK_2.lock();
                    LOGGER.info(THREAD + " " + threadNumber + ": захватил lock2");
                } else {
                    LOCK_2.lock();
                    LOGGER.info(THREAD + " " + threadNumber + ": захватил lock2");
                    Thread.sleep(100);
                    LOCK_1.lock();
                    LOGGER.info(THREAD + " " + threadNumber + ": захватил lock1");
                }
            } finally {
                if (LOCK_1.isHeldByCurrentThread()) {
                    LOCK_1.unlock();
                    LOGGER.info(THREAD + " " + threadNumber + ": освободил lock1");
                }
                if (LOCK_2.isHeldByCurrentThread()) {
                    LOCK_2.unlock();
                    LOGGER.info(THREAD + " " + threadNumber + ": освободил lock2");
                }
            }
        }

        private static void handleInterruptedException(InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.info("Прерывание потока: " + e.getMessage());
        }
    }
}
