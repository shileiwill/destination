package cc150.chapter10.sorting.searching;

import java.util.Arrays;

public class Sorting {

	public static void main(String[] args) {
		Sorting bubble = new Sorting();
		int[] arr = {12, 4, 2, 17, 11, 28, 5, 2};
		bubble.insertionSort(arr);
//		bubble.quickSort(arr, 0, arr.length - 1);
		for (int val : arr) {
			System.out.print(val + " - ");
		}
		System.out.println();
	}

	// https://www.khanacademy.org/computing/computer-science/algorithms/insertion-sort/a/insertion-sort
	void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int cur = i;
			while (cur > 0 && arr[cur] < arr[cur - 1]) { // Compare from right to left until find the correct place
				swap(arr, cur, cur - 1);
				cur--;
			}
		}
	}
	
	void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					swap(arr, i, j);
				}
			}
		}
	}
	
	void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int minIndex = findMin(arr, i);
			swap(arr, i, minIndex);
		}
	}
	
	int getMax(int[] arr)
	{
		int mx = arr[0];
		for (int i = 1; i < arr.length; i++)
			if (arr[i] > mx)
				mx = arr[i];
		return mx;
	}
	
	// http://www.geeksforgeeks.org/radix-sort/
	void radixSort(int[] arr) {
		int maxVal = arr[findMax(arr, 0)];
		for (int digit = 1; maxVal / digit > 0; digit *= 10) {
			radixSortHelper(arr, digit);
		}
	}
	
	void radixSortHelper(int[] arr, int digit) {
		int[] count = new int[10];
		int[] update = new int[arr.length];
		
		// Fill count[] with the count of that digit
		for (int i = 0; i < arr.length; i++) {
			int singleVal = (arr[i] / digit) % 10;
			count[singleVal]++;
		}
		
		// Make count[] reflect the position
		for (int i = 1; i < 10; i++) {
			count[i] += count[i - 1];
		}
		
		// Build update[], must from the end. that is also why need to use bucket sort, which is stable
		for (int i = arr.length - 1; i >= 0; i--) {
			int pos = count[(arr[i] / digit) % 10];
			update[pos - 1] = arr[i];
			count[(arr[i] / digit) % 10]--;
		}
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = update[i];
		}
	}
	
	int findMin(int[] arr, int start) {
		int minIndex = start, minValue = arr[start];
		for (int i = start + 1; i < arr.length; i++) {
			if (minValue > arr[i]) {
				minIndex = i;
				minValue = arr[i];
			}
		}
		return minIndex;
	}
	
	int findMax(int[] arr, int start) {
		int maxIndex = start, maxValue = arr[start];
		for (int i = start + 1; i < arr.length; i++) {
			if (maxValue < arr[i]) {
				maxIndex = i;
				maxValue = arr[i];
			}
		}
		return maxIndex;
	}
	
	void bucketSort(int[] arr) {
		int maxValue = arr[findMax(arr, 0)];
		int[] buckets = new int[maxValue + 1];
		
		for (int i = 0; i < arr.length; i++) {
			buckets[arr[i]]++;
		}
		
		int index = 0;
		for (int i = 0; i < buckets.length; i++) {
			int count = buckets[i];
			for (int j = 0; j < count; j++) {
				arr[index++] = i;
			}
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
		
		if (arr[left] <= arr[pivot]) {
			swap(arr, pivot, left);
			return left;
		} else {
			swap(arr, pivot, left - 1);
			return left - 1;
		}
	}
	
	int[] helperArr = new int[100];
	void mergeSort(int[] arr, int left, int right) {
		if (left < right) {
			int mid = left + (right - left) / 2;
			mergeSort(arr, left, mid);
			mergeSort(arr, mid + 1, right);
			merge(arr, left, mid, right);
		}
	}
	
	void merge(int[] arr, int left, int mid, int right) {
		for (int i = left; i <= right; i++) {
			helperArr[i] = arr[i];
		}
		
		int start1 = left;
		int start2 = mid + 1;
		int index = left;
		while (start1 <= mid && start2 <= right) {
			if (helperArr[start1] < helperArr[start2]) {
				arr[index++] = helperArr[start1];
				start1++;
			} else {
				arr[index++] = helperArr[start2];
				start2++;
			}
		}
		
		while (start1 <= mid) {
			arr[index++] = helperArr[start1];
			start1++;
		}
	}
	
	void swap(int[] arr, int left, int right) {
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}
}
