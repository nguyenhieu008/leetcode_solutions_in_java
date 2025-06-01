// https://leetcode.com/problems/minimum-absolute-difference-in-sliding-submatrix/description/

// Solution: Simply loop through every items and calculate absoluate difference for each position.
// The key thing here is to collect all the items in the window, and sort them to easily calculate the absolute difference.

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int resM = m - k + 1, resN = n - k + 1;
        int [][] res = new int [resM][resN];
        
        for (int row = 0; row < resM; row++) {
            for (int col = 0; col < resN; col++) {
                res[row][col] = absDiff(grid, row, col, k);
            }
        }
        return res;
    }

    private int absDiff(int[][] grid, int top, int left, int k) {
        if (k == 1) {
            return 0;
        }
        
        int size = k * k;
        int bottom = top + k, right = left + k;
        int[] items = new int[size];
        int curIdx = 0;
        for (int row = top; row < bottom; row++) {
            for (int col = left; col < right; col++) {
                items[curIdx] = grid[row][col];
                curIdx++;
            }
        }
        Arrays.sort(items);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < size; i++) {
            if (items[i] > items[i - 1]) {
                res = Math.min(res, items[i] - items[i - 1]);   
            }
        }
        // If all elements in the submatrix have the same value, the answer will be 0.
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
