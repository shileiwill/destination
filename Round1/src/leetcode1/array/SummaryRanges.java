package leetcode1.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 228. Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */
public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<String>();
        
        if (nums.length < 1) {
            return res;
        }
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            
            while (i + 1 < nums.length && nums[i + 1] == nums[i] + 1) {
                i++; // The same i with for loop
            }
            
            if (num == nums[i]) {
                res.add(num + "");
            } else {
                String str = num + "->" + nums[i];
                res.add(str);
            }
        }
        
        return res;
    }
}
