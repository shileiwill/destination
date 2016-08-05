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
		
		ArrayList<Integer> res = s.subarraySumClosest(nums);
		
		for (int i : res) {
			System.out.println(i);
		}
	}
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number 
     *          and the index of the last number
     */
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
