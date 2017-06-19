package company.facebook;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import chapter3.binaryTree.TreeNode;
/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

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
 */
public class SymmetricTree {

    public boolean isSymmetricRecursion(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        return helper(root.left, root.right);
    }
    
    boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        
        if (left == null || right == null) {
            return false;
        }
        
        if (left.val != right.val) {
            return false;
        }
        
        return helper(left.left, right.right) && helper(left.right, right.left);
    }

    public boolean isSymmetric(TreeNode root) {
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
