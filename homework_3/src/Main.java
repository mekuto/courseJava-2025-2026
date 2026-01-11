import etc.Builder;
import etc.ChainOfResponsibility;
import etc.Strategy;
import etc.Proxy;

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
    }
}
