package company.facebook;

import java.util.ArrayList;

/**
 * Given an array ‘A’ of sorted integers and another non negative integer k, find if there exists 2 indices i and j such that A[i] - A[j] = k, i != j.

 Example: Input : 
    A : [1 3 5] 
    k : 4
 Output : YES as 5 - 1 = 4 
Return 0 / 1 ( 0 for false, 1 for true ) for this problem

Try doing this in less than linear space complexity.
 */
public class DiffK {

	public static void main(String[] args) {

	}

	public int diffPossible(ArrayList<Integer> a, int b) {
	    int left = 0, right = 1;
	    
	    while (right < a.size()) {
	        int diff = a.get(right) - a.get(left);
	        
	        if (diff == b) {
	            return 1;
	        }
	        
	        if (diff > b) {
	            left++;
	            right = Math.max(left + 1, right);
	        } else {
	            right++;
	        }
	    }
	    
	    return 0;
	}
}
