// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // sorted
        // Exactly one solution. Output array of 2 ints
        // cannot return same items (not needed as the solution is guaranteed)
        // indexes must be added by one
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                return new int[]{l + 1, r + 1};
            } else if (numbers[l] + numbers[r] > target) {
                // sum is larger, no need to make it bigger => r-- to reduce the sum
                r--;
            } else {
                // l++ to increase the sum
                l++;
            }
        }
        // won't reach here as there is exactly one solution
        return new int[]{-1, -1};
    }
}
