/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean values[];
    private int grid_size;
    private WeightedQuickUnionUF wquf;

    public Percolation(int n) {
        grid_size = n;
        int size = (n * n);
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.values = new boolean[size];

        for (int i = 0; i <= size - 1; i++) {
            values[i] = false;
        }
        wquf = new WeightedQuickUnionUF(n * n + 2);
    }


    public boolean isOpen(int row, int col) {
        if (row < 1 || row > grid_size || col < 1 || col > grid_size) {
            throw new IllegalArgumentException();
        }
        int i = row - 1;
        int j = col - 1;
        int site = (i) * grid_size + j;
        if (values[site] == true) {
            return true;
        }
        else {
            return false;
        }
    }

    public void open(int row, int col) {
        if (row < 1 || row > grid_size || col < 1 || col > grid_size) {
            throw new IllegalArgumentException();
        }
        int i = row - 1;
        int j = col - 1;
        int site = (i) * grid_size + j;
        if (values[site] == true) {
            return;
        }
        values[site] = true;
        if ((1 + i) < grid_size) {
            int bottom = (i + 1) * grid_size + j;
            if (values[bottom] == true)
                wquf.union(bottom, site);
        }
        if (i - 1 >= 0) {
            int top = (i - 1) * grid_size + j;
            if (values[top] == true)
                wquf.union(top, site);
        }
        if (j - 1 >= 0) {
            int left = i * grid_size + (j - 1);
            if (values[left] == true)
                wquf.union(left, site);
        }
        if (j + 1 < grid_size) {
            int right = i * grid_size + (j + 1);
            if (values[right] == true)
                wquf.union(right, site);
        }
        if (i == grid_size - 1) {
            int virtual_bottom = grid_size * grid_size + 1;
            wquf.union(virtual_bottom, site);
        }
        if (i == 0) {
            int virtual_top = grid_size * grid_size;
            wquf.union(virtual_top, site);
        }
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || row > grid_size || col < 1 || col > grid_size) {
            throw new IllegalArgumentException();
        }
        int i = row - 1;
        int j = col - 1;
        int site = i * grid_size + j;
        int top = grid_size * grid_size;
        if (wquf.connected(site, top) && isOpen(row, col)) {
            return true;
        }
        else {
            return false;
        }
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < grid_size * grid_size; i++) {
            if (values[i] == true) {
                count++;
            }
        }
        return count;
    }

    public boolean percolates() {
        return wquf.connected(grid_size * grid_size, grid_size * grid_size + 1);
    }


    public static void main(String[] args) {

    }
}
