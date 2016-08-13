package chapter8.frequent;
/**
 * 121. Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5

max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0

In this case, no transaction is done, i.e. max profit = 0.
 * @author Lei
 *
 */
public class BuySellStock1 {
    
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int minPrice = Integer.MAX_VALUE; // Remember the lowest price
        int profit = 0;
        
        for (int price : prices) {
            minPrice = Math.min(price, minPrice);
            profit = Math.max(profit, price - minPrice);
        }
        
        return profit;
    }
    
    // This solution is not correct. [7,1,5,3,6,4]
    public int maxProfit2(int[] prices) {
        
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // If sell at the ith day, how much we can get at most
        int[] hash = new int[prices.length];
        hash[0] = 0;
        
        for (int i = 1; i < prices.length; i++) {
            hash[i] = Math.max(hash[i - 1], prices[i] - prices[i - 1]);
        }
        
        return hash[prices.length - 1];
    }
}