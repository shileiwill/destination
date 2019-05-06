package company.snapchat;
/**
 * 55. Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
 * @author Lei
 *
 */
public class JumpGame {
    public boolean canJump(int[] A) {
        boolean[] can = new boolean[A.length];
        can[0] = true;
        
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) { // j is Every stage before i
                if (can[j] && j + A[j] >= i) { // if j is reachable and we can reach i from j
                    can[i] = true;
                    break; // Stop immediately
                }
            }
        }
        
        return can[A.length - 1];
    }

    public boolean canJump2(int[] nums) {
        int n = nums.length;
        boolean[] hash = new boolean[n];
        
        if (nums.length == 1) {
            return true;
        }
        if (nums[0] == 0) {
            return false;
        }
        hash[0] = true;
        
        for (int i = 0; i < n; i++) {
            if (hash[i]) { // Make sure the stand/jump point is reachable
                int steps = nums[i];
                for (int j = 1; j <= steps; j++) { // Make all stages I can reach to true
                    if (i + j < n) {
                        hash[i + j] = true; // This is not efficient as (i + j) may duplicate many many times
                    }
                }
            }
        }
        
        return hash[n - 1];
    }
    
	// This version is from program creek
    public boolean canJump0(int[] nums) {
        if (nums.length <= 1) {
            return true;
        }
        
        int max = nums[0]; //max stands for the largest index that can be reached.
        
        for (int i = 0; i < nums.length; i++) {
            if (max == i && nums[i] == 0) {
                return false;
            }
            
            max = Math.max(max, i + nums[i]);
            
            if (max >= nums.length - 1) {
                return true;
            }
        }
        
        return false;
    }
    
    // Greedy. Just take a look
    public boolean canJump3(int[] A) {
        // think it as merging n intervals
        if (A == null || A.length == 0) {
            return false;
        }
        int farthest = A[0];
        for (int i = 1; i < A.length; i++) { // i <= farthest表示这个点reachable
            if (i <= farthest && A[i] + i >= farthest) {
                farthest = A[i] + i;
            }
        }
        return farthest >= A.length - 1;
    }
    
    // My new version of greedy
    public boolean canJumpGreedy(int[] A) {
        int maxReach = 0;
        
        for (int i = 0; i < A.length; i++) {
            if (i <= maxReach) {
                maxReach = Math.max(maxReach, i + A[i]);
            }
        }
        
        return maxReach >= A.length - 1;
    }
    
    // Another version
    public boolean canJump4(int[] A) {
        boolean[] hash = new boolean[A.length];
        hash[0] = true;
        for (int i = 0; i < A.length; i++) {
            int steps = A[i];
            for (int s = 1; s <= steps; s++) {
                if (hash[i] && i + s < A.length) {
                    hash[i + s] = true;
                }
            }
        }
    
        return hash[A.length - 1];
    }
}
