// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

// Use greedy approach
// At day i, we take profit
// At day i + 1:
// - If prices[i+1] > prices[i] => We take another profit. It can be think of we rebuy the stock at day i, then sell at day i + 1, because it maximizes our profit.
// - If prices[i+1] <= prices[i] => We already take profit yesterday => we are all good.

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int res = 0;
        for (int i = 1; i < n; i++) {
            res += Math.max(0, prices[i] - prices[i-1]);
        }
        return res;
    }
}
