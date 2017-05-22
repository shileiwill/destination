package company.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DistinctNumbersInK {

	public static void main(String[] args) {
		Integer[] arr = {2, 7, 7, 81, 81};
		ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(arr));
		DistinctNumbersInK.dNums(A, 1);
	}

    public static ArrayList<Integer> dNums(ArrayList<Integer> A, int B) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        
        int len = A.size();
        if (B > len) {
            return res;
        }
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        int left = 0, right = 0;
        while (right < B) { // First group with K elements
            int now = A.get(right);
            map.put(now, map.getOrDefault(now, 0) + 1);
            right++;
        }
        res.add(map.size()); // How many distinct numbers in this first group
        
//        right++;
        while (left < A.size() - B && right <= A.size()) { // left is the index to remove, right is to add
            int leftVal = A.get(left);
            
            int leftCount = map.get(leftVal);
            if (leftCount > 1) {
                map.put(leftVal, leftCount - 1);
            } else {
                map.remove(leftVal);
            }
            
            if (right == A.size()) {
                res.add(map.size());
                break;
            }
            
            int now = A.get(right);
            map.put(now, map.getOrDefault(now, 0) + 1);
            
            res.add(map.size());
            
            left++; // Increase left, right pointers at the same time
            right++;
        }
        
        return res;
    }
}
