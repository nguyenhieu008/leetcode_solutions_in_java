// https://leetcode.com/problems/spiral-matrix/description/

// Solution 2: move the current cell 1-by-1, we must get the proper dRow and dCols by the directions and curDir - which is the index in the directions array.
// After we reach the end of a direction, or a visited cell, we change the direction by add 1 to the curDir and % 4.
// Loop until we add full (m * n) items to the result list.
// Time complexity: O(m * n)
// Space complexity: O(m * n) for visited matrix. We can update the matrix inplace by putting some out-of-range value to save memory.

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int curDir = 0;
        int row = 0, col = 0;

        List<Integer> res = new ArrayList<>();
        while (res.size() < m * n) { // to check
            res.add(matrix[row][col]);
            visited[row][col] = true;

            int dRow = directions[curDir][0], dCol = directions[curDir][1];
            int nRow = row + dRow, nCol = col + dCol;
            
            if (nRow < 0 || nRow >= m || nCol < 0 || nCol >= n || visited[nRow][nCol]) {
                curDir = (curDir + 1) % directions.length; // 4 dirs

                dRow = directions[curDir][0];
                dCol = directions[curDir][1]; // change direction
                nRow = row + dRow; 
                nCol = col + dCol;
            }

            row = nRow;
            col = nCol;
        }
        return res;
    }
}

// Solution 1: Each turn, we print the 4 edges in correct order. After 1 edge, we shrink the other side by one. 
// For each edge, we also need to check both conditions (start-end/row-col) to valid to be able to print, e.g: there maybe case startRow > endRow already, but startCol still < endCol.
// Time complexity: O(m * n)
// Space complexity: O(1)

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int startRow = 0, endRow = m - 1, startCol = 0, endCol = n - 1;

        List<Integer> res = new ArrayList<>();
        while (startRow <= endRow && startCol <= endCol) {
            for (int i = startCol; i <= endCol && startRow <= endRow; i++) {
                res.add(matrix[startRow][i]);
            }
            startRow++;

            for (int i = startRow; i <= endRow && startCol <= endCol; i++) {
                res.add(matrix[i][endCol]);
            }
            endCol--;

            for (int i = endCol; i >= startCol && startRow <= endRow; i--) {
                res.add(matrix[endRow][i]);
            }
            endRow--;
            
            for (int i = endRow; i >= startRow && startCol <= endCol; i--) {
                res.add(matrix[i][startCol]);
            }
            startCol++;
        }
        return res;
    }
}
