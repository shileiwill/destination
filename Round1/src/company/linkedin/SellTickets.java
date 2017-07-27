package company.linkedin;

import java.util.Arrays;

/**
 * 铁路是售票处有n个售票窗口，每个窗口有a[i]张票。每张票的价格等于当时剩下车票的数量。当已经有m张车票卖出时，售票处最多还能赚多少钱？
 */
public class SellTickets {

	public static void main(String[] args) {

	}

	/*
	 * 因为票的价格就是arr[i], 所以那个窗口的钱就是arr[i] * arr[i].
	 * 是不是一直卖最便宜的票就行？
	 */
	int sellTickets(int[] arr, int m) {
		Arrays.sort(arr);
		
		int i = 0;
		for (i = 0; i < arr.length; i++) {
			while (arr[i] > 0 && m > 0) {
				arr[i]--;
				m--;
			}
		}
		
		int res = 0;
		for (; i < arr.length; i++) {
			res += arr[i];
		}
		
		return res;
	}
}
