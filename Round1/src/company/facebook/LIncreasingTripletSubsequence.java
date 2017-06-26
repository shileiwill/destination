package company.facebook;

import java.util.Map;
import java.util.TreeMap;

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
public class LIncreasingTripletSubsequence {
    // This question is very similar to Longest Increasing Subsequence, but easier
	// DP的时间复杂度不大好
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
    
    // 用TreeMap的思想 O(N) space, time
    public static boolean increasingTripletTreeMap(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        // TreeMap记录的是<= Key的count
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            Map.Entry<Integer, Integer> entry = map.floorEntry(nums[i]);
            
            if (entry == null) {
            	map.put(nums[i], 1);
            } else {
            	int count = entry.getValue();
            	
            	if (count >= 2) {
            		return true;
            	}
            	
            	map.put(nums[i], count + 1);
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
		int[] arr = {5, 4, 3, 2, 1};
		boolean res = increasingTripletTreeMap(arr);
		System.out.println(res);
	}
}
