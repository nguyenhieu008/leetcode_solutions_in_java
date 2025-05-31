// https://leetcode.com/problems/snakes-and-ladders/description/

// Solution 1a: Same as solution 1, but a bit shorter.
// Mark visited for the destination cell of the snake/ladder right away => no need to if/else based on the value of a cell
// => We also check visited a cell based on the destination, not just based on the nextSquare.
// NOTICE: WE CAN USE 1 ARRAY FOR DISTANCE TO MARK VISITED AND SAVE THE DISTANCE AS WELL 
// => NO NEED LEVEL ORDER TRANVERSAL (1 MORE NESTED WHILE);
// Time complexity: O(n^2), n is the size of the edge of the board
// Space complexity: O(n^2);

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int maxSquare = n * n;

        boolean[] visited = new boolean[maxSquare + 1];
        visited[1] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int curSquare = q.poll();
                if (curSquare == maxSquare) {
                    return steps;
                }

                for (int nextSquare = curSquare + 1; nextSquare <= Math.min(curSquare + 6, maxSquare); nextSquare++) {
                    int nextValue = getSquareValue(board, nextSquare);
                    int dest = nextValue != -1 ? nextValue : nextSquare;

                    if (visited[dest]) {
                        continue;
                    }

                    q.offer(dest);
                    visited[dest] = true;
                }
            }
            steps++;
        }
        return -1;
    }

    private int getSquareValue(int[][] board, int square) {
        int n = board.length;
        int baseRow = (square - 1) / n;
        int baseCol = (square - 1) % n;
        int row = n - baseRow - 1;
        int col = 0;
        if (baseRow % 2 == 0) {
            col = baseCol;
        } else {
            col = n - baseCol - 1;
        }

        return board[row][col];
    }
}

// Solution 1: Should use level order traversal, the distance to the last cell is the level

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
