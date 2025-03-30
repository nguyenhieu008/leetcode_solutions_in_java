// https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/

// Solution 3: Based on solution 1 & 2.
// Our purpose is for index j, we need largest index i the has the prefix[j] - prefix[i] >= target.
// Observation 1: for i1 < i2, if prefix[i2] <= prefix[i1] => i1 will always NOT BE the possible solution, because the if we choose i2, 
// we can have a larger (prefix[j] - prefix[i2]) and also shorter => we can maintain a monotonic increased stack of prefix sums => we add item to the end of deque.
// Observation 2: If j is the first item choosing i2 as candidate subarray, the latter subarray (i2, j2) won't be possible because of longer length.
// => we can remove the index from startig of the deque, if it has been chosen for the candicate subarray

// Time complexity: O(n)
// Space complexity: O(n)

class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefixSums = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSums[i] = prefixSums[i-1] + nums[i-1];
        }

        int res = Integer.MAX_VALUE;
        Deque<Integer> monotonicDq = new ArrayDeque<>();

        for (int i = 0; i <= n; i++) {
            while (!monotonicDq.isEmpty() && prefixSums[monotonicDq.peekFirst()] <= prefixSums[i] - k) {
                res = Math.min(res, i - monotonicDq.removeFirst());
            }

            while (!monotonicDq.isEmpty() && prefixSums[monotonicDq.peekLast()] >= prefixSums[i]) {
                monotonicDq.removeLast();
            }
            monotonicDq.offerLast(i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}

// Solution 2: We maintain the monotonic increasing stack as new latter smaller one will discard all previous larger prefix sum as it won't produce better result.
// So for the monotonic increasing stack, at each i, we can find the one that is the greatest one smaller or equal to (sum - target), using binary search.
// Time complexity: O(nlog(n))
// Space complexity: O(n)
class Solution {
    private static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        List<Pair<Long, Integer>> prefixSumStack = new ArrayList<>();
        prefixSumStack.add(new Pair(0L, -1));

        long sum = 0;
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            sum += nums[i];

            while (!prefixSumStack.isEmpty() 
                && prefixSumStack.get(prefixSumStack.size() - 1).getKey() >= sum) {
                prefixSumStack.remove(prefixSumStack.size() - 1);
            }

            prefixSumStack.add(new Pair(sum, i));

            int candidateIdx = binarySearch(prefixSumStack, sum - k);
            if (candidateIdx != -1) {
                res = Math.min(res, i - prefixSumStack.get(candidateIdx).getValue());
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int binarySearch(List<Pair<Long, Integer>> a, long target) {
        int l = 0, r = a.size() - 1;

        while (r - l >= 0) {
            int mid = l + (r - l) / 2;

            if (a.get(mid).getKey() <= target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }
}

// Solution 1: For each i, we can find the previous sums that is smaller than (sum - target) and calculate the result.
// But because for them, the latter index i2 will always produce worse result => we can remove the found index from the set of prefix sum
// => we will use a priority queue to choose the smallest prefix sums and remove it when we find the possible target.

// Time complexity: O(nlogn)
// Space complexity: O(n)
class Solution {
    private static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Pair<Long, Integer>> pq = new PriorityQueue<>((a, b) -> {
            return (a.getKey() - b.getKey()) > 0 ? 1 : (a.getKey() - b.getKey()) < 0 ? -1 : 0;
        });
        pq.offer(new Pair(0L, -1));

        long sum = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            sum += nums[i];

            while (!pq.isEmpty() && (sum - pq.peek().getKey() >= k)) {
                res = Math.min(res, i - pq.poll().getValue());
            }
            pq.offer(new Pair(sum, i));
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
