import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedPQ {
    private Integer[] pq;
    private int n;

    public RandomizedPQ(int capacity) {
        this.pq = new Integer[capacity + 1];
        this.n = 0;
    }

    public void insert(int x) {
        pq[++n] = x;
    }

    public void insertForDel(int x) {
        pq[++n] = x;
        swim(n);
    }

    public int sample() {
        return findSample();
    }

    public int delSample() {
        int smpl = findSample();

        exch(1, n--);
        sink(1);

        pq[n + 1] = null;

        return smpl;
    }

    public int findSample() {
        int r = StdRandom.uniform(pq.length);
        return pq[r];
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        RandomizedPQ rndm = new RandomizedPQ(n);

        for (int i = 1; i <= n; i++) {
            rndm.insert(i);
            StdOut.print(i + " ");
        }

        StdOut.println("Random: " + rndm.findSample());
        StdOut.println();
        
        StdOut.println("Previous random deleted. New random: " + rndm.delSample());
    }
}
