// https://leetcode.com/problems/surrounded-regions/description/

// Solution 2: PLEASE READ THE PROBLEM CAREFULLY. It just need to replace the 'O' that can be surrounded, no need to list the connected components => no need to dfs/bfs from every 'O'
// We just need to find/mark which 'O' that cannot be surrounded, then it's all good. So which one cannot be surrounded?
// The the edge 'O's => Just dfs/bfs from edge 'O' and mark those 'O's connected to them as visited. => the unvisited 'O's inside the board will be replaced by 'X'
// Time complexity: O(m * n)
// Space complexity: O(m * n) for visited.

class Solution {
    private int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public void solve(char[][] board) {
        // m, n > 0
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O' && !visited[i][0]) {
                bfs(board, visited, i, 0);
            }
            if (board[i][n-1] == 'O' && !visited[i][n-1]) {
                bfs(board, visited, i, n - 1);
            }
        }

        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O' && !visited[0][j]) {
                bfs(board, visited, 0, j);
            }
            if (board[m-1][j] == 'O' && !visited[m-1][j]) {
                bfs(board, visited, m - 1, j);
            }
        }

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == 'O' && !visited[row][col]) {
                    // If the 'O' connected to edge => it's already visited
                    // => If not visited, it's an 'O' that can be surround
                    board[row][col] = 'X';
                }
            }
        }
    }

    private void bfs(char[][] board, boolean[][] visited, int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            int[] coordinate = queue.poll();
            int x = coordinate[0], y = coordinate[1];

            for (int[] d : directions) {
                int nextX = x + d[0], nextY = y + d[1];
                if (valid(board, nextX, nextY) && board[nextX][nextY] == 'O' && !visited[nextX][nextY]) {
                    queue.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
        }
    }

    private boolean valid(char[][] board, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board[0].length;
    }
}

// Solution 1: use normal DFS, if it reaches some edge 'O', return false indicates this region cannot be surrounded.
// Otherwise, it can be surrounded so push them all to a stack and proceed later.
// Time complexity: O(m * n)
// Space complexity: O(m * n) for visited

class Solution {
    private int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public void solve(char[][] board) {
        // m, n > 0
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                // If visited or X cell, continue
                if (visited[row][col] || board[row][col] == 'X') {
                    continue;
                }

                // Stack to store the surrounded region.
                Stack<int[]> stack = new Stack<>();
                if (dfs(board, visited, row, col, stack)) {
                    // if return true => capture region
                    while (!stack.isEmpty()) {
                        int[] coordinate = stack.pop();
                        board[coordinate[0]][coordinate[1]] = 'X';
                    }
                }
                // if not, do not capture
            }
        }
    }

    private boolean dfs(char[][] board, boolean[][] visited, int row, int col, Stack<int[]> connected) {
        if (onEdge(board, row, col)) {
            if (board[row][col] == 'X') {
                return true;
            }
            visited[row][col] = true;
            // connected.push(new int[]{row, col}); no need
            return false;
        }

        if (visited[row][col] || board[row][col] == 'X') {
            return true;
        }

        visited[row][col] = true;
        connected.push(new int[]{row, col});
        boolean res = true;
        
        for (int[] d : directions) {
            int nextRow = row + d[0];
            int nextCol = col + d[1];
            res = dfs(board, visited, nextRow, nextCol, connected) & res;
        }

        return res;
    }

    private boolean onEdge(char[][] board, int row, int col) {
        return row == 0 || row == board.length - 1 || col == 0 || col == board[0].length - 1;
    }
}
