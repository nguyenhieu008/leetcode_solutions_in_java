// https://leetcode.com/problems/merge-strings-alternately/

// Solution 2: Use string builder
class Solution {
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.max(m, n); i++) {
            if (i < m) {
                sb.append(word1.charAt(i));
            }
            if (i < n) {
                sb.append(word2.charAt(i));
            }
        }
        return sb.toString();
    }
}

// Solution 1: Use char array
class Solution {
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        char[] res = new char[m + n];

        int curIdx = 0;
        for (int i = 0; i < Math.max(m, n); i++) {
            if (i < m) {
                res[curIdx] = word1.charAt(i);
                curIdx++;
            }
            if (i < n) {
                res[curIdx] = word2.charAt(i);
                curIdx++;
            }
        }
        return new String(res);
    }
}
