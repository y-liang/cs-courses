import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Item[] a; // array of items
    private int n; // site of the deque

    // construct an empty deque
    public Deque()

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


    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();


    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();


    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();


    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    // an iterator
    private class ArrayIterator implements Iterator<Item> {
        private int i;

        public ArrayIterator() {

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
        Deque<String> deque = new Deque<String>();


    }

}
