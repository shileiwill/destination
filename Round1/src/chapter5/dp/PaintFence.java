package chapter5.dp;
/*
 * 276. There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color. 

Return the total number of ways you can paint the fence. 

Note:
 n and k are non-negative integers. 

 */
public class PaintFence {
    public int numWays(int n, int k) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return k;
        }
        // 一直分两种情况, Initialization
        int diff = k * (k - 1); // 前两个post不同颜色
        int same = k; // 前两个post相同颜色
        for (int i = 2; i < n; i++) {// 当前post
            int temp = diff;
            diff = (same + diff) * (k - 1);
            same = temp;
        }
        
        return same + diff;
    }
    
    /*
     * The key to solve this problem is finding this relation.

f(n) = (k-1)(f(n-1)+f(n-2))

Assuming there are 3 posts, if the first one and the second one has the same color, then the third one has k-1 options. The first and second together has k options.
If the first and the second do not have same color, the total is k * (k-1), then the third one has k options. Therefore, f(3) = (k-1)*k + k*(k-1)*k = (k-1)(k+k*k)
     */
    public int numWays2(int n, int k) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return k;
        } else if (n == 2) {
            return k * k;
        }
        // 最开始两个
        int[] hash = {k, k * k, 0};
        for (int i = 2; i < n; i++) {// 当前post
            hash[2] = (hash[1] + hash[0]) * (k - 1);
            hash[0] = hash[1];
            hash[1] = hash[2];
        }
        
        return hash[2];
    }
}

/*
We divided it into two cases.

1.the last two posts have the same color, the number of ways to paint in this case is sameColorCounts.


2.the last two posts have different colors, and the number of ways in this case is diffColorCounts.


The reason why we have these two cases is that we can easily compute both of them, and that is all I do. 
When adding a new post, we can use the same color as the last one (if allowed) or different color. 
If we use different color, there're k-1 options, and the outcomes should belong to the diffColorCounts category. 
If we use same color, there's only one option, and we can only do this when the last two have different colors (which is the diffColorCounts). 
There we have our induction step.

*/