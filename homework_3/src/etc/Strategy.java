package etc;

import java.util.Arrays;
import java.util.Random;

interface Sort<T>{
    void run(T in);
}


class ConcreteBubbleSort implements Sort<int[]> {
    private void swap(int[] array, int indexA, int indexB) {
        int temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
    }

    public void run(int[] in) throws NullPointerException  {
        if (in == null) throw new NullPointerException("ConcreteBubbleSort: int[] in == null");
        if (in.length < 2) return;


        for (int i = 0; i < in.length; i++) {
            for (int k = in.length - 1; k > i; k--) {
                int j = k - 1;
                if (in[ k ] < in[ j ]) swap(in, k, j);
            }
        }
    }

}

class ConcreteInsertionSort implements Sort<int[]> {
    public void run(int[] in) throws NullPointerException {
        if (in == null) throw new NullPointerException("ConcreteInsertionSort: int[] in == null");
        if (in.length < 2) return;

        for (int i = 1; i < in.length; i++) {
            int current = in[ i ];
            int j = i;
            while (j > 0 && in[ j - 1 ] > current) {
                in[ j ] = in[ j - 1];
                j--;
            }
            in[ j ] = current;
        }
    }
}

class ConcreteShellSort implements Sort<int[]> {
    public void run(int[] in) throws NullPointerException {
        if (in == null) throw new NullPointerException("ConcreteShellSort: int[] in == null");
        if (in.length < 2) return;

        int[] imperial = {1, 4, 10, 23, 57, 132, 301, 701, 1750};
        int imperialBase = 0;

        while (imperial[imperialBase] < in.length) {
            imperialBase++;
        }

        for (int k = imperialBase - 1; k >= 0; k-- ) {
            int step = imperial[k];
            for (int i = step; i < in.length; i++) {
                for (int j = i - step; j >= 0 && in[ j ] > in[ j + step]; j -= step) {
                    int temp = in[ j ];
                    in[ j ] = in[ j + step];
                    in[ j + step ] = temp;
                }
            }
        }
    }
}

class ConcreteHeapSort implements Sort<int[]> {
    private void sortHelper(int[] in, int indexBorder, int node) {
        int largest = node;
        boolean enableCycle;
        int leftLeaf, rightLeaf;
        int nodeCorrection;

        do {
            nodeCorrection = node << 1;
            leftLeaf = 1 + nodeCorrection;
            rightLeaf = 2 + nodeCorrection;
            if (leftLeaf <= indexBorder && in[ leftLeaf ] > in[ largest ]) largest = leftLeaf;
            if (rightLeaf <= indexBorder && in[ rightLeaf ] > in[ largest ]) largest = rightLeaf;
            enableCycle = largest != node;

            if (enableCycle) {
                int temp = in[ node ];
                in[ node ] = in[ largest ];
                in[ largest ] = temp;
            }
            node = largest;
        } while (enableCycle);
    }

    public void run(int[] in) throws NullPointerException {
        if (in == null) throw new NullPointerException("ConcreteHeapSort: int[] in == null");
        if (in.length < 2) return;

        int middle = in.length / 2;

        for (int i = middle; i >= 0; i--) sortHelper(in, in.length - 1, i);

        for (int i = in.length - 1; i > 0; i--) {
            int temp = in[ 0 ];
            in[ 0 ] = in[ i ];
            in[ i ] = temp;
            sortHelper(in, i - 1, 0);
        }
    }
}


public class Strategy {
    static int[] rnd() {
        final int maxSize = 15;

        Random rndGen = new Random();

        int[] testArray = new int[ maxSize ];
        for (int i = 0; i < maxSize; i++) {
            testArray[ i ] = rndGen.nextInt();
        }
        return testArray;
    }

    static void tester(Sort<int[]> algorithm) {
        int[] testArray = rnd();
        System.out.println("Before sort:\t" + Arrays.toString(testArray));

        try {
            algorithm.run(testArray);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("After sort:\t\t" + Arrays.toString(testArray));
    }

    public static void main(String[] args) {
        tester(new ConcreteBubbleSort());
        tester(new ConcreteInsertionSort());
        tester(new ConcreteShellSort());
        tester(new ConcreteHeapSort());
    }
}