package company.tripadvisor.trialpay;

public class Stock2 {

	public static void main(String[] args) {

	}

	int maxProfit(int[] prices) {
		int len = prices.length;
		if (len < 2) {
			return 0;
		}
		
		int[] left = new int[len];
		int[] right = new int[len];
		
		left[0] = 0;
		int min = prices[0];
		for (int i = 1; i < len; i++) {
			left[i] = Math.max(left[i - 1], prices[i] - min);
			min = Math.min(min, prices[i]);
		}
		
		right[len - 1] = 0;
		int max = prices[len - 1];
		for (int i = len - 2; i >= 0; i--) {
			right[i] = Math.max(right[i + 1], max - prices[i]);
			max = Math.max(max, prices[i]);
		}
		
		int res = 0;
		for (int i = 0; i < len; i++) {
			res = Math.max(res, left[i] + right[i]);
		}
		
		return res;
	}
	
	// What if at most K transactions
	public int maxProfit(int k, int[] prices) {
		int len = prices.length;
		// Max profit of K transactions in past i days
		int[][] hash = new int[len][k + 1];
		
		for (int times = 1; times <= k; times++) {
			int max = -prices[0];// Buy in at the first day
			for (int day = 1; day < len; day++) {
				hash[day][times] = Math.max(hash[day - 1][times], prices[day] + max); // Sell on that day
				max = Math.max(max, hash[day][times - 1] - prices[day]); // Buy on that day
			}
		}
		
		return hash[len - 1][k];
	}
}
