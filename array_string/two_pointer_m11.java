// https://leetcode.com/problems/container-with-most-water/

class Solution {
    public int maxArea(int[] height) {
        // n = 1e5, height = 1e4 => multiply = 1e9 wont exceed integer
        // res = max((i2 - i1) * min(height[i2], height[i1]))
        // n = 1e5 => brute-force with O(n^2) won't work
        // Output the res = max area.

        // We can start with two lines from both ends of the array.
        // As we want to increase the area, we will shrink from the shorter line (with hope that will get larger)
        // Shrink untill 2 lines meet.
        // Time complexity: O(n)
        // Space complexity: O(1)

        int l = 0, r = height.length - 1;
        int res = 0;
        while (l < r) {
            int area = (r - l) * Math.min(height[l], height[r]);
            res = Math.max(res, area);
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return res;
    }
}
