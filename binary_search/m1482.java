// https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/

// Solution: If (m * k) > n => we can always have result, just be careful that (m * k) can exceed integer.
// In order to make a bouquet, we can use greedy approach - use a first flower we see to make the bouquet if possible, because it guarantees the maximum number of bouquets.
// Usual binary search, because the result always exists and if canMake => r = mid, so, we just return r. Notice that, after the while, l == r always.

// Time complexity: O(n * log(INT_MAX))
// Space complexity: O(1);

class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if ((long)m * k > n) {
            return -1;
        }

        int l = 0, r = Integer.MAX_VALUE;
        while (r - l > 0) {
            int mid = l + (r - l) / 2;
            if (canMake(bloomDay, mid, m, k)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

//         if (l != r) return 1275;  // Just fun condition to make sure l always == r
        // return l;
        return r;
    }

    boolean canMake(int[] a, int day, int m, int k) {
        int n = a.length;
        int cur = 0;
        int total = 0;

        for (int i = 0; i < n; i++) {
            if (a[i] <= day) {
                cur++;
                if (cur == k) {
                    cur = 0;
                    total++;
                }
            } else {
                cur = 0;
            }
        }
        return total >= m;
    }
}
