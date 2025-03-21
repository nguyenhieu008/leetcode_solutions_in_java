// https://leetcode.com/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-i/description/

// Solution: If nums[0] = 1, the optimal way is to ignore it and handle the array from nums[1] => base case.
// If nums[0] = 0, the only way is to flip the first triplet (index 0, 1, 2) and then return to the base case.
// So, we can get the minimum flip using greedy approach.

// Time complexity: O(n)
// Space complexity: O(1)

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        int res = 0;
        for (int i = 0; i < n - 2; i++) {
            if (nums[i] == 0) {
                nums[i + 1] = 1 - nums[i + 1];
                nums[i + 2] = 1 - nums[i + 2];
                res++;
            }
        }
        if (nums[n - 1] == 0 || nums[n - 2] == 0) {
            return -1;
        }
        return res;
    }
}
