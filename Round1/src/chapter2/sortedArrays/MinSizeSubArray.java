package chapter2.sortedArrays;
/**
 * 209. Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead. 
For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint. 
click to show more practice.
More practice: 
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */
public class MinSizeSubArray {
    public int minSubArrayLen1(int s, int[] nums) {
        int res = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        
        while (end < nums.length) {
            while (end < nums.length && sum < s) {
                sum += nums[end];
                end++;
            }
            
            if (sum < s) {
                break; // It will never be bigger than s
            }
            
            while (start < end && sum >= s) {
                sum -= nums[start];
                start++;
            }
            
            res = Math.min(res, end - start + 1);
        }
        
        return res == Integer.MAX_VALUE ? 0 : res;
    }
    public int minSubArrayLen3(int s, int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= s) {
                    res = Math.min(res, j - i + 1);
                    break;
                }
            }
        }
        
        return res == Integer.MAX_VALUE ? 0 : res;
    }
    public int minSubArrayLen2(int s, int[] nums) {
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < sum.length - 1; i++) {
            for (int j = i; j < sum.length; j++) {
                if (sum[j] - sum[i] >= s) {
                    res = Math.min(res, (j - i));
                    break;
                }
            }
        }
        
        return res == Integer.MAX_VALUE ? 0 : res;
    }
    
    // O(nLogN)
    public int minSubArrayLen(int s, int[] nums) {
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < sum.length; i++) {
            int index = binarySearch(sum, sum[i] + s, i); // From i, find the first bigger than sum[i] + s
            if (index == - 1) {
                break;
            }
            res = Math.min(res, index - i);
        }
        
        return res == Integer.MAX_VALUE ? 0 : res;
    }
    
    int binarySearch(int[] sum, int target, int start) {
        int end = sum.length - 1;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (sum[mid] >= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        
        if (sum[start] >= target) {
            return start;
        } else if (sum[end] >= target) {
            return end;
        } else {
            return -1;
        }
    }
}
