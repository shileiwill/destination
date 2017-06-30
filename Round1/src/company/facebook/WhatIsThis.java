package company.facebook;

import java.util.Random;

public class WhatIsThis {
/**
 * 给你一个unsorted integer array （array 很大，你只有一个iterator）和 一个用double表示的百分数（比如 0.4 代表40%）， 
 * 输出：sort array后处于40%位置的那个数的值。例子：输入[1 3 2 5 4 6 7 9 8 10], 和 0.6， 你就返回6. 方法就是bucket sort嘛，
 * 注意一些corner case， 比如输入0.3333什么的注意四舍五入
 */
	
	/**
	 * find index of maximum number in array, if multiple maximum numbers occurs, return a random index
	 */
	int findMaxRandom(int[] arr){
		int max = Integer.MIN_VALUE;
		int count = 0;
		int index = -1;
		Random ran = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == max) {
				count++;
				if (ran.nextInt(count) == 0) {
					index = i;
				}
			} else if (arr[i] > max) {
				max = arr[i];
				count = 1;
				index = i;
			}
		}
		
		return index;
	}
	
	public static void main(String[] args) {
		int[] arr = {3, 4, 1, 8, 9, 9, 4, 1, 9, 3};
		WhatIsThis what = new WhatIsThis();
		int res = what.findMaxRandom(arr);
		System.out.println(res);
	}
}
