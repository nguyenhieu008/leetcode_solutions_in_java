// https://leetcode.com/problems/removing-stars-from-a-string/description/

// Solution 3: Use 2 pointer to keep track where to fill character, but it's harder to read than stack

// Solution 2: Simulate stack by string builder
class Solution {
    public String removeStars(String s) {
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '*') {
                sb.setLength(sb.length() - 1); // no need to delete in string builder
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}

// Solution 1: Pure stack
class Solution {
    public String removeStars(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '*') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        char[] res = new char[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return new String(res);
    }
}
