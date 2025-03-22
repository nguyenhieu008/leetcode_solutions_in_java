// https://leetcode.com/problems/max-consecutive-ones/description/

// Solution: can solve easily with count, but try to use sliding window to familiarize with it.
// l is the first '1' in the window, updated if we find a '0'
// At each step, update the window size to the result accordingly.

// Time complexity: O(n)
// Space complexity: O(1)

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int l = 0, res = 0;
        
        for (int r = 0; r < n; r++) {
            if (nums[r] == 0) {
                l = r + 1;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
