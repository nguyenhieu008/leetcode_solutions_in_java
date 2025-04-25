// https://leetcode.com/problems/number-of-islands/description/

// Solution 2: same as solution 1, use directions for shorter code

class Solution {
    public int numIslands(char[][] grid) {
        // m, n already > 0
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        int res = 0;
        for (int row = 0; row < m; row ++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == '1' && !visited[row][col]) {
                    res++;
                    dfs(grid, visited, row, col, directions); 
                }
            }
        }

        return res;
    }

    private void dfs(char[][] grid, boolean[][] visited, int row, int col, int[][] directions) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != '1' || visited[row][col]) {
            return ;
        }
        visited[row][col] = true;

        for (int[] d : directions) {
            dfs(grid, visited, row + d[0], col + d[1], directions);
        }
    }
}

// Solution 1: for every node, find the connected component using either dfs or bfs, then mark visited nodes.
class Solution {
    public int numIslands(char[][] grid) {
        // m, n already > 0
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        int res = 0;
        for (int row = 0; row < m; row ++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == '1' && !visited[row][col]) {
                    res++;
                    dfs(grid, visited, row, col); 
                }
            }
        }

        return res;
    }

    private void dfs(char[][] grid, boolean[][] visited, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != '1' || visited[row][col]) {
            return ;
        }
        visited[row][col] = true;
        dfs(grid, visited, row - 1, col);
        dfs(grid, visited, row + 1, col);
        dfs(grid, visited, row, col - 1);
        dfs(grid, visited, row, col + 1);
    }
}
