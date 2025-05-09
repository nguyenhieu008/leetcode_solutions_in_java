// https://leetcode.com/problems/network-delay-time/description/
// Solution: Dijkstra heap
// Notice: the array dist can be optional, details in comment

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
