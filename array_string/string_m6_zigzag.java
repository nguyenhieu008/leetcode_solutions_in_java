// https://leetcode.com/problems/zigzag-conversion/description/

// Use "numRows" string builders for rows. Fill them up when iterating through the string, then concat together.
class Solution {
    public String convert(String s, int numRows) {
        //  numsRows = 4 => k = 3
        //  0           6           12
        //  1       5   7       11  13
        //  2   4       8   10
        //  3           9

        // 0, 6, 12, 1, 5, 7, 11, 13 => i
        // 0, 1, 2,  3, 4, 5,  6, 7 => j

        // i: index in original
        // j: index in the result

        // if (i % 2k == 0) => j = i / 2k
        // mod = i % 2k
        //      if (mod < k) => j = i / 2k + mod
        //      else if (mod == k) => j =  => NOT WORK

        // We try to make numRows buffer string. As we iterate throught original string, 
        // we choose appropritate buffer string to add to.

        int n = s.length();
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int j = 0; j < numRows; j++) {
            sb[j] = new StringBuilder();
        }


        /* Tested and worked:
        int direction = 1;
        for (int i = 0, j = 0; i < n; i++, j = (j + direction) % numRows) {
            sb[j].append(s.charAt(i));

            if (j <= 0) {
                direction = 1;
            } else if (j >= numRows - 1) {
                direction = -1;
            }
        }
        */
        int direction = 1;
        int j = 0;
        for (int i = 0; i < n; i++) {
            sb[j].append(s.charAt(i));

            j = (j + direction) % numRows;
            if (j <= 0) {
                direction = 1;
            } else if (j >= numRows - 1) {
                direction = -1;
            }
        }

        StringBuilder res = new StringBuilder();
        for (int k = 0; k < numRows; k++) {
            res.append(sb[k]);
        }
        return res.toString();
    }
}
