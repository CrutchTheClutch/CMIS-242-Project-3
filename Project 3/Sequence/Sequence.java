package Sequence;

/**
 * File: Sequence.App.java
 * Author: William Crutchfield
 * Date: February 18, 2017
 * Description: This class computes the next number in the sequence, along with the efficiency of each computation.
 */
class Sequence {

    private static int efficiency;

    /**
     * Determines the number in the Sequence at position n, iteratively
     * @param n position to calculate up to
     * @return nth number in the sequence
     */
    static int computeIterative(int n) {

        if (n == 0) {   // base case
            return 0;
        }
        if (n == 1) {   // base case
            return 1;
        }

        // Variables
        int first = 1;
        int second = 0;
        int result = 0;
        efficiency = 0;

        // Loop
        for (int i = 1; i < n; i++) {
            result = (first * 2) + second;
            second = first;
            first = result;
            efficiency++;
        }

        return result;
    }

    /**
     * Helper method for recursive
     * @param n position to calculate up to
     * @return nth number in the sequence, sends to recursive method
     */
    static int computeRecursive(int n) {
        efficiency = 0;
        return recursive(n);
    }

    /**
     * Determines the number in the Sequence at position n, recursively
     * @param n position to calculate up to
     * @return nth number in the sequence
     */
    private static int recursive(int n) {
        efficiency++;   // counts num of loops
        if (n == 0) {   // base case
            return 0;
        }
        if (n == 1) {   // base case
            return 1;
        }
        return ((recursive(n-1)*2) + (recursive(n-2)));
    }

    /**
     * Getter Method for the efficiency, number of loops required, to calculate the current computation
     * @return efficiency
     */
    static int getEfficiency() {
        return efficiency;
    }
}
