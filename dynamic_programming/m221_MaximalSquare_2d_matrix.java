// https://leetcode.com/problems/maximal-square/

// Given dp[i][j] is the max length of square where [i,j] is bottom right.
// If a[i][i] == 1 => dp[i][j] = min length of 3 adjacent squares + 1 
// Time complexity: O(m*n);
// Space complexity: O(m*n);

class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] edgeLength = new int[m+1][n+1];
        int maxEdge = 0;

        for (int row = 1; row <= m; row++) {
            for (int col = 1; col <= n; col++) {
                if (matrix[row-1][col-1] == '0') continue;
                    
                edgeLength[row][col] += min3(edgeLength[row-1][col-1], edgeLength[row-1][col], edgeLength[row][col-1]) + 1;
                maxEdge = Math.max(maxEdge, edgeLength[row][col]);
            }
        }
        
        return maxEdge * maxEdge;
    }

    private int min3(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
