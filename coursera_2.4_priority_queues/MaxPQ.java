import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int n;
    private Comparator<Key> comparator;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
        n = 0;
    }

    public MaxPQ() {
        this(1);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public MaxPQ(int capacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Comparable[capacity + 1];
        n = 0;
    }

    public MaxPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public MaxPQ(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Comparable[keys.length + 1];

        System.arraycopy(keys, 0, pq, 1, n);

        for (int k = n/2; k >= 1; k--)
            sink(k);
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException();
        return pq[1];
    }

    public int size() {
        return n;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void resize(int capacity) {
        assert capacity > n;
        Key[] temp = (Key[]) new Comparable[capacity];
        if (n >= 0) System.arraycopy(pq, 1, temp, 1, n);
        pq = temp;
    }

    public void insert(Key v) {
        if (n == pq.length - 1) resize(2 * pq.length);
        pq[++n] = v;
        swim(n);
    }

    public Key delMax() {
        Key max = pq[1];

        exch(1, n--);
        sink(1);
        pq[n+1] = null;

        return max;
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
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
        return comparator.compare(pq[i], pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
        }

        StdOut.println("(" + pq.size() + " left on pq");
    }
}
