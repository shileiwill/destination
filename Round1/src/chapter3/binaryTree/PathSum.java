package chapter3.binaryTree;

public class PathSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        // Make sum moving
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
