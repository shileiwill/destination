package company.linkedin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given一个array of non-negative integers, 找出3组数字可以组成3角形, 每个数字表示边长, 组成3角形的充要条件就是任2边的和要大于第3边
 * 
 * 对这个array做sorting后, 从array的最小的element开始往最大去扫, 以连续3个数字一组确认是否可以构成3角形
 */
public class CanFormTriangle {

	public static void main(String[] args) {
		int[] arr = {3, 2, 1, 9, 5, 7, 4, 2, 9, 5};
		CanFormTriangle t = new CanFormTriangle();
		t.canFormTriangle(arr);
	}

	// Hey, this doesnt work! this will consider only the sequential 3.
	boolean canFormTriangleWrong(int[] arr) {
		Arrays.sort(arr);
		for (int i = 0; i <= arr.length - 3; i++) {
			if (arr[i] + arr[i + 1] > arr[i + 2]) {
				return true;
			}
		}
		return false;
	}
	
	int numberOfTriangle(int[] arr) {
		int count = 0;
		Arrays.sort(arr);
		
		for (int i = 0; i <= arr.length - 3; i++) {
			int left = i + 1;
			int right = arr.length - 1;
			
			while (left < right) {
				if (arr[i] + arr[left] > arr[right]) {
					count += right - left;
					right--;
				} else {
					left++;
				}
			}
		}
		
		return count;
	}
	
	List<List<Integer>> solutionsOfTriangle(int[] arr) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		Arrays.sort(arr);
		
		for (int i = 0; i <= arr.length - 3; i++) {
			int left = i + 1;
			int right = arr.length - 1;
			
			while (left < right) {
				if (arr[i] + arr[left] > arr[right]) {
					for (int j = left; j < right; j++) {
						List<Integer> list = new ArrayList<Integer>();
						list.add(arr[i]);
						list.add(arr[j]);
						list.add(arr[right]);
						res.add(list);
					}
					right--;
				} else {
					left++;
				}
			}
		}
		
		return res;
	}
}
