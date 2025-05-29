// https://leetcode.com/problems/rotting-oranges/description/

class Solution {
    public int orangesRotting(int[][] grid) {
        /*
            numFresh: number of fresh orange currently
            Queue rotten: the oranges that have been rotten at minute i

            - Loop through every cells: 
                - count num of fresh oranges
                - put the rotten into queues
            
            minute: count the number of minutes until the queue is empty
            - Do the level-order tranversal with the queue "rotten", until no item in queue:
                - For 1 level:
                    - For each rotten orange, scan 4-adj cells and put fresh orange to queue
                    - Mark the orange as rotten after put
                    - Decrease the number of fresh by 1
                - increment the minute

            if (numFresh > 0) return -1;
            else return the minute
        */

        int m = grid.length, n = grid[0].length;
        int numFresh = 0;
        Queue<int[]> rotten = new LinkedList<>();

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                int value = grid[row][col];
                if (value == 1) {
                    numFresh++;
                } else if (value == 2) {
                    rotten.offer(new int[]{row, col});
                }
            }
        }

        int minute = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!rotten.isEmpty()) {
            int size = rotten.size();
            while (size-- > 0) {
                int[] coordinate = rotten.poll();
                int row = coordinate[0], col = coordinate[1];
                
                for (int[] d : dirs) {
                    // Rotten the fresh orange at the next minute
                    int drow = d[0], dcol = d[1];
                    int nextRow = row + drow, nextCol = col + dcol;

                    if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n || grid[nextRow][nextCol] != 1) {
                        continue;
                    }
                    numFresh--;
                    grid[nextRow][nextCol] = 2;
                    rotten.offer(new int[]{nextRow, nextCol});
                }
            }
            if (!rotten.isEmpty()) {
                // If there are some oranges left, we need another minute for them to be rotten
                minute++;
            }
        }

        if (numFresh > 0) {
            return -1;
        }
        return minute;
    }
}
