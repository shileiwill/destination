package company.facebook;
// 重要！给一个array，还有一个windows size。 滑动window，求最大(Sliding Window Maximum)。 
// follow up，一样的参数，要求是从array里选3个windows，求所有windows的item sum最大，window 不能相互重合
/**
 * 410. Given an array which consists of non-negative integers and an integer m, 
 * you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

Note:
If n is the length of array, assume the following constraints are satisfied:

1 ≤ n ≤ 1000
1 ≤ m ≤ min(50, n)
Examples:

Input:
nums = [7,2,5,10,8]
m = 2

Output:
18

Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.

 */
public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        int maxVal = 0;
        long sum = 0;
        
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
            sum += num;
        }
        
        long left = maxVal, right = sum;
        while (left + 1 < right) {
            long mid = left + (right - left) / 2;
            if (isValid(mid, nums, m)) { // 这里使用的mid可能不是一个合法的值，但是这个binary search会一直往左， 小的方向搜，直到找到一个边界值，which is exactly the result
                right = mid;
            } else {
                left = mid;
            }
        }
        
        if (isValid(left, nums, m)) {
            return (int)left;
        } else {
            return (int)right;
        }
        // Left and right may not be a valid sum?
        /*
        https://discuss.leetcode.com/topic/61315/java-easy-binary-search-solution-8ms/2
        you are returning 18, but how did you make sure 18 is a sum of subarray ?
        i'm not sure if it is possible : every sum of subarray is small than 18 but NO equals.
        If none sum of the subarrays is 18, then there exists a sum that is the largest sum of these subarrays, say, 17. 
        The binary search returns the FIRST one that is feasible, so 17 will be returned instead.
        */
    }
	    
	    boolean isValid(long val, int[] nums, int m) {
	        int count = 1; // It is 1 by default, why?
	        int total = 0;
	        
	        for (int num : nums) {
	            total += num;
	            
	            if (total > val) {
	                total = num;
	                count++;
	                
	                if (count > m) {
	                    return false;
	                }
	            }
	        }
	        
	        return true;
	    }
	    
	    /**
	     * K-window Max-Sum
		Given a num array, find a window of size k, that has a maximum sum of the k entries.
		follow-up: Find three non-overlap windows of size k, that together has a maximum sum of the 3k entries, time complexity O(n^2)
	     */
	    int[] kWindowSum(int[] arr, int k) {
	    	int len = arr.length;
	    	if (arr == null || arr.length < k || k <= 0) {
	    		return null;
	    	}
	    	
	    	int[] res = new int[len - k + 1];

	    	for (int i = 0; i < k; i++) {
	    		res[0] += arr[i];
	    	}
	    	
	    	for (int i = k; i < len; i++) {
	    		res[i - k + 1] = res[i - k] - arr[i - k] + arr[i];
	    	}
	    	
	    	// If you want to get the largest sum, just sort the res array
	    	return res;
	    }
	    
	    /**
	     * 在一个array 里面找到 sum最大，长度为 k 的 subarray， 返回sum。 这题太简单应该没算分，但是还让我写了。 
	     * 第二个 题 找 sum最大，每个长度都是k 的三个subarray。 三个subarray不能有overlap。 举个栗子 1,2,1,2,6,7,5,1。k = 2。 
	     * 这个里面找到的就应该是[1,2], 1,[2,6],[7,5],1 同样返回和。 楼猪这题傻逼的写了个 N^3的解法。铁定跪了。回学校问了下大神，大神说dp， 我也明白dp怎么写了，O(N)。
	     * 
	     *  这个其实可以优化到O(n)时间。
			建从左端到每个下标的最大window数组，再建从右端到每个下标的最大window数组。
			再从左往右走一遍所有的size k window，将其和与上一步建的两个数组一起加起来。遍历一遍取最大值即可。
			
			有点类似于买卖股票3, 一左一右， 这里维护三个window
			
			数组分成3个连续小数组，目标是小数组的和最大化
	     */
	    public static int find(int[] arr, int k) {
	    	int res = Integer.MIN_VALUE;
	    	int len = arr.length;
	    	
	    	int leftSum = 0;
	    	int[] left = new int[len];
	    	for (int i = 0; i < len; i++) {
	    		leftSum += arr[i];
	    		if (i >= k) {
	    			leftSum -= arr[i - k];
	    		}
	    		if (i - k + 1 >= 0) {
	    			left[i] = Math.max(leftSum, i >= 1 ? left[i - 1] : 0); 
	    		}
	    	}
	    	
	    	int rightSum = 0;
	    	int[] right = new int[len];
	    	for (int i = len - 1; i >= 0; i--) {
	    		rightSum += arr[i];
	    		if (i + k < len) {
	    			rightSum -= arr[i + k];
	    		}
	    		if (i + k - len <= 0) {
	    			right[i] = Math.max(rightSum, i < len - 1 ? right[i + 1] : 0); 
	    		}
	    	}
	    	
	    	int midSum = 0;
	    	for (int i = k; i <= len - k - 1; i++) {
	    		midSum += arr[i];
	    		if (i - k >= k) {
	    			midSum -= arr[i - k];
	    		}
	    		
	    		if (i >= k + k - 1) {
	    			res = Math.max(res, midSum + left[i - k] + right[i + 1]);
	    		}
	    	}
	    	
	    	return res;
	    }
}
