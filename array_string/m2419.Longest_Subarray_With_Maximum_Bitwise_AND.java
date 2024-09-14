// https://leetcode.com/problems/longest-subarray-with-maximum-bitwise-and/description/?envType=daily-question&envId=2024-09-14

/* Solution 1: 1 pass
- When bitwise AND 2 number, the maximum number received is the greater one. 
- So, for a subarray, the maximum subarray is the longest subarray with all maximum element.
- So, we need to find the longest subarray that contains contiguous max element of the whole array, either in 1 pass or 2 pass.
- 1 pass is easier to read.
- Time complexity: O(n)
- Space complexity: O(1)
*/

class Solution {
    public int longestSubarray(int[] nums) {
        int max = 0;
        int count = 0;
        int res = 1;

        for (int num : nums) {
            if (num > max) {
                max = num;
                count = 1;
                res = 1;
            } else if (num == max) {
                count++;
                res = Math.max(res, count);
            } else {
                count = 0;
            }
        }
        return res;
    }
}

/* Solution 2: same as solution 1 but using 2 pass */

class Solution {
    public int longestSubarray(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        int res = 0;
        int count = 0;
        for (int num: nums) {
            if (num == max) {
                res = Math.max(res, ++count);
            } else {
                count = 0;
            }
        }
        return res;
    }
}

/*
You are given an integer array nums of size n.

Consider a non-empty subarray from nums that has the maximum possible bitwise AND.

In other words, let k be the maximum value of the bitwise AND of any subarray of nums. Then, only subarrays with a bitwise AND equal to k should be considered.
Return the length of the longest such subarray.

The bitwise AND of an array is the bitwise AND of all the numbers in it.

A subarray is a contiguous sequence of elements within an array.

 

Example 1:

Input: nums = [1,2,3,3,2,2]
Output: 2
Explanation:
The maximum possible bitwise AND of a subarray is 3.
The longest subarray with that value is [3,3], so we return 2.
Example 2:

Input: nums = [1,2,3,4]
Output: 1
Explanation:
The maximum possible bitwise AND of a subarray is 4.
The longest subarray with that value is [4], so we return 1.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 106
*/
