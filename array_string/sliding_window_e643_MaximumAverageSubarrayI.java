
// Solution 2: use 2 for-loop but easier to read.
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        int curSum = 0;
        for (int i = 0; i < k; i++) {
            curSum += nums[i];
        }

        int maxSum = curSum;
        for (int i = k; i < n; i++) {
            curSum += nums[i];
            curSum -= nums[i - k];
            maxSum = Math.max(maxSum, curSum);
        }
        return (double)maxSum / k;
    }
}

// Solution 1: Use sliding window left-right, but a bit complicated with nested if
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        for (int left = -k, right = 0; right < n; left++, right++) {
            curSum += nums[right];

            if (left >= -1) {
                if (left >= 0) {
                    curSum -= nums[left];
                }
                maxSum = Math.max(maxSum, curSum);
            }
        }
        return (double)maxSum / k;
    }
}
