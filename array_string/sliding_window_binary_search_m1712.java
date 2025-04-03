// https://leetcode.com/problems/ways-to-split-array-into-three-subarrays/description/

// Solution 2: sliding window, Call i = last item of left window, [j, k) (k exclusive) is the selectable range of last item for mid window
// => j and k are montonically increasing
// => We can for all possible solution with 3 loops (not nested) for i, j, k
// Time complexity: O(n)
// Space complexity: O(n) for prefix sum

class Solution {
    public int waysToSplit(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i-1] + nums[i];
        }

        int res = 0;
        int MOD = (int)1e9 + 7;
        int j = 1, k = 2;

        for (int i = 0; i < n - 2; i++) {
            int leftSum = prefix[i];

            // j is the first one that makes midSum >= leftSum
            // Notice that we need the condition (j <= i), in case midSum == leftSum then j does not increase.
            while (j <= i || (j < n - 1 && prefix[j] - prefix[i] < leftSum)) {
                j++;
            }

            // k is the first one that makes midSum > rightSum => [j, k) (k exclusive) is the selectable range.
            // Notice that we need condition (k < j), because k and j are controlled by 2 different loops and sometimes k may less than j => add negative to result => wrong.
            while (k < j || (k < n - 1 && prefix[k] - prefix[i] <= prefix[n - 1] - prefix[k])) {
                k++;
            }

            res = (res + k - j) % MOD;
        }
        return res;
    }
}

// Solution 1: Binary search. It's better to split the code into 2 other functions to find the left/right index range.
// Time complexity: O(nlogn)
// Space complexity: O(n) for prefix sum.

class Solution {
    public int waysToSplit(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i-1] + nums[i];
        }

        int res = 0;
        int MOD = (int)1e9 + 7;
        for (int i = 0; i < n - 2; i++) {
            
            int to = -1;
            int l = i + 1, r = n - 2;
            while (r - l >= 0) {
                int mid = l + (r - l) / 2;

                int sumMid = prefix[mid] - prefix[i];
                int sumRight = prefix[n - 1] - prefix[mid];

                if (sumMid <= sumRight) {
                    to = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            if (to == -1) continue;

            l = i + 1; r = n - 2;

            int sumLeft = prefix[i];
            int from = -1;
            while (r - l >= 0) {
                int mid = l + (r - l) / 2;

                int sumMid = prefix[mid] - prefix[i];

                if (sumMid >= sumLeft) {
                    from = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

            if (from == -1) continue;
            // System.out.println("nums[" + i + "] = " + nums[i] + ", nums[" + from + "] = " + nums[from]+  ", nums[" + to + "] = " + nums[to]);
            // System.out.format("(Case from = %d) leftSum = %d, midSum = %d, rightSum = %d\n", from, sumLeft, prefix[from] - prefix[i], prefix[n-1] - prefix[from]);
            // System.out.format("(Case to = %d) leftSum = %d, midSum = %d, rightSum = %d\n", to, sumLeft, prefix[to] - prefix[i], prefix[n-1] - prefix[to]);

            if (from > to) {
                // System.out.println("hereeee");
                continue;
            } else {
                res = (res + (to - from + 1)) % MOD;
            }
        }
        return res;
    }
}
