// https://leetcode.com/problems/largest-color-value-in-a-directed-graph/description/

// Solution: DP on graph + topo-sort
// Suppose a graph like this:
//   a ->        -> e
//        c -> d 
//   b ->        -> f
// Multiple paths end at c, and multiple paths start from d
// We need to store the colors count along the way till end, so we does not need to trace back after reaching the end.
// Because there are multiple paths end at c => We record the maximum code when seeing this adj node 
// (propagating from the previous node in the path)
// Time complexity: O(n + m), m edges, in case no cycle => m < n, but if has cycle => m > n
// Space complexity: O((n + m) + 26 * n + n + n) = O(n + m)

class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int size = 26;
        int n = colors.length();

        List<Integer>[] g = buildGraph(n, edges);
        int[] inDegs = new int[n];
        Queue<Integer> q = new LinkedList<>();
        // colorCount[cur][color] = maximum count of this color, from all paths that ends at cur.
        int[][] colorCount = new int[n][size];

        // Build inDegs
        for (List<Integer> adjNodes : g) {
            for (int adj : adjNodes) {
                inDegs[adj]++;
            }
        }

        // Initialize the queue by putting 0-in degree nodes into it.
        for (int i = 0; i < n; i++) {
            if (inDegs[i] == 0) {
                q.offer(i);
            }
        }

        int res = 0; // largest color value of all possible paths

        while (!q.isEmpty()) {
            int curNode = q.poll();
            int colorCode = colors.charAt(curNode) - 'a';

            // Record the color of this node into the path
            colorCount[curNode][colorCode]++;
            res = Math.max(res, colorCount[curNode][colorCode]);
            
            List<Integer> adjNodes = g[curNode];
            for (int adj : adjNodes) {
                inDegs[adj]--;
                if (inDegs[adj] == 0) {
                    q.offer(adj);
                }

                // because multiple node can point to the next node and each path has different number of colors
                // We try to get the maximum of each color code of every path, so we can always get the maximum for each color at the end
                for (int c = 0; c < size; c++) {
                    colorCount[adj][c] = Math.max(colorCount[adj][c], colorCount[curNode][c]);
                }
            }
        }

        // Check that whether the graph has cycle.
        // Notice that we have another way to check using count of visited nodes.
        for (int deg : inDegs) {
            if (deg != 0) {
                return -1;
            }
        }
        return res;
    }

    private List<Integer>[] buildGraph(int n, int[][] edges) {
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
        }
        return g;
    }
}
