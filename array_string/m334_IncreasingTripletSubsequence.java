// https://leetcode.com/problems/increasing-triplet-subsequence/description/

// Solution 2: The trickiest problem here is how to deal with first case where the mid == small.
// THE TRICK HERE IS: use 2 int max => the mid will only be init if there is a real mid value
// => if the mid value is initialized (< max_value), then we can base on that value to determine whether the current one is the largest
// and form a triplet. 
// Other ways to have small and mid point to the same value will not be solved easily

class Solution {
    public boolean increasingTriplet(int[] nums) {
        int small = Integer.MAX_VALUE, mid = Integer.MAX_VALUE;
        for (int v : nums) {
            if (v <= small) {
                small = v;
            } else if (v <= mid) {
                mid = v;
            } else {
                return true;
            }
        }
        return false;
    }
}

// Solution 1: Preprocess both ends.
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int[] minLeft = new int[n];
        int[] maxRight = new int[n];

        // Min/max can be the current one, as it does not affect the final result
        minLeft[0] = nums[0];       
        maxRight[n-1] = nums[n-1];

        for (int i = 1; i < n; i++) {
            minLeft[i] = Math.min(minLeft[i-1], nums[i]);
            maxRight[n-1-i] = Math.max(maxRight[n-i], nums[n-1-i]);
        }

        for (int i = 1; i < n - 1; i++) {
            if (minLeft[i] < nums[i] && nums[i] < maxRight[i]) {
                return true;
            }
        }
        return false;
    }
}
