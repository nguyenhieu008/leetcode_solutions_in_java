// https://leetcode.com/problems/rotate-image/description/

// Solution 2: Transpose then reverse
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // a: ===>
        // b: ===>
        // c: ===>

        // after transposed (flip symmetrically)
        // a b c
        // | | |
        // | | |
        // | | |
        // v v v

        // then reverse each row (flip horizontally)
        // c b a
        // | | |
        // | | |
        // | | |
        // v v v

        // Transpose the matrix first
        for (int row = 0; row < n; row++) {
            for (int col = row + 1; col < n; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
            }
        }

        // Reverse each row
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n / 2; col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[row][n - col - 1];
                matrix[row][n - col - 1] = temp;
            }
        }
    }
}

// Solution 1: Do the transformation from outer to inner. Swap the cells in the anti-clockwise order so we don't need to store the previous value when updating along the way.
// Time complexity: O(m * n)
// Space complexity: O(1);

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int top = 0, bottom = n - 1, left = 0, right = n - 1;

        while (top <= bottom) { // no need left <= right because both sides are the same
            for (int i = 0; left + i < right; i++) {
                int topLeftValue = matrix[top][left + i];

                matrix[top][left + i] = matrix[bottom - i][left];
                matrix[bottom - i][left] = matrix[bottom][right - i];
                matrix[bottom][right - i] = matrix[top + i][right];
                matrix[top + i][right] = topLeftValue;
            }
            top++; bottom--;
            left++; right--;
        }
    }
}
