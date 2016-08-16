package chapter8.frequent;
/**
 * 259. Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:
[-2, 0, 1]
[-2, 0, 3]


Follow up:
 Could you solve it in O(n2) runtime? 

 */
import java.util.Arrays;

public class ThreeSumSmaller {
	    public int threeSumSmaller(int[] nums, int target) {
	        Arrays.sort(nums);
	        int res = 0;
	        for (int i = 0; i < nums.length - 2; i++) {
	            int left = i + 1;
	            int right = nums.length - 1;
	            
	            while (left < right) {
	                int curSum = nums[i] + nums[left] + nums[right];    
	                if (curSum < target) {
	                    res += (right - left); // In between. This is the magic!!!
	                    left++; // We can also try moving left right. No, just get all the count in between
	                } else { // Sum is too big
	                    right--;
	                }
	            }
	        }
	        
	        return res;
	    }
}
