// https://leetcode.com/problems/knight-probability-in-chessboard/description/

// Solution 2a: Top-down
class Solution {
    private int[][] dirs = {{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
    public double knightProbability(int n, int k, int r, int c) {
        /*
            A state can be define as (moves, row, col)
            dp[move][row][col]: probability of the knight land at [row, col] when there are "move" moves left
        */

        double[][][] dp = new double[k+1][n][n];
        return calculate(n, k, r, c, dp);
    }

    // k how many steps left
    private double calculate(int n, int k, int row, int col, double[][][] dp) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            return 0L;
        }
        if (k == 0) {
            return 1L;
        }
        if (dp[k][row][col] > 0) {
            return dp[k][row][col];
        }
        double res = 0;
        for (int[] d : dirs) {
            res += 0.125 * calculate(n, k - 1, row + d[0], col + d[1], dp);
        }
        dp[k][row][col] = res;
        return res;
    }

}

// Solution 2: Top-down
class Solution {
    private int[][] dirs = {{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}};
    public double knightProbability(int n, int k, int r, int c) {
        /*
            A state can be define as (moves, row, col)
            Starting from [row, col], calling recusively with next step and next [nextRow, nextCol].
            If it still in the board, continue to go. 
            If step == k, then with that path, after k step, knight still in the board => return 1 as possibility
            dp[move][row][col]: probability of the knight land at [row, col] when there are (k - move) left.
        */

        double[][][] dp = new double[k][n][n];
        return calculate(n, 0, k, r, c, dp);
    }

    // k how many steps left
    private double calculate(int n, int step, int k, int row, int col, double[][][] dp) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            return 0L;
        }
        if (step == k) {
            return 1L;
        }
        if (dp[step][row][col] > 0) {
            return dp[step][row][col];
        }

        double res = 0;
        for (int[] d : dirs) {
            res += 0.125 * calculate(n, step + 1, k, row + d[0], col + d[1], dp);
        }
        dp[step][row][col] = res;
        return res;
    }

}

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
