package company.facebook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 398. Given an array of integers with possible duplicates, randomly output the index of a given target number. 
 * You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);
 */
public class RandomPickIndex {
	Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
    Random ran = new Random();
    
    // O(N) space, O(N) search worse case
    // public Solution(int[] nums) {
    //     for (int i = 0; i < nums.length; i++) {
    //         if (!map.containsKey(nums[i])) {
    //             map.put(nums[i], new ArrayList<Integer>());
    //         }
    //         map.get(nums[i]).add(i);
    //     }    
    // }
    
    public int pick2(int target) {
        List<Integer> list = map.get(target);
        int ranIndex = ran.nextInt(list.size());
        return list.get(ranIndex);
    }
    
    // Another solution from discussion board
    // O(1) space, O(N) pick
    int[] nums;
    public RandomPickIndex(int[] nums) {
        this.nums = nums;  
    }
    
    public int pick(int target) {
        int res = -1;
        int count = 0; // 每一个target出现的概率都是一样的
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (ran.nextInt(++count) == 0) { // The probability of 0 is 1/N
                    res = i;
                }
            }
        }
        
        return res;
    }
}
