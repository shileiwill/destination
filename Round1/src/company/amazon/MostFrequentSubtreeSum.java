package company.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chapter3.binaryTree.TreeNode;

/**
 * 508. Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of 
 * all the node values formed by the subtree rooted at that node (including the node itself). 
 * So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order.
Examples 2
Input:

  5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.
Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 */
public class MostFrequentSubtreeSum {
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        
        // sum, frequency
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        getSum(map, root);
        
        List<Integer>[] bucket = new List[maxCount + 1];
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            
            if (bucket[value] == null) {
                bucket[value] = new ArrayList<Integer>();
            }
            bucket[value].add(key);
        }
        
        for (int i = bucket.length - 1; i >= 0; i--) {
            List<Integer> list = bucket[i];
            
            if (list == null) {
                continue;
            }
            
            int[] res = new int[list.size()];
            for (int j = 0; j < list.size(); j++) {
                res[j] = list.get(j);
            }
            
            return res;
        }
        
        return null;
    }
    
    int maxCount = Integer.MIN_VALUE;
    int getSum(Map<Integer, Integer> map, TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = getSum(map, root.left);
        int right = getSum(map, root.right);
        int sum = left + right + root.val;
        
        if (!map.containsKey(sum)) {
            map.put(sum, 1);
        } else {
            map.put(sum, map.get(sum) + 1);
        }
        maxCount = Math.max(maxCount, map.get(sum));
        
        return sum;
    }
}
