import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final WeightedQuickUnionUF wuf;
    private boolean[][] openSites;
    private int numOfOpenSites;

//    private void printSites() {
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(openSites[i][j] + " ");
//            }
//            System.out.print("\n");
//        }
//    }

//    private void printWuf() {
//        for (int i = 1; i <= n * n; i++) {
//            System.out.print(wuf.find(i) + " ");
//            if (i % n == 0) {
//                System.out.print("\n");
//            }
//        }
//    }


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("invalid argument.");
        this.n = n;
        openSites = new boolean[n + 1][n + 1]; // indices end at n
        wuf = new WeightedQuickUnionUF(n * n + 2); // start at 0 end at n*n+1, but 1 to n*n are in the grid
        numOfOpenSites = 0;
    }

    // by convention, supposedly, row and col starts at 1 and ends at n
    // overall index k, however, starts at 1 and ends at n*n
    // index 0 and index n*n+1 serve as the top and bottom, respectively
    private int gridTo1D(int row, int col) {
        return (row - 1) * n + col;
    }


    private void validate(int size) {
        if (size <= 0 || size > n) throw new IllegalArgumentException("invalid argument.");
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        // validate the indices of the site
        validate(row);
        validate(col);


        int k = gridTo1D(row, col);

        // if top or bottom, link to outside sets 0 or n*n+1
        if (row == 1) wuf.union(0, k);
        if (row == n) wuf.union(n * n + 1, k);

        // link the site to its open neighbors
        if (row > 1) {
            if (isOpen(row - 1, col)) wuf.union(k, gridTo1D(row - 1, col));
        }
        if (col < n) {
            if (isOpen(row, col + 1)) wuf.union(k, gridTo1D(row, col + 1));
        }
        if (row < n) {
            if (isOpen(row + 1, col)) wuf.union(k, gridTo1D(row + 1, col));
        }
        if (col > 1) {
            if (isOpen(row, col - 1)) wuf.union(k, gridTo1D(row, col - 1));
        }

//        printSites();
//        System.out.print("\n");
//        printWuf();

        openSites[row - 1][col - 1] = true;
        numOfOpenSites += 1;

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row);
        validate(col);

        return openSites[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row);
        validate(col);

        int k = gridTo1D(row, col);
        return wuf.find(k) == wuf.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return wuf.find(0) == wuf.find(n * n + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        // Percolation sites = new Percolation(n);
    }
}
