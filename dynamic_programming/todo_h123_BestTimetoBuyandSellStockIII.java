// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/

// Solution 1: only applied for this problem, at most 2 transactions.
// See solution 2 for more general solutions.

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        
        int[] maxProfitLeft = new int[n];
        int minLeft = prices[0];
        for (int i = 1; i < n; i++) {
            // maxProfitLeft count profit at current i
            maxProfitLeft[i] = Math.max(maxProfitLeft[i-1], prices[i] - minLeft);
            minLeft = Math.min(prices[i], minLeft);
        }
        
        int[] maxProfitRight = new int[n];
        int maxRight = prices[n-1];
        for (int i = n - 2; i >= 0; i--) {
            // maxProfitRight does not count profit at current i
            maxProfitRight[i] = Math.max(maxProfitRight[i+1], maxRight - prices[i+1]);
            maxRight = Math.max(prices[i], maxRight);
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, maxProfitLeft[i] + maxProfitRight[i]);
        }
        return res;
    }
}
