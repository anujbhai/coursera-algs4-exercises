import edu.princeton.cs.algs4.StdOut;

public class MedianHeap {
    private Integer[] pq;
    private int n;

    private MedianHeap(int capacity) {
        this.pq = new Integer[capacity + 1];
        this.n = 0;
    }

    private void insert(int x) {
        pq[++n] = x;
        swim(n);
    }

    public int delMedian() {
        int median = findMedian();

        exch(1, n--);
        sink(1);

        pq[n + 1] = null;

        return median;
    }

    public int findMedian() {
        return pq[1];
    }

    private void swim(int k) {
        while (even(k) && k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            if (n > k && less(k + 1, k / 2)) exch(k + 1, k / 2);
            k = k / 2;
        }

        while (!even(k) && k > 1 && !less(k / 2, k)) {
            exch(k, k / 2);
            if (!less(k - 1, k / 2)) exch(k - 1, k / 2);
            k = k / 2;
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;

            if (j < n && less(j, j + 1)) j++;
            if (!less(k, j)) break;

            exch(k, j);

            k = j;
        }
    }

    private boolean even(int i) {
        return (i % 2) == 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    public static void main(String[] args) {
        MedianHeap mh = new MedianHeap(10);

        for (int i = 1; i <= 10; i++) {
            mh.insert(i);
        }
        StdOut.println("Median: " + mh.findMedian());

        mh.delMedian();
        StdOut.println("Original median deleted. New median: " + mh.findMedian());
    }
}
