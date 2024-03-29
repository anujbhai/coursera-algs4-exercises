/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StringSorter {
    public static void main(String[] args) {
        String[] str = StdIn.readAllStrings();

        Insertion.sort(str);

        for (int i = 0; i < str.length; i++) {
            StdOut.println(str[i]);
        }
    }
}
