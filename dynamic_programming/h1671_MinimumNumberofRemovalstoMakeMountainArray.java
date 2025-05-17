// https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/description/

// Solution 2: Using LIS with binary search. One trick here is to reverse the nums and the resulted lis to make it right->left.
// Time complexity: O(nlogn)
// Space complexity: O(n)

class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        // lisLeft[i] = longest increasing subsequency that ends at i, from left to right, not including i
        // lisRight[i] = longest increasing subsequency that ends at i, from right to left, not including i
        int[] lisLeft = buildLIS(nums);

        reverse(nums);
        int[] lisRight = buildLIS(nums);
        reverse(lisRight);

        int maxMountainLength = 0;
        for (int i = 0; i < n; i++) {
            // Only check if it's real mountain
            if (lisLeft[i] > 0 && lisRight[i] > 0) {
                maxMountainLength = Math.max(maxMountainLength, lisLeft[i] + lisRight[i] + 1);
            }   
        }
        return n - maxMountainLength;
    }

    // Build LIS for every i, not including i, default to 0
    private int[] buildLIS(int[] nums) {
        int n = nums.length;
        List<Integer> increasing = new ArrayList<>();
        int[] lis = new int[n];

        for (int i = 0; i < n; i++) { // to check i == 0
            int idx = indexGreater(increasing, nums[i]);
            if (idx == increasing.size()) {
                increasing.add(nums[i]);
            } else {
                increasing.set(idx, nums[i]);
            }
            lis[i] = idx;
        }
        return lis;
    }

    // find the first index i, so that value <= nums[i]
    //                                  value > nums[i - 1]
    private int indexGreater(List<Integer> increasing, int value) {
        int left = 0, right = increasing.size();
        while (right > left) {
            int mid = left + (right - left) / 2;

            if (value <= increasing.get(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // result can reach n, it means we need to add a new item because all items are smaller than value
        return left;
    }

    private void reverse(int[] a) {
        int n = a.length;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
}

// Solution 1: Using naive LIS dp approach. 
// Time complexity: O(n^2)
// Space complexity: O(n);

class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        // lisLeft[i] = longest increasing subsequency that ends at i, from left to right, not including i
        // lisRight[i] = longest increasing subsequency that ends at i, from right to left, not including i
        int[] lisLeft = new int[n];
        int[] lisRight = new int[n];

        for (int right = 1; right < n; right++) {
            for (int left = 0; left < right; left++) {
                if (nums[right] > nums[left]) {
                    lisLeft[right] = Math.max(lisLeft[right], lisLeft[left] + 1);
                }
            }
        }

        for (int left = n - 2; left >= 0; left--) {
            for (int right = n - 1; right > left; right--) {
                if (nums[left] > nums[right]) {
                    lisRight[left] = Math.max(lisRight[left], lisRight[right] + 1);
                }
            }
        }

        int maxMoutainLength = 0; // guarantee can make mountain
        // Actually, should start from 1 and ends at n - 2, but over-do it will not cause any problem
        for (int i = 0; i < n; i++) {
            // Only try to update if it's a mountain at i
            if (lisLeft[i] > 0 && lisRight[i] > 0) {
                maxMoutainLength = Math.max(maxMoutainLength, lisLeft[i] + lisRight[i] + 1);
            }
        }
        return n - maxMoutainLength;
    }
}
