package chapter8.frequent;
/**
 * 188. Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * @author Lei
 *
 */
public class BuySellStock4 {

	public static void main(String[] args) {
		BuySellStock4 s4 = new BuySellStock4();
		int[] prices = {1,2,4};
		int res = s4.maxProfit(2, prices);
		System.out.println(res);
	}
	
	// A new version
    private int greedy(int[] prices) {
        int r = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                r += prices[i] - prices[i - 1];
            }
        }
        return r;
    }
    public int maxProfit2(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
    
        if (k > prices.length / 2) { // buy on day 1 and sell on day 2
            return greedy(prices);
        }
        // 前i天进行j次交易能获得的最大利润
        int[][] hash = new int[prices.length][k + 1];
        
        for (int i = 0; i < hash.length; i++) {
            hash[i][0] = 0; // 前i天进行0次交易
        }
        for (int i = 1; i < hash[0].length; i++) {
            hash[0][i] = 0; // 前0天进行j次交易
        }
        
        for (int i = 1; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                int max = 0;
                for (int x = 0; x < i; x++) { // Find max
                    max = Math.max(max, hash[x][j - 1] + prices[i] - prices[x]);
                }
                hash[i][j] = Math.max(hash[i - 1][j], max);
            }
        }
        // We dont loop here, as more transactions mean more chance to get more money
        return hash[prices.length - 1][k];
    }
    
    // O(N2) solution
    public int maxProfit3(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
    
        if (k > prices.length / 2) {
            return greedy(prices);
        }
        // 前i天进行j次交易能获得的最大利润
        int[][] hash = new int[prices.length][k + 1];
        
        for (int i = 0; i < hash.length; i++) {
            hash[i][0] = 0; // 前i天进行0次交易
        }
        for (int i = 1; i < hash[0].length; i++) {
            hash[0][i] = 0; // 前0天进行j次交易
        }
        
        for (int j = 1; j <= k; j++) { // Number of transactions
            int maxDiff = 0 - prices[0]; // maintain a variable here to 之前买了没卖
            for (int i = 1; i < prices.length; i++) {
                hash[i][j] = Math.max(hash[i - 1][j], prices[i] + maxDiff);
                maxDiff = Math.max(maxDiff, hash[i][j - 1] - prices[i]);
            }
        }
        
        // We dont need to do exactly k transactions, so need to loop.
        return hash[prices.length - 1][k];
    }
    
	public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // 前i天进行j次交易能获得的最大利润, 第i天没必要必须进行交易
        int[][] hash = new int[prices.length + 1][k + 1];
        
        // Initialization
        for (int i = 0; i <= prices.length; i++) {
            hash[i][0] = 0; // 前i天进行0次交易
        }
        
        for (int i = 1; i <= k; i++) {
            hash[0][i] = Integer.MIN_VALUE; // This is impossible. 前0天进行i次交易
        }
        
        for (int i = 1; i <= prices.length; i++) {
            for (int j = 1; j <= k && j <= i; j++) {
                for (int x = 0; x < i; x++) { 
                    hash[i][j] = Math.max(hash[i][j], hash[x][j - 1] + getMaxProfitBetween(x + 1, i, prices));
                }
            }
        }
        
        // We dont have to do exactly k times transactions. 
        // Still need to go through last line to figure our the max profit
        int max = 0;
        for (int j = 0; j < hash[hash.length - 1].length; j++) {
        	max = Math.max(hash[hash.length - 1][j], max);
        }
        return max;
        // return hash[prices.length][k];
    }
    
	// Be careful with corner cases, what is start, end
    int getMaxProfitBetween(int start, int end, int[] prices) {
        if (start >= end) {
            return 0;
        }
        
        int minPrice = prices[start - 1];
        int maxProfit = 0;
        for (int i = start; i < end; i++) {
            maxProfit = Math.max(prices[i] - minPrice, maxProfit);
            minPrice = Math.min(minPrice, prices[i]);
        }
        
        return maxProfit;
    }
}