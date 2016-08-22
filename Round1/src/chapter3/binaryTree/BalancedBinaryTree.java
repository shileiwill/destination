package chapter3.binaryTree;
/**
 * Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * @author Lei
 *
 */
public class BalancedBinaryTree {

	public static void main(String[] args) {
		
	}
	class ResultType {
	    boolean isBalanced;
	    int height;
	    
	    ResultType(boolean isBalanced, int height) {
	        this.isBalanced = isBalanced;
	        this.height = height;
	    }
	}
	// If use ResultType
    ResultType helper2(TreeNode node) {
        if (node == null) {
            return new ResultType(true, 0);
        }
        
        ResultType left = helper2(node.left);
        ResultType right = helper2(node.right);
        
        if (!left.isBalanced || !right.isBalanced || Math.abs(left.height - right.height) > 1) {
            return new ResultType(false, -1);
        }
        
        return new ResultType(true, Math.max(left.height, right.height) + 1);
    }
    
	// This solution will take every single node into account
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        return helper(root) != -1;
    }
    
    int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int leftHeight = helper(node.left);
        int rightHeight = helper(node.right);
        
        // If there is mismatch, return immediately
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
	
	// This solution is not correct. It will only compare the left final total height with right total height
    public boolean isBalancedWrong(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }
        
        return true;
    }
    
    int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
