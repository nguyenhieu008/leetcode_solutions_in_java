// https://leetcode.com/problems/network-delay-time/description/

// Solution: Dijkstra heap
// Notice: the array dist can be optional, details in comment
// Time complexity: O(E + VlogV), e = number of edges, when build graph. v = number of vertices, there are at most V nodes in heap.
// In fact, I think it should be O(E + Vlog(v^2)). Worst case:
// Edges: [1, 2, 1], [1, 3, 1000], [1, 4, 1000], [1, 5, 1000], [2, 3, 1], [2, 4, 900], [2, 5, 900], [3, 4, 1], [3, 5, 800], [4, 5, 1]
// => queue will have the following in the INSERT ORDER (not the heap order):
//     [[1, 0], [2, 1], [3, 1000], [4, 1000], [5, 1000], [3, 2], [4, 901], [5, 901], [4, 3], [5, 802], [5, 4]]
// Popped items: [1, 0], [2, 1], [3, 2], [4, 3], [5, 4];
// Leftover items: [3, 1000], [4, 1000], [5, 1000], [4, 901], [5, 901], [5, 802] => there are redundant items
// => worst case: there are v^2 items
// Space complexity: O(E + V), for the graph. Or can be O(E + V^2)

class Solution {
    class Node {
        int v;
        int w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    class PathHop implements Comparable<PathHop> {
        int v;
        int dist;

        PathHop(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(PathHop a) {
            return Integer.compare(this.dist, a.dist);
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        List<Node>[] g = buildGraph(n, times);

        boolean[] visited = new boolean[n+1];
        // dist is used only to avoid adding too many Nodes into the priority queue. Sometimes, we can ignore it to save time.
        int[] dist = new int[n+1]; // distance to k
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        // min heap, storing the index of node where comparing the distance from it to k
        PriorityQueue<PathHop> pq = new PriorityQueue<>();
        pq.offer(new PathHop(k, 0));
        dist[k] = 0;

        while (!pq.isEmpty()) {
            PathHop node = pq.poll();
            int u = node.v;
            if (visited[u]) continue;

            visited[u] = true;

            List<Node> adjNodes = g[u];
            for (Node adj : adjNodes) {
                int nextDist = dist[u] + adj.w;
                if (nextDist < dist[adj.v]) {
                    pq.offer(new PathHop(adj.v, nextDist));
                    dist[adj.v] = nextDist;
                }
            }
        }

        // Another approach is updating res inside the while loop
        // Because we need to get the maximum dist => we update it always in the while loop, since the next PathHop will always have >= dist compared to previous hop.
        int res = 0; 
        // Ignore 0
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            } else {
                res = Math.max(res, dist[i]);
            }
        }
        return res;
    }

    private List<Node>[] buildGraph(int n, int[][] times) {
        List<Node>[] g = new ArrayList[n+1];
        // Ignore 0
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] t : times) {
            int u = t[0], v = t[1], w = t[2];
            g[u].add(new Node(v, w));
        }
        return g;
    }
}

// Solution 2: Bellman Ford or Shortest Path Faster Algorigthm
class Solution {

    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, Integer>[] g = buildGraph(times, n);

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.offer(k);

        while (!q.isEmpty()) {
            int curNode = q.poll();

            Map<Integer, Integer> adjNodes = g[curNode];
            for (int adj : adjNodes.keySet()) {
                int w = adjNodes.get(adj);
                int nextDist = dist[curNode] + w;
                if (dist[adj] > nextDist) {
                    dist[adj] = nextDist;
                    q.offer(adj);
                }
            }
        }
        int res = 0;
        for (int i = 1; i <= n; i++) {
            int d = dist[i];
            if (d == Integer.MAX_VALUE) {
                return -1;
            }
            res = Math.max(res, d);
        }
        return res;
    }

    private Map<Integer, Integer>[] buildGraph(int[][] times, int n) {
        Map<Integer, Integer>[] g = new HashMap[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new HashMap<>();
        }
        for (int[] t : times) {
            int u = t[0], v = t[1], w = t[2];
            g[u].put(v, w);
        }
        return g;
    }
      
}
