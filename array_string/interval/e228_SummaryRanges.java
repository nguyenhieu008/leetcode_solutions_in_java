// https://leetcode.com/problems/summary-ranges/description/
// No thing to special, just think how to code it in clean way.

class Solution {
    public List<String> summaryRanges(int[] nums) {
        int n = nums.length;
        List<String> res = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int start = nums[i];
            while (i + 1 < n && nums[i+1] == nums[i] + 1) {
                i++;
            }
            if (nums[i] == start) {
                res.add(String.valueOf(start));
            } else {
                res.add(String.format("%d->%d", start, nums[i]));
            }
        }
        return res;
    }
}
