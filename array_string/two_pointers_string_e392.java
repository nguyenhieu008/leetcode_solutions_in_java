// https://leetcode.com/problems/is-subsequence/description

// Solution 1: This is kind of greedy matching
class Solution {
    public boolean isSubsequence(String s, String t) {
        // only lower case => no need to check`
        int i = 0, j = 0;
        while (i < s.length()) {
            while (j < t.length() && s.charAt(i) != t.charAt(j)) {
                j++;
            }
            // Reach here when 1. reach end of t; 2. found a match
            if (j == t.length()) {
                // reach end of t => s is not a full subsequence of t
                return false;
            }
            // Reach here when 2.found a match => increase pointers to next chars
            i++; j++;
        }
        return true;
    }
}

// Other solution could be recursive (same idea as solution 1), memoization, binary search (with indexes of each char in increasing order => need a hash table of each char and its corresponding indexes)
