// https://leetcode.com/problems/3sum/description/

// Detail in comment.
// Time complexity: O(n^2) as a loop for i, j and k use another nested for loop
// Space complexity: O(1); it depends on sort algorithm. Whether it use extra space

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // Assume i < j < k and num[i] + num[j] + num[k] == 0
        // length = 3 * 1e3 => O(n^2) could work
        // return all triplets regarless of order

        // Tested with [-1,0,1,2,-1,-4, -1, -1, 2]
        // And should return only [[-4,2,2],[-1,-1,2],[-1,0,1]]
        // => Multiple [-1,-1,2] should be returned as one.

        // We should sort the array, so we can move i < j < k better
        // But the hash table will be hard to remove duplicate
        // We will use two pointer on sorted array
        // for every i: 0 -> n - 2
        //      j = i + 1, k = n - 1. While l < r
        //      if (sum > 0) => need decrease => k-- until num[k] != num[k+1]
        //      else (sum == 0) => j++ until , k-- until
        //      else (sum < 0) => need increase => j++ until num[j] != num[j-1]

        int n = nums.length;
        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n - 2; i++) {
            int j = i + 1, k = n - 1;

            while (j < k) {
                int total = nums[i] + nums[j] + nums[k];
                if (total == 0) {
                    // Add to result then move both pointers
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    // Because we don't wnat duplicate triplet
                    // move j by 1 first, then continue if nums[j] == previous one. Backward check.
                    j++;
                    while(j < k && nums[j] == nums[j-1]) j++;
                    // decrement k by 1 first, then continue if nums[k] == nums[k+1]. Backward check when decreasing.
                    // Dont need: while (j < k && nums[k] == nums[k+1]) k--; as the j already moved to another greater one => it won't yield duplciate result
                } else if (total < 0) {
                    // Need to increase the total
                    j++;
                    // First, i use this: while(j < k && nums[j] == nums[j-1]) j++;
                    // But dont' need because the same item would also cause the same total
                    // => it won't be the duplicate result
                } else {
                    // Need to decrease the total
                    k--;
                    // Same item wont' be duplicate result => dont need:
                    // while (j < k && nums[k] == nums[k+1]) k--;
                }
            }
            // Increment until the last same item. 
            // => i++ in the for will move it to the next one
            while(i < n - 2 && nums[i] == nums[i + 1]) i++;
        }


        return res;
    }
}
