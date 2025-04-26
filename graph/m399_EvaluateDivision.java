// https://leetcode.com/problems/evaluate-division/description/

// For a new query, e.g. "a" / "c", we need to find a path from "a" -> "c", e.g. "a" -> "b" and "b" -> "c"
// => We need to use graph. Should have separate functions to build graph and calculate result for clarity
// Time complexity: O(n + m) for each query, while n is the number of nodes, m is number of edge.
// n is at most 2 * m => O(m * q) where m is the number of equations, q is number of queries
// Space complexity: O(m + n) for graph

class Solution {
    class Node {
        String name;
        double val;

        Node(String name, double val) {
            this.name = name;
            this.val = val;
        }
    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = equations.size();
        HashMap<String, List<Node>> g = new HashMap<>();
        buildGraph(g, equations, values);

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            HashSet<String> visited = new HashSet<>();

            res[i] = dfs(g, visited, query.get(0), query.get(1), 1);
        }
        return res;
    }

    private void buildGraph(HashMap<String, List<Node>> g, List<List<String>> equations, double[] values) {
        // No need to add self direction, because
        // a -> a = (a -> b) * (b -> a)
        // We will get the result anyway
        for (int i = 0; i < equations.size(); i++) {
            List<String> e = equations.get(i);
            String curNode = e.get(0);
            String nextNode = e.get(1);
            double value = values[i];

            g.putIfAbsent(curNode, new ArrayList<>());
            g.putIfAbsent(nextNode, new ArrayList<>());

            g.get(curNode).add(new Node(nextNode, value));
            g.get(nextNode).add(new Node(curNode, 1 / value));
        }
    }

    private double dfs(HashMap<String, List<Node>> g, HashSet<String> visited, String from, String to, double cur) {
        if (g.containsKey(from) == false || g.containsKey(to) == false) {
            return -1;
        }

        if (from.equals(to)) {
            return cur;
        }

        visited.add(from);

        List<Node> adjNodes = g.get(from);
        for (Node n : adjNodes) {
            if (visited.contains(n.name)) {
                continue;
            }

            double res = dfs(g, visited, n.name, to, cur * n.val);
            if (res != -1) {
                return res;
            }
        }

        // No need to remove visited(from);
        return -1;
    }
}
