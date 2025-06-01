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

// Solution 2: Top-down dp + memoize
class Solution {
    int[][] dp;
    private int UNSET = -1;
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], UNSET);
        }
        
        return lcs(text1, m - 1, text2, n - 1);
    }

    private int lcs(String s1, int i1, String s2, int i2) {
        if (i1 < 0 || i2 < 0) {
            return 0;
        }

        if (dp[i1][i2] != UNSET) {
            return dp[i1][i2];
        }

        if (s1.charAt(i1) == s2.charAt(i2)) {
            dp[i1][i2] = 1 + lcs(s1, i1 - 1, s2, i2 - 1);
        } else {
            int res = Math.max(lcs(s1, i1, s2, i2 - 1), lcs(s1, i1 - 1, s2, i2));
            dp[i1][i2] = res;
        }

        return dp[i1][i2];
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
