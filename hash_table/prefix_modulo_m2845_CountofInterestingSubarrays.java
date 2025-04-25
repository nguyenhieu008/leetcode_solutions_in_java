// https://leetcode.com/problems/evaluate-division/
// Detail in comment.
// Time complexity: O(n)
// Space complexity: O(min(module, n))

class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        // [3       2   4           5         7     9]  : nums
        // [true false false      true      true    true]   : true if nums[i] % mod == k
        // [1       1       1       2        3        4  ] : prefixSum of true
/*
    Store by  number of prefixSum
        [0, 1]
        [1, 3]
        [2, 1]  
        [3, 1]
        [4, 1]
    But for a new sum, e.g. 5 => need to find all prefixSum == 4 ((5 - 4) % 2 == 1), prefixSum == 2 ((5 - 2) % 2 == 1), and prefixSum == 0
    => We can store the prefixSum by module: (0, 2, 4) % 2 == 0 => same item.
    => prefixSumModulo[curSum % modulo]++;
    => We have
        int[] prefixSum[modulo] 
        int sum

            Because we want to find idx where: (sum - idx) % mod == k => 
            (sum - k) % modulo = idx

            prefix[sum % modulo]++;

        After all, need to care about edge case: long type, 1e9 module => in that case, don't need to store all the possible moudlo result
        => We need to store at most [n + 1] item, because at each index, we increase the modulo at most 1 => maximum [n+1] item.
        */

        int n = nums.size();
        int[] prefixSumModulo = new int[Math.min(modulo, n + 1)];
        prefixSumModulo[0] = 1;
        int curSum = 0;

        long res = 0;
        for (int v : nums) {
            if (v % modulo == k) {
                curSum++;
            }
            int targetIdx = (curSum - k) % modulo;
            if (targetIdx >= 0) {
                res += prefixSumModulo[targetIdx];
            }
            prefixSumModulo[curSum % modulo]++;
        }
        return res;
    }
}
