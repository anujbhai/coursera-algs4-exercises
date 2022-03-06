import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private final Key key;
        private Value val;
        private Node left, right;
        private int count; // Number of nodes in a subtree

        public Node(Key key, Value val, int count) {
            this.key = key;
            this.val = val;
            this.count = count;
        }
    }

    public BST() {}

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException();
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
    }
    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);

        int cmp = key.compareTo(x.key);

        if (cmp < 0) x.left = put(x.left, key, val); // recursion
        else if (cmp > 0) x.right = put(x.right, key, val); // Recursion
        else x.val = val;

        x.count = 1 + size(x.left) + size(x.right);

        return x;
    }

    public Value get(Key key) {
        return get(root, key);
    }
    private Value get(Node x, Key key) {
        if (key == null) throw new IllegalArgumentException();
        if (x == null) return null;

        int cmp = key.compareTo(x.key);

        if (cmp < 0) return get(x.left, key); // Recursion
        else if (cmp > 0) return get(x.right, key); // Recursion
        else return x.val;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException();
        root = delete(root, key);
    }
    private Node delete(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);

        if (cmp < 0) x.left = delete(x.left, key); // Recursion
        else if (cmp > 0) x.right = delete(x.right, key); // Recursion
        else {
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;

            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }

        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException();
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }
    private int size(Node x) {
        if (x == null) return 0;
        else return x.count;
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException();
        return min(root).key;
    }
    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left); // Recursion
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException();
        return max(root).key;
    }
    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right); // Recursion
    }

    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException();
        if (isEmpty()) throw new NoSuchElementException();

        Node x = floor(root, key);
        if (x == null) throw new NoSuchElementException();
        return x.key;
    }
    private Node floor(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);

        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key); // Recursion

        Node t = floor(x.right, key); // Recursion
        if (t != null) return t;
        else return x;
    }

    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException();
        if (isEmpty()) throw new NoSuchElementException();

        Node x = ceiling(root, key);
        if (x == null) throw new NoSuchElementException();
        return x.key;
    }
    private Node ceiling(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);

        if (cmp == 0) return x;
        if (cmp < 0) {
            Node t = ceiling(x.left, key); // Recursion
            if (t != null) return t;
            else return x;
        }

        return ceiling(x.right, key); // Recursion
    }

    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException();
        return rank(key, root);
    }
    private int rank(Key key, Node x) {
        if (x == null) return 0;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left); // Recursion
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right); // Recursion
        else return size(x.left);
    }

    public Key select(int rank) {
        if (rank < 0 || rank >= size()) throw new IllegalArgumentException();
        return select(root, rank);
    }
    private Key select(Node x, int rank) {
        if (x == null) return null;

        int lt = size(x.left);

        if (lt > rank) return select(x.left, rank); // Recursion
        else if (lt < rank) return select(x.right, rank - lt - 1);
        else return x.key;
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException();
        root = deleteMin(root);
    }
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;

        x.left = deleteMin(x.left); // Recursion
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException();
        root = deleteMax(root);
    }
    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;

        x.right = deleteMin(x.right); // Recursion
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }

    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException();
        if (hi == null) throw new IllegalArgumentException();

        if (lo.compareTo(hi) > 0) return 0;

        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    public Iterable<Key> keys() {
        if (isEmpty()) return new Queue<>();
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException();
        if (hi == null) throw new IllegalArgumentException();

        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;

        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);

        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmplo > 0) keys(x.right, queue, lo, hi);
    }

    public Iterable<Key> levelOrder() {
        Queue<Key> keys = new Queue<>();
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }

    public static void main(String[] args) {
        BST<String, Integer> st = new BST<>();

        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.levelOrder())
            StdOut.println(s + " " + st.get(s));

        StdOut.println();

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
