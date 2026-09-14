/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int n;
    private final int trials;
    private final double percT[];


    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.trials = trials;
        this.percT = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percs = new Percolation(n);
            while (!percs.percolates()) {
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                percs.open(row, col);
            }
            percT[i] = (double) percs.numberOfOpenSites() / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(percT);
    }

    public double stddev() {
        return StdStats.stddev(percT);
    }

    public double confidenceLo() {
        double mean = mean();
        double s = stddev();
        double rooT = Math.sqrt(trials);
        double confidenceLo = mean - (1.96 * s) / rooT;
        return confidenceLo;
    }

    public double confidenceHi() {
        double mean = mean();
        double s = stddev();
        double rooT = Math.sqrt(trials);
        double confidenceHi = mean + (1.96 * s) / rooT;
        return confidenceHi;
    }


    public static void main(String[] args) {
        String number = args[0];
        String Tcs = args[1];
        int n = Integer.valueOf(number);
        int T = Integer.valueOf(Tcs);
        PercolationStats percsts = new PercolationStats(n, T);
        double mean = percsts.mean();
        double stddev = percsts.stddev();
        double low = percsts.confidenceLo();
        double high = percsts.confidenceHi();
        StdOut.printf("mean                    = %f\n", mean);
        StdOut.printf("stddev                  = %f\n", stddev);
        StdOut.printf("95% confidence interval = [%f,%f]", low, high);

    }
}
