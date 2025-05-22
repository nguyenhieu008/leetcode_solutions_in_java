// https://leetcode.com/problems/backspace-string-compare/description/

// Solution 2: space optimized. The backspace will modify the previous character, so it will change overtime so we cannot base on that.
// Instead, we go backward the compare the last characters. Going this way, we know the number of backspace applied,
// so we can definitely determine the next character, without storing them in a stack.
// Time complexity: O(m + n)
// Space complexity: O(1)

class Solution {
    public boolean backspaceCompare(String s, String t) {
        /* 
            m: s length
            n: t length

            first: pointer iterating s, from (m - 1) -> 0
            second: (n - 1) -> 0

            - Loop first until we get a character:
        */
        int m = s.length(), n = t.length();
        int left = m - 1, right = n - 1;

        while (left >= 0 || right >= 0) {
            left = getNextChar(s, left);
            right = getNextChar(t, right);

            if ((left == -1 && right >= 0) || (left >= 0 && right == -1)) {
                return false;
            }

            if (left >= 0 && right >= 0 && s.charAt(left) != t.charAt(right)) {
                return false;
            }

            left--;
            right--;
        }
        return true;
    }

    private int getNextChar(String s, int i) {
        int count = 0;  // count of #
        while (i >= 0 && count >= 0) {
            if (s.charAt(i) == '#') {
                count++;
            } else {
                count--;
            }

            if (count >= 0) {
                i--;
            }
        }

        return i;
    }
}

// Solution 1: build the final string and compare them.
// Time complexity: O(m + n)
// Space complexity: O(m + n)
class Solution {
    public boolean backspaceCompare(String s, String t) {
        String s1 = build(s), s2 = build(t);
        return s1.equals(s2);
    }

    private String build(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '#') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }
        return String.valueOf(stack);
    }
}
