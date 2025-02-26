// https://leetcode.com/problems/count-good-meals/

// Because max is 2^20 + 2^20 = 2^21 => targets is from 1, 2, ..., 2^21
// We store all the targets and maintain a hash map to count how many times an item appears.
// For each item d, we need to loop 22 times to find 22 possible complements of d then add their appearance to result.
// Time complexity: O(22n)
// Space complexity: O(n)

class Solution {
    public int countPairs(int[] deliciousness) {
        final int MOD = (int) 1e9 + 7;
        final int maxPower = 21;

        HashMap<Integer, Integer> count = new HashMap<>();
        int[] targets = new int[maxPower + 1];
        targets[0] = 1;

        for (int i = 1; i <= maxPower; i++) {
            targets[i] = targets[i - 1] * 2;
        }

        int res = 0;
        for (int d : deliciousness) {
            for (int i = 0; i <= maxPower; i++) {
                if (count.containsKey(targets[i] - d)) {
                    res = (res + count.get(targets[i] - d)) % MOD;    
                }
            }
            count.put(d, count.getOrDefault(d, 0) + 1);
        }
        return res;
    }
}
