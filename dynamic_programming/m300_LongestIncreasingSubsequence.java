// https://leetcode.com/problems/longest-increasing-subsequence/description/

// Solution 2: Detailed thought process and the refinement in the comment.
// We store an increasing stack, where the actual increasing value is the LIS (the index of the stack, 0-1-2-...), stack[0] = smallest number on the left that make the LIS of 1, and so on.
// But it also yields increasing in the number[j] itself (if we found a smaller number with same lis => update to smaller value)
// Time complexity: O(n * logn)
// Space complexity: O(n)
class Solution {
    public int lengthOfLIS(int[] nums) {
        // 1 80 90 100 2 110 3 4 5 6
        // stack of [nums[a], lis[a]]
        // Shoud if decreasing by nums[a]?
        // => for a nums[i], binary search the nums[a] < nums[i], then append there.  => MODIFIED BELOW.
        // S = {[1, 1]}
        //     {[1, 1], [80, 2]}
        //      {[1, 1], [80, 2], [90, 3]}
        //      {[1, 1], [80, 2], [90, 3], [100, 4]}
        //      {[1, 1], [2, 2], [90, 3], [100, 4]} ?? how can?
        // => stack is increasing by lis[a]? We also maintain the decreasing by nums[a]
        //      {[1, 1], [2, 80], [3, 90], [4, 100]}
        // When new nums[i], first, binary search the stack for greatest nums[a] < nums[i]
        //      - If not found => this is smaller than all item => replace stack to s[0] = [1, nums[i]]
        //      - Otherwise found index[j] => check if nums[i] < s[j+1][1] , if yes, then update the stack 
        // Because the first item in the array of 2 will always be incremented, 1-2-3-4... => it's also the index of the stack
        // => we no need to store the index, just need to store the nums[a]

        int n = nums.length;
        ArrayList<Integer> increasingLis = new ArrayList<>();
        increasingLis.add(nums[0]);
        
        for (int i = 1; i < n; i++) {
            int smallerIdx = greatestIndexOfSmaller(increasingLis, nums[i]);
            if (smallerIdx == increasingLis.size() - 1) {
                increasingLis.add(nums[i]);
            } else {
                int nextIdx = smallerIdx + 1;
                if (increasingLis.get(nextIdx) > nums[i]) {
                    increasingLis.set(nextIdx, nums[i]);
                }
            }
        }
        return increasingLis.size();
    }

    private int greatestIndexOfSmaller(ArrayList<Integer> increasingLis, int val) {
        int l = 0, r = increasingLis.size() - 1;
        while (r - l >= 0) {
            int mid = l + (r - l) / 2;
            if (increasingLis.get(mid) >= val) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return r;
    }
}

// Solution 1: Basic/traditional dp table build. For all possible j position before i, append nums[i] to the lis[j] and get the max.
// Time complexity: O(n^2)
// Space complexity: O(n)
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] lis = new int[n];
        Arrays.fill(lis, 1);

        int res = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                    res = Math.max(res, lis[i]);
                }
            }
        }
        return res;
    }
}
