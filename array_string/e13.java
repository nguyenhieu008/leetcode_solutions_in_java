// https://leetcode.com/problems/roman-to-integer/

class Solution {
    public int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        int res = 0;
        for (int i = 0; i < n; i++) {
            char c = chars[i];
            int value = valueOf(c);
            if (i + 1 < n && valueOf(chars[i + 1]) > value) {
                res += valueOf(chars[i + 1]) - value;
                i++;
            } else {
                res += value;
            }
        }
        return res;
    }
    private int valueOf(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
