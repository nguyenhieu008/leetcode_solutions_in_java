// https://leetcode.com/problems/4sum-ii/description/

// Solution: Treat it as (a + b) + (c + d), so for every pairs of c-d, we need to find number of pairs a-b that sums up to the same value
// => We use a hash table to store the sum value and its corresponding count.
// Time complexity: O(n^2)
// Space complexity: O(n^2), hash table to store n1 * n2 possible values.

class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> sumCount = new HashMap<>();

        for (int v1 : nums1) {
            for (int v2 : nums2) {
                sumCount.put(v1 + v2, sumCount.getOrDefault(v1 + v2, 0) + 1);
            }
        }

        int res = 0;
        for (int v3 : nums3) {
            for (int v4 : nums4) {
                res += sumCount.getOrDefault(-v3 - v4, 0);
            }
        }
        return res;
    }
}
