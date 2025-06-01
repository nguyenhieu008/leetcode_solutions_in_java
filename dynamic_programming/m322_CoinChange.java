// https://leetcode.com/problems/coin-change/description/

// Solution 2: DP tabulation
// For a specific amount i, if there is a coin c <= i => we can make that amount by dp[i] = min(dp[i], dp[i-c] + c)
// We check all possible coins and build up the dp from 0 up to amount.
// Time complexity: O(n * m), n = amount, m = num of coins
// Space complexity: O(n), for dp table.

class Solution {
    public int coinChange(int[] coins, int amount) {
        int INFINITY = (int)amount + 2; // beware exceed int max. amount <= 10^4 => okay
        int[] minCoins = new int[amount + 1];
        Arrays.fill(minCoins, INFINITY);
        minCoins[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int c : coins) {
                if (i - c >= 0) {
                    minCoins[i] = Math.min(minCoins[i], minCoins[i-c] + 1);
                }
            }
        }

        return minCoins[amount] == INFINITY ? -1 : minCoins[amount];
    }
}

// Solution: 1a: dp top-down and memoize. Different implementation.
class Solution {
    int[] dp;
    private int INFINITY = 1_000_000_000;
    private int UNSET = -1;

    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        dp = new int[amount + 1];
        Arrays.fill(dp, UNSET);
        dp[0] = 0;

        int res = change(coins, amount);

        return res == INFINITY ? -1 : res;
    }

    private int change(int[] coins, int amount) {
        if (dp[amount] != UNSET) {
            return dp[amount];
        }

        int res = INFINITY;
        for (int c : coins) {
            // Try all possible coins
            if (c > amount) {
                continue;
            }
            
            res = Math.min(res, 1 + change(coins, amount - c));
        }
        dp[amount] = res;

        return res;
    }
}

// Solution 1: DP memoization, top-down.
// Same solution as solution, but we try to solve the dp(amount) first => it will yield the problem to find dp(i) where i < amount.
// We use memoization to limit the call to each amount only, beware that we need 3 states, undefined = INFINITY, noWayToChange = -1, canChange(with minimum) >= 0
// Time complexity: O(n * m), as we calculate each amount only once
// Time complexity: O(n + n) = O(n), for dp table and recursion stack.

class Solution {
    private int INFINITY = (int)1e9;
    public int fewestCoins(int[] coins, int n, int[] dp) {
        if (dp[n] != INFINITY) {
            return dp[n];
        }

        for (int value : coins) {
            if (value <= n) {
                int res = fewestCoins(coins, n - value, dp);
                if (res != -1 && res < dp[n]) {
                    dp[n] = res + 1;
                }
            }
        }
        if (dp[n] == INFINITY) {
            dp[n] = -1;
        }
        return dp[n];
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 1, amount + 1, INFINITY);

        return fewestCoins(coins, amount, dp);
    }
}
