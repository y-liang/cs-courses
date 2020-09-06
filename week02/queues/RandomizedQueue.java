import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// resize() doubles the underlying array when it is full and
// halves the underlying array when it is one-quarter full.

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int n;
    private Item[] a;


    // construct an empty randomized queue
    public RandomizedQueue() {
        // a = new Item[2]; // not allowed

        // @SuppressWarnings("unchecked")
        a = (Item[]) new Object[2];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // helper resize function, resizing the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;

        // textbook implementation
        // @SuppressWarnings("unchecked")
        Item[] copy = (Item[]) new Object[capacity];
        // Item[] copy = new Item[capacity]; // this does not work for generic array creation
        for (int i = 0; i < n; i++) {
            copy[i] = a[i];
        }

        a = copy;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if (n == a.length) resize(2 * a.length); // double size of array if necessary
        if (isEmpty()) resize(2); // n and a.length both equal to 0

        a[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int k = StdRandom.uniform(n);
        Item item = a[k];

        // copy elements of original array to new array except for element at index k
        // Item[] copy = new Item[n--];
        // @SuppressWarnings("unchecked")
        Item[] copy = (Item[]) new Object[--n];
        for (int i = 0; i < k; i++) {
            copy[i] = a[i];
        }
        for (int i = k; i < n; i++) {
            copy[i] = a[i + 1];
        }

        a = copy;

        if (n > 0 && n == a.length / 4) resize(a.length / 2); // shrink size of array if necessary
        // but why over 4?
        // making sure the size of the array is always twice?

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int k = StdRandom.uniform(n);
        return a[k];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    // helper iterator class
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
//        private Item[] b;

        public ArrayIterator() {
            StdRandom.shuffle(a);
        }

        public boolean hasNext() {
            return i < n;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[i++];

        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }


    // unit testing (required)
    public static void main(String[] args) {
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();

        for (int i = 0; i < n; i++) {
            queue.enqueue(i);
        }
//        for (int q : queue) {
//            StdOut.print(q + " ");
//        }


        StdOut.println("\n" + queue.sample() + " sampled from the queue");
        StdOut.println(queue.dequeue() + " dequeued from the queue");

//        for (int q : queue) {
//            StdOut.print(q + " ");
//        }

        StdOut.println("\n" + queue.size() + " left on the queue");


    }

}
