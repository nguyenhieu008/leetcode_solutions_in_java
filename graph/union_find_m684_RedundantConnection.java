// https://leetcode.com/problems/redundant-connection/

class Solution {
    class DisjointSet {
        int[] parent;
        int[] rank; // utilize union by rank
        DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int u) {
            if (parent[u] != u) {
                parent[u] = find(parent[u]); // path compression
            }
            return parent[u];
        }

        public boolean union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);

            if (rootU == rootV) {
                return false;
            }

            if (rank[rootV] < rank[rootU]) {
                parent[rootV] = rootU;
            } else  {
                parent[rootU] = rootV;

                if (rank[rootU] == rank[rootV]) {
                    rank[rootV]++;
                }
            }
            return true;
        }
    }
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length + 1;
        DisjointSet tree = new DisjointSet(n);
        for (int[] e : edges) {
            if (!tree.union(e[0], e[1])) {
                return e;
            }
        }
        return new int[]{}; // should not reach here
    }
}
