package chapter8.frequent1;

import java.util.ArrayList;
import java.util.List;
/**
 * 136. Given an array of integers, every element appears twice except for one. Find that single one.
 * @author Lei
 *
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        // XOR 异或 相同的等于0， 不同的等于1 == 不进位加法
        // a ^ a = 0 信息抵消
        // a ^ 0 = a
        int item = nums[0];
        for (int i = 1; i < nums.length; i++) {
            item = item ^ nums[i];
        }
        
        return item;
    }
    public int singleNumber2(int[] nums) {
        List<Integer> list = new ArrayList<Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            int item = nums[i];
            
            if (list.contains(item)) {
                list.remove(new Integer(item));
            } else {
                list.add(item);
            }
        }
        
        return list.get(0);
    }
}