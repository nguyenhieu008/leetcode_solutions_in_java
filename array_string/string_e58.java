// https://leetcode.com/problems/length-of-last-word/

class Solution {
    public int lengthOfLastWord(String s) {
        String[] split = s.split(" ");
        return split[split.length - 1].length();
    }
}

class Solution {
    public int lengthOfLastWord(String s) {
        String trimmed = s.trim();
        int lastSpace = trimmed.lastIndexOf(" ");
        return trimmed.length() - lastSpace - 1;
    }
}

// Because finding the last word => go back
// If see space but res == 0, it's the trailing spaces, ignore. Otherwise, return the res right away.
class Solution {
    public int lengthOfLastWord(String s) {
        int n = s.length();
        int res = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (res > 0) return res;
            } else {
                res++;
            }
        }
        return res;
    }
}
