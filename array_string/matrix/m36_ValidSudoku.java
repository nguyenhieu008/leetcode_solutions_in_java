// https://leetcode.com/problems/valid-sudoku/description/

// Solution: use boolean array to check a number is occupied for each row, column and square
// Notice: Another way to do is to use hash set to store whether a value has appeared.

class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] squares = new boolean[9][9];

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int v = board[row][col] - '1';
                if (v < 0 || v >= 9) continue;

                int sIdx = squareIdx(row, col);
                if (rows[row][v] || cols[col][v] || squares[sIdx][v]) {
                    return false;
                }
                rows[row][v] = true;
                cols[col][v] = true;
                squares[sIdx][v] = true;
            }
        }
        return true;
    }

    private int squareIdx(int row, int col) {
        return (row / 3) * 3 + (col / 3);
    }
}
