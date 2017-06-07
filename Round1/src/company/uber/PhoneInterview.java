package company.uber;
import java.util.*;

public class PhoneInterview {
	// Time complexity of this method is (arr.length * target)
	int makingChange(int[] arr, int target) {
		// Use first i item to form target j, how many solutions?
		int[][] hash = new int[arr.length + 1][target + 1];
		
		for (int i = 0; i < hash.length; i++) {
			hash[i][0] = 1; // From first i items to form target 0, it has 1 solution. Just pick up nothing.
		}
		
		for (int i = 1; i < hash[0].length; i++) {
			hash[0][i] = 0; // From first 0 items to form target i(>=1), it has 0 solution. Impossible.
		}
		
		for (int i = 1; i <= arr.length; i++) {
			for (int t = 1; t <= target; t++) {
				hash[i][t] = hash[i - 1][t]; // Default value is using first (i - 1) coins
				if (t - arr[i - 1] >= 0) {
					hash[i][t] += hash[i][t - arr[i - 1]]; // Coins could be used repeatedly, so it is hash[i][t - arr[i - 1]]
				}
			}
		}
		
		return hash[arr.length][target]; // Final result
	}
	
	public static void main(String[] args) {
		PhoneInterview tm = new PhoneInterview();
	    int[] arr = {1, 2, 5, 10};
	    int target = 7;
	    int res = tm.makingChange(arr, target);
	    System.out.println(res);
	}
}

//
////This is the text editor interface. 
////Anything you type or change here will be seen by the other person in real time.
//
///*
//Making change.
//
//integer number... 4 cents.
//a set of coin denominations (1, 2, 5, 10)
//
//1 + 1 + 1 + 1 = 1
//1 + 1 + 2 = 1
//2 + 2 = 1
//
//1 2 1 != 1 1 2
//
//4 => 3 possible ways
//*/
//
//class TestMain {
// // Coins can be repeatedly selected
// // we assume input is valid
// int makingChange(int[] arr, int target) {
//     // hash[i] stands for how many ways to form i
//     int[] hash = new int[target + 1];
//     hash[0] = 1; // Start point, initialization
//     
//     for (int i = 1; i <= target; i++) {
//         hash[i] = 0; // No necessary, because it is 0 by default. Just want to be clear
//         
//         for (int j = 0; j < arr.length; j++) { // Every coin again and again, since coins can be used repeatedly
//             if (i - arr[j] >= 0) { // The coin will count only if it is smaller or equal to i
//                 hash[i] += hash[i - arr[j]]; // A new possibility
//             }
//         }
//     }
//     
//     return hash[target];
// }
// 
// // Stack overflow
// 
// In Java, Stack and Heap;
// Object obj = new Object();
// recursion
// Every time we go deeper in recursion
// 
// // Java, Go. Javascript, nodejs
// 
// // N coins, target. The smallest coin is 1, the max coins are target
// // Math.pow(N, target)
// int count = 0; // public variable.
// int makingChange3(int[] arr, int target) {
//     //Arrays.sort(arr);
//     // First x coins to form target y.
//     Integer[] hash = new Integer[arr.length];
//     helper(arr, target, 0, hash);
//     
//     return count;
// }
// 
// // 2 1 1 will never happen. we got 1 1 2
// void helper(int[] arr, int target) {
//     if (target == 0) { // One solution
//         count++;
//         
//         return;
//     }
//     
//     if (target < 0 || pos == arr.length) {
//         return;
//     }
//     
//     for (int i = 0; i < arr.length; i++) {
//         //shrink the array here
//         int[] newArr = shrink(arr, pos);
//         helper(arr, target - arr[i]); // Use i here since coins can be reused
//         // add Back the lost elements
//     }
// }
// 
// // Backtracking
// int makingChange2(int[] arr, int target) {
//     //Arrays.sort(arr);
//     List<List<Integer>> res = new ArrayList<List<Integer>>();
//     List<Integer> list = new ArrayList<Integer>();
//     
//     helper(res, list, arr, target, 0);
//     
//     return res.size();
// }
// 
// // 2 1 1 will never happen. we got 1 1 2
// void helper(List<List<Integer>> res, List<Integer> list, int[] arr, int target, int pos) {
//     if (target == 0) { // One solution
//         res.add(new ArrayList<Integer>(list));
//         return;
//     }
//     
//     if (target < 0 || pos == arr.length) {
//         return;
//     }
//     
//     for (int i = pos; i < arr.length; i++) {
//         list.add(arr[i]);
//         helper(res, list, arr, target - arr[i], i); // Use i here since coins can be reused
//         list.remove(list.size() - 1);
//     }
// }
// 
// public static void main(String[] args) {
//     TestMain tm = new TestMain();
//     int[] arr = {1, 2, 5, 10};
//     int target = 7;
//     int res = tm.makingChange3(arr, target);
//     System.out.println(res);
// }
//}