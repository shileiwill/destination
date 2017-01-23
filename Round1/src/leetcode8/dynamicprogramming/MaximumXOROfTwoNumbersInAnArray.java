package leetcode8.dynamicprogramming;

import java.util.HashSet;
import java.util.Set;

/**
 * 421. Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.
 */
public class MaximumXOROfTwoNumbersInAnArray {
    // https://discuss.leetcode.com/topic/63207/java-o-n-solution-using-trie/2
    public int findMaximumXOR2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Trie root = new Trie();
        
        for (int num : nums) {
            Trie curNode = root;
            
            for (int i = 31; i >= 0; i--) {
                int curBit = (num >>> i) & 1;
                if (curNode.children[curBit] == null) {
                    curNode.children[curBit] = new Trie();
                }
                curNode = curNode.children[curBit];
            }
        }
        
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            Trie curNode = root;
            int curSum = 0;
            
            for (int i = 31; i >= 0; i--) {
                int curBit = (num >>> i) & 1;
                if (curNode.children[curBit ^ 1] != null) { // If these 2 bits are differnt, very good, accumulate!
                    curSum += (1 << i);
                    curNode = curNode.children[curBit ^ 1];
                } else {
                    curNode = curNode.children[curBit];
                }
            }
            
            max = Math.max(max, curSum);
        }
        
        return max;
    }

    class Trie {
        Trie[] children;
        public Trie() {
            children = new Trie[2];
        }
    }
    
    // https://discuss.leetcode.com/topic/63213/java-o-n-solution-using-bit-manipulation-and-hashmap/8
    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        
        for (int i = 31; i >= 0; i--) {
            mask = mask | (1 << i);
            
            Set<Integer> set = new HashSet<Integer>();
            for (int num : nums) {
                set.add(mask & num);
            }
            
            int tmp = max | (1 << i);
            for (int prefix : set) {
                if (set.contains(prefix ^ tmp)) {
                    max = tmp;
                    break;
                }
            }
        }
        
        return max;
    }
}
