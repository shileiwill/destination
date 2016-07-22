package chapter3.binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InorderTraversal {
	
	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		node1.right = node2;
		node2.left = node3;
		
		InorderTraversal it = new InorderTraversal();
		it.inorderTraversal(node1);
		
	}
	
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        if (root == null) {
            return res;
        }
        
        // This comment is not correct
        // cur is always right children. Stack contains only left children.
        // In this way, we can differentiate nodes. 
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            res.add(node.val);
            cur = node.right;
        }  
        
        return res;
    }
    
    public List<Integer> inorderTraversalDC(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        
        if (root == null) {
            return res;
        }
        List<Integer> left = inorderTraversal(root.left);
        res.addAll(left);
        
        res.add(root.val);
        List<Integer> right = inorderTraversal(root.right);
        res.addAll(right);
        
        
        return res;
    }
        
    public List<Integer> inorderTraversalRecursion(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        helper(res, root);
        return res;
    }
    
    void helper(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        helper(res, root.left);
        res.add(root.val);
        helper(res, root.right);
    }
}
