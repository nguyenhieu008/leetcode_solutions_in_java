// https://leetcode.com/problems/minimum-size-subarray-sum/description/

// Solution 1: SLIDING WINDOW: expand the right until sum larger than target. Then shrink the window until smaller than target. Record the min of window while shrinking.
// In order to handle no subarray case, init result with n - 1 (so we can use Math.min), so we can easily check if no subarray found.
// return res % (n + 1); is also an option.

// Time complexity: O(n)
// Space complexity: O(1);

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int l = 0, sum = 0, res = n + 1;

        for (int r = 0; r < n; r++) {
            sum += nums[r];

            while (sum >= target) {
                res = Math.min(res, r - l + 1);
                sum -= nums[l];
                l++;
            }
        }
        return res == n + 1 ? 0 : res;
    }
}

/* Here is the explanation in leetcode which is useful

  Given that we only have positive integers, there is no purpose in adding further elements to a subarray if its sum exceeds or equals target. 
  Adding more elements to such a subarray will result in the construction of longer subarrays, 
  which is useless because we have already found a smaller subarray that meets our requirements.

Only if the sum of the current subarray under consideration is smaller than target, we should append elements to the subarray. 
When the sum of the subarrays exceeds or equals target, we will attempt to update our answer with the length of the current subarray.

We now try to remove the elements from the start and see if we can form a smaller subarray that meets our requirements. 
We remove the first element from the subarray and check if we still have the total higher than or equal to target. 
If the total exceeds or equals target, we have a smaller subarray that meets our requirement. 
As a result, we again try to update our answer with the length of the current subarray 
and repeat the process of eliminating the first element from the current subarray until the sum no longer exceeds or equals target.

Now after removing elements, if the sum of the subarray is less than target, 
we have to append more elements to it until the sum becomes larger than or equal to target. 
We append elements until the sum equals or exceeds target, then try to update our answer variable and repeat the process of eliminating the first element.

The above approach can be efficiently solved using the sliding window approach.
*/

// Solution 2: PREFIX SUM + TREE , we can use hash table to store prefix sum, but we need to find the greatest one that is smaller than the (sum - target)
// So we need to store the prefix sum in a tree, so we can query them with O(logn)
// Remember to handle null case, when there is no entry that is smaller than (sum - target) (in case sum too small compared to target)

// Time complexity: O(nlogn)
// Space complexity: O(n);

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        TreeMap<Integer, Integer> prefixSum = new TreeMap<>(Map.of(0, -1));
        int sum = 0, res = n + 1;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            Integer found = prefixSum.floorKey(sum - target);
            if (found != null) {
                res = Math.min(res, i - prefixSum.get(found));
            }
            prefixSum.put(sum, i);  
        }

        return res % (n + 1);
    }
}
