// https://leetcode.com/problems/three-divisors/description/

// Solution 2: Detail in comment.
// Time complexity: O(n^1/4)
// Space complexity: O(1)
class Solution {
    public boolean isThree(int n) {
        int sqrt = (int)Math.sqrt(n);
        if (n <= 1) {
            return false;
        }
        if (sqrt * sqrt != n) {
            // If square of (square root of n) != n => can not satisfy 3 divisors condition
            return false;
        }

        // If sqrt*sqrt == n, the condition satisfies only if the sqrt is a prime number
        // So, to check if it's a prime number, we check all numbers less than sqrt of sqrt, then it should not is divisible by them.

        int sqrtOfSqrt = (int)Math.sqrt(sqrt);
        for (int i = 2; i <= sqrtOfSqrt; i++) {
            // Check if it's a prime.
            if (sqrt % i == 0) {
                // It has a divisor => not a prime => return false
                return false;
            }
        }
        return true;
    }
}

// Solution 1: A bit shorter, 
// Time complexity: O(n ^ 1/2)
// Space complexity: O(1)
class Solution {
    public boolean isThree(int n) {
        int sqrt = (int)Math.sqrt(n);
        if (n <= 1) {
            return false;
        }
        if (sqrt * sqrt != n) {
            return false;
        }
        for (int i = 2; i < sqrt; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
