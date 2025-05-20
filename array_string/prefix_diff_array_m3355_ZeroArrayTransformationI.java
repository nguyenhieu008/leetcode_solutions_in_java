// https://leetcode.com/problems/zero-array-transformation-i/description/

// Solution: detail in comment.
// Time complexity: O(max(n, q))
// Space complexity: O(n)

class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        // start[i]: number of queries start at i
        // end[i]: number of queries end at i
        // cur: number of operations that can apply to the value at index i
        // cur should >= so the value can be decreased to 0
        // => cur += start[i]
        // if (cur >= nums[i]) => ok.
        // if not => return false;
        // cur -= end[i]

        int n = nums.length;
        int[] start = new int[n];
        int[] end = new int[n];

        for (int[] q : queries) {
            start[q[0]] ++;
            end[q[1]] ++;
        }

        int maxOps = 0;
        for (int i = 0; i < n; i++) {
            maxOps += start[i];
            if (maxOps < nums[i]) {
                return false;
            }
            maxOps -= end[i];
        }
        return true;
    }
}
