// https://leetcode.com/problems/longest-nice-subarray/description/

// Solution 2: A subarray is nice when all distinct items do not have commit bits. It means for all items in the subarray, we can merge the bits into a window (using OR), 
// and the new item can AND with the window, if it is > 0 => it has some common bits with the window.
// So we must shrink the window from the left, until we find no commit bits with the next element. => Sliding window technique.
// Notice: in order to to remove the bits of the left element, we XOR the left with the window => both have common bits 1 set => the 1 bits of left element are flipped into 0, in the window.
//        => that is the improvement for the solution 1:

// Time complexity: O(n)
// Space complexity: O(1)

class Solution {
    public int longestNiceSubarray(int[] nums) {
        int n = nums.length;

        int longest = 1;
        int l = 0;
        int window = 0;
        for (int r = 0; r < n; r++) {
            while ((window & nums[r]) > 0) {
                window ^= nums[l];
                l++;
            }
            window |= nums[r];
            longest = Math.max(longest, r - l + 1);
        }
        return longest;
    }
}


// Solution 1 : At i, we go backward and find the max possible window in that nums[i] bitwise-AND with every items results in 0.
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
