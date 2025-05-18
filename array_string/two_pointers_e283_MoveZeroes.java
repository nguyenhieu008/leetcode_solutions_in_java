// https://leetcode.com/problems/move-zeroes/description/

// Solution: We use a slow pointer to fill the next non-zero value, while fast pointer keeps going forward, ignore 0 and swap non-0 value.
// => While moving, the left pointer will be the first index of all-zeors-segment.

class Solution {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int left = 0; // All items before left are non-zero
        for (int right = 0; right < n; right++) {
            // if nums[right] = 0, we do nothing because 0 is now already after left
            if (nums[right] != 0) {
                // swap with left, then increment left 
                // => all non-zero items will placed before left
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
        }
    }
}
