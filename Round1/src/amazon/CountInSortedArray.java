package amazon;

public class CountInSortedArray {

	public static void main(String[] args) {
		int[] arr = {1, 1, 2, 4, 4, 4, 5, 5, 6, 6, 6, 6, 6, 6, 9};
		int count = count(arr, 9);
		System.out.println(count);
	}
	
	static int count (int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;
		int index = -1;
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			
			if (arr[mid] == target) {
				index = mid;
				break;
			} else if (arr[mid] > target) {
				right = mid;
			} else {
				left = mid;
			}
		}
		
		if (arr[left] == target) {
			index = left;
		} else if (arr[right] == target) {
			index = right;
		}
		
		if (index == -1) {
			return 0;
		}
		
		int count = 1;
		left = index - 1;
		while (left >= 0 && arr[left] == target) {
			count++;
			left--;
		}
		
		right = index + 1;
		while (right < arr.length && arr[right] == target) {
			count++;
			right++;
		}
		
		return count;
	}

}
