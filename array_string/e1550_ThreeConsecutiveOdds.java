// https://leetcode.com/problems/three-consecutive-odds/

class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        int n = arr.length;
        // No need to handle edge case n < 3, because only enter loop if size >= 3
        for (int i = 2; i < n; i++) {
            if (arr[i - 2] % 2 == 1 && arr[i - 1] % 2 == 1 && arr[i] % 2 == 1) {
                return true;
            }
        }
        return false;
    }
}
