package amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepeatAndMissingNumberArray {

	public static void main(String[] args) {
		RepeatAndMissingNumberArray r = new RepeatAndMissingNumberArray();
		List<Integer> a = new ArrayList<Integer>();
		a.add(4);
		a.add(1);
		a.add(2);
		a.add(5);
		a.add(4);
		List<Integer> res = r.repeatedNumber(a);
		for (int val : res) {
			System.out.print(val + " -- ");
		}
	}
	
	// Bucket
	public ArrayList<Integer> repeatedNumberBucket(final List<Integer> a) {
	    int[] hash = new int[a.size()];
	    ArrayList<Integer> res = new ArrayList<Integer>();
	    
        for (int val : a) {
            int index = val - 1;
            if (hash[index] > 0) { // Already exist
                res.add(val);
            }
            
            hash[index]++;
        }
        
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] == 0) {
                res.add(i + 1);
            }
        }
        
        return res;
	}
	
	// Sort
	public ArrayList<Integer> repeatedNumber(final List<Integer> a) {
	    Collections.sort(a);
	    
	    int val1 = -1;
	    int val2 = -1;
	    
	    for (int i = 1; i < a.size(); i++) {
	        int prev = a.get(i - 1);
	        int now = a.get(i);
	        
	        if (prev + 1 == now) {
	            continue;
	        } else if (prev == now) {
	            val1 = prev;
	        } else {
	            val2 = prev + 1;
	        }
	    }
	    
	    ArrayList<Integer> res2 = new ArrayList<Integer>();
	    if (val1 == -1) {
	        val1 = a.get(a.size() - 1) + 1;
	    }
	    if (val2 == -1) {
	        val2 = 1;
	    }
	    
	    res2.add(val1);
	    res2.add(val2);
	    
	    return res2;
	}
}
