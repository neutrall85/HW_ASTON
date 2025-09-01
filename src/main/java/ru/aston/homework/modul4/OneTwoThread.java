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
        Thread thread1 = new Thread(new NumberPrinter(semaphore1, semaphore2, "1"));
        Thread thread2 = new Thread(new NumberPrinter(semaphore2, semaphore1, "2"));

        thread1.start();
        thread2.start();
    }

    // Отдельный класс для повторяющейся логики
    private static class NumberPrinter implements Runnable {
        private final Semaphore acquireSemaphore;
        private final Semaphore releaseSemaphore;
        private final String number;

        public NumberPrinter(Semaphore acquireSemaphore, Semaphore releaseSemaphore, String number) {
            this.acquireSemaphore = acquireSemaphore;
            this.releaseSemaphore = releaseSemaphore;
            this.number = number;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    acquireSemaphore.acquire();
                    logger.log(Level.INFO, number);
                    TimeUnit.MILLISECONDS.sleep(DELAY);
                    releaseSemaphore.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.log(Level.SEVERE, "Поток прерван", e);
            }
        }
    }
}
