// TODO: this is a DP solution and similar to https://leetcode.com/problems/longest-increasing-subsequence/description/ , or LIS - Longest Increasing Subsequence

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // Find the largest set
        // Whereas, for every pair, 1 element is multiple of the other.
        // output: any list that satisfies (if multiple)
        // input: length = 1000 => O(n^2* logn) still okay. length >= 1 => no empty result
        // UNIQUE numbers.

        // Brute-force: for every i, find the paired element and add to its lists
        // Should mark the one chosen => no duplicate list
        // e.g: 3 9 6 12
        // result = [3, 6, 12]
        // What if 3 and 9 already grouped
        // => when go to 6, we need to reexamine 3 => no need chosen
        // WRONG direction, for [5,9,18,54,108,540,90,180,360,720]
        // output is [720,5,90,180,360]
        // where the expected is [9,18,90,180,360,720]

        // Thinking: this is like a graph traversal problem
        // Where the result is a subgraph, within it, every node is connected to all other nodes.

        // int n = nums.length;
        // int maxSize = 0;
        // List<Integer> result = null;

        // for (int i = 0; i < n; i++) {
        //     List<Integer> subset = new ArrayList<>(Arrays.asList(nums[i]));
        //     for (int j = 0; j < n && j != i; j++) {
        //         boolean isValid = true;
        //         for (int v : subset) {
        //             if (v % nums[j] != 0 && nums[j] % v != 0) {
        //                 isValid = false;
        //                 break;
        //             }
        //         }
        //         if (isValid) {
        //             subset.add(nums[j]);
        //         }
        //     }
        //     if (subset.size() > maxSize) {
        //         maxSize = subset.size();
        //         result = subset;
        //     }
        // }
        // return result;

        int n = nums.length;
        Arrays.sort(nums);
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int maxSizeIdx = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0 || nums[j] % nums[i] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (dp[i] > dp[maxSizeIdx]) {
                        maxSizeIdx = i;
                    }
                }
            }
        }


        List<Integer> result = new ArrayList<>();
        result.add(nums[maxSizeIdx]);
        for (int i = maxSizeIdx - 1, prev = maxSizeIdx; i >= 0; i--) {
            if (nums[prev] % nums[i] == 0 && dp[i] == dp[prev] - 1) {
                result.add(nums[i]);
                prev = i;
            }
        }
        return result;
    }
}
