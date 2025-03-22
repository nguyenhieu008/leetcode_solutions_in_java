// https://leetcode.com/problems/max-consecutive-ones-iii/description/

// Solution: Observation: the 0s in the answer will all be in the same subarray of 1s, it means they are adjacent to other 0s (separated by 1s) 
// => we can use sliding window that has exactly k 0s.
// => result will be the max of all windows.

// Time complexity: O(n)
// Space complexity: O(1)

class Solution {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int l = 0, count = 0, res = 0;

        for (int r = 0; r < n; r++) {
            if (nums[r] == 0) {
                count++;
            }

            while (count > k) {
                if (nums[l] == 0) {
                    count--;
                }
                l++;
            }

            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
