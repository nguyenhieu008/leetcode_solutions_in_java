// https://leetcode.com/problems/number-of-provinces/description/

public class Solution {
    class DisjointSet {
        int count;
        int[] parent;
        int[] rank; // virtually upper and a guess on which tree to merge
        DisjointSet(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0; // Optional
            }
        }

        public int find(int u) {
            if (u != parent[u]) {
                parent[u] = find(parent[u]);
            }
            return parent[u];
        }

        public void union(int u, int v) {
            // No need result as we won't handle
            int rootU = find(u);
            int rootV = find(v);

            if (rootU == rootV) {
                // already in the same set
                return ;
            }

            if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else if (rank[rootV] < rank[rootU]) {
                parent[rootV] = rootU;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
            count--;
        }
    }
    
    public int findCircleNum(int[][] M) {
        int n = M.length;
        DisjointSet provinces = new DisjointSet(n);
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (M[u][v] == 1) {
                    provinces.union(u, v);
                }
            }
        }
        return provinces.count;
    }
}
