package chapter8.frequent1;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Given an integer array, find a subarray with sum closest to zero. Return the indexes of the first number and last number.
 * @author Lei
 *
 */
public class SubArraySumClosest {

	public static void main(String[] args) {
		SubArraySumClosest s = new SubArraySumClosest();
		int[] nums = {-3, 1, 1, -3, 5};
		
		int[] res = s.subarraySumClosest2(nums);
		
		for (int i : res) {
			System.out.println(i);
		}
	}
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number 
     *          and the index of the last number
     */
	// Not working, see next method
    public ArrayList<Integer> subarraySumClosest(int[] nums) {
        int size = nums.length;
        ResultType[] sum = new ResultType[size];
        
        sum[0] = new ResultType(nums[0], 0);
        for (int i = 1; i < size; i++) {
            sum[i] = new ResultType(sum[i - 1].value + nums[i], i);
        }
        
        Arrays.sort(sum);
        ArrayList<Integer> res = new ArrayList<Integer>();
        
        // Find closest
        int min = Integer.MAX_VALUE;
        
        for (int i = 1; i < size; i++) {
            if (Math.abs(sum[i].value - sum[i - 1].value) < min) {
                res.add(0, Math.min(sum[i - 1].index, sum[i].index));
                res.add(1, Math.max(sum[i - 1].index, sum[i].index) - 1);
                min = Math.abs(sum[i].value - sum[i - 1].value);
            }
        }
        
        return res;
    }
    
    public int[] subarraySumClosest2(int[] nums) {
        int[] res = new int[2];
//        if (nums.length == 1) { // This is not necessary
//            res[0] = 0;
//            res[1] = 0;
//            return res;
//        }
        int size = nums.length;
        ResultType[] sum = new ResultType[size];
        
        int curSum = 0;
        for (int i = 0; i < size; i++) {
            curSum += nums[i];
            ResultType rt = new ResultType(curSum, i);
            sum[i] = rt;
            
            if (sum[i].value == 0) { // Better here
            	res[0] = 0;
                res[1] = i;
                return res;
            }
        }
        
        Arrays.sort(sum);
        
        ResultType left = sum[0]; // Not must be left or right, just 2 results
        ResultType right = sum[0];
        int min = Math.abs(sum[0].value);
        
        for (int i = 1; i < size; i++) {
            if (Math.abs(sum[i].value) < min) {
            	left = sum[i];
                right = sum[i];
                min = Math.abs(sum[i].value);
            }
            int diff = Math.abs(sum[i].value - sum[i - 1].value);
            if (diff < min) {
                left = sum[i - 1];
                right = sum[i];
                min = diff;
            }
        }
        
        if (left == right) {
        	res[0] = 0;
        	res[1] = left.index;
        } else {
        	res[0] = Math.min(left.index, right.index) + 1;
        	res[1] = Math.max(left.index, right.index);
        }
        
        return res;
    }
}

class ResultType implements Comparable<ResultType> {
    int value;
    int index;
    
    ResultType(int value, int index) {
        this.value = value;
        this.index = index;
    }
    
    public int compareTo(ResultType rt) {
        return this.value - rt.value;
    }
}
