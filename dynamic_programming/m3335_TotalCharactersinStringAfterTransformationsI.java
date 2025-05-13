// https://leetcode.com/problems/total-characters-in-string-after-transformations-i/description/

// Solution 1: Consider using count as a dp, which is the count of a character after i transformations.
// We can use 1d array to store the count to save memory
// => Tranform from (i - 1) by: count[c] = count[c - 1]; count[0] = count[25]; count[1] += count[25]; (because b can be yielded from z as well)
// Time complexity: O(26*t + n) = O(t + n)
// Space complexity: O(26) = O(1)

class Solution {
    public int lengthAfterTransformations(String s, int t) {
        int n = s.length();
        final int SIZE = 26;
        final int MOD = (int)1e9 + 7;
        int[] count = new int[SIZE];
        

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        for (int trans = 1; trans <= t; trans++) {
            int zNum = count[SIZE - 1];
            for (int i = SIZE - 1; i >= 1; i--) {
                // to 'b' == 1
                count[i] = count[i-1];
            }

            count[0] = zNum; // no need to mod
            count[1] = (count[1] + zNum) % MOD;
            // System.out.println("count: " + Arrays.toString(count));
        }

        int res = 0;
        for (int i = 0; i < SIZE; i++) {
            res = (res + count[i]) % MOD;
        }
        return res;
    }
}
