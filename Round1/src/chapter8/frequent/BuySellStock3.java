package chapter8.frequent;
/**
 * 123. Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * @author Lei
 *
 */
public class BuySellStock3 {
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