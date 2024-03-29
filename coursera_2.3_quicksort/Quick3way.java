import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick3way {
    private Quick3way() { }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;

        int lt = lo;
        int gt = hi;

        Comparable v = a[lo];
        int i = lo;

        while (i <= gt) {
            int cmp = a[i].compareTo(v);

            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }

        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
        assert isSorted(a, lo, hi);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static boolean less(Comparable v, Comparable w) {
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
        }
        StdOut.println("-1-");
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Quick3way.sort(a);
        show(a);
    }
}
