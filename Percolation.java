/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF wf;
    private int coln;
    private int top;
    private int bottom;
    private int rown;
    private boolean[] openArray;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        wf = new WeightedQuickUnionUF(n * n + 2);
        coln = n;
        rown = n;
        top = n * n;
        bottom = n * n + 1;
        for (int i = 0; i < n; i++) {
            wf.union(top, i);
            wf.union(n * n - i - 1, bottom);
        }
        openArray = new boolean[n * n + 2];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        col = col - 1;
        row = row - 1;
        openArray[row * coln + col] = true;
        if (col + 1 <= coln - 1 && isOpen(row + 1, col + 1 + 1)) {
            wf.union(row * coln + col, row * coln + col + 1);
        }
        if (col - 1 >= 0 && isOpen(row + 1, col + 1 - 1)) {
            wf.union(row * coln + col, row * coln + col - 1);
        }
        if (row - 1 >= 0 && isOpen(row - 1 + 1, col + 1)) {
            wf.union(col * rown + row - 1, col * rown + row);
        }
        if (row + 1 <= rown - 1 && isOpen(row + 1 + 1, col + 1)) {
            wf.union(col * rown + row + 1, col * rown + row);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        col = col - 1;
        row = row - 1;
        return openArray[row * coln + col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        col = col - 1;
        row = row - 1;
        return wf.find(row * coln + col) == wf.find(top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return wf.count();
    }

    // does the system percolate?
    public boolean percolates() {
        return wf.find(bottom) == wf.find(top);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
