import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int n;
    private Node first, last;

    // helper doubly linked list class, no () because it's a class, not a function
    private class Node {
        private Item item;
        private Node prev, next;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node oldFirst = first;
        first = new Node(); // do not forget to create a new Node because first was null

        first.item = item;
        first.prev = null;

        if (isEmpty()) {
            last = first;
        } else {
            first.next = oldFirst;
            oldFirst.prev = first;
        }

        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node oldLast = last;
        last = new Node();

        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            last.prev = oldLast;
            oldLast.next = last;
        }

        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        Item item = first.item;
        if (first.next != null) {
            first = first.next;
            first.prev = null;
        } else {
            first = null;
        }

        n--;

        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();

        Item item = last.item;
        if (last.prev != null) {
            last = last.prev;
            last.next = null;
        } else {
            last = null;
        }

        n--;


        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    // helper iterator class
    private class LinkedIterator implements Iterator<Item> {
        private Node current = first; // current is actually the next Node coming up

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;

            return item;
        }


        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        int n = 9;
        Deque<Integer> deque = new Deque<Integer>();

        for (int i = 0; i < n; i++) {
            deque.addFirst(i);
        }
//        for (int a : deque) {
//            StdOut.print(a + " ");
//        }
//        StdOut.println("\ntested addFirst()\n");

        for (int i = 0; i < n; i++) {
            deque.addLast(i);
        }
//        for (int a : deque) {
//            StdOut.print(a + " ");
//        }
//        StdOut.println("\ntested addLast()\n");

        deque.removeLast();
//        for (int a : deque) {
//            StdOut.print(a + " ");
//        }
//        StdOut.println("\ntested removeLast()\n");

        deque.removeFirst();
//        for (int a : deque) {
//            StdOut.print(a + " ");
//        }
//        StdOut.println("\ntested removeFirst()\n");

        StdOut.println(deque.size() + " left on stack");

    }

}
