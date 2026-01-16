/*
 * File: ThreadObserver.java
 * Path: ./etc/
 * Build: javac ./Main.java
 * Date: 2026-01-16
 * Author: Aleksandr Karpenko Ivanovich
 *
 * Thesis: Auxiliary class of the stream observer
 */

package etc;

import java.util.HashSet;
import java.util.Set;

class ThreadObserver extends Thread {
    private final static String MODULE_INFO =
        "This module not for production use. Only for studying purpose.";

    private Thread thread1, thread2;
    private long delay = 250;

    public ThreadObserver(Thread th1, Thread th2) {
        if (th1 == null || th2 == null)
            throw new NullPointerException("ThreadObserved -> ThreadObserver(null || null)");
        this.thread1 = th1;
        this.thread2 = th2;

        thread1.setName("OurThread");
        thread2.setName("OurThread");
        System.out.println(thread1.getName() + " -> " + thread1);
        System.out.println(thread2.getName() + " -> " + thread2);
    }

    public void setDelay(final long delay) {
        this.delay = delay;
    }

    @Override
    public void run() {
        Set<Thread> keyOfAllThreadBeforeLaunch;
        Set<Thread> keyOfAllThreadAfterLaunch;
        Set<Thread> keyOfUniqueThreads;

        System.out.println("-> Starting Observer");
        keyOfAllThreadBeforeLaunch = Thread.getAllStackTraces().keySet();

        thread1.start();
        thread2.start();

        keyOfAllThreadAfterLaunch = Thread.getAllStackTraces().keySet();

        keyOfUniqueThreads = new HashSet<>(keyOfAllThreadAfterLaunch);
        keyOfUniqueThreads.removeAll(keyOfAllThreadBeforeLaunch);

        if (keyOfUniqueThreads.isEmpty()) return;

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.printf("Dbg: after wait %d ms\n", delay);

        for (Thread key: keyOfUniqueThreads) {
            if (key.getName().contains("OurThread")) {
                key.interrupt();
                System.out.println("Interrupt -> " + key);
            }
        }

        keyOfUniqueThreads.clear();
        thread1 = null;
        thread2 = null;

        try {
            Thread.currentThread().join(200);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}