/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class Permutation {
    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        RandomizedQueue<String> rd = new RandomizedQueue<String>();
        String s = StdIn.readString();
        while (s != null) {
            rd.enqueue(s);
            try {
                s = StdIn.readString();
            }
            catch (NoSuchElementException E) {
                s = null;
            }
        }
        while (num > 0) {
            StdOut.println(rd.dequeue());
            --num;
        }
    }
}
