// https://leetcode.com/problems/partition-array-into-two-equal-product-subsets/

// Notice: we can loop through all possible subset by using bitmask technique, loop i : (1 -> 2^n - 1)

class Solution {
    public boolean checkEqualPartitions(int[] nums, long target) {
        boolean[] used = new boolean[nums.length];
        return backtrack(nums, 0, used, 1L, target);
    }
    private boolean backtrack(int[] nums, int curIdx, boolean[] used, long curProduct, long target) {
        if (curProduct == target && notUsedProductEqual(nums, used, target)) {
            return true;
        }
        
        if (curIdx >= nums.length) {
           return false;
        }

        // not used
        if (backtrack(nums, curIdx + 1, used, curProduct, target)) {
            return true;
        }

        used[curIdx] = true;
        curProduct *= nums[curIdx];
        if (backtrack(nums, curIdx + 1, used, curProduct, target)) {
            return true;
        }
        used[curIdx] = false;
        return false;
    }

    private boolean notUsedProductEqual(int[] nums, boolean[] used, long target) {
        long curProduct = 1L;
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                curProduct *= nums[i];
            }
            if (curProduct > target) {
                return false;
            }
        }
        return curProduct == target;
    }
}
