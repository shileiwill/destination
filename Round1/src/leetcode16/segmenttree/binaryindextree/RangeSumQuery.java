package leetcode16.segmenttree.binaryindextree;
/**
 * 307. Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.
 */
public class RangeSumQuery {

    
    // https://discuss.leetcode.com/topic/29918/17-ms-java-solution-with-segment-tree
    // http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
    class SegmentTreeNode {
        int start, end;
        SegmentTreeNode left, right;
        int sum;
        
        SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    SegmentTreeNode root = null;
    
    public RangeSumQuery(int[] nums) {
        root = buildTree(nums, 0, nums.length - 1);
    }
    
    SegmentTreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        } else {
            SegmentTreeNode node = new SegmentTreeNode(start, end);
            
            if (start == end) {
                node.sum = nums[start];
            } else {
                int mid = (start + end) / 2;
                node.left = buildTree(nums, start, mid);
                node.right = buildTree(nums, mid + 1, end);
                node.sum = node.left.sum + node.right.sum;
            }
            
            return node;        
        }
    }
    
    public void update(int i, int val) {
        update(root, i, val);
    }
    
    void update(SegmentTreeNode node, int i, int val) {
        if (node.start == node.end) { // node.left == null && node.right == null also work
            node.sum = val;
        } else {
            int mid = (node.start + node.end) / 2;
            if (i <= mid) {
                update(node.left, i, val);
            } else {
                update(node.right, i, val);
            }
            node.sum = node.left.sum + node.right.sum;
        }
    }
    
    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }
    
    int sumRange(SegmentTreeNode node, int start, int end) {
        if (node.start == start && node.end == end) {
            return node.sum;
        }
        
        int mid = (node.start + node.end) / 2; // The mid of node
        
        if (end <= mid) { // This should go first
            return sumRange(node.left, start, end);
        } else if (start >= mid + 1) { // Need to add 1
            return sumRange(node.right, start, end);
        } else {
            return sumRange(node.left, start, mid) + sumRange(node.right, mid + 1, end);
        }
    }

}
