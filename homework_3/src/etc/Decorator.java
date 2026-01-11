package etc;

interface BasicInterface {
    int op(int a, int b);
}

class AnyClass implements BasicInterface {
    public int op(int a, int b) {
        int result = a + b;
        System.out.println("AnyClass: " + "a=" + a + "; b=" + b + "; result=" + result);
        return result;
    }
}

abstract class DecoratorAbstract implements BasicInterface {

    protected BasicInterface vault;

    public DecoratorAbstract(BasicInterface any) throws NullPointerException {
        if (any == null) throw new NullPointerException("BestDecorationOfAnyClass(null)");
        vault = any;
    }

    @Override
    public int op(int a, int b) {
        return vault.op(a, b);
    }

    abstract public void other();
}

/*class DecoratorSum extends DecoratorAbstract {
    public DecoratorSum(BasicInterface o) {
        super(o);
    }

    public void other() {
        System.out.println("DecoratorSum");
    }
}*/

class DecoratorSub extends DecoratorAbstract {
    public DecoratorSub(BasicInterface o) {
        super(o);
    }

    @Override
    public int op(int a, int b) {
        int c = -b;
        int result = a + c;

        other();
        System.out.println("a=" + a + "; b=" + b + "; result=" + result);

        return super.op(a, b);
    }

    public void other() {
        System.out.print("DecoratorSub: ");
    }
}

class DecoratorPower extends DecoratorAbstract {
    public DecoratorPower(BasicInterface o) {
        super(o);
    }

    @Override
    public int op(int a, int b) {
        if (b == 0) return 1;
        if (b == 1) return a;
        int result = a;
        if (b > 1 && b < 15)
            for (int i = 1; i < b; i++) result *= a;

        other();
        System.out.println("a=" + a + "; b=" + b + "; result=" + result);

        return super.op(a, b);
    }

    public void other() {
        System.out.print("DecoratorPower: ");
    }
}

class DecoratorPower2 extends DecoratorAbstract {
    public DecoratorPower2(BasicInterface o) {
        super(o);
    }

    @Override
    public int op(int a, int b) {
        if (b == 0) return a;
        int result = a;
        if (b > 0 && b < 15)
            result = a << b;

        other();
        System.out.println("a=" + a + "; b=" + b + "; result=" + result);

        return super.op(a, b);
    }

    public void other() {
        System.out.print("DecoratorPower2: ");
    }
}

public class Decorator {
    public static void main(String[] args) {
        AnyClass b = new AnyClass();
//        DecoratorAbstract a = new DecoratorPower(b);
        DecoratorAbstract demo = new DecoratorPower(new DecoratorPower2(new DecoratorSub(new AnyClass())));
        int result = demo.op(1,3);
        System.out.println("result: " + result);
    }
}
