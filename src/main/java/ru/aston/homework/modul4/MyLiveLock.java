package ru.aston.homework.modul4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class MyLiveLock {
    private static final Logger LOGGER = Logger.getLogger(MyLiveLock.class.getName());

    private static final Lock RESOURCE_A = new ReentrantLock();
    private static final Lock RESOURCE_B = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ResourceAcquirer("Поток 1", RESOURCE_A, RESOURCE_B));
        Thread thread2 = new Thread(new ResourceAcquirer("Поток 2", RESOURCE_B, RESOURCE_A));

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
            LOGGER.severe("Основное выполнение прервано: " + e.getMessage());
        }
    }

    static class ResourceAcquirer implements Runnable {
        private final String threadName;
        private final Lock firstResource;
        private final Lock secondResource;

        ResourceAcquirer(String threadName, Lock firstResource, Lock secondResource) {
            this.threadName = threadName;
            this.firstResource = firstResource;
            this.secondResource = secondResource;
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    if (firstResource.tryLock()) {
                        try {
                            LOGGER.info(threadName + " получил ресурс " + getResourceName(firstResource));

                            if (secondResource.tryLock()) {
                                try {
                                    LOGGER.info(threadName + " получил ресурс " + getResourceName(secondResource));
                                    Thread.sleep(1000);
                                } finally {
                                    secondResource.unlock();
                                    LOGGER.info(threadName + " освободил ресурс " + getResourceName(secondResource));
                                }
                            }
                        } finally {
                            firstResource.unlock();
                            LOGGER.info(threadName + " освободил ресурс " + getResourceName(firstResource));
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.severe(threadName + " прерван: " + e.getMessage());
            }
        }

        private String getResourceName(Lock lock) {
            return lock == RESOURCE_A ? "A" : "B";
        }
    }
}
