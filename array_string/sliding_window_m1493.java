// Solution 1: The condition to shrink the window is that number of '0' (count) is more than 2.
// We only update the length when the current item is '1', and we remove 'count' of '0' from length.
// If all array is '1' => we subtract 1 from length

// Time complexity: O(n)
// Space complexity: O(1)

class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int l = 0, length = 0, count = 0;

        for (int r = 0; r < n; r++) {
            if (nums[r] == 0) {
                count++;
            } else {
                length = Math.max(length, r - l + 1 - count);
            }

            while (count > 1) {
                if (nums[l] == 0) {
                    count--;
                }
                l++;
            }
        }
        return length == n ? length - 1 : length;
    }
}

// Solution 2: Same as solution 1, but handle the length better, it's a bit tricky as
// - If the window has one '0' => we do not count it to length.
// - If the window has all '1' => we auto subtract 1 from the window length since we need to always remove 1 item as required in the problem description.

class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int l = 0, length = 0, count = 0;

        for (int r = 0; r < n; r++) {
            if (nums[r] == 0) {
                count++;
            }

            while (count > 1) {
                if (nums[l] == 0) {
                    count--;
                }
                l++;
            }

            // If there is one '0' => r - l (without + 1) is right answer
            // If all array are '1' => This also removes one '1' as required.
            // Notice that we allow to update length even if the current item is 0 => it's excluded
            length = Math.max(length, r - l);
        }
        return length;
    }
}
