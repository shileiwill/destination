package leetcode8.dynamicprogramming;
/**
 * 276. There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.
 */
public class PaintFence {
    public int numWays2(int n, int k) {
        if (n == 0) { // size == 0
            return 0;
        } else if (n == 1) { // size == 1
            return k;
        }
        
        // Start from size == 2
        int[] different = new int[n];
        int[] same = new int[n];
        different[1] = k * (k - 1);
        same[1] = k;
        
        for (int i = 2; i < n; i++) {
            different[i] = (different[i - 1] + same[i - 1]) * (k - 1);
            same[i] = different[i - 1];
        }
        
        return different[n - 1] + same[n - 1];
    }
    
    public int numWays(int n, int k) {
        if (n == 0) { // size == 0
            return 0;
        } else if (n == 1) { // size == 1
            return k;
        }
        
        // Since we need only the previous value
        int diff = k * (k - 1);
        int same = k;
        
        for (int i = 2; i < n; i++) {
            int temp = diff;
            diff = (diff + same) * (k - 1);
            same = temp;
        }
        
        return diff + same;
    }
}
