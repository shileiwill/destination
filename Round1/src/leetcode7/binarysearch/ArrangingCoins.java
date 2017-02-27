package leetcode7.binarysearch;
/**
 * 441. You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:

n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.
Example 2:

n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.
 */
public class ArrangingCoins {
    public int arrangeCoinsNew(int n) {
        int left = 1, right = n / 2 + 1;
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (0.5 * mid * mid + 0.5 * mid == n) {
                return mid;
            } else if (0.5 * mid * mid + 0.5 * mid > n) { // Apply 0.5 first to avoid overflow
                right = mid;
            } else { // sum < n
                left = mid;
            }
        }
        
        if (0.5 * right * right + 0.5 * right <= n) {
            return right;
        }
        
        if (0.5 * left * left + 0.5 * left <= n) {
            return left;
        }
        
        return left - 1;
    }
    
    public int arrangeCoins2(int n) {
        long count = 0;
        long levelCount = 0;
        long level = 0;
        
        while (count <= n) {
            count += (++levelCount);
            level++;
            if (count > n) {
                return (int)(level - 1);
            }
        }
        
        return (int)level;
    }
    
    public int arrangeCoins(int n) {
        int left = 1, right = n / 2 + 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (0.5 * mid * mid + 0.5 * mid == n) {
                return mid;
            } else if (0.5 * mid * mid + 0.5 * mid > n) { // Apply 0.5 first to avoid overflow
                right = mid - 1;
            } else { // sum < n
                left = mid + 1;
            }
        }
        
        return left - 1;
    }
}
