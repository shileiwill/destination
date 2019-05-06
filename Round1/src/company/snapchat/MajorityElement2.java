package company.snapchat;

import java.util.ArrayList;
import java.util.List;
/**
 * 229. Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.

Hint:

How many majority elements could it possibly have?
 * @author Lei
 *
 */
public class MajorityElement2 {
    public List<Integer> majorityElement(int[] nums) {
        // There can be at most 2 elements.
        int candidate1 = 0, count1 = 0;
        int candidate2 = 0, count2 = 0;
        
        for (int num : nums) {
            if (num == candidate1) {
                candidate1 = num;
                count1 += 1;
            } else if (num == candidate2) {
                candidate2 = num;
                count2 += 1;
            } else if (count1 == 0) { // Equals 0 must come after
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        
        // Find out the real ones, which are more than n / 3
        count1 = 0;
        count2 = 0;
        List<Integer> res = new ArrayList<Integer>();
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }
        
        if (count1 > nums.length / 3) {
            res.add(candidate1);
        }
        if (count2 > nums.length / 3) {
            res.add(candidate2);
        }
        
        return res;
    }
}