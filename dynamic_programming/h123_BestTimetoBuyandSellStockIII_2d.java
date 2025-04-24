// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/

// Solution 7: same as solution 6, but think of it as a state machine.
// Reference: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/solutions/149383/easy-dp-solution-using-state-machine-o-n-time-complexity-o-1-space-complexity/

class Solution {
    public int maxProfit(int[] prices) {
        int s1 = Integer.MIN_VALUE, s2 = Integer.MIN_VALUE, s3 = Integer.MIN_VALUE, s4 = Integer.MIN_VALUE;

        for (int i = 0; i < prices.length; i++) {
            s1 = Math.max(s1, -prices[i]);
            s2 = Math.max(s2, s1 + prices[i]);
            s3 = Math.max(s3, - prices[i] + s2);
            s4 = Math.max(s4, s3 + prices[i]);
        }
        
        return s4;
    }
}

// Solution 6: Based on solution 5.
// Reference: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/

class Solution {
    public int maxProfit(int[] prices) {
        int buy1 = Integer.MAX_VALUE, buy2 = Integer.MAX_VALUE;
        int sell1 = 0, sell2 = 0;

        for (int i = 0; i < prices.length; i++) {
            buy1 = Math.min(buy1, prices[i]);
            sell1 = Math.max(sell1, prices[i] - buy1);
            buy2 = Math.min(buy2, prices[i] - sell1);
            sell2 = Math.max(sell2, prices[i] - buy2);
        }
        
        return sell2;
    }
}

// Solution 5: Switch the 2 for loops, it becomes
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;        
        int[] profit = new int[3];
        int[] maxProfitGain = new int[3];
        Arrays.fill(maxProfitGain, -prices[0]);

        // i = number of transaction
        for (int day = 1; day < n; day++) {
            for (int i = 1; i <= 2; i++) {
                maxProfitGain[i-1] = Math.max(maxProfitGain[i-1], profit[i-1] - prices[day]);
                profit[i] = Math.max(profit[i], prices[day] + maxProfitGain[i-1]);
            }
        }
        
        return profit[2];
    }
}

// Solution 4: Same as solution 3. But shorter implementation
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;        
        int[] prevProfit = new int[n];
        int[] profit = new int[n];

        // i = number of transaction
        for (int i = 1; i <= 2; i++) {
            profit = new int[n];
            int maxProfitGain = -prices[0];

            for (int day = 1; day < n; day++) {
                profit[day] = Math.max(profit[day-1], prices[day] + maxProfitGain);

                maxProfitGain = Math.max(maxProfitGain, prevProfit[day] - prices[day]);
            }

            prevProfit = profit;
        }
        
        return profit[n-1];
    }
}

// Solution 3: DP optimized time complexity
// Based on solution 2, we can see that for each day, we want to maximute the sum
// (profit[i-1][prevDay] + prices[day] - prices[prevDay]), whereas, prices[day] is O(1)
// We need to for all prevDay to check for that sum, but the sum (profit[i-1][prevDay] - prices[prevDay]) can be precalculate on the go
// When looping throught the day, we store the maxPrevDay where the sum (profit[i-1][prevDay] - prices[prevDay]) is at max
// Then we only need to update the profit at current day based on maxPrevDay, and update the maxPrevDay on the go.
// Time complexity: O(n^2)
// Space complexity: O(m*n), notice we can optimize the space to O(n);
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] profit = new int[3][n];

        // i = number of transaction
        for (int i = 1; i <= 2; i++) {
            int maxProfitDay = 0;
            for (int day = 1; day < n; day++) {
                profit[i][day] = Math.max(profit[i][day-1], profit[i-1][maxProfitDay] + prices[day] - prices[maxProfitDay]);

                if (profit[i-1][maxProfitDay] - prices[maxProfitDay] < profit[i-1][day] - prices[day]) {
                    maxProfitDay = day;
                }
            }
        }
        
        return profit[2][n-1];
    }
}

// Solution 2: TLE - We build dp table where profit[i][day] = profit of making AT MOST i transaction at day "day".
// => We check max of all possible case:
//     - [i][day-1]: Do not make transaction at day i
//     - For all prevday < day: get max of (profit[i-1][day] + prices[day] - prices[day-1]
// Time complexity: O(n^2)
// Space complexity: O(m * n), m is the num of transactions
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] profit = new int[3][n];

        // i = number of transaction
        for (int i = 1; i <= 2; i++) {
            for (int day = 1; day < n; day++) {
                profit[i][day] = profit[i][day-1]; // Skip this day

                // The final day can skip all/make no transactions
                // if prevDay starts with 1, we are skipping first day => not good
                // => We must go with (profit[i-1][prevDay] + prices[day] - prices[prevDay]). Does it have any issues?
                // The answer is no, since the profit[i][day] is the maximum profit when making AT MOST i transaction at day "day".
                // => If profit[i-1][prevDay] complete the transaction at prevDay, => the next transaction continue with prevDay, combine to one tracsaction only
                // => Still satisfy the condition of AT MOST i transation (actually, in this case, it's (i-1))
                for (int prevDay = 0; prevDay < day; prevDay++) {
                    // Choose all possible previous day to make transaction
                    profit[i][day] = Math.max(profit[i][day], profit[i-1][prevDay] + prices[day] - prices[prevDay]);
                }
            }
        }

        // System.out.println("profit = ");
        // for (int i = 0; i <= 2; i++) {
        //     System.out.println(Arrays.toString(profit[i]));
        // }
        
        return profit[2][n-1];
    }
}

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
