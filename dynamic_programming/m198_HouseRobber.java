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

// Solution 3a: top-down concise
class Solution {
    private int UNSET = -1;
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, UNSET);

        return rob(nums, n - 1, dp);
    }

    // Assume there 3 houses, 0 - 1 - 2 - 3 - 4. 
    // How many cases:
    // - Rob house 3 then end
    // - Rob house 4 then possible rob house 2 or not
    //
    // - return the result at house 4 to be the max between 2 above cases
    // - We get max as we go.
    private int rob(int[] nums, int i, int[] dp) {
        if (i < 0) {
            return 0;
        }
        if (dp[i] != UNSET) {
            return dp[i];
        }

        int res = Math.max(nums[i] + rob(nums, i - 2, dp), rob(nums, i - 1, dp));
        dp[i] = res;
        return res;
    }

    
}

// Solution 3: top-down memo
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[0] = nums[0];
        dp[1] = nums[1];

        return Math.max(rob(nums, n - 1, dp), rob(nums, n - 2, dp));
    }

    // Assume there 3 houses, 0 - 1 - 2 - 3 - 4. 
    // How many cases:
    // - Rob house 3 then end
    // - Rob house 4 then 2
    // - Rob house 4 then 1, skip 2 - 3
    // - Rob house 4, but want to rob house 0, -> definitely rob house 2 as it maximize the amount
    private int rob(int[] nums, int i, int[] dp) {
        if (i < 0) {
            return 0;
        }
        if (dp[i] != - 1) {
            return dp[i];
        }

        int res = nums[i] + Math.max(rob(nums, i - 2, dp), rob(nums, i - 3, dp));
        dp[i] = res;
        return res;
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
