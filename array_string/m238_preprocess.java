// https://leetcode.com/problems/product-of-array-except-self/submissions/

// Solution 1: Same as 1a, but we only keep 1 array for result. For the right-side product, we update the result array in-place, from right to left
// Time complexity: O(n)
// Space complexity: O(1), do not count the result array.

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i-1] * nums[i-1];
        }
        
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= rightProduct;
            rightProduct *= nums[i];
        }
        return res;
    }
}

// Solution 1a: res[i] = product to left * product to the right
// we preprocess by making two leftProduct, rightProduct array where item at i is the product of the two sides, without nums[i].
// Time complexity: O(n)
// Space complexity: O(n)

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] leftProduct = new int[n];
        int[] rightProduct = new int[n];

        leftProduct[0] = 1;
        for (int i = 1; i < n; i++) {
            leftProduct[i] = leftProduct[i-1] * nums[i-1];
        }

        rightProduct[n-1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            rightProduct[i] = rightProduct[i+1] * nums[i+1];
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = leftProduct[i] * rightProduct[i];
        }
        return res;
    }
}
