// https://leetcode.com/problems/cheapest-flights-within-k-stops/

// Solution 1a: Bellman Ford with optimized space
// Time complexity: O(k * (n + e)), e = number of flights, n = number of nodes
// Space complexity: O(n)
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int INFINITY = 1_000_000_000;
        // dist[i][j] = the cheapest price from src to j, with at most i edges, or (i - 1) stops
        // dist[k][j] = the cheapest price from src to j, with at most k edges, or (k - 1) stops
        // => needs (k + 2) rows
        int[] prevDist = new int[n];
        Arrays.fill(prevDist, INFINITY);
        prevDist[src] = 0;

        for (int row = 1; row <= k + 1; row++) {
            int[] curDist = new int[n];
            for (int i = 0; i < n; i++) {
                curDist[i] = prevDist[i];
            }
            boolean stop = true;
            for (int[] f : flights) {
                int u = f[0], v = f[1], p = f[2];

                if (curDist[v] > prevDist[u] + p) {
                    curDist[v] = prevDist[u] + p;
                    stop = false;
                }
            }
            prevDist = curDist;
            if (stop) {
                break;
            }
        }
        return prevDist[dst] == INFINITY? -1 : prevDist[dst];
    }
}

// Solution 1: Naive bellman ford
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int INFINITY = 1_000_000_000;
        // dist[i][j] = the cheapest price from src to j, with at most i edges, or (i - 1) stops
        // dist[k][j] = the cheapest price from src to j, with at most k edges, or (k - 1) stops
        // => needs (k + 2) rows
        int[][] dist = new int[k + 2][n];
        for (int row = 0; row <= k + 1; row++){
            Arrays.fill(dist[row], INFINITY);
        }
        dist[0][src] = 0;

        for (int row = 1; row <= k + 1; row++) {
            for (int i = 0; i < n; i++) {
                dist[row][i] = dist[row-1][i];
            }
            for (int[] f : flights) {
                int u = f[0], v = f[1], p = f[2];

                if (dist[row][v] > dist[row-1][u] + p) {
                    dist[row][v] = dist[row-1][u] + p;
                }
            }
        }
        return dist[k + 1][dst] == INFINITY? -1 : dist[k + 1][dst];
    }
}

// Solution 1b: Use queue and similar idea to SPFA algo, but in this case, it runs longer than optimized Bellman Ford
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int INFINITY = 1_000_000_000;
        Map<Integer, Integer>[] g = buildGraph(n, flights);

        Queue<Integer> q = new LinkedList<>();
        q.offer(src);

        int stops = 0; // to check
        int[] dist = new int[n];
        Arrays.fill(dist, INFINITY);
        dist[src] = 0;

        // If the number of stops reach k, we go one more time, so we can reach the dst with k stops in between
        while (!q.isEmpty() && stops <= k) {
            int[] nextDist = new int[n];
            for (int i = 0; i < n; i++) {
                nextDist[i] = dist[i];
            }

            int size = q.size();
            while (size-- > 0) {
                int cityIdx = q.poll();
                int cityDist = dist[cityIdx];

                Map<Integer, Integer> adjCities = g[cityIdx];
                for (int adj : adjCities.keySet()) {
                    int adjPrice = adjCities.get(adj);
                    if (nextDist[adj] > cityDist + adjPrice) {
                        nextDist[adj] = cityDist + adjPrice;
                        q.offer(adj);
                    }
                }
            }

            dist = nextDist;
            stops++; // Add 1 more stop
        }

        return dist[dst] == INFINITY ? -1 : dist[dst];
    }

    // graph[i] = a map adjacent cities of city i, key = the city id, value = price to that city
    private Map<Integer, Integer>[] buildGraph(int n, int[][] flights) {
        Map<Integer, Integer>[] g = new HashMap[n];
        for (int i = 0; i < n; i++) {
            g[i] = new HashMap<>();
        }
        for (int[] f : flights) {
            int u = f[0], v = f[1], p = f[2];
            g[u].put(v, p);
        }
        return g;
    }
}

// Solution 2: TLE - Naive BFS 
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int INFINITY = 1_000_000_000;
        Map<Integer, Integer>[] g = buildGraph(n, flights);

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{src, 0});

        int stops = 0; // to check

        // If the number of stops reach k, we go one more time, so we can reach the dst with k stops in between
        int res = INFINITY;
        while (!q.isEmpty() && stops <= k) {

            int size = q.size();
            while (size-- > 0) {
                int[] curCity = q.poll();
                int cityIdx = curCity[0];
                int cityDist = curCity[1];

                Map<Integer, Integer> adjCities = g[cityIdx];
                for (int adj : adjCities.keySet()) {
                    int adjPrice = adjCities.get(adj);
                    int nextDist = cityDist + adjPrice;
                    if (nextDist > res) {
                        // No way to move this way and have a smaller price than result
                        continue;
                    }
                    
                    if (adj == dst) {
                        res = Math.min(res, nextDist);
                    }
                    q.offer(new int[]{adj, nextDist});
                }
            }
            stops++; // Add 1 more stop
        }

        return res == INFINITY ? -1 : res;
    }

    // graph[i] = a map adjacent cities of city i, key = the city id, value = price to that city
    private Map<Integer, Integer>[] buildGraph(int n, int[][] flights) {
        Map<Integer, Integer>[] g = new HashMap[n];
        for (int i = 0; i < n; i++) {
            g[i] = new HashMap<>();
        }
        for (int[] f : flights) {
            int u = f[0], v = f[1], p = f[2];
            g[u].put(v, p);
        }
        return g;
    }
}

// Solution 3: Dijkstra - TLE. Because we cannot discard states and must put all of them into heap
// Because we must store the stops it takes along with the distance => too many elemnts.
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int INFINITY = 1_000_000_000;
        Map<Integer, Integer>[] g = buildGraph(n, flights);

        // item[0] = city idx, item[1] = distance to get there, item[2] how many stops including it
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[1], b[1]);
        });      
        pq.offer(new int[]{src, 0, 0});

        while (!pq.isEmpty()) {
            int[] curCity = pq.poll();
            int cityIdx = curCity[0];
            int cityDist = curCity[1];
            int cityStops = curCity[2];

            if (cityIdx == dst) {
                // Because we always pop the minimum distance => if reach dst, it is the answer
                return cityDist;
            }

            if (cityStops > k) {
                continue;
            }

            Map<Integer, Integer> adjCities = g[cityIdx];
            for (int adj : adjCities.keySet()) {
                int adjPrice = adjCities.get(adj);
                int nextDist = cityDist + adjPrice;
                
                pq.offer(new int[]{adj, nextDist, cityStops + 1});
            }
        }

        return -1;
    }

    // graph[i] = a map adjacent cities of city i, key = the city id, value = price to that city
    private Map<Integer, Integer>[] buildGraph(int n, int[][] flights) {
        Map<Integer, Integer>[] g = new HashMap[n];
        for (int i = 0; i < n; i++) {
            g[i] = new HashMap<>();
        }
        for (int[] f : flights) {
            int u = f[0], v = f[1], p = f[2];
            g[u].put(v, p);
        }
        return g;
    }
}
