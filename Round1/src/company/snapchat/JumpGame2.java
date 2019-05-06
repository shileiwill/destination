package company.snapchat;
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
                if (hash[j] != Integer.MAX_VALUE && j + nums[j] >= i) { //这个hash[j] != Integer.MAX_VALUE应该不需要， 这个保证j reachable (NEED MAX?)
                    hash[i] = Math.min(hash[i], hash[j] + 1);
                    // Based on experience, the earlier, the shorter.
                    // break; This is an optimization, but not in alignment with DP.
                }
            }
        }
                
        
        return hash[n - 1];
    }
    
    // My another version of DP
    public int jumpDP(int[] A) {

        int[] hash = new int[A.length]; 
        hash[0] = 0;
        
        for (int i = 1; i < A.length; i++) {
            hash[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (j + A[j] >= i) {
                    hash[i] = Math.min(hash[i], hash[j] + 1);
                }
            }
        }
        
        return hash[A.length - 1];
    }
    
    // From program creek
    public int jump0(int[] nums) {
        int maxReach = 0;
        int lastReach = 0;
        int step = 0;
        
        for (int i = 0; i <= maxReach && i < nums.length; i++) {
            if (i > lastReach) { //when last jump can not reach current i, increase the step by 1
                step++;
                lastReach = maxReach;
            }
            
            maxReach = Math.max(maxReach, i + nums[i]); 
        }
        
        if (maxReach < nums.length - 1) {
            return 0; // Cant reach the end
        }
        
        return step;
    }
}
