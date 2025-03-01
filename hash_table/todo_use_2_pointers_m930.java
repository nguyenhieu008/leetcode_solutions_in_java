// https://leetcode.com/problems/binary-subarrays-with-sum/description/

// Solution 2: TODO, use 2-pointers.

// Solution 1: number of non-empty subarrays with sum = goal, equals the number of prefixSums = sum - goal. So, we calculate prefix sum and add its frequency to hash table.
// Time complexity: O(n)
// Space complexity: O(n);

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        HashMap<Integer, Integer> map = new HashMap<>(Map.of(0, 1));

        int res = 0;
        int sum = 0;
        for (int v : nums) {
            sum += v;
            res += map.getOrDefault(sum - goal, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
