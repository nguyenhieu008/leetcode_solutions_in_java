// https://leetcode.com/problems/subarray-sums-divisible-by-k/description/

// Solution: For each i, we calculate sum at i => we need to find number of previous prefixSum which mod k == sum. Need to care about the negative case. 
// Use array for shorter code, and we need to store all possible remainers of modulo k.
// Time complexity: O(n)
// Space complexity: O(k);
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int[] freq = new int[k];
        freq[0] = 1;

        int sum = 0;
        int res = 0;
        for (int v : nums) {
            sum = calMod(sum + v, k);
            res += freq[sum];
            freq[sum]++;
        }
        
        return res;
    }

    private int calMod(int a, int k) {
        return ((a % k) + k) % k;
    }
}
