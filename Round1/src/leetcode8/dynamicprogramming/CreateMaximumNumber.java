package leetcode8.dynamicprogramming;

import java.util.Stack;
/**
 * 321. Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.

Example 1:
nums1 = [3, 4, 6, 5]
nums2 = [9, 1, 2, 5, 8, 3]
k = 5
return [9, 8, 6, 5, 3]

Example 2:
nums1 = [6, 7]
nums2 = [6, 0, 4]
k = 5
return [6, 7, 6, 0, 4]

Example 3:
nums1 = [3, 9]
nums2 = [8, 9]
k = 3
return [9, 8, 9]
 */
public class CreateMaximumNumber {
// http://52.20.106.37/create-maximum-number/
// https://discuss.leetcode.com/topic/32272/share-my-greedy-solution
	public static void main(String[] args) {
		int[] arr1 = {9, 4, 7, 5, 8, 3};
		int[] arr2 = {1, 8, 3};
		int[] res = maxNumber(arr1, arr2, 3);
		for (int val : res) {
			System.out.print(val + " -- ");
		}
	}

	static int[] maxNumber(int[] arr1, int[] arr2, int k) {
		int m = arr1.length;
		int n = arr2.length;
		int[] res = new int[k];
		
		// count1 stands for the count of numbers chosen in array1
		for (int count1 = Math.max(0, k - n); count1 <= m && count1 <= k; count1++) {
			int[] A1 = maxNumberWith1Array(arr1, count1);
			int[] A2 = maxNumberWith1Array(arr2, k - count1);
			
			int[] candidate = maxNumberWith2Arrays(A1, A2, k);
			
			if (greater(candidate, 0, res, 0)) {
				res = candidate;
			}
		}
		
		return res;
	}
	/**
	 Given one array of length n, create the maximum number of length k.

The solution to this problem is Greedy with the help of stack. The recipe is as following

Initialize a empty stack
Loop through the array nums
pop the top of stack if it is smaller than nums[i] until
stack is empty
the digits left is not enough to fill the stack to size k
if stack size < k push nums[i]
Return stack
	 */
	static int[] maxNumberWith1Array(int[] arr, int k) {
		Stack<Integer> stack = new Stack<Integer>();
		
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && stack.peek() < arr[i] && (stack.size() + arr.length - i) > k) {
				stack.pop();
			}
			stack.push(arr[i]);
		}
		
		int[] res = new int[k];
		while (stack.size() > k) {
			stack.pop();
		}
		while (!stack.isEmpty() && k > 0) {
			Integer pop = stack.pop();
			res[k - 1] = pop;
			k--;
		}
		return res;
	}
	
	/*
	 * Given two array of length m and n, create maximum number of length k = m + n.
	 */
	static int[] maxNumberWith2Arrays(int[] arr1, int[] arr2, int k) {
		int[] res = new int[k];
		
		int i = 0, j = 0;
		for (int m = 0; m < k; m++) {
			res[m] = greater(arr1, i, arr2, j) ? arr1[i++] : arr2[j++];
		}
		
		return res;
	}
	
	static boolean greater(int[] arr1, int i, int[] arr2, int j) {
		while (i < arr1.length && j < arr2.length && arr1[i] == arr2[j]) {
			i++;
			j++;
		}
		// arr2 is to the end (they are equal until the end) OR Neither of them are to the end and array1 is larger
		return (j == arr2.length) || (i < arr1.length && arr1[i] > arr2[j]);
	}
}
