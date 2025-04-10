// Solution 2: There are 2 cases:
// - The max subarray is within the ordinary array => simply Kanane solution
// - The max subarray is wrapped around the array => it's: (total sum) - (minimum sum), where the minimum sum is within the ordinary array
//     => Along with Kadane solution, we also use Kadane with minimum sum, then the max possible "wrapped around sum" will be (total - minimum)
// - Then return the maximum between 2 cases.
// - One edge case where all items are negative => max sum < 0, (total - minimum sum) = 0 => we need to detect when total == minimum, we return the maxSum right away.
// - Time complexity: O(n)
// - Space complexity: O(1)

class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int curMax = 0;
        int maxSum = nums[0];
        int curMin = 0;
        int minSum = nums[0];
        int total = 0;

        for (int v : nums) {
            curMax = Math.max(0, curMax) + v;
            maxSum = Math.max(maxSum, curMax);

            curMin = Math.min(0, curMin) + v;
            minSum = Math.min(minSum, curMin);

            total += v;
        }

        if (total == minSum) {
            return maxSum;
        }
        return Math.max(maxSum, total - minSum);
    }
}

// Solution 1: Detail in comments:
// rightMax[i] is the maximum suffix at or after i => it can combines with prefixSum at (i - 1) to know the maxixum sum that wrap around the array.
// Time complexity: O(n)
// Space complexity: O(n)

class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        
        // Largest suffix sum that comes on or after i
        int[] rightMax = new int[n];
        rightMax[n-1] = nums[n-1];
        int suffixSum = nums[n-1];

        for (int i = n - 2; i >= 0; i--) {
            suffixSum += nums[i];
            // rightMax[i] is the maximum suffix at or after i => it can combines with prefixSum at (i - 1) to know the maxixum sum that wrap around the array.
            rightMax[i] = Math.max(rightMax[i+1], suffixSum);
        }

        int prefixSum = 0;
        // In case there is only one item, specialMax may not be updated => set it to nums[0] for safety
        // But it can be set to Integer.MIN_VALUE as well, because it can be overriden by normalMax.
        int specialMax = nums[0]; 

        // Because curMax = 0, we should update curMax first, before use it to calculate normalMax
        int curMax = 0;
        int normalMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // this is Kadane's algorithm
            curMax = Math.max(curMax, 0) + nums[i];
            normalMax = Math.max(normalMax, curMax);

            if (i + 1 < n) {
                prefixSum = prefixSum + nums[i];
                specialMax = Math.max(specialMax, prefixSum + rightMax[i + 1]);
            }
        }
        return Math.max(normalMax, specialMax);
    }
}
