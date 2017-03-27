package company.amazon;

public class ReverseStringRecursively {

	public static void main(String[] args) {
		String[] arr = {"I", "am", "a", "dog"};
		helper2(arr, 0, new int[]{0});
		for (String s : arr) {
			System.out.println(s);
		}
	}
	
	static int start = 0;
	static void helper(String[] arr, int pos) {
		if (pos == arr.length) {
			return;
		}
		System.out.println("coming"); // It comes arr.length times, which is not good
		helper(arr, pos + 1);
		
		if (start < arr.length / 2) {
			swap(arr, pos, start);
			start++;
		} 
	}
	
	static void helper2(String[] arr, int pos, int[] leftPoint) {
		if (pos == arr.length) {
			return;
		}
		
		helper2(arr, pos + 1, leftPoint);
		
		if (leftPoint[0] < arr.length / 2) {
			swap(arr, pos, leftPoint[0]);
			leftPoint[0]++;
		} 
	}
	
	static void swap(String[] arr, int left, int right) {
		String temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}
}
