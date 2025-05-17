// https://leetcode.com/problems/reverse-vowels-of-a-string/description/

class Solution {
    public String reverseVowels(String s) {
        int n = s.length();
        char[] res = s.toCharArray();
        for (int left = 0, right = n - 1; left < right; left++, right--) {
            while (left < right && !isVowel(res[left])) {
                left++;
            }
            while (left < right && !isVowel(res[right])) {
                right--;
            }
            char temp = res[left];
            res[left] = res[right];
            res[right] = temp;
        }
        return new String(res);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e'|| c == 'i' || c == 'o' || c == 'u'
               || c == 'A' || c == 'E'|| c == 'I' || c == 'O' || c == 'U';
    }
}
