public class RevesPuzzle {

    private static void hanoi(int n, String from, String temp, String to) {
        if (n == 0) return;
        hanoi(n - 1, from, to, temp);
        StdOut.println("Move disc " + n + " from " + from + " to " + to);
        hanoi(n - 1, temp, from, to);
    }


    private static void reves(int n, String from, String temp1, String temp2, String to) {
        if (n == 0) return;
        int k = (int) (n + 1 - Math.sqrt(2 * n + 1));

        hanoi(k, from, temp1, temp2);
        StdOut.println("Move disc " + k + " from " + from + " to " + temp2);

        hanoi(n - k, from, temp1, to);
        StdOut.println("Move disc " + (n - k) + " from " + from + " to " + to);

        hanoi(k, temp2, temp1, to);
        StdOut.println("Move disc " + k + " from " + temp2 + " to " + to);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

//        hanoi(n, "A", "B", "C");
        reves(n, "A", "B", "C", "D");


    }
}
