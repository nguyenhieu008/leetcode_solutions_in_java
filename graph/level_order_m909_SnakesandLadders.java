// https://leetcode.com/problems/snakes-and-ladders/description/
// Should use level order traversal, the distance to the last cell is the level

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];

        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1] = true;

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            steps++;

            for (int i = 0; i < size; i++) {
                int square = q.poll();

                for (int next = square + 1; next <= square + 6 && next <= n * n; next++) {
                    if (next == n * n) {
                        return steps;
                    }

                    int[] nextCoordinate = squareToIdx(next, n);
                    int nextRow = nextCoordinate[0], nextCol = nextCoordinate[1];
                    if (visited[next]) continue;

                    visited[next] = true;
                    if (board[nextRow][nextCol] != -1) {
                        int jumpedSquare = board[nextRow][nextCol];
                        if (jumpedSquare == n * n) {
                            return steps;
                        }
                        q.offer(jumpedSquare);
                    } else {
                        q.offer(next);
                    }
                }
            }

        }
        return -1; 
    }

    private int[] squareToIdx(int square, int n) {
        int div = (square - 1) / n;
        int row = (n - 1) - div;

        int col = (square - 1) % n;
        if (div % 2 == 1) {
            col = (n - 1) - col;
        }
        return new int[]{row, col};
    }
}
