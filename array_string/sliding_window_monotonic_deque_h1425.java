// https://leetcode.com/problems/constrained-subsequence-sum/description/

// Solution: 
// We for sure want to take all positive integer, but they maybe gapped by negative ones.
// So we need to know that the maxSum at i (from the beginning), to know whether we should extend the subarray from i. (if the maxSum at i < 0 => we should not)
// In order to know the maxSum of all possible windows ends at i, we need to know the max of sums inside the window (i - k, i) => Need to get it with smallest complexity
// => We should use MONOTONIC DECREASING double-ended queue, why decreasing
//    - decreasing so we can always get the largest one from top of queue with O(1)
//    - if the later one having greater value, we surely should use the later one => the former + smaller value is no use => should be removed from queue
//    => the queue should be decreasing 
// We also need to keep the index of the maxValue to remove the ones that are outside of the window
// We update res at each i, using res = Math.max(res, maxAtI) as the result will be the largest sum all over the array.
// One more approach is using another array dp[] to store the maxValue, and keep only the index in the queue => easier to implement, but O(n) space complexity.

// Time complexity: O(n)
// Space complexity: O(k)

import java.util.AbstractMap;

class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int res = Integer.MIN_VALUE;
        Deque<Map.Entry<Integer, Integer>> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (!deque.isEmpty() && deque.peekFirst().getValue() < i - k) {
                deque.poll();
            }
            int maxAtI = nums[i];
            if (!deque.isEmpty()) {
                maxAtI = Math.max(maxAtI, maxAtI + deque.peek().getKey());
            }
            while (!deque.isEmpty() && deque.peekLast().getKey() < maxAtI) {
                deque.pollLast();
            }
            deque.offer(new AbstractMap.SimpleEntry<>(maxAtI, i));
            res = Math.max(res, maxAtI);
        }
        return res;
    }
}
