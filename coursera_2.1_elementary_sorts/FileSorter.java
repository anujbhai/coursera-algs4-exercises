/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Insertion;

import java.io.File;

public class FileSorter {
    public static void main(String[] args) {
        File directory = new File(args[0]);
        File[] files = directory.listFiles();

        assert files != null;
        Insertion.sort(files);

        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getName());
        }
    }
}
