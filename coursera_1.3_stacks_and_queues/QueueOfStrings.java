/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QueueOfStrings<Item> {
    private Node<Item> first, last;
    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public QueueOfStrings() {
        first = null;
        last = null;
        n = 0;
    }

    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        }
        else {
            oldlast.next = last;
        }
    }

    public Item dequeue() {
        Item item = first.item;
        first = first.next;

        if (isEmpty()) {
            last = null;
        }

        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public static void main(String[] args) {
        QueueOfStrings<String> queue = new QueueOfStrings<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();

            if (!item.equals("-")) {
                queue.enqueue(item);
            }
            else if (!queue.isEmpty()) {
                StdOut.println(queue.dequeue() + " ");
            }
        }

        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
