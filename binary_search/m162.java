// https://leetcode.com/problems/find-peak-element/description/

// Solution: Notice that the index 0, and n-1, in considered > than adjacent => we will always have peaks, even if the inner items are decreasing, two ends of array are peaks.
// Now, let take an example, x denotes that items are less than the adjacent, so we have an array that both ends are greater than the adjacent items:
// [x] 1 2 3 4 5 6 7 [x], mid = 4 => x x x x 5 6 7 x 
// Because for peak, we only care about the adjacent item, so we remove the whole left of the mid (including mid). 
// We still guarantee: "an array that both ends are greater than the adjacent items (x 5 6 7 x)"
// So, downgrade until we have 1-sized array, it stills guarantee the feature, both 2-adjacent items are less than the current array including only 1 item => it's the peak

// Time complexity: O(logn);
// Space complexity: O(1)

class Solution {
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int l = 0, r = n - 1; 
        while (r - l > 0) {
            int mid = l + (r - l) / 2;

            if (nums[mid] < nums[mid + 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
