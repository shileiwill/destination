package company.amazon;

import java.util.Arrays;

import sun.applet.Main;

/**
 * 280. Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 */
public class WiggleSort {

    public void wiggleSort2(int[] nums) {
        int[] res = new int[nums.length];
        
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        int index = 0;
        
        while (left <= right) {
            if (left == right) {
                res[index] = nums[left];
                break;
            }
            res[index++] = nums[left++];
            res[index++] = nums[right--];
        }
        
        for (int i = 0; i < res.length; i++) {
            nums[i] = res[i];
        }
    }
    
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if ((i % 2 == 1 && nums[i] < nums[i - 1]) || (i % 2 == 0 && nums[i] > nums[i - 1])) {
                swap(nums, i, i - 1);
            }
        }
    }
    
    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 50. wiggle sort 2 
		(给一个数组，里面有负数和正数，让输出负数／正数间隔的数组，我用了两个pointer从头到尾／从尾到头同时扫一遍。估计这题没法做成in-place，
		最后没时间写完code，只是大概说了一下，最后面试官觉得可以work)
		
		Negative - Positive - Negative
     */
    static void sort(int[] arr) {
    	int pos1 = 0;
    	int pos2 = arr.length - 1;
    	int index = 0;
    	int[] res = new int[arr.length];
    	
    	while (pos1 < arr.length && pos2 >= 0) {
    		while (pos1 < arr.length && arr[pos1] > 0) {
    			pos1++;
    		}
    		
    		if (pos1 < arr.length) {
    			res[index++] = arr[pos1++];
    		}
    		
    		while (pos2 >= 0 && arr[pos2] < 0) {
    			pos2--;
    		}
    		
    		if (pos1 >= 0) {
    			res[index++] = arr[pos2--];
    		}
    	}
    	
    	for (int val : res) {
    		System.out.print(val + "==");
    	}
    }
    
    public static void main(String[] args) {
		int[] arr = {1, 3, -4, -5, 4, -7};
		sort(arr);
	}
}
