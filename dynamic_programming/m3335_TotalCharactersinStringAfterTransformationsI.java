// https://leetcode.com/problems/total-characters-in-string-after-transformations-i/description/

// Solution 2: DP:
// dp[i] = numbers of characters we get if we start from 'a' and apply i transformations.
// Let's examine:
// t:        1    2            25
// s:        a    b            z    
// dp[t]:    1    1            1
// t:        26   27           51
// s:        ab   bc           zab    
// dp[t]:    2    2            3
// t:        52   53           77
// s:        abbc bccd         zababbc
// dp[t]:    4    4            7
// Let's see: s[26] = s[0] + s[1]
//            s[51] = s[25] + s[26]
//            s[52] = s[26] + s[27]
//            ...
// In fact, for a character "a", after 26 transformations, it turn to be "ab". Which is "(A)(B)", "(s[26 - 26])(s[26 - 26 + 1])"
// So, this creates a recurrence relations, after 26 moves, it will create another string consists of 2 parts where
//     - First part is the same as the 26-previous string
//     - Second part is that one applied 1 transformation.
// s[t] = (A) => s[t + 26] = (A)(A applied 1 tranformation) = (AB)
// E.g. (A) => (A)(B) = (AB) => ((A)(B))((B)(C)) = (AB)(BC)
// So, we build a dp of how a character a transformed after t, and of couse, base case needs to cover all 26 charactesr.
// Then for each character, we check that, after t transformation, how many character does it produce.
// Time complexity: O(t + n)
// Space complexity: O(t)

class Solution {
    public int lengthAfterTransformations(String s, int t) {
        final int MOD = (int)1e9 + 7;
        // dp[i] = numbers of characters we get if we start from 'a' and apply i transformations.
        int[] dp = new int[t + 26];
        for (int i = 0; i < 26; i++) {
            dp[i] = 1;
        }
        for (int i = 26; i < t + 26; i++) {
            dp[i] = (dp[i - 26] + dp[i - 25]) % MOD;
        }
        int res = 0;
        for (char c : s.toCharArray()) {
            res = (res + dp[c - 'a' + t]) % MOD;
        }
        return res;
    }
}

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
