package leetcode8.dynamicprogramming;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 354. You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Example:
Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0] == null || envelopes[0].length == 0) {
            return 0;
        }
        Comparator<int[]> com = new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                // Sort by ascending order, 1, 2, 3
                return Integer.compare(a[0], b[0]); // Looks more professional
                //return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0];
            }
        };
        
        Arrays.sort(envelopes, com);
        
        int res = 0;
        int len = envelopes.length;
        int[] hash = new int[len];
        for (int i = 0; i < len; i++) {
            hash[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    hash[i] = Math.max(hash[i], hash[j] + 1);
                }
            }
            // The maximum can be anywhere
            res = Math.max(res, hash[i]);
        }
        
        return res;
    }
}
