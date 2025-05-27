// https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/

class Solution {
    public String minRemoveToMakeValid(String s) {
        int n = s.length();
        Deque<Integer> removedIdx = new ArrayDeque<>();

        // We want to use stack to keep track of removing indexes.
        // Buf when popping out to build the final string, we want to get the early indexes first
        // So we use deque to be able to pop from the other end.
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                removedIdx.offerLast(i);
            } else if (c == ')') {
                if (!removedIdx.isEmpty() && s.charAt(removedIdx.peekLast()) == '(') {
                    removedIdx.pollLast();
                } else {
                    // If there is something else in the stack, not '(', then it would be ')' and will be removed
                    // So just push them all then remove later.
                    removedIdx.offerLast(i);
                }
            }
        }

        if (removedIdx.isEmpty()) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        int cur = 0;
        while (!removedIdx.isEmpty()) {
            int idx = removedIdx.pollFirst();
            sb.append(s.substring(cur, idx));
            cur = idx + 1;
        }
        sb.append(s.substring(cur, n));
        return sb.toString();
    }
}
