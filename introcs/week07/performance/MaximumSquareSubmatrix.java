public class MaximumSquareSubmatrix {

    // Returns the size of the largest contiguous square submatrix
    // of a[][] containing only 1s.
    public static int size(int[][] a) {
        int n = a.length;
        int b[][] = new int[n][n];
        int s = 0; // size is also the max

        // compute the size of the largest contiguous square submatrix whose lower right endpoint is (i, j)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (i > 0 && j > 0 && a[i][j] == 1) {
                    b[i][j] = Math.min(Math.min(b[i - 1][j], b[i][j - 1]), b[i - 1][j - 1]) + 1;
                } else {
                    b[i][j] = a[i][j];
                }

            }
        }

        // find the max
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (b[i][j] > s) {
                    s = b[i][j];
                }
            }
        }

        return s;

    }

    // Reads an n-by-n matrix of 0s and 1s from standard input
    // and prints the size of the largest contiguous square submatrix
    // containing only 1s.
    public static void main(String[] args) {
        int n = StdIn.readInt();
        int a[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = StdIn.readInt();

//                System.out.println(a[i][j]);
            }
        }

        StdOut.print(size(a));

    }
}
