// Solution 1a: Same as 1, but we send the current XOR to the next step. The trick is that the curXor is an int => it will only effective for this step => it will be reset after the function return.
// Time complexity: O(2^n)
// Space complexity: O(n) for stack depth.
class Solution {
    public int subsetXORSum(int[] nums) {
        return backtrack(nums, 0, 0);
    }
    private int backtrack(int[] nums, int idx, int curXor) {
        if (idx >= nums.length) {
            return curXor;
        }

        int sum1 = backtrack(nums, idx + 1, curXor);
        int sum2 = backtrack(nums, idx + 1, curXor ^ nums[idx]);
        return sum1 + sum2;
    }
}

// Solution 1: generate all subsets, at each index, we whether choose or not choose that.
// Then add a for loop to calculate sum for a subset.
// Time complexity: O(n * 2^n). 2^n for generating all subsets, n for calculate sum
// Space complexity: O(n), for boolean[] chosen and the depth of the recursive stack. They are separated and not multiplied.
class Solution {
    public int subsetXORSum(int[] nums) {
        boolean[] chosen = new boolean[nums.length];
        return backtrack(nums, chosen, 0);
    }
    private int backtrack(int[] nums, boolean[] chosen, int startIdx) {
        if (startIdx >= nums.length) {
            return getSum(nums, chosen);
        }

        int sum1 = backtrack(nums, chosen, startIdx + 1);
        chosen[startIdx] = true;
        int sum2 = backtrack(nums, chosen, startIdx + 1);
        chosen[startIdx] = false;
        return sum1 + sum2;
    }
    private int getSum(int[] nums, boolean[] chosen) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (chosen[i]) {
                res ^= nums[i];
            }
        }
        return res;
    }
}

// Solution 2: https://leetcode.com/problems/sum-of-all-subset-xor-totals/solutions/5175480/sum-of-all-subset-xor-totals/?envType=daily-question&envId=2025-04-05
// reference only, use XOR properties but I do not understand yet.
class Solution {
    public int subsetXORSum(int[] nums) {
        int result = 0;
        // Capture each bit that is set in any of the elements
        for (int num : nums) {
            result |= num;
        }
        // Multiply by the number of subset XOR totals that will have each bit set
        return result << (nums.length - 1);
    }
}
