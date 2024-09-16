// https://leetcode.com/problems/beautiful-towers-i/description/

/* Solution 1: brute force solution
  */

class Solution {
    public long maximumSumOfHeights(int[] heights) {
        long res = 0;
        for (int i = 0; i < heights.length; i++) {
            long tempRes = heights[i];

            int curHeight = heights[i];
            for (int j = i - 1; j >= 0; j--) {
                if (heights[j] <= curHeight) {
                    curHeight = heights[j];
                }
                tempRes += curHeight;
            }

            curHeight = heights[i];
            for (int k = i + 1; k < heights.length; k++) {
                if (heights[k] <= curHeight) {
                    curHeight = heights[k];
                }
                tempRes += curHeight;
            }

            res = Math.max(res, tempRes);
        }
        return res;
    }
}
