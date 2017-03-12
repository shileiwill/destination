package chapter2.sortedArrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 4. There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 * @author Lei
 *
 */
public class MedianOf2SortedArrays {

	public static void main(String[] args) {
		MedianOf2SortedArrays m = new MedianOf2SortedArrays();
		Integer[] arr1 = {-50, -41, -40, -19, 5, 21, 28};
		Integer[] arr2 = {-50, -21, -10, 12};
		
		List<Integer> a = new ArrayList<Integer>(Arrays.asList(arr1));
		List<Integer> b = new ArrayList<Integer>(Arrays.asList(arr2));
		
		double res = m.findMedianSortedArrays(a, b);
		System.out.println(res);
	}
	
	
	public double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
	     int len = a.size() + b.size();
	     
	     if (len % 2 == 0) {
	         return (findKth(a, 0, b, 0, len / 2 - 1) + findKth(a, 0, b, 0, len / 2)) / 2.0; 
	     } else {
	         return (double)findKth(a, 0, b, 0, len / 2);
	     }
	}
	
	double findKth(List<Integer> a, int aStart, List<Integer> b, int bStart, int k) {
	    if (aStart >= a.size()) {
	        return b.get(bStart + k);
	    }
	    
	    if (bStart >= b.size()) {
	        return a.get(aStart + k);
	    }
	    
	    if (k == 0) {
	        return Math.min(a.get(aStart), b.get(bStart));
	    }
	    
	    int aMid = (aStart + k / 2 < a.size()) ? a.get(aStart + k / 2) : Integer.MAX_VALUE;
	    int bMid = (bStart + k / 2 < b.size()) ? b.get(bStart + k / 2) : Integer.MAX_VALUE;
	    
	    if (aMid <= bMid) {
	        return findKth(a, aStart + k / 2 + 1, b, bStart, k - k / 2);
	    } else {
	        return findKth(a, aStart, b, bStart + k / 2 + 1, k - k / 2);
	    }
	}
	
    public double findMedianSortedArrays(int[] A, int[] B) {
        // Based on isEven, find either mid or (mid - 1, mid)
        int len = A.length + B.length;
        boolean isEven = (len % 2 == 0);
        
        if (isEven) {
            return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)) / 2.0;
        } else {
            return findKth(A, 0, B, 0, len / 2 + 1);
        }
    }
    
    // Find kth element in 2 sorted array
    private int findKth(int[] A, int A_start, int[] B, int B_start, int k) {
        if (A_start >= A.length) {
            return B[B_start + k - 1];
        }
        if (B_start >= B.length) {
            return A[A_start + k - 1];
        }
        if (k == 1) {
            return Math.min(A[A_start], B[B_start]);
        }
        
        // Minus 1 because this is index
        int A_key = A_start + k / 2 - 1 < A.length ? A[A_start + k / 2 - 1] : Integer.MAX_VALUE;
        int B_key = B_start + k / 2 - 1 < B.length ? B[B_start + k / 2 - 1] : Integer.MAX_VALUE;
        
        if (A_key <= B_key) { // Cut half every time
            // The 2rd parameter will advance. last parameter will cut half
            return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
        } else {
            return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
        }
    }
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        
        // Based on isEven, find either mid or (mid - 1, mid)
        boolean isEven = (len1 + len2) % 2 == 0;
        int mid = (len1 + len2) / 2;
        
        int count = 0;
        int i = 0;
        int j = 0;
        
        int val1 = 0;
        int val2 = Integer.MAX_VALUE;
        while (count < mid - 1) {
            if (i >= nums1.length) {
                j++;
            } else if (j >= nums2.length) {
                i++;
            } else if (nums1[i] <= nums2[j]) {
                i++;
            } else {
                j++;
            }   
            count++;
        }
        
        if (i != nums1.length && j != nums2.length) { // Both nums1 and nums2 still exists
            val1 = (nums1[i] < nums2[j]) ? nums1[i++] : nums2[j++];
        } else if (i < nums1.length) {
            val1 = nums1[i++];
        } else if (j < nums2.length) {
        	val1 = nums2[j++];
        }
        
        if (i != nums1.length && j != nums2.length) { // Both nums1 and nums2 still exists
            val2 = (nums1[i] < nums2[j]) ? nums1[i++] : nums2[j++];
        } else if (i < nums1.length) {
            val2 = nums1[i++];
        } else if (j < nums2.length) {
            val2 = nums2[j++];
        }
        
        if (isEven) {
            return (val1 + val2) / 2.0;
        } else {
            return val2 == Integer.MAX_VALUE ? val1 : val2;
        }
    }
    
    // My new version on 08/12/2016. This is not as good as above 2 solutions.
    public double findMedianSortedArraysNew(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        
        if ((m + n) % 2 == 0) { // K is index
            return (findKth(nums1, nums2, (m + n) / 2) + findKth(nums1, nums2, (m + n) / 2 - 1)) / 2.0;
        } else {
            return findKth(nums1, nums2, (m + n) / 2);
        }
    }
    
    // Keep in mind that nums1 and nums2 are sorted. K is index
    double findKth(int[] nums1, int[] nums2, int k) {
        int pos1 = 0;
        int pos2 = 0;
        int pos = 0;
        
        int res = 0;
        while (pos1 < nums1.length && pos2 < nums2.length && pos1 + pos2 <= k) {
            if (nums1[pos1] <= nums2[pos2]) {
                res = nums1[pos1];
                pos1++;
            } else {
                res = nums2[pos2];
                pos2++;
            }
        }
        
        if (pos1 + pos2 > k) { // This is actually (pos1 + pos2 == k + 1)
            return res;
        } else {
            if (pos1 < nums1.length) {
                return nums1[k - nums2.length];
            }
            return nums2[k - nums1.length];
        }
    }
}
