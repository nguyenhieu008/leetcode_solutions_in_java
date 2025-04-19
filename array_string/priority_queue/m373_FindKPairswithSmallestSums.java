// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/description/

// Solution 2: reference: https://leetcode.com/problems/find-k-pairs-with-smallest-sums/solutions/84551/simple-java-o-klogk-solution-with-explanation/
// We add all items from (nums1+nums2[0]) first => Then, next time when we need to add item to min heap, we only need to increment the index in nums2.
// => very intelligent idea (we can apply this later: 
//        - Co dinh 1 chieu (nums1)
//        - Di chuyen chieu con lai (nums2))
// Time complexity: O(klogk), because the min heap always have size k at most;
// Space complexity: O(k)

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0] + a[1], b[0] + b[1]));
        
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            minHeap.offer(new int[] {nums1[i], nums2[0], 0}); 
        }
        
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            result.add(Arrays.asList(curr[0], curr[1]));  
            
            if (curr[2] + 1 < nums2.length) {
                minHeap.offer(new int[] {curr[0], nums2[curr[2] + 1], curr[2] + 1});
            }
        }
        return result;
    }
}

// Solution 1: self-done, reference: https://leetcode.com/problems/find-k-pairs-with-smallest-sums/solutions/3399718/find-k-pairs-with-smallest-sums/
// Because the original arrays are sorted, we only add adjacent items to the min heap (by the sum of items);
// Some tricks to implement easier:
//  - Store the sum along with the idx
//  - Store the visited set by string
// Time complexity: O(min(klogk, m*n*log(m*n))
// Space complexity: O(min(k, m*n))
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        // int[3], with [0] = total, [1] = idx1, [2] = idx2
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            return Integer.compare(a[0], b[0]);
        });
        minHeap.offer(new int[]{nums1[0] + nums2[0], 0, 0});

        int i = 0;
        List<List<Integer>> res = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        visited.add("0_0");

        while (i < k && !minHeap.isEmpty()) {
            int[] smallestPair = minHeap.poll();
            int idx1 = smallestPair[1], idx2 = smallestPair[2];

            res.add(Arrays.asList(nums1[idx1], nums2[idx2]));
            i++;

            if (idx1 + 1 < m) {
                String key = (idx1 + 1) + "_" + idx2;
                if (!visited.contains(key)) {
                    minHeap.offer(new int[]{nums1[idx1+1] + nums2[idx2], idx1 + 1, idx2});
                    visited.add(key);
                }
            } 

            if (idx2 + 1 < n) {
                String key = idx1 + "_" + (idx2 + 1);
                if (!visited.contains(key)) {
                    minHeap.offer(new int[]{nums1[idx1] + nums2[idx2+1], idx1, idx2 + 1});
                    visited.add(key);
                }
            } 
        }

        return res;
    }
}
