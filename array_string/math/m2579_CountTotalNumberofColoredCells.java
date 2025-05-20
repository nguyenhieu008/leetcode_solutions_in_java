// https://leetcode.com/problems/count-total-number-of-colored-cells/description/

// Solution 2: Pure math
class Solution {
    public long coloredCells(int n) {
        // dp[i]: number of cells at the end of n minutes
        // i = 1: dp[1] = 1
        // i = 2: dp[2] = dp[1] + (2 * 2) + (2 * (2 - 2)) = 1 + 4 + 0 = 5
        // i = 3: dp[3] = dp[2] + (2 * 3) + (2 * (3 - 2)) = 5 + 6 + 2 = 13
        // dp[i] = dp[i-1] + (2 * i) + (2 * (i - 2))
        //       = dp[i-1] + (4 * i) - 4 (4 edges - 4 overlap corners) = summ all: 4 * (i - 1)
        // => result = 1 + 4 + 8 + 12 + ...

        // sum of all i: 4 * (i - 1) = 4 * (sum of i from 1 to n) - 4n
        //                           = 4 * (n * (n + 1) / 2) - 4n
        //                           = 2n^2 -2n = 2n * (n - 1)
        // Need to (+ 1), in case i = 1 => result = 1;

        return (long) 2 * n * (n - 1) + 1;
    }
}

// Solution 3: 1 nice solution using geometry thought
// Reference: https://leetcode.com/problems/count-total-number-of-colored-cells/solutions/3256196/java-c-python-cut-and-combine-o-1/

// Solution 1: math + dp
class Solution {
    public long coloredCells(int n) {
        // dp[i]: number of cells at the end of n minutes
        // i = 1: dp[1] = 1
        // i = 2: dp[2] = dp[1] + (2 * 2) + (2 * (2 - 2)) = 1 + 4 + 0 = 5
        // i = 3: dp[3] = dp[2] + (2 * 3) + (2 * (3 - 2)) = 5 + 6 + 2 = 13
        // dp[i] = dp[i-1] + (2 * i) + (2 * (i - 2))
        //       = dp[i-1] + (4 * i) - 4 = (4 edges - 4 overlap corners)

        long dp = 1; // if n == 1, it won't enter for loop then just return
        for (int i = 2; i <= n; i++) {
            dp += 4 * i - 4;
        }
        return dp;
    }
}
