package chapter5.dp;
/**
 * 45. Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 * @author Lei
 *
 */
public class JumpGame2 {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] hash = new int[n];
        
        hash[0] = 0;
        // This iteration is not necessary
        // for (int i = 1; i < n; i++) {
        //     hash[i] = Integer.MAX_VALUE;
        // }
        
        for (int i = 1; i < n; i++) {
            hash[i] = Integer.MAX_VALUE; // Better to iterate here
            for (int j = 0; j < i; j++) {
                if (hash[j] != Integer.MAX_VALUE && j + nums[j] >= i) { //这个hash[j] != Integer.MAX_VALUE应该不需要， 这个保证j reachable
                    hash[i] = Math.min(hash[i], hash[j] + 1);
                    // Based on experience, the earlier, the shorter.
                    // break; This is an optimization, but not in alignment with DP.
                }
            }
        }
                
        
        return hash[n - 1];
    }
}
