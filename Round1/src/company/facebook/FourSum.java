package company.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 18. Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note: The solution set must not contain duplicate quadruplets.

For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
 * @author Lei
 *
 */
public class FourSum {
	public static void main(String[] args) {
		int[] nums = {1,0,-1,0,-2,2};
		int target = 0;
		
		new FourSum().fourSum(nums, target);
	}
	
	// Using Map makes it faster. One issue with this Map method is how to remove redundant list in result
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) { // This could be i < nums.length - 3
            
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            for (int j = i + 1; j < nums.length; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
            
                int sum = nums[i] + nums[j];
                
                int remainder = target - sum;
                if (map.containsKey(remainder)) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.addAll(map.get(remainder));
                        
                    res.add(list);
                } else {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    
                    map.put(sum, list);
                }
            }
        }
        
        return res;
    }
    
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            for (int j = i + 1; j < nums.length - 2; j++) {
                
                if (j != i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
            
                int left = j + 1;
                int right = nums.length - 1;
            
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    
                    if (sum == target) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        
                        res.add(list);
                        
                        left++;
                        right--;
                        
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        
        return res;
    }
}