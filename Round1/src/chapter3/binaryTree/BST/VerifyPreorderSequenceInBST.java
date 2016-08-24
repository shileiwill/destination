package chapter3.binaryTree.BST;
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
                firstBigger = i; // Try to find first bigger element
            }
            if (firstBigger != -1 && preorder[i] < pivot) {
                return false; // There is bigger element, but found smaller on the right..bad
            }
        }
        
        if (firstBigger == -1) { // No bigger at all
            return helper(preorder, start + 1, end);
        } else {
            return helper(preorder, start + 1, firstBigger - 1) && helper(preorder, firstBigger, end);
        }
    }
}
