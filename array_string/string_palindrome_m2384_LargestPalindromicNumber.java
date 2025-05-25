// https://leetcode.com/problems/largest-palindromic-number/description/

// Solution: When build a palindrome, only need to build the first half, then the second half can be reverse of first half.
// Notice that we should make the middle from the largest posible number with odd appearance.
// Care about some edge cases: 
// "0000" -> "0"
// "50000" -> "5"
// "22000" -> "20002"
// Time complexity: O(n)
// Space complexity: O(1)

class Solution {
    public String largestPalindromic(String num) {
        int[] digitCount = new int[10];
        for (char c : num.toCharArray()) {
            digitCount[c - '0']++;
        }

        StringBuilder first = new StringBuilder();
        String middle = "";
        for (int i = 9; i >= 0; i--) {
            int count = digitCount[i];
            if (count % 2 == 1 && middle.isEmpty()) {
                middle = Character.toString(toChar(i));
            }

            if (first.length() == 0 && i == 0) {
                return middle.isEmpty() ? "0" : middle;    
            }
            
            if (count >= 2) {
                appendRepeat(first, toChar(i), count / 2);
            }
        }
        StringBuilder second = new StringBuilder(first);

        return first.toString() + middle + second.reverse().toString();
    }

    private void appendRepeat(StringBuilder sb, char c, int repeat) {
        for (int r = 0; r < repeat; r++) {
            sb.append(c);
        }
    }
    
    private char toChar(int i) {
        return (char)('0' + i);
    }
}
