package etc;

import java.lang.NullPointerException;
import java.util.Arrays;
import java.util.Random;

abstract class  Handler {
    private Handler Successor;

    public void setSuccessor(Handler set) {
        if (set == this) this.Successor = null;
        else this.Successor = set;
    }

    public Handler getSuccessor() {
        return this.Successor;
    }

    public abstract void payload(int[] in);
}

class ConcretePrint extends Handler {
    @Override
    public void payload(int[] in) throws NullPointerException {
        if (in == null) throw new NullPointerException("ConcretePrintString: String in == null");
        System.out.println(Arrays.toString(in));

        if (this.getSuccessor() == null) return;
        else this.getSuccessor().payload(in);
    }
}

class ConcreteAdd extends Handler{
    @Override
    public void payload(int[] in) throws NullPointerException{
        if (in == null) throw new NullPointerException("ConcreteAdd: int[] in == null");
        int total = 0;
        for (int a : in) {
            total += a;
        }
        in[0] = total;

        if (this.getSuccessor() == null) return;
        else this.getSuccessor().payload(in);
    }
}

class ConcreteSub extends Handler {
    @Override
    public void payload(int[] in) throws NullPointerException {
        if (in == null) throw new NullPointerException("ConcreteSub: int[] in == null");
        int total = 0;
        for (int a : in) {
            total -= a;
        }
        in[0] = total;

        if (this.getSuccessor() == null) return;
        else this.getSuccessor().payload(in);
    }
}

class ConcreteNewArray extends Handler {
    @Override
    public void payload(int[] in) throws NullPointerException{
        if (in == null) throw new NullPointerException("ConcreteNewArray: int[] in == null");
        if (in.length < 1) {
            in = null;
            return;
        }
        int size = in[0];
        in = new int[ size ];

        if (this.getSuccessor() == null) return;
        else this.getSuccessor().payload(in);
    }
}

class ConcreteFillArray extends Handler {
    @Override
    public void payload(int[] in) throws NullPointerException{
        if (in == null) throw new NullPointerException("ConcreteFillArray: int[] in == null");
        Random rndGen = new Random();
        for (int a = 0; a < in.length; a++) in[ a ] = rndGen.nextInt();

        if (this.getSuccessor() == null) return;
        else this.getSuccessor().payload(in);
    }
}

class ConcreteTruncateArray extends Handler {
    @Override
    public void payload(int[] in) throws NullPointerException{
        if (in == null) throw new NullPointerException("ConcreteTruncateArray: int[] in == null");
        if (in.length < 2) return;
        int[] newArray = new int[ 1 ];
        System.arraycopy(in, 0, newArray, 0, 1 );
        in = newArray;

        if (this.getSuccessor() == null) return;
        else this.getSuccessor().payload(in);
    }
}


public class ChainOfResponsibility {
    public static void main(String[] args) {
        Handler chainNew = new ConcreteNewArray();
        Handler chainFill = new ConcreteFillArray();
        Handler chainPrint1 = new ConcretePrint();
        Handler chainAdd = new ConcreteAdd();
        Handler chainTruncate = new ConcreteTruncateArray();
        Handler chainPrint2 = new ConcretePrint();

        chainNew.setSuccessor(chainFill);
        chainFill.setSuccessor(chainPrint1);
        chainPrint1.setSuccessor(chainAdd);
        chainAdd.setSuccessor(chainTruncate);
        chainTruncate.setSuccessor(chainPrint2);

        int[] test = {5};

        chainNew.payload( test );
    }
}
