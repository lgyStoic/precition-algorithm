/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] xts;
    private final int ptrails;
    private final double magicnumber;
    private double xbar;
    private double xdev;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        xbar = 0;
        xdev = 0;
        magicnumber = 1.96;
        xts = new double[trials];
        ptrails = trials;
        for (int t = 0; t < trials; t++) {
            Percolation perc = new Percolation(n);
            // StdDraw.enableDoubleBuffering();
            // PercolationVisualizer.draw(perc, n);
            // StdDraw.show();
            // StdOut.println("===== trials" + t);
            int opentime = 0;
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!perc.isOpen(row, col)) {
                    opentime++;
                }
                perc.open(row, col);
                // PercolationVisualizer.draw(perc, n);
                // StdDraw.show();
            }
            // StdOut.println("opening time = " + opentime);
            xts[t] = (double) opentime / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        xbar = StdStats.mean(xts);
        return xbar;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        xdev = StdStats.stddev(xts);
        return xdev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        if (xbar == 0 || xdev == 0) {
            mean();
            stddev();
        }
        return xbar - magicnumber * xdev / Math.sqrt(ptrails);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        if (xbar == 0 || xdev == 0) {
            mean();
            stddev();
        }
        return xbar + magicnumber * xdev / Math.sqrt(ptrails);
    }

    // test client (see below)
    public static void main(String[] args) {
        int grid = Integer.parseInt(args[0]);
        int trail = Integer.parseInt(args[1]);
        // StdOut.println(grid);
        PercolationStats stats = new PercolationStats(grid, trail);
        StdOut.println("mean = " + stats.mean());
        StdOut.println("stddev = " + stats.stddev());
        StdOut.println("95% confidence interval = " + "[" + stats.confidenceLo() + "," + stats
                .confidenceHi() + "]");
    }

}
