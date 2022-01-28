/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ResizingArrayStack<Item> {
    private static final int INIT_CAPACITY = 8;
    private Item[] s;
    private int n;

    public ResizingArrayStack() {
        s = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        if (n == s.length) {
            resize(2 * s.length);
        }
        s[n++] = item;
    }

    public Item pop() {
        Item item = s[n - 1];
        s[n - 1] = null;
        n--;

        if (n > 0 && n == s.length / 4) {
            resize(s.length / 2);
        }

        return item;
    }

    public void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < n; i++) {
            copy[i] = s[i];
        }

        s = copy;
    }

    public static void main(String[] args) {
        ResizingArrayStack<String> stack = new ResizingArrayStack<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();

            if (!item.equals("-")) {
                stack.push(item);
            }
            else if (!stack.isEmpty()) {
                StdOut.print(stack.pop() + " ");
            }
        }

        StdOut.println("(" + stack.size() + " left on stack()");
    }
}
