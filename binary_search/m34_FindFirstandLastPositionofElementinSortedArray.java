// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

// Solution 1: Use the binary search to find the first position that satisfy the condition (number >= target)
// Time complexity: O(logn)
// Space complexity: O(1)

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int startRange = greaterOrEqualIdx(nums, target); // inclusive
        int endRange = greaterOrEqualIdx(nums, target + 1); // exclusive

        if (startRange == endRange) {
            return new int[]{-1, -1};
        }
        return new int[]{startRange, endRange - 1};
    }

    private int greaterOrEqualIdx(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

// Solution 2: bonus. Try to find the first index of >= target and last index of <= target
// compare then output
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int startRange = greaterOrEqualIdx(nums, target); // inclusive
        int endRange = equalOrLessIdx(nums, target); // inclusive

        if (startRange > endRange) {
            return new int[]{-1, -1};
        }
        return new int[]{startRange, endRange};
    }

    private int greaterOrEqualIdx(int[] nums, int target) {
        /* 
            (value < target) -> (value >= target)

            condition: (value >= target)
            false -> true;

            if (true) {
                right = mid;
            } else {
                left = mid + 1;
            }

            return left;  => the first index the condition becomes true
            if no true => left go outside of the array
        */

        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int equalOrLessIdx(int[] nums, int target) {
        /*
            (value <= target) -> (value > target) 
            condition: (value > target)
            false -> true

            => find first index become true
            => minus one
            => last index holds false
        */
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left - 1;
    }
}
