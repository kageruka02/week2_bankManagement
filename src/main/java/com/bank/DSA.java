package com.bank;


import java.util.concurrent.locks.ReentrantLock;


public class DSA {

    private static final ReentrantLock lock = new ReentrantLock();
    static int n  = 0;
    private static  boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(DSA::examineVolatile);
        Thread thread1 = new Thread(DSA::examineVolatile);
        Thread thread2 = new Thread(DSA::examineVolatile);
        Thread thread3 = new Thread(DSA::examineVolatile);
        Thread thread4 = new Thread(DSA::examineVolatile);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread.start();
        System.out.println(n);
        thread.join();

    }

    private static void  examineLock(){

        lock.lock();
        lock.lock();
        try{
            n++;
        } finally {
            lock.unlock();
            lock.unlock();
        }


    }

    private static void examineVolatile() {
        while(running){
            System.out.println("hey bro what's going on");
            try{
                Thread.sleep(500);
            }
            catch (InterruptedException e){

            }

        }
        System.out.println(Thread.currentThread().getName() + "finished");
    }

    private static void stopRunning(){
        running = false;
    }
}
