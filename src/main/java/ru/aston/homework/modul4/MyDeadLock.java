package ru.aston.homework.modul4;

import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;


class MyDeadLock {
    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();
    private static final Logger logger = Logger.getLogger(MyDeadLock.class.getName());
    private static final String THREAD = "Thread";

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                acquireLocks(1);
            } catch (InterruptedException e) {
                handleInterruptedException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                acquireLocks(2);
            } catch (InterruptedException e) {
                handleInterruptedException(e);
            }
        });

        thread1.start();
        thread2.start();
    }

    private static void acquireLocks(int threadNumber) throws InterruptedException {
        try {
            if (threadNumber == 1) {
                lock1.lock();
                logger.info(THREAD + " " + threadNumber + ": захватил lock1");
                Thread.sleep(100);
                lock2.lock();
                logger.info(THREAD + " " + threadNumber + ": захватил lock2");
            } else {
                lock2.lock();
                logger.info(THREAD + " " + threadNumber + ": захватил lock2");
                Thread.sleep(100);
                lock1.lock();
                logger.info(THREAD + " " + threadNumber + ": захватил lock1");
            }
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
                logger.info(THREAD + " " + threadNumber + ": освободил lock1");
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
                logger.info(THREAD + " " + threadNumber + ": освободил lock2");
            }
        }
    }

    private static void handleInterruptedException(InterruptedException e) {
        Thread.currentThread().interrupt();
        logger.info("Прерывание потока: " + e.getMessage());
    }
}
