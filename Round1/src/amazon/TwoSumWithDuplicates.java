package amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * 先讲了用hashmap, 然后用binary search。follow up问了一下如果有duplicate 怎么办

2sum要求输出所有可能的pair,而且原数组可能有duplicate
 */
public class TwoSumWithDuplicates {

	public static void main(String[] args) {
		           //0, 1, 2, 3, 4, 5, 6, 7, 08, 09, 10, 11, 12, 13, 14, 15
//		int[] arr = {1, 2, 2, 2, 4, 5, 7, 8, 12, 15, 15, 15, 17, 17, 19, 19};
//		int target = 21;
//		List<int[]> res = twoSumWithDuplicates(arr, target);
//		for (int[] A : res) {
//			System.out.println(A[0] + "===" + A[1]);
//		}
		
		int[] A = {1, 2};
		int[] B = {1, 2};
		Set<int[]> set = new HashSet<int[]>();
		set.add(A);
		set.add(B);
		
		System.out.println(set.size()); // Result is 2. unlike Set<List<Integer>>
	}
	
	// Array is sorted already
	static List<int[]> twoSumWithDuplicates(int[] arr, int target) {
		List<int[]> res = new ArrayList<int[]>();
		
		int left = 0, right = arr.length - 1;
		while (left < right) {
			int sum = arr[left] + arr[right];
			
			if (sum == target) {
				int[] A = {left, right};
				res.add(A);
				
				int l = left + 1;
				while (l < right && arr[l] == arr[left]) {
					int[] B = {l, right};
					res.add(B);	
					l++;
				}
				
				int r = right - 1;
				while (left < r && arr[r] == arr[right]) {
					int[] B = {left, r};
					res.add(B);	
					r--;
				}
				
				left++;
				right--;
			} else if (target < sum) {
				right--;
			} else {
				left++;
			}
		}
		
		return res;
	}

}
