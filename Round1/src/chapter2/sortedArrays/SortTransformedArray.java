package chapter2.sortedArrays;

import java.util.Arrays;

public class SortTransformedArray {

	public static void main(String[] args) {
		SortTransformedArray sta = new SortTransformedArray();
		int[] nums = {-4, -2, 2, 4};
		sta.sortTransformedArray(nums, 1, 3, 5);
	}

    public int[] sortTransformedArrayBruceForce(int[] nums, int a, int b, int c) {
        for (int i = 0; i < nums.length; i++) {
            int res = nums[i] * nums[i] * a + b * nums[i] + c;
            nums[i] = res;
        }
        
        Arrays.sort(nums);
        
        return nums;
    }
    
    //if a >= 0, the min value is at its vertex. So our our sorting should goes from two end points towards the vertex, high to low.
    //if a < 0, the max value is at its vertex. So our sort goes the opposite way.
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if (nums.length == 0) {
            return nums;
        }
        int[] res = new int[nums.length];
        int i = (a >= 0) ? nums.length - 1 : 0;
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int leftVal = getNum(nums[left], a, b, c);
            int rightVal = getNum(nums[right], a, b, c);
            
            if (a >= 0) {
                if (leftVal >= rightVal) {
                    res[i--] = leftVal;
                    left++;
                } else {
                    res[i--] = rightVal;
                    right--;
                }
            } else {
                if (leftVal >= rightVal) {
                    res[i++] = rightVal;
                    right--;
                } else {
                    res[i++] = leftVal;
                    left++;
                }
            }            
        }
        
        return res;
    }
    
    int getNum(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}
