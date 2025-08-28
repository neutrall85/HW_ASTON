package ru.aston.homework.modul4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLiveLock {
    private static final Lock resourceA = new ReentrantLock();
    private static final Lock resourceB = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                while (true) {
                    if (resourceA.tryLock()) {
                        System.out.println("Thread 1: захватил ресурс A");

                        if (resourceB.tryLock()) {
                            System.out.println("Thread 1: захватил ресурс B");
                            break;
                        } else {
                            System.out.println("Thread 1: не смог захватить B, освобождаю A");
                            resourceA.unlock();
                            Thread.sleep(100);
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Прерван Thread 1");
            } finally {
                resourceA.unlock();
                resourceB.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                while (true) {
                    if (resourceB.tryLock()) {
                        System.out.println("Thread 2: захватил ресурс B");

                        if (resourceA.tryLock()) {
                            System.out.println("Thread 2: захватил ресурс A");
                            break;
                        } else {
                            System.out.println("Thread 2: не смог захватить A, освобождаю B");
                            resourceB.unlock();
                            Thread.sleep(100);
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Прерван Thread 2");
            } finally {
                resourceA.unlock();
                resourceB.unlock();
            }
        });

        thread1.start();
        thread2.start();
    }
}
