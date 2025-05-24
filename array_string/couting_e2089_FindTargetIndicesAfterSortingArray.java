// https://leetcode.com/problems/find-target-indices-after-sorting-array/description/

// Solution 2a: Only count target and less value, no need to store all
class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int equal = 0, less = 0;
        for (int v : nums) {
            if (v == target) {
                equal++;
            } else if (v < target) {
                less++;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < equal; i++) {
            res.add(less + i);
        }
        return res;
    }
}

// Solution 2: Counting
class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int maxValue = 100;
        int[] count = new int[maxValue + 1];
        for (int v : nums) {
            count[v]++;
        }
        for (int i = 1; i <= maxValue; i++) {
            count[i] += count[i-1];
        }

        List<Integer> res = new ArrayList<>();
        for (int start = count[target - 1]; start < count[target]; start++) {
            res.add(start);
        }
        return res;
    }
}

// Solution 1: Sort
class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {
                res.add(i);
            }
        }
        return res;
    }
}
