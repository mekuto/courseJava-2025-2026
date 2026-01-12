/*
 * File: Main.java
 * Path: ./
 * Build: javac ./Main.java
 * Date: 2026-01-12
 * Author: Aleksandr Karpenko Ivanovich
 *
 * Thesis: Main.java
 */

import etc.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("==============================================================");
        System.out.println("Demo: template Strategy");
        Strategy.main(args);
        System.out.println("==============================================================");
        System.out.println("Demo: template Chain of Responsibility");
        ChainOfResponsibility.main(args);
        System.out.println("==============================================================");
        System.out.println("Demo: template Builder");
        Builder.main(args);
        System.out.println("==============================================================");
        System.out.println("Demo: template Proxy");
        Proxy.main(args);
        System.out.println("==============================================================");
        System.out.println("Demo: template Decorator");
        Decorator.main(args);
        System.out.println("==============================================================");
        System.out.println("Demo: template Adapter");
        Adapter.main(args);
    }
}
