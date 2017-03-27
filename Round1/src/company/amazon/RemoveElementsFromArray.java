package company.amazon;

import java.util.ArrayList;

/**
 * Remove Element

Given an array and a value, remove all the instances of that value in the array. 
Also return the number of elements left in the array after the operation.
It does not matter what is left beyond the expected length.

 Example:
If array A is [4, 1, 1, 2, 1, 3]
and value elem is 1, 
then new length is 3, and A is now [4, 2, 3] 
Try to do it in less than linear additional space complexity.
 */
public class RemoveElementsFromArray {
	// TLE
	public int removeElement2(ArrayList<Integer> a, int b) {
	    int left = 0, right = 0;
	    
	    while (left < a.size() && right < a.size()) {
	        while (left < a.size() && a.get(left) != b) {
	            left++;
	        }
	        
	        if (left == a.size()) {
	            return left;
	        }
	        
	        right = left + 1;
	        while (right < a.size() && a.get(right) == b) {
	            right++;
	        }
	        
	        if (right == a.size()) {
	            return left;
	        }
	        
	        swap(a, left, right);
	        
	        left++;
	        right++;
	    }
	    
	    return left;
	}
	
	void swap(ArrayList<Integer> a, int left, int right) {
	    int tmp = a.get(left);
	    a.set(left, a.get(right));
	    a.set(right, tmp);
	}
	
	// Good one
	public int removeElement(ArrayList<Integer> a, int b) {
	    int count = 0;
	    
	    for (int i = 0; i < a.size(); i++) {
	        if (a.get(i) == b) {
	            count++;
	        } else {
	            a.set(i - count, a.get(i)); // The new index of this guy
	        }
	    }
	    
	    return a.size() - count;
	}
}
