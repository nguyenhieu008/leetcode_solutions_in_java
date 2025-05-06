// https://leetcode.com/problems/course-schedule-ii/
// topo sort
class Solution {
    public int[] findOrder(int n, int[][] ps) {
        List<Integer>[] g = buildGraph(n, ps);

        // number of requirements for a course
        int[] inDegs = new int[n];
        for (int[] p : ps) {
            inDegs[p[0]]++;
        }

        // Add courses without requirements
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegs[i] == 0) {
                q.offer(i);
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int a = q.poll();
            res.add(a);

            List<Integer> adjNodes = g[a];
            for (int adjNode : adjNodes) {
                inDegs[adjNode]--;
                if(inDegs[adjNode] == 0) {
                    q.offer(adjNode);
                }
            }
        }
        if (res.size() == n) {
            return res.stream().mapToInt(Integer::intValue).toArray();
        }
        return new int[0];
    }

    private List<Integer>[] buildGraph(int n, int[][] ps) {
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] p : ps) {
            int a = p[0], b = p[1];
            g[b].add(a);
        }
        return g;
    }
}

// https://leetcode.com/problems/course-schedule/description/
// Solution 2: topo-sort.


// Solution 1: DFS. Read more about tarjan algorithm to list connected component in directed graph
// When first visit a graph, mark it as visiting, and after traverse the whole dfs tree at that node, mark it as visited.
// When accessing an adjacent node, if it's
//   - visited => removed from graph => it's already traversed completely => forward/cross edge => it's fine
//   - visiting but not visited => not removed from graph, we are traversing it => it's an ascendent in the tree => backedge => cycle
// After visit the whole subtree, remember to mark a node as visited to remove from tree.

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // need to build graph, adjacent list 
        // HashMap<Integer, List<Integer>> requirements: p[0] -> p[1]

        // traverse through all nodes using dfs, mark visited
        // the directed graph must not have cycle => How to detect cycle in directed graph? 
        // Mark the treeIdx for each DFS tree from a specific node.

        // graph of key-values, where the values courses need to be finished first
        Map<Integer, List<Integer>> g = new HashMap<>();
        buildGraph(g, numCourses, prerequisites);

        boolean[] visited = new boolean[numCourses];
        boolean[] visiting = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (!dfs(g, visiting, visited, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(Map<Integer, List<Integer>> g, boolean[] visiting, boolean[] visited, int node) {
        visiting[node] = true;

        List<Integer> adjNodes = g.get(node);
        for (int adjNode : adjNodes) {
            if (visited[adjNode]) {
                // forward edge or cross edge
                continue;
            }
            if (visiting[adjNode]) {
                // this is a back edge.
                return false;
            }

            if (!dfs(g, visiting, visited, adjNode)) {
                return false;
            }
        }
        visited[node] = true;
        return true;
    }

    private void buildGraph(Map<Integer, List<Integer>> graph, int n, int[][] p) {
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] pair : p) {
            int a = pair[0], b = pair[1];
            graph.get(a).add(b);
        }
    }
}
