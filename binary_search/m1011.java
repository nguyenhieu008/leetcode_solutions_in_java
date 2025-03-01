// https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/

// List of similar problems:

// 1482. Minimum Number of Days to Make m Bouquets
// 1283. Find the Smallest Divisor Given a Threshold
// 1231. Divide Chocolate
// 1011. Capacity To Ship Packages In N Days
// 875. Koko Eating Bananas
// 774. Minimize Max Distance to Gas Station
// 410. Split Array Largest Sum

// Solution: If capacity is increased -> days need to ship is decreased => f(cap) is monotonically decreased => we can use binary to find the mid point between able/not-able to ship.
// Be careful, the left of search should be the maximum weight. If not, we can get wrong result when cap < weight. Right of search can be Integer.MAX_VALUE as well
// Time complexity: O(n * log(sum of weight))
// Space complexity: O(1)

class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int n = weights.length;
        int maxw = 0;
        int sum = 0;
        for (int w : weights) {
            maxw = Math.max(w, maxw);
            sum += w;
        }

        int l = maxw, r = sum;

        while (r - l > 0) {
            int mid = l + (r - l) / 2;

            if (calNeededDays(weights, mid) <= days) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }

    int calNeededDays(int[] weights, int cap) {
        int days = 0;
        int load = 0;
        for (int w : weights) {
            if (load + w > cap) {
                days++;
                load = w;
            } else {
                load += w;
            }
        }
        return days + 1;
    }
}
