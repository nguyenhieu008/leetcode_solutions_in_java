// https://leetcode.com/problems/maximum-ascending-subarray-sum/description/

class Solution {
    public int maxAscendingSum(int[] nums) {
        int n = nums.length;
        int curSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                curSum += nums[i];
            } else {
                curSum = nums[i];
            }
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }
}
