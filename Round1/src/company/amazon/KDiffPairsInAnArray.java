package company.amazon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import sun.applet.Main;

/**
 * integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

Example 1:
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.
Example 2:
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
Example 3:
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).
Note:
The pairs (i, j) and (j, i) count as the same pair.
The length of the array won't exceed 10,000.
All the integers in the given input belong to the range: [-1e7, 1e7].
 */
public class KDiffPairsInAnArray {

	public static void main(String[] args) {
		KDiffPairsInAnArray kd = new KDiffPairsInAnArray();
		int[] nums = {1, 3, 1, 5, 4};
		int k = 0;
		int res = kd.findPairs2MyStyle(nums, k);
		System.out.println(res);
	}
	
    public int findPairs(int[] nums, int k) {
        int left = 0, right = 1;
        int res = 0;
        
        Arrays.sort(nums);
        while (left < nums.length && right < nums.length) {
            if (left == right || nums[left] + k > nums[right]) {
                right++;
            } else if (nums[left] + k < nums[right]) {
                left++;
            } else {
                res++;
                
                left++;
                while (left < nums.length && nums[left] == nums[left - 1]) {
                    left++;
                }
                
                right = Math.max(left + 1, right + 1);
            }
        }
        
        return res;
    }
    
    public int findPairs2MyStyle(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int res = 0;
        
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int value = entry.getKey();
            int count = entry.getValue();
            int toFind = k + value; // Care about only 1 side
            
            if (toFind == value) {
            	if (count >= 2) {
            		res++;
            	}
            } else {
            	if (map.containsKey(toFind)) {
            		res++;
            	}
            }
        }
        
        return res;
    }
    
    // Easier to understand?
    public int findPairs2(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int count = 0;
        
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                if (entry.getValue() >= 2) {
                    count++;
                }
            } else {
                if (map.containsKey(entry.getKey() + k)) { // Care only 1 side
                    count++;
                }
            }
        }
        
        return count;
    }

}
