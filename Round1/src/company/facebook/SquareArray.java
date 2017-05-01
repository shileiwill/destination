package company.facebook;
/**
 * We're given a sorted array of integers: [-3, -1, 0, 1, 2]. We want to generate a sorted array of their squares: [0, 1, 1, 4, 9]
 */
public class SquareArray {

	public static void main(String[] args) {
		SquareArray sa = new SquareArray();
		
		int[] arr = {-3, -1, 0, 1, 2};
		sa.square(arr);
		
		for (int val : arr) {
			System.out.print(val + " ");
		}
	}

	// Insertion sort, O(N^2). If use Arrays.sort, it will be O(nlog(n))
	void square(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i] * arr[i];
			
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					swap(arr, j, j - 1);
				}
			}
		}
	}
	
	void swap(int[] arr, int left, int right) {
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}
}
