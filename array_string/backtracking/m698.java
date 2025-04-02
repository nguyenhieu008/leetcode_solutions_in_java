// https://leetcode.com/problems/partition-to-k-equal-sum-subsets/description/

class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length;
        boolean[] visited = new boolean[n];
        int sum = 0;
        for (int v : nums) {
            sum += v;
        }
        if (sum % k != 0) return false;
        return canPartition(nums, visited, 0, k, 0, sum / k);
    }

    public boolean canPartition(int[] nums, boolean[] visited, int startIndex, int k, int curSum, int target) {
        if (k == 1) return true;
        if (curSum > target) return false;
        if (curSum == target) return canPartition(nums, visited, 0, k - 1, 0, target);

        for (int i = startIndex; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (canPartition(nums, visited, i + 1, k, curSum + nums[i], target)) return true;
                visited[i] = false;
            }
        }
        return false;
    }
}
