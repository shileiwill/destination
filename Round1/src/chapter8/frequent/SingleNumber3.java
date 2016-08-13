package chapter8.frequent;

import java.util.ArrayList;
import java.util.List;
/**
 * 260. Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.

For example:

Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].

Note:
The order of the result is not important. So in the above example, [5, 3] is also correct.
Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 * @author Lei
 *
 */
public class SingleNumber3 {
    public int[] singleNumber(int[] nums) {
        int xor = nums[0];
        for (int i = 1; i < nums.length; i++) {
            xor = xor ^ nums[i];
        } // After all XORs, we will find the bits which are different between A and B
        
        // We want to separate A and B into 2 groups, so we can use XOR again
        int lastDigit = xor - (xor & (xor - 1)); // This is to find the last different bit. One is enough to differentiate A, B
        // Eg. lastDigit = 00000000000000010000000
        int group1 = 0, group2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & lastDigit) == 0) {
                group1 ^= nums[i];
            } else {
                group2 ^= nums[i];
            }
        }
        
        int[] res = {group1, group2};
        
        return res;
    }
    public int[] singleNumber2(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            int item = nums[i];
            
            if (list.contains(item)) {
                list.remove(new Integer(item));
            } else {
                list.add(item);
            }
        }
        
        int[] res = {list.get(0), list.get(1)};
        return res;
    }
}