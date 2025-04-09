// https://leetcode.com/problems/contains-duplicate-ii/description/

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int n = nums.length;
        HashSet<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (seen.contains(nums[i])) {
                return true;
            }
            seen.add(nums[i]);
            if (i - k >= 0) {
                seen.remove(nums[i-k]);
            }
        }
        return false;
    }
}
