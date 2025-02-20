// https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/

// Use dynamic programming approach. We maintain a hash table to store the longest subsequence length for each value
// For each value v, we find in the hash table the longest of (v - difference) then update the longest of v accordingly.
// Because the values always get larger over time, we do not need to use Math.max each time we update the hash table.

class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        int n = arr.length;
        HashMap<Integer, Integer> longest = new HashMap<>();


        // i        v        v-diff     longest[v-diff]             longest[v]      result
        // 0        1         0                                         {1, 1}          1
        // 1        2         1             1                           {2, 2}          2
        int result = 0;
        for (int i = 0; i < n; i++) {
            int v = arr[i];

            if (longest.containsKey(v - difference)) {
                int cur = longest.get(v - difference);
                longest.put(v,  cur + 1);
            } else {
                longest.put(v, 1);
            }

            result = Math.max(result, longest.get(v));
        }
        return result;
    }
}
