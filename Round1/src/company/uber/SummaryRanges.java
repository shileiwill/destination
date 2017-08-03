package company.uber;

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
        
        int start = nums[0];
        int end = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == end + 1) {
                end = nums[i];
            } else {
                if (start == end) {
                    res.add(start + "");
                } else {
                    res.add(start + "->" + end);
                }
                
                start = nums[i];
                end = nums[i];
            }
        }
        
        if (start == end) {
            res.add(start + "");
        } else {
            res.add(start + "->" + end);
        }
        
        return res;
    }
}
