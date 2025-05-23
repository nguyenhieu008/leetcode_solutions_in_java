// https://leetcode.com/problems/maximum-score-after-applying-operations-on-a-tree/description/

// Solution 2: DP, DFS. But handle directly on the result returned by children.
class Solution {
    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        int n = edges.length + 1;
        List<Integer>[] tree = buildTree(edges);

        // If this subtree already healthy:
        //  - We can pick this and consider all subtrees as healthy (collect all)
        // If this subtree is not healthy yet, two cases:
        //  - Do not pick this => consider all subtrees as healthy (collect all)
        //  - Pick this => consider all subtree as not healthy yet
        //  - Then get max of both case
        //  - Edge case: If this is a leaf, we cannot pick
        // 

        long[] rootValue = dfs(tree, values, 0, -1);

        return rootValue[0];
    }

    private long[] dfs(List<Integer>[] tree, int[] values, int curNode, int parent) {
        long healthyChildrenScore = 0, unhealthyChildrenScore = 0;
        boolean isLeaf = true;

        for (int child : tree[curNode]) {
            if (child == parent) continue;

            long[] childScore = dfs(tree, values, child, curNode);
            healthyChildrenScore += childScore[1]; // child is healthy
            unhealthyChildrenScore += childScore[0]; // child is unhealthy yet

            // have a child then it's not leaf
            isLeaf = false;
        }

        if (isLeaf) {
            // When this is leaf, we have no other child to consider it as unhealthy
            unhealthyChildrenScore = Integer.MIN_VALUE;
        }

        // If this already healthy before, pick this and all children
        long curNodeHealthyScore = values[curNode] + healthyChildrenScore;

        // If this unhealthy yet, we need consider 2 cases
        //  - Not pick this (do not count values[curNode]) then pick all children as healthy
        //  - Pick this, then consider all children as unhealthy

        long curNodeUnhealthyScore = Math.max(healthyChildrenScore, values[curNode] + unhealthyChildrenScore);
        
        return new long[]{curNodeUnhealthyScore, curNodeHealthyScore};
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


// Solution 1: DP, DFS + memo

class Solution {
    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        int n = edges.length + 1;
        List<Integer>[] tree = buildTree(edges);
        // dp[i][0] = maximum score at index i, if the subtree at i not healthy yet
        // dp[i][1] = maximum score at index i, if the subtree at i already healthy
        long[][] dp = new long[n][2];

        // If this subtree already healthy:
        //  - We can pick this and consider all subtrees as healthy (collect all)
        // If this subtree is not healthy yet, two cases:
        //  - Do not pick this => consider all subtrees as healthy (collect all)
        //  - Pick this => consider all subtree as not healthy yet
        //  - Then get max of both case
        //  - Edge case: If this is a leaf, we cannot pick
        // 
        // 
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        dfs(tree, values, dp, 0, -1);

        return dp[0][0];
    }

    private void dfs(List<Integer>[] tree, int[] values, long[][] dp, int curNode, int parent) {

        // Do not pick this one, so all children can be picked
        long notPick = 0; 
        long pick = values[curNode];
        long healthyChildren = 0, unhealthyChildren = 0;
        boolean isLeaf = true;

        for (int child : tree[curNode]) {
            if (child == parent) continue;

            dfs(tree, values, dp, child, curNode);
            healthyChildren += dp[child][1]; // next node is healthy
            unhealthyChildren += dp[child][0];
            isLeaf = false;
        }

        if (isLeaf) {
            // When this is leaf, we have no other child to consider it as unhealthy
            unhealthyChildren = Integer.MIN_VALUE;
        }

        dp[curNode][0] = Math.max(notPick + healthyChildren, pick + unhealthyChildren);
        dp[curNode][1] = pick + healthyChildren;
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
