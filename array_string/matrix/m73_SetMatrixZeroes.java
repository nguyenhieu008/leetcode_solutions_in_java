// https://leetcode.com/problems/set-matrix-zeroes/description/

// Solution 2: When a cell is 0, we mark the whole row and column to be changed to 0 by setting the first cell of the row and column = 0.
// So the cell [0][0] is interfere, we use this for first row and make another separate variable to denote the first column.
// When fill 0 to cells, we need to fill the first row/column last, so it won't affect the other cells in the same row/column.
// Time complexity: O(m * n)
// Space complexity: O(1)

class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int col0 = 1;
        for (int row = 0; row < m; row++) {
            // to add first col
            if (matrix[row][0] == 0) col0 = 0;

            for (int col = 1; col < n; col++) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }

        for (int row = m - 1; row >= 0; row--) {
            for (int col = 1; col < n; col++) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
            // This block needed to put after, so it wont' affect the whole row 
            if (col0 == 0) {
                matrix[row][0] = 0;
            }
        }
    }
}

// Solution 1: use two boolean arrays to mark the rows/columns need to be tranformed to 0.
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] rows = new boolean[m], cols = new boolean[n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (matrix[row][col] == 0) {
                    rows[row] = true;
                    cols[col] = true;
                }
            }
        }

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (rows[row] || cols[col]) {
                    matrix[row][col] = 0;
                }
            }
        }
    }
}
