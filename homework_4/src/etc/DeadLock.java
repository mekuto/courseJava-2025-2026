/*
 * File: DeadLock.java
 * Path: ./etc/
 * Build: javac ./Main.java
 * Date: 2026-01-16
 * Author: Aleksandr Karpenko Ivanovich
 *
 * Thesis: Demo of thread DeadLock
 */

package etc;

public class DeadLock {
    private final static String MODULE_INFO =
        "This module not for production use. Only for studying purpose.";

    public static void main(String[] args) {
        System.out.println("DeadLock thread example");

        DeadLock.Data data1 = new Data();
        DeadLock.Data data2 = new Data();

        TestThread thread1 = new TestThread(data1, data2);
        TestThread thread2 = new TestThread(data2, data1);

        ThreadObserver observer = new ThreadObserver(thread1, thread2);
        observer.start();
    }

    static class Data {
        private static void delay250msWithException() throws InterruptedException {
            final long ms = 250;
            Thread.sleep(ms);
        }

        synchronized void fromOwnMethodCallsAnother(Data neighbour) throws InterruptedException {
            System.out.println("Thread " + Thread.currentThread().getName() + Thread.currentThread().getId() + " -> " +
                            "Enters of fromOwnThreadCallsAnother");

            delay250msWithException();
            neighbour.lockData();

            System.out.println("Thread " + Thread.currentThread().getName() + Thread.currentThread().getId() + " -> " +
                            "Exit of fromOwnThreadCallsAnother");
        }

        synchronized void lockData() throws InterruptedException {
            System.out.println("Thread " + Thread.currentThread().getName() + Thread.currentThread().getId() + " -> " +
                "Enters of lockData");

            delay250msWithException();

            System.out.println("Thread " + Thread.currentThread().getName() + Thread.currentThread().getId() + " -> " +
                "Exit of lockData");
        }
    }

    static class TestThread extends Thread {
        private final DeadLock.Data data1;
        private final DeadLock.Data data2;

        private volatile Thread thisThread;

        public void stopThisThread() {
            thisThread = null;
        }

        public TestThread(DeadLock.Data data1, DeadLock.Data data2) {
            if (data1 == null || data2 == null)
                throw new NullPointerException("TestThread(null || null)");
            this.data1 = data1;
            this.data2 = data2;
        }

        @Override
        public void start() {
            thisThread = new Thread(this);
            thisThread.setName("OurThread");
            thisThread.start();
        }

        @Override
        public void run() {
            Thread certainThread = Thread.currentThread();
            while (thisThread == certainThread && !Thread.currentThread().isInterrupted()) {
                try {
                    data1.fromOwnMethodCallsAnother(data2);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    stopThisThread();
                    return;
                }
            }
        }
    }
}