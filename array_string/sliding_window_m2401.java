// https://leetcode.com/problems/longest-nice-subarray/description/

// Solution: At i, we go backward and find the max possible window in that nums[i] bitwise-AND with every items results in 0.
// It means, numbers in that window do not have any common bit. 
// So, if we bitwise-OR every items except i (common bits of all other numbers in the window), and then bitwise-AND nums[i] with it, it must result in 0.
// So, we use sliding window, from start -> end, which is the maximum sub-array that ends at "end". Every items to the left of start will not satisfy.
// As we increment "end" pointer, we check every items up to "start", and update "start" when we find the point that breaks the condition.
// Because a sub-array can have maximum 30 bits (< 10^9) => the sliding window has maximum size of 30.

// Time complexity: O(30n)
// Space complexity: O(1);


class Solution {
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;

        int longest = 1;
        int start = 0, end = 1;
        while (end < n) {
            int orAll = nums[end];

            for (int k = end - 1; k >= start; k--) {
                if ((orAll & nums[k]) != 0) {
                    start = k + 1;
                    break;
                } else {
                    orAll |= nums[k];
                }
            }
            longest = Math.max(longest, end - start + 1);

            end++;
        }
        return longest;
    }
}
