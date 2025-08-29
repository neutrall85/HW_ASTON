package ru.aston.homework.modul4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class MyLiveLock {
    private static final Logger logger = Logger.getLogger(MyLiveLock.class.getName());

    private static final Lock resourceA = new ReentrantLock();
    private static final Lock resourceB = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    if (resourceA.tryLock()) {
                        try {
                            logger.info("Поток 1 получил ресурс A");

                            if (resourceB.tryLock()) {
                                try {
                                    logger.info("Поток 1 получил ресурс B");
                                    Thread.sleep(1000);
                                } finally {
                                    resourceB.unlock();
                                    logger.info("Поток 1 освободил ресурс B");
                                }
                            }
                        } finally {
                            resourceA.unlock();
                            logger.info("Поток 1 освободил ресурс A");
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.severe("Поток 1 прерван: " + e.getMessage());
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    if (resourceB.tryLock()) {
                        try {
                            logger.info("Поток 2 получил ресурс B");

                            if (resourceA.tryLock()) {
                                try {
                                    logger.info("Поток 2 получил ресурс A");
                                    Thread.sleep(1000);
                                } finally {
                                    resourceA.unlock();
                                    logger.info("Поток 2 освободил ресурс A");
                                }
                            }
                        } finally {
                            resourceB.unlock();
                            logger.info("Поток 2 освободил ресурс B");
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.severe("Поток 2 прерван: " + e.getMessage());
            }
        });

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(50000);
            thread1.interrupt();
            thread2.interrupt();
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.severe("Основное выполнение прервано: " + e.getMessage());
        }
    }
}
