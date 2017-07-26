package company.facebook.others;

/**
 * Created by weixwu on 7/11/2017.
 * Find the max sum of continuous subarray which has at least 2 elements
 * 这是一个DP问题啊， 递推公式的关键点是， 必须包含当前点
 */
public class AtLeastTwoMaxSubarray {
    public int maxSubarray(int[] nums){
        int max = nums[0];
        int maxSoFar = Integer.MIN_VALUE;
        
        for (int i = 1; i < nums.length; i++){
            max = Math.max(nums[i - 1] + nums[i], max + nums[i]);
            maxSoFar = Math.max(max, maxSoFar);
        }
        
        return maxSoFar;
    }
    
    public int maxSubarrayDPWithArray(int[] nums){
        int max = Integer.MIN_VALUE;
        int[] hash = new int[nums.length];
        hash[0] = nums[0]; // Itself, only 1 element, not valid, but just as a starter
        
        for (int i = 1; i < nums.length; i++){
        	// Either 跟前边的一个item凑两个元素，或者跟前边的potential一大堆，凑。 必须保证包含当前item
        	hash[i] = Math.max(nums[i - 1] + nums[i], hash[i - 1] + nums[i]);
            max = Math.max(max, hash[i]);
        }
        
        return max;
    }
    
    public int maxSubarrayMyStyle(int[] arr){
    	int len = arr.length;
    	int[] sum = new int[len + 1];
    	sum[0] = 0;
    	
    	for (int i = 1; i <= len; i++) {
    		sum[i] = sum[i - 1] + arr[i - 1];
    	} // 因为可以有负数，所以这个sum array不保证是递增的
    	
    	int res = Integer.MIN_VALUE;
    	
    	for (int i = 0; i <= len; i++) {
    		for (int j = i + 2; j <= len; j++) {
    			res = Math.max(res, sum[j] - sum[i]);
    		}
    	}
    	
    	return res;
    }

    public static void main(String args[]){
        AtLeastTwoMaxSubarray ms = new AtLeastTwoMaxSubarray();
        System.out.print(ms.maxSubarrayDPWithArray(new int[]{-2, 1, 1, -1, 5, -2, -4, -1}));
    }
}
