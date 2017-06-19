package company.facebook;
/**
 * Given a list of number, there is only one peak or one drop. Find the maximum drop.
Exps:
1 -> 2 -> 3 -> 9 -> 3 -> 0 = 9;
10 -> 4 -> 3 -> 8 = 7 ;
 */
public class FindMaxDrop {

	public static void main(String[] args) {

	}

	int maxDrop(int[] arr) {
		if (arr == null || arr.length <= 2) {
			return -1; // Not valid
		}
		
		if (arr[0] < arr[1]) {
			int min = Math.min(arr[0], arr[arr.length - 1]);
			int max = findMax(arr, 0, arr.length - 1);
			return max - min;
		} else {
			int max = Math.max(arr[0], arr[arr.length - 1]);
			int min = findMin(arr, 0, arr.length - 1);
			return max - min;
		}
	}

	// Is it possible to use Binary Search here?
	private int findMin(int[] arr, int left, int right) {
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (arr[mid] ) {
				
			}
		}
		return 0;
	}

	private int findMax(int[] arr, int left, int right) {
		int max = arr[left];
		for (int i = left + 1; i <= right; i++) {
			if (arr[i] < arr[i - 1]) {
				break; // Start dropping
			} else {
				max = arr[i];
			}
		}
		return max;
	}
}
