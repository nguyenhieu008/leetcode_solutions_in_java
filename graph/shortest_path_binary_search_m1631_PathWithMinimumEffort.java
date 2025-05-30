// https://leetcode.com/problems/path-with-minimum-effort/

// Solution 1: Dijkstra heap
// Time complexity: O(E * logV) = O(4 * m * n * log (m * n)) = O(m * n * log(m * n))
// Space complexity: O(m * n), for heap
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int[][] effort = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(effort[i], Integer.MAX_VALUE);
        }
        effort[0][0] = 0;

        // a[0] = row of a, a[1] = col of a, a[2] = effort to reach a
        // min heap, sorted by effort 
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[2], b[2]);
        });
        pq.offer(new int[]{0, 0, 0});
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!pq.isEmpty()) {
            int[] cell = pq.poll();
            int curRow = cell[0], curCol = cell[1], curEffort = cell[2];
            // System.out.println("curRow = " + curRow + ", curCol = " + curCol + ", curEffort = " + curEffort);

            if (curRow == m - 1 && curCol == n - 1) {
                return curEffort;
            }

            if (curEffort > effort[curRow][curCol]) {
                // There may be duplicate in heap
                continue;
            }

            for (int[] d : dirs) {
                int dRow = d[0], dCol = d[1];
                int nextRow = curRow + dRow, nextCol = curCol + dCol;

                if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n) {
                    continue;
                }
                int nextEffort = Math.max(curEffort, Math.abs(heights[curRow][curCol] - heights[nextRow][nextCol]));
                // System.out.println("nextEffort = " + nextEffort + ", nextRow = " + nextRow + ", nextCol = " + nextCol + ", effort[nextRow][nextCol] = " + effort[nextRow][nextCol]);
                if (nextEffort < effort[nextRow][nextCol]) {
                    effort[nextRow][nextCol] = nextEffort;
                    pq.offer(new int[]{nextRow, nextCol, nextEffort});
                }
            }
            // System.out.println("effort = ");
            // for (int i = 0; i < m; i++) {
            //     System.out.println(Arrays.toString(effort[i]));
            // }
        }
        return effort[m - 1][n - 1]; // should not go here
    }
}


// Solution 2: Binary search the threshold
// If the threshold greater => more chance to finish
// => array shape: false false -> true true
// Find the smallest threshold that the condition turns true.
// The dfs takes O(m * n) as we marked visited array
// Time complexity: O(m * n * log(maxHeight))
// Space complexity: O(m * n), we create new visited matix each time testing new mid value

class Solution {
    private int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        int maxHeight = 1_000_000;
        
        int left = 0, right = maxHeight;
        while (left < right) {
            int mid = left + (right - left) / 2;
            boolean[][] visited = new boolean[m][n];

            if (canFinish(heights, 0, 0, mid, visited)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean canFinish(int[][] heights, int row, int col, int threshold, boolean[][] visited) {
        int m = heights.length, n = heights[0].length;
        if (row == m - 1 && col == n - 1) {
            return true;
        }

        visited[row][col] = true;

        for (int[] d : dirs) {
            int dRow = d[0], dCol = d[1];
            int nextRow = row + dRow, nextCol = col + dCol;
            if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n || visited[nextRow][nextCol]) {
                continue;
            }
            if (Math.abs(heights[nextRow][nextCol] - heights[row][col]) <= threshold) {
                if (canFinish(heights, nextRow, nextCol, threshold, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
}
