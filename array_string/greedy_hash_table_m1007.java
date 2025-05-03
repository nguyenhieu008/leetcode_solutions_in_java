// https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/description/

// Solution 2: Count number of values. Then calculate the result base on:
// - For example, value 1, if the number of 1s in A + number of 1s in B - number of 1s in both A-B (no need to count => subtract from total) == n
// => we can make the whole array to equal row. 
// The result will be min of countA and countB, minus the same, because we do not need to rotate them.
// THIS CAN ALSO BE UNDERSTOOD THAT WE ARE UTILIZING HASH TABLE, AND DETERMINE RESULT BASED ON HASH TABLE VALUES
// Reference: https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/solutions/901271/c-java-python-one-pass-clean-concise-strictly-o-n/


class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        // Instead of compare dominoes together, we compare them to absolute value [1..6]
        int n = tops.length;
        int[] countA = new int[7], countB = new int[7], countSame = new int[7];

        for (int i = 0; i < n; i++) {
            int a = tops[i], b = bottoms[i];
            countA[a]++;
            countB[b]++;
            if (a == b) {
                countSame[a]++;
            }
        }

        for (int i = 1; i <= 6; i++) {
            if (countA[i] + countB[i] - countSame[i] == n) {
                return Math.min(countA[i], countB[i]) - countSame[i];
            }
        }
        return -1;
    }


}

// Solution 1: Count all possible cases. Make a functions to count number of rotations to make all a items the same.
// Then try with 4 cases:
//   - All top similar to tops[0];
//   - All top similar to bottoms[0]
//   - All bottom similar to tops[0]
//   - All bottom similar to bottoms[0]
// Get min of them. 
// NOTICE: Beware of implement the for cleanly, try not to use continue to make it complicated.

class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        // make all tops same with tops[0]. Count number of rotations. 
        // return Min(rotations, n - rotations) => WRONG ASSUMPTION
        // e.g: top = [1, 1, 3, 3, 3], bottom = [2, 1, 1, 1, 1]
        // => rotations = 3, but min = 1, instead of (5 - 3 = 2)
        // 

        // Otherwise, make all bottoms same with bottoms[0], and so on

        int res = Integer.MAX_VALUE;

        res = Math.min(res, rotate(tops, bottoms, tops[0]));
        res = Math.min(res, rotate(bottoms, tops, tops[0]));

        res = Math.min(res, rotate(tops, bottoms, bottoms[0]));
        res = Math.min(res, rotate(bottoms, tops, bottoms[0]));

        return res == Integer.MAX_VALUE? -1 : res;        
    }

    // count rotations to make a[i] == target for all i
    private int rotate(int[] a, int[] b, int target) {
        int rotations = 0;
        int i = 0;
        for (i = 0; i < a.length; i++) {
            if (a[i] != target) {
                if (b[i] != target) {
                    return Integer.MAX_VALUE;
                } else {
                    rotations++;
                }
            }
        }
        
        return rotations;
    }
}
