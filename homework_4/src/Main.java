/*
 * File: Main.java
 * Path: ./
 * Build: javac ./Main.java
 * Date: 2026-01-16
 * Author: Aleksandr Karpenko Ivanovich
 *
 * Thesis: A common file for classes and demo implementation
 */

import etc.DeadLock;
import etc.LiveLock;
import etc.SwitchesFromOneToTwoAndBack;

public class Main {
    private final static String MODULE_INFO =
        "This module not for production use. Only for studying purpose.";

    static public void main(String[] args){
        System.out.println("=========================================================");
        System.out.println("Start: tho thread print 1 and 2 alternately");
        SwitchesFromOneToTwoAndBack.main(null);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("=========================================================");
        System.out.println("Start: LiveLock demonstration");
        LiveLock.main(null);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("=========================================================");
        System.out.println("Start: DeadLock demonstration.\nFor terminate this demo needed terminate JVM");
        DeadLock.main(null);
    }
}
