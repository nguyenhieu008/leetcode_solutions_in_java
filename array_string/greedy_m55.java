// https://leetcode.com/problems/jump-game/description/
// At each step, we update the largest possible index where we can jump to
// If it reachs the last one => we are good
class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int maxIdx = 0;

        for (int i = 0; i <= maxIdx && i < n; i++) {
            maxIdx = Math.max(maxIdx, i + nums[i]);
        }
        return maxIdx >= n - 1;
    }
}
