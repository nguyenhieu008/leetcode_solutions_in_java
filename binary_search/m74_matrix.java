// Solution 2: return right away, but need to change the condition to `while (r - l >= 0) {`, because we need to test the case where l == r as well.

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // non-descending => can duplicate,
        // go from left-> right, top -> bottom, the order is guaranteed
        // return true, false only
        // 1 <= m, n <= 100
        int m = matrix.length;
        int n = matrix[0].length;
        int totalItems = m * n; 

        int l = 0, r = totalItems - 1;
        while (r - l >= 0) {
            int mid = l + (r - l) / 2;
            int midValue = matrix[getX(mid, n)][getY(mid, n)];
            if (midValue == target) {
                return true;
            } else if (midValue > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }
    private int getX(int idx, int columns) {
        return idx / columns;
    }
    private int getY(int idx, int columns) {
        return idx % columns;
    }
}

// Solution 1: just test template
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // non-descending => can duplicate,
        // go from left-> right, top -> bottom, the order is guaranteed
        // return true, false only
        // 1 <= m, n <= 100
        int m = matrix.length;
        int n = matrix[0].length;
        int totalItems = m * n; 

        int l = 0, r = totalItems - 1;
        while (r - l > 0) {
            int mid = l + (r - l) / 2;
            if (matrix[getX(mid, n)][getY(mid, n)] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return matrix[getX(l, n)][getY(l, n)] == target;
    }
    private int getX(int idx, int columns) {
        return idx / columns;
    }
    private int getY(int idx, int columns) {
        return idx % columns;
    }
}
