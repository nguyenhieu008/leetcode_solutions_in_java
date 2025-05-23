// https://leetcode.com/problems/find-the-maximum-sum-of-node-values/description/

// KEY OBSERVATION: With the XOR properties of commutative and associative, xor 3 times = xor 1 times, and xor 2 times = no xor.
// For a tree: connected, acyclic graph - There always have path between 2 nodes u-v
// If we xor all edges from u -> n1 -> n2 -> ... -> v => all intermediate nodes are xor 2 times = no xor. u and v xored 1 times => toggled.
// => We can freely choose which pair to xor, just need to make sure number of xored nodes is even.
// Reference: https://leetcode.com/problems/find-the-maximum-sum-of-node-values/editorial/

// Solution 1b: bottom-up DP with memory optimized
class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        int n = nums.length;
        long evenOpsSum = 0, oddOpsSum = Integer.MIN_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            // current even
            //  - xor and next odd
            //  - not xor and next even
            long xorEven = (nums[i] ^ k) + oddOpsSum;
            long notXorEven = nums[i] + evenOpsSum;
            long curEvenOpsSum = Math.max(xorEven, notXorEven);

            // current odd ops
            //  - xor and previous even
            //  - not xor and previous odd
            long xorOdd = (nums[i] ^ k) + evenOpsSum;
            long notXorOdd = nums[i] + oddOpsSum;
            long curOddOpsSum = Math.max(xorOdd, notXorOdd);

            evenOpsSum = curEvenOpsSum;
            oddOpsSum = curOddOpsSum;
        }

        
        return evenOpsSum;
    }
}

// Solution 1a: Bottom-up - tabulation DP:
// Notice that for the first root node, there is no ops applied => result will be the dp for root node with even xored node.
class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        int n = nums.length;
        long[][] dp = new long[n][2];
        dp[0][1] = nums[0];         // even ops => not xor
        dp[0][0] = nums[0] ^ k;     // odd ops => xor

        for (int i = 1; i < n; i++) {
            // current odd ops
            //  - xor and previous even
            //  - not xor and previous odd
            dp[i][0] = Math.max(dp[i-1][0] + nums[i], dp[i-1][1] + (nums[i] ^ k));

            // current even
            //  - xor and previous odd
            //  - not xor and previous even
            dp[i][1] = Math.max(dp[i-1][0] + (nums[i] ^ k), dp[i-1][1] + nums[i]);
        }

        // for (int i = 0; i < n; i++) {
        //     System.out.println("odd: " + dp[i][0] + ", even: " + dp[i][1]);
        // }
        // first one even
        return dp[n-1][1];
    }
}

// Solution 1: Top-down DP and memoization. Make sure when we reach the end, it's even number of xored node. Otherwise, return -infinity.
class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        int n = nums.length;
        long[][] memo = new long[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return maxSumOfNodes(nums, 0, 1, k, memo);
    }

    private long maxSumOfNodes (int[] nums, int index, int isEven, int k, long[][] memo) {
        if (index == nums.length) {
            if (isEven == 1) {
                return 0;
            }
            return Integer.MIN_VALUE;
        }

        if (memo[index][isEven] != -1) {
            return memo[index][isEven];
        }

        long notApplyXor = nums[index] + maxSumOfNodes(nums, index + 1, isEven, k, memo);
        long applyXor = (nums[index] ^ k) + maxSumOfNodes(nums, index + 1, isEven ^ 1, k, memo);

        memo[index][isEven] = Math.max(notApplyXor, applyXor);
        return memo[index][isEven];
    }
}

// Solution 2a: Greedy, sort.
// We always try to get a pair that maximize the gain => Sort the gain array and take pairs till not gain anything
class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        int n = nums.length;
        int[] gain = new int[n];
        long total = 0;

        for (int i = 0; i < n; i++) {
            total += nums[i];
            gain[i] = (nums[i] ^ k) - nums[i];
        }

        Arrays.sort(gain);

        for (int i = n - 1; i - 1 >= 0; i -= 2) {
            int pairGain = gain[i] + gain[i - 1];
            if (pairGain <= 0) {
                break;
            }
            total += pairGain;
        }
        return total;
    }
}

// Solution 2b: Greedy. Always add positive gain and store minPositive, maxNegative to handle the case of odd pair.
class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        int n = nums.length;
        int minPositive = Integer.MAX_VALUE, maxNegative = Integer.MIN_VALUE; // to check init value
        long total = 0;
        int numGain = 0;

        for (int v : nums) {
            total += v;
            int gain = (v ^ k) - v;
            if (gain > 0) {
                total += gain;
                numGain++;
                minPositive = Math.min(minPositive, gain);
            } else {
                maxNegative = Math.max(maxNegative, gain);
            }
        }

        if (numGain % 2 == 0) {
            return total;
        }

        if (minPositive + maxNegative >= 0) {
            total += maxNegative;
        } else {
            total -= minPositive;
        }
        return total;
    }
}
