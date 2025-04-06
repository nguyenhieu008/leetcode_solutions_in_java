// https://leetcode.com/problems/longest-common-prefix/description
// self-done

class Solution {
    public String longestCommonPrefix(String[] strs) {
        String res = strs[0];
        int maxCommon = res.length();
        for (String s : strs) {
            int common = 0;
            for (int i = 0; i < s.length() && i < maxCommon; i++) {
                if (s.charAt(i) != res.charAt(i)) {
                    break;
                }
                common++;
            }
            maxCommon = common;
        }
        return res.substring(0, maxCommon);
    }
}

// reference: sort => the first and last one of sorted array are different the most => can find longest common prefix by those two.
// Remember that, the order of strings based on the characters from left->right => it works.
class Solution {
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        String s1 = strs[0];
        String s2 = strs[strs.length-1];
        int idx = 0;
        while(idx < s1.length() && idx < s2.length()){
            if(s1.charAt(idx) == s2.charAt(idx)){
                idx++;
            } else {
                break;
            }
        }
        return s1.substring(0, idx);
    }
}
