// https://leetcode.com/problems/game-of-life/description/

// Solution: Store states in cells and recover the second state after first round. Detail in comment;
// NOTICE: one nice apporach is that, we store the next dead/live status in the second bit. 
// (cur dead/live status is store in first bit already)
// Reference: https://leetcode.com/problems/game-of-life/solutions/73223/easiest-java-solution-with-explanation/

class Solution {
    private int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, 
                            {0, -1}, {0, 1},
                            {1, -1}, {1, 0}, {1, 1}};

    // Store states inside the cells:
    // 0: cur dead -> next dead
    // 1: cur live -> next live
    // 2: cur dead -> next live
    // 3: cur live -> next dead

    // Count cur lives if values = (1 or 3) (cur live) => (1 or 3) % 2 == 1

    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                int lives = countLives(board, row, col);
                if (board[row][col] == 0) {
                    if (lives == 3) {
                        board[row][col] = 2; // from dead to live
                    }
                } else {
                    // board[row][col] == 1;
                    if (lives < 2 || lives > 3) {
                        board[row][col] = 3; // from live to dead;
                    }
                }
            }
        }     
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] > 1) {
                    // 3 -> 0 and 2 -> 1
                    board[row][col] = 3 - board[row][col];
                }
            }
        }   
    }
    private int countLives(int[][] board, int row, int col) {
        int lives = 0;
        for (int[] d : dirs) {
            int nRow = row + d[0], nCol = col + d[1];
            if (nRow >= 0 && nRow < board.length && nCol >= 0 && nCol < board[0].length && board[nRow][nCol] % 2 == 1) {
                // is live if value == (1 or 3) % 2 == 1
                lives++;
            }
        }
        return lives;
    }
}
