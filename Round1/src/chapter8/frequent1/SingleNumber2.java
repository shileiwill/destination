package chapter8.frequent1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 137. Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * @author Lei
 *
 */
public class SingleNumber2 {
    // & 全都为1才是1
    // | 一个为1就是1
    // ^ 不一样就是1
    public int singleNumber(int[] nums) {
        int[] bits = new int[32]; // 32 because integer has maximum 32 bits
        int res = 0;
        
        for (int i = 0; i < 32; i++) { // Every bit in integer
            for (int j = 0; j < nums.length; j++) { // Every number in array
                bits[i] += (nums[j] >> i & 1); // Move ith bit to the very end, and mask other bits as 0. Here 1 is 0000...00001
                bits[i] %= 3; // Mode 3 every time. Since same number will appear at most 3 times, bits[i] <= 3
            }
            res |= (bits[i] << i); // Move ith bit back to its original place and construct result bit by bit
        }
        
        return res;
    }
    public int singleNumber2(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).add(nums[i]);
            } else {
                map.put(nums[i], new ArrayList<Integer>());
            }
        }
        
        for (int key : map.keySet()) {
            List<Integer> value = map.get(key);
            if (value.size() == 0) {
                return key;
            }
        }
        
        return -1;
    }
}
