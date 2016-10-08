package chapter5.dp;
/**
 * 70. You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top? (Follow up, what if you can climb 2 or 3 steps?)
 * @author Lei
 *
 */
public class ClimbStairs {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        // N steps. Index 0 is invalid
        int[] hash = new int[n + 1]; 
        
        hash[1] = 1;
        hash[2] = 2; // Either 2 one-steps or 1 two-step
        
        for (int i = 3; i <= n; i++) { // 这叫递推 Memorized ..
            hash[i] = hash[i - 1] + hash[i - 2];
        }
        
        return hash[n];
    }
}
