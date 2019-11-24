/* *****************************************************************************
 *  Name:              garryling
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
        openArray = new boolean[n * n + 2];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        col = col - 1;
        row = row - 1;
        openArray[row * coln + col] = true;
        // StdOut.println("================");
        // StdOut.println("row = " + row + " col = " + col);
        // right
        if (col + 1 < coln && isOpen(row + 1, col + 1 + 1)) {
            // StdOut.println("right ===== " + (row * coln + col) + " " + (row * coln + col + 1));
            wf.union(row * coln + col, row * coln + col + 1);
        }
        // left
        if (col - 1 >= 0 && isOpen(row + 1, col + 1 - 1)) {
            // StdOut.println("left =====" + (row * coln + col) + " " + (row * coln + col - 1));
            wf.union(row * coln + col, row * coln + col - 1);
        }
        // up
        if (row - 1 >= 0 && isOpen(row - 1 + 1, col + 1)) {
            // StdOut.println("up =====" + (col * rown + row - 1) + " " + (col * rown + row));
            wf.union(row * coln + col, (row - 1) * coln + col);
        }
        // down
        if (row + 1 < rown && isOpen(row + 1 + 1, col + 1)) {
            // StdOut.println("down =====" + (col * rown + row + 1) + " " + (col * rown + row));
            wf.union(row * coln + col, (row + 1) * coln + col);
        }
        if (row == rown - 1 && wf.find(row * coln + col) == wf.find(top)) {
            // StdOut.println("union bottom = " + bottom + "from = " + (row * coln + col));
            wf.union(row * coln + col, bottom);
        }
        if (row == 0) {
            // StdOut.println("union top = " + top + "from = " + (col));
            wf.union(col, top);
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
        // StdOut.println("bottom = " + wf.find(bottom) + ", top = " + wf.find(top));
        if (wf.find(bottom) == wf.find(top)) {
            return true;
        }
        else {
            for (int i = (rown - 1) * coln; i < rown * coln; i++) {
                if (isOpen(rown, i % coln + 1) && wf.find(i) == wf.find(top)) {
                    return true;
                }
            }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
