public class Inversions {

    // Return the number of inversions in the permutation a[].
    public static long count(int[] a) {
        int len = a.length;
        int cnt = 0;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (a[i] > a[j]) cnt += 1;
            }
        }

        return cnt;

    }

    // Return a permutation of length n with exactly k inversions.
    public static int[] generate(int n, long k) {
        int[] array = new int[n];

        int a = (int) Math.sqrt((n * (n - 1) - 2 * k)); // find out if k is between [(n-1)+...+(n-x)] and [(n-1)+...+(n-x-1)]
        int b = n - 1 - a; // counts of (n-1), (n-2), ...
        int c = (int) (k - n * b + (b * b + b) / 2); // be careful with type (k - n * b + b * b / 2 + b / 2); // leftover = k - [(n-1)+(n-b)]*b/2;

//        System.out.println("a " + a + " | b " + b + " | c " + c + " | " + (k - n * b + (b * b + b) / 2));

        for (int i = 0; i < b; i++) {
            array[i] = n - 1 - i;
        }

        for (int i = b; i < n - 1 - c; i++) {
            array[i] = i - b;
        }

        array[n - 1 - c] = n - 1 - b;

        for (int i = n - c; i < n; i++) {
            array[i] = i - b - 1;
        }

        return array;

        // 0 1 2 3 4 5
        // 5 0 1 2 3 4 -> 5 inversions
        // 5 4 0 1 2 3 -> 4 inversions = 9
        // 5 4 0 3 1 2 -> 2 inversions = 11
        // 5 4"0"3"1 2
    }

    // Takes an integer n and a long k as command-line arguments,
    // and prints a permutation of length n with exactly k inversions.
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        long k = Long.parseLong(args[1]);

        for (int i = 0; i < n; i++) {
            StdOut.print(generate(n, k)[i] + " ");
        }

//        StdOut.print(count(generate(n, k)));

    }
}
