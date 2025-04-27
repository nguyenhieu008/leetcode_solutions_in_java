// https://leetcode.com/problems/path-existence-queries-in-a-graph-i/description/

// Solution 2: Same idea as solution 1, but we just mark the nodes in same connected component with same group id => easily check 2 nodes are in the same connected component.
class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] group = new int[n];
        
        int groupIdx = 0;
        group[0] = groupIdx;
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                groupIdx++;
            }
            group[i] = groupIdx;
        }
        
        boolean[] res = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0], v = queries[i][1];
            res[i] = group[u] == group[v];
        }
        return res;
    }
}

// Solution 1: The connected component in the graph will always be an subarray (with a min-max index)
// So, we just need to list the subarray, then check the 2 nodes in queries are in the same connected component.
// Time complexity: O(n + q)
// Space complexity: O(n)

class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Because the whole array will be modified below => no need a specific value for initialization
        int[] minReach = new int[n];
        int[] maxReach = new int[n]; 
        
        int left = 0;
        int maxRange = nums[0] + maxDiff;
        for (int right = 1; right < n; right++) {
            if (nums[right] > maxRange) {
                // Cannot reach further nodes => we have a connected subgraph from left -> right - 1.
                // NOTICE: we must update the left as well, after calling the function.
                // Because left is a primitive type and not updated after the function.
                // After that, still need to update maxRange;
                buildGraph(minReach, maxReach, left, right);
                left = right;
            }
            maxRange = nums[right] + maxDiff;
        }
        // Add the last sub-graph that ends at n - 1
        buildGraph(minReach, maxReach, left, n);
        
        boolean[] res = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0], v = queries[i][1];
            // if v is valid => u will be valid anyway => no need to check more
            res[i] = (v >= minReach[u] && v <= maxReach[u]);
        }
        return res;
    }
    
    private void buildGraph(int[] minReach, int[] maxReach, int left, int right) {
        int minIdx = left;
        while (left < right) {
            minReach[left] = minIdx;
            maxReach[left] = right - 1;
            left++;
        }
    }
}
