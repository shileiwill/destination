package leetcode13.tree;

import java.util.ArrayList;
import java.util.List;

import chapter3.binaryTree.TreeNode;

/**
 * 95. Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 */
public class UniqueBinarySearchTrees2 {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<TreeNode>();
        }
        return helper(1, n);
    }
    
    List<TreeNode> helper(int start, int end) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        
        if (start > end) {
            list.add(null);
            return list;
        }
        // This snippet is actually not necessary. The following for loop can cover this
        if (start == end) {
            list.add(new TreeNode(start));
            return list;
        }
        
        for (int i = start; i <= end; i++) { // All potential roots
            List<TreeNode> left = helper(start, i - 1);
            List<TreeNode> right = helper(i + 1, end);
            
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode node = new TreeNode(i); // i is the root
                    node.left = l;
                    node.right = r;
                    list.add(node);
                }
            }
        }
        
        return list;
    }
}
