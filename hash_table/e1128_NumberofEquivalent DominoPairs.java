// https://leetcode.com/problems/number-of-equivalent-domino-pairs/description/

// We can keep track of seen dominoes using a hash map.
// Time complexity: O(n)
// Space complexity: O(n)

class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] frequency = new int[100]; // there will be some slot not used

        // The hash of a domino is:
        // 10 * min num + max num

        int res = 0;
        for (int[] domino : dominoes) {
            int a = domino[0], b = domino[1];
            int min = Math.min(a, b);
            int max = Math.max(a, b);
            int key = 10 * min + max;

            res += frequency[key];
            frequency[key]++;
        }
        return res;
    }
}
