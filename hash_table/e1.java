// https://leetcode.com/problems/two-sum/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (seen.containsKey(target - nums[i])) {
                return new int[]{i, seen.get(target - nums[i])};
            }
            seen.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}
