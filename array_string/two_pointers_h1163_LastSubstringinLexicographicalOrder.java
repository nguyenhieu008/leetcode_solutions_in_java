// https://leetcode.com/problems/last-substring-in-lexicographical-order/description/

// Solution: Detailed solution is here:
// https://leetcode.com/problems/last-substring-in-lexicographical-order/solutions/363662/short-python-code-o-n-time-and-o-1-space-with-proof-and-visualization/
// One observation, if 2 substrings both start from i, the longer one will be grater => the result will already reach the end of string
// or called suffix.
// In short, lest imagine (only show index for easy understanding):
// i = 1, j = 3, k = 6
// 1  2  3  4  5  6  7
// i                (i + k)
//       3  4  5  6  7  8  9
//       j                (j + k)
// Before k, any d < k, we have s[i + d] == s[j + d]
// segment [1 -> 6] == [3 -> 8] for all character, 
// => For every d, let's say d = 4, segment [5 -> 6] == [7 -> 8]
// s[i + k] > s[j + k], s[7] > s[9], so when we append the the same segments before
// It will make segment: [5 -> 7] > [7 -> 9], [4 -> 7] > [6 -> 9], and the same for all other segments
// => In other words, any j starting from [3 -> 9] cannot be the start of the result string
// => we skip j straight to (j + k + 1)
// Same for i, i = (i + k + 1), but in case (i + K + 1) < j => we will go stright to j.

// Time complexity: O(n)
// Space complexity: O(1)

class Solution {
    public String lastSubstring(String s) {
        int n = s.length();
        int i = 0, j = 1, k = 0; // i always < j
        while (j + k < n) {
            char c1 = s.charAt(i + k), c2 = s.charAt(j + k);
            if (c1 == c2) {
                k++;
            } else if (c1 > c2) {
                j = j + k + 1; // skip segment [j, j + k] where guaranteed no solution here
                k = 0;
            } else {
                // Use max to opmize, but required, in fact, always set (i = i + k + 1) will also work
                // But because the one before j is guaranteed not the result
                // => in case (i + k + 1 < j), skip straight through to j
                // But in that cases, j must go back => NOT GUARANTEED O(N)
                // => WE NEED TO KEEP THE MAX
                i = Math.max(i + k + 1, j); 
                j = i + 1;
                k = 0;
            }
        }
        return s.substring(i);
    }
}
