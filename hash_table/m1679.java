// https://leetcode.com/problems/max-number-of-k-sum-pairs/solutions/

class Solution {
    public int maxOperations(int[] nums, int k) {
        HashMap<Integer, Integer> count = new HashMap<>();

        int res = 0;
        for (int v : nums) {
            int c = count.getOrDefault(k - v, 0);
            if (c > 0) {
                // Check if (k - v) exists before v, if so, "remove them from the array"
                res++;
                count.put(k - v, c - 1);
            } else {
                // Add v to hash map so it can match with later numbers
                int cv = count.getOrDefault(v, 0);
                count.put(v, cv + 1);
            }
        }
        return res;
    }
}
