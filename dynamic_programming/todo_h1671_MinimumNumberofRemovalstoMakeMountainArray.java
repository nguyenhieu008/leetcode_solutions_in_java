// https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/description/

// Solution 1: Using naive LIS dp approach. 
// Time complexity: O(n^2)
// Space complexity: O(n);

class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        // lisLeft[i] = longest increasing subsequency that ends at i, from left to right, not including i
        // lisRight[i] = longest increasing subsequency that ends at i, from right to left, not including i
        int[] lisLeft = new int[n];
        int[] lisRight = new int[n];

        for (int right = 1; right < n; right++) {
            for (int left = 0; left < right; left++) {
                if (nums[right] > nums[left]) {
                    lisLeft[right] = Math.max(lisLeft[right], lisLeft[left] + 1);
                }
            }
        }

        for (int left = n - 2; left >= 0; left--) {
            for (int right = n - 1; right > left; right--) {
                if (nums[left] > nums[right]) {
                    lisRight[left] = Math.max(lisRight[left], lisRight[right] + 1);
                }
            }
        }

        int maxMoutainLength = 0; // guarantee can make mountain
        // Actually, should start from 1 and ends at n - 2, but over-do it will not cause any problem
        for (int i = 0; i < n; i++) {
            // Only try to update if it's a mountain at i
            if (lisLeft[i] > 0 && lisRight[i] > 0) {
                maxMoutainLength = Math.max(maxMoutainLength, lisLeft[i] + lisRight[i] + 1);
            }
        }
        return n - maxMoutainLength;
    }
}
