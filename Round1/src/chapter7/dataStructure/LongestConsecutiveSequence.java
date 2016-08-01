package chapter7.dataStructure;

import java.util.HashSet;
import java.util.Set;
/**
 * 128. Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
 * @author Lei
 *
 */
public class LongestConsecutiveSequence {

	public static void main(String[] args) {
		int[] nums = {1, 3, 2, 2, 4, 34, 35, 2, 23};
	}

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        
        int max = 0;
        for (int num : nums) {
            
            int down = num - 1; // Going down
            while (set.contains(down)) {
                set.remove(down); // This will speed up
                down--;
            }
            
            int up = num + 1; // Going up
            while (set.contains(up)) {
                set.remove(up);
                up++;
            }
            
            max = Math.max(max, up - down -1);
        }
        
        return max;
    }
}
