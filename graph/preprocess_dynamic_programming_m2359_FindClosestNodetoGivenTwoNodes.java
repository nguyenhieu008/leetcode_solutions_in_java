// https://leetcode.com/problems/find-closest-node-to-given-two-nodes/description/

// Solution: We can use both DFS or BFS to preprocess and calculate the distance from node1 and node2, then calculate the result
// But because there is only 1 edge from 1 node, and they are all on array, we can simply traverse using a while loop
// Time complexity: O(n)
// Space complexity: O(n);

class Solution {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        /*
        - Build 2 arrays:
            - dist1[i] = distance from node1 to i
            - dist2[i] = distance from node2 to i
        - For dist1:
            - dist1[node1] = 0;
            - step = 0;
            - Follow the edges to find next node, dist[next node] = step++
            - end if next node == -1 or we traversed next node before (dist1[next node] != infinity)

        - For all i, store the minimum of Max(dist1[i], dist2[i]), and the idx the produce that minimum
        */

        int n = edges.length;
        int[] dist1 = traverse(edges, node1);
        int[] dist2 = traverse(edges, node2);

        int idx = -1;
        int curMin = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int value = Math.max(dist1[i], dist2[i]);
            if (value < curMin) {
                // Only update if less than curMin => not update if equal
                // => idx is the smallest index
                idx = i; 
                curMin = value;
            }
        }
        return idx;
    }

    private int[] traverse(int[] edges, int startNode) {
        int n = edges.length;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startNode] = 0;

        int curNode = edges[startNode], steps = 1;
        while (curNode != -1 && dist[curNode] == Integer.MAX_VALUE) {
            dist[curNode] = steps;
            steps++;
            curNode = edges[curNode];
        }
        return dist;
    }
}
