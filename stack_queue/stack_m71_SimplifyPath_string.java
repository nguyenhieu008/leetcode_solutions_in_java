// https://leetcode.com/problems/simplify-path/

class Solution {
    public String simplifyPath(String path) {
        int n = path.length();
        char[] pathArr = path.toCharArray();
        // When build the path, we need stack. But when we build string, we need the queue => use deque
        Deque<String> deque = new ArrayDeque<>(); 
        for (int i = 0; i < n; i++) {
            if (pathArr[i] == '/') {
                continue;
            }
            StringBuilder name = new StringBuilder();
            // only stop if see '/' or end of string => the i++ in for loop will skip that '/' and will not ignore real character.
            while (i < n && pathArr[i] != '/') {
                name.append(pathArr[i]);
                i++; 
            }

            String nameStr = name.toString();
            if (nameStr.equals("..")) {
                if (!deque.isEmpty()) {
                    deque.removeLast();
                }
            } else if (nameStr.equals(".")) {
                // Do nothing
                continue;
            } else {
                deque.offerLast(nameStr.toString());
            }
        }

        StringBuilder res = new StringBuilder();
        while (!deque.isEmpty()) {
            res.append("/" + deque.removeFirst());
        }
        if (res.isEmpty()) {
            return "/";
        }
        return res.toString();
    }
}
