package chapter2.binarySearch;

import java.util.Arrays;

/**
 * Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces 
 * with the same length. What is the longest length you can get from the n pieces of wood? 
 * Given L & k, return the maximum length of the small pieces.

 Notice

You couldn't cut wood into float length.

Have you met this question in a real interview? Yes
Example
For L=[232, 124, 456], k=7, return 114.
 */
public class WoodCut {
    public int woodCut(int[] L, int k) {
        if (L.length == 0) {
            return 0;
        }
        // sort is not as good as just find the max using O(n)
        Arrays.sort(L);
        int left = 1;
        int right = L[L.length - 1];
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            
            if (count(L, mid, k)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        
        if (count(L, right, k)) {
            return right;
        }
        if (count(L, left, k)) {
            return left;
        }
        return 0;
    }
    
    boolean count(int[] L, int num, int k) {
        long sum = 0;
        for (int i = 0; i < L.length; i++) {
            sum += L[i] / num;
        }
        return sum >= k;
    }
}
