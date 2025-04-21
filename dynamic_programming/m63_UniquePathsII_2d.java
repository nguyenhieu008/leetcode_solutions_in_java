// https://leetcode.com/problems/unique-paths-ii/

// For each item, the number of path to reach [i][j] = numPath[i-1][j](same column) + numPath[i][j-1](same row)
// Beware that if the current item is obstacle, we return 0 anyway.
// Time complexity: O(m * n)
// Space complexity: O(n)

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) {
            // This is a very edge case. need to ask when interview whether the initial position of robot can be obstacle
            return 0;
        }
        int numPath[] = new int[n];
        numPath[0] = 1;
        
        // for first row, there is maximum 1 path from left to right
        for (int i = 1; i < n; i++) {
            numPath[i] = obstacleGrid[0][i] == 1 ? 0 : numPath[i-1];
        }

        for (int row = 1; row < m; row++) {
            // for first column, there is maximum 1 path from top to bottom
            numPath[0] = obstacleGrid[row][0] == 1 ? 0 : numPath[0];
            for (int col = 1; col < n; col++) {
                numPath[col] = obstacleGrid[row][col] == 1 ? 0 : (numPath[col-1] + numPath[col]);
            }
        }
        return numPath[n-1];
    }
}
