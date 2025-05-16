// https://leetcode.com/problems/non-decreasing-array/ 

// Solution: Because we can only have 1 change, we choose smallest one possible and marked we already make change by boolean.

class Solution {
    public boolean checkPossibility(int[] nums) {
        int n = nums.length;
        boolean modified = false;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                if (modified) {
                    return false;
                } 

                modified = true;
                // Because we can only change 1 item, we choose the one that produce minimum possible value.
                if (i - 2 < 0 || nums[i - 2] <= nums[i]) {
                    nums[i - 1] = nums[i];
                } else {
                    nums[i] = nums[i - 1];
                }
            }
        }
        return true;
    }
}
