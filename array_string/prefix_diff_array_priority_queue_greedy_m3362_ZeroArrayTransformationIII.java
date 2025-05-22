// https://leetcode.com/problems/zero-array-transformation-iii/description/

// Solution: Maximum removals from queries also means that we want a way to apply the minimum queries possible.
// So we do it greedily with the greatest possible query. But for an index i, and multiple queries that cover i. which query to choose?
// What is difficult here is that for an inner index, we cannot choose which query to use, as the start/end idx varies.
// So we need to stabilize the index to apply query, starting from 0, => we can always choose the farthest/greatest right index query.
// After apply the queries and if something left, just leave it here and move on to the index 1.
// => Now also put all possible queries to the heap to choose the greatest one. Because the previous (index 0) has already been solved,
// => which one to choose (starting from 0 or 1) will not matter.
// Time complexity: O(n + qlogq), where q is the number of queries
// Space complexity: O(n + q)

class Solution {
    public int maxRemoval(int[] nums, int[][] queries) {
        /*
        // Sort the queries by l or q[0]
        // rights: max heap, store the rights of able to used queries 
        // decreased[i]: number of queries that un-effected at that index
        // curOps = 0

        // For each i:
            - Push all the queries able to use (starting from that i) to heap.
            - curOps -= decreased[i]
            - Then use the queries greedily, while curOps < nums[i] && rights.peek() >= i.
                - right = pop rights
                - curOps++
                - decreased[right + 1]++
            - if (curOps < nums[i]) return -1

        - return size of rights.
        */

        int n = nums.length;
        Arrays.sort(queries, (a, b) -> {
            // sort by left of each query
            return Integer.compare(a[0], b[0]);
        });

        PriorityQueue<Integer> rights = new PriorityQueue<>(Collections.reverseOrder()); // max heap
        int[] decreased = new int[n + 1]; // because we decreased[right + 1]
        int curOps = 0;

        for (int i = 0, q = 0; i < n; i++) {
            while (q < queries.length && queries[q][0] == i) {
                int[] query = queries[q];
                rights.offer(query[1]);
                q++;
            }

            curOps -= decreased[i];

            int required = nums[i];
            while (curOps < required && !rights.isEmpty() && rights.peek() >= i) {
                int right = rights.poll();
                decreased[right + 1]++;
                curOps++;
            }

            if (curOps < required) {
                return -1;
            }
        }
        return rights.size();
    }
}
