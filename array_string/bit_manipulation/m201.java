// Solution 2: self-done. Examine the largest bit:
//   e.g: left = 001111, right = 010000 => AND results in 0, 
// because when we increase to next bit, all less significant bits are set to 0 => if We increase largest bit by 0, the result will be 0
// and because the AND will always process a smaller or equal to the smaller int => 0 will be the result in that case
// So, we are finding the COMMON LARGEST BITS of 2 integers.
// So we go down from largest bit (31), if the bits between left & right are the same => it will be added to the result (0 or 1 both ok)
// For the first bit that they differ => we break and return result right away.
// Time complexity: O(1)
// Space complexity: O(1)

class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            int mask = 1 << i;
            if ((left & mask) == (right & mask)) {
                res |= left & mask;
            } else {
                break;
            }
        }

        return res;
    }
}

// Solution 1: TLE. Do as the problem description. Need to check i >= left to avoid the test case where right == Integer.MAX_VALUE
class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        int res = left;
        int i = left;
        while (res > 0 && i >= left && i <= right ) {
            res &= i;
            i++;
        }

        return res;
    }
}

// Solution 3: reference: https://leetcode.com/problems/bitwise-and-of-numbers-range/solutions/593317/simple-3-line-java-solution-faster-than-100/
/* The trick here is that :
Bitwise-AND of any two numbers will always produce a number less than or equal to the smaller number.
*/
class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        while (right > left) {
            // Do the AND as required, but save it to right, 
            // because other ANDs of number between [old right, new right] will finally need to AND with [new right (the smaller one)] and produce smaller ones.
            // So we skip it fast to the [new right]
            right &= (right - 1); 
        }

        return right & left;
    }
}

// Solution 4: Same as solution 2, but copied from the others: 
// reference: https://leetcode.com/problems/bitwise-and-of-numbers-range/submissions/1604517943/
/* Common Prefix Identification:

The function iteratively right-shifts both left and right until they become equal, identifying the common prefix of their binary representations.
Counting Shifts:

It counts the number of right-shifts performed, storing the count in the variable cnt.
Bitwise AND Calculation:

After finding the common prefix, it reconstructs the bitwise AND result by left-shifting the common prefix by cnt bits.

*/
class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        int cnt = 0;
        while (left != right) {
            left >>= 1;
            right >>= 1;
            cnt++;
        }
        return (left << cnt);
    }
}
