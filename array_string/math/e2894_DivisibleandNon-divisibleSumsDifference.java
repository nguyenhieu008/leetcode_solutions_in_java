// https://leetcode.com/problems/divisible-and-non-divisible-sums-difference/description/

// Solution: Brute-force approach is easy, try to use math. Detail in comment.

class Solution {
    public int differenceOfSums(int n, int m) {
        // sum1 of [1, n] = n * (n + 1) / 2
        // sum of mutiple of m in [1, n] is:
        //  - there are: k = (n / m) number, from 1 -> k. 
        //  => sum2 = m * 1 + m * 2 + ... + m * k
        //  - sum2 = m * sum([1, k]) = m * (k * (k + 1) / 2)
        // => sum3 = sum of [1,n] except multiple of m
        //         = sum1 - sum2
        //         = ((n * (n + 1)) - (m * k * (k + 1))) / 2
        // res = sum3 - sum2 = sum1 - 2 * sum2

        int k = n / m;
        int sum1 = n * (n + 1) / 2;
        int sum2 = m * k * (k + 1) / 2;
        return sum1 - 2 * sum2;
        
    }
}
