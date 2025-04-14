// https://leetcode.com/problems/valid-parentheses/description/
// Simple solution, use map and set to make clean code.

class Solution {
    public boolean isValid(String s) {
        HashMap<Character, Character> closeToOpenBracket = new HashMap<>(Map.of(')', '(', ']', '[', '}', '{'));
        HashSet<Character> openBracketSet = new HashSet<>(Arrays.asList('(', '[', '{'));
        Stack<Character> openBrackets = new Stack<>();

        for (char c : s.toCharArray()) {
            if (openBracketSet.contains(c)) {
                openBrackets.push(c);
            } else {
                if (openBrackets.isEmpty() || openBrackets.pop() != closeToOpenBracket.get(c)) {
                    return false;
                }
            }
        }
        return openBrackets.isEmpty();
    }
}

// Another clean solution as well. Notice that we allow to add closing bracket to stack, as it will yield the false result anyway.

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (!stack.isEmpty()) {
                char last = stack.peek();
                if (isPair(last, cur)) {
                    stack.pop();
                    continue;
                }
            }
            stack.push(cur);
        }

        return stack.isEmpty();        
    }

    private boolean isPair(char last, char cur) {
        return (last == '(' && cur == ')') ||
               (last == '{' && cur == '}') ||
               (last == '[' && cur == ']');
    }    
}
