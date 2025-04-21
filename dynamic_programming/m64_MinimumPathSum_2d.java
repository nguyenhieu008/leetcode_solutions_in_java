// https://leetcode.com/problems/minimum-path-sum/description/

// Solution 2: use 1d array to memoise the last min path row. 
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];

        dp[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            // Build top row dp table, we only have one path from left to right
            dp[i] = dp[i-1] + grid[0][i];
        }

        for (int row = 1; row < m; row++) {
            // First column, only have one path from top to bottom
            dp[0] += grid[row][0];

            // At each inner item, we have 2 choices, from left item or from top item. We get the minimum among them.
            for (int col = 1; col < n; col++) {
                dp[col] = Math.min(dp[col-1], dp[col]) + grid[row][col];
            }
        }
        return dp[n-1];
    }
}

// Solution 1: use 2d array to memoise
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            // Build top row dp table, we only have one path from left to right
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }

        for (int row = 1; row < m; row++) {
            // First column, only have one path from top to bottom
            dp[row][0] = dp[row-1][0] + grid[row][0];

            // At each inner item, we have 2 choices, from left item or from top item. We get the minimum among them.
            for (int col = 1; col < n; col++) {
                dp[row][col] = Math.min(dp[row][col-1], dp[row-1][col]) + grid[row][col];
            }
        }
        return dp[m-1][n-1];
    }
}
