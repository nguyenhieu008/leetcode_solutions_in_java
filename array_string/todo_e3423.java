

// https://leetcode.com/problems/maximum-difference-between-adjacent-elements-in-a-circular-array/description/?envType=daily-question&envId=2025-06-12

class Solution {
    public int maxAdjacentDistance(int[] nums) {
        int maxi = 0;
        for (int i = 0; i < nums.length - 1; i++)
            maxi = Math.max(maxi, Math.abs(nums[i + 1] - nums[i]));
        maxi = Math.max(maxi, Math.abs(nums[nums.length - 1] - nums[0]));
        return maxi;
    }
}
