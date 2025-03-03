// https://leetcode.com/problems/sum-of-mutated-array-closest-to-target/

// Solution: the result will be need to be in the range of (0, max] (inclusive at max). If sum < target => every change will make the diff larger => result will be the max.
// Otherwise, do usual binary search can call diff for each value. We let r = mid if d >= 0 => we try to align the result to r => when if-else to find result, we try "r" and "r-1"
// Because the right of r always be worse result.

// Time complexity: O(nlogn)
// Space complexity: O(1)

class Solution {
    public int findBestValue(int[] arr, int target) {
        int n = arr.length;
        int sum = 0, max = 0;
        for (int v : arr) {
            sum += v;
            max = Math.max(max, v);
        }

        if (sum <= target) return max;

        int l = 0, r = max;
        while (r - l > 0) {
            int mid = l + (r - l) / 2;
            int d = calDiff(arr, mid, target);
            System.out.println("mid = " + mid + " d = " + d);

            if (d >= 0) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        if (Math.abs(calDiff(arr, r, target)) < Math.abs(calDiff(arr, r - 1, target))) {
            return r;
        } else {
            return r - 1;
        }
    }

    int calDiff(int[] arr, int value, int target) {
        int sum = 0;
        for (int v : arr) {
            sum += v > value ? value : v;
        }
        return sum - target;
    }
}

// Solution 2: just for reference, copied from leetcode
// Sort ascending then try the value from left to right, if a[i] is not the value, we can remove it from the target and go on.
// Although this solution is interesting, it's not recommended when interview as it too difficult to reasoning.

public int findBestValue(int[] A, int target) {
    Arrays.sort(A);
    int n = A.length, i = 0;
    while (i < n && target > A[i] * (n - i)) {
        target -= A[i++];
    }
    if (i == n) return A[n - 1];
    int res = target / (n - i);
    if (target - res * (n - i) > (res + 1) * (n - i) - target)
        res++;
    return res;
}
