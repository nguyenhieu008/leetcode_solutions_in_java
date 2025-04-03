// https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-ii/description/

// Solution 1: real thinking process
class Solution {
    public long maximumTripletValue(int[] nums) {
        // max value
        // value = (nums[i] - nums[j]) * nums[k]), i < j < k
        // res = Math.max(0, all possible values)
        // nums[i] range? 1 -> 1e6 => positive only
        // 1e6 * 1e6 = 1e16 => need long
        // length = 1e5 => O(n^2) not possible

        // How about generate all triplet? => O(n^3), lets try
        // This works and passed example 

        int n = nums.length;
        long res = 0;

        // for (int i = 0; i < n - 2; i++) {
        //     for (int j = i + 1; j < n - 1; j++) {
        //         for (int k = j + 1; k < n; k++) {
        //             long value = (long)(nums[i] - nums[j]) * nums[k];
        //             res = Math.max(res, value);
        //         }
        //     }
        // }

        // How to optimize?
        // for each k, we only care about max (nums[i] - nums[j])
        // => for each j, can we do it with O(1)? yes, use a max value of passed values
        // => O(n^2), lets try
        // TLE: as expected, O(n^2) won't work
        
        // int max = nums[0];
        // for (int j = 1; j < n - 1; j++) {
        //     for (int k = j + 1; k < n; k++) {
        //         long value = (long)(max - nums[j]) * nums[k];
        //         res = Math.max(res, value);
        //         max = Math.max(max, nums[j]);
        //     }
        // }

        // How to optimize?
        // each k, need max(nums[i] - nums[j]), i > j
        // => no need to store max, but need to store max(i-j)
        // We update with k loop, after calculated value, we also update 
        //  max = Math.max(max, nums[k-1])
        //  maxDiff = Math.max(maxDiff, max - nums[k]) 
        // => O(n), lets try

        int max = nums[0];
        int maxDiff = nums[0] - nums[1];

        for (int k = 2; k < n; k++) {
            res = Math.max(res, (long)maxDiff * nums[k]);
            // i must at most k-1 => it works for next round (i = k-1, j = k, k+1)
            max = Math.max(max, nums[k-1]);
            // update whether maxDiff can ends with j = k
            maxDiff = Math.max(maxDiff, max - nums[k]);
        }

        return res;
    }
}

// Solution 2: reference: https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-ii/solutions/6586318/maximum-value-of-an-ordered-triplet-ii/
public class Solution {

    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], nums[i - 1]);
            rightMax[n - 1 - i] = Math.max(rightMax[n - i], nums[n - i]);
        }
        long res = 0;
        for (int j = 1; j < n - 1; j++) {
            res = Math.max(res, (long) (leftMax[j] - nums[j]) * rightMax[j]);
        }
        return res;
    }
}
