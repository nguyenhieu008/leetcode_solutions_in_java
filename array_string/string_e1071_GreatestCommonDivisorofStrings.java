// https://leetcode.com/problems/greatest-common-divisor-of-strings/description/

// Solution 2: KEY OBSERVATION HERE IS THAT IF 2 STRINGS HAVE GCD, IF AND ONLY IF 2 WAYS OF CONCATENATION OF THEM ARE EQUAL.
// In that case, there are some common divisor, if the common divisor length < gcd, it must also be the divisor for gcd => gcd can be maded up by the smaller divisor
// And because both strings contains multiple number of gcd, it will be the result.
// Time complexity: O(m + n), gcd takes O(logn) then will be fine.
// Space complexity: O(m + n), build 2 strings of (m + n) size

class Solution {
    public String gcdOfStrings(String str1, String str2) {
        int m = str1.length(), n = str2.length();

        if ((str1 + str2).equals(str2 + str1)) {
            int gcdLength = gcd(m, n);
            return str1.substring(0, gcdLength);
        }

        return "";
    }

    private int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

// Solution 1: Start from min of both strings and decrement continuously, we cut the subtring and check if it's the divisor of both strings.
// Time complexity: O(min(m, n) * (m + n)), the isDivisor function takes: O(n) where n is the length of the string
// Space complexity: O(min(m, n)) where we need to store the candidate

class Solution {
    public String gcdOfStrings(String str1, String str2) {
        int m = str1.length(), n = str2.length();

        for (int i = Math.min(m, n); i >= 1; i--) {
            if (m % i == 0 && n % i == 0) {
                String candidate = str1.substring(0, i);
                if (isDivisor(str1, candidate) && isDivisor(str2, candidate)) {
                    // Because we are decreasing i, we return immediately when found
                    return candidate;
                }
            }
        }
        return "";
    }

    private boolean isDivisor(String s, String candidate) {
        int l = candidate.length();
        for (int i = 0; i + l <= s.length(); i += l) {
            String cur = s.substring(i, i + l);
            if (!cur.equals(candidate)) {
                return false;
            }
        }
        return true;
    }
}
