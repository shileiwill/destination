package company.snapchat;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {

    // Solution 1: Pass in mutatable List, and use Recursion
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
	
    // Solution 2: Return List, and use recursion
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

    // Solution 3: Use Stack, no recursion
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        
        if (root == null) {
            return res;
        }
        
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) { // 这个稍微复杂点，得判断当前节点
            while (cur != null) { // 一直往左走
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            res.add(node.val);
            cur = node.right;
        }  
        
        return res;
    }

    ///////////END

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		node1.right = node2;
		node2.left = node3;
		
		BinaryTreeInorderTraversal it = new BinaryTreeInorderTraversal();
		it.inorderTraversal(node1);
		
	}

}
