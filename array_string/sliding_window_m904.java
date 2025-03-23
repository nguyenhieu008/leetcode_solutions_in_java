// https://leetcode.com/problems/fruit-into-baskets/description/

// Solution: problem intepret: find the maximum subarray length that has at most 2 different integers.
// Use sliding window, the frequency map also use to get count of diff integers by freq.size()

// Time complexity: O(n)
// Space complexity: O(1);

class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        HashMap<Integer, Integer> freq = new HashMap<>();
        int l = 0, res = 0;

        for (int r = 0; r < n; r++) {
            freq.put(fruits[r], freq.getOrDefault(fruits[r], 0) + 1);

            while (freq.size() > 2) {
                freq.put(fruits[l], freq.get(fruits[l]) - 1);
                if (freq.get(fruits[l]) == 0) {
                    freq.remove(fruits[l]);
                }
                l++;
            }
            res = Math.max(res, r - l + 1);
        }

        return res;
    }
}
