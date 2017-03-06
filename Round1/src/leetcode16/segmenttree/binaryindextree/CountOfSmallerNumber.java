package leetcode16.segmenttree.binaryindextree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Give you an integer array (index from 0 to n-1, where n is the size of this array, value from 0 to 10000) and an query list. For each query, give you an integer, return the number of element in the array that are smaller than the given integer.

 Notice

We suggest you finish problem Segment Tree Build and Segment Tree Query II first.

Have you met this question in a real interview? Yes
Example
For array [1,2,7,8,5], and queries [1,8,5], return [0,4,2]
 */
public class CountOfSmallerNumber {
    public ArrayList<Integer> countOfSmallerNumber(int[] A, int[] queries) {
        Arrays.sort(A);
         
        ArrayList<Integer> res = new ArrayList<Integer>();
        
        if (A.length == 0) {
            for (int query : queries) {
                res.add(0);
            }
            return res;
        }
        
        for (int query : queries) {
            int left = 0;
            int right = A.length - 1;
            
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                if (A[mid] < query) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            
            if (A[left] >= query) {
                res.add(0);
            } else if (A[left] < query && A[right] >= query) {
                res.add(left + 1);
            } else {
                res.add(right + 1);
            }
        }
        
        return res;
     }
    public ArrayList<Integer> countOfSmallerNumber2(int[] A, int[] queries) {
        // write your code here
        ArrayList<Integer> res = new ArrayList<Integer>();
        
        for (int query : queries) {
            int count = count(A, query);
            res.add(count);
        }
        
        return res;
    }
    
    int count(int[] A, int query) {
        int count = 0;
        for (int a : A) {
            if (a < query) {
                count++;
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
		Solution so = new Solution();
		int[] A = {55,81,56,91,35,92,10,53,27,94,64,45,19,44,52,19,79,12,16,90,97,33,73,2,20,68,19,7,17,62,45,48,62,26,85,4,63,67,56,16};
		System.out.println(A.length);
		int[] queries = {10,43,2,17,28,75,75,12};
		ArrayList<Integer> res = so.countOfSmallerNumber(A, queries);
		
		for (int val : res) {
			System.out.print(val + " -- ");
		}
	}
}

class Solution {
	   /**
	     * @param A: An integer array
	     * @return: The number of element in the array that 
	     *          are smaller that the given integer
	     */
	    Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // To deal with duplicates
	    Node root = null;
	    public ArrayList<Integer> countOfSmallerNumber(int[] A, int[] queries) {
	         if (A == null || A.length == 0) {
	             ArrayList<Integer> list2 = new ArrayList<Integer>();
	             for (int i = 0; i < queries.length; i++) {
	                 list2.add(0);
	             }
	             return list2;
	         }
	         
	         int min = Integer.MAX_VALUE;
	         int max = Integer.MIN_VALUE;
	         
	         for (int val : A) {
	             min = Math.min(min, val);
	             max = Math.max(max, val);
	             
	             if (!map.containsKey(val)) {
	            	 map.put(val, 1);
	             } else {
	            	 map.put(val, map.get(val) + 1);
	             }
	         }
	         
	         root = build(min, max);
	         
	         ArrayList<Integer> res = new ArrayList<Integer>();
	         for (int query : queries) {
	             if (query - 1 < min) {
	                 res.add(0);
	                 continue;
	             }
	             int count = query(root, query - 1); // Smaller, not include, so minus 1
	             res.add(count);
	         }
	         return res;
	    }
	     
	    Node build(int start, int end) {
	        if (start > end) {
	            return null;
	        }
	        
	        Node node = new Node(start, end, 0);
	        if (start == end) {
	            if (map.containsKey(start)) {
	                node.count = map.get(start);
	                map.remove(start);
	            }
	            return node;
	        }
	        
	        int mid = (start + end) / 2;
	        node.left = build(start, mid);
	        node.right = build(mid + 1, end);
	        
	        node.count = node.left.count + node.right.count;
	        
	        return node;
	    }
	    
	    int query(Node node, int query) {
	        if (query >= node.end) {
	            return node.count;
	        }
	        
	        int mid = (node.start + node.end) / 2;
	        int count = 0;
	        
	        if (query < mid) {
	            count += query(node.left, query); 
	        } else if (query == mid) {
	            count += node.left.count;
	        } else {
	            count += node.left.count;
	            count += query(node.right, query); 
	        }
	        
	        return count;
	    }
	}
	class Node {
	    Node left, right;
	    int start, end;
	    int count;
	    
	    Node(int start, int end, int count) {
	        this.start = start;
	        this.end = end;
	        this.count = count;
	        this.left = null;
	        this.right = null;
	    }
	}
