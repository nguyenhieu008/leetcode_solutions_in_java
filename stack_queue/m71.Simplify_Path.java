// https://leetcode.com/problems/simplify-path/description/

class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<String>();

        path += "/";
        int start = 0;
        for(int i = 0; i < path.length(); i++) {    
            if (path.charAt(i) == '/') {
                String temp = path.substring(start, i);
                if (temp.equals("..")) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else if (temp.equals("") || temp.equals(".")) {
                    // Do nothing
                } else {
                    stack.push(temp);
                }
                start = i + 1;
            } 
        }

        StringBuilder resBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            resBuilder.insert(0, "/" + stack.pop());
        }
        String res = resBuilder.toString();
        return res.equals("")? "/" : res;
    }
}
