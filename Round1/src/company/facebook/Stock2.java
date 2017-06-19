package company.facebook;

public class Stock2 {

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
		
//		right[len - 1] = 0;
//		int max = prices[len - 1];
//		for (int i = len - 2; i >= 0; i--) {
//			right[i] = Math.max(right[i + 1], max - prices[i]);
//			max = Math.max(max, prices[i]);
//		}
//		
//		int res = 0;
//		for (int i = 0; i < len; i++) {
//			res = Math.max(res, left[i] + right[i]);
//		}
		
		// What if removing 1 for loop
		int max = prices[len - 1];
		int maxDiff = 0;
		int res = left[len - 1] + 0;
		for (int i = len - 2; i >= 0; i--) {
			maxDiff = Math.max(maxDiff, max - prices[i]);
			max = Math.max(max, prices[i]);
			res = Math.max(res, left[i] + maxDiff);
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
				// Buy on that day. tmpMax means the maximum profit of just doing at most i-1 transactions, 
				// using at most first j-1 prices, and buying the stock at price[j] - this is used for the next loop.
				max = Math.max(max, hash[day - 1][times - 1] - prices[day]); 
			}
		}
		
		return hash[len - 1][k];
	}
	
	//Stock with charge/fee:
		// maintain a veriable which is the profit we make 
		// when the prices is continuously acending,
		// when today's prices is lower than yesterday,
		// which means we finish one transaction, 
		// calculate the final profit with charge, if > 0 add to result
	/*
	 * 我觉得你的方法是对的，plus可以用一个Stack存过去的交易，每次有一个新的可行交易的时候，和栈顶的交易比较能否合并。 
	 * 这样省空间，并且one pass。 
	 * 补充内容 (2017-3-26 10:30): 又想了一下，不需要Stack，只需要存前一次交易就可以了，合并当次交易的前提是 
	 * 当次交易的买入+交易费 > 前次交易的卖出 所以不会有连锁合并的情况，只存前一次就可以了
	 */
	public static int maxProfitWithCharge(int[] prices, int charge) {
		    int profit = 0;
		    int localProfit = 0;
		    boolean yesterdaySold = false;
		    int lastMin = prices[0];
		    for (int i = 1; i < prices.length; i++) {
		        if (prices[i] >= prices[i - 1]) {// 价格一直在递增
		            localProfit += prices[i] - prices[i - 1];
		        } else { // 价格开始drop了，考虑出手
		            if (prices[i] + charge < prices[i - 1]) { // 当次交易的买入+交易费 > 前次交易的卖出
		            	
		            }
		        	profit += localProfit > 0 ? localProfit : 0;
		            localProfit = 0;
		            yesterdaySold = false;
		        }
		    }
		    if (localProfit > 0) {
		        profit += localProfit;
		    }
		    return profit;
	}
	
	public static void main(String[] args) {
		int[] prices = {1, 3, 4, 4, 4, 8};
		int charge = 1;
		int res = maxProfitWithCharge(prices, charge);
		System.out.println(res);
	}
}
