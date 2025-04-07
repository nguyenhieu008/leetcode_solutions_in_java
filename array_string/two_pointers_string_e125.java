// https://leetcode.com/problems/valid-palindrome/description/

// Solution 2: Just loop through the string, ignore the invalid characters. Then use Character.toLowerCase to compare.
// Time complexity: O(n);
// Space complexity: O(1)
class Solution {
    public boolean isPalindrome(String s) {
        for (int l = 0, r = s.length() - 1; l < r; l++, r--) {
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) {
                l++;
            }
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) {
                r--;
            }
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            }
        }
        return true;
    }
}

// Solution 1: preprocess the string first. Then validate the palindrome as usual. It's a bit complicated and need more spaces
// Time complexity: O(n);
// Space complexity: O(n), for lower and filtered string

class Solution {
    public boolean isPalindrome(String s) {
        String lower = s.toLowerCase();
        StringBuilder filtered = new StringBuilder();
        for (int i = 0; i < lower.length(); i++) {
            if (Character.isLetterOrDigit(lower.charAt(i))) {
                filtered.append(lower.charAt(i));
            }
        }
        for (int l = 0, r = filtered.length() - 1; l < r; l++, r--) {
            if (filtered.charAt(l) != filtered.charAt(r)) {
                return false;
            }
        }
        return true;
    }
}
