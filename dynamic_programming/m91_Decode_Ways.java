// https://leetcode.com/problems/decode-ways/description/

// Solution 3: bottom-up, memory optimized
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int dp = 0, prev1 = 1, prev2 = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            dp = 0;
            if (c != '0') {
                dp += prev1; 
            }

            if (i > 0 && (s.charAt(i-1) == '1' || (s.charAt(i-1) == '2' && c >= '0' && c <= '6'))) {
                dp += prev2; 
            }
            
            prev2 = prev1;
            prev1 = dp;
        }
        return dp; 
    }
}

// Solution 2: bottom-up - tabulation dp, use dp array
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        // dp[i] is number of ways to decode (i - 1) first character of s
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            int ways = 0;
            if (c != '0') {
                ways += dp[i]; // (i - 1) first character
            }

            if (i > 0 && (s.charAt(i-1) == '1' || (s.charAt(i-1) == '2' && c >= '0' && c <= '6'))) {
                ways += dp[i-1]; // (i - 2) first character
            }
            dp[i + 1] = ways;
        }
        return dp[n]; // number of ways to decode first (n - 1) characters.
    }
}

// Solution 3: top-down dp, memoization
// Note: should return int should we can do "res += countWays..."
class Solution {
    int[] memo;
    public int numDecodings(String s) {
        int n = s.length();
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        memo[n] = 1;    // base case
        
        countWays(s, 0);
        return memo[0];
    }

    private int countWays(String s, int startingIdx) {
        if (memo[startingIdx] != -1) {
            return memo[startingIdx];
        }

        int res = 0;
        char c = s.charAt(startingIdx);
        if (c != '0') {
            res += countWays(s, startingIdx + 1);

            int nIdx = startingIdx + 1;
            if (nIdx < s.length() && (c == '1' || (c == '2' && s.charAt(nIdx) >= '0' && s.charAt(nIdx) <= '6'))) {
                res += countWays(s, startingIdx + 2);
            }
        }
        // if c == '0', return res = 0;
        memo[startingIdx] = res;
        return res;
    }
}

// Solution 0: recursive, TLE
class Solution {
    public int numDecodings(String s) {
        return countWays(s, 0);
    }

    private int countWays(String s, int startingIdx) {
        int n = s.length();
        if (startingIdx == n) {
            return 1;
        }

        int res = 0;
        char c = s.charAt(startingIdx);
        if (c != '0') {
            res += countWays(s, startingIdx + 1);

            int nIdx = startingIdx + 1;
            if (nIdx < n && (c == '1' || (c == '2' && s.charAt(nIdx) >= '0' && s.charAt(nIdx) <= '6'))) {
                res += countWays(s, startingIdx + 2);
            }
        }
        // if c == '0', return res = 0;
        return res;
    }
}
