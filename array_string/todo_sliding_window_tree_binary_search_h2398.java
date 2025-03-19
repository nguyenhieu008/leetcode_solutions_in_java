// https://leetcode.com/problems/maximum-number-of-robots-within-budget/description/

// Solution 1: sliding window with tree to detect max value in a window, and also allow removing item from the window
class Solution {
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int n = chargeTimes.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        long sum = 0;

        int l = 0, res = 0;
        for (int r = 0; r < n; r++) {
            sum += runningCosts[r];
            map.put(chargeTimes[r], map.getOrDefault(chargeTimes[r], 0) + 1);

            while (l <= r && map.lastKey() + (r - l + 1) * sum > budget) {
                int key = chargeTimes[l];
                if (map.get(key) == 1) {
                    map.remove(key);
                } else {
                    map.put(key, map.get(key) - 1);
                }
                sum -= runningCosts[l];
                l++;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
