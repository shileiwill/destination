package company.facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * 第二题似乎没在面经见过，给定一个超级大的排好序的list     
 * [1(a个), 2(b个), 3(c个), 4(d个), 5(e个)...] 要求返回 [{1, a}, {2, b}, {3, c}, {4, d},{5, e}....] 
 * 复杂度要优于O(n)。 lz当时第一反应是binary search 找上下界，然后小哥问我worst case，我说O(nlgn)，小哥说对啊，我要优于O(n)。 
 * 想了一会儿没想出来（印哥打来电话就迟到十分钟，眼看时间也过了一会儿了，心里可急），我问小哥有没有啥hint，他和我说了要啥啥啥综合一下mix一下啥啥啥。
 *  然后我才想起来地里有个first bad version的follow up很像。所以和烙印说 每次比较start point 和start point + 2^n位置上的数，
 *  假如一样就continue，不一样就在区间里面binary search找上界，这样的话worstcase O(n)。烙印说可以，我就开始写，大部分内容写完了。
 *  中间while循环写错了一个变量被他指出来，改了之后等我要写corner case的时候，烙印说不要写了问问题吧。
 *  
 *  给定一个数组, 已经sort好, 里面有大量重复的数, 输出每个数和这个数出现的次数. 先说扫一遍, 用hashmap计数, 问time complexity说是O(n), 不够好, 
 *  怎么快点儿, 答binary search. 这个题没写完, 什么时候更新left什么时候更新right挺复杂, 一个while loop好像很难搞定的样子. 
 *  // input [1 (X200), 2(X50), 3(X10)] 200个1, 50个2, 10个3 
 *  // count the occurrence of the elements // output { // 1: 200, // 2: 50, // 3: 10, // } 
 *  
 *  假设没有重复的数， 每次都找 2^(n-1) == 2^0 ==1 , 相当于依次 找1 找2 找3 找4 找5 发现都是出现一次， 遍历一遍， O(n) 
 *  如果有大量重复， 【1，1，1，1，2，2，2，3】，对于1， 找右边界是 依次 尝试 2^0=1, 2^1 =2, 2^2=4, 2^3=8, O(nlogn) 
 *  每次都是尝试 start point +2^n 这样，也实现了 log(n) 的复杂度， 这个思路是不是很genius, 不是我想出来的
 */
public class CountFrequencyInLargeList {

	public static void main(String[] args) {
		int[] arr = {1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 5, 5, 5, 5, 5, 5, 7, 8, 8, 9, 9, 9, 9, 9, 9};
		CountFrequencyInLargeList cf = new CountFrequencyInLargeList();
		
		Map<Integer, Integer> map = cf.count(arr);
		
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "==" + entry.getValue());
		}
	}

	Map<Integer, Integer> count(int[] arr) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < arr.length;) {
			int curVal = arr[i];
			
			int radix = 0;
			int pos = (int)(i + Math.pow(2, radix));
			while (pos < arr.length && arr[pos] == curVal) { // Jump, O(log(N))
				radix++;
				pos = (int)(i + Math.pow(2, radix));
			}
			
			int left = i;
			int right = (pos < arr.length) ? pos : arr.length - 1;
			
			// Binary search to find the right border of curVal
			int rightBorder = binarySearch(arr, left, right, curVal);
			
			map.put(curVal, rightBorder - left + 1);
			i = rightBorder + 1;
		}
		
		return map;
	}

	private int binarySearch(int[] arr, int left, int right, int curVal) {
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			
			if (arr[mid] == curVal) {
				left = mid; 
			} else {
				right = mid;
			}
		}
		
		if (arr[right] == curVal) {
			return right;
		}
		return left;
	}
}
