package chapter8.frequent1;
/**
 * 122. Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * @author Lei
 *
 */
public class BuySellStock2 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int profit = 0;
        
        // Buy and sell as long as there is benefit
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                int diff = prices[i] - prices[i - 1];
                profit += diff;
            }
        }
        
        return profit;
    }
}
