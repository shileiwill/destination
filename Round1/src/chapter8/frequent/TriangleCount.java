package chapter8.frequent;
// Given an array of positive integers, find how many possible combination of triangles
import java.util.Arrays;

/**
 * 1. A[i] + A[j] > A[k]
 * 2. A[i] + A[k] > A[j]
 * 3. A[j] + A[k] > A[i]
 */
public class TriangleCount {

	public static void main(String[] args) {

	}

	int triangleCount(int[] A) {
		Arrays.sort(A);
		int ans = 0;
		for (int i = 0; i < A.length; i++) {
			int left = 0; // A[i] must > A[left], A[i] must > A[right] as well. So, 1, 2 is satisfied, need to check 3rd
			int right = i - 1;
			int target = A[i];
			
			while(left < right) {
				if(target < A[left] + A[right]) {
					ans += right - left;
					right--;
				} else {
					left++;
				}
			}
		}
		
		return ans;
	}
}
