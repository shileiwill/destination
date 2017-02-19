package facebook;
/**
 * 334. Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:
Return true if there exists i, j, k 
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Your algorithm should run in O(n) time complexity and O(1) space complexity.

Examples:
Given [1, 2, 3, 4, 5],
return true.

Given [5, 4, 3, 2, 1],
return false.
 */
public class IncreasingTripletSubsequence {
    // This question is very similar to Longest Increasing Subsequence, but easier
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        int[] hash = new int[nums.length];
        hash[0] = 1;
        
        for (int i = 1; i < nums.length; i++) {
            hash[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    hash[i] = Math.max(hash[i], hash[j] + 1);
                    if (hash[i] >= 3) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
}
