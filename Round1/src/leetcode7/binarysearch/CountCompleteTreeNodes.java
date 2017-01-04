package leetcode7.binarysearch;

import chapter3.binaryTree.TreeNode;

/**
 * 222. Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 */
public class CountCompleteTreeNodes {
    public int countNodesNaive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countNodesRecursion(root.left);
        int right = countNodesRecursion(root.right);
        
        return 1 + left + right;
    }
    
    /*
    That would be O(n). But... the actual solution has a gigantic optimization. It first walks all the way left and right to determine the height and whether it's a full tree, meaning the last row is full. If so, then the answer is just 2^height-1. And since always at least one of the two recursive calls is such a full tree, at least one of the two calls immediately stops. Again we have runtime O(log(n)^2).
    */
    public int countNodesRecursion(TreeNode root) {
        int leftHeight = leftHeight(root);
        int rightHeight = rightHeight(root);
        
        if (leftHeight == rightHeight) {
            return (1 << leftHeight) - 1;
        }
        
        return 1 + countNodesRecursion(root.left) + countNodesRecursion(root.right);
    }
    
    int leftHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        
        return height;
    }
    
    int rightHeight(TreeNode node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.right;
        }
        
        return height;
    }
    
    // Reference: https://discuss.leetcode.com/topic/15525/concise-java-iterative-solution-o-logn-2
    public int countNodesBinarySearch(TreeNode root) {
        int sum = 0;
        
        while (root != null) {
            int leftLeftH = leftHeight(root.left);
            int leftRightH = rightHeight(root.left);
            int rightRightH = rightHeight(root.right);
            
            if (leftLeftH == rightRightH) {
                sum += (1 << (leftLeftH + 1)) - 1; // Balanced, Add all. (leftLeftH + 1) is the height from root 
                break;
            } else if (leftLeftH > leftRightH) { // It can never be leftLeftH < leftRightH as it is a complete tree
                sum += (1 << rightRightH); // Should be -1 and then +1(root)
                root = root.left;
            } else { // Left is balanced, right is not
                sum += (1 << leftLeftH);
                root = root.right;
            }
            
        }
        
        return sum;
    }
}
