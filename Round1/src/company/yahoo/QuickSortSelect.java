package company.yahoo;

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
}
