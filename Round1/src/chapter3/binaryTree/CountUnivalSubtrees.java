package chapter3.binaryTree;
/**
 * 250. Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
 Given binary tree,

              5
             / \
            1   5
           / \   \
          5   5   5



return 4. 

 */
public class CountUnivalSubtrees {
	// Realize we dont need the ResultType at all. also this is post order traversal! count[0]++ is fine
    public int countUnivalSubtrees2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] count = {0};
        helper2(root, count);
        return count[0];
    }
    
    boolean helper2(TreeNode root, int[] count) {
        if (root == null) {
            return true;
        }
        
        boolean left = helper2(root.left, count);
        boolean right = helper2(root.right, count);
        
        if (left && right) { // As long as there is one different, return false
            if ((root.left == null || root.val == root.left.val) && (root.right == null || root.val == root.right.val)) {
                count[0]++;
                return true;
            }
        }
        
        return false;
    }
	
    int count = 0;
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return count;
        }
        helper(root);
        return count;
    }
    
    ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(true);
        }
        // if (root.left == null && root.right == null) { // Not necessary
        //     count++;
        //     return new ResultType(true);
        // }
        
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        if (!left.isUnival || !right.isUnival) { // As long as there is one different, return false
            return new ResultType(false);
        }
        
        if ((root.left == null || root.val == root.left.val) && (root.right == null || root.val == root.right.val)) {
            count++;
            return new ResultType(true);
        } else {
            return new ResultType(false);
        }
    }
}

class ResultType {
    boolean isUnival;
    ResultType (boolean isUnival) {
        this.isUnival = isUnival;
    }
}