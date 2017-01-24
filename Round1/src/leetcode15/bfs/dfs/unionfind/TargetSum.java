package leetcode15.bfs.dfs.unionfind;

import java.util.HashMap;
import java.util.Map;

/**
 * 494. You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
 */
public class TargetSum {

    int res = 0;
    public int findTargetSumWays2(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        dfs(nums, S, 0, 0);
        return res;
    }
    
    void dfs(int[] nums, int target, int pos, int curSum) {
        if (pos == nums.length) {
            if (curSum == target) {
                res++;
            }
            return;
        }
        
        dfs(nums, target, pos + 1, curSum + nums[pos]);
        dfs(nums, target, pos + 1, curSum - nums[pos]);
    }
    
    public int findTargetSumWaysNoMemory(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        return dfsNoMemory(nums, S, 0, 0);
    }
    
    int dfsNoMemory(int[] nums, int target, int pos, int curSum) {
        if (pos == nums.length) {
            if (curSum == target) {
                return 1;
            } else {
                return 0;
            }
        }
        
        int add = dfsNoMemory(nums, target, pos + 1, curSum + nums[pos]);
        int minus = dfsNoMemory(nums, target, pos + 1, curSum - nums[pos]);
        
        return add + minus;
    }
    
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        return dfsMemory(nums, S, 0, 0, new HashMap<String, Integer>());
    }
    
    int dfsMemory(int[] nums, int target, int pos, int curSum, Map<String, Integer> map) {
        String str = pos + "->" + curSum; // 从当前pos位置到结尾 求得curSum 的方案个数
        if (map.containsKey(str)) {
            return map.get(str);
        }
        
        if (pos == nums.length) {
            if (curSum == target) {
                return 1;
            } else {
                return 0;
            }
        }
        
        int add = dfsMemory(nums, target, pos + 1, curSum + nums[pos], map);
        int minus = dfsMemory(nums, target, pos + 1, curSum - nums[pos], map);
        map.put(str, add + minus);
        
        return add + minus;
    }

}
