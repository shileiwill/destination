package chapter3.binaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * 129. Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
 * @author Lei
 *
 */
public class SumRootToLeaf {
	
    public int sumNumbers3(TreeNode root) {
        return dfs3(root, 0);
    }
    
    int dfs3(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }
        
        sum = sum * 10 + node.val;
        
        if (node.left == null && node.right == null) {
            return sum;    
        }
        
        // Sum sum, add add
        return dfs3(node.left, sum) + dfs3(node.right, sum);
    }
    
	// This solution is very similar to Path Sum 2, but We dont need to get all the list/stringbuilder
    public int sumNumbers(TreeNode root) {
        List<StringBuilder> res = new ArrayList<StringBuilder>();
        StringBuilder sb = new StringBuilder();
        
        helper(res, sb, root);
        
        int sum = 0;
        // Iterate all the SB in result, and sum up
        for (int i = 0; i < res.size(); i++) {
            StringBuilder path = res.get(i);
            int pathVal = Integer.valueOf(path.toString());
            sum += pathVal;
        }
        
        return sum;
    }
    
    void helper(List<StringBuilder> res, StringBuilder sb, TreeNode node) {
        if (node == null) {
            return;
        }
        
        // Leaf node, add to res
        if (node.left == null && node.right == null) {
            // Must here
            sb.append(node.val);
            res.add(new StringBuilder(sb));
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
        
        sb.append(node.val);
        helper(res, sb, node.left);
        helper(res, sb, node.right);
        sb.deleteCharAt(sb.length() - 1);
    }
}
