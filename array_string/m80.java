// Solution 2:
class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        
        // fillIdx is the current top of the "result array". This "result array" with the fillIdx will be returned.
        int fillIdx = 0;

        // j is the current top of original array
        for (int j = 0; j < n; j++) {
            // if j < 2, we just want to increase the fillIdx 
            // for a new item, we want to compare it with the "result array"
            // It means we do not add an item to the "result array" more than twice
            // => 2 most recent items of the "result array" is nums[fillIdx-2] and nums[fillIdx-1]
            // => If nums[j] != nums[fillIdx-2], we can guarantee it's a new number and not added more than twice to the "result item"
            if (j < 2 || nums[j] != nums[fillIdx - 2]) {
                nums[fillIdx] = nums[j];
                fillIdx++;
            }
        }
        return fillIdx;
    }
}

// Solution 1: self done, complicated. Should go solution 2.

class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        // Keep track of value and count of it
        int curCount = 1;
        int curVal = nums[0];
        // index to fill
        int fillIdx = 1;
        
        for (int i = 1; i < n; i++) {
            if (nums[i] != curVal) {
                // if it's diff => reset
                curVal = nums[i];
                curCount = 1;
            } else {
                // Otherwise, increase count
                curCount++;
            }
            if (curCount <= 2) {
                // Only fill if at most 2
                nums[fillIdx] = nums[i];
                fillIdx++;
            }
        }
        return fillIdx;
    }
}
