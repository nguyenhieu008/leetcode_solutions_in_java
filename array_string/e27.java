// https://leetcode.com/problems/remove-element/description/
class Solution {
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int storeRemovals = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] == val) {
                nums[i] = nums[storeRemovals];
                storeRemovals--;
            }
        }
        return storeRemovals + 1;
    }
}
