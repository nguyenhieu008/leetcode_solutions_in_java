// https://leetcode.com/problems/can-make-palindrome-from-substring/description/

// Solution: Using prefix sum. Detail in comment.
// NOTICE THAT: we can only use true/false to know that the number of charactesr is odd or not.
// Then can improve by using 1 bit to store 1 character. But it's harder to implement and understand.
// Time complexity: O(26 * (n + q)) = O(n + q)
// Space complexity: O(26 * n) = O(n) 

class Solution {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        /*
            Because we can rearrange the substring, we just care about the number of characters within them
            To get the number of chars for a substring, store prefix sum of them, one for each character.

            int[n+1][26] prefixCount: [i][c] is the prefix sum for character c at index i, not including i (i.e. including s.charAt(i - 1) only)

            for query, we have left-right:
            - for c in [0, 26): count[c] = prefixCount[right + 1][c] - prefixCount[left][c];
            - odd: number of odd counts
            - if (odd / 2 <= k) => return true

        */

        int size = 26;
        int n = s.length();
        int[][] prefixCount = new int[n + 1][size];

        for (int i = 1; i <= n; i++) {
            // Ignore zero, since the prefix count excluding the current character
            for (int c = 0; c < size; c++) {
                prefixCount[i][c] = prefixCount[i-1][c];
            }
            // including the (i - 1) char
            prefixCount[i][s.charAt(i - 1) - 'a']++;
        }

        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            int left = q[0], right = q[1], k = q[2];
            int odd = 0;
            for (int c = 0; c < size; c++) {
                // right + 1: including the character at right
                // count = number of specific character between [left, right]
                int count = prefixCount[right + 1][c] - prefixCount[left][c];
                if (count % 2 == 1) {
                    odd++;
                }
            }
            boolean canMake = ((odd / 2) <= k);
            res.add(canMake);
        }
        return res;
    }
}
