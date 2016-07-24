package chapter3.binaryTree.sort;

public class MergeSort {

	public static void main(String[] args) {
		MergeSort merge = new MergeSort();
		int[] N = {3, 1, 12, 9, 4, 11, 8, 0, 1, 5, 23, 1, 14, 12, 13, 11};
		merge.mergeSort(N, 0, N.length - 1);
		
		for (int i = 0; i < N.length; i++) {
			System.out.print(N[i] + " - ");
		}
	}
	
	public void mergeSort(int[] N, int left, int right) {
		if (left < right) {
			int mid = left + (right - left) / 2;
			mergeSort(N, left, mid);
			mergeSort(N, mid + 1, right);
			
			merge(N, left, mid, right);
		}
	}
	
	public void merge(int[] N, int left, int mid, int right) {
		// Make sure this is full length, as we dont know which part we are using
		int[] extra = new int[N.length];
		
		for (int i = left; i <= right; i++) {
			extra[i] = N[i];
		}
		
		// Merge 2 sorted arrays
		int i = left;
		int j = mid + 1;
		int cur = left;
		
		while (i <= mid && j <= right) {
			if (extra[i] <= extra[j]) {
				N[cur++] = extra[i++];
			} else {
				N[cur++] = extra[j++];
			}
		}
		
		// Take care only of i, the left side
		while (i <= mid) {
			N[cur++] = extra[i++];
		}
	}

}
