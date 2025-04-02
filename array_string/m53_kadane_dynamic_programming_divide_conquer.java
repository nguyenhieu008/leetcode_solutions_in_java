// https://leetcode.com/problems/maximum-subarray/description/

// Solution 1: do myself. If at i, the curSum is > 0, then we want to expand the next subarray from i, as it increase the total.
// If it's <= 0, so we no need to expand from i since it make the total worse, and we reset the curSum to 0.
// We update result at each step.

// Time complexity: O(n)
// Space complexity: O(n)

class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int curSum = 0;
        int res = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            curSum += nums[i];
            res = Math.max(res, curSum);
            curSum = Math.max(0, curSum);
        }
        return res;
    }
}

// Solution 1a: Kadane's Algorithm, same but we use curMax instead of curSum and no need to reset to 0.

class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int curMax = nums[0];
        int res = curMax;

        for (int i = 1; i < n; i++) {
            curMax = Math.max(nums[i], curMax + nums[i]);
            res = Math.max(res, curMax);
        }
        return res;
    }
}

// Solution 2: Divide and conquer, just for reference
// if we divide to left - mid - right => max subarray will be one of:
// -  inside left
// -  inside right
// -  max sum that ends at (mid - 1) + mid + max sum starts at (mid + 1)
// Time complexity: O(nlogn);
// Space complexity: O(logn) for stack

class Solution {
    public int maxSubArray(int[] nums) {
        return maxSubArrayRecursive(nums, 0, nums.length - 1);
    }
    private int maxSubArrayRecursive(int[] nums, int l, int r) {
        if (l > r) return Integer.MIN_VALUE;

        int mid = l + (r - l) / 2;
        int leftSum = 0, rightSum = 0;
        int maxLeft = 0, maxRight = 0;

        for (int i = mid - 1; i >= l; i--) {
            leftSum += nums[i];
            maxLeft = Math.max(maxLeft, leftSum);
        }
        for (int i = mid + 1; i <= r; i++) {
            rightSum += nums[i];
            maxRight = Math.max(maxRight, rightSum);
        }

        return Math.max(
            Math.max(maxSubArrayRecursive(nums, l, mid - 1), 
                        maxSubArrayRecursive(nums, mid + 1, r)), 
            maxLeft + nums[mid] + maxRight
        );
    }
}

// Solution 3: Same as 2, but we precalculate the sums that ends at i, and start at i => so we can the 
// (max sum that ends at (mid - 1) + mid + max sum starts at (mid + 1)) with O(1)
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] maxPrev = new int[n];
        int[] maxPost = new int[n];

        maxPrev[0] = nums[0];
        for (int i = 1; i < n; i++) {
            maxPrev[i] = nums[i] + Math.max(0, maxPrev[i-1]);
        }
        maxPost[n-1] = nums[n-1];
        for (int i = n - 2; i >= 0; i--) {
            maxPost[i] = nums[i] + Math.max(0, maxPost[i+1]);
        }
        return maxSubArrayRecursive(nums, 0, nums.length - 1, maxPrev, maxPost);
    }

    private int maxSubArrayRecursive(int[] nums, int l, int r, int[] maxPrev, int[] maxPost) {
        if (l == r) return nums[l];

        int mid = l + (r - l) / 2;

        return Math.max(
            Math.max(maxSubArrayRecursive(nums, l, mid, maxPrev, maxPost), 
                        maxSubArrayRecursive(nums, mid + 1, r, maxPrev, maxPost)), 
            maxPrev[mid] + maxPost[mid+1]
        );
    }
}

// But after done that, we see that the result is the maxValue in the maxPrev, LOL
// https://leetcode.com/problems/maximum-subarray/solutions/1595195/c-python-7-simple-solutions-w-explanation-brute-force-dp-kadane-divide-conquer
