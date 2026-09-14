/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        String number = args[0];
        int k = Integer.valueOf(number);
        while (!StdIn.isEmpty()) {
            String temp = StdIn.readString();
            rq.enqueue(temp);
        }
        int count = 0;
        for (String val : rq) {
            if (count < k) {
                StdOut.println(val);
            }
            count++;
        }

    }
}
