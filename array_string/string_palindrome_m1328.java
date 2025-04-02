// https://leetcode.com/problems/break-a-palindrome/description/

class Solution {
    public String breakPalindrome(String palindrome) {
        char[] s = palindrome.toCharArray();
        int n = s.length;

        if (n <= 1) return "";

        // For case "aba", we should not change b -> a, because changing middle char does not break palindrome
        // => We should only iterate through the first halp to handle this case.
        // The second half is the same
        for (int i = 0; i < n/2; i++) {
            if (s[i] != 'a') {
                s[i] = 'a';
                return new String(s);
            }
        }

        // If all the chars are 'a' => we change the last one to 'b' to be the smallest
        s[n-1] = 'b';
        return new String(s);
    }
}
