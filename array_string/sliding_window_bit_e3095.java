// https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-i/description/

// Solution: If a window has orVal >= k, then no need to expand, because the orVal will always gets bigger.
// Key point is that when shrinking the window, we should take care of bit count for each bit position, 
// Because if we remove the bit always, we can lead to wrong value of the orVal of the window.
// So we need to keep track of the bit count for each bit position, and only flip bit if it downs to 0.

// Time complexity: O(n * 32). 32 it the max bits, but can reduce since the value range is smaller.
// Space complexity: O(1)

class Solution {
    private int doOr(int[] bitCount, int orVal, int v) {
        int n = bitCount.length;
        for (int i = 0; i < n; i++) {
            if ((v & (1 << i)) > 0) {
                bitCount[i]++;
            }
        }
        return orVal | v;
    }

    private int undoOr(int[] bitCount, int orVal, int v) {
        int n = bitCount.length;
        for (int i = 0; i < n; i++) {
            if ((v & (1 << i)) > 0) {
                bitCount[i]--;
                if (bitCount[i] == 0) {
                    orVal &= ~(1 << i);
                }
            }
        }
        return orVal;
    }

    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int[] bitCount = new int[32];
        int orVal = 0;

        int l = 0, res = Integer.MAX_VALUE;

        for (int r = 0; r < n; r++) {
            orVal = doOr(bitCount, orVal, nums[r]);

            while (l <= r && orVal >= k) {
                res = Math.min(res, r - l + 1);
                orVal = undoOr(bitCount, orVal, nums[l]);
                l++;
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
