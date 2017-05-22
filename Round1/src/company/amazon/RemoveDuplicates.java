package company.amazon;

import java.util.ArrayList;
import java.util.Arrays;

public class RemoveDuplicates {

	public static void main(String[] args) {
		Integer[] arr = {5000, 5000, 5000};
		ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(arr));
		int res = removeDuplicates(a);
		System.out.println(res);
	}

	public static int removeDuplicates(ArrayList<Integer> a) {
	    if (a.size() <= 1) {
	        return a.size();
	    }
	    
	    int left = 0, right = 0;
	    while (right < a.size()) {
	        while (right < a.size() && a.get(right).equals(a.get(left))) {
	            right++; // Right is the first num which is not equal to left
	        }
	        
	        if (right < a.size() && left + 1 < a.size() && left + 1 != right) {
    	        swap(a, left + 1, right);
	        }
	        
	        if (right < a.size()) {
    	        left++;
    	        right++;
	        }
	    }
	    
	    return left + 1;
	}
	
	static void swap(ArrayList<Integer> a, int left, int right) {
	    int tmp = a.get(left);
	    a.set(left, a.get(right));
	    a.set(right, tmp);
	}
}
