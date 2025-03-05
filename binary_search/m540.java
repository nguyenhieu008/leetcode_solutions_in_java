// https://leetcode.com/problems/single-element-in-a-sorted-array/description/

class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;

        while (r - l > 1) {
            int mid = l + (r - l) / 2;

            if ((r - mid) % 2 == 0) {
                if (nums[mid] == nums[mid + 1]) {
                    l = mid;
                } else {
                    r = mid;
                }
            } else {
                if (nums[mid] == nums[mid+1]) {
                    r = mid - 1;
                } else {
                    l = mid;
                }
            }
        }

        if (l == 0 || nums[l] != nums[l-1]) {
            return nums[l];
        }
        return nums[r];
    }
}
