// https://leetcode.com/problems/edit-distance/description/

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int minOps = Integer.MAX_VALUE;

                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    minOps = Math.min(minOps, dp[i-1][j-1]);
                }
                minOps = Math.min(minOps, 1 + dp[i-1][j]); // delete
                minOps = Math.min(minOps, 1 + dp[i-1][j-1]); // replace
                minOps = Math.min(minOps, 1 + dp[i][j-1]); // insert
                dp[i][j] = minOps;
            }
        }
        
        return dp[m][n];
    }
}
