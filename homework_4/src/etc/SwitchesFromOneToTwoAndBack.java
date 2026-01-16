/*
 * File: SwitchesFromOneToTwoAndBack.java
 * Path: ./etc/
 * Build: javac ./Main.java
 * Date: 2026-01-16
 * Author: Aleksandr Karpenko Ivanovich
 *
 * Thesis: Demo of thread switch
 */

package etc;

public class SwitchesFromOneToTwoAndBack {
    private final static String MODULE_INFO =
        "This module not for production use. Only for studying purpose.";

    public static void main(String[] args) {
        JobThread job_1 = new JobThread("1");
        JobThread job_2 = new JobThread("2");
        ThreadObserver observer = new ThreadObserver(job_1, job_2);

        observer.start();
    }

    static class JobThread extends Thread {
        final String taskString;

        public JobThread(String task) {
            taskString = task;
        }

        public String getTaskString() {
            return taskString;
        }

        @Override
        public void run() {
            do {
                if (Thread.currentThread().isInterrupted()) break;
                System.out.println(taskString);
                Thread.yield();
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                        return;
                    }
            } while (true);
        }
    }
}
