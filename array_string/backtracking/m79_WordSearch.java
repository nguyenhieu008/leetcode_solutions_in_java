// https://leetcode.com/problems/word-search/

class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        char first = word.charAt(0);
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == first) {
                    if (backtrack(board, visited, i, j, word, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean backtrack(char[][] board, boolean[][] visited, int i, int j, String word, int idx) {
        if (idx == word.length()) {
            return true;
        }
        char c = word.charAt(idx);
        if (i < 0 || i >= board.length 
                || j < 0 || j >= board[0].length 
                || board[i][j] != word.charAt(idx) || visited[i][j]) {
            return false;
        }
        
        visited[i][j] = true;
        if (backtrack(board, visited, i - 1, j, word, idx + 1)) return true;
        if (backtrack(board, visited, i + 1, j, word, idx + 1)) return true;
        if (backtrack(board, visited, i, j - 1, word, idx + 1)) return true;
        if (backtrack(board, visited, i, j + 1, word, idx + 1)) return true;
        visited[i][j] = false;
        return false;
    }
}
