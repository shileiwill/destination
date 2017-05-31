package chapter5.dp;

public class BackpackAll6 {
	/**
	 * Problem 单次选择+最大体积
	Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?
	 */
	int backpack1(int[] A, int m) {
		// dp[i][j]表示数组的前i个，书包空间为j的时候能装的A物品最大容量
		int[][] dp = new int[A.length + 1][m + 1];
		dp[0][0] = 0;
		
		// 前i个物品中取 书包体积为0
		for (int i = 1; i < dp.length; i++) {
			dp[i][0] = 0;
		}
		
		// 前0个物品中取 书包体积为i
		for (int i = 1; i < dp[0].length; i++) {
			dp[0][i] = 0;
		}
		
		for (int i = 1; i <= A.length; i++) { // 前i个物品
			for (int j = 1; j <= m; j++) { // 书包体积
				dp[i][j] = dp[i - 1][j];
				if (j - A[i - 1] >= 0) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]] + A[i - 1]);
				}
			}
		}
		
		return dp[A.length][m];
	}
	
	/**
	 * Problem 单次选择+最大价值
	   Given n items with size A[i] and value V[i], and a backpack with size m. What's the maximum value can you put into the backpack?
	 */
	int backpack2(int[] A, int[] V, int m) {
		// dp[i][j]表示数组的前i个，书包空间为j的时候能装的A物品最大Value
		int[][] dp = new int[A.length + 1][m + 1];
		dp[0][0] = 0;
		
		// 前i个物品中取 书包体积为0
		for (int i = 1; i < dp.length; i++) {
			dp[i][0] = 0;
		}
		
		// 前0个物品中取 书包体积为i
		for (int i = 1; i < dp[0].length; i++) {
			dp[0][i] = 0;
		}
		
		for (int i = 1; i <= A.length; i++) { // 前i个物品
			for (int j = 1; j <= m; j++) { // 书包体积
				dp[i][j] = dp[i - 1][j];
				if (j - A[i - 1] >= 0) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]] + V[i - 1]); // Here is the only thing changed
				}
			}
		}
		
		return dp[A.length][m];
	}
	
	/**
	 * Problem 重复选择+最大价值
	   Given n kind of items with size Ai and value Vi( each item has an infinite number available) and a backpack with size m. What's the maximum value can you put into the backpack?
	 */
	int backpack3(int[] A, int[] V, int m) {
		// dp[i][j]表示数组的前i个，书包空间为j的时候能装的A物品最大Value
		int[][] dp = new int[A.length + 1][m + 1];
		dp[0][0] = 0;
		
		// 前i个物品中取 书包体积为0
		for (int i = 1; i < dp.length; i++) {
			dp[i][0] = 0;
		}
		
		// 前0个物品中取 书包体积为i
		for (int i = 1; i < dp[0].length; i++) {
			dp[0][i] = 0;
		}
		
		for (int i = 1; i <= A.length; i++) { // 前i个物品
			for (int j = 1; j <= m; j++) { // 书包体积
				dp[i][j] = dp[i - 1][j]; // First get a value
				if (j - A[i - 1] >= 0) {
					dp[i][j] = Math.max(dp[i][j], dp[i][j - A[i - 1]] + V[i - 1]); // Change here
				}
			}
		}
		
		return dp[A.length][m];
	}
	
	/**
	 * Problem 重复选择+唯一排列+装满可能性总数
	   Given n items with size nums[i] which an integer array and all positive numbers, no duplicates. 
	   An integer target denotes the size of a backpack. Find the number of possible fill the backpack.
	   Each item may be chosen unlimited number of times
	 */
	int backpack4(int[] A, int m) {
		// dp[i][j]表示数组的前i个，书包空间为j的时候能装的A物品最大Value
		int[][] dp = new int[A.length + 1][m + 1];
		dp[0][0] = 1;
		
		// 前i个物品中取 书包体积为0
		for (int i = 1; i < dp.length; i++) {
			dp[i][0] = 1;
		}
		
		// 前0个物品中取 书包体积为i
		for (int i = 1; i < dp[0].length; i++) {
			dp[0][i] = 0;
		}
		
		for (int i = 1; i <= A.length; i++) { // 前i个物品
			for (int j = 1; j <= m; j++) { // 书包体积
				dp[i][j] = dp[i - 1][j]; // First get a value
				if (j - A[i - 1] >= 0) {
					dp[i][j] += dp[i][j - A[i - 1]]; // Change here. 从最优方案到方案总数，只需要把max换成加号就行了
				}
			}
		}
		
		return dp[A.length][m];
	}
	
	/**
	 * Problem 单次选择+装满可能性总数
	   Given n items with size nums[i] which an integer array and all positive numbers. 
	   An integer target denotes the size of a backpack. Find the number of possible fill the backpack.
       Each item may only be used once
	 */
	int backpack5(int[] A, int m) {
		// dp[i][j]表示数组的前i个，书包空间为j的时候能装的A物品最大Value
		int[][] dp = new int[A.length + 1][m + 1];
		dp[0][0] = 1;
		
		// 前i个物品中取 书包体积为0
		for (int i = 1; i < dp.length; i++) {
			dp[i][0] = 1;
		}
		
		// 前0个物品中取 书包体积为i
		for (int i = 1; i < dp[0].length; i++) {
			dp[0][i] = 0;
		}
		
		for (int i = 1; i <= A.length; i++) { // 前i个物品
			for (int j = 1; j <= m; j++) { // 书包体积
				dp[i][j] = dp[i - 1][j]; // First get a value
				if (j - A[i - 1] >= 0) {
					dp[i][j] += dp[i - 1][j - A[i - 1]]; // Change here. 从最优方案到方案总数，只需要把max换成加号就行了
				}
			}
		}
		
		return dp[A.length][m];
	}
	
	/**
	 * Backpack VI aka: Combination Sum IV
	   Problem 重复选择+不同排列+装满可能性总数
	   Given an integer array nums with all positive numbers and no duplicates, 
	   find the number of possible combinations that add up to a positive integer target.
	   The different sequences are counted as different combinations. This is the same with climbing stairs
	 */
	int backpack6(int[] A, int m) {
		int[] dp = new int[m + 1];
		dp[0] = 1;
		
		for (int i = 1; i <= m; i++) { // Package size
			for (int j = 0; j < A.length; j++) {
				if (i - A[j] >= 0) {
					dp[i] += dp[i - A[j]];
				}
			}
		}
		
		return dp[m];
	}
	
	public static void main(String[] args) {
		int[] A = {1, 2, 4};
		int[] V = {1, 5, 2, 4};
		BackpackAll6 b6 = new BackpackAll6();
		int res = b6.backpack6(A, 4);
		System.out.println(res);
	}
}
