// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

// Solution: for each item i, the max value possible for i is we subtract the min values before it, and compare to max res to that point
// After that, we update the min if needed.

class Solution {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int p : prices) {
            res = Math.max(res, p - min);
            min = Math.min(min, p);
        }
        return res;
    }
}

// It also relates to Kadane algorithm, reference: 
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/solutions/39038/kadane-s-algorithm-since-no-one-has-mentioned-about-this-so-far-in-case-if-interviewer-twists-the-input/
