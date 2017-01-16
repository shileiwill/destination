package chapter3.binaryTree.BST;

import java.util.Stack;

/**
 * 255. Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
 Could you do it using only constant space complexity?

 */
public class VerifyPreorderSequenceInBST {

	public static void main(String[] args) {
		int[] preorder = {2, 3, 1};
		VerifyPreorderSequenceInBST v = new VerifyPreorderSequenceInBST();
		v.verifyPreorder(preorder);
	}

    public boolean verifyPreorder(int[] preorder) {
        if (preorder.length == 0) {
            return true;
        }
        return helper(preorder, 0, preorder.length - 1);
    }
    
    boolean helper(int[] preorder, int start, int end) {
        if (start >= end) {
            return true;
        }
        
        int firstBigger = -1;
        int pivot = preorder[start];
        
        for (int i = start + 1; i <= end; i++) {
            if (firstBigger == -1 && preorder[i] > pivot) {
                firstBigger = i; // Try to find first bigger element, the (very first right node), nodeRight
            }
            if (firstBigger != -1 && preorder[i] < pivot) { // All nodes after nodeRight should be bigger than pivot, as they are on the right of pivot
                return false; // There is bigger element, but found smaller on the right..bad
            }
        }
        
        if (firstBigger == -1) { // No bigger at all, no right node. That is totally fine for BST
            return helper(preorder, start + 1, end); // Left smaller subtree
        } else {
            return helper(preorder, start + 1, firstBigger - 1) && helper(preorder, firstBigger, end);
        }
    }
    
    // https://discuss.leetcode.com/topic/21466/72ms-c-solution-using-one-stack-o-n-time-and-space
    /*
    The idea is traversing the preorder list and using a stack to store all predecessors. curr_p is a predecessor of current node and current node is in the right subtree of curr_p.
    */
    public boolean verifyPreorder2(int[] preorder) {
        if(preorder.length < 2) return true;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(preorder[0]);
        
        int curMax = Integer.MIN_VALUE;
        
        for (int i = 1; i < preorder.length; i++) {
            int val = preorder[i];
            if (stack.isEmpty() || val < stack.peek()) {
                if (val < curMax) {
                    return false;
                }
                stack.push(val);
            } else {
                while (!stack.isEmpty() && val > stack.peek()) {
                    curMax = stack.pop(); // Pop all smaller ones
                }
                stack.push(val); // Push this big one at the end
            }
        }
        
        return true;
    }
}
