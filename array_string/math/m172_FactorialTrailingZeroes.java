// https://leetcode.com/problems/factorial-trailing-zeroes/description/

// Solution 2: based on solution 1, Because it's number of 5s, we have:
//  - if i % 5 == 0 => we have 1 5s for i => we calculate all possible i by (n / 5) then add to res
//  - if i % 25 == 0 => we have 2 5s for i, but we already add 1 before => we add one more => we use (n / 25) then add to res
//  - Continue with other powers of 5 until it's greater than n.
// Time complexity: O(logn)

class Solution {
    public int trailingZeroes(int n) {
        int res = 0;
        
        for (int i = 1; (int)Math.pow(5, i) <= n; i++) {
            int multipleOf5 = (int)Math.pow(5, i);
            res += n / multipleOf5;
        }
        return res;
    }
}

// Number of 0s will be the number of 5s, e.g: in 25, we can divide into 2 5s, 5 * 5 = 25
// Time complexity: O(n)
class Solution {
    public int trailingZeroes(int n) {
        int res = 0;
        for (int i = 5; i <= n; i += 5) {
            int numOf5 = 0;
            int temp = i;
            while (temp % 5 == 0) {
                temp /= 5;
                numOf5++;
            }
            res += numOf5;
        }
        return res;
    }
}
