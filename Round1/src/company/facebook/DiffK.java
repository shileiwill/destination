package company.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Integer[] arr = {1, 3, 3};
		int target = 0;
		
		List<Integer> list = Arrays.asList(arr);
		DiffK dk = new DiffK();
		boolean res = dk.diffPossibleMap(list, target);
		System.out.println(res);
	}

	// 2 pointers because array is sorted
	public int diffPossible(ArrayList<Integer> a, int b) {
	    int left = 0, right = 1;
	    
	    while (right < a.size()) {
	        int diff = a.get(right) - a.get(left);
	        
	        if (diff == b) {
	            return 1;
	        }
	        
	        if (diff > b) {
	            left++;
	            right = Math.max(left + 1, right); // 这个很巧妙，保证right总在left后边
	        } else {
	            right++;
	        }
	    }
	    
	    return 0;
	}
	
	public boolean diffPossibleMap(List<Integer> list, int target) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < list.size(); i++) {
			int num = list.get(i);
			int toFind = num - target; // list is sorted
			
			if (map.containsKey(toFind)) {
				if (target == 0) { // 特殊情况特殊考虑。 不需要吧， 前边如果存在，肯定是之前有过
					if (map.get(toFind) >= 1) {
						return true;
					}
				} else {
					return true;
				}
			}
			
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		
		return false;
	}
}
