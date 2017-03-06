package leetcode16.segmenttree.binaryindextree;

import java.util.ArrayList;

/**
 * Given an integer array (index from 0 to n-1, where n is the size of this array), and an query list. Each query has two integers [start, end]. For each query, calculate the sum number between index start and end in the given array, return the result list.

 Notice

We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

Have you met this question in a real interview? Yes
Example
For array [1,2,7,8,5], and queries [(0,4),(1,2),(2,4)], return [23,9,20]
 */
public class IntervalSum {
    /**
     *@param A, queries: Given an integer array and an query list
     *@return: The result list
     */
    public ArrayList<Long> intervalSum(int[] A, 
                                       ArrayList<Interval> queries) {
        Node root = build(A, 0, A.length - 1);
        ArrayList<Long> res = new ArrayList<Long>();
        
        for (Interval in : queries) {
            long sum = query(root, in.start, in.end);
            res.add(sum);
        }
        
        return res;
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
    
    long query(Node root, int start, int end) {
        if (start <= root.start && end >= root.end) {
            return root.sum;
        }
        
        int mid = (root.start + root.end) / 2;
        long sum = 0;
        
        if (start <= mid) {
            sum += query(root.left, start, end);    
        }
        
        if (end >= mid + 1) {
           sum += query(root.right, start, end);
        }
        
        return sum;
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
