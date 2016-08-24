package chapter3.binaryTree.BST;
/**
 * 270. Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target. 

Note:

•Given target value is a floating point.
•You are guaranteed to have only one unique value in the BST that is closest to the target.

 */
import chapter3.binaryTree.TreeNode;

public class ClosestBSTValue {
    double diff = Double.MAX_VALUE;
    int answer = 0;
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return 0; //Random value
        }
        
        double curDiff = Math.abs(root.val - target);
        if (curDiff < diff) {
            answer = root.val;
            diff = curDiff;
        }
        
        if (root.val < target) {
            closestValue(root.right, target);
        } else {
            closestValue(root.left, target);
        }
        
        return answer;
    }
    
    //Iteration
    public int closestValue2(TreeNode root, double target) {
        int res = root.val;
        while (root != null) {
            if (Math.abs(target - root.val) < Math.abs(target - res)) {
                res = root.val;
            }
            if (target == root.val) {
                return root.val;
            } else if (target < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return res;
    }
}
