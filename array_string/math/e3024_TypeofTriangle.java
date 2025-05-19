// https://leetcode.com/problems/type-of-triangle/description/

class Solution {
    public String triangleType(int[] nums) {
        Arrays.sort(nums);
        // nums[0] = smallest, nums[2] = largest
        if (nums[2] >= nums[0] + nums[1]) {
            // Only largest one can break the condition of a triangle: c < a + b
            // We should check the condition for a valid triangle first, because check type of triangle.
            return "none";
        } else if (nums[0] == nums[2]) {
            // If smallest = largest => all 3 equal.
            return "equilateral";
        } else if (nums[0] == nums[1] || nums[1] == nums[2]) {
            // only one pair equals, not all
            // Because there may be a case where nums[0] == nums[1], but sum <= nums[2] => we must check for "none" before this
            return "isosceles";
        } else {
            return "scalene";
        }
    }
}
