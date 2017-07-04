package company.linkedin;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 364. Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:
Given the list [[1,1],2,[1,1]], return 8. (four 1's at depth 1, one 2 at depth 2)

Example 2:
Given the list [1,[4,[6]]], return 17. (one 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17)
 */
public class NestedListWeightSum2 {
    public int depthSumInverse2PassDFS(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        int depth = getDepth(nestedList);
        return dfs(nestedList, depth);
    }
    
    int dfs(List<NestedInteger> list, int level) {
        int res = 0;
        for (NestedInteger ni : list) {
            if (ni.isInteger()) {
                res += ni.getInteger() * level;
            } else {
                res += dfs(ni.getList(), level - 1);
            }
        }
        return res;
    }
    
    int getDepth(List<NestedInteger> list) {
        int max = 0;
        
        for (NestedInteger ni : list) {
            if (ni.isInteger()) {
                max = Math.max(max, 1);
            } else {
                max = Math.max(max, getDepth(ni.getList()) + 1);
            }
        }
        
        return max;
    }
    
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        
        int total = 0;
        int prev = 0;
        Queue<NestedInteger> queue = new LinkedList<NestedInteger>(nestedList);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;
            
            for (int i = 0; i < size; i++) {
                NestedInteger ni = queue.poll();
                if (ni.isInteger()) {
                    levelSum += ni.getInteger();        
                } else {
                    queue.addAll(ni.getList()); // Same to offer()
                }
            }
            
            prev += levelSum;
            total += prev; // The secret is this levelSum will be added many times
        }
        
        return total;
    }
}
