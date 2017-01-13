package leetcode10.sort;

import java.util.Arrays;

/**
 * 164. Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 */
public class MaximumGap {
    public int maximumGapBruteForce(int[] nums) {
        Arrays.sort(nums);
        
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            res = Math.max(res, nums[i] - nums[i - 1]);
        }
        
        return res;
    }
    
    /*
    Suppose there are N elements in the array, the min value is min and the max value is max. Then the maximum gap will be no smaller than ceiling[(max - min ) / (N - 1)].

Let gap = ceiling[(max - min ) / (N - 1)]. We divide all numbers in the array into n-1 buckets, where k-th bucket contains all numbers in [min + (k-1)gap, min + k*gap). Since there are n-2 numbers that are not equal min or max and there are n-1 buckets, at least one of the buckets are empty. We only need to store the largest number and the smallest number in each bucket.

After we put all the numbers into the buckets. We can scan the buckets sequentially and get the max gap.
    */
    public int maximumGapBucketSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        int gap = (int)Math.ceil(((double)(max - min)) / (nums.length - 1));
        
        int[] bucketsMAX = new int[nums.length - 1];
        int[] bucketsMIN = new int[nums.length - 1];
        
        Arrays.fill(bucketsMAX, Integer.MIN_VALUE);
        Arrays.fill(bucketsMIN, Integer.MAX_VALUE);
        
        for (int num : nums) {
            if (num == min || num == max) {
                continue;
            }
            int index = (num - min) / gap;
            bucketsMAX[index] = Math.max(bucketsMAX[index], num);
            bucketsMIN[index] = Math.min(bucketsMIN[index], num);
        }
        
        int maxGap = Integer.MIN_VALUE;
        int previous = min;
        for (int i = 0; i < nums.length - 1; i++) {
            if (bucketsMIN[i] == Integer.MAX_VALUE) { // bucketsMAX[i] == Integer.MIN_VALUE is also fine
                continue;
            }
            maxGap = Math.max(maxGap, bucketsMIN[i] - previous);
            previous = bucketsMAX[i];
        }
        
        maxGap = Math.max(maxGap, max - previous);
        return maxGap;
    }
    
    // https://www.cs.usfca.edu/~galles/visualization/RadixSort.html
    // https://discuss.leetcode.com/topic/22221/radix-sort-solution-in-java-with-explanation/2
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        
        int[] helper = new int[nums.length];
        
        int exp = 1;
        while (max / exp > 0) {
            int[] count = new int[10];
            
            for (int i = 0; i < nums.length; i++) {
                int digit = (nums[i] / exp) % 10;
                count[digit]++;
            }
            
            for (int i = 1; i < count.length; i++) { // To determine the following index/position
                count[i] += count[i - 1];
            }
            
            for (int i = nums.length - 1; i >= 0; i--) { // From the end
                int digit = (nums[i] / exp) % 10;
                helper[--count[digit]] = nums[i];
            }
            
            for (int i = 0; i < nums.length; i++) {
                nums[i] = helper[i];
            }
            
            exp *= 10;
        }
        
        int maxGap = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            maxGap = Math.max(maxGap, nums[i] - nums[i - 1]);
        }
        
        return maxGap;
    }
}