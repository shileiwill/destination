package leetcode13.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import chapter3.binaryTree.TreeNode;

/**
 * 101. Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        return root == null || helper(root.left, root.right);
    }
    
    boolean helper(TreeNode n1, TreeNode n2) {
        if (n1 == null || n2 == null) {
            return n1 == n2;
        }
        
        if (n1.val != n2.val) {
            return false;
        }
        
        return helper(n1.left, n2.right) && helper(n1.right, n2.left);
    }
    public boolean isSymmetric2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<String> list = new ArrayList<String>();
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    list.add("Null");
                } else {
                    list.add(node.val + "");
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            if (!check(list)) {
                return false;
            }
        }
        
        return true;
    }
    
    boolean check(List<String> list) {
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
