// https://leetcode.com/problems/find-minimum-time-to-reach-last-room-ii/description/
// Solution: Djistra heap

class Solution {
    class Point implements Comparable<Point> {
        int x;
        int y;
        int time;

        Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public int compareTo(Point a) {
            return Integer.compare(this.time, a.time);
        }

        // @Override
        // public String toString() {
        //     return String.format("Point(x = %d, y = %d, time = %d)", x, y, time);
        // }
    }

    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;
        int[][] dp = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(0, 0, 0));
        dp[0][0] = 0;

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            int row = p.x, col = p.y;
            if (row == n - 1 && col == m - 1) {
                return p.time;
            }
            if (visited[row][col]) {
                continue;
            }

            visited[row][col] = true;

            for (int[] dir : dirs) {
                int nextRow = row + dir[0], nextCol = col + dir[1];
                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) {
                    continue;
                }
                int nextTime = Math.max(moveTime[nextRow][nextCol], dp[row][col]) + cost(nextRow, nextCol);
                if (nextTime < dp[nextRow][nextCol]) {
                    dp[nextRow][nextCol] = nextTime;
                    pq.offer(new Point(nextRow, nextCol, nextTime));
                }
            }
            // System.out.println("pq = " + pq);
        }
        return dp[n-1][m-1]; // should not reach here
    }

    // This is the same, regardless of the the path (longer path will yield the same cost)
    private int cost(int i, int j) {
        return 2 - ((i + j) % 2);
    }
}
