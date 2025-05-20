// https://leetcode.com/problems/find-the-maximum-factor-score-of-array/description/

// Solution 2: GCD, LCM with left and right preprocessing
// gcdLeft, gcdRight, lcmLeft, lcmRight store the gcd, lcm of left and right items, excluding the current one.
// Key knowledge here is that:
//   - GCD of array = gcd(cur gcd, nums[i]). Init at 0, gcd(0, v) = v
//   - LCM of array = lcm(cur lcm, nums[i]). Init at 1, lcm(1, v) = v
// Time complexity: O(n * log(max value))
// Space complexity: O(n)

class Solution {
    public long maxScore(int[] nums) {
        int n = nums.length;
        long[] gcdLeft = new long[n];
        long[] gcdRight = new long[n];
        long[] lcmLeft = new long[n];
        long[] lcmRight = new long[n];
    
        gcdLeft[0] = 0;
        lcmLeft[0] = 1;
        for (int i = 1; i < n; i++) {
            gcdLeft[i] = gcd(gcdLeft[i-1], nums[i-1]);
            lcmLeft[i] = lcm(lcmLeft[i-1], nums[i-1]);
        }

        gcdRight[n-1] = 0;
        lcmRight[n-1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            gcdRight[i] = gcd(gcdRight[i+1], nums[i+1]);
            lcmRight[i] = lcm(lcmRight[i+1], nums[i+1]);
        }

        // Initially set to total, do not skip any item
        long res = gcd(gcdLeft[n - 1], nums[n-1]) * lcm(lcmLeft[n - 1], nums[n - 1]);

        for (int i = 0; i < n; i++) {
            long cur = gcd(gcdLeft[i], gcdRight[i]) * lcm(lcmLeft[i], lcmRight[i]);
            res = Math.max(res, cur);
        }
        
        return res;
    }

    private long lcm(long a, long b) {
        long gcd = gcd(a, b);
        return a * b / gcd;
    }

    private long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

// Solution 1: Just calculate the gcd and lcm of all possible cases, try skip = -1 means do not skip anything
// Time complexity: O(n^2 * log(max value))
// Space complexity: O(1)

class Solution {
    public long maxScore(int[] nums) {
        int n = nums.length;
        long res = 0;
        for (int skip = -1; skip < n; skip++) {
            long curGcd = 0, curLcm = 1;

            for (int i = 0; i < n; i++) {
                if (i == skip) {
                    continue;
                }
                curGcd = gcd(curGcd, nums[i]);
                curLcm = lcm(curLcm, nums[i]);
            }
            res = Math.max(res, curGcd * curLcm);
        }
        
        return res;
    }

    private long lcm(long a, long b) {
        long gcd = gcd(a, b);
        return a * b / gcd;
    }

    private long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
