package company.linkedin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 给一个数组， 给一个数字 k. 问能不能把数组里面的数字分到k个桶里面， 使得每个桶里面所有的数字和相同。 DFS暴力解。
// sort the array, always take the current number into the bucket whose sum is the smallest
 */
public class SplitArrayToKBuckets {

	public static void main(String[] args) {
		SplitArrayToKBuckets s = new SplitArrayToKBuckets();
		int[] arr = {1,2, 4, 5, 3, 8, 9, 4, 1, 3};
		int k = 4;
		System.out.println(s.canSplitBruteForce(arr, k));
	}

    
	// 贪婪算法，想法很好，但是不对！
	boolean canSplit(int[] arr, int k) {
		// Min Heap by default. This heap is the buckets
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(k);
		
		for (int i = 0; i < k; i++) {
			heap.offer(0);
		}
		
		Arrays.sort(arr);
		int sum = 0;
		for (int val : arr) {
			int smallestBucketInHeap = heap.poll();
			smallestBucketInHeap += val;
			sum += val;
			heap.offer(smallestBucketInHeap);
		}
		
		if (sum % k != 0) {
			return false;
		}
		
		while (!heap.isEmpty()) {
			int bucket = heap.poll();
			if (bucket != sum / k) {
				return false;
			}
		}
		
		return true;
	}
	
	boolean canSplitBruteForce(int[] arr, int k) {
		int sum = 0;
		
		for (int val : arr) {
			sum += val;
		}
		
		if (sum % k != 0) {
			return false;
		}
		
		int target = sum / k;
		
		List<Integer> list = new ArrayList<Integer>();
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		helper(arr, target, k, res, list, 0, 0);
		
		return flag;
	}

	boolean flag = false;
	private void helper(int[] arr, int target, int k, List<List<Integer>> res, List<Integer> list, int curSum, int pos) {
		if (flag) {
			return;
		}
		if (pos == arr.length) {
			if (curSum == target) {
				res.add(new ArrayList<>(list));
				if (res.size() == k) {
					flag = true;
				}
			}
			
			return;
		}
		if (res.size() == k) {
			return;
		}

		for (int i = pos; i < arr.length; i++) {
			list.add(arr[i]);
			helper(arr, target, k, res, list, curSum + arr[i], i + 1);
			list.remove(list.size() - 1);
		}
	}
}
