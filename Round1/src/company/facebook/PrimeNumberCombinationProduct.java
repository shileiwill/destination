package company.facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给一个质数数组，返回所有可能的product，顺序不管
比如给 [2,3,5] 返回 [2,3,5,6,10,15,30]
数组中的数如果有重复则需要去重，不允许用set。
比如给 [2,2,2] 返回 [2,4,8]，顺序不用管。

那个很简单，就是基本的combination题，就是DFS
然后乘积的集合不用查重，因为题目说了给你一堆unique的prime number，他们乘出来的结果肯定没有duplicate
 */
public class PrimeNumberCombinationProduct {

	public static void main(String[] args) {
		PrimeNumberCombinationProduct pn = new PrimeNumberCombinationProduct();
		int[] arr = {2,2,2};
		List<Integer> res = pn.productCombination(arr);
		for (int val : res) {
			System.out.print(val + " -- ");
		}
	}

	List<Integer> productCombination(int[] arr) {
		Arrays.sort(arr);
		List<Integer> res = new ArrayList<Integer>();
		helper(arr, res, 1, 0);
		return res;
	}

	// Product一直在变， 随时加到result中。这是combination问题的变种
	private void helper(int[] arr, List<Integer> res, int product, int pos) {
		if (product != 1) { // 那如果数组中本身有1呢， 1是质数吗
			res.add(product);
		}

		if (pos == arr.length) {
			return;
		}
		
		for (int i = pos; i < arr.length; i++) {
			if (i != pos && arr[i] == arr[i - 1]) {
				continue;
			}
			helper(arr, res, product * arr[i], i + 1);
		}
	}
}
