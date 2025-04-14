// https://leetcode.com/problems/climbing-stairs/description/

// Simple Fibonacci problem with two most recent sum to optimize memory. Other ways may be, recursion, dp with memoisation (top-down), dp tabulation (bottom-up).

class Solution {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int twoSteps = 1, oneStep = 1;
        for (int i = 2; i <= n; i++) {
            int sum = twoSteps + oneStep;
            twoSteps = oneStep;
            oneStep = sum;
        }
        return oneStep;
    }
}
