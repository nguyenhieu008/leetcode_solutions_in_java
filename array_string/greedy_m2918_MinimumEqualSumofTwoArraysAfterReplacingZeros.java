// https://leetcode.com/problems/minimum-equal-sum-of-two-arrays-after-replacing-zeros/description/

// Solution: We calculate the min possible sum for each array, replace 0 by 1.
// If an array with less minSum and does not have zero (cannot increased) => return -1
// Otherwise, we return the max between minSums => it will be the minimum sum when replaced 0.
// Time complexity: O(n);
// Space complexity: O(1);

class Solution {
    public long minSum(int[] nums1, int[] nums2) {
        long minSum1 = 0, minSum2 = 0;
        boolean zero1 = false, zero2 = false;
        for (int v : nums1) {
            if (v == 0) {
                zero1 = true;
                minSum1++;
            } else {
                minSum1 += v;
            }
        }

        for (int v : nums2) {
            if (v == 0) {
                zero2 = true;
                minSum2++;
            } else {
                minSum2 += v;
            }
        }
        if ((zero1 == false && minSum1 < minSum2) || (zero2 == false && minSum2 < minSum1)) {
            return -1;
        }
        return Math.max(minSum1, minSum2);
    }
}
