// https://leetcode.com/problems/maximize-the-number-of-target-nodes-after-connecting-trees-ii/description/

// Solution 1a: A bit shorter and cleaner than solution using boolean because we can use the label as array indices
// For 2 nodes u-v that are target of each others => length between u-v is even 
// => any nodes event length to u will also have even length to v
// => Labels the nodes in the tree into 2 sets, then count number of each set, each onode need to know which set it belongs to
// When connect to tree2, just need to connect in the way to match it with the largest set of tree 2
// Time complexity: O(m + n), tree1 has m nodes, tree2 has n nodes
// Space complexity: O(m + n);
class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int[] labels1 = labelNodes(edges1);
        int[] labels2 = labelNodes(edges2);
        int m = labels1.length, n = labels2.length;

        int[] numOfLabels1 = new int[2]; // Only 2 labels for 1 tree, odd or even
        int[] numOfLabels2 = new int[2];

        for (int l : labels1) {
            numOfLabels1[l]++;
        }
        
        for (int l : labels2) {
            numOfLabels2[l]++;
        }
        int maxSet2 = Math.max(numOfLabels2[0], numOfLabels2[1]);

        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int label = labels1[i];
            res[i] = maxSet2 + numOfLabels1[label];
        }
        return res;
    }

    private int[] labelNodes(int[][] edges) {
        List<Integer>[] tree = buildTree(edges);
        int n = tree.length;
        int[] labels = new int[n];

        dfs(tree, 0, -1, labels, 1);

        return labels;
    }

    // We label the nodes into 2 sets: odd and even set.
    // The nodes in the same set will have even number of edges between them.
    // In other words, all the nodes in the same set will be the target node to each other
    // And size of each set will be the number of target nodes for each node in that set.
    private void dfs(List<Integer>[] tree, int root, int parent, int[] labels, int label) {
        // to check base case

        labels[root] = label;

        List<Integer> adjNodes = tree[root];
        for (int adj : adjNodes) {
            if (adj == parent) {
                continue;
            }
            // Because it's tree, there is no cycle and each node will be visited once
            dfs(tree, adj, root, labels, label ^ 1); // level ^ 1 == flip the first bit
        }
    }

    private List<Integer>[] buildTree(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            tree[u].add(v);
            tree[v].add(u);
        }
        return tree;
    }
    
}

// Solution 1: Intuition in solution 1a
class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        boolean[] labels1 = labelNodes(edges1);
        boolean[] labels2 = labelNodes(edges2);
        int m = labels1.length, n = labels2.length;

        int set1True = 0, set1False = 0;
        int set2True = 0, set2False = 0;

        for (int i = 0; i < m; i++) {
            if (labels1[i]) {
                set1True++;
            } else {
                set1False++;
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (labels2[i]) {
                set2True++;
            } else {
                set2False++;
            }
        }
        int maxSet2 = Math.max(set2True, set2False);

        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            if (labels1[i]) {
                res[i] = set1True + maxSet2;
            } else {
                res[i] = set1False + maxSet2;
            }
        }
        return res;
    }

    private boolean[] labelNodes(int[][] edges) {
        List<Integer>[] tree = buildTree(edges);
        int n = tree.length;
        boolean[] labels = new boolean[n];

        dfs(tree, 0, -1, labels, true);

        return labels;
    }

    // We label the nodes into 2 sets: odd and even set.
    // The nodes in the same set will have even number of edges between them.
    // In other words, all the nodes in the same set will be the target node to each other
    // And size of each set will be the number of target nodes for each node in that set.
    private void dfs(List<Integer>[] tree, int root, int parent, boolean[] labels, boolean label) {
        // to check base case

        labels[root] = label;

        List<Integer> adjNodes = tree[root];
        for (int adj : adjNodes) {
            if (adj == parent) {
                continue;
            }
            // Because it's tree, there is no cycle and each node will be visited once
            dfs(tree, adj, root, labels, label ^ true); // level ^ 1 == 
        }
    }

    private List<Integer>[] buildTree(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            tree[u].add(v);
            tree[v].add(u);
        }
        return tree;
    }
    
}

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
