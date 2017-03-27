package company.expedia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KSubsequence {

	public static void main(String[] args) {
		KSubsequence kSub = new KSubsequence();
		
		int k = 3;
		int[] arr = {1, 2, 3, 4, 1};
		
		int res = kSub.countKSubsequence2(k, arr);
		System.out.print(res + "--");
	}

	int countKSubsequence2(int k, int[] arr) {
		int count = 0;
		
		for (int i = 0; i < arr.length; i++) { // Start Point
			int sum = 0;
			for (int j = i; j < arr.length; j++) { // End Point
				sum += arr[j];
				
				if (sum % k == 0) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	List<Integer> countKSubsequence(int k, int[] arr) {
		List<Integer> res = new ArrayList<Integer>();
		LinkedList<Integer> list = new LinkedList<Integer>();
		int len = arr.length;
		
		if (k > len) {
			return res;
		}
		
		int count = 0;
		int i = 0;
		int sum = 0;
		// Add first k elements to list
		while (i < k) {
			list.addLast(arr[i]);
			sum += arr[i];
			i++;
		}
		
		while (i < len) {
			if (sum % k == 0) {
				count++;
				res.add(i - k);
			}
			int toRemove = list.getFirst();
			sum -= toRemove;
			list.removeFirst();
			
			int toAdd = arr[i];
			sum += toAdd;
			list.addLast(toAdd);
			
			i++;
		}
		
		System.out.println(count);
		return res;
	}
}
