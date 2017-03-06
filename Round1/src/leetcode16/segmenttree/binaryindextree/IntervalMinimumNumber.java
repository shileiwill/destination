package leetcode16.segmenttree.binaryindextree;

import java.util.ArrayList;

/**
 * Given an integer array (index from 0 to n-1, where n is the size of this array), and an query list. Each query has two integers [start, end]. For each query, calculate the minimum number between index start and end in the given array, return the result list.

 Notice

We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.

Have you met this question in a real interview? Yes
Example
For array [1,2,7,8,5], and queries [(1,2),(0,4),(2,4)], return [2,1,5]
 */
public class IntervalMinimumNumber {
	/**
     *@param A, queries: Given an integer array and an query list
     *@return: The result list
     */
    public ArrayList<Integer> intervalMinNumber(int[] A, 
                                                ArrayList<Interval> queries) {
        // write your code here
        Node root = build(A, 0, A.length - 1);
        ArrayList<Integer> res = new ArrayList<Integer>();
        
        for (Interval in : queries) {
            int min = query(root, in.start, in.end);
            res.add(min);
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
        
        root.min = Math.min(root.left.min, root.right.min);
        
        return root;
    }
    
    int query(Node root, int start, int end) {
        if (start <= root.start && end >= root.end) {
            return root.min;
        }
        
        int mid = (root.start + root.end) / 2;
        int min = Integer.MAX_VALUE;
        
        if (start <= mid) {
            min = Math.min(min, query(root.left, start, end));    
        }
        
        if (end >= mid + 1) {
            min = Math.min(min, query(root.right, start, end));
        }
        
        return min;
    }
    class Node {
    	Node left, right;
    	int start, end;
    	int min;
    	
    	Node(int start, int end, int min) {
    		this.start = start;
    		this.end = end;
    		this.min = min;
    		this.left = null;
    		this.right = null;
    	}
    }
}
