package company.facebook.others;

import java.util.*;

/**
 * Created by weixwu on 7/11/2017.
 * I dont understand this question
 */
public class ReArrangeArray {
    public static void reArrage(int[] nums){
        Arrays.sort(nums);
        int minIndex = 0, maxIndex = nums.length - 1;
        int n = nums[nums.length - 1] + 1;
        for (int i = 0; i < nums.length; i++){
            if (i % 2 == 0){
                nums[i] += nums[maxIndex] % n * n;
            }
            else {
                nums[i] += nums[minIndex] % n * n;
            }
        }
        for (int i = 0; i < nums.length; i++){
            nums[i] /= n;
            System.out.print(nums[i] + "-");
        }
    }
    
    public static void main(String[] args) {
    	int[] nums = {1, 2, 4, 7, 8};
    	ReArrangeArray.reArrage(nums);
	}
}
