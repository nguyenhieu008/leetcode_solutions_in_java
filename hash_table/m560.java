// https://leetcode.com/problems/subarray-sum-equals-k/submissions/

// Solution: We call the prefix sum at i is sum[i] => Number of subarray ends at i, which has sum = k, is number of sum[j] with sum[i] - sum[j] = k
// We need to maintain a hash table to store the frequency of each value of prefix sum.
// Time complexity: O(n);
// Space complexity: O(n)

class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(Map.of(0, 1));

        int sum = 0;
        int res = 0;
        for (int v : nums) {
            sum += v;
            res += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
