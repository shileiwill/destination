package company.facebook;
// 重要
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 78. Given a set of distinct integers, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 *
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        if (nums == null) {
            return null;
        }
        
        helper(res, list, nums, 0);
        
        return res;
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int pos) {
    	res.add(new ArrayList<Integer>(list)); // Remember to deep copy the list. Just add, cant return, which is a difference with Permutation
        
        for (int i = pos; i < nums.length; i++) { // Dont repeat yourself. Start from a new point always.
            list.add(nums[i]);
            helper(res, list, nums, i + 1); // In order to keep moving, here is i+1, rather than pos+1
            list.remove(list.size() - 1);
        }
    }
    
    // Iteration
    public static List<List<Integer>> subsetsIteration(int[] arr) {
    	List<List<Integer>> res = new ArrayList<List<Integer>>();
    	res.add(new ArrayList<Integer>());
    	
    	for (int i = 0; i < arr.length; i++) { // Every element
    		int val = arr[i];
    		
    		int size = res.size(); // Fix the size here
    		for (int j = 0; j < size; j++) {
    			List<Integer> list = res.get(j);
    			List<Integer> copy = new ArrayList<Integer>(list); // Make a deep copy
    			copy.add(val);
    			res.add(copy);
    		}
    	}
    	
    	return res;
    }
    
    public static void main(String[] args) {
       List<List<Integer>> r =  subsetsIteration(new int[]{1,2,3});
       for(List <Integer> one : r){
           System.out.println(Arrays.toString(one.toArray()));
       }
     }
    
    // What if there is duplicates in input array
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        if (nums == null) {
            return null;
        }
        
        Arrays.sort(nums); // Sort and recognize duplicate elements easily
        helper2(res, list, nums, 0);
        
        return res;
    }
    
    private void helper2(List<List<Integer>> res, List<Integer> list, int[] nums, int pos) {
    	res.add(new ArrayList<Integer>(list)); // Remember to deep copy the list
        
        for (int i = pos; i < nums.length; i++) { // Dont repeat yourself. Start from a new point always.
            // If 2 elements are same, and if the first element is not selected, then next ones should be ignored.
            if (i != pos && nums[i] == nums[i - 1]) {
            	// i != pos, means we executed for loop at least once, and we removed the previous element(not selected)
                continue;
            }
            list.add(nums[i]);
            helper2(res, list, nums, i + 1); // In order to keep moving, here is i+1, rather than pos+1
            list.remove(list.size() - 1);
        }
    }
    
    // 重要
    /**
     * 这是一个好题 找 所有 subset的个数，并且subset的 max + min < 给定k
     * 给一个set，求有多少个subsets，里面的min和max之和小于k。国人小哥很nice的提示了可以先排序，然后two pointer从两头往中间找。
     */
    int subsetsMinMaxLessThanK(int[] arr, int k) {
    	Arrays.sort(arr);
    	int left = 0;
    	int right = arr.length - 1;
    	int res = 0;
    	
    	while (left < right) {
    		if (arr[left] + arr[right] < k) {
    			res += Math.pow(2, right - left); //固定住left, 右边随便挑，组成所有subset的可能
    			left++;
    		} else {
    			right--;
    		}
    	}
    	
    	return res;
    }
}
