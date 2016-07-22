package chapter3.binaryTree;

public class QuickSort {
	public static void main(String[] args) {
		QuickSort quick = new QuickSort();
		int[] N = {3, 1, 12, 9, 4, 11, 8, 0, 1, 5, 4, -11, 23, 1, 14, 12, 13, 11};
		quick.quickSort(N, 0, N.length - 1);
		
		for (int i = 0; i < N.length; i++) {
			System.out.print(N[i] + " - ");
		}
	}
	public void quickSort(int[] N, int left, int right) {
		if (left < right) {
			int p = partition(N, left, right);
			quickSort(N, left, p - 1);
			quickSort(N, p + 1, right);
		}
	}
	
	private int partition(int[] N, int left, int right) {
		int pivot = left;
		// When will this stop? only when left == right? YES! Because it is step by step
		while (left < right) {
			while (left < right && N[left] <= N[pivot]) {
				left++;
			}
			while (left < right && N[right] > N[pivot]) {
				right--;
			}
			
			swap(N, left, right);
		}
		System.out.println("left : " + left + " right : " + right);
		// Put pivot to the correct place
		if (N[pivot] >= N[left]) { // Why using right/left, rather than left. BOTH are fine! Since left will always equals to right at the end.
			swap(N, pivot, left);
			return left;
		} else {
			swap(N, pivot, left - 1);
			return left - 1;
		}
	}
	
	private void swap(int[] N, int left, int right) {
		int temp = N[left];
		N[left] = N[right];
		N[right] = temp;
	}
}
