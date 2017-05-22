package company.uber;

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
		s3.sum3(A, B, C, 22);
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
}
