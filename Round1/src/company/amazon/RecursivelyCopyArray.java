package company.amazon;

public class RecursivelyCopyArray {

	public static void main(String[] args) {
		int[] arr = {1, 2, 5, 3, 8, 7};
		int[] copy = new int[arr.length];
		copy(arr, copy, 0);
		for (int v : copy) {
			System.out.print(v + "--");
		}
	}

	static void copy(int[] arr, int[] copy, int pos) {
		if (pos == arr.length) {
			return;
		}
		copy[pos] = arr[pos];
		copy(arr, copy, pos + 1);
	}
}
