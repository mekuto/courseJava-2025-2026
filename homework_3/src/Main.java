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
