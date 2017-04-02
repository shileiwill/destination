package company.yahoo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sum3 {

	public static void main(String[] args) {
		Sum3 s3 = new Sum3();
		int[] arr = {5, 3, 9, 1, 1, 1, 0, 7, 4};
		int target = 10;
		List<List<Integer>> res = s3.sum3(arr, target);
		
		for (List<Integer> list : res) {
			for (int val : list) {
				System.out.print(val + "--");
			}
			System.out.println();
		}
	}

	List<List<Integer>> sum3(int[] arr, int target) {
		Arrays.sort(arr);
		
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		
		for (int i = 0; i < arr.length - 2; i++) {
			int left = i + 1;
			int right = arr.length - 1;
			
			while (left < right) {
				int sum = arr[i] + arr[left] + arr[right];
				
				if (sum == target) {
					List<Integer> list = new ArrayList<Integer>();
					list.add(arr[i]);
					list.add(arr[left]);
					list.add(arr[right]);
					
					res.add(list);
					
					left++;
					while (left < right && arr[left] == arr[left - 1]) {
						left++;
					}
					
					right--;
					while (left < right && arr[right] == arr[right + 1]) {
						right--;
					}
				} else if (sum < target) {
					left++;
				} else {
					right--;
				}
			}
		}
		
		return res;
	}
}
