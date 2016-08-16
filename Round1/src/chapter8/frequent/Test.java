package chapter8.frequent;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test m3 = new Test();
		int[] arr = {-1,-2,-3,-100,-1,-50};
		int[] nums = {1,2,3};
		int res = m3.maxSubArray(nums, 1);
		
		System.out.println(res);
	}
    public int maxSubArray(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    	for (int j = 0; j < sum.length; j++) {
    		System.out.print(sum[j] + " - ");
    	}
    	System.out.println();
    	
        int[][] hash = new int[nums.length + 1][k + 1];
        
        for (int i = 0; i <= nums.length; i++) {
            hash[i][0] = 0;
        }

        for (int i = 1; i <= k; i++) {
            hash[0][i] = Integer.MIN_VALUE;
        }   
        
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= k; j++) {
                if (i == j) {
                    hash[i][j] = sum[i];
                    continue;
                }
                if (j > i) {
                    hash[i][j] = Integer.MIN_VALUE;
                    continue;
                }
                hash[i][j] = Integer.MIN_VALUE;
                for (int x = 0; x < i; x++) {
                    hash[i][j] = Math.max(hash[i][j], hash[x][j - 1] + getMaxBetween(x+1, i, sum, nums));
                }
            }
        }
        
        for (int i = 0; i < hash.length; i++) {
        	for (int j = 0; j < hash[i].length; j++) {
        		System.out.print(hash[i][j] + " - ");
        	}
        	System.out.println();
        }
        
        int max = Integer.MIN_VALUE;
        for (int i = k; i < hash.length; i++) {
            max = Math.max(max, hash[i][k]);
        }
        
        return max;
    }
    
    int getMaxBetween(int start, int end, int[] sum, int[] nums) {
        if (start == end) {
            return nums[start - 1];
        }
        if (start > end) {
            return Integer.MIN_VALUE;
        }
        
        int minSum = sum[start];
        if (start == 1) {
        	minSum = Math.min(minSum, 0);
        }
        int maxProfit = Integer.MIN_VALUE;
        for (int i = start + 1; i <= end; i++) {
            maxProfit = Math.max(sum[i] - minSum, maxProfit);
            minSum = Math.min(minSum, sum[i]);
        }
        
        return maxProfit;
    }
}
