package chapter8.frequent;
/**
 * 123. Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *
 */
public class BuySellStock3 {
    public int maxProfitBruce(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int[] buy1 = new int[prices.length];
        int[] sell1 = new int[prices.length];
        int[] buy2 = new int[prices.length];
        int[] sell2 = new int[prices.length];
        
        buy1[0] = -prices[0];
        sell1[0] = 0;
        buy2[0] = Integer.MIN_VALUE;
        sell2[0] = Integer.MIN_VALUE;
        
        for (int i = 1; i < prices.length; i++) {
            buy1[i] = Math.max(buy1[i - 1], 0 - prices[i]); // First time to buy, not sell1[i - 1] - price
            sell1[i] = Math.max(sell1[i - 1], buy1[i - 1] + prices[i]);
            buy2[i] = Math.max(buy2[i - 1], sell1[i - 1] - prices[i]);
            sell2[i] = Math.max(sell2[i - 1], buy2[i - 1] + prices[i]);
        }
        
        int res = Math.max(sell1[prices.length - 1], sell2[prices.length - 1]);
        return res;
    }
    
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];
        
        // From Left to Right DP
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < left.length; i++) {
            left[i] = Math.max(left[i - 1], prices[i] - min);
            min = Math.min(prices[i], min);
        }
        
        // From right to left DP
        right[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            right[i] = Math.max(max - prices[i], right[i + 1]);
            max = Math.max(max, prices[i]);
        }
        
        // Merge. Find a place where 2 values together is maximum
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            res = Math.max(res, left[i] + right[i]);
        }
        
        return res;
    }
}