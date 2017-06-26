package company.uber;

import java.util.HashSet;
import java.util.Set;

/**
 * 3sum 变形 很简单 就要O(n^2)做法就可以了 给三个sorted array 和target sum 每个array拿一个加起来是target sum
 * 
 * 如果第二个Array和第三个Array是分别sorted, 我感觉不能用2 pointers. 假设当前的sum小了， move which pointer?
 * 两个pointers, 一个指向Array2的开头，一个指向Array3的结尾
 */
public class Sum3 {

	public static void main(String[] args) {
		int[] A = {1, 4, 7, 8};
		int[] B = {3, 9, 14};
		int[] C = {2, 4, 8, 9};
		
		Sum3 s3 = new Sum3();
		s3.sum3(A, B, C, 93);
		
		boolean bool = s3.sum3Map(A, B, C, 93);
		System.out.println(bool);
	}

	void sum3(int[] A, int[] B, int[] C, int target) {
		for (int i = 0; i < A.length; i++) {
			int pos1 = 0, pos2 = C.length - 1;
			while (pos1 < B.length && pos2 >= 0) {
				int sum = A[i] + B[pos1] + C[pos2];
				
				if (sum == target) {
					System.out.println(A[i] + " : " + B[pos1]  + " : " +  C[pos2]);
					pos1++;
					pos2--;
//					return;
				} else if (sum < target) {
					pos1++;
				} else {
					pos2--;
				}
			}
		}
	}
	
	/**
	 * 给了三个array, 和target, 问能不能从三个array 里面分别取一个数，加起来等于target. 
	 * Solution: 用HashMap 存前两个array 中所有数之和，然后遍历第三个array,判断map是否包含target-num。 O(n^2) time complexity
	 * 
	 * 跟4Sum II的思路是一模一样的， 先算俩的，再算俩的
	 */
	
	boolean sum3Map(int[] A, int[] B, int[] C, int target) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B.length; j++) {
				set.add(A[i] + B[j]);
			}
		}
		
		for (int i = 0;  i < C.length; i++) {
			if (set.contains(target - C[i])) {
				return true;
			}
		}
		return false;
	}
}
