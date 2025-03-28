// https://leetcode.com/problems/longest-arithmetic-subsequence/description/

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
