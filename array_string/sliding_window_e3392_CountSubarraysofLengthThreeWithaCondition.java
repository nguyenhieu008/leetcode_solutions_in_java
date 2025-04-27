// https://leetcode.com/problems/count-subarrays-of-length-three-with-a-condition/description/

class Solution {
    public int countSubarrays(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 2; i < n; i++) {
            res += (nums[i] + nums[i-2]) * 2 == nums[i-1] ? 1 : 0;
        }
        return res;
    }
}
