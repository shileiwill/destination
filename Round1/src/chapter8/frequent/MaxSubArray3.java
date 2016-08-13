package chapter8.frequent;

// This is not really working, edge cases issue 难题
public class MaxSubArray3 {
	
	public static void main(String[] args) {
		MaxSubArray3 m3 = new MaxSubArray3();
		int[] arr = {-1,-2,-3,-100,-1,-50};
		int[] nums = {1,2,3};
		int res = m3.maxSubArray(arr, 2);
		
		System.out.println(res);
	}
    /**
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */
    public int maxSubArray(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // Build a sum array
        int[] sum = new int[nums.length + 1];
        
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        
    	for (int j = 0; j < sum.length; j++) {
    		System.out.print(sum[j] + " - ");
    	}
    	System.out.println();
    	
        // 前i个数，取出j个subarray 能得到的最大sum
        int[][] hash = new int[sum.length + 1][k + 1];
        
        for (int i = 0; i <= sum.length; i++) {
            hash[i][0] = 0;
        }
        
        for (int i = 1; i <= k; i++) {
            hash[0][i] = Integer.MIN_VALUE;
        }
        
        // 从前i个数中取出i个来, 就是把当前的sum给取出来。为了避免 < 0
        for (int i = 1; i < sum.length && i <= k; i++) {
        	hash[i][i] = sum[i];
        }
        
        for (int i = 1; i <= sum.length; i++) {
            for (int j = 1; j <= k; j++) {
            	if (j > i) {
            		hash[i][j] = Integer.MIN_VALUE;
            		continue;
            	}
                for (int x = 1; x < i; x++) {
                    hash[i][j] = Math.max(hash[i][j], hash[x][j - 1] + getMaxBetween(x + 1, i, sum));
                }
            }
        }
        
        for (int i = 0; i < hash.length; i++) {
        	for (int j = 0; j < hash[i].length; j++) {
        		System.out.print(hash[i][j] + " - ");
        	}
        	System.out.println();
        }
        
        // Still need to go through last line to figure our the max profit
        int max = 0;
        for (int j = 0; j < hash[hash.length - 1].length; j++) {
        	max = Math.max(hash[hash.length - 1][j], max);
        }
        return max;
        
    }
    
    int getMaxBetween(int start, int end, int[] sum) {
        if (start == end && start > 1) { // 只能从一个数中找出最大，就是他自己了
            return sum[start - 1] - sum[start - 2];
        }
        
        int minPrice = sum[start - 1];
        int maxProfit = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            maxProfit = Math.max(sum[i] - minPrice, maxProfit);
            minPrice = Math.min(minPrice, sum[i]);
        }
        
        return maxProfit;
    }
}
