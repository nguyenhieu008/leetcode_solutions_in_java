// https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i/
// Reference: https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-i/editorial/

class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int[] count1 = buildCount(edges1, k);
        int m = count1.length;
        int[] count2 = buildCount(edges2, k - 1);

        int maxCount2 = 0;
        for (int c2 : count2) {
            maxCount2 = Math.max(maxCount2, c2);
        }

        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            res[i] = count1[i] + maxCount2;
        }
        return res;
    }

    private int[] buildCount(int[][] edges, int k) {
        List<Integer>[] tree = buildTree(edges);
        int n = tree.length;

        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            count[i] = dfs(tree, i, -1, k);
        }
        return count;
    }

    private int dfs(List<Integer>[] tree, int root, int parent, int k) {
        if (k < 0) {
            // In case input k is < 0
            return 0;
        }

        int num = 1; // this node
        List<Integer> children = tree[root];
        for (int c : children) {
            if (c == parent) {
                continue;
            }
            num += dfs(tree, c, root, k - 1);
        }
        return num;
    }

    private List<Integer>[] buildTree(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] t = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            t[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            t[u].add(v);
            t[v].add(u);
        }
        return t;
    }
}
