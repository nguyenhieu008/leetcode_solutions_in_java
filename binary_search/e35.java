// https://leetcode.com/problems/search-insert-position/description/

class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (r - l > 0) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
