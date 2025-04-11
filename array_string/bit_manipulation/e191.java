/*Solution 2: Reference https://leetcode.com/problems/number-of-1-bits/solutions/55255/c-solution-n-n-1/

n & (n - 1) drops the lowest set bit. It's a neat little bit trick.

Let's use n = 00101100 as an example. This binary representation has three 1s.

If n = 00101100, then n - 1 = 00101011, so n & (n - 1) = 00101100 & 00101011 = 00101000. Count = 1.

If n = 00101000, then n - 1 = 00100111, so n & (n - 1) = 00101000 & 00100111 = 00100000. Count = 2.

If n = 00100000, then n - 1 = 00011111, so n & (n - 1) = 00100000 & 00011111 = 00000000. Count = 3.

n is now zero, so the while loop ends, and the final count (the numbers of set bits) is returned.
*/

class Solution {
    public int hammingWeight(int n) {
        int res = 0;
        while (n > 0) {
            n &= (n - 1);
            res++;
        }
        return res;
    }
}

// Solution 1: Bit shift until 0

class Solution {
    public int hammingWeight(int n) {
        int res = 0;
        while (n > 0) {
            if ((n & 1) > 0) {
                res++;
            }
            n >>= 1;
        }
        return res;
    }
}
