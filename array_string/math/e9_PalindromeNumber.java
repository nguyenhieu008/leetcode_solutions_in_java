// https://leetcode.com/problems/palindrome-number/description/

// Solution 2: for a palindrome, we reverse the whole number (string) will make a similar number (string)
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int temp = x;
        int reversed = 0;
        while (temp != 0) {
            int digit = temp % 10;
            temp /= 10;
            reversed = reversed * 10 + digit;
        }
        return reversed == x;
    }
}

// self-done. extract the each numeral and compare to the symmetrical one. not recommended be cause complicated

class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int temp = x;
        int numerals = 0;
        while (temp != 0) {
            temp /= 10;
            numerals++;
        }
        for (int i = 0; i < numerals / 2; i++) {
            int firstPow = i;
            int smallNumber = (x % (int)Math.pow(10, firstPow + 1)) / (int)Math.pow(10, firstPow);

            int largePow = numerals - i - 1;
            int largeNumber = (x % (int)Math.pow(10, largePow + 1)) / (int)Math.pow(10, largePow);

            if (smallNumber != largeNumber) {
                return false;
            }
        }
        return true;
    }
}
