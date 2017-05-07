package company.linkedin;

import java.util.Arrays;

public class MinDifferenceIn2Arrays {

	public static void main(String[] args) {
		MinDifferenceIn2Arrays minDiff = new MinDifferenceIn2Arrays();
		
		int[] arr1 = {3, 27, 45, 68, 70, 81, 99};
		int[] arr2 = {9, 16, 25, 35, 60, 84};
		
		int res = minDiff.minDifference(arr1, arr2);
		System.out.println(res);
	}

	public int minDifference(int[] a, int[] b) {
		Arrays.sort(a);
		Arrays.sort(b);
		int min = Integer.MAX_VALUE;
		
		int pos1 = 0, pos2 = 0;
		while (pos1 < a.length && pos2 < b.length) {
			if (a[pos1] < b[pos2]) {
				min = Math.min(min, b[pos2] - a[pos1]);
				pos1++;
			} else {
				min = Math.min(min, a[pos1] - b[pos2]);
				pos2++;
			}
		}
		
		return min;
	}
	
	/**
	 * a中的每个元素都得用上，b的长度大于等于a，a中每个元素到b里面选一个对应的元素（必须按顺序取）使的差值的之和为最小。
我是举例子，math.abs(a1-b3)＋math.abs(a2-b9)+math.abs(a3-b17)为最小，不能sort是因为如果sort了，a和b的顺序就被打乱了。比如上面的例子，不能取a1-b3, a2-b1这种是不合法的。

// array[i][j] mean when we reach a[i] and we try to chose b[j]
// thus array[i][j] = min(array[i - 1][j - 1] + abs(a[i] - b[j]), array[i - 1][j])
// thus array[a.length][b.length] will be the result
	 */
	public int minDifferenceSum(int[] a, int[] b) {
		int[][] hash = new int[a.length + 1][b.length + 1];
		
		for (int i = 1; i <= a.length; i++) {
			hash[i][0] = Integer.MAX_VALUE;
		}
		
		for (int i = 1; i <= a.length; i++) {
			for (int j = 1; j <= b.length - (a.length - i); j++) { // 保证在b上留下空间 to match a
				hash[i][j] = Math.min(hash[i - 1][j - 1] + Math.abs(a[i - 1] - b[j - 1]), hash[i][j - 1]);
			}
		}
		
		return hash[a.length][b.length];
	}
	
	// What if B is super long
	// This solution has better space efficiency
	public int minDifferenceSumLong(int[] a, int[] b) {
		int[] hash = new int[a.length + 1];
		
		for (int i = 1; i <= a.length; i++) {
			hash[i] = Integer.MAX_VALUE;
		}
		
		for (int i = 0; i < b.length; i++) { // Start from index i in b, b is super long
			int[] temp = new int[a.length + 1];
			for (int j = 1; j <= a.length; j++) {
				if (b.length - i - 1 < a.length - j) {
					continue;
				}
				temp[j] = Math.min(hash[j], hash[j - 1] + Math.abs(b[i] - a[j - 1]));
			}
			
			hash = temp;
		}
		
		return hash[a.length];
	}
}
