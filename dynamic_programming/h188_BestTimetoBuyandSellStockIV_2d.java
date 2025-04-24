// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/

// Solution 2: Think of it as a state machine of 2 states, buys and sell, plus the number of transaction
// => buys[t] = max of buys[t], sells[t-1] - prices[d]. It means at day d, we can skip (buys[t]) or can buy (sells[t-1] - prices[d]). We use max to choose the best option.
// => sells[t] = max of sells[t] (skip day), or (buys[t] + prices[d]) (sell a hold stock). Notice that we can sell on the same day of buy, it won't affect result as prices[d] - prices[d] = 0;
// Iterate till k => we can have at most k transactions.
// Time complexity: O(d * k), k transactions, d days
// Space complexity: O(k)

class Solution {
    public int maxProfit(int k, int[] prices) {
        int days = prices.length;
        int[] buys = new int[k+1];
        int[] sells = new int[k+1];
        
        Arrays.fill(buys, Integer.MIN_VALUE);
        Arrays.fill(sells, Integer.MIN_VALUE);

        buys[0] = 0;
        sells[0] = 0;

        for (int d = 0; d < days; d++) {
            for (int t = 1; t <= k; t++) {
                buys[t] = Math.max(buys[t], sells[t-1] - prices[d]);
                sells[t] = Math.max(sells[t], buys[t] + prices[d]);
            }
        }

        return sells[k];
    }
}


// dp[t][day] is the maximum profit of maximum t transaction within "day" days
// Two cases:
//    - Skip day d => profit of previous day
//    - Sell at day d => get prices[d] and maxGain of at most (t-1) transactions + a buy = max gain = max of (profit[t-1][d-1] - prices[d]);
// notice that profit[t-1][d-1] and profit[t-1][d] won't affect the final result, since prices[d] will affect the profit and make it possible.
// Think of it as: we can sell at day (d-1) and buy at d, and sell at (d) and buy at d. It both works, since the profit[d] has been adjusted by prices[d]

class Solution {
    public int maxProfit(int k, int[] prices) {
        int days = prices.length;
        int[][] profit = new int[k+1][days];

        for (int t = 1; t <= k; t++) {
            /*maximize (prices[i] + max gain) */
            // max gain = max of (profit[t-1][d] - prices[d]);
            int maxGain = -prices[0];
            for (int day = 1; day < days; day++) {
                profit[t][day] = Math.max(profit[t][day-1], prices[day] + maxGain);
                maxGain = Math.max(maxGain, profit[t-1][day-1] - prices[day]);
            }
        }

        return profit[k][days-1];
    }
}
