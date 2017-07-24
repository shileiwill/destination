package company.uber;

public class MakingChange {
	// Time complexity of this method is (arr.length * target)
	int makingChange(int[] arr, int target) {
		// Use first i item to form target j, how many solutions?
		int[][] hash = new int[arr.length + 1][target + 1]; // 不能重复使用，必须二维数组
		
		for (int i = 0; i < hash.length; i++) {
			hash[i][0] = 1; // From first i items to form target 0, it has 1 solution. Just pick up nothing.
		}
		
		for (int i = 1; i < hash[0].length; i++) {
			hash[0][i] = 0; // From first 0 items to form target i(>=1), it has 0 solution. Impossible.
		}
		
		for (int i = 1; i <= arr.length; i++) {
			for (int t = 1; t <= target; t++) {
				hash[i][t] = hash[i - 1][t]; // Default value is using first (i - 1) coins
				if (t - arr[i - 1] >= 0) {
					hash[i][t] += hash[i][t - arr[i - 1]]; // Coins could be used repeatedly, so it is hash[i][t - arr[i - 1]]
				}
			}
		}
		
		return hash[arr.length][target]; // Final result
	}
	
	public static void main(String[] args) {
		MakingChange tm = new MakingChange();
	    int[] arr = {1, 2, 5, 10};
	    int target = 13;
	    int res = tm.makingChange(arr, target);
	    System.out.println(res);
	}
}