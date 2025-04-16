// https://leetcode.com/problems/sqrtx/description/

// Solution 1: binary search. Notice that we need to find the greatest one that satify some condition => try to use r
// Time complexity: O(logn)

class Solution {
    public int mySqrt(int x) {
        long l = 0, r = x;
        while (r - l >= 0) {
            long mid = l + (r - l) / 2;
            if (mid * mid > x) {
                // resulted r will be the greatest one which is <= sqrt(x)
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int)r;
    }
}

// Solution 2: Newton method. Just for reference.
// Time complexity: O(logn);
class Solution {
    public int mySqrt(int x) {
        if (x == 0) return 0;
        long i = x;
        while(i > x / i)  
            i = (i + x / i) / 2;	    	
        return (int)i;
    }
}
