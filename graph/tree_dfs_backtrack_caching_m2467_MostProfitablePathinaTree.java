// https://leetcode.com/problems/most-profitable-path-in-a-tree/description/

// Solution 2: Combine 2 DFS into 1. To calculate the amount of a node, use the current level, and distanceFromBob technique
// It init the distance with large value (> n), if reach bob then set to 0. After backtrack to previous node, reset the distance
// based on the value of the recently visited node.
// NOTICE: THIS TECHNIQUE TO CACHE THE DISTANCE AND BACKTRACK/MODIFY THE VALUE OF PREVIOUS NOT IS GOOD AND IMPORTANT TO KNOW
// Time complexity: O(n)
// Space complexity: O(n);

class Solution {
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {

        List<Integer>[] tree = buildTree(edges);
        int[] distanceFromBob = new int[tree.length];

        return dfs(tree, 0, -1, bob, 0, amount, distanceFromBob);
    }

    // return res[0] = total amount, res[1] = bob level
    private int dfs(List<Integer>[] tree, int curNode, int parent, int bob, int level, int[] amount, int[] distanceFromBob) {
        int subTreeValue = Integer.MIN_VALUE;

        if (curNode == bob) {
            distanceFromBob[curNode] = 0;
        } else {
            distanceFromBob[curNode] = tree.length;
        }

        List<Integer> children = tree[curNode];
        for (int child : children) {
            if (child == parent) {
                continue;
            }

            int value = dfs(tree, child, curNode, bob, level + 1, amount, distanceFromBob);
            subTreeValue = Math.max(subTreeValue, value);

            distanceFromBob[curNode] = Math.min(distanceFromBob[curNode], distanceFromBob[child] + 1);    
        }

        int curAmount = 0;
        if (level < distanceFromBob[curNode]) {
            // Alice reaches first
            curAmount = amount[curNode];
        } else if (level == distanceFromBob[curNode]){
            // Alice and Bob reach the same time
            curAmount = amount[curNode] / 2;
        }
        
        return subTreeValue == Integer.MIN_VALUE ? curAmount : curAmount + subTreeValue;
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

// Solution 1: Run 2 DFS to find the bob then modify the amount, then calculate the result
// Time compleixty: O(n)
// Space complexity: O(n)

class Solution {
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        /*
            We use DFS to find bob node, with the level, so we can determine how many node we need to mondiy the amount along the path
            When found, go back through the call stack and modify the amount of nodes (to 0 or to half amount) along the path.
            Then finally, do dfs again to get the maximum net income.
        */

        List<Integer>[] tree = buildTree(edges);
        findBob(tree, 0, -1, bob, 0, amount);

        return dfs(tree, 0, -1, amount);
    }

    private int dfs(List<Integer>[] tree, int curNode, int parent, int[] amount) {
        if (tree[curNode].size() == 1 && curNode != 0) {
            // leaf node just return amount;
            return amount[curNode];
        }

        int subTreeValue = Integer.MIN_VALUE;

        List<Integer> children = tree[curNode];
        for (int child : children) {
            if (child == parent) {
                continue;
            }
            subTreeValue = Math.max(subTreeValue, dfs(tree, child, curNode, amount));
        }
        return subTreeValue + amount[curNode];
    }

    // return int to know which level to modify amount
    private int findBob(List<Integer>[] tree, int curNode, int parent, int bob, int level, int[] amount) {
        if (curNode == bob) {
            amount[curNode] = 0;
            return level;
        }

        List<Integer> children = tree[curNode];
        for (int child : children) {
            if (child == parent) {
                continue;
            }
            int bobLevel = findBob(tree, child, curNode, bob, level + 1, amount);
            if (bobLevel == -1) {
                continue;
            }
            if (level * 2 > bobLevel) {
                amount[curNode] = 0;
            } else if (level * 2 == bobLevel) {
                amount[curNode] /= 2;
            }
            return bobLevel;
        }
        return -1;
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
