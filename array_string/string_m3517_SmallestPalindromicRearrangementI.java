// https://leetcode.com/problems/smallest-palindromic-rearrangement-i/description/
// My solution: https://leetcode.com/problems/smallest-palindromic-rearrangement-i/solutions/6645738/java-sort-and-reflect-through-middle-point-beat-100/

// Use distribution counting sort.
class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        int[] charFrequency = new int[26];
        for (int i = 0; i < n / 2; i++) {
            charFrequency[s.charAt(i) - 'a']++;
        }

        char[] res = s.toCharArray(); // Use toCharArray so we no need to handle middle point
        int fillIdx = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < charFrequency[i]; j++) {
                res[fillIdx] = (char)('a' + i);
                res[n - 1 - fillIdx] = (char)('a' + i);
                fillIdx++;
            }
        }
        return new String(res);
    }
}

// Use normal sort. And reflect, because Java does not allow sort by reverse order when comes to primitive types.

class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        char[] res = s.toCharArray();
        Arrays.sort(res, 0, n / 2);
        for (int i = n - 1; i >= n / 2; i--) {
            res[i] = res[n - 1 - i];
        }
        return new String(res);
    }
}
