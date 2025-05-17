// https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/

class Solution {
    public int maxVowels(String s, int k) {
        int n = s.length();
        int maxVowel = 0;
        int countVowel = 0;
        for (int left = -k, right = 0; right < n; left++, right++) {
            if (isVowel(s.charAt(right))) { // vowel condition
                countVowel++;
            }
            if (left >= 0 && isVowel(s.charAt(left))) {
                countVowel--;
            }
            maxVowel = Math.max(maxVowel, countVowel);
        }
        return maxVowel;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
