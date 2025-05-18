// https://leetcode.com/problems/longest-common-subsequence/description/

// Solution 1a: Optimize memory
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[] prev = new int[n + 1];

        for (int row = 1; row <= m; row++) {
            int[] dp = new int[n + 1];

            for (int col = 1; col <= n; col++) {
                if (text1.charAt(row - 1) == text2.charAt(col - 1)) {
                    dp[col] = prev[col-1] + 1;
                } else {
                    dp[col] = Math.max(dp[col-1], prev[col]);
                }
            }
            prev = dp;
        }

        return prev[n];
    }
}

// Solution 1: DP
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int row = 1; row <= m; row++) {
            for (int col = 1; col <= n; col++) {
                if (text1.charAt(row - 1) == text2.charAt(col - 1)) {
                    dp[row][col] = dp[row-1][col-1] + 1;
                } else {
                    dp[row][col] = Math.max(dp[row-1][col], dp[row][col-1]);
                }
            }
        }

        return dp[m][n];
    }
}
