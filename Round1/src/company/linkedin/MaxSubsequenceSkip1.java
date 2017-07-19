package company.linkedin;
/**
 * max subsequence skip one（也就是说，给一个int array，求max sum的subsequence，你可以跳过一些element，但是不能连续跳两个。举个栗子，[1，-1，-2，3，4] -> 7）
 */
public class MaxSubsequenceSkip1 {

	public static void main(String[] args) {
		MaxSubsequenceSkip1 mss = new MaxSubsequenceSkip1();
		int[] arr = {1, 3, -2, -3, 4};
		
		int res = mss.maxSkip1(arr);
		System.out.println(res);
	}

	int maxSkip1(int[] arr) {
		int[] hash1 = new int[arr.length]; // Yes, take this one
		int[] hash2 = new int[arr.length]; // No, don't take this one
		
		hash1[0] = arr[0];
		hash2[0] = 0;
		
		for (int i = 1; i < arr.length; i++) {
			hash1[i] = arr[i] + Math.max(hash1[i - 1], hash2[i - 1]);
			hash2[i] = hash1[i - 1]; // 必须取前边的一个
		}
		
		return Math.max(hash1[arr.length - 1], hash2[arr.length - 1]);
	}
}
