package leetcode6.divideandconquer;

import java.util.Arrays;
import java.util.List;

/**
 * 315. You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].
 */
public class CountOfSmallerNumber {
    public List<Integer> countSmaller(int[] nums) {
        Integer[] arr = new Integer[nums.length];
        TreeNode root = null;
        
        // Must start from the end
        for (int i = nums.length - 1; i >= 0; i--) {
            root = insert(root, arr, i, nums[i], 0);
        }
        
        return Arrays.asList(arr);
    }
    
    TreeNode insert(TreeNode node, Integer[] arr, int i, int val, int preLeftSum) {
        if (node == null) {
            node = new TreeNode(val);
            arr[i] = preLeftSum; // It could have been to right child, so preLeftSum need to be taken care
        } else if (node.val == val) {
            node.count++;
            arr[i] = preLeftSum + node.leftSum;
        } else if (val < node.val) {
            node.leftSum++;
            node.left = insert(node.left, arr, i, val, preLeftSum);
        } else {
            node.right = insert(node.right, arr, i, val, preLeftSum + node.leftSum + node.count);
        }
        return node;
    }
    
    class TreeNode {
        TreeNode left, right;
        int val, leftSum, count = 1; // count is 1
        
        TreeNode(int val) {
            this.val = val;
        }
    }
}