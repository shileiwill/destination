package company.amazon;
/**
 * 
You are given a sequence of black and white horses, and a set of K stables numbered 1 to K. You have to accommodate the horses into the stables in such a way that the following conditions are satisfied:

You fill the horses into the stables preserving the relative order of horses. For instance, you cannot put horse 1 into stable 2 and horse 2 into stable 1. You have to preserve the ordering of the horses.
No stable should be empty and no horse should be left unaccommodated.
Take the product (number of white horses * number of black horses) for each stable and take the sum of all these products. This value should be the minimum among all possible accommodation arrangements
Example:


Input: {WWWB} , K = 2
Output: 0

Explanation:
We have 3 choices {W, WWB}, {WW, WB}, {WWW, B}
for first choice we will get 1*0 + 2*1 = 2.
for second choice we will get 2*0 + 1*1 = 1.
for third choice we will get 3*0 + 0*1 = 0.

Of the 3 choices, the third choice is the best option. 

If a solution is not possible, then return -1
 */
public class Arrange2 {
    public int arrange(String a, int b) {
        if(a.length() < b) return -1;
        int[][] hash = new int[a.length() + 1][b + 1];
        
        for (int i = 1; i <= a.length(); i++) {
            hash[i][1] = product(a, 0, i - 1);
        }
        
        for (int stableCount = 2; stableCount <= b; stableCount++) {
            for (int horseCount = stableCount; horseCount <= a.length(); horseCount++) {
                int min = Integer.MAX_VALUE;
                for (int k = stableCount - 1; k <= horseCount - 1; k++) { // k is possible horse count
                    hash[horseCount][stableCount] = Math.min(min, hash[k][stableCount - 1] + product(a, k, horseCount - 1));
                
                    min = hash[horseCount][stableCount];
                }
            }
        }
        
        return hash[a.length()][b];
    }
    
    int product(String s, int left, int right) {
        int w = 0;
        int b = 0;
        
        for (int i = left; i <= right; i++) {
            if (s.charAt(i) == 'W') {
                w++;
            } else {
                b++;
            }     
        }
        
        return w * b;
    }
}
