package chapter8.frequent;
/**
 * 309. Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

prices = [1, 2, 3, 0, 2]
maxProfit = 3
transactions = [buy, sell, cooldown, buy, sell]
 *
 */
public class BuySellStockWithCoolDown {
    /**
        Define:

        profit1[i] = max profit on day i if I sell
        
        profit2[i] = max profit on day i if I do nothing
        How will those profits on day i+1 relate to profits on day i ?
        
        1. profit1[i+1] means I must sell on day i+1, and there are 2 cases:
        
        a. If I just sold on day i, then I have to buy again on day i and sell on day i+1
        
        b. If I did nothing on day i, then I have to buy today and sell today 
        
        Taking both cases into account, profit1[i+1] = max(profit1[i]+prices[i+1]-prices[i], profit2[i])
        
        2. profit2[i+1] means I do nothing on day i+1, so it will be max(profit1[i], profit2[i])
     * */
     
     // https://discuss.leetcode.com/topic/31349/7-line-java-only-consider-sell-and-cooldown
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int days = prices.length;
        
        int[] profit1 = new int[days]; // I must sell on day i
        int[] profit2 = new int[days]; // I must rest on day i
        
        profit1[0] = 0; // Buy and sell immediately
        profit2[0] = 0; // Just do nothing
        
        for (int i = 1; i < days; i++) { // Not implemented yet
            profit1[i] = Math.max(profit2[i - 1] + prices[i], -1);
            profit2[i] = Math.max(profit1[i - 1], profit2[i - 1]);
        }
        
        return -1;
    }
    
    // https://discuss.leetcode.com/topic/30421/share-my-thinking-process
    public int maxProfit2(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        
        // Before day i, what is the max profit which ends up with a "buy" (can be any day before i)
        // We dont have to do transactions on day i
        int days = prices.length;
        int[] buy = new int[days]; 
        int[] sell = new int[days];
        int[] rest = new int[days];
        
        buy[0] = -prices[0];
        sell[0] = 0;
        rest[0] = 0;
        
        for (int i = 1; i < days; i++) {
            buy[i] = Math.max(buy[i - 1], rest[i - 1] - prices[i]); // Here is only rest, not sell because of cooldown
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
            rest[i] = Math.max(rest[i - 1], Math.max(sell[i - 1], buy[i - 1]));
        }
        
        return Math.max(sell[days- 1], rest[days - 1]);
    }
}