// https://leetcode.com/problems/knight-probability-in-chessboard/description/

// Solution 1a: Same as solution 1 but use 2 matrix then swap
class Solution {
    public double knightProbability(int n, int k, int r, int c) {
        /*
            A state can be define as (moves, row, col)
            dp[move][row][col]: probability of the knight land at [row, col] after "move" moves
            => dp[m][r][c] = sum of 8 possible previous cells (dp[m-1][r-2][c-1]), and so on

            result will be sum of all dp of cells in the board after k moves.
            Because a state only depends on in previous state, we can optimize memory using 2-d array only
        */

        int[][] dirs = {{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
        double[][] prevDp = new double[n][n];
        double[][] dp = new double[n][n];
        prevDp[r][c] = 1L;

        for (int time = 0; time < k; time++) {
            for (int row = 0; row < n; row ++) {
                for (int col = 0; col < n; col ++) {
                    dp[row][col] = 0L; // reset state

                    for (int[] d : dirs) {
                        int prevRow = row + d[0], prevCol = col + d[1];
                        if (prevRow >= 0 && prevRow < n && prevCol >= 0 && prevCol < n) {
                            dp[row][col] += prevDp[prevRow][prevCol] / 8L;
                        }
                    }
                }
            }

            // swap so prevDp stores current dp, then current dp can be use for next iteration
            double[][] temp = prevDp;
            prevDp = dp;
            dp = temp;
        }

        double res = 0L;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col ++) {
                res += prevDp[row][col];
            }
        }
        return res;
    }
}

// Solution 1: Detail in comment:
// Time complexity: O(k * n ^ 2 * 8) = O(k * n ^ 2);
// Space complexity: O(n ^ 2)
class Solution {
    public double knightProbability(int n, int k, int r, int c) {
        /*
            A state can be define as (moves, row, col)
            dp[move][row][col]: probability of the knight land at [row, col] after "move" moves
            => dp[m][r][c] = sum of 8 possible previous cells (dp[m-1][r-2][c-1]), and so on

            result will be sum of all dp of cells in the board after k moves.
            Because a state only depends on in previous state, we can optimize memory using 2-d array only
        */

        int[][] dirs = {{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
        double[][] dp = new double[n][n];
        dp[r][c] = 1;

        for (int time = 0; time < k; time++) {
            double[][] nextDp = new double[n][n];

            for (int row = 0; row < n; row ++) {
                for (int col = 0; col < n; col ++) {
                    for (int[] d : dirs) {
                        int prevRow = row + d[0], prevCol = col + d[1];
                        if (prevRow >= 0 && prevRow < n && prevCol >= 0 && prevCol < n) {
                            nextDp[row][col] += dp[prevRow][prevCol] / 8;
                        }
                    }
                }
            }

            dp = nextDp;
        }

        double res = 0L;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col ++) {
                res += dp[row][col];
            }
        }
        return res;
    }
}
