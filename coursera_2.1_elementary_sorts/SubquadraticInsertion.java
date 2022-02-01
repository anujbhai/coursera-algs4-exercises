/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SubquadraticInsertion {
    private SubquadraticInsertion() {
    }

    public static void sort(Comparable[] c) {
        int n = c.length;

        int h = 1;

        while (h < n / 3) h = 3 * h + 1;

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(c[j], c[j - h]); j -= h) {
                    exch(c, j, j - h);

                    for (int k = 0; k < n; k++) {
                        StdOut.print(c[k] + " ");
                    }

                    StdOut.println();
                }
            }

            h = h / 3;
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] c, int i, int j) {
        Comparable swap = c[i];
        c[i] = c[j];
        c[j] = swap;
    }

    public static void main(String[] args) {
        String[] s = StdIn.readAllStrings();

        SubquadraticInsertion.sort(s);
    }
}
