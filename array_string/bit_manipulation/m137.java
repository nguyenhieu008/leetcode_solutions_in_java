// https://leetcode.com/problems/single-number-ii/description/

// Solution 1: 
// We examine a specific bit = 1, we have this truth table:
//   Times of this bit == 1      0        1        2        3      4        5        6
//   This bit in ones:           0        1        0        0      1        0        0
//   This bit in twos:           0        0        1        0      0        1        0
// If this bit == 0, it does not change anything as ((bit ^ 0) = bit)
// So we have the below logic, for more generay way, we should apply digital logic (gates)
// https://leetcode.com/problems/single-number-ii/solutions/43296/an-general-way-to-handle-all-this-sort-of-questions/

class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        int ones = 0; // Store bits that appear once
        int twos = 0; // Store bits that appear twice
        for (int v : nums) {
            ones ^= v & ~twos;
            twos ^= v & ~ones;
        }
        return ones;
    }
}

// Solution 2: A generic way to solve this kind of problem, although it's not optimal:
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int v : nums) {
                sum += (v >> i) & 1;
            }
            if (sum % 3 != 0) {
                res |= 1 << i;
            }
        }
        return res;
    }
}

// Solution 3: Sum all 
class Solution {
    public int singleNumber(int[] nums) {
        HashSet<Integer> set = new HashSet(Arrays.stream(nums).boxed().collect(Collectors.toSet()));
        long sumAll = 0, sumSet = 0;
        for (int v : nums) {
            sumAll += (long)v;
        }
        for (Integer v : set) {
            sumSet += (long)v;
        }
        return (int)(((sumSet * 3) - sumAll) / 2);
    }
}
