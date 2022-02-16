import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick {
    private Quick() {}

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);

        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
        assert isSorted(a, lo, hi);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];

        while (true) {
            while (less(a[++i], v))
                if (i == hi) break;

            while (less(v, a[--j]))
                if (j == lo) break;

            if (i >= j) break;

            exch(a, i, j);
        }

        exch(a, lo, j);
        return j;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static Comparable select(Comparable[] a, int k) {
        if (k < 0 || k >= a.length) throw new IllegalArgumentException();

        StdRandom.shuffle(a);

        int lo = 0;
        int hi = a.length - 1;

        while (hi > lo) {
            int i = partition(a, lo, hi);

            if (i > k) hi = i - 1;
            else if (i < k) lo = i + 1;
            else return a[i];
        }

        return a[lo];
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static boolean less(Comparable v, Comparable w) {
        if (v == w) return false;
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;

        return true;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void show(Comparable[] a) {
        for (Comparable comparable : a) {
            StdOut.print(comparable);
        };
        StdOut.println("-1-");
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Quick.sort(a);
        show(a);
        assert isSorted(a);

        StdRandom.shuffle(a);

        for (int i = 0; i < a.length; i++) {
            String ith = (String) Quick.select(a, i);
            StdOut.print(ith);
        }
        StdOut.println("-2-");
    }
}
