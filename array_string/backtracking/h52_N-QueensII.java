// https://leetcode.com/problems/n-queens-ii/

class Solution {
    public int totalNQueens(int n) {
        boolean[] column = new boolean[n];
        boolean[] diagonalMinus = new boolean[2 * n];
        boolean[] diagonalPlus = new boolean[2 * n];
        
        return backtrack(n, 0, column, diagonalMinus, diagonalPlus);
    }

    private int backtrack(int n, int row, boolean[] column, boolean[] diagonalMinus, boolean[] diagonalPlus) {
        if (row == n) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            int minusIdx = row - i + n;
            int plusIdx = row + i;
            if (column[i] || diagonalMinus[minusIdx] || diagonalPlus[plusIdx]) continue;

            column[i] = true;
            diagonalMinus[minusIdx] = true;
            diagonalPlus[plusIdx] = true;
            
            res += backtrack(n, row + 1, column, diagonalMinus, diagonalPlus);

            column[i] = false;
            diagonalMinus[minusIdx] = false;
            diagonalPlus[plusIdx] = false;
        }
        return res;
    }

}
