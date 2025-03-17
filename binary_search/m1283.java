// https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/description/
// Same as https://leetcode.com/problems/koko-eating-bananas/description/ . https://github.com/nguyenhieu008/leetcode_solutions_in_java/blob/main/binary_search/m875.java


class Solution {
    private boolean isSumLessEqualToThreshold(int[] nums, int threshold, int divisor) {
        int res = 0;
        for (int v : nums) {
            res += v / divisor;
            if (v % divisor > 0) {
                res++;
            }
        }
        return res <= threshold;
    }
    public int smallestDivisor(int[] nums, int threshold) {
        int l = 1, r = Integer.MAX_VALUE;

        while (r - l > 0) {
            int mid = l + (r - l) / 2;
            if (isSumLessEqualToThreshold(nums, threshold, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
