// https://leetcode.com/problems/longest-arithmetic-subsequence/description/

// Solution 2: For every index i and j (i < j), we want to know that the max possible length of arithmetic subsequence when we add nums[j] to subsequence ending at nums[i].
// It means, given d = nums[j] - nums[i], we need to know the length of arithmetic subsequence that ends at nums[i] and have the diff of d.
// So we build a dynamic programming table, where dp[j][d] = dp[i][d] + 1 and d = nums[j] - nums[i], for every i from 0 -> j
// Notice that, the d can be negative, so we use hash map for it => no need to deal with negative index.

// Time complexity: O(n^2)
// Space complexity: O(n^2)

class Solution {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer>[] dp = new HashMap[n];
        int res = 1;

        for (int j = 0; j < n; j++) {
            dp[j] = new HashMap<>();
            for (int i = 0; i < j; i++) {
                int d = nums[j] - nums[i];
                dp[j].put(d, dp[i].getOrDefault(d, 1) + 1);
                res = Math.max(res, dp[j].get(d));
            }
        }
        return res;
    }
}

// Solution 3: For every two item i, j, we can have d = nums[j] - nums[i]. Then we find till the end, the max possible subsequence with difference between all items are d.
// Time complexity: O(n^3)
// Space complexity: O(1)

class Solution {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        int res = 2;

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int d = nums[j] - nums[i];
                int prev = nums[j];
                int count = 2;

                for (int k = j + 1; k < n; k++) {
                    if (nums[k] - prev == d) {
                        prev = nums[k];
                        count++;
                    }
                }

                res = Math.max(res, count);
            }
        }
        return res;
    }
}
