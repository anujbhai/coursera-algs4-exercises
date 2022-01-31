/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Shuffle {
    private Shuffle() {
    }

    public static void shuffleSort(Object[] a) {
        int n = a.length;

        for (int i = 0; i < n; i++) {
            int r = StdRandom.uniform(i + 1);
            exch(a, i, r);

            /* For printing out the sequence of shuffling */
            // for (int k = 0; k < n; k++)
            //     StdOut.print(a[k] + " ");
            //
            // StdOut.println();
        }
    }

    public static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        String[] s = StdIn.readAllStrings();

        Shuffle.shuffleSort(s);

        /* For printing out the result of shuffling */
        for (int i = 0; i < s.length; i++) {
            StdOut.print(s[i] + " ");
        }

        StdOut.println();
    }
}
