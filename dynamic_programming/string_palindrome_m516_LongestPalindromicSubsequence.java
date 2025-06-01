// https://leetcode.com/problems/longest-palindromic-subsequence/description/

// Solution 2: Bottom-up dp
// Notice, there are some other ways for bottom-up dp, including space-optimized solution. Check out here:
// https://leetcode.com/problems/longest-palindromic-subsequence/solutions/1468396/c-python-2-solutions-top-down-dp-bottom-up-dp-o-n-space-clean-concise/

class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
            if (i + 1 < n) {
                if (s.charAt(i) == s.charAt(i + 1)) {
                    dp[i][i + 1] = 2;
                } else {
                    dp[i][i + 1] = 1; // contains [i, i]
                }
            }
        }

        for (int l = 3; l <= n; l++) {
            for (int left = 0; left + l <= n; left++) {
                int right = left + l - 1;
                if (s.charAt(left) == s.charAt(right)) {
                    dp[left][right] = 2 + dp[left + 1][right - 1];
                } else {
                    dp[left][right] = Math.max(dp[left + 1][right], dp[left][right - 1]);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}

// Solution 1: top-down dp
class Solution {
    private int[][] dp;
    private int UNSET = -1;
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], UNSET);
        }
        
        return solve(s, 0, n - 1);
    }

    private int solve(String s, int left, int right) {
        if (left > right) {
            return 0;
        }
        if (left == right) {
            dp[left][right] = 1;
            return 1;
        }

        if (dp[left][right] != UNSET) {
            return dp[left][right];
        }

        if (s.charAt(left) == s.charAt(right)) {
            dp[left][right] = 2 + solve(s, left + 1, right - 1);
        } else {
            int res = Math.max(solve(s, left + 1, right), solve(s, left, right - 1));
            dp[left][right] = res;
        }
        return dp[left][right];
    }
}
