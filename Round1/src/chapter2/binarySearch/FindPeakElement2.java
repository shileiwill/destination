package chapter2.binarySearch;
/**
 * There is an integer matrix which has the following features:
The numbers in adjacent positions are different. The matrix has n rows and m columns.
For all i < m, A[0][i] < A[1][i] && A[n - 2][i] > A[n - 1][i].
For all j < n, A[j][0] < A[j][1] && A[j][m - 2] > A[j][m - 1].
We define a position P is a peek if:
A[j][i] > A[j+1][i] && A[j][i] > A[j-1][i] && A[j][i] > A[j][i+1] && A[j][i] > A[j][i-1]
Find a peak element in this matrix. Return the index of the peak.
Have you met this question in a real interview? Yes Example Given a matrix:
[
  [1 ,2 ,3 ,6 ,5],
  [16,18,23,26,6],
  [15,17,22,21,7],
  [14,18,19,20,10],
  [13,14,11,10,9]
]
return index of 41 (which is [1,1]) or index of 24 (which is [2,2])

从中间行，找到这一行的最大数
如果上面数比它大，则上半部(rows)必然有一个Peak，二分法

 */
public class FindPeakElement2 {

	public static void main(String[] args) {

	}

	public int[] findPeakII(int[][] A) {
		int top = 0;
		int bottom = A.length - 1;
		
		while (top <= bottom) {
			int midRow = top + (bottom - top) / 2;
			int peakCol = findMax(A[midRow]); // Find the max element in that row
			
			if (A[midRow][peakCol] > A[midRow - 1][peakCol] && A[midRow][peakCol] > A[midRow + 1][peakCol]) {
				int[] res = {midRow, peakCol};
				return res;
			}
			if (A[midRow][peakCol] < A[midRow - 1][peakCol]) {
				bottom = midRow - 1;
			} else {
				top = midRow + 1;
			}
		}
		return null;
	}
	
	int findMax(int[] A) {
		int max = 0;
		for (int i = 1; i < A.length; i++) {
			if (A[i] > A[max]) {
				max = i;
			}
		}
		return max;
	}
}
