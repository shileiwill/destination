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
    class Pair {
        int index;
        int value;
        Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
    /*
    Use the idea of merge sort. Key algorithm:
ex:
index: 0, 1
left: 2, 5
right: 1, 6
Each time we choose a left to the merged array. We want to know how many numbers from array right are moved before this number.
For example we take 1 from right array and merge sort it first. Then it’s 2 from left array. We find that there are j numbers moved before this left[i], in this case j == 1.
So the array smaller[original index of 2] += j.
Then we take 5 from array left. We also know that j numbers moved before this 5.
smaller[original index of 6] += j.
ex:
index: 0, 1, 2
left: 4, 5, 6
right: 1, 2, 3
when we take 4 for merge sort. We add j (j == 3) because we already take j numbers before take this 4.

During the merge sort, we have to know number and it’s original index. We use a class called Pair to encapsulate them together.
We need to pass the array smaller to merge sort method call because it might be changed during any level of merge sort. And the final smaller number is add up of all the numbers moved before this value.
    */
    public List<Integer> countSmaller(int[] nums) {
        Integer[] smaller = new Integer[nums.length];
        Arrays.fill(smaller, 0);
        
        Pair[] pairs = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            pairs[i] = new Pair(i, nums[i]);
        }
        
        mergeSort(pairs, smaller);       
        
        return Arrays.asList(smaller);
    }
    
    Pair[] mergeSort(Pair[] pairs, Integer[] smaller) {
        if (pairs.length <= 1) {
            return pairs;
        }
        
        int mid = pairs.length / 2;
        Pair[] left = mergeSort(Arrays.copyOfRange(pairs, 0, mid), smaller);
        Pair[] right = mergeSort(Arrays.copyOfRange(pairs, mid, pairs.length), smaller);
        
        for (int i = 0, j = 0; i < left.length || j < right.length;) {
            // right is done, or left value is smaller
            if (j == right.length || (i < left.length && left[i].value <= right[j].value)) {
                pairs[i + j] = left[i];
                smaller[left[i].index] += j;
                i++;
            } else {
                pairs[i + j] = right[j];
                j++;
            }
        }
        
        return pairs;
    }
    
    public List<Integer> countSmallerRecursion(int[] nums) {
        Integer[] arr = new Integer[nums.length];
        TreeNode1 root = null;
        
        // Must start from the end
        for (int i = nums.length - 1; i >= 0; i--) {
            root = insert(root, arr, i, nums[i], 0);
        }
        
        return Arrays.asList(arr);
    }
    
    TreeNode1 insert(TreeNode1 node, Integer[] arr, int i, int val, int preLeftSum) {
        if (node == null) {
            node = new TreeNode1(val);
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
    
    public List<Integer> countSmallerIteration(int[] nums) {
        Integer[] arr = new Integer[nums.length];
        
        if (nums.length == 0) {
            return Arrays.asList(arr);
        }
        
        TreeNode0 root = new TreeNode0(nums[nums.length - 1]);
        for (int i = nums.length - 1; i >= 0; i--) {
            arr[i] = insert(root, nums[i]);
        }
        
        return Arrays.asList(arr);
    }
    
    int insert(TreeNode0 node, int num) {
        int sum = 0;
        while (node.val != num) {
            if (num < node.val) { // Go left
                node.leftSum++;
                if (node.left == null) {
                    node.left = new TreeNode0(num);
                }
                node = node.left;
            } else { // num > node.val
                sum += node.leftSum + node.count;
                if (node.right == null) {
                    node.right = new TreeNode0(num);
                }
                node = node.right;
            }     
        }
        node.count++;
        return sum + node.leftSum; // Need to add leftSum as well
    }
    
    class TreeNode1 {
        TreeNode1 left, right;
        int val, leftSum, count = 1; // count is 1
        
        TreeNode1(int val) {
            this.val = val;
        }
    }
    
    class TreeNode0 {
        TreeNode0 left, right;
        int val, leftSum, count = 0; // Initially, count is 0
        
        TreeNode0(int val) {
            this.val = val;
        }
    }
}