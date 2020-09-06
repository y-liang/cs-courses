import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        int count = 0;

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();

            if (count == k) {
                queue.dequeue();
            }

            queue.enqueue(s);
            count++;

        }

        for (String q : queue) {
            StdOut.println(q);
        }
    }
}
