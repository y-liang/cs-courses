// import edu.princeton.cs.algs4.Stopwatch;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double Z_VALUE = 1.96; // This instance variable should be turned into a class constant by adding the 'static' modifier

    private final int trials;
    private double sumOfThresholds;
    private final double[] thresholds;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("invalid arguments.");

        this.trials = trials;
        this.thresholds = new double[trials];


        for (int i = 0; i < trials; i++) {

            // Initialize all sites to be blocked.
            Percolation sites = new Percolation(n);

            while (!sites.percolates()) {
                // Choose a site uniformly at random among all blocked sites
                // row and col start at 1 and end at n
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);

                while (sites.isOpen(row, col)) {
                    row = StdRandom.uniform(1, n + 1);
                    col = StdRandom.uniform(1, n + 1);
                }

                // Open the site
                sites.open(row, col);
            }

            double threshold = (double) sites.numberOfOpenSites() / (n * n);

            thresholds[i] = threshold;
            sumOfThresholds += threshold;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - Z_VALUE * stddev() / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + Z_VALUE * stddev() / Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, t);

        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% condifence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }

}
