package leetcode1.array;

import java.util.ArrayList;
import java.util.List;
/**
 * 163. Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
 */
public class FindMissingRange {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<String>();
        
        long prev = lower - 1;
        for (int i = 0; i <= nums.length; i++) {
            long cur = (i == nums.length) ? upper + 1 : nums[i];
            if (cur - prev >= 2) {
                res.add(getRange(prev + 1, cur - 1));
            }
            
            prev = cur;
        }
        
        return res;
    }
    
    String getRange(long start, long end) {
        return start == end ? start + "" : start + "->" + end;
    }
}
