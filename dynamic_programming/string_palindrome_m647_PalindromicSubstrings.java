// https://leetcode.com/problems/palindromic-substrings/description/

// Solution 3: Expand from center.
// Time complexity: O(n ^ 2)
// Space complexity: O(1)
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        int res = 0;

        for (int i = 0; i < n; i++) {
            int odd = countPalindromes(s, i, i);
            int even = countPalindromes(s, i, i + 1);
            res += odd + even;
        }        
        return res;
    }

    private int countPalindromes(String s, int left, int right) {
        int res = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            res++;
            left--;
            right++;
        }
        return res;
    }
}

// Solution 2a: DP tabulation, shorter than solution 2, because we care about the palindrome string of len 1 [i, i] in the for loop.
// Time complexity: O(n ^ 2)
// Space complexity: O(n ^ 2)
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] palindrome = new boolean[n][n];
        int res = 0;

        for (int right = 0; right < n; right++) {
            for (int left = 0; left <= right; left++) {
                if (s.charAt(right) == s.charAt(left)) {
                    if (left + 1 > right - 1 || palindrome[left + 1][right - 1]) {
                        palindrome[left][right] = true;
                        res++;
                    }
                }
            }
        }
        return res;
    }
}

// Solution 2: DP tabulation
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] palindrome = new boolean[n][n];
        int res = 0;

        for (int i = 0; i < n; i++) {
            palindrome[i][i] = true;
            res++;
        }

        for (int right = 1; right < n; right++) {
            for (int left = 0; left < right; left++) {
                if (s.charAt(right) == s.charAt(left)) {
                    if (left + 1 > right - 1 || palindrome[left + 1][right - 1]) {
                        palindrome[left][right] = true;
                        res++;
                    }
                }
            }
        }
        return res;
    }
}

// Solution 1a: bottom-up dp and memoize. Different memoization technique compared to solution 2
// But solution 2 is more readable, but needs space complexity: O(n ^ 2)
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        List<Integer> prevPalindromes = new ArrayList<>();
        int res = 0;

        for (int i = 0; i < n; i++) {
            List<Integer> newPalindromes = new ArrayList<>();
            char c = s.charAt(i);

            for (int left : prevPalindromes) {
                if (left > 0 && c == s.charAt(left - 1)) {
                    newPalindromes.add(left - 1);
                }
            }

            newPalindromes.add(i);
            // update res before the next statement
            res += newPalindromes.size();

            newPalindromes.add(i + 1); // make sure the next char will test for palin start at i
            prevPalindromes = newPalindromes;
        }
        return res;
    }
}

// Solution 1: top-down dp and memoize. Not recommend:
class Solution {
    List<Integer> prevPalins;
    public int countSubstrings(String s) {
        prevPalins = new ArrayList<>();
        return countPalindrome(s, s.length() - 1);
    }

    // prevPalins store the list of left pointer that makes a palindrome that ends at idx - 1
    private int countPalindrome(String s, int idx) {
        if (idx < 0) {
            return 0;
        }
        
        int countPrev = countPalindrome(s, idx - 1);
        // System.out.println("prev = " + prevPalins);

        char c = s.charAt(idx);
        List<Integer> temp = new ArrayList<>();
        temp.add(idx);
        if (idx > 0 && s.charAt(idx - 1) == c) {
            temp.add(idx - 1);
        }

        for (int left : prevPalins) {
            if (left > 0 && s.charAt(left - 1) == c) {
                temp.add(left - 1);
            }
        }
        // System.out.println("temp = " + temp);
        prevPalins = temp;

        return countPrev + temp.size();
    }
}
