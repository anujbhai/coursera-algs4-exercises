import edu.princeton.cs.algs4.Queue;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = true;
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private boolean color;
        private int count;

        public Node(Key key, Value val, boolean color, int count) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.count = count;
        }
    }

    public RedBlackBST() {}

    /***************************************************************************
     *  Node helper methods.
     ***************************************************************************/
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    public int size() {
        return size(root);
    }
    private int size(Node x) {
        if (x == null) return 0;
        return x.count;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /***************************************************************************
     *  Standard BST search.
     ***************************************************************************/
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException();
        return get(root, key);
    }
    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }

        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    /***************************************************************************
     *  Red-black tree insertion.
     ***************************************************************************/
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException();
        if (val == null) {
            delete(key);
            return;
        }

        root = put(root, key, val);
        root.color = BLACK;
    }
    private Node put(Node h, Key key, Value val) {
        if (h == null) return new Node(key, val, RED, 1);

        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.count = size(h.left) + size(h.right) + 1;

        return h;
    }

    /***************************************************************************
     *  Red-black tree deletion.
     ***************************************************************************/
    public void delete(Key key) {}
    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left)) h = moveRedLeft(h);
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left)) h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
        }
    }

    public void deleteMin() {}
    private Node deleteMin(Node h) {}

    public void deleteMax() {}
    private Node deleteMax(Node h) {}

    /***************************************************************************
     *  Red-black tree helper functions.
     ***************************************************************************/
    private Node rotateRight(Node h) {}

    private Node rotateLeft(Node h) {}

    private void flipColors(Node h) {}

    private Node moveRedLeft(Node h) {}

    private Node moveRedRight(Node h) {}

    private Node balance(Node h) {}

    /***************************************************************************
     *  Ordered symbol table methods.
     ***************************************************************************/
    public Key min() {}
    private Node min(Node x) {}

    public Key max() {}
    private Node max(Node x) {}

    public Key floor(Key key) {}
    private Node floor(Node x, Key key) {}

    public Key ceiling(Key key) {}
    private Node ceiling(Node x, Key key) {}

    public Key select(int rank) {}
    private Key select(Node x, int rank) {}

    public int rank(Key key) {}
    private int rank(Key key, Node x) {}

    /***************************************************************************
     *  Range count and range search.
     ***************************************************************************/
    public Iterable<Key> keys() {}

    public Iterable<Key> keys(Key lo, Key hi) {}
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {}

    public int size(Key lo, Key hi) {}


    public static void main(String[] args) {

    }
}
