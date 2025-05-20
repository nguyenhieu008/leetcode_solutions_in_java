// https://leetcode.com/problems/remove-one-element-to-make-the-array-strictly-increasing/description/

// Solution 2: A bit shorter, if we try to remove the previous one, we just do nothing
class Solution {
    public boolean canBeIncreasing(int[] nums) {
        int n = nums.length;
        boolean removed = false;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i-1]) {
                continue;
            }

            if (removed) {
                // More than 1 removal to make it increasing
                return false;
            }

            removed = true;
            // We try to replace the item using the optimal way
            if (i >= 2 && nums[i] <= nums[i-2]) {
                // If cannot remove the previous one, we remove the current one = use the previous one as the current
                nums[i] = nums[i-1];
            }
        }
        
        // If reach here, it satisfies the condition
        return true;
    }
}

// Solution 1: Works but a bit long
class Solution {
    public boolean canBeIncreasing(int[] nums) {
        int n = nums.length;
        boolean removed = false;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i-1]) {
                continue;
            }

            if (removed) {
                // More than 1 removal to make it increasing
                return false;
            }

            removed = true;
            // We try to replace the item using the optimal way
            if (i < 2 || nums[i] > nums[i-2]) {
                // If i and (i - 2) can form an increasing array, 
                // Do nothing 
                // Or nums[i-1] = nums[i]; remove previous one
                continue;
                
            } else {
                // Otherwise, we remove the current one = use the previous one as the current
                nums[i] = nums[i-1];
            }
        }
        // If reach here, it satisfies the condition
        return true;
    }
}
