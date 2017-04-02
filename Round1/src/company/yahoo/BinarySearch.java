package company.yahoo;

public class BinarySearch {

	public static void main(String[] args) {
		int[] arr = {1, 4, 5, 6, 7, 7, 7, 7, 7, 7, 7, 7, 8, 9, 9, 9};
		BinarySearch bs = new BinarySearch();
		int count = bs.countOfTarget(arr, 7);
		System.out.println(count);
	}

	int countOfTarget(int[] arr, int target) {
		int left = binarySearchLeft(arr, target, 0, arr.length - 1);
		int right = binarySearchRight(arr, target, left, arr.length - 1);
		
		return right - left + 1;
	}
	
	int binarySearchLeft(int[] arr, int target, int left, int right) {
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			
			if (arr[mid] < target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		
		if (arr[left] == target) {
			return left;
		}
		return right;
	}
	
	int binarySearchRight(int[] arr, int target, int left, int right) {
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			
			if (arr[mid] <= target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		
		if (arr[right] == target) {
			return right;
		}
		return left;
	}
}
