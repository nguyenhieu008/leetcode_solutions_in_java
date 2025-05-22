// https://leetcode.com/problems/valid-palindrome-ii/description/

// Solution: we try to remove both chars when it does not match.
// Time complexity: O(n)
// Space complexity: O(1)
class Solution {
    public boolean validPalindrome(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return valid(s, left + 1, right) || valid(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    public boolean valid(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
