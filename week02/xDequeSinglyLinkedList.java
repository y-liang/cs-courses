/**
 * The Deque class partially references LinkedStack.java authored by Robert Sedgewick and Kevin Wayne.
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {

    private int n; // size of the deque
    private Node first, last; // front and back of deque


    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }


    // construct an empty deque
    public Deque() {
        n = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front, stack's push
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;

        if (isEmpty()) last = first;

        n++;
    }

    // add the item to the back, queue's enqueue
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) first = last;
        else oldlast.next = last;

        n++;
    }

    // remove and return the item from the front, queue's dequeue
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        System.out.println("first.item " + first.item);

        Item item = first.item;
        first = first.next;

        if (isEmpty()) last = null;

        n--;
        return item;
    }

    // remove and return the item from the back, stack's pop but for last
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();

        System.out.println("last.item " + last.item);

        Item item = last.item;
        Node oldlast = last;
        oldlast = null;

        last.item = null; // need this to clear up iterator
        last.next = null;

//        Item newlast = null; // fake initialization

        Iterator iterator = iterator();

        for (Item i : this) {
            System.out.println("i " + i);
            if (iterator.hasNext()) {
                last = iterator.getCurrent();

                System.out.println("i inside" + i);
                System.out.println("iterator().hasNext() " + iterator().hasNext());

//                break;;
            }

        }


//        last.item = newlast;

        if (isEmpty()) first = null;

        System.out.println("last.item " + last.item);
        System.out.println("last.next " + last.next);


        n--;
        return item;

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    // an iterator
    private class LinkedIterator implements Iterator<Item> {
        private Node current = first;

        public Node getCurrent() {
            return current;
        }

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
        Deque<String> deque = new Deque<String>();


        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();

            if (deque.size() % 2 == 1) deque.addLast(item);
            else deque.addFirst(item);

        }

        StdOut.println(deque.size() + " is on deque");

        for (String s : deque) {
            StdOut.print(s + " ");
        }

        StdOut.print("\n");

        if (!deque.isEmpty()) {
            StdOut.println(deque.removeFirst());
        }

        if (!deque.isEmpty()) {
            StdOut.println(deque.removeLast());
        }


        StdOut.print("\n");

        for (String s : deque) {
            StdOut.print(s + " ");
        }


    }

}
