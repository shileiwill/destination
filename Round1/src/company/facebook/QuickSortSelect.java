package company.facebook;
// 重要
import java.util.ArrayList;
import java.util.List;

/**
 * 215. Find the kth largest element in an unsorted array. 
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class QuickSortSelect {

	public static void main(String[] args) {
		QuickSortSelect qs = new QuickSortSelect();
		int[] arr = {5, 4, 7, 1, 3, 5, 6, 3, 9, 2};
		int res = qs.quickSelect(arr, 0, arr.length - 1, 5);
		
		System.out.println(res);
		
		for (int val : arr) {
			System.out.print(val + " -- ");
		}
	}
	
	// Average time is O(N), worse case time O(N^2)
	// Compare with quick sort, quick select will go only 1 side
	int quickSelect(int[] arr, int left, int right, int k) {
		int pos = partition(arr, left, right);
		if (pos == k - 1) {
			return arr[pos];
		} else if (pos < k - 1) {
			return quickSelect(arr, pos + 1, right, k);
		} else {
			return quickSelect(arr, left, pos - 1, k);
		}
	}

	void quickSort(int[] arr, int left, int right) {
		if (left < right) {
			int pos = partition(arr, left, right);
			quickSort(arr, left, pos - 1);
			quickSort(arr, pos + 1, right);
		}
	}
	
	int partition(int[] arr, int left, int right) {
		int pivot = left;
		
		while (left < right) {
			while (left < right && arr[left] <= arr[pivot]) {
				left++;
			}
			while (left < right && arr[right] > arr[pivot]) {
				right--;
			}
			swap(arr, left, right);
		}
		
		if (arr[left] < arr[pivot]) {
			swap(arr, pivot, left);
			return left;
		} else {
			swap(arr, pivot, left - 1);
			return left - 1;
		}
	}
	
	void swap(int[] arr, int left, int right) {
		int tmp = arr[left];
		arr[left] = arr[right];
		arr[right] = tmp;
	}
	
	// 数组里最大K个数然后限定了一下数字范围 所以不用minheap用一个array记录次数
	List<Integer> largestK(List<Integer> list, int k) {
		List<Integer> res = new ArrayList<Integer>();
		int[] arr = new int[100]; // Suppose the largest element in list is 99
		
		for (int val : list) {
			arr[val]++;
		}
		
		int count = 0;
		for (int i = 99; i >= 0 && count < k; i++) {
			for (int j = 0; j < arr[i]; j++) {
				res.add(i);
				count++;
			}
		}
		
		return res;
	}
	
}
