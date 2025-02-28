// https://leetcode.com/problems/3sum-with-multiplicity/description/

// Solution 2: because the range of arr[i] is 0 -> 100, we try counting sort for better complexity
// for i in 0 -> 100, j in i -> 100 => find k, there are 3 possible cases:
//    - i = j = k => combinations of 3 over c[i] = c[i]! / ((c[i] - 3)! * 3!) = c[i] * (c[i] - 1) * (c[i] - 2) / 6
//    - i = j != k => Beware that k is allowed to less than i, in order not to miss case of (a (least) - b and c equals), this case, k = a, and i, j = b,c
//    - i < j < k => Beware that j must != k, because that case is covered by previous condition
//                               j must not > k, because it will generate some dup, e.g. i < k < j, or k < i < j
// Time complexity: O(n + 101 * 101)
// Space complexity: O(101)

class Solution {
    public int threeSumMulti(int[] arr, int target) {
        long[] c = new long[101];

        for (int v : arr) {
            c[v]++;
        }

        long res = 0;
        final int MOD = 1_000_000_007;
        for (int i = 0; i <= 100; i++) {
            for (int j = i; j <= 100; j++) {
                int k = target - i - j;

                if (k < 0 || k > 100) continue;

                if (i == j && j == k) {
                    res += c[i] * (c[i] - 1) * (c[i] - 2) / 6;
                } else if (i == j && j != k) {
                    res += c[i] * (c[i] - 1) / 2 * c[k];
                } else if (j < k) {
                    res += c[i] * c[j] * c[k];
                }
                res %= MOD;
            }
        }
        return (int)res;
    }
}

// Solution 1: at each item called j, we look how many previous 2-items that sums to (target - arr[j]) using hash map.
// We build hash map by, still at j, for all i from 0 -> j, add arr[i] + arr[j] to hash map.
// Because i < j => no duplicates. And make sure we look up first, before we add item to map => no dup.

// Time complexity: O(n ^ 2) = O(3000 * 3000)
// Space complexity: O(n ^ 2)

class Solution {
    public int threeSumMulti(int[] arr, int target) {
        final int MOD = (int)1e9 + 7;
        int n = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();

        int res = 0;
        for (int j = 1; j < n; j++) {
            int v = arr[j];
            res = (res + map.getOrDefault(target - v, 0)) % MOD;

            for (int i = 0; i < j; i++) {
                map.put(arr[i] + v, map.getOrDefault(arr[i] + v, 0) + 1);
            }
        }

        return res;
    }
}
