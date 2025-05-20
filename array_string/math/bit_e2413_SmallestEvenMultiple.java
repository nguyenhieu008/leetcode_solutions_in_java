// https://leetcode.com/problems/smallest-even-multiple/description/

// Solution 1:
class Solution {
    public int smallestEvenMultiple(int n) {
        if (n % 2 == 0) {
            return n;
        }
        return n * 2;
    }
}

// Solution 2: Bit manipulation
class Solution {
    public int smallestEvenMultiple(int n) {
        if ((n & 1) == 1) {
            return n << 1;
        }
        return n;
    }
}
