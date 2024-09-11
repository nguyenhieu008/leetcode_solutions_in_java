// https://leetcode.com/problems/minimum-bit-flips-to-convert-number/description/?envType=daily-question&envId=2024-09-11


/* Solution: 
- The number of required bit flips is the number of differences at each bit index between 2 number.
- So, we make a bitwise xor 2 numbers, the result number will have 1s bits for each position that the bits of two numbers differ.
- After that, we count number of 1 bit in xor, by continuously check the first bit, then remove that bit.
- Time complexity: O(log(max(start, goal))
- Space complexity: O(1)

*/
class Solution {
    public int minBitFlips(int start, int goal) {
        int diff = start ^ goal;
        int count = 0;
        while (diff > 0) {
            if ((diff & 1) == 1) {
                count++;
            }
            diff >>= 1;
        }
        return count;
    }
}
