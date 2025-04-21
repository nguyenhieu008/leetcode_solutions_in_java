// https://leetcode.com/problems/longest-palindromic-substring/description/

// Solution 2: At each i, we expand from center. Notice that we need 2 cases, 1 for 1-point center and another for 2-points center.
// Save indexes of the max and return result
// Time complexity: O(n^2)
// Space complexity: O(1)

class Solution {
    public String longestPalindrome(String s) {
        int left = 0, right = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            int oddLength = expand(s, i, i);
            if (oddLength > (right - left + 1)) {
                int half = oddLength / 2;
                left = i - half;
                right = i + half;
            }

            int evenLength = expand(s, i, i + 1);
            if (evenLength > (right - left + 1)) {
                int half = evenLength / 2;
                left = i - half + 1;
                right = i + half;
            }
        }
        return s.substring(left, right + 1);
    }

    private int expand(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return j - i - 1;
    }
}

// Solution 3: 2-d dp. Reference: https://leetcode.com/problems/longest-palindromic-substring/solutions/3598120/longest-palindromic-substring/

// Solution 1: self-done. At each i, we must append the palindrome to (i - 1) => We add all palindromes that ends at current index to a queue, pop them all for the next index.
// Notice that, we need to add the current index as well, as it 1-length palinedrome. previous (i-1) if s[i] == s[i-1]. And need to add null to separate indexes.
// Time complexity: O(n^2)
// Space complexity: O(n)
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        char[] sChar = s.toCharArray();
        Queue<Integer> palindromeIdx = new LinkedList<>();
        int left = 0, right = 0, length = 1;

        for (int i = 0; i < n; i++) {
            while (!palindromeIdx.isEmpty()) {
                Integer idx = palindromeIdx.poll();
                if (idx == null) {
                    break;
                }
                if (idx > 0 && sChar[i] == sChar[idx-1]) {
                    palindromeIdx.offer(idx-1);
                    if ((i - idx + 2) > length) { 
                        left = idx - 1;
                        right = i;
                        length = right - left + 1;
                    }
                }
            }
            palindromeIdx.offer(i);
            if (i > 0 && sChar[i] == sChar[i-1]) {
                palindromeIdx.offer(i-1);
                if (length == 1) {
                    left = i - 1;
                    right = i;
                    length = 2;
                }
            }
            palindromeIdx.offer(null);
        }
        return s.substring(left, right + 1);
    }
}
