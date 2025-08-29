package ru.aston.homework.modul4;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.logging.Level;

public class OneTwoThread {
    private static final Logger logger = Logger.getLogger(OneTwoThread.class.getName());
    private static final Semaphore semaphore1 = new Semaphore(1);
    private static final Semaphore semaphore2 = new Semaphore(0);

    private static final long DELAY = 1000;

    public static void main(String args[]) {
        Thread thread1 = new Thread(() -> {
            try {
                while (true) {
                    semaphore1.acquire();
                    logger.log(Level.INFO, "1");
                    TimeUnit.MILLISECONDS.sleep(DELAY);
                    semaphore2.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.log(Level.SEVERE, "Поток 1 прерван", e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                while (true) {
                    semaphore2.acquire();
                    logger.log(Level.INFO, "2");
                    TimeUnit.MILLISECONDS.sleep(DELAY);
                    semaphore1.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.log(Level.SEVERE, "Поток 2 прерван", e);
            }
        });

        thread1.start();
        thread2.start();

    }
}
