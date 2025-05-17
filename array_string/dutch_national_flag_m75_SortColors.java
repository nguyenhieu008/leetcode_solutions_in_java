// https://leetcode.com/problems/sort-colors/description/

// Solution 1: Maintain 2 pointers to fill the value of 0s and 2s
// If swap 2, we need to stay at i, because it can be 0 and should be swapped back.
// If swap 0, we can move forward i, because the swapped value will definitely be 0 or 1 (0 only for the starting 0s of the array)
// It's done in one-pass
// NOTICE: This problem is so-called Dutch National Flag
class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int pos0 = 0, pos2 = n - 1;
        int i = 0;
        while (i <= pos2) { 
            // if i == pos2, it swaps it itself and pos2 decrement => terminate
            if (nums[i] == 2) {
                // After swap, not increment i, because we need to handle that value (0 or 1)
                swap(nums, i, pos2);
                pos2--;
            } else if (nums[i] == 0) {
                // i always >= pos0
                swap(nums, i, pos0);
                pos0++;
                i++;
                // i = Math.max(i, pos0); // We can increment i as well
            } else {
                // If 1, simply do nothing
                i++;
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// Solution 2: Distribution counting
class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int[] count = {0, 0, 0};

        for (int v : nums) {
            count[v]++;
        }
        count[1] += count[0];
        count[2] += count[1];

        for (int i = 0; i < n; i++) {
            if (i < count[0]) {
                nums[i] = 0;
            } else if (i < count[1]) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }
}
