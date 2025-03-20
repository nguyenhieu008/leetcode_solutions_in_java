// Solution 2: Same as Solution 1, the array is sorted to easily using the sliding window. At each i, we can only apply operations to the ones before it, rather than applying to all items before and after.
// We just change the way we calculate the number of operations needed, by (the largest one * window length) - (sum of items in the window).

// Time complexity: O(n)
// Space complexity: O(1)

class Solution {
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        int l = 0, maxFreq = 1;
        long sum = 0;
        for (int r = 0; r < n; r++) {
            sum += nums[r];

            while ((long)nums[r] * (r - l + 1) - sum > k) {
                sum -= nums[l];
                l++;
            }
            maxFreq = Math.max(maxFreq, r - l + 1);
        }
        return maxFreq;
    }
}

// Solution 1: When looping, we save the number of ops needed to convert the window to the largest one.
// When reach another i, we diff the prev largest one with the current one (because the array is sorted ascending), and then multiple with the size of PREVIOUS WINDOW (r - l only) => nums of ops needed.

class Solution {
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        int l = 0, maxFreq = 1;
        long reqOps = 0;
        for (int r = 1; r < n; r++) {
            reqOps += (long)(r - l) * (nums[r] - nums[r-1]);

            while (reqOps > k) {
                reqOps -= (nums[r] - nums[l]);
                l++;
            }
            maxFreq = Math.max(maxFreq, r - l + 1);
        }
        return maxFreq;
    }
}
