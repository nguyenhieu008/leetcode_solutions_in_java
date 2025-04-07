// https://leetcode.com/problems/reverse-words-in-a-string/

// Solution 2: Use trim, split, and stringbuilder
class Solution {
    public String reverseWords(String s) {
        String[] splitWords = s.trim().split("\\s+");
        // System.out.println("word = " + Arrays.toString(splitWords));
        StringBuilder sb = new StringBuilder();

        for (int i = splitWords.length - 1; i >= 0; i--) {
            sb.append(splitWords[i]);
            if (i > 0) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }
}

// Solution 1a: Same idea as solution 1. But code structure is a bit better. from reference:
// https://leetcode.com/problems/reverse-words-in-a-string/

class Solution {
    public String reverseWords(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int i = 0, fillIdx = 0;

        reverse(chars, 0, n - 1);
        while (i < n) {
            // skip multiple spaces and find wordStart for next word
            while (i < n && chars[i] == ' ') {
                i++;
            }

            // Only add ' ' before next word if it's not that first word, or there are words left
            if (i < n && fillIdx > 0) {
                chars[fillIdx] = ' ';
                fillIdx++;
            }

            int wordStart = fillIdx;
            // Fill the word to result char array (in-place)
            while (i < n && chars[i] != ' ') {
                chars[fillIdx] = chars[i];
                fillIdx++;
                i++;
            }

            // reverse the recent filled word
            reverse(chars, wordStart, fillIdx - 1);
        }
        
        // fillIdx now store the length of resulting string
        return new String(chars, 0, fillIdx);
    }

    private void reverse(char[] s, int start, int end) {
        for (int l = start, r = end; l < r; l++, r--) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
        }
    }
}

// Solution 1: self-done. detail in comments. A bit long. Can be solve better.

class Solution {
    public String reverseWords(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int i = 0;
        // Preprocess string, trim/remove unnecessary spaces
        for (int j = 0; j < n; j++) {
            if (chars[j] != ' ') {
                chars[i++] = chars[j];
            } else {
                if (i > 0 && (j < n - 1 && chars[j + 1] != ' ')) {
                    chars[i++] = ' ';
                }
            }
        }

        // create a res array which contains valid string (not reversed)
        char[] res = Arrays.copyOfRange(chars, 0, i);
        // reverse whole array
        reverse(res, 0, i - 1);
        int prev = 0;
        for (int j = 0; j <= i; j++) {
            // reverse each word
            if (j == i || res[j] == ' ') {
                reverse(res, prev, j - 1);
                prev = j + 1;
            }
        }
        return new String(res);
    }

    private void reverse(char[] s, int start, int end) {
        for (int l = start, r = end; l < r; l++, r--) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
        }
    }
}
