package chapter2.binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Give you an integer array (index from 0 to n-1, where n is the size of this array, value from 0 to 10000) and an query list. For each query, give you an integer, return the number of element in the array that are smaller than the given integer.

 Notice

We suggest you finish problem Segment Tree Build and Segment Tree Query II first.

Have you met this question in a real interview? Yes
Example
For array [1,2,7,8,5], and queries [1,8,5], return [0,4,2]

Challenge 
Could you use three ways to do it.

Just loop
Sort and binary search
Build Segment Tree and Search.
 */
public class CountOfSmallerNumber {
	   /**
	     * @param A: An integer array
	     * @return: The number of element in the array that 
	     *          are smaller that the given integer
	     */
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
	}
