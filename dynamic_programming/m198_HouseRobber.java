// https://leetcode.com/problems/house-robber/description/

// Solution 2: DP with memory optimised, only store last 2 variables. A bit harder to reasoning about the dp fomular.
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        int prevMax = nums[0], curMax = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            int max = Math.max(prevMax + nums[i], curMax);
            prevMax = curMax;
            curMax = max;
        }
        return curMax;
    }
}

// Solution 1: DP with tabulation. Easier to code/visualize and reasoning about the dp fomular.
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[n-1];
    }
}
