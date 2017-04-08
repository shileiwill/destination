package company.facebook;

import java.util.ArrayList;
import java.util.List;

/**
 * Rearrange a given array so that Arr[i] becomes Arr[Arr[i]] with O(1) extra space.

Example:

Input : [1, 0]
Return : [0, 1]
 Lets say N = size of the array. Then, following holds true :
* All elements in the array are in the range [0, N-1]
* N * N does not overflow for a signed integer 
 */
public class RearrangeArray {

	public static void main(String[] args) {

	}

	// It will be very simple if we can use extra space
	public void arrange2(ArrayList<Integer> a) {
	    List<Integer> backup = new ArrayList<Integer>();
	    
	    for (int val : a) {
	        backup.add(val);
	    }
	    
	    for (int i = 0; i < a.size(); i++) {
	        int val1 = a.get(i);
	        
	        a.set(i, backup.get(val1));
	    }
	}
	
	// The idea is to use 1 single val to hold both old and new value
	public void arrange(ArrayList<Integer> a) {
	    int N = a.size();
	    
	    for (int i = 0; i < a.size(); i++) {
	        int val1 = a.get(i);
	        
	        a.set(i, a.get(i) + (a.get(a.get(i)) % N) * N);
	    }
	    
	    for (int i = 0; i < a.size(); i++) {
	        a.set(i, a.get(i) / N);
	    }
	}
}
