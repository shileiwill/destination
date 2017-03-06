package leetcode16.segmenttree.binaryindextree;
/**
 * Given an integer array in the construct method, implement two methods query(start, end) and modify(index, value):

For query(start, end), return the sum from index start to index end in the given array.
For modify(index, value), modify the number in the given index to value
 Notice

We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

Have you met this question in a real interview? Yes
Example
Given array A = [1,2,7,8,5].

query(0, 2), return 10.
modify(0, 4), change A[0] from 1 to 4.
query(0, 1), return 6.
modify(2, 1), change A[2] from 7 to 1.
query(2, 4), return 14.
 */
public class IntervalSum2 {
    /* you may need to use some attributes here */
    Node root = null;

    /**
     * @param A: An integer array
     */
    public IntervalSum2(int[] A) {
        // write your code here
        root = build(A, 0, A.length - 1);
    }
    
    /**
     * @param start, end: Indices
     * @return: The sum from start to end
     */
    public long query(int start, int end) {
        // write your code here
        return helper2(root, start, end);
    }
    
    long helper2(Node root, int start, int end) {
        if (start <= root.start && end >= root.end) {
            return root.sum;
        }
        
        int mid = (root.start + root.end) / 2;
        long sum = 0;
        
        if (start <= mid) {
            sum += helper2(root.left, start, end);    
        }
        
        if (end >= mid + 1) {
           sum += helper2(root.right, start, end);
        }
        
        return sum;
    }
    
    /**
     * @param index, value: modify A[index] to value.
     */
    public void modify(int index, int value) {
        // write your code here
        helper(root, index, value);
    }
    
    long diff = 0;
    void helper(Node node, int index, int value) {
        if (node.start == index && node.end == index) {
            diff = value - node.sum;
            node.sum = value;
            return;
        }
        
        int mid = (node.start + node.end) / 2;
        if (index <= mid) {
            helper(node.left, index, value);
            node.sum += diff;
        }
        
        if (index >= mid + 1) {
            helper(node.right, index, value);
            node.sum += diff;
        }
    }
    
    Node build(int[] A, int start, int end) {
        if (start > end) {
            return null;
        }
        
        Node root = new Node(start, end, A[start]);
        if (start == end) {
            return root;
        }
        
        int mid = (start + end) / 2;
        root.left = build(A, start, mid);
        root.right = build(A, mid + 1, end);
        
        root.sum = root.left.sum + root.right.sum;
        
        return root;
    }
    
    class Node {
        Node left, right;
        int start, end;
        long sum;
        
        Node(int start, int end, long sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
            this.left = null;
            this.right = null;
        }
    }
}