// https://leetcode.com/problems/ugly-number-iii/description/

// Solution: Use number theory, count of number <= n, and divisibe by a, is (n / a). 
// => Number of ugly number is union of 3 sets, it's inclusion-exclusion principle. 
// => Num of ugly numbers is: (divisible by a) + (d by b) + (d by c) - (d by lcm(a, b)) - (d by lcm(a,c)) - (d by lcm(b,c)) + (d by lcm(a, b,c))
// given that, n % lcm(a,b) == 0 => n % a == 0 and n % b == 0 => n is intersection between 2 sets.
// So, for every value v, we can count how many ugly number <= v. We use binary search, so the result will be the MINIMUM SATISFIED NUMBER => it must be the nth ugly number.

// Time complexity: O(log(max_int)*log(min(a,b,c)). We can reduce to log(max_int) if we calculate the lcm of them once. 
// Space complexity: O(1)
public class Solution {
    int MAX_ANS = (int) 2e9; // 2*10^9
    long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    private boolean greaterEqualThanN(long v, long n, long a, long b, long c) {
        long numUgly = (v / a + v / b + v / c) 
                - (v / lcm(a, b)) 
                - (v / lcm(a, c))
                - (v / lcm(b, c))
                + (v / lcm(a, lcm(b, c)));
        return numUgly >= n;
    }

    public int nthUglyNumber(int n, int a, int b, int c) {
        int l = 1, r = Integer.MAX_VALUE;

        while (r - l > 0) {
            int mid = l + (r - l) / 2;
            if (greaterEqualThanN(mid, n, a, b, c)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    
}
