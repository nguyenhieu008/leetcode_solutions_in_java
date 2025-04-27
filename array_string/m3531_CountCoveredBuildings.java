// https://leetcode.com/problems/count-covered-buildings/description/
// My solution: https://leetcode.com/problems/count-covered-buildings/solutions/6691459/simple-idea-the-preprocess-the-buildings-into-max-min-ac-java-solution-that-beats-100/

class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        int[] minX = new int[n+1];
        int[] maxX = new int[n+1];
        int[] minY = new int[n+1];
        int[] maxY = new int[n+1];
        
        Arrays.fill(minX, n + 1);
        Arrays.fill(minY, n + 1);
        
        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            
            minX[y] = Math.min(minX[y], x);
            maxX[y] = Math.max(maxX[y], x);
            minY[x] = Math.min(minY[x], y);
            maxY[x] = Math.max(maxY[x], y);
        }
        
        int res = 0;
        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            if (minX[y] < x && maxX[y] > x && minY[x] < y && maxY[x] > y) {
                res++;
            }
        }
        return res;
    }
}
